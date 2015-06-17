package com.realface

class PhotoController
{
    def photoTemplateService

    def index()
    {
        [photos: PhotoTemplate.list(sort: "id", order: "desc")]
    }

    def resource()
    {
        File file
        PhotoTemplate photo = PhotoTemplate.get(params.id)
        if (photo)
        {
            file = photoTemplateService.getPhotoFile(photo)
        }
        return render(file: file, contentType: "image/png")
    }
}