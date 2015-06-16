package com.realface

import java.io.File

class PhotoController
{
    def photoService

    def index()
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