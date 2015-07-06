import com.realface.LibraryManager
import com.neurotec.plugins.NDataFileManager
import com.neurotec.samples.FaceTools

class BootStrap
{
    def userService
    def deviceService
    def grailsApplication
    def identificationService

    def init = {  servletContext ->
        FaceTools.getInstance().obtainLicenses([
            "Devices.Cameras",
            "Biometrics.FaceMatching",
            "Biometrics.FaceDetection",
            "Biometrics.FaceExtraction",
            "Biometrics.FaceSegmentsDetection"
        ])

        String sdkHome = grailsApplication.config.neurotec.sdk.home

        LibraryManager.initLibraryPath(sdkHome)
        NDataFileManager.getInstance().addFromDirectory(getNDataFilePath(), false)

        deviceService.init()
        // deviceService.update()

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