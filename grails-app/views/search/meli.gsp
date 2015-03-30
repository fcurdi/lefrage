<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Busqueda Regalo</title>
	</head>
	
	<body>
		<div id="search_result"class="container marketing" style="width: 70%">
		</div><!-- /container marketing -->

		<nav>
			<ul class="pager">
				<li class="previous">
					<a id="btn-previous" href="#"><span aria-hidden="true">&larr;</span>Anterior</a>
				</li>
				<li class="next">
					<a id="btn-next" href="#">Siguiente<span aria-hidden="true">&rarr;</span></a>
				</li>
				<li class="next">
					<g:if test="${!favourited}">
						<a id="btn-favourite" href="#">
					</g:if>
					<g:else>
						<a id="btn-favourite" class="next-selected" href="#">
					</g:else>
						<span class="fa fa-heart"></span>
					</a>
				</li>
			</ul> <!-- /ul -->
		</nav> <!-- /nav -->

		<!-- Modal -->
		<div class="modal fade" id="postModal" tabindex="-1" role="dialog" aria-labelledby="postLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h3 class="modal-title" id="postLabel">Contenido Post</h3>
					</div>
		      		<g:form method="post" controller="empleados" action="agregarEmpleado">
				        <div>
		                    <g:textArea class="form-control" id="postContent" name="text"/>
		                </div>
		            </g:form>
					<div class="modal-footer">
		      			<div id="post_result"></div>
		      			<div class="row"></div>
		      			<div>
							<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
							<button onclick="postProduct()" type="button" class="btn btn-primary">Post</button>
		      			</div>
					</div>
				</div> <!-- /modal-content -->
			</div> <!-- /modal-dialog -->
		</div> <!-- /modal -->
	
		<script id="search_item_template" type="text/template">
			<article class="col-md-4 search-item">
		   		<div class="thumbnail">
		    		<img src="#urlImg" height="200">
		        	<div class="caption">
		            	<h3>#itemTitle</h3>
		            	<p>$ #itemPrice</p>
		            <hr class="featurette-divider">
				        <div class="row">
				        	<div class="buttons col-md-6">
				        		<button type="button" id="btn-post" class="btn btn-primary btn-block" data-toggle="modal" data-target="#postModal" onclick="completePostTemplate('#urlImg', '#itemTitle', #itemPrice)" >Post</button>
				        	</div>
				        	<div class="buttons col-md-6">
				        		<button class="btn btn-primary btn-block">Compartir</button>
				        	</div>
				       	</div> <!-- /row -->
		        	</div> <!-- /caption -->
		    	</div> <!-- /thumbnail -->
		    </article>
		</script>
		
		<script id="post_item_template" type="text/template">
			<article class="col-md-6 search-item">
					<img src="#urlImg" width="100%" height="200">
		    </article>
			<article class="col-md-6 search-item">
		    	<div class="caption">
		        	<h3>#itemTitle</h3>
		        	<p>$ #itemPrice</p>
		    	</div> <!-- /caption -->
		    </article>
		    
		</script>

		<script id="error_search" type="text/template">
			<div>
				<div class="alert alert-danger alert-dismissible" role="alert">
					Error en la búsqueda, por favor vuelva a intentarlo.
				</div>
			</div>
		</script>

		<script type="text/javascript">
			var favourited=${favourited};
			var link="${createLink(controller:'search', action:'favourite')}"
			searchItems(0, "${search}", link);
		</script>
	</body>
</html>
