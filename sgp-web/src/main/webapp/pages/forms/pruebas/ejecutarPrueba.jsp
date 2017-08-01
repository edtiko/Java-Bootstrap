<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ include file="/pages/templates/layout.jsp"%>
<html>
<head>
<title><spring:message code="tituloEjecutarCasodePrueba" /></title>
<style type="text/css">
.bordeInput {
	background-color: #FFFFFF;
	border-width: 0;
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
					<strong><spring:message code="tituloEjecutarCasodePrueba" />
					</strong>
				</h5>
			</legend>
			<form class="form-horizontal frm" method="post"
				id="frm_casosdeprueba" name="frm_casosdeprueba">
				<input type="hidden" id="idCasoPrueba" value="" />
			
				<!-- empieza la prueba div de hallazgo -->
				<div class="form-group" id="mapa_container">
					<label class="col-sm-2 control-label"><spring:message
							code="lblProyecto" />:</label>
					<div class="col-sm-4">
						<div class="input-group">
							<input type="text" id="proyecto" name="proyecto" class="form-control input-sm" data-id="" title="Proyecto" placeholder="Seleccione un Proyecto" readonly>
							<div class="input-group-btn btn-group-sm">
								<button type="button" class="btn btn-default btn-group-sm" id="buscarProyecto">Buscar</button>
							</div>
						</div>
					</div>
					<label class="col-sm-2 control-label"><spring:message
							code="lblMapaPruebas" />:</label>
					<div class="col-sm-4">
						<select class="form-control input-sm" id="mapaPruebas"
							name="mapaPruebas" title="Mapa de Pruebas" readonly>
							<option>Seleccione un Mapa de Pruebas</option>
						</select>
					</div>
				</div>

				<!-- termina el primer div -->
			</form>
		</fieldset>
	</div>
	<pmz:datatables datasource="/casodepruebas/getcasosdeprueba"
		id="casosdeprueba" dataUrlAsoc="ejecutarPrueba">
		<pmz:column label="lblAccion" field="id" width="7%"/>
		<pmz:column label="lblConsecutivoCaso" field="consecutivo" width="5%" />
		<pmz:column label="lblIdCasoPrueba" field="id" width="5%"/>
		<pmz:column label="lblArtefacto" field="artefacto.nombre" />
		<pmz:column label="lblDescripcionEjePrueba" field="descripcion" width="30%" 
		cssclass="text-format" styleColumnHeader="text-align: center !important;" />
		<pmz:column label="lblTipoprueba" field="tipoPrueba.nombre" />
		<pmz:column label="lblMapaPruebas" field="mapaPrueba.nombre" />
		<pmz:column label="lblResultado" field="resultado" width="30%" cssclass="text-format"
		styleColumnHeader="text-align: center !important;" />
		<pmz:column label="lblCumple" field="cumple" width="7%" />
	</pmz:datatables>
	<div class="modal fade" style="display: none" id="modal_hallazgo">
	</div>
	<div class="modal fade" style="display: none" id="modal_check">
	</div>
	<div class="modal fade" style="display: none" id="modal_msg"></div>
	<!-- 	Modal detalle del registro -->
	<div class="modal fade" style="display: none" id="modal_show">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h5 class="modal-title"><strong>
						<spring:message code="detallecasoprueba" />
					</strong>
					</h5>
					
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
					<h4 class="modal-title">
						<spring:message code="tltCargueCasosPruebas" />
					</h4>
				</div>
				<div class="modal-body text-left">
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message
									code="tltCargueCasosPruebas" /></label>
							<div class="col-sm-7">
								<input name="logo" id="logo" type="file" multiple
									accept='application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel' /><br />
								<br /> <br />
								<div class="col-sm-7" id="result"></div>
							</div>
						</div>

					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" id="upload_action">
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
		src="<%=request.getContextPath()%>/resources/js/pruebas/ejecutarPrueba.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/pruebas/reportarHallazgos.js"></script>
</body>
<div style="text-align: center">
	<footer>
		<hr>
		<p>
			<spring:message code="footer" />
		</p>
	</footer>
</div>
</html>