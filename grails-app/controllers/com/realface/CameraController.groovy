package com.realface

import com.github.sarxos.webcam.Webcam
import java.awt.Dimension
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class CameraController
{
    def test() { }

    def recognize()
    {
        File file = new File("/Volumes/dmonti/Development/workspace/realface/realface-app/target/tmp/${System.currentTimeMillis()}.txt")

        User user = User.get(params.user.id)
        String filePath = "/Volumes/dmonti/Development/workspace/realface/realface-app/target/tmp/${params.photo.name}"
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
        webcam.setViewSize(new Dimension(640, 480))
        webcam.open()

        BufferedImage image = webcam.getImage()
        File file = new File("/Volumes/dmonti/Development/workspace/realface/realface-app/target/tmp/${fileName}")
        ImageIO.write(image, "PNG", file)

        webcam.close()

        return render(contentType: "text/json") { [ name: fileName ] }
    }
}