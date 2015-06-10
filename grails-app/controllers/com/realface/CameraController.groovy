package com.realface

import com.github.sarxos.webcam.Webcam
import java.awt.Dimension
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class CameraController
{
    private static final String STORAGE_PATH = "/Volumes/dmonti/Development/workspace/realface/realface-app/target/"

    private static final Dimension DEFAULT_PHOTO_DIMENSION = new Dimension(640, 480)

    def test() { }

    def recognize()
    {
        File file = new File("${STORAGE_PATH}tmp/${System.currentTimeMillis()}.txt")

        User user = User.get(params.user.id)
        String filePath = "${STORAGE_PATH}tmp/${params.photo.name}"
        file << ("${user.id} ${user.name} ${filePath}\n")

        FaceRecognition faceRecognition = new FaceRecognition()

        String msg
        boolean test = faceRecognition.recognizeFileList(file.getAbsolutePath())
        if (test)
            msg = "Usuário ${user.name} identificado."
        else
            msg = "Usuário ${user.name} não reconhecido."

        return render(contentType: "text/json") { [ test: test, message: msg ] }
    }

    def init()
    {
        FaceRecognition faceRecognition = new FaceRecognition()
        java.util.List<UserPhoto> photos = UserPhoto.findAll()
        faceRecognition.learn2(photos)
        render(1)
    }

    def shoot()
    {
        String fileName = "${System.currentTimeMillis()}.png"

        Webcam webcam = Webcam.getDefault()
        webcam.setViewSize(DEFAULT_PHOTO_DIMENSION)
        webcam.open()

        BufferedImage image = webcam.getImage()
        File file = new File("${STORAGE_PATH}tmp/${fileName}")
        ImageIO.write(image, "PNG", file)

        webcam.close()

        return render(contentType: "text/json") { [ name: fileName ] }
    }
}