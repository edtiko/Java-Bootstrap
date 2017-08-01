<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="page">
	<table class="table table-bordered">
	<input type="hidden" id="tiposMapa" value='${tiposMapa}'/> 
	<input type="hidden" id="tiposArtefacto" value='${tiposArtefacto}'/> 
		<tr>
			<td><label class="control-label">PREMIZE S.A.S</label> <br>
				<img id="logo"
				src="<%=request.getContextPath()%>/resources/images/premize.png"
				width='80px' height='80px' /></td>
			<td><br> <br> <label class="control-label">Informe
					Semanal de Seguimiento sobre el Proceso de Pruebas</label></td>
		</tr>
	</table>
	<div style="text-align: center"></div>
	<table class="table table-bordered table-condensed"
		style="font-size: 12px">
		<tr>
			<td class="left"><label class="control-label">Proyecto:</label></td>
			<td colspan="3" class="left"><p class="form-control-static">${proyecto.nombre}</p></td>
		<tr>
		<tr>
			<td class="left"><label class="control-label">Fecha:</label></td>
			<td colspan="3" class="left"><p class="form-control-static">${fecha}</p></td>
		</tr>
		<tr>
			<td colspan="4" class="left"><label class="control-label">Dirigido
					a:</label></td>
		</tr>
		<tr>
			<th>Copia</th>
			<th>Nombre</th>
			<th>Cargo</th>
			<th>Empresa</th>
		</tr>
		<tbody>
			<c:forEach var="usuario" items="${usuarios}" varStatus="loop">
				<tr>
					<td>${loop.count}</td>
					<td>${usuario.nombre}</td>
					<td>${usuario.cargo}</td>
					<td>${usuario.empresa}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:set var="totalAbierto" value="${0}" />
	<c:set var="totalDevuelta" value="${0}" />
	<c:set var="totalDesarrollo" value="${0}" />
	<c:set var="totalReproceso" value="${0}" />
	<c:set var="totalAnulado" value="${0}" />
	<c:set var="totalInvalidado" value="${0}" />
	<c:set var="totalCerrado" value="${0}" />
	<c:set var="totalHallazgos" value="${0}" />
	<c:set var="totalBloqueantes" value="${0}" />
	<c:set var="totalFuncional" value="${0}" />
	<c:set var="totalPresentacion" value="${0}" />
	<c:set var="totalNuevoReq" value="${0}" />
	<c:set var="totalIncorrecto" value="${0}" />
	<c:set var="totalIncompleto" value="${0}" />
	<c:set var="totalAmbiguo" value="${0}" />
	<c:set var="totalMetodologia" value="${0}" />
	<c:set var="totalForma" value="${0}" />
	<c:set var="totalSugerencia" value="${0}" />
	


	<c:forEach var="total" items="${totales}" varStatus="loop">
		<c:set var="totalAbierto" value="${totalAbierto + total.totalAbierto}" />
		<c:set var="totalDevuelta"
			value="${totalDevuelta + total.totalDevuelta}" />
		<c:set var="totalDesarrollo"
			value="${totalDesarrollo + total.totalDesarrollo}" />
		<c:set var="totalReproceso"
			value="${totalReproceso + total.totalReproceso}" />
		<c:set var="totalAnulado" value="${totalAnulado + total.totalAnulado}" />
		<c:set var="totalInvalidado"
			value="${totalInvalidado + total.totalInvalidado}" />
		<c:set var="totalCerrado" value="${totalCerrado + total.totalCerrado}" />
		<c:set var="totalHallazgos"
			value="${totalHallazgos + total.totalHallazgos}" />
		
		<c:if test="${total.severidad == 'Bloqueante'}">
			<c:set var="totalBloqueantes"
				value="${totalBloqueantes + total.totalHallazgos}" />
		</c:if>

		<c:if test="${total.severidad == 'Presentación'}">
			<c:set var="totalPresentacion"
				value="${totalPresentacion + total.totalHallazgos}" />
		</c:if>

		<c:if test="${total.severidad == 'Funcional'}">
			<c:set var="totalFuncional"
				value="${totalFuncional + total.totalHallazgos}" />
		</c:if>
		<c:if test="${total.severidad == 'Nuevo Requerimiento'}">
			<c:set var="totalNuevoReq"
				value="${totalNuevoReq + total.totalHallazgos}" />
		</c:if>
		<c:if test="${total.severidad == 'Incorrecto'}">
			<c:set var="totalIncorrecto"
				value="${totalIncorrecto + total.totalHallazgos}" />
		</c:if>
		<c:if test="${total.severidad == 'Incompleto'}">
			<c:set var="totalIncompleto"
				value="${totalIncompleto + total.totalHallazgos}" />
		</c:if>
		<c:if test="${total.severidad == 'Ambiguo'}">
			<c:set var="totalAmbiguo"
				value="${totalAmbiguo + total.totalHallazgos}" />
		</c:if>
		<c:if test="${total.severidad == 'Metodología'}">
			<c:set var="totalMetodologia"
				value="${totalMetodologia + total.totalHallazgos}" />
		</c:if>
		<c:if test="${total.severidad == 'Forma'}">
			<c:set var="totalForma"
				value="${totalForma + total.totalHallazgos}" />
		</c:if>
		<c:if test="${total.severidad == 'Sugerencia'}">
			<c:set var="totalSugerencia"
				value="${totalSugerencia + total.totalHallazgos}" />
		</c:if>

	</c:forEach>

	<fieldset>
		<legend>
			<h5>
				<strong>Estado del Producto</strong>
			</h5>
		</legend>
		<p class="text-justify">A la fecha se encuentran registrados un
			total de ${totalHallazgos} hallazgos, que corresponden a
			<c:forEach var="total" items="${totales}" varStatus="loop">
			
			<c:if test="${total.severidad == 'Bloqueante'}">
		  ${totalBloqueantes} bloqueantes, 
		</c:if>

		<c:if test="${total.severidad == 'Presentación'}">
			${totalPresentacion} de presentación,
		</c:if>

		<c:if test="${total.severidad == 'Funcional'}">
			${totalFuncional} funcionales,
		</c:if>
		<c:if test="${total.severidad == 'Nuevo Requerimiento'}">
			${totalNuevoReq} de nuevo requerimiento,
		</c:if>
		<c:if test="${total.severidad == 'Incorrecto'}">
			${totalIncorrecto} incorrecto, 
		</c:if>
		<c:if test="${total.severidad == 'Incompleto'}">
			${totalIncompleto} incompleto, 
		</c:if>
		<c:if test="${total.severidad == 'Ambiguo'}">
			${totalAmbiguo} ambiguo,
		</c:if>
		<c:if test="${total.severidad == 'Metodología'}">
			${totalMetodologia} de metodología,
		</c:if>
		<c:if test="${total.severidad == 'Forma'}">
			${totalForma} de forma,
		</c:if>
		<c:if test="${total.severidad == 'Sugerencia'}">
			${totalSugerencia} de sugerencia,
		</c:if>
			</c:forEach>
			como se muestra en el cuadro a
			continuación:</p>
	</fieldset>
	<table class="table table-bordered table-condensed"
		style="font-size: 12px">
		<thead>
			<tr>
				<th>Severidad/Estado</th>
				<th>Abierta</th>
				<th>Devuelta</th>
				<th>Desarrollo</th>
				<th>Reproceso</th>
				<th>Anulado</th>
				<th>Inválido</th>
				<th>Cerrada</th>
				<th>Total</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="total" items="${totales}" varStatus="loop">

				<tr>
					<td>${total.severidad}</td>
					<td>${total.totalAbierto}</td>
					<td>${total.totalDevuelta}</td>
					<td>${total.totalDesarrollo}</td>
					<td>${total.totalReproceso}</td>
					<td>${total.totalAnulado}</td>
					<td>${total.totalInvalidado}</td>
					<td>${total.totalCerrado}</td>
					<td>${total.totalHallazgos}</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<th>Total</th>
				<th>${totalAbierto}</th>
				<th>${totalDevuelta}</th>
				<th>${totalDesarrollo}</th>
				<th>${totalReproceso}</th>
				<th>${totalAnulado}</th>
				<th>${totalInvalidado}</th>
				<th>${totalCerrado}</th>
				<th>${totalHallazgos}</th>
			</tr>
		</tfoot>
	</table>

