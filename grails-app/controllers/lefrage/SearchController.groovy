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
        render(view:"/friends/index", model: [friends: friends,
            search: params.item_search, optionName: params.optionName, optionValue: params.optionValue])
    }

    def meli(){
        def currentSpringUser = springSecurityService.currentUser
        def user = User.findBySpringUser(currentSpringUser)
        def favourited = user.queries.find{it.queryString==params.item_search} != null
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
