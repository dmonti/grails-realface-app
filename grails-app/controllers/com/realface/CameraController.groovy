package com.realface

import com.github.sarxos.webcam.Webcam
import java.awt.Dimension
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class CameraController
{
    def test()
    {
        //FaceRecognition faceRecognition = new FaceRecognition()
        //faceRecognition.learn("/Volumes/dmonti/Development/workspace/realface/realface-cv/data/all10.txt")

        //faceRecognition.recognizeFileList(params.fileName)
    }

    def init()
    {
        FaceRecognition2 faceRecognition = new FaceRecognition2()
        faceRecognition.learn()
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

        return render(contentType: "text/json") { [ name: fileName ] };
    }
}