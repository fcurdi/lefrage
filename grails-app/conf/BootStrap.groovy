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

		User us3 = new User(name: "Pedro", surname: "Asadad", dateOfBirth: new Date())
		def springUs3 = new SpringUser(username: "pedro", password: "pedro")
		springUs3.save(flush: true, failOnError: true)
		us3.springUser = springUs3
		us3.save(flush:true, failOnError: true)
		SpringUserRole.create(springUs3, userRole, true)

		User us4 = new User(name: "Juan", surname: "Rqweqwe", dateOfBirth: new Date())
		def springUs4 = new SpringUser(username: "juan", password: "juan")
		springUs4.save(flush: true, failOnError: true)
		us4.springUser = springUs4
		us4.save(flush:true, failOnError: true)
		SpringUserRole.create(springUs4, userRole, true)

		User us5 = new User(name: "Ken", surname: "Wasdasdas", dateOfBirth: new Date())
		def springUs5 = new SpringUser(username: "ken", password: "ken")
		springUs5.save(flush: true, failOnError: true)
		us5.springUser = springUs5
		us5.save(flush:true, failOnError: true)
		SpringUserRole.create(springUs5, userRole, true)


		User us6 = new User(name: "Jorge", surname: "Qwgeyqe", dateOfBirth: new Date())
		def springUs6 = new SpringUser(username: "jorge", password: "jorge")
		springUs6.save(flush: true, failOnError: true)
		us6.springUser = springUs6
		us6.save(flush:true, failOnError: true)
		SpringUserRole.create(springUs6, userRole, true)

		User us7 = new User(name: "Eduardo", surname: "asdadsasd", dateOfBirth: new Date())
		def springUs7 = new SpringUser(username: "eduardo", password: "eduardo")
		springUs7.save(flush: true, failOnError: true)
		us7.springUser = springUs7
		us7.save(flush:true, failOnError: true)
		SpringUserRole.create(springUs7, userRole, true)

		us1.addToFriends(us2)
		us2.addToFriends(us1)
		us1.addToFriends(us3)
		us1.addToFriends(us4)
		us1.addToFriends(us5)
		us1.addToFriends(us6)


		def newPost1 = new Post()
		Date newDate1 = new Date()
		newPost1.author = us1
		newPost1.content = 'que deje de cortarse la luz porfi'
		newPost1.date = newDate1
		newPost1.save(flush: true, failOnError: true)

		def newPost2 = new Post()
		Date newDate2 = new Date()
		newPost2.author = us1
		newPost2.content = 'postis es molesto'
		newPost2.date = newDate2
		newPost2.save(flush: true, failOnError: true)

		def newPost3 = new Post()
		Date newDate3 = new Date()
		newPost3.author = us1
		newPost3.content = 'se me apaga el wifi'
		newPost3.date = newDate3
		newPost3.save(flush: true, failOnError: true)

		us1.addToPosts(newPost1)
		us1.addToPosts(newPost2)
		us1.addToPosts(newPost3)
		
    }
    def destroy = {
    }
}
