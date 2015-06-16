package com.realface

import java.io.File

class StorageService
{
    static transactional = false

    private static final String STORAGE_PATH = "/Volumes/dmonti/Development/Realface/storage"

    private File baseDir

    public File getBaseDir()
    {
        if (baseDir == null)
        {
            baseDir = new File("${STORAGE_PATH}/")
            if (!baseDir.exists())
            {
                baseDir.mkdirs()
            }
        }
        return baseDir
    }
}