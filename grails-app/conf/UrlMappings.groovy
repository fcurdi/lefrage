class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/$username/perfil"(controller: "wall", action: "index")
        "/$username/amigos"(controller: "friends", action: "index")
        "/$username/busquedas"(controller: "wall", action: "searches")
        "/" (controller: "wall", action: "indexBypass") //need logged user
        "500"(view:'/error')
	}
}
