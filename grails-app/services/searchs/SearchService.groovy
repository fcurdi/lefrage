package searchs

import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.*
import lefrage.*
import groovy.json.JsonSlurper

@Transactional
class SearchService {

    def springSecurityService

    def searchPeople(query) {
        def currentSpringUser = springSecurityService.currentUser
        def user = User.findBySpringUser(currentSpringUser)
        return User.findAllByNameLikeOrSurnameLike("%"+query+"%", "%"+query+"%")
    }

    def favourite(queryString){
        def currentSpringUser = springSecurityService.currentUser
        def currentUser = User.findBySpringUser(currentSpringUser)
    	def query = currentUser.queries.find{it.queryString==queryString}
        if(!query){
            query = new Query(queryString: queryString)
            addStat(query)
            currentUser.addToQueries(query)
        }
        else{
            currentUser.removeFromQueries(query)
            query.delete()
        }
    }

    def addStat(query){
        def json = 
            ("https://api.mercadolibre.com/sites/MLA/search?q="+query.queryString+"&limit=51&condition=new&buying_mode=buy_it_now").toURL()
        json = json.getText(
            requestProperties: ["accept": 'application/json']
        )
        def slurper = new JsonSlurper()
        def products = slurper.parseText(json).results
        def stat = new Stat(date:new Date())
            .calcMean(products.price)
            .calcDeviation(products.price)
            .calcMax(products.price)
            .calcMin(products.price)
            .calcSales(products.sold_quantity)
        query.addToStats(stat)
        return query
    }
}
