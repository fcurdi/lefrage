package lefrage

import grails.plugin.springsecurity.annotation.*
import security.*
import lefrage.User
import searchs.SearchService
import groovy.json.JsonSlurper
import groovyx.net.http.*
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*

@Secured(['ROLE_USER'])
class SearchController {    
    def springSecurityService
    def searchService

    def index() {
        if(params.optionValue=="Meli")redirect(action: "meli", params: params)
        else redirect(action: "friends", params: params)
    }

    def friends(){
        def friends = searchService.searchPeople(params.item_search)
        render(view:"/friends/index", model: [friends: friends,
            search: params.item_search, optionName: params.optionName, optionValue: params.optionValue])
    }

    def meli(){
        def currentSpringUser = springSecurityService.currentUser
        def user = User.findBySpringUser(currentSpringUser)
        def query = user.queries.find{it.queryString==params.item_search}
        def favourited = query != null
        if (favourited){
            def http = new HTTPBuilder('https://api.mercadolibre.com/sites/MLA/search')
            //Obtain http builder class
            def html = http.get( query : [q:'params.item_search'] ){ resp, reader ->
                def prices = reader.results.price
                def stat = new Stat(date:new Date())
                    .calcMean(prices)
                    .calcDeviation(prices)
                    .calcMax(prices)
                    .calcMin(prices)
                query.addToStats(stat)
                user.addToQueries(query)
            }
        }
        def postDestination = user.friends
        postDestination.add(user) //add myself to it
    	[search: params.item_search, optionName: params.optionName, 
            optionValue: params.optionValue, favourited: favourited, postDestination: postDestination] 
    }

    def favourite(){
        def slurper = new JsonSlurper()
        def params = slurper.parseText(params.infoStr)
        searchService.favourite(params.query, params.products)
        render "okk"
    }
}
