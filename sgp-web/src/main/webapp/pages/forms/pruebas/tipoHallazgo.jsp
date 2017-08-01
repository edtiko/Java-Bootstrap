
<html>
<head>
<%@ include file="/pages/templates/layout.jsp"%>
<title><spring:message code="lblGestionTipoHallazgo" /></title>
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
					<strong><spring:message code="lblTituloTipoHallazgo" /></strong>
				</h5>
			</legend>
			<form class="form-horizontal" id="form_tipoHallazgo">
				<div class="form-group">
					<input type="hidden" id="idTipoHallazgo"> <label
						class="col-sm-2 control-label"><spring:message
							code="lblNombre" />:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control input-sm" name="nombre"
							id="nombre" title="Nombre del Tipo de Hallazgo" value=""
							placeholder="Este campo es requerido" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><spring:message
							code="lblDescripcionTipoHallazgo" />:</label>
					<div class="col-sm-4">
						<textarea class="form-control" rows="3" name="descripcion"
							title="Descripción" id="descripcion"></textarea>
					</div>
				</div>
				
				<div class="form-group" id="divEsPuntuado">
					<label class="col-sm-2 control-label"><spring:message
							code="lblEsPuntuado" />:</label>
					<div class="col-sm-4">
						<select class="form-control input-sm" name="esPuntuado"
							id="esPuntuado" title="Es Puntuado">
							<option value="0">No</option>
							<option value="1">Sí</option>

						</select>
					</div>
				</div>
				<div class="form-group" id="divTieneCausaGeneracion">
					<label class="col-sm-2 control-label"><spring:message
							code="lblTieneCausaGeneracion" />:</label>
					<div class="col-sm-4">
						<select class="form-control input-sm" name="tieneCausaGeneracion"
							id="tieneCausaGeneracion" title="Causa Generación">
							<option value="0">No</option>
							<option value="1">Sí</option>

						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><spring:message
							code="lblEstado" />:</label>
					<div class="col-sm-4">
						<select class="form-control input-sm" id="estado" name="estado"
							title="Estado">
							<option value="1">Activo</option>
							<option value="0">Inactivo</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-5">
						<button type="button" class="btn btn-default" id="sv_tipohallazgo">
							<spring:message code="lblGuardar" />
						</button>
						<button type="button" class="btn btn-default"
							id="edit_tipohallazgo">
							<spring:message code="lblModificar" />
						</button>
						<button type="reset" class="btn btn-default">
							<spring:message code="lblLimpiar" />
						</button>
					</div>
				</div>
			</form>

		</fieldset>
	</div>
	<!-- Aqui empieza la DataTable -->
	<pmz:datatables datasource="/tipoHallazgo/getTipoHallazgos"
		id="tipoHallazgos">
		<pmz:column label="lblAccion" field="id" />
		<pmz:column label="lblConsecutivo" field="id" />
		<pmz:column label="lblTipoHallazgo" field="nombre" />
		<pmz:column label="lblEsPuntuado" field="esPuntuado" />
		<pmz:column label="lblTieneCausaGeneracion"
			field="tieneCausaGeneracion" />
		<pmz:column label="lblEstado" field="indActivo" />
	</pmz:datatables>
	<hr />
	<!-- Aqui termina  la dataTable -->
	<!--<pmz:column label="lblTblAccionesPais" field="acciones" />-->
	<!-- Modal -->
	<div class="modal fade" style="display: none" id="modal_show">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h5 class="modal-title">
						<strong><spring:message
								code="lblPopupDetalleTipoHallazgo" /></strong>
					</h5>
				</div>
				<div class="modal-body text-left">
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message
									code="lblConsecutivoTitle" />:</label>
							<div class="col-sm-6">
								<p class="form-control-static" id="consecutivo"></p>

							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message
									code="lblNombre" />:</label>
							<div class="col-sm-6">
								<p class="form-control-static" id="nombre"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message
									code="lblEstado" />:</label>
							<div class="col-sm-6">
								<p class="form-control-static" id="estado"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message
									code="lblEsPuntuado" />:</label>
							<div class="col-sm-6">
								<p class="form-control-static" id="esPuntuado_mod"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message
									code="lblTieneCausaGeneracion" />:</label>
							<div class="col-sm-6">
								<p class="form-control-static" id="tieneCausa_mod"></p>
							</div>
							<!-- /.modal-content -->
						</div>

						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message
									code="lblfechaCreacion" />:</label>
							<div class="col-sm-6">
								<p class="form-control-static" id="fec_creacion"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message
									code="lblUsuarioCreacion" />:</label>
							<div class="col-sm-6">
								<p class="form-control-static" id="usu_crea"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message
									code="lblFechaEdita" />:</label>
							<div class="col-sm-6">
								<p class="form-control-static" id="fec_mod"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message
									code="lblUsuarioEdita" />:</label>
							<div class="col-sm-6">
								<p class="form-control-static" id="usu_mod"></p>
							</div>
						</div>

					</form>
				</div>
				<!--  
				<div class="form-group">
					<label class="col-sm-4 control-label"><spring:message code="lblEstado" />:</label>
					<div class="col-sm-6">
						<p class="form-control-static" id="estado" ></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label"><spring:message code="lblfechaCreacion"/>:</label>
					<div class="col-sm-6">
						<p class="form-control-static" id="fec_creacion" ></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label"><spring:message code="lblUsuarioCreacion" />:</label>
					<div class="col-sm-6">
						<p class="form-control-static" id="usu_crea" ></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label"><spring:message code="lblFechaEdita" />:</label>
					<div class="col-sm-6">
						<p class="form-control-static" id="fec_mod" ></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label"><spring:message code="lblUsuarioEdita"/>:</label>
					<div class="col-sm-6">
						<p class="form-control-static" id="usu_mod" ></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label"><spring:message code="lblEsPuntuado"/>:</label>
					<div class="col-sm-6">
						<p class="form-control-static" id="esPuntuado_mod" ></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label"><spring:message code="lblTieneCausaGeneracion"/>:</label>
					<div class="col-sm-6">
						<p class="form-control-static" id="tieneCausa_mod" ></p>
   					</div>
				</div>
				-->
				</div>
				
				</form>
      </div>
</div><!-- /.modal -->
	<!-- /.modal -->


	<footer class="text-center">

	<p><spring:message code="footer" /></p>
	
	</footer>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/pruebas/tipohallazgo.js"></script>
</body>
</html>



