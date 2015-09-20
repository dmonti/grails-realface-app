import com.realface.AccessPoint
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
        enrollService.loadCache()
        userService.bootStrap()

        if (!AccessPoint.count()) {
            new AccessPoint(name: "Ponto de acesso padrão", cameraIdx: 0).save()
        }
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