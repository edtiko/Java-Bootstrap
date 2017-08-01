<%@ page isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
          "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>

<title>Error Desconocido</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/rime/rime.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/mp_estilos.css" />
</head>
<body>
<div id="logo" xmlns:ui="http://java.sun.com/jsf/facelets" class="fondoHeader">
<table width="100%" align="center" cellspacing="8" cellpadding="0" border="0">
	<tr>
		<td width="30%"></td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td width="40%"></td>
		<td>
		<h1 class="texto1">Error Desconocido</h1>
		</td>
		<td></td>
	</tr>
	<tr>
		<td width="30%"></td>
		<td></td>
		<td>&nbsp;&nbsp;&nbsp;</td>
	</tr>
</table>
</div>
<div align="center">
<p>
<h2 class="texto2">Se ha generado un error de aplicaci&oacute;n no esperado, por favor reintente la operaci&oacute;n.</h2>
</p>
<br />
<textarea name="textarea" cols="140" rows="15" readonly="readonly">
<%
	if (exception != null) {
 		exception.printStackTrace(new java.io.PrintWriter(out));
 	}
 %>
</textarea>
<h2><a href="javascript:history.go(0);">Volver</a></h2>
</div>
</body>
</html>
