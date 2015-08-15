package com.realface

import com.realface.UserAccessLog.Status

class AccessController
{
    private static final byte OK = 1
    private static final byte NOK = 0

    def userService

    def accessService

    def userAccessLogService

    def identificationService

    def login(LoginCommand command)
    {
        boolean hasErrors = command.hasErrors()
        if (!hasErrors)
        {
            String username = command.username
            User user = userService.findUser(username)
            session.currentUserId = user.id

            userAccessLogService.create(username, Status.LOGGED_IN)
        }

        return render(contentType: "text/json") {[
            status: hasErrors ? NOK : OK,
            message: hasErrors ? message(error: command.errors.allErrors.first()) : null
        ]}
    }

    def logout()
    {
        session.currentUserId = null
        return redirect(uri: "/login")
    }

    def camera()
    {
        def accessPoint = AccessPoint.get(params.id)

        return [accessPoint: accessPoint]
    }

    def snapshot()
    {
        String dataUri = params.dataUri
        dataUri = dataUri.substring(dataUri.indexOf(",") + 1)

        byte[] data = Base64.getDecoder().decode(dataUri)
        identificationService.savePhoto(data)

        def accessPoint = AccessPoint.get(params.id)

        render(params)
    }
}