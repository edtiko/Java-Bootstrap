<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="<%=request.getContextPath()%>/resources/images/Favicon.ico" type="image/x-icon" />
<link href="<%=request.getContextPath()%>/resources/css/master.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/css/bootstrap.css" rel="stylesheet">
<meta charset="utf-8">

<title>Login SGP</title>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}

.borderLogin {
	border: 1px solid #ba0606;
	height: 370px;
	width: 330px;
	text-align: center;
	margin: 0 auto;
	line-height: 20px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	padding: 19px 19px 29px;
	background: #FFFFFF
}

.input-sm {
	width: 235px;
	margin: 0 auto;
}

.interlineado {
	line-height: 20px;
}

.btn-danger {
	background-color: #ba0606
}
</style>

</head>
<body onload='document.f.j_username.focus();' background="<%=request.getContextPath()%>/resources/images/Background.gif">
	<c:if test="${not empty error}">
		<div class="errorblock">
			Su intento para conectarse no tuvo éxito, inténtalo de nuevo.<br />
			Causa : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
	<div class="borderLogin row">
		<form name='f' style="text-align: center;"
			action="<c:url value='j_spring_security_check' />" method="post">


			<div>
				<img
					src="<%=request.getContextPath()%>/resources/images/LogoPremize.png"
					width='150px' height='122px' />

			</div>
			</br>
			<div class="text-center">
				<img src="<%=request.getContextPath()%>/resources/images/Letra.png" />

			</div>
			</br>

			<div class="form-horizontal">
				<input type="text" class="form-control input-sm" name="j_username"
					placeholder="Usuario" /> </br> <input type="password"
					class="form-control  input-sm" name="j_password"
					placeholder="Contraseña" />

			</div>
			</br>
			<button class="btn btn-danger" name="submit" type="submit"
				value="submit">Iniciar Sesión</button>
		</form>
	</div>




</body>
</html>
