<%@ include file="/pages/templates/layout.jsp"%>
<html>
<head>
<title><spring:message code="descargarMapasPruebas-titulo" /></title>
</head>
<body>
	<div class="container">
		<div class="alert alert-success" style="display: none;" id="alerta">
			<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>Mensaje!</strong>
			<span id="msg"><ul></ul></span>
		</div>

		<fieldset>
			<legend>
				<h5>
					<strong><spring:message
							code="descargarMapasPruebas-titulo" /></strong>
				</h5>
			</legend>
			<form class="form-horizontal" id="frm_descargarMapasPruebas"
				name="frm_descargarMapasPruebas" 
				action="/sgp/gestionarPrueba/descargarMapasZip" method="post">
				
				<div class="form-group">
					<label class="col-sm-2 control-label">
					<spring:message code="lbl_proyecto" />
					</label>
					<div class="col-sm-4">
						<div class="input-group data-proyecto">
							<input type="text" id="proyecto" name="proyecto"
								class="form-control input-sm" data-id=""
								placeholder="Seleccione"
								title="<spring:message code="lbl_proyecto"/>" readonly>
						    <input type="hidden" id="idProyecto" name="idProyecto" />
							<div class="input-group-btn btn-group-sm">
								<button type="button" class="btn btn-default btn-group-sm"
									id="buscarProyecto">
									<spring:message code="btn_buscarProyecto" />
								</button>
							</div>
						</div>
					</div>
					<label class="col-sm-2 control-label" id="lblfecha"> <spring:message
							code="lbl_fechaCorte" />
					</label>
<!-- 					<div class="col-sm-2"> -->
<!-- 						<input type="text" id="fechaFrom" name="fechaFrom" -->
<!-- 							class="form-control input-sm" readonly -->
<%-- 							placeholder="<spring:message code="lbl_fechaInicial"/>" --%>
<%-- 							title="<spring:message code="lbl_fechaInicial"/>"> --%>
<!-- 					</div> -->
					<div class="col-sm-2">
						<input type="text" id="fechaTo" name="fechaTo"
							class="form-control input-sm" readonly
							placeholder='<spring:message code='lbl_fechaFinal'/>'
							title='<spring:message code='lbl_fechaFinal'/>'>
					</div>
				</div>
				<div class="form-actions col-sm-offset-2 col-sm-5">
					<button id="btnDescargarMapasZip" type="submit"
						class="btn btn-success"
						title="Descargar todos los Mapas en un Zip">
						<spring:message code="btn_descargarZip" />
					</button>
				</div>
				<!--<div class="form-actions col-sm-offset-2 col-sm-5">
					<button type="button" class="btn btn-default" id="consultar">
						<spring:message code="btn_consultarMapaPruebas" />
					</button>
				</div>-->
				
				
			</form>
		</fieldset>
	</div>
	<pmz:datatables datasource="/gestionarPrueba/getMapasPruebaReporte" id="mapa"
 dataUrlAsoc="descargarMapa" disableSortColumns="6">
		<pmz:column label="lbl_accion" field="id" />
		<pmz:column label="lbl_consecutivo" field="id" width="5%" />
		<pmz:column label="lbl_nombreMapas" field="nombre" />
		<pmz:column label="lbl_proyecto" field="proyecto.nombre" />
		<pmz:column label="lbl_cantidadPruebasConstruidas"
			field="totalPruebasConstruidas" width="12%" />
		<pmz:column label="lbl_cantidadPruebasEjecutadas"
			field="totalPruebasEjecutadas" width="12%" />
		<pmz:column label="lbl_cantidadHallazgos" field="totalHallazgos" width="7%" />
		<pmz:column label="lbl_fechaCreacion" field="fechaCreacionString" width="12%"/>
		<pmz:column label="lbl_fechaCreacion" field="fromFechaFilter"/>
		<pmz:column label="lbl_fechaCreacion" field="toFechaFilter" />
		<pmz:column label="lbl_proyecto" field="idProyecto" />
		
	</pmz:datatables>

    <hr />
	<footer class="text-center">
		<p>
			<spring:message code="footer" />
		</p>
	</footer>


	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/pruebas/descargarMapas.js"></script>
</body>
</html>