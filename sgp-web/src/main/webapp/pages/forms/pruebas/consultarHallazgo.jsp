<%@ include file="/pages/templates/layout.jsp"%><html>
<head>
<title>Consultar Hallazgos</title>
</head>
<body>
	<div class="container">
	<div class="panel panel-default">
    <div class="panel-heading">
      <h5>
         <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
         <strong>Consulta de Hallazgos</strong>
        </a>
      </h5>
    </div>
    <div id="collapseOne" class="panel-collapse collapse">
      <div class="panel-body">
      <form class="form-horizontal" id="frmHallazgo" action="/sgp/reportes/reporteHallazgosXls" method="post">
          <input type="hidden" id="gf_idHallazgo" name="gf_idHallazgo"/>
          <input type="hidden" id="gf_artefacto" name="gf_artefacto"/>
          <input type="hidden" id="gf_severidad" name="gf_severidad"/>
          <input type="hidden" id="gf_prioridad" name="gf_prioridad"/>
          <input type="hidden" id="gf_tipoHallazgo" name="gf_tipoHallazgo"/>
          <input type="hidden" id="gf_usuAsignado" name="gf_usuAsignado"/>
          <input type="hidden" id="gf_titulo" name="gf_titulo"/>
          <input type="hidden" id="gf_estado" name="gf_estado"/>
          <input type="hidden" id="gf_causaGeneracion" name="gf_causaGeneracion"/>
          
				<div class="form-group">
					<label class="col-sm-2 control-label">Proyecto:</label>
					<div class="col-sm-4">
						<div class="input-group">
							<input type="text" id="proyecto" name="proyecto"
								class="form-control input-sm" data-id=""
								placeholder="Seleccione un Proyecto" title="Proyecto" readonly>
							<div class="input-group-btn btn-group-sm">
								<button type="button" class="btn btn-default btn-group-sm"
									id="buscarProyecto">Buscar</button>
							</div>
						</div>
					</div>
					<label class="col-sm-2 control-label"><spring:message
							code="lblMapaPruebas" />:</label>
					<div class="col-sm-4">
						<select class="form-control input-sm" id="mapaPruebas"
							name="mapaPruebas" title="Mapa de Pruebas" readonly>
							<option value="">Seleccione un Mapa de Pruebas</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Causa de Anulación:</label>
					<div class="col-sm-4">
						<select class="form-control input-sm" id="causaAnula"
							name="causaAnula" title="Causa de Anulación">
							<option value="">Seleccione una Causa de Anulación</option>
						</select>
					</div>
					<label class="col-sm-2 control-label">Tipo de Hallazgo:</label>
					<div class="col-sm-4">
						<select class="form-control input-sm" id="tipoHallazgo"
							name="tipoHallazgo" title="Tipo de Hallazgo ">
							<option value="">Seleccione un Tipo de Hallazgo</option>
						</select>
					</div>
					
				</div>
				<!-- empieza el primer div -->
				<div class="form-group">
					<label class="col-sm-2 control-label"><spring:message code="lblRegistra" />:</label>
					<div class="col-sm-4">
						<div class="input-group">
							<input type="text" id="usuario" name="usuario"
								class="form-control input-sm" data-id=""
								placeholder="Seleccione un Usuario" title="Usuario" readonly>
							<div class="input-group-btn btn-group-sm">
								<button type="button" class="btn btn-default btn-group-sm"
									id="buscarUsuario">Buscar</button>
							</div>
						</div>
					</div>
					<label class="col-sm-2 control-label">Causa de Generación:</label>
					<div class="col-sm-4">
						<select class="form-control input-sm" id="causaGeneracion"
							name="causaGeneracion" title="Causa de Generación ">
							<option value="0">Seleccione una Causa de Generación</option>
						</select>
					</div>
					
					
				
				
				</div>
				<div class="form-group">
						<label class="col-sm-2 control-label">Fecha Creación:</label>
						<div class="col-sm-2" >
								<input type="text" id="fechaFrom" name="fechaFrom"
									class="form-control input-sm" placeholder="Fecha Inicial"
									title="Fecha Inicial" >
						</div>
						<div class="col-sm-2" >
								<input type="text" id="fechaTo" name="fechaTo"
									class="form-control input-sm" placeholder="Fecha Final"
									title="Fecha Final" >
						</div>
				</div>
				<div class="form-actions col-sm-offset-2 col-sm-5">
					<button type="reset" class="btn btn-default">
						<spring:message code="lblLimpiar" />
					</button>
					<button id="btnGenerarReporteExcel" type="submit" class="btn btn-default" 
					        title="Generar Reporte en Excel" name="submitForm">
						<spring:message code="lbl_btn_generar_reporte" />
					</button>
				</div>
				<!--  termina el tercer div-->
			</form>
      </div>
      </div>
      </div>
	</div>
	<pmz:datablehallazgos datasource="/hallazgos/getHallazgos"
		id="hallazgos" dataUrlAsoc="hallazgos">
		<pmz:column label="lblConsecutivo" field="id" width="5%"/>
		<pmz:column label="lblTipoHallazgo" field="tipoHallazgo" />
		<pmz:column label="lblArtefacto" field="artefacto" />
		<pmz:column label="lblSeveridad" field="severidad" />
		<pmz:column label="lblPrioridad" field="prioridad" />
		<pmz:column label="lblAsignada" field="usuarioAsignado" />
		<pmz:column label="lblTitulo" field="titulo" />
		<pmz:column label="lblfechaCreacion" field="fechaCreacionString" />
		<pmz:column label="lblEstado" field="accion"/>
		<pmz:column label="lblProyecto" field="proyecto" />
		<pmz:column label="lblMapaPrueba" field="mapaPrueba" />
		<pmz:column label="lblNombre" field="causaAnulacion" />
		<pmz:column label="lblNombre" field="causaGeneracion" />
		<pmz:column label="lblUsuarioCreacion" field="usuarioAsigna" />
		<pmz:column label="lblfechaCreacion" field="fromFechaFilter" />
		<pmz:column label="lblfechaCreacion" field="toFechaFilter" />
	</pmz:datablehallazgos>
   <div class="container table-responsive">
    <table class="table table-bordered table-condensed dataTable">
    <tr>
    <td class="danger"><label class="control-label">Abierta</label></td>
    <td class="success"><label class="control-label">Desarrollo</label></td>
    <td style="background-color:#E6D1E4"><label class="control-label">Devuelta</label></td>
    <td class="warning"><label class="control-label">Reproceso</label></td>
    <td class="active"><label class="control-label">Cerrado</label></td>
    <td style="background-color:#F8FAB5"><label class="control-label">Anulado</label></td>
    <td style="background-color:#d9edf7"><label class="control-label">Inválido</label></td>
    </tr>
    </table>
    </div>
	<hr />
	<footer class="text-center">
		<p>
			<spring:message code="footer" />
		</p>
	</footer>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/pruebas/consultarHallazgo.js"></script>
</body>
</html>