package com.realface

import java.io.File

class StorageService
{
    static transactional = false

    def grailsApplication

    private File baseDir
    private File photoBaseDir
    private File templateBaseDir

    public File getBaseDir()
    {
        if (baseDir == null)
        {
            baseDir = new File(getStoragePath())
            if (!baseDir.exists())
            {
                baseDir.mkdirs()
            }
        }
        return baseDir
    }

    public String getStoragePath()
    {
        return grailsApplication.config.realface.storage.path
    }

    public File getPhotoFile(PhotoTemplate photo)
    {
        if (photoBaseDir == null)
        {
            photoBaseDir = new File(getBaseDir(), "photos")
            if (!photoBaseDir.exists())
                photoBaseDir.mkdirs()
        }
        return new File(photoBaseDir, photo.getPhotoFileName())
    }

    public File getTemplateFile(PhotoTemplate photo)
    {
        if (templateBaseDir == null)
        {
            templateBaseDir = new File(getBaseDir(), "templates")
            if (!templateBaseDir.exists())
                templateBaseDir.mkdirs()
        }
        return new File(templateBaseDir, photo.getTemplateFileName())
    }
}