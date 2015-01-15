class BootStrap
{
    def userService;

    def init = {  servletContext ->
        userService.bootStrap();
    }

    def destroy = {
    }
}