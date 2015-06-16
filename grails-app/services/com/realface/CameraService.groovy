package com.realface

import java.awt.Dimension
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import com.github.sarxos.webcam.Webcam

class CameraService
{
    static transactional = false

    private static final String DEFAULT_FORMAT = "PNG"
    private static final Dimension DEFAULT_PHOTO_DIMENSION = new Dimension(640, 480)

    def photoService

    public UserPhoto shoot()
    {
        UserPhoto photo
        Webcam webcam = Webcam.getDefault()
        try
        {
            webcam.setViewSize(DEFAULT_PHOTO_DIMENSION)
            webcam.open()

            BufferedImage image = webcam.getImage()
            photo = photoService.save(image, DEFAULT_FORMAT)
        }
        catch(Exception e)
        {
            log.warn("Exception shooting photo.", e)
        }
        finally
        {
            webcam.close()
        }
        return photo
    }
}