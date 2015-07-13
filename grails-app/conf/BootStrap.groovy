import com.realface.LibraryManager
import com.neurotec.plugins.NDataFileManager

class BootStrap
{
    def userService
    def deviceService
    def enrollService
    def licenseService

    def grailsApplication

    def init = {  servletContext ->
        LibraryManager.initLibraryPath(getSDKHome())

        NDataFileManager.getInstance().addFromDirectory(getNDataFilePath(), false)

        licenseService.obtainAll()
        deviceService.init()
        // deviceService.update()
        // enrollService.loadCache()
        userService.bootStrap()
    }

    def destroy = { }

    private String getSDKHome()
    {
        return grailsApplication.config.neurotec.sdk.home
    }

    private String getNDataFilePath()
    {
        return "${getSDKHome()}${File.separator}Bin${File.separator}Data"
    }
}