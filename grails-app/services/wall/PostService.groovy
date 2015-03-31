package wall

import grails.transaction.Transactional
import security.*
import org.springframework.security.core.context.SecurityContextHolder // necessary to obtain current user
import lefrage.Post
import lefrage.Product
import lefrage.User
import java.util.Date

@Transactional
class PostService {

    def productPost(prodContent, loggedUser) {
        println "hola productPost(), prodContent.image: " + prodContent.image
        def prod = new Product(
            productTitle: prodContent.title,
            productUrlImg: prodContent.image,
            productPrice: prodContent.price
        )

        prod.save(flush: true, failOnError: true)
        println "hola productPost(), prod.productUrlImg: " + prod.productUrlImg
        def textContent = prodContent.text
        def newDate = new Date()
        def newPost = new Post(
            content: textContent,
            product: prod,
            date: newDate,
            author: loggedUser,
            containingWallUser: loggedUser,
            isAutoPost: true // modificar cuando este hecho el compartir de producto
        )

    	newPost.save(flush: true, failOnError: true)
    	loggedUser.addToWallPosts(newPost)

    }

    def textPost(loggedUser, username, htmlPostContent) { // verificar que no sea null el content?
		def urlSpringUser = SpringUser.findByUsername(username)
		def wallOwner = User.findBySpringUser(urlSpringUser)

        def autoPostBoolean = wallOwner.id == loggedUser.id

	    Date newDate = new Date()
	    def newPost = new Post(
	     	content: htmlPostContent,
	        product: null,
	       	date: newDate,
	       	author: loggedUser,
	        containingWallUser: wallOwner,
            isAutoPost: autoPostBoolean
	    )


        newPost.save(flush: true, failOnError: true)
        println "Hola. textPost(" + loggedUser.name + ", " + wallOwner.name + ", " + htmlPostContent + "). isAutoPost: " + newPost.isAutoPost
        wallOwner.addToWallPosts(newPost)
    }
}
