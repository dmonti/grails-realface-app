import com.neurotec.plugins.NDataFileManager

class BootStrap
{
    def userService

    def identificationService

    def grailsApplication

    def init = {  servletContext ->
        NDataFileManager.getInstance().addFromDirectory(getNDataFilePath(), false)

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