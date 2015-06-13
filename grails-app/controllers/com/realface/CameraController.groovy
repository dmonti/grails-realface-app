package com.realface

import com.github.sarxos.webcam.Webcam
import java.awt.Dimension
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

import com.neurotec.biometrics.NBiometricStatus;
import com.neurotec.biometrics.NBiometricTask;
import com.neurotec.biometrics.NFace
import com.neurotec.biometrics.NLAttributes
import com.neurotec.biometrics.NSubject
import com.neurotec.biometrics.NBiometricOperation;
import com.neurotec.util.concurrent.CompletionHandler;

import com.neurotec.samples.FaceTools

class CameraController
{
    private static final String STORAGE_PATH = "/Volumes/dmonti/Development/Realface/workspace/realface-app/target/"

    private static final Dimension DEFAULT_PHOTO_DIMENSION = new Dimension(640, 480)

    private final ImageCreationHandler imageCreationHandler = new ImageCreationHandler();

    private NSubject subject
    private NFace tokenFace

    def test() { }

    def recognize()
    {
        NFace face = new NFace()
        face.setFileName("${STORAGE_PATH}tmp/${params.photo.name}")
        subject = new NSubject()
        subject.getFaces().add(face)

        NBiometricTask task = FaceTools.getInstance().getClient().createTask(EnumSet.of(NBiometricOperation.SEGMENT, NBiometricOperation.ASSESS_QUALITY), subject);
        FaceTools.getInstance().getClient().performTask(task, null, imageCreationHandler)

        return render(1)
    }

    def init()
    {
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

        return render(contentType: "text/json") {[ name: fileName ]}
    }

    private class ImageCreationHandler implements CompletionHandler<NBiometricTask, Void>
    {
        @Override
        public void completed(final NBiometricTask task, final Void attachment)
        {
            NBiometricStatus status = task.getStatus();
            println("run status: ${status}")
            if (status == NBiometricStatus.OK) {
                tokenFace = subject.getFaces().get(1);
            } else {
                tokenFace = null;
            }
        }

        @Override
        public void failed(final Throwable th, final Void attachment)
        {
            println(">>>> FAILED!!!")
            tokenFace = null;
        }
    }
}