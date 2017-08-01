<%@ include file="/pages/templates/layout.jsp"%><html>
<head>
<title>Consultar Avance Ejecución Mapas de Pruebas</title>
</head>
<body>
	<div class="container">
	<div class="panel panel-default">
    <div class="panel-heading">
      <h5>
         <a data-toggle="collapse"  href="#collapseOne">
         <strong>Consultar Avance Ejecución Mapas de Pruebas</strong>
        </a>
      </h5>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in">
				<div class="panel-body">
					<form class="form-horizontal" role="form" 
					    id="formAvanceMapa" name="formAvanceMapa"
						action="/sgp/reportes/descargarReporteAvanceMapas" method="post">
						
						<div class="form-group ">
							<label class="col-sm-2 control-label">Proyecto:</label>
							<div class="col-sm-4">
								<div class="input-group data-proyecto">
									<input type="text" id="proyecto" name="proyecto"
										class="form-control input-sm" data-id=""
										placeholder="Seleccione un Proyecto" title="Proyecto" readonly>
									<input type="hidden" id="proyectoId" name="proyectoId" />
									<div class="input-group-btn btn-group-sm">
										<button type="button" class="btn btn-default btn-group-sm"
											id="buscarProyecto">Buscar</button>
									</div>
								</div>
							</div>
							<label class="col-sm-2 control-label" id="lblfecha"> 
							        <spring:message code="lbl_fechaCorte" />
							</label>
<!-- 							<div class="col-sm-2"> -->
<!-- 								<input type="text" id="fechaFrom" name="fechaFrom" -->
<!-- 									class="form-control input-sm" readonly -->
<%-- 									placeholder="<spring:message code="lbl_fechaInicial"/>" --%>
<%-- 									title="<spring:message code="lbl_fechaInicial"/>"> --%>
<!-- 							</div> -->
							<div class="col-sm-2">
								<input type="text" id="fechaTo" name="fechaTo"
									class="form-control input-sm" readonly
									placeholder='<spring:message code='lbl_fechaFinal'/>'
									title='<spring:message code='lbl_fechaFinal'/>'>
							</div>
						</div>

						<div class="form-actions col-sm-offset-2 col-sm-5">
							<button type="submit" class="btn btn-success"
								id="generarReporteAvance"><spring:message code="btnExportarInformeAvance"/></button>
						</div>

						<!--  termina el tercer div-->
					</form>
				</div>
			</div>
      </div>
	</div>
	
<pmz:datatables datasource="/gestionarPrueba/getMapasPruebaReporte" id="mapa" dataUrlAsoc="avance"
        disableSortColumns="3,4,5,6,7,8,9,10,11,12">
		<pmz:column label="lblConsecutivo" field="id" width="5%" />
		<pmz:column label="lblNombreMapa" field="nombre" />
		<pmz:column label="lblProyecto" field="idProyecto" />
		<pmz:column label="lblTotalPruebasConstruidas" field="totalPruebasConstruidas" width="8%"/>
		<pmz:column label="lblTotalPruebasEjecutadas" field="totalPruebasEjecutadas"   width="8%" />
		<pmz:column label="lblTotalPruebasSatisfactorias" field="totalPruebasSatisfactorias" width="10%"/>
		<pmz:column label="lblTotalPruebasInsatisfactorias" field="totalPruebasInsatisfactorias" width="10%"/>
		<pmz:column label="lblTotalPruebasAnuladas" field="totalPruebasAnuladas"   width="8%" />			
		<pmz:column label="lblTotalPruebasSinEjecutar" field="totalPruebasSinEjecutar"   width="8%" />	
		<pmz:column label="lblPorcentajeAvance" field="porcentajeAvance" width="8%"/>
		<pmz:column label="lblIndicadorEfectividad" field="porcentajeIndicador" width="8%"/>
		<pmz:column label="lbl_fechaCreacion" field="fromFechaFilter"/>
		<pmz:column label="lbl_fechaCreacion" field="toFechaFilter" />		
	</pmz:datatables>

	<hr />
	<footer class="text-center">
		<p>
			<spring:message code="footer" />
		</p>
	</footer>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/pruebas/informeAvanceMapasPruebas.js"></script>

</body>
</html>