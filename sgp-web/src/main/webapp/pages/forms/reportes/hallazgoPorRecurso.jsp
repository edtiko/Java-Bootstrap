<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="table table-bordered" style="font-size: 12px">
<thead>
<tr>
	<th>Responsable/Severidad</th>
	<th>Bloqueante</th>
	<th>Funcional</th>
	<th>Presentación</th>
	<th>Total de Hallazgos</th>
	<th>Pruebas Ejecutadas</th>
	<th>Indicador</th>
	
	<th>Sem&aacute;foro</th>
</tr>
</thead>
<tbody>
<c:set var="total" value="${0}" />
<c:set var="totalBloqueantes" value="${0}" />
<c:set var="totalFuncionales" value="${0}" />
<c:set var="totalPresentacion" value="${0}" />
<c:set var="totalIndicador" value="${0}" />
<c:set var="totalPruebasEjecutadas" value="${0}" />
<c:forEach var="val" items="${list}">
<c:set var="total" value="${total + val.totalHallazgos}" />
<c:set var="totalIndicador" value="${ val.totalIndicadorPorcentaje}" />
<c:set var="totalBloqueantes" value="${totalBloqueantes + val.bloqueantes}" />
<c:set var="totalFuncionales" value="${totalFuncionales + val.funcionales}" />
<c:set var="totalPresentacion" value="${totalPresentacion + val.presentacion}" />
<c:set var="totalPruebasEjecutadas" value="${totalPruebasEjecutadas + val.pruebaEjecutada}" />
</c:forEach>
<c:forEach var="val" items="${list}">
<tr>
	<th>${val.responsable}</th>
	<td>${val.bloqueantes}</td>
	<td>${val.funcionales}</td>
	<td>${val.presentacion}</td>
		<td>${val.totalHallazgos}</td>
	<td>${val.pruebaEjecutada}</td>

	<td>${val.indicador.indicadorString}</td>
	<td><img style="width: 20px; height: 20px"
							src="<%=request.getContextPath()%>/resources/images/indicadores/${val.indicador.semaforoImage}"></td>				
</tr>
</c:forEach>
<tr>
<th>Totales</th>
<td>${totalBloqueantes}</td>
<td>${totalFuncionales}</td>
<td>${totalPresentacion}</td>

<td>${total}</td>
<td>${totalPruebasEjecutadas}</td>
<td></td>
<td></td>
</tr>
</tbody>
</table>