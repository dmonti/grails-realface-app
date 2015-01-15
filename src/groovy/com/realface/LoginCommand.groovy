package com.realface

import grails.validation.Validateable;
import com.realface.UserAccessLog.Status;

@Validateable
class LoginCommand
{
    def userAccessLogService;

    String username;
    String password;

    static constraints =
    {
        username(blank: false, size: 4..64, validator: { value, command ->
            return command.usernameValidator(value);
        });
        password(blank: false, size: 8..16, validator: { value, command ->
            return command.passwordValidator(value);
        });
    }

    public boolean usernameValidator(String value)
    {
        boolean exists = User.existsUsernameOrEmail(value);
        if (!exists)
            userAccessLogService.create(value, Status.INVALID_USERNAME);

        return exists;
    }

    public boolean passwordValidator(String value)
    {
        User user = User.findUser(username);
        if (user == null)
        {
            userAccessLogService.create(value, Status.INVALID_USERNAME);
            return false;
        }

        boolean passwordChecked = user.checkPassword(password);
        if (!passwordChecked)
            userAccessLogService.create(value, Status.INVALID_PASSWORD);

        return passwordChecked;
    }
}