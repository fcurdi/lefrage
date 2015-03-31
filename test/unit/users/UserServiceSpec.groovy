package users
import lefrage.*
import security.*
import grails.test.mixin.TestFor
import spock.lang.Specification
/**
* See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
*/
@TestFor(UserService)
class UserServiceSpec extends Specification {

	void setup() {
		def springUser = new SpringUser(username: "lefrage", password: "lefrage")
		mockDomain(SpringUser, [springUser])
	
		def user = new User(springUser: springUser, name: "TestName", surname: "TestSurame", dateOfBirth: new Date())
		mockDomain(User, [user])
	
		def springSecurityMock = [currentUser: springUser]
		service.springSecurityService = springSecurityMock
	}

	//Tests Update User

	void "se puede cambiar, el nombre, apellido y la contraseña"() {
		given:
			def name = "NewName"
			def surname = "NewSurname"
			def date = "01/02/2003"
			def password = "newPassword"
		
		when:
			def result = service.update(name, surname, date, password)
		
		then:
			result.name == "NewName"
			result.surname == "NewSurname"
			result.springUser.password == "newPassword"
	}
	
	void "si no especifico la clave queda la anterior"() {
		given:
			def name = "NewName"
			def surname = "NewSurname"
			def date = "01/02/2003"
		
		when:
			def result = service.update(name, surname, date, null)
		
		then:
			result.name == "NewName"
			result.surname == "NewSurname"
			result.springUser.password == "lefrage"
	}

	void "si la contraseña es vacia no cambia la clave"() {
		given:
			def name = "NewName"
			def surname = "NewSurname"
			def date = "01/02/2003"
		
		when:
			def result = service.update(name, surname, date, "")
		
		then:
			result.name == "NewName"
			result.surname == "NewSurname"
			result.springUser.password == "lefrage"
	}

}