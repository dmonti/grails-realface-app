import com.neurotec.plugins.NDataFileManager;

class BootStrap
{
    def userService;

    def init = {  servletContext ->
        NDataFileManager.getInstance().addFromDirectory("/Volumes/dmonti/Development/Realface/Neurotec_Biometric_5_1_SDK_Trial/Bin/Data", false)
        userService.bootStrap();
    }

    def destroy = { }
}