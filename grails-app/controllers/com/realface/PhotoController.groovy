package com.realface

class PhotoController
{
    def identificationService

    def capture()
    {
        PhotoTemplate photo = identificationService.capture()
        return render(contentType: "text/json") { photo }
    }

    def index()
    {
        [photos: PhotoTemplate.list(sort: "id", order: "desc")]
    }

    def resource()
    {
        PhotoTemplate photo = PhotoTemplate.get(params.id)
        if (photo)
        {
            File file = identificationService.getPhotoFile(photo)
            return render(file: file, contentType: "image/png")
        }
        else
        {
            return render(0)
        }
    }
}