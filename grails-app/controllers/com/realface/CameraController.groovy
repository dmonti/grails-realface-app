package com.realface

import java.io.File

class CameraController
{
    private static final String STORAGE_PATH = "/Volumes/dmonti/Development/Realface/workspace/realface-app/target/"

    def cameraService

    def templateService

    def test() { }

    def recognize()
    {
        render(1)
    }

    def init()
    {
        render(1)
    }

    def shoot()
    {
        String id = params.id

        File file = new File("${STORAGE_PATH}tmp/user-${id}.png")
        cameraService.shootAndSaveOnFile(file)
        templateService.saveFrom(id, file)

        return render(contentType: "text/json") {[ name: fileName ]}
    }
}