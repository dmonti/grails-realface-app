package com.realface

import java.io.File

class CameraController
{
    def storageService

    def cameraService

    def templateService

    def test() { }

    def shoot()
    {
        UserPhoto photo = cameraService.shoot()
        templateService.generate(photo)
        return render(contentType: "text/json") { photo }
    }

    def recognize()
    {
        println("params: ${params}")
        UserPhoto photo1 = UserPhoto.get(params.id1)
        UserPhoto photo2 = UserPhoto.get(params.id2)

        File template1 = templateService.getFile(photo1.template)
        File template2 = templateService.getFile(photo2.template)

        templateService.recognize(template1, template2)

        return render(1)
    }
}