</div>
	<div class="pie text-center" style="display: none">1</div>
<div class="page">
	<div class="head" style="display: none">
		<table class="table table-bordered">
			<tr>
				<td><label class="control-label">PREMIZE S.A.S</label> <br>
					<img id="logo"
					src="<%=request.getContextPath()%>/resources/images/premize.png"
					width='80px' height='80px' /></td>
				<td><br> <br> <label class="control-label">Informe
						Semanal de Seguimiento sobre el Proceso de Pruebas</label></td>
			</tr>
		</table>
	</div>
	<legend>
		<h5>
			<strong>Gráfica de Hallazgos por Severidad</strong>
		</h5>
	</legend>
	<div class="panel panel-default">
		<div id="hallazgoPorSeveridad" align="center"></div>
	</div>
	<c:set var="porcentajeAvance"
		value="${((mapaPrueba.totalPruebasEjecutadas) / (mapaPrueba.totalPruebasConstruidas - mapaPrueba.totalPruebasAnuladas))*100}" />
		
	<c:if test="${mapaPrueba.totalPruebasConstruidas == 0}">
			<c:set var="porcentajeAvance"
				value="0" />
		</c:if>
		<div id="avanceEjecucion" style="display: none">
	<fieldset>
		<legend>
			<h5>
				<strong>Avance en la Ejecución de Mapas de Pruebas</strong>
			</h5>
		</legend>
		<p class="text-justify">
			A la fecha se encuentran construidas un total de
			${mapaPrueba.totalPruebasConstruidas} pruebas, de las cuales se han
			ejecutado ${mapaPrueba.totalPruebasEjecutadas}. El porcentaje de avance en la
			ejecución de pruebas es de
			<fmt:formatNumber type="number" maxFractionDigits="2"
				value="${porcentajeAvance}" />
			%. El total de pruebas anuladas es de
			${mapaPrueba.totalPruebasAnuladas}.
		</p>
	</fieldset>
	</div>	
	<div id="hallazgoPorMapa1" >
		<fieldset>
		<legend>
			<h5>
				<strong>Gráfica de Hallazgos por Mapa de Pruebas</strong>
			</h5>
		</legend>
		<div class="panel panel-default">
			<div id="hallazgoPorMapa" ></div>
		</div>
	</fieldset>
