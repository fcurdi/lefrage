package lefrage
import java.util.Date

class Post {

	String content

	Product product

	Date date

	User author // author of post
	
	static belongsTo = [containingWallUser: User] // the user in whose wall this post lives

	// [TODO] like

    static constraints = {
		product nullable: true
		content nullable: true
    }

}