<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html>
<head>

<title>Accesso Denegado</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/rime/rime.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/mp_estilos.css" />
</head>
<body>
<div id="logo" xmlns:ui="http://java.sun.com/jsf/facelets" class="fondoHeader">
<table width="100%" align="center" cellspacing="8" cellpadding="0" border="0">
	<tr>
		<td width="30%"></td>
		<td>&nbsp;&nbsp;&nbsp;</td>
		<td>&nbsp;&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td width="40%"></td>
		<td>
		<h1 class="texto1">Accesso Denegado</h1>
		</td>
		<td></td>
	</tr>
	<tr>
		<td width="30%">&nbsp;&nbsp;&nbsp;</td>
		<td>&nbsp;&nbsp;&nbsp;</td>
		<td>&nbsp;&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td width="30%"></td>
		<td>&nbsp;&nbsp;&nbsp;</td>
		<td></td>
	</tr>
</table>
</div>
<div align="center"><br />
<p><br />
<h2 class="texto2">Usted esta tratando de ingresar a una página sobre la<br />
que no posee permisos, por favor contacte al administrador.</h2>
</p>
<br />

<table>
	<tr>
		<td>
		<h2><a href="<%=request.getContextPath() + "/login.jsp"%>">[Login]</a></h2>
		</td>
		<td></td>
		<td>
		<h2><a href="javascript:history.back();">[Back]</a></h2>
		</td>
	</tr>
</table>
</div>
</body>
</html>
