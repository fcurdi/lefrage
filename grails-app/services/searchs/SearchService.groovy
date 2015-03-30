package searchs

import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.*
import lefrage.*

@Transactional
class SearchService {

    def springSecurityService

    def searchPeople(query) {
        def currentSpringUser = springSecurityService.currentUser
        def user = User.findBySpringUser(currentSpringUser)
        return User.findAllByNameLikeOrSurnameLike("%"+query+"%", "%"+query+"%")
    }

    def favourite(queryString, products){
        def currentSpringUser = springSecurityService.currentUser
        def currentUser = User.findBySpringUser(currentSpringUser)
    	def query = currentUser.queries.find{it.queryString==queryString}
        if(!query){
            query = new Query(queryString: queryString)
            def stat = new Stat(date:new Date())
                .calcMean(products.price)
                .calcDeviation(products.price)
                .calcMax(products.price)
                .calcMin(products.price)
            query.addToStats(stat)
            currentUser.addToQueries(query)
        }
        else{
            currentUser.removeFromQueries(query)
            query.delete()
        }
    }
}
