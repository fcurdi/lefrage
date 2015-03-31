<!DOCTYPE html>
<html>
	<head>
		<title>Búsquedas de ${name}</title>
		<meta name="layout" content="main">
		<style>
			.well-lg{
				width:85%;
				margin-right: 7.5%;
				margin-left: 7.5%;
				float:right;
			}
		</style>
	</head>
	<body>
		<g:each var="search" in="${searches}">
			<div class="well well-lg">
				<h2>${search.queryString}</h2>
				<g:each var="stat" in="${search.stats}">
					<h3>${stat.date}</h3>
					mean=${stat.mean}, 
					deviation=${stat.deviation},
					max=${stat.max},
					min=${stat.min},
					sales=${stat.sales}
				</g:each>
			</div>
		</g:each>
	</body>
</html>