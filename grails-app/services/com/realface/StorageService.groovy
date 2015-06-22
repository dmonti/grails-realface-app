package com.realface

import java.io.File

class StorageService
{
    static transactional = false

    def grailsApplication

    private File baseDir

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
}