class UrlMappings
{
	static mappings =
    {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller:"home", action: "index")

        "/login"(view: "login")
        "/logout"(controller: "access", action: "logout")

        // Error pages
        "500"(view:'/error')
	}
}