<!DOCTYPE html>
<html>
	<head>
		<title>Le Perfil de ${name}</title>
		<meta name="layout" content="main">
		<style>
			
		</style>
	</head>
	<body>
		<div class="well well-lg profile" style="width:27%;margin-left:60px;position:fixed;float:left">
			<div class="profile-data" style="margin-left:auto; margin-right:auto">
				<h2 style="margin-bottom:0px">
					${name} ${surname}
				</h2>
				<h3 style="margin-top:5px;color:#C2C2A3" >
					${username}
				</h3>
				<h3 style="margin-top:5px">
					<g:formatDate date="${profileDOB}" format="yyyy-MM-dd"/>
				</h3>
				<h3 style="margin-top:5px">
					<a href="/lefrage/${username}/amigos">
						Amigos: ${profileFriendsCount}
					</a>
				</h3>
					<g:if test="${isOwnWall}">
					<a href="/lefrage/${username}/busquedas" class="btn btn-default mlblue-btn" style="margin-top:0px; width:100%; margin-top:10px">
						Mis búsquedas
					</a>
				</g:if>
			</div>
		</div>
		<div style="margin-right:60px;width:67%	;float:right">
		<div class="well well-lg" style="width:800px;margin-left:auto;">
			<form style="margin-left:auto;margin-right:auto;width:700px" method="post" action="${createLink(controller: "wall", action: "writePost", params: [username: username])}">
	       		<div>
	            	<g:textArea id="input-post" style="font-size:13pt" placeholder="Le post text..." class="form-control" name="htmlPostContent"/>
	            </div>
	            <br/>
	            <button id="btn-post" disabled="disabled" type="submit" class="btn btn-primary pull-right">Postear</button>
	            <p/>
	            <button type="reset" class="btn btn-default pull-right">Borrar</button>	
	            <br />
	        </form>
	    </div>
    	<br/><br/>
    	<g:if test="${userPosts.size() != 0}">
			<div class="well well-lg" style="width:800px;margin-left:auto;">
				<div style="width:700px;margin-left:auto;margin-right:auto" >
					<g:each status="i" var="post" in="${userPosts}">
						<div style="font-size:15pt"	class="panel panel-default">
					  		<div class="panel-heading ${post.isAutoPost ? "autopost" : (post.product ? "ml" : "")}">
					  			${post.author.name}
					  			<div style="float:right" id="parsedDate${i}">
				  					<g:formatDate date="${post.date}" format="yyyy-MM-dd HH:mm:ss"/>
					  			</div>
					  		</div>
			  				<div class="panel-body">
			    				${post.content}
			  				</div>
			  				<g:if test="${post.product}">
			  					<div>
			  				 		<hr/>
			  				 		<table>
				  				 		<th>
				  				 			<a href="http://www.mercadolibre.com/">
							  					<img src="${post.product.productUrlImg}" target="_blank" alt="${post.product.productTitle}" height="150" width="150" hspace="20">
							  					</img>
						  					</a>
						  					<br/><br/>
					  					</th>
					  					<th>
					  						<a href="http://www.mercadolibre.com/" target="_blank">
						  						<h2>
						  							${post.product.productTitle}
						  						</h2>
						  					</a>
					  						<h3>
					  							${post.product.productPrice}
					  						</h3>
				  						</th>
				  					</table>
			  					</div>
			  				</g:if>
						</div>
					</g:each>
				</div>
			</div>
		</g:if>
		</div>
		<script type="text/javascript">
			$("#input-post").keypress(function() {
				setTimeout(buttons, 0);
			});

			function buttons() {
				if($("#input-post").val().length != 0) {
					$("#btn-post").removeAttr("disabled");
				} else {
					$("#btn-post").attr("disabled","disabled");
				}
			};
		</script>
	</body>
</html>
