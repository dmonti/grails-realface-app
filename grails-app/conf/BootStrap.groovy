import com.neurotec.plugins.NDataFileManager

class BootStrap
{
    def userService

    def grailsApplication

    def init = {  servletContext ->
        NDataFileManager.getInstance().addFromDirectory(getNDataFilePath(), false)
        userService.bootStrap()
    }

    def destroy = { }

    public String getNDataFilePath()
    {
        return grailsApplication.config.neurotec.dataFile.path
    }
}