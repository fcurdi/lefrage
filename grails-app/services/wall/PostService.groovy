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
    	def textContent = prodContent.text
    	def prod = new Product(
    		productTitle: prodContent.title,
    		prodcutUrlImg: prodContent.image,
    		productPrice: prodContent.price
    	)
    	def newDate = new Date()
    	def newPost = new Post(
    		content: textContent,
    		product: prod,
    		date: newDate,
    		author: loggedUser,
    		containingWallUser: loggedUser
    	)

    	newPost.save(flush: true, failOnError: true)
    	wallOwner.addToWallPosts(newPost)

    }

    def textPost(loggedUser, username, htmlPostContent) { // verificar que no sea null el content?
		def urlSpringUser = SpringUser.findByUsername(username)
		def wallOwner = User.findBySpringUser(urlSpringUser)

	    Date newDate = new Date()
	    def newPost = new Post(
	     	content: htmlPostContent,
	        product: null,
	       	date: newDate,
	       	author: loggedUser,
	        containingWallUser: wallOwner
	    )

        println "content: " + newPost.content
        println "author: " + newPost.author
        println "containingWallUser: " + newPost.containingWallUser

        newPost.save(flush: true, failOnError: true)
        wallOwner.addToWallPosts(newPost)
    }
}
