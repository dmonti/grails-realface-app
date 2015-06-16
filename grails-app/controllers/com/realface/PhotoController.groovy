package com.realface

import java.io.File

class PhotoController
{
    def photoService

    def index()
    {
        [photos: UserPhoto.list(sort: "id", order: "desc")]
    }

    def resource()
    {
        File file
        UserPhoto photo = UserPhoto.get(params.id)
        if (photo)
        {
            file = photoService.getFile(photo)
        }
        return render(file: file, contentType: "image/png")
    }
}