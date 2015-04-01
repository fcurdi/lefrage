<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Configuracion</title>
	</head>
	
	<body>
		<div class="container marketing" style="width: 70%">
			<form id="formCreate" style="width: 60%; margin: 0 auto">
				<div class="form-group">
					<label>Nombre</label>
					<input class="form-control" type="text" value="${user.name}" pattern="[A-Za-z]+" placeholder="Ingrese el nombre" name="name" id="usr_name"/>
				</div>
				<div class="form-group">
					<label>Apellido</label>
					<input class="form-control" type="text" value="${user.surname}" pattern="[A-Za-z]+" placeholder="Ingrese el apellido" name="surname" id="usr_surname"/>
				</div>
				<div class="form-group">
					<label>Contraseña</label>
					<input class="form-control" type="password" value="" pattern="[A-Za-z]+" placeholder="Ingrese la contraseña (sólo si desea cambiarla)" name="password" id="usr_password"/>
				</div>
				<div class="form-group">
					<label>Nacimiento</label>
					<input class="form-control" type="text" value="${formatDate(date: user.dateOfBirth, format: 'dd/MM/yyyy')}" placeholder="Ingrese la fecha aaaa/mm/dd" name="date" id="usr_date"/>
				</div>
				<div style="text-align: right">
					<button type="button" onclick="updateUser()" class="btn btn-primary" id="btn-acept">Aceptar Cambios</button>
					<a type="button" onclick="deleteUser()" class="btn btn-danger">Eliminar Usuario</a>
				</div>
		</form>
		</div><!-- /container marketing -->	

	<script type="text/javascript">
		function deleteUser () {
			var promise = $.ajax({ 
					type: "delete", 
					url: "${createLink(controller: 'settings', action: 'deleteUser')}"
				});
			promise.done(function(){
				window.location.replace("/lefrage/logout/index");
			});
		};

		function updateUser (id) {
			var usr = {
				name: $("#usr_name").val(),
				surname: $("#usr_surname").val(),
				password: $("#usr_password").val(),
				date: $("#usr_date").val(),
			}

			var promise = $.ajax({
				type: "post",
				url: "${createLink(controller: 'settings', action: 'updateUser')}",
				data: usr
			});

			promise.done(function (){
				window.location.replace("/lefrage/settings/index");
			});
		};
	</script>
	</body>


</html>
