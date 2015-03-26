class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/$username/perfil"(controller: "wall", action: "index")
        "/$username/amigos"(controller: "friends", action: "index")
        "/"(view:"/index")
        "500"(view:'/error')
	}
}
