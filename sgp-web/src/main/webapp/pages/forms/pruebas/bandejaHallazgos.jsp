<%@ include file="/pages/templates/layout.jsp"%><html>
<head>
<title><spring:message code="lblTituloBandejaHallazgos" /></title>
</head>
<body>
	<div class="container">
	<div class="panel panel-default">
    <div class="panel-heading">
    <input type="hidden" id="userLogin" value="${userLogin}"/>
      <h5>
         <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
         <strong><spring:message code="lblTituloBandejaHallazgos" /></strong>
        </a>
      </h5>
    </div>
    <div id="collapseOne" class="panel-collapse collapse">
      <div class="panel-body">
      <form class="form-horizontal" role="form">
				<div class="row">
					<div class="col-sm-6 col-sm-offset">
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message
									code="lblProyecto" />:</label>
							<div class="col-lg-8">
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="proyecto"
										name="proyecto" placeholder="Seleccione un Proyecto"
										readOnly>
									<div class="input-group-btn btn-group-sm">
										<button class="btn btn-default btn-group-sm" type="button"
											id="buscarProyecto">Buscar</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-sm-offset">
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message
									code="lblMapaPruebas" />:</label>
							<div class="col-sm-8">
								<select class="form-control input-sm" id="mapaPruebas" name="mapaPruebas" placeholder ="Seleccione un Mapa de Pruebas">
								<option value="">Seleccione un Mapa de Pruebas</option>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset">
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message
									code="lblRegistra" />:</label>
							<div class="col-lg-8">
								<div class="input-group">
									<input type="text" class="form-control input-sm" id="usuario"
										name="usuario" data-id=""
										placeholder="Seleccione un Usuario" readOnly>
									<div class="input-group-btn btn-group-sm">
										<button class="btn btn-default btn-group-sm" type="button"
											id="buscarUsuario">Buscar</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-sm-offset">
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message
									code="lblTipoHallazgo" />:</label>
							<div class="col-sm-8">
								<select class="form-control input-sm" id="tipoHallazgo" name="tipoHallazgo">
								<option value="">Seleccione un Tipo de Hallazgo</option>
								</select>
							</div>
						</div>
					</div>
				</div>
		<div class="row">
			<div class="col-sm-6 col-sm-offset">
				<div class="form-group">	
						<label class="col-sm-4 control-label">Fecha Creación:</label>
						<div class="col-sm-4" >
								<input type="text" id="fechaFrom" name="fechaFrom"
									class="form-control input-sm" placeholder="Fecha Inicial"
									title="Fecha Inicial" >
						</div>
						<div class="col-sm-4" >
								<input type="text" id="fechaTo" name="fechaTo"
									class="form-control input-sm" placeholder="Fecha Final"
									title="Fecha Final" >
						</div>
				</div>
			</div>
			<div class="col-sm-6 col-sm-offset">
				<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message
									code="lblCausaGeneracion" />:</label>
							<div class="col-sm-8">
								<select class="form-control input-sm" id="causaGeneracion" name="causaGeneracion">
								<option value="0">Seleccione una Causa de Generación</option>
								</select>
							</div>
				</div>
			</div>				
					
					</div>
		
				<div class="form-actions col-sm-offset-2 col-sm-5">
					<button type="reset" class="btn btn-default" ><spring:message code="lblLimpiar" /></button>
				</div>
				</form>
      </div>
      </div>
      </div>
				</div>
				<pmz:datablehallazgos datasource="/hallazgos/getHallazgos"
					id="bandejaHallazgos" dataUrlAsoc="bandejaHallazgos">
					<pmz:column label="lblConsecutivo" field="id" />
					<pmz:column label="lblTipoHallazgo" field="tipoHallazgo" />
					<pmz:column label="lblArtefacto" field="artefacto" />
					<pmz:column label="lblSeveridad" field="severidad" />
					<pmz:column label="lblPrioridad" field="prioridad" />
					<pmz:column label="lblTitulo" field="titulo" />
					<pmz:column label="lblfechaCreacion" field="fechaCreacionString" />
					<pmz:column label="lblEstadoActual" field="accion" />
					<pmz:column label="lblProyecto" field="proyecto" />
					<pmz:column label="lblMapaPrueba" field="mapaPrueba" />
					<pmz:column label="lblCausaGeneracion" field="causaGeneracion" />
					<pmz:column label="lblUsuarioCreacion" field="usuarioAsigna" />
					<pmz:column label="lblfechaCreacion" field="fromFechaFilter" />
		            <pmz:column label="lblfechaCreacion" field="toFechaFilter" />
		            <pmz:column label="lblAsignada" field="usuarioAsignado" />
		            <pmz:column label="lblAsignada" field="indBandeja" />
				    </pmz:datablehallazgos>
			
<!-- 	<div class="container table-responsive"> -->
<!--     <table class="table table-bordered table-condensed dataTable" > -->
<!--     <tr> -->
<!--     <td class="danger"><label class="control-label">Bloqueante</label></td> -->
<!--     <td style="background-color:#d9edf7" ><label class="control-label">Funcional</label></td> -->
<!--     <td class="success"><label class="control-label">Presentación</label></td> -->
<!--     </tr> -->
<!--     </table> -->
<!--     </div> -->
   
<div id="tablas"  class="container table-responsive">
</div>

	<hr />
	<footer class="text-center">
		<p>
			<spring:message code="footer" />
		</p>
	</footer>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/pruebas/bandejaHallazgos.js"></script>
</body>
</html>