import com.realface.AccessPoint
import com.realface.LibraryManager
import com.neurotec.plugins.NDataFileManager
import java.lang.ExceptionInInitializerError
import java.lang.NoClassDefFoundError

class BootStrap
{
    def userService
    def deviceService
    def enrollService
    def licenseService

    def grailsApplication

    def init = {  servletContext ->
        LibraryManager.initLibraryPath(getSDKHome())

        try {
            NDataFileManager.getInstance().addFromDirectory(getNDataFilePath(), false)
        } catch (ExceptionInInitializerError e) {
            log.warn("Exception initializing NDataFileManager.", e)
        }

        try {
            licenseService.obtainAll()
            deviceService.init()
            // deviceService.update()
            enrollService.loadCache()
        } catch (NoClassDefFoundError e) {
            log.warn("Neurotec license not load.", e)
        }

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