package lefrage

import grails.plugin.springsecurity.annotation.*
import security.*
import lefrage.User
import searchs.SearchService
import groovy.json.JsonSlurper

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
        render(view:"/friends/index", model: [friends: friends])
    }

    def meli(){
        def currentSpringUser = springSecurityService.currentUser
        def user = User.findBySpringUser(currentSpringUser)
        def query = user.queries.find{it.queryString==params.item_search}
        def favourited = query != null
        if(favourited){
            searchService.addStat(query)
            user.addToQueries(query)
        }
        def postDestination = user.friends
        postDestination.add(user) //add myself to it
    	[search: params.item_search, optionName: params.optionName, 
            optionValue: params.optionValue, favourited: favourited, postDestination: postDestination] 
    }

    def favourite(){
        def slurper = new JsonSlurper()
        def parameters = slurper.parseText(params.infoStr)
        searchService.favourite(parameters.query)
        render "okk"
    }
}
