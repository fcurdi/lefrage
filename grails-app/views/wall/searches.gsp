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
				<h3>${search.queryString}</h3>
			</div>
		</g:each>
	</body>
</html>