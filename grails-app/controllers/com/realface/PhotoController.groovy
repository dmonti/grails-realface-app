package com.realface

class PhotoController
{
    def storageService
    def identificationService

    def index()
    {
        return [photos: PhotoTemplate.list(sort: "id", order: "desc")]
    }

    def create()
    {
    }

    def capture()
    {
        User user
        if (params.user?.id != null)
        {
            user = User.get(params.user.id)
        }

        PhotoTemplate photo = identificationService.capture(user)
        return render(contentType: "text/json") { photo }
    }

    def resource()
    {
        PhotoTemplate photo = PhotoTemplate.get(params.id)
        if (photo)
        {
            File file = storageService.getPhotoFile(photo)
            return render(file: file, contentType: "image/png")
        }
        else
        {
            response.status = 404
            return
        }
    }
}