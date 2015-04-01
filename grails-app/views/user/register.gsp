<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
		<script type="text/javascript" src="${request.contextPath}/js/jquery-2.1.3.js"></script>
		<title>Registrarse</title>
	</head>

	<body>
		<form id="formCreate" style="width: 60%; margin: 0 auto">
			<div class="form-group">
				<label>Nombre</label>
				<input class="form-control" type="text" pattern="[A-Za-z]+" placeholder="Ingrese el nombre" name="name" id="usr_name"/>
			</div>
			<div class="form-group">
				<label>Apellido</label>
				<input class="form-control" type="text" pattern="[A-Za-z]+" placeholder="Ingrese el apellido" name="surname" id="usr_surname"/>
			</div>
			<div class="form-group">
				<label>Usuario</label>
				<input class="form-control" type="text" pattern="[A-Za-z]+" placeholder="Ingrese el usuario" name="nick" id="usr_nick"/>
			</div>
			<div class="form-group">
				<label>Contraseña</label>
				<input class="form-control" type="password" pattern="[A-Za-z]+" placeholder="Ingrese la contraseña" name="password" id="usr_password"/>
			</div>
			<div class="form-group">
				<label>Nacimiento</label>
				<input class="form-control" type="text" placeholder="Ingrese la fecha aaaa/mm/dd" name="date" id="usr_date"/>
			</div>
			<div style="text-align: right">
				<button type="button" class="btn btn-success" id="btn-acept">Aceptar</button>
				<a type="button" href="${createLink(controller: 'login', action: 'auth')}" class="btn btn-danger">Cancelar</a>
			</div>
		</form>
	</body>
	<script type="text/javascript">
		$("#btn-acept").click(createUser);

		function createUser() {
			var usr = {
				name: $("#usr_name").val(),
				surname: $("#usr_surname").val(),
				nick: $("#usr_nick").val(),
				password: $("#usr_password").val(),
				date: $("#usr_date").val()
			}

			var promise = $.post("/lefrage/user/createUser", usr);

			promise.done(function() {
				window.location.replace("/lefrage/login/auth");
			});
		};
	</script>
</html>
