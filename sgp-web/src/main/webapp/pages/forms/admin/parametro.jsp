<%@ include file="/pages/templates/layout.jsp"%>
<html>
<head>
<title><spring:message code="parametro-titulo" /></title>
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
					<strong><spring:message code="parametro-titulo" /></strong>
				</h5>
			</legend>
			<form class="form-horizontal" id="frm_parametro" name="frm_parametro">
				<input type="hidden" id="idParametro" value="" />
				<div class="form-group">
					<label class="col-sm-2 control-label">Tipo de Parámetro:</label>
					<div class="col-sm-4">
						<select id="tipoParametro" name="tipoParametro" title="Tipo de Parámetro"
							class="form-control input-sm">
							<option value="">Seleccione un Tipo de Parámetro..</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Nombre:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control input-sm" name="nombre" title="Nombre"
							id="nombre" placeholder="Este campo es requerido" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><spring:message code="lblDescripcion" />:</label>
					<div class="col-sm-4">
						<textarea class="form-control" rows="3"name="descripcion" id="descripcion" title="Descripción"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Valor:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control input-sm" name="valor"
							id="valor" title="Valor" placeholder="Este campo es requerido" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Estado:</label>
					<div class="col-sm-4">
						<select class="form-control input-sm" id="estado" title="Estado">
							<option value="1">Activo</option>
							<option value="0">Inactivo</option>
						</select>
					</div>
				</div>

				<div class="form-actions col-sm-offset-2 col-sm-5">
					<button type="button" class="btn btn-default" id="sv_param"><spring:message code="lblGuardar" /></button>
					<button type="button" class="btn btn-default" id="edit_param"><spring:message code="lblModificar" /></button>
					<button type="reset" class="btn btn-default" ><spring:message code="lblLimpiar" /></button>
				</div>

			</form>
		</fieldset>
	</div>
	<pmz:datatables datasource="/parametros/getParametros" id="parametros">
		<pmz:column label="lblAccion" field="id" />
		<pmz:column label="lblConsecutivo" field="id" />
		<pmz:column label="lblTipo" field="tipoParametro.nombre" />
		<pmz:column label="lblNombreParametro" field="nombre" />
		<pmz:column label="lblValor" field="valor" />
		<pmz:column label="lblEstado" field="indActivo" />
	</pmz:datatables>
	<hr />
	<footer class="text-center">
		<p><spring:message code="footer" /></p>
	</footer>

	<div class="modal fade" style="display: none" id="modal_show">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h5 class="modal-title"><strong>Detalle Parámetro</strong></h5>
				</div>
				<div class="modal-body text-left">
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label class="col-sm-4 control-label">Consecutivo:</label>
							<div class="col-sm-6">
								<p class="form-control-static" id="consecutivo"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message code="lblNombre" />:</label>
							<div class="col-sm-6">
								<p class="form-control-static" id="nombre"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message code="lblEstado" />:</label>
							<div class="col-sm-6">
								<p class="form-control-static" id="estado"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message code="lblfechaCreacion" />:</label>
							<div class="col-sm-6">
								<p class="form-control-static" id="fec_creacion"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message code="lblUsuarioCreacion" />:</label>
							<div class="col-sm-6">
								<p class="form-control-static" id="usu_crea"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message code="lblFechaEdita" />:</label>
							<div class="col-sm-6">
								<p class="form-control-static" id="fec_mod"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label"><spring:message code="lblUsuarioEdita" />:</label>
							<div class="col-sm-6">
								<p class="form-control-static" id="usu_mod"></p>
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


	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/parametros/parametros.js"></script>
	<script type="text/javascript">

      </script>
</body>
</html>