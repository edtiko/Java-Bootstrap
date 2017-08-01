<%@ include file="/pages/templates/layout.jsp"%>
<html>
<!-- prueba commit -->
<head>
<title><spring:message code="lblGestionCiudad" /></title>
</head>
<body>
    <div id="piechart_div" style="width: 600px; height: 400px;"></div>
    <div id="barchart_div" style="width: 600px; height: 400px;"></div>
</body>
    <script type="text/javascript"
    src="https://www.google.com/jsapi"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/reportes/reportes.js"></script>
</html>



