 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!--  <script type="text/javascript" -->
<%-- 		src="<%=request.getContextPath()%>/resources/js/pruebas/indicadoresCalidadCuerpo.js"></script> --%>
 <div>
	<fieldset>
		<c:set var="mapSize" value="${fn:length(indicadores.indicadorCalidadDocumentacion)}" />
		<c:forEach var="indiCalidadDoc" begin="0" end="0"
					items="${indicadores.indicadorCalidadDocumentacion}" varStatus="i">
					<c:set var="paramIndiCalidadDoc" value="${indiCalidadDoc.value.parametroIndicador}"/>
		</c:forEach>
		<legend>
			<h5>
				<strong>
					${paramIndiCalidadDoc.nombre}
				</strong>
			</h5>
		</legend>
		<div>
			<p>
				<spring:message code="lbl_initFormula" /> ${paramIndiCalidadDoc.formula}
			</p>
			
		</div>

		<table class="table table-bordered" id="tableIndiCalidadDoc"
			style="font-size: 12px">
			<thead>
				<tr>
					<th rowspan="2">Nombre del Artefacto</th>
					
					<c:forEach var="severidadNombre" items="${tipoSeveridad}">
						<th colspan="2">${severidadNombre}</th>
					</c:forEach>
					
					<th colspan="2">Totales</th>
					<th colspan="2">Indicador</th>
				</tr>
				
				<tr style="font-size: 9px">
					<c:forEach var="severidadNombre" items="${tipoSeveridad}">
						<th>Hallazgos</th>
						<th>Puntuaci&oacute;n</th>
					</c:forEach>

					<th>Hallazgos</th>
					<th>Puntuaci&oacute;n</th>

					<th>Indicador</th>
					<th>Sem&aacute;foro</th>
				</tr>
			</thead>
			<tbody>
			    <c:if test="${mapSize > 1}">
				<c:forEach var="indiCalidadDoc"
					items="${indicadores.indicadorCalidadDocumentacion}" varStatus="i">
					<c:set var="mapSeveridad" value="${indiCalidadDoc.value.mapSeveridad}" />
					<tr>
						<td style="font-weight: bold">${indiCalidadDoc.value.artefactoNombre}</td>
						
						<c:forEach var="severidad" items="${mapSeveridad}">
							<c:choose>
								<c:when test="${severidad.key!='Totales'}">
									<td>${severidad.value.hallazgos}</td>
									<td>${severidad.value.puntuacionDouble}</td>
								</c:when>
								<c:otherwise>
									<td>${severidad.value.hallazgos}</td>
									<td>${severidad.value.puntuacionDouble}</td>

									<td>${indiCalidadDoc.value.indicadorString}</td>
									<td><img style="width: 20px; height: 20px"
										src="<%=request.getContextPath()%>/resources/images/indicadores/${indiCalidadDoc.value.semaforoImage}">
									</td>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</tr>

				</c:forEach>
				</c:if>
			</tbody>
		</table>
	</fieldset>
	
	<br/>

	<fieldset>
		<c:set var="listSize" value="${fn:length(indicadores.indicadorCalidadFuncionalidad)}" />
		<legend>
			<h5>
				<strong>
					${indicadores.indicadorCalidadFuncionalidad[0].parametroIndicador.nombre}
				</strong>
			</h5>
		</legend>
		<div>
			<p>
				<spring:message code="lbl_initFormula" />
				${indicadores.indicadorCalidadFuncionalidad[0].parametroIndicador.formula}
			</p>
		</div>

		<table class="table table-bordered" id="indiCalidadFuncionalidad"
			style="font-size: 12px">
			<thead>
				<tr>
					<th>Nombre del Artefacto</th>
					<th>Usuario Responsable</th>
					<th>Pruebas Ejecutadas</th>
					<th>Hallazgos Reportados</th>
					<th>Hallazgos Invalidados</th>
					<th>Hallazgos Anulados</th>
					<th>Indicador</th>
					<th>Sem&aacute;foro</th>
				<tr>
			</thead>
			<tbody>
				<c:if test="${listSize > 1}">
					<c:set var="size" value="${listSize - 2}" />
					<c:forEach var="indiCalidadFuncionalidad" begin="0" end="${size}"
						items="${indicadores.indicadorCalidadFuncionalidad}" varStatus="i">
						<tr>
							<td>${indiCalidadFuncionalidad.artefactoNombre}</td>
							<td>${indiCalidadFuncionalidad.usuario}</td>
							<td>${indiCalidadFuncionalidad.pruebasEjecutadas}</td>
							<td>${indiCalidadFuncionalidad.hallazgosReportados}</td>
							<td>${indiCalidadFuncionalidad.hallazgosInvalidados}</td>
							<td>${indiCalidadFuncionalidad.hallazgosAnulados}</td>
							<td>${indiCalidadFuncionalidad.indicador.indicadorString}</td>
							<td><img style="width: 20px; height: 20px"
								src="<%=request.getContextPath()%>/resources/images/indicadores/${indiCalidadFuncionalidad.indicador.semaforoImage}">
							</td>
						</tr>
					</c:forEach>
					<tr>
						<c:set var="totales"
							value="${indicadores.indicadorCalidadFuncionalidad[listSize-1]}" />
						<th colspan="2">Totales</th>
						<td>${totales.pruebasEjecutadas}</td>
						<td>${totales.hallazgosReportados}</td>
						<td>${totales.hallazgosInvalidados}</td>
						<td>${totales.hallazgosAnulados}</td>
						<td>${totales.indicador.indicadorString}</td>
						<td><img style="width: 20px; height: 20px"
							src="<%=request.getContextPath()%>/resources/images/indicadores/${totales.indicador.semaforoImage}">
					</tr>
				</c:if>
			</tbody>
		</table>
	</fieldset>

	<br/>
 <fieldset>
 <legend>
 <h5>
