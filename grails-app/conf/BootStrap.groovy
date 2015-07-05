import com.realface.LibraryManager
import com.neurotec.plugins.NDataFileManager

class BootStrap
{
    def userService
    def deviceService
    def grailsApplication
    def identificationService

    def init = {  servletContext ->
        String sdkHome = grailsApplication.config.neurotec.sdk.home

        LibraryManager.initLibraryPath(sdkHome)
        NDataFileManager.getInstance().addFromDirectory(getNDataFilePath(), false)

        deviceService.init()
        userService.bootStrap()
        identificationService.loadCache()
    }

    def destroy = { }

    public String getNDataFilePath()
    {
        String sdkHome = grailsApplication.config.neurotec.sdk.home
        return "${sdkHome}${File.separator}Bin${File.separator}Data"
    }
}