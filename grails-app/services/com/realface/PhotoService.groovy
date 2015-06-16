package com.realface

import grails.transaction.Transactional
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

@Transactional
class PhotoService
{
    def storageService

    File baseDir

    public UserPhoto save(BufferedImage image, String format)
    {
        UserPhoto photo = new UserPhoto()
        photo.save(failOnError: true)

        File file = getFile(photo)
        if (!file.exists())
        {
            ImageIO.write(image, format, file)
        }
        return photo
    }

    public File getBaseDir()
    {
        if (baseDir == null)
        {
            baseDir = new File(storageService.getBaseDir(), UserPhoto.DIR)
            if (!baseDir.exists())
                baseDir.mkdirs()
        }
        return baseDir
    }

    public File getFile(UserPhoto photo)
    {
        return new File(getBaseDir(), photo.getFileName())
    }
}