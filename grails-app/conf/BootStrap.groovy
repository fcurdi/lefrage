import security.Role
import security.SpringUser
import security.SpringUserRole
import java.util.Date
import lefrage.User
import lefrage.Post

class BootStrap {

	def init = { servletContext ->
    	def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true, failOnError: true);
		def userRole = new Role(authority: 'ROLE_USER').save(flush: true, failOnError: true);
		
		def adminUser = new SpringUser(username: 'root', password: 'root');
		adminUser.save(flush: true, failOnError: true);

		SpringUserRole.create(adminUser, adminRole, true);

		User us1 = new User(name: "Shai", surname: "Bianchi", dateOfBirth: new Date())
		def springUs1 = new SpringUser(username: "shai", password: "shai")
		springUs1.save(flush: true, failOnError: true)
		us1.springUser = springUs1
		us1.save(flush:true, failOnError: true)
		SpringUserRole.create(springUs1, userRole, true)

		User us2 = new User(name: "Lucas", surname: "Rossi", dateOfBirth: new Date())
		def springUs2 = new SpringUser(username: "lucas", password: "lucas")
		springUs2.save(flush: true, failOnError: true)
		us2.springUser = springUs2
		us2.save(flush:true, failOnError: true)
		SpringUserRole.create(springUs2, userRole, true)

		User us3 = new User(name: "Pedro", surname: "Curdi", dateOfBirth: new Date())
		def springUs3 = new SpringUser(username: "pedro", password: "pedro")
		springUs3.save(flush: true, failOnError: true)
		us3.springUser = springUs3
		us3.save(flush:true, failOnError: true)
		SpringUserRole.create(springUs3, userRole, true)

		User us4 = new User(name: "Juan", surname: "Mozart", dateOfBirth: new Date())
		def springUs4 = new SpringUser(username: "juan", password: "juan")
		springUs4.save(flush: true, failOnError: true)
		us4.springUser = springUs4
		us4.save(flush:true, failOnError: true)
		SpringUserRole.create(springUs4, userRole, true)

		User us5 = new User(name: "Ken", surname: "Nek", dateOfBirth: new Date())
		def springUs5 = new SpringUser(username: "ken", password: "ken")
		springUs5.save(flush: true, failOnError: true)
		us5.springUser = springUs5
		us5.save(flush:true, failOnError: true)
		SpringUserRole.create(springUs5, userRole, true)


		User us6 = new User(name: "Jorge", surname: "Basta", dateOfBirth: new Date())
		def springUs6 = new SpringUser(username: "jorge", password: "jorge")
		springUs6.save(flush: true, failOnError: true)
		us6.springUser = springUs6
		us6.save(flush:true, failOnError: true)
		SpringUserRole.create(springUs6, userRole, true)

		User us7 = new User(name: "Eduardo", surname: "Tables", dateOfBirth: new Date())
		def springUs7 = new SpringUser(username: "eduardo", password: "eduardo")
		springUs7.save(flush: true, failOnError: true)
		us7.springUser = springUs7
		us7.save(flush:true, failOnError: true)
		SpringUserRole.create(springUs7, userRole, true)

		us1.addToFriends(us2)
		us2.addToFriends(us1)

		us1.addToFriends(us3)
		us3.addToFriends(us1)

		us1.addToFriends(us4)
		us4.addToFriends(us1)

		us1.addToFriends(us5)
		us5.addToFriends(us1)

		us1.addToFriends(us6)
		us6.addToFriends(us1)

		us2.addToFriends(us5)
		us5.addToFriends(us2)

		us2.addToFriends(us6)
		us6.addToFriends(us2)


		Date newDate1 = new Date()
		def newPost1 = new Post(
			containingWallUser: us1,
			author: us1,
			content: 'que deje de cortarse la luz porfi',
			date: newDate1,
			product: null,
			isAutoPost: true
		)
		
		newPost1.save(flush: true, failOnError: true)

		Date newDate2 = new Date()
		def newPost2 = new Post(
			containingWallUser: us1,
			author: us1,
			content: 'postis es molesto',
			date: newDate2,
			product: null,
			isAutoPost: true
		)
		newPost2.save(flush: true, failOnError: true)

		Date newDate3 = new Date()
		def newPost3 = new Post(
			containingWallUser: us1,
			author: us1,
			content: 'se me apaga el wifi',
			date: newDate3,
			product: null,
			isAutoPost: true
		)
		newPost3.save(flush: true, failOnError: true)

		us1.addToWallPosts(newPost1)
		us1.addToWallPosts(newPost2)
		us1.addToWallPosts(newPost3)
    }

    def destroy = {
    }
}