<strong> <spring:message code="lblHallazgosRecurso" />
				</strong>
			</h5>	
 </legend>
 	<div>
	<p>
	<spring:message code="formulaIndicadorHallazgosRecurso" />
	</p>
	</div>
	<form >
	<div class="form-group">
	<div class="col-sm-4">
	<label class="control-label">Omitir los Siguientes Estados:</label>
					
					<select id="estadoHallazgo" style="height: 100px" multiple class="form-control input-sm">
					</select>
					</div>
					<div class="col-sm-4">
	<label class="control-label">Omitir las Siguientes Causas de Generación:</label>
					
					<select id="causaGeneracion" style="height: 100px" multiple class="form-control input-sm">
					</select>
					</div>
	</div>
	</form>	
 </fieldset>
 <br/>
 <div id="hallazgosRecurso">
 </div>
 <br/><br/>
	<fieldset>
		<legend>
			<h5>
				<strong> ${indicadores.indicadorReprocesoConstruccionPruebas.parametroIndicador.nombre}
				</strong>
			</h5>
		</legend>
		<div>
			<p>
				<spring:message code="lbl_initFormula"/> ${indicadores.indicadorReprocesoConstruccionPruebas.parametroIndicador.formula}
			</p>
		</div>
		<br/>
		<div style="text-align: center; margin: 0 30px;">
			<div class="row">
				<div class="col-md-4" style="font-weight: bold">
					<spring:message code="lbl_indicadorReprocesoDisenioPruebas" />
				</div>
				<div class="col-md-1">${indicadores.indicadorReprocesoConstruccionPruebas.indicador.indicadorString}</div>
				<div class="col-md-1">
					<img style="width: 20px; height: 20px"
						src="<%=request.getContextPath()%>/resources/images/indicadores/${indicadores.indicadorReprocesoConstruccionPruebas.indicador.semaforoImage}" />
				</div>
				<div class=""></div>
			</div>
			<br/>
			<div class=row>
				<div class="col-md-6">
					<table class="table table-bordered" style="font-size: 12px">
						<thead>
							<tr>
								<th>Pruebas Construidas</th>
								<th>Pruebas No Aplican</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${indicadores.indicadorReprocesoConstruccionPruebas.pruebasConstruidas}</td>
								<td>${indicadores.indicadorReprocesoConstruccionPruebas.pruebasNoAplican}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</fieldset>
	
	<br/>

	<fieldset>
		<legend>
			<h5>
				<strong>
					${indicadores.indicadorEfectividadHallazgosReportados.parametroIndicador.nombre}
				</strong>
			</h5>
		</legend>
		<div>
			<p>
				<spring:message code="lbl_initFormula"/> ${indicadores.indicadorEfectividadHallazgosReportados.parametroIndicador.formula}
			</p>
		</div>
		<br/>
		<div style="text-align: center; margin: 0 30px;">
			<div class="row">
				<div class="col-md-4" style="font-weight: bold">
					Indicador ${indicadores.indicadorEfectividadHallazgosReportados.parametroIndicador.nombre}:
				</div>
				<div class="col-md-1">${indicadores.indicadorEfectividadHallazgosReportados.indicador.indicadorString}</div>
				<div class="col-md-1">
					<img style="width: 20px; height: 20px"
						src="<%=request.getContextPath()%>/resources/images/indicadores/${indicadores.indicadorEfectividadHallazgosReportados.indicador.semaforoImage}" />
				</div>
				<div class=""></div>
			</div>
			<br/>
			<div class=row>
				<div class="col-md-8">
					<table class="table table-bordered" style="font-size: 12px">
						<thead>
							<tr>
								<th>Hallazgos Reportados</th>
								<th>Hallazgos Anulados</th>
								<th>Hallazgos Invalidados</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>${indicadores.indicadorEfectividadHallazgosReportados.hallazgosReportados}</td>
								<td>${indicadores.indicadorEfectividadHallazgosReportados.hallazgosAnulados}</td>
								<td>${indicadores.indicadorEfectividadHallazgosReportados.hallazgosInvalidados}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</fieldset>

	<br /> <br />
	
	<fieldset>
		<c:set var="listSize" value="${fn:length(indicadores.indicadorMejorasIdentificadas)}" />
		<legend>
			<h5>
				<strong>
					${indicadores.indicadorMejorasIdentificadas[0].parametroIndicador.nombre}
				</strong>
			</h5>
		</legend>
		<div>
			<p>
				<spring:message code="lbl_initFormula" />
				${indicadores.indicadorMejorasIdentificadas[0].parametroIndicador.formula}
			</p>
		</div>

		<table class="table table-bordered" id="indiMejorasIdentificadas"
			style="font-size: 12px">
			<thead>
				<tr>
					<th><spring:message code="lbl_responsable" /></th>
					<th><spring:message code="lbl_cantidadMejoras" /></th>
					<th><spring:message code="lbl_mejorasAnuladas" /></th>
					<th><spring:message code="lbl_mejorasInvalidas" /></th>
					<th><spring:message code="lbl_mejorasAceptadas" /></th>
					<th><spring:message code="lbl_indicador" /></th>
					<th><spring:message code="lbl_semaforo" /></th>
				<tr>
			</thead>
			<tbody>
			
				<c:if test="${listSize > 1}">
					<c:set var="size" value="${listSize - 2}" />
					<c:forEach var="indiMejorasIdentificadas" begin="0" end="${size}"
						items="${indicadores.indicadorMejorasIdentificadas}" varStatus="i">
						<tr>
							<td>${indiMejorasIdentificadas.usuarioResponsable}</td>
							<td>${indiMejorasIdentificadas.mejoras}</td>
							<td>${indiMejorasIdentificadas.mejorasAnuladas}</td>
							<td>${indiMejorasIdentificadas.mejorasInvalidadas}</td>
							<td>${indiMejorasIdentificadas.mejorasAceptadas}</td>
							<td>${indiMejorasIdentificadas.indicador.indicadorString}</td>
							<td><img style="width: 20px; height: 20px"
								src="<%=request.getContextPath()%>/resources/images/indicadores/${indiMejorasIdentificadas.indicador.semaforoImage}">
							</td>
						</tr>
					</c:forEach>
					<tr>
						<c:set var="totales"
							value="${indicadores.indicadorMejorasIdentificadas[listSize-1]}" />
						<th>Totales</th>
						<td>${totales.mejoras}</td>
						<td>${totales.mejorasAnuladas}</td>
						<td>${totales.mejorasInvalidadas}</td>
						<td>${totales.mejorasAceptadas}</td>
						<td>${totales.indicador.indicadorString}</td>
						<td><img style="width: 20px; height: 20px"
							src="<%=request.getContextPath()%>/resources/images/indicadores/${totales.indicador.semaforoImage}">
					</tr>
				</c:if>
			</tbody>
		</table>
	</fieldset>

	<br /> <br />

