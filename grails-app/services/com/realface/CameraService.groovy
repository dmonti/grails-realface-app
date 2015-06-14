package com.realface

import java.awt.Dimension
import java.io.File
import javax.imageio.ImageIO
import com.github.sarxos.webcam.Webcam

class CameraService
{
    private static final Dimension DEFAULT_PHOTO_DIMENSION = new Dimension(640, 480)

    def shootAndSaveOnFile(File file, String format = "PNG")
    {
        Webcam webcam = Webcam.getDefault()
        webcam.setViewSize(DEFAULT_PHOTO_DIMENSION)
        try
        {
            webcam.open()
            ImageIO.write(webcam.getImage(), format, file)
        }
        catch(Exception e)
        {
            log.warn("Exception shooting photo.", e)
        }
        finally
        {
            webcam.close()
        }
    }
}