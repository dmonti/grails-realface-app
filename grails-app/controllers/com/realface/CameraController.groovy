package com.realface

import java.io.File

class CameraController
{
    private static final String STORAGE_PATH = "/Volumes/dmonti/Development/Realface/workspace/realface-app/target/"

    def cameraService

    def templateService

    def photo()
    {
        File file = new File("${STORAGE_PATH}tmp/${params.id}.png")
        return render(file: file, contentType: "image/png")
    }

    def test() { }

    def recognize()
    {
        String id = params.user.id

        File file = new File("${STORAGE_PATH}tmp/test.png")
        cameraService.shootAndSaveOnFile(file)

        File templateFile = new File(file.getParent(), "test.template")
        templateService.saveFrom(id, file, templateFile)

        File templateTest = new File("${STORAGE_PATH}tmp/${id}.template")
        templateService.recognize(templateTest, templateFile)

        return render(contentType: "text/json") {[ test: test ]}
    }

    def init()
    {
        render(1)
    }

    def shoot()
    {
        String id = params.id

        File file = new File("${STORAGE_PATH}tmp/${id}.png")
        cameraService.shootAndSaveOnFile(file)

        File templateFile = new File(file.getParent(), "${id}.template")
        templateService.saveFrom(id, file, templateFile)

        return render(contentType: "text/json") {[ name: fileName ]}
    }
}