<fieldset>
		<legend>
			<h5>
				<strong> <spring:message code="lbl_hallazgosporgarantias" />
				</strong>
			</h5>
		</legend>
		<div>
			<p>
				<spring:message code="formulaHallazgoGarantia" /> ${hallazgoGarantia.formula}
			</p>
		</div>
<div class="form-group">
		
		<p>
			<label class="control-label"><spring:message code="OmitirCausasGeneracion" /></label>
		</p>
			<div class="col-sm-4">
			<select id="causaGeneracionHallazgosGarantia" style="height: 100px" multiple class="form-control input-sm">
			</select>
			</div>
		
</div>
</fieldset>
<br/>

<div id="hallazgosPorGarantias">

</div>

	<br/><br/>
	<fieldset>
		<legend>
			<h5>
				<strong>
					${indicadores.indicadorTiemposRespuestaGarantia.parametroIndicador.nombre}
				</strong>
			</h5>
		</legend>
		<div>
			<p>
				<spring:message code="lbl_initFormula" />
				${indicadores.indicadorTiemposRespuestaGarantia.parametroIndicador.formula}
			</p>
		</div>

		<table class="table table-bordered" id="indiCalidadFuncionalidad"
			style="font-size: 12px">
			<thead>
			<tr>
				<th><spring:message code="lbl_idHallazgo" /></th>
				<th><spring:message code="lbl_clasificacion" /></th>
				<th><spring:message code="lbl_prioridad" /></th>
				<th><spring:message code="lbl_fechaCreacion" /></th>
				<th><spring:message code="lbl_fechaSolicitud" /></th>
				<th><spring:message code="lbl_fechaRespuesta" /></th>
				<th><spring:message code="lbl_horasTranscurridas" /></th>
				<th><spring:message code="lbl_semaforo" /></th>
				<tr>
			</thead>
			<tbody>
				<c:forEach var="indiTiemposRespuesta"
					items="${indicadores.indicadorTiemposRespuestaGarantia.listaIndicador}"
					varStatus="i">
					<tr>
						<td>${indiTiemposRespuesta.idHallazgo}</td>
						<td>${indiTiemposRespuesta.clasificacion}</td>
						<td>${indiTiemposRespuesta.prioridad}</td>
						<td>${indiTiemposRespuesta.fechaCreacion}</td>
						<td>${indiTiemposRespuesta.fechaSolicitud}</td>
						<td>${indiTiemposRespuesta.fechaRespuesta}</td>
						<td>${indiTiemposRespuesta.indicador.indicadorString}</td>
						<td>
							<c:if test="${indiTiemposRespuesta.indicador.semaforoImage != ''}">
								<img style="width: 20px; height: 20px"
									src="<%=request.getContextPath()%>/resources/images/indicadores/${indiTiemposRespuesta.indicador.semaforoImage}">
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</fieldset>

</div>



	<!-- </fieldset>
	
</div> -->
		