package com.realface

import com.neurotec.samples.util.Utils
import com.sun.jna.Platform
import java.io.File
import java.lang.reflect.Field
import org.apache.log4j.Logger

public final class LibraryManager
{
    private static final String WIN32_X86 = "Win32_x86";
    private static final String WIN64_X64 = "Win64_x64";
    private static final String LINUX_X86 = "Linux_x86";
    private static final String LINUX_X86_64 = "Linux_x86_64";
    private static final String MAC_OS = "/Library/Frameworks/";

    private static final Logger log = Logger.getLogger(LibraryManager.class)

    public static void initLibraryPath(String sdkHome)
    {
        String libraryPath = getLibraryPath(sdkHome);
        log.debug("Setting library path: ${libraryPath}.")

        String jnaLibraryPath = System.getProperty("jna.library.path");
        if (Utils.isNullOrEmpty(jnaLibraryPath))
        {
            System.setProperty("jna.library.path", libraryPath.toString());
        }
        else
        {
            System.setProperty("jna.library.path", String.format("%s%s%s", jnaLibraryPath, Utils.PATH_SEPARATOR, libraryPath.toString()));
        }

        System.setProperty("java.library.path",String.format("%s%s%s", System.getProperty("java.library.path"), Utils.PATH_SEPARATOR, libraryPath.toString()));

        try
        {
            Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
            fieldSysPath.setAccessible(true);
            fieldSysPath.set(null, null);
        }
        catch (Exception e)
        {
            log.warn("Exception initializing library path!", e)
        }
    }

    public static String getLibraryPath(String sdkHome)
    {
        String path
        if (Platform.isMac())
        {
            path = MAC_OS
        }
        else
        {
            path = sdkHome + File.separator + "Bin" + File.separator
            if (Platform.isWindows())
            {
                return path + (Platform.is64Bit() ? WIN64_X64 : WIN32_X86)
            }
            else if (Platform.isLinux())
            {
                return path + (Platform.is64Bit() ? LINUX_X86_64 : LINUX_X86)
            }
        }
        return path
    }
}