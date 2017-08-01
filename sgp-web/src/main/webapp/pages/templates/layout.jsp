<%@ include file="/pages/general/menu.html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pmz" tagdir="/WEB-INF/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="<%=request.getContextPath() %>/resources/css/bootstrap.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/resources/css/jquery-ui.css" rel="stylesheet">
<link
	href="<%=request.getContextPath() %>/resources/css/sgp_estilos.css"
	rel="stylesheet">
<script type="text/javascript" src="https://www.google.com/jsapi"></script>		
<script type="text/javascript"
	src="<%=request.getContextPath() %>/resources/js/util/jquery-2.0.3.js" charset="utf-8" ></script>
	<script type="text/javascript"
	src="<%=request.getContextPath() %>/resources/js/util/jquery-validate.js" charset="utf-8" ></script>	

<script type="text/javascript"
	src="<%=request.getContextPath() %>/resources/js/util/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/resources/js/util/bootstrap.js"></script>
<script
	src="<%=request.getContextPath() %>/resources/js/util/jquery.dataTables.min.js" charset="utf-8" ></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/resources/js/util/datatables.js" charset="utf-8" ></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/resources/js/util/jquery.dataTables.columnFilter.js"></script>
	<script type="text/javascript"
	src="<%=request.getContextPath() %>/resources/js/util/util.js"></script>
	<script type="text/javascript"
	src="<%=request.getContextPath() %>/resources/js/util/menu.js"></script>
</head>

<body>
 <spring:url var="homeUrl" value="/" />
                <spring:url var="loginUrl" value="/login" />
                <spring:url var="logoutUrl" value="/logout" />
                <spring:url var="profileShow" value="/profile/show" />
                <spring:url var="role_list" value="/role/list" />
                <spring:url var="account_list" value="/account/list" />
                <spring:url var="thread_list" value="/thread/list" />
                <spring:url var="user_thread_list" value="/user/thread/list" />

</body>
<div class="modal fade" id="modalEmpresa"></div>
<div class="modal fade" id="modalProyecto"></div>
<div class="modal fade" id="modalArtefacto"></div>
<div class="modal fade" id="modalHallazgo"></div>
<div class="modal fade" id="modalUser"></div>
<div class="modal fade" id="divConfirma"></div>
<div class="modal fade" id="timeOut"></div>
<div class="modal fade" id="modal"></div>
</html>