</div>
	<fieldset>
		<legend>
			<h5>
				<strong>Gráfica de Hallazgos por Artefacto</strong>
			</h5>
		</legend>
		<div class="panel panel-default">
			<div id="hallazgoPorArtefacto" ></div>
		</div>
	</fieldset>
</div>
	<div class="pie text-center" style="display: none">2</div>
<div class="page">
	<div class="head" style="display: none">
		<table class="table table-bordered">
			<tr>
				<td><label class="control-label">PREMIZE S.A.S</label> <br>
					<img id="logo"
					src="<%=request.getContextPath()%>/resources/images/premize.png"
					width='80px' height='80px' /></td>
				<td><br> <br> <label class="control-label">Informe
						Semanal de Seguimiento sobre el Proceso de Pruebas</label></td>
			</tr>
		</table>
	</div>


	<fieldset>
		<legend>
			<h5>
				<strong>Hallazgos Pendientes por Usuario</strong>
			</h5>
		</legend>
	
	<div id="tablaSeveridad" class="col-md-10 col-md-offset-3"></div>
	</fieldset>
	<table class="table table-bordered table-condensed"
		style="font-size: 12px">
		<tr>
			<td class="left"><label class="control-label">Observaciones
					Adicionales:</label></td>
		</tr>
		<tr>
			<td><textarea class="form-control" rows="3"
					style="resize: none; overflow-y: hidden; overflow: auto"
					name="observacion" id="descripcion" title="Observación">Este informe contiene el avance de ejecución de pruebas desde el inicio del proyecto a la fecha.</textarea>
			</td>
		</tr>
		<tr>
			<td class="left"><label class="control-label">Elaborado
					Por:</label></td>
		</tr>
		<tr>
		<br>
			<td class="left"><br> <label class="control-label">${usuario.nombre}</label>
				<br> <label class="control-label">${usuario.cargo}</label></td>
		</tr>
	</table>
	
</div>
<div class="pie text-center" style="display: none">3</div>