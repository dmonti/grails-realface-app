package com.realface

import java.io.File

class CameraController
{
    private static final String STORAGE_PATH = "/Volumes/dmonti/Development/Realface/workspace/realface-app/target/"
    public static Boolean TEST = null;

    def cameraService

    def templateService

    def test() { }

    def photo()
    {
        File file = new File("${STORAGE_PATH}tmp/${params.id}.png")
        return render(file: file, contentType: "image/png")
    }

    def recognize()
    {
        CameraController.TEST = null

        String id = params.user.id
        File templateUser = new File("${STORAGE_PATH}tmp/${id}.template")
        File templateTest = new File("${STORAGE_PATH}tmp/test.template")

        templateService.recognize(templateUser, templateTest)

        return render(1)
    }

    def check()
    {
        return render(contentType: "text/json") {
            [ test: CameraController.TEST != null ? CameraController.TEST : "null" ]
        }
    }

    def shoot()
    {
        String id = params.id ?: params.user

        File file = new File("${STORAGE_PATH}tmp/${id}.png")
        file.delete()

        File templateFile = new File(file.getParent(), "${id}.template")
        templateFile.delete()

        cameraService.shootAndSaveOnFile(file)
        templateService.saveFrom(id, file, templateFile)

        return render(contentType: "text/json") {[ name: fileName ]}
    }
}