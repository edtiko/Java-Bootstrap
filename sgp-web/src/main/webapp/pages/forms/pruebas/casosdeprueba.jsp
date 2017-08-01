<%@ include file="/pages/templates/layout.jsp"%>
<html>
<title><spring:message code="casopruebatitulo" /></title>
<head>

<style type="text/css">
.bordeInput {
	background-color: #FFFFFF;
	border-width: 0;
}
.alignLeft td {
	text-align: left !important;
}
</style>
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
					<strong><spring:message code="casopruebatitulo" /> </strong>
				</h5>
			</legend>
			<form class="form-horizontal frm" method="post"
				id="frm_casosdeprueba" name="frm_casosdeprueba">
				<input type="hidden" id="idMapaPrueba_default"
					name="idMapaPrueba_default" value="${idMapaPrueba}" /> 
					<input type="hidden" id="idCasoPrueba" value="" />

				<!-- empieza primer div -->
				<div class="form-group mapa_container data-proyecto" >
					<label class="col-sm-2 control-label"><spring:message
							code="lblProyecto" />:</label>
					<div class="col-sm-4">
						<div class="input-group">
							<input type="text" id="proyecto" name="proyecto" class="form-control input-sm" placeholder="Seleccione un Proyecto" title="Proyecto" readonly>
							<div class="input-group-btn btn-group-sm">
								<button type="button" class="btn btn-default btn-group-sm"
									id="buscarProyecto">Buscar</button>
							</div>
						</div>
					</div>
					<label class="col-sm-2 control-label"><spring:message
							code="lblMapaPruebas" />:</label>
					<div class="col-sm-4">
						<select class="form-control input-sm" id="mapaPruebas" name="mapaPruebas" title="Mapa de Pruebas" readonly>
							<option value="">Seleccione un Mapa de Pruebas</option>
						</select>
					</div>
				</div>
				<!-- termina el  primer div -->
				<br />
				<fieldset>
					<legend>
						<h5>
							<strong> <spring:message code="SubTitle" />:
							</strong>&nbsp;<span id="nombreMapaPruebas"></span>
						</h5>
					</legend>
				</fieldset>
				<!-- Empieza el segundo Div -->
				<div class="form-group data-proyecto">
					<label class="col-sm-2 control-label"><spring:message
							code="lblArtefacto" />:</label>
					<div class="col-sm-4">
						<select class="form-control input-sm" id="artefacto"
							name="artefacto" title="Artefacto" readOnly>
							<option value="">Seleccione un Artefacto</option>
						</select>
					</div>

					<label class="col-sm-2 control-label"><spring:message
							code="lblTipoprueba" />:</label>
					<div class="col-sm-4">
						<select class="form-control input-sm" id="tipoPruebas" title="Tipo de Prueba"
							name="tipoPruebas">
							<option value="">Seleccione el Tipo de Prueba</option>
						</select>
					</div>
				</div>

				<br />
				<!-- termina el segundo div -->

				<!-- empieza el tercer div -->
				<div id="mapa_container">
					<div class="form-group data-proyecto">
						<label class="col-sm-2 control-label"><spring:message
								code="lblDescricionCasoPrueba" />:</label>
						<div class="col-sm-4">
							<textarea class="form-control textarea-sm" name="descripcion" id="descripcion" rows="" cols="" title="<spring:message code="lblDescricionCasoPrueba"/>"placeholder="<spring:message code="CAMPO_REQUERIDO"/>"></textarea>
						</div>

						<label class="col-sm-2 control-label"><spring:message
								code="lblResultado" />:</label>
						<div class="col-sm-4">
							<textarea class="form-control textarea-sm" name="resultadoEsperado" id="resultadoEsperado" rows="" cols="" title="<spring:message code="lblResultado"/>"placeholder="<spring:message code="CAMPO_REQUERIDO"/>"></textarea>
						</div>
					</div>
				</div>
				<br /> <br />
				<div class="form-group" align="center">
					<div class="col-sm-offset-2 col-sm-5">
						<button type="button" id="upload_casoprueba"
							class="btn btn-default">
							<spring:message code="btnCargarArchivo" />
						</button>
						<button type="button" id="sv_casoprueba" class="btn btn-default">
							<spring:message code="btnGuardar" />
						</button>
						<button type="button" id="edit_casoprueba" class="btn btn-default">
							<spring:message code="btnEditar" />
						</button>
						<button type="reset" id="limpiar" class="btn btn-default">
							<spring:message code="btnLimpiar" />
						</button>
					</div>
				</div>
			</form>
		</fieldset>
	</div>
	<br />
	<pmz:datatables datasource="/casodepruebas/getcasosdeprueba"
		id="casosdeprueba" dataUrlAsoc="casoPrueba">
		<pmz:column label="lblAccion" field="id" />
		<pmz:column label="lblConsecutivoCaso" field="consecutivo" />
		<pmz:column label="lblArtefacto" field="artefacto.nombre" />
		<pmz:column label="lblDescricionCasoPrueba" field="descripcion"
			width="30%" cssclass="text-format"
			styleColumnHeader="text-align: center !important;" />
		<pmz:column label="lblTipoprueba" field="tipoPrueba.nombre" />
		<pmz:column label="lblMapaPruebas" field="mapaPrueba.nombre" />
		<pmz:column label="lblResultado" field="resultado" width="30%"
			cssclass="text-format"
			styleColumnHeader="text-align: center !important;" />
	</pmz:datatables>

	<!-- 	Modal detalle del registro -->
	<div class="modal fade" style="display: none" id="modal_show">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h5 class="modal-title"><strong><spring:message code="detallecasoprueba" /></strong></h5>
				</div>
				<div class="modal-body text-left">
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message
									code="lblConsecutivoEmpresa" />:</label>
							<div class="col-sm-6">
								<p class="form-control-static" id="consecutivo"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message
									code="lblIdCasoPrueba" />:</label>
							<div class="col-sm-6">
								<p class="form-control-static" id="id_caso"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message
									code="lblfechacreacion" />:</label>
							<div class="col-sm-6">
								<p class="form-control-static" id="fec_creacion"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message
									code="lblUsuariocreacion" />:</label>
							<div class="col-sm-6">
								<p class="form-control-static" id="usu_crea"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message
									code="lblfechamodificacion" />:</label>
							<div class="col-sm-6">
								<p class="form-control-static" id="fec_mod"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message
									code="lblusuariomodificacion" />:</label>
							<div class="col-sm-6">
								<p class="form-control-static" id="usu_mod"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message
									code="lblFechaEjecuta" />:</label>
							<div class="col-sm-6">
								<p class="form-control-static" id="fec_ejecuta"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message
									code="lblUsuarioEjecuta" />:</label>
							<div class="col-sm-6">
								<p class="form-control-static" id="usu_ejecuta"></p>
							</div>
						</div>
					</form>
				</div>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<div class="modal fade" style="display: none" id="modal_File_show">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 align="center" class="modal-title">
						<spring:message code="tltCargueCasosPruebas" />
					</h4>
				</div>
				<div class="modal-body text-left">
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label align="center" class="col-sm-4 control-label"><spring:message
									code="tltImportarArchivo" /></label>
							<div class="col-sm-7">
								<input name="archivo" id="archivo" type="file" class="form-control input-sm" multiple
									accept='application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel' /><br />
								<br /> <br />
							</div>
							<div id="resultImport"></div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default btn-sm" id="upload_action" data-loading-text="Cargando...">
						<spring:message code="btnCargarArchivo" />
					</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/pruebas/casosdeprueba.js"></script>
</body>
<div  style="text-align: center">
	<footer>
	<hr>
	<p><spring:message code="footer" /></p>
	</footer>
	</div>
</html>