package users

import grails.transaction.Transactional
import lefrage.User
import security.SpringUser
import security.SpringUserRole
import security.Role
import java.util.Date

@Transactional
class UserService {

    def create(name, surname, nick, password, dateOfBirth) {

    	User us = new User(name: name, surname: surname, dateOfBirth: Date.parse("yyyy/MM/dd",dateOfBirth))
    	SpringUser sp = new SpringUser(username: nick, password: password)
    	sp.save(flush: true, failOnError: true)
    	us.springUser = sp
    	us.save(flush: true, failOnError: true)
        Role role = Role.list().find{it.authority == "ROLE_USER"};
    	SpringUserRole.create(sp, role, true)
    }

    def update(id, name, surname, date, password) {

    	def user = User.get(id)
        
        println "---------------------------"
        println user

        user.name = name
        user.dateOfBirth = Date.parse("dd/MM/yyyy", date)
        user.surname = surname

        User.withTransaction { status ->
            if(password) {
                def springUser = user.springUser
                springUser.password = password
                springUser.save(failOnError: true)
            }

            user.save(failOnError: true)
        }
        
    }
}
