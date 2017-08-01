<%@ include file="/pages/templates/layout.jsp"%>
<html>
<head>
<title><spring:message code="tipoparametro-titulo" /></title>
</head>
<body>
	<div class="container">
		<div class="alert alert-success" style="display: none;" id="alerta">
			<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>Mensaje!</strong>
			<span id="msg"><ul></ul></span>
		</div>
		<fieldset>
			<legend><h5><strong><spring:message code="tipoparametro-titulo" /></strong></h5></legend>
			<form class="form-horizontal frm" id="frm_tipoparametro" name="frm_tipoparametro" >
				<input type="hidden" id="id" value="" />
		
				<div class="form-group">
					<label class="col-sm-2 control-label">Nombre:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control input-sm" name="nombre" title="Nombre"
							id="nombre"  placeHolder="Este campo es requerido" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Descripción:</label>
					<div class="col-sm-4">
						<textarea class="form-control input-sm" rows="3" name="descripcion" id="descripcion" title="Descripción" ></textarea>
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
					<button type="button" class="btn btn-default" id="sv_param">Guardar</button>
					<button type="button" class="btn btn-default" id="edit_param">Editar</button>
					<button type="reset" class="btn btn-default">Limpiar</button>
				</div>

			</form>
		</fieldset>
	</div>
	<pmz:datatables datasource="/tipoParametros/getTiposParametro" id="tiposparametro" >
		<pmz:column label="lblAccion" field="id" />
		<pmz:column label="lblConsecutivo" field="id" />
		<pmz:column label="lblNombre" field="nombre" />
		<pmz:column label="lblEstado" field="indActivo" />
	</pmz:datatables>

	<div class="modal fade" style="display: none" id="modal_show">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h5 class="modal-title"><strong>Detalle Tipo Parámetro</strong></h5>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" role="form">
        <div class="form-group">
					<label class="col-sm-4 control-label">Consecutivo:</label>
					<div class="col-sm-6">
					<p class="form-control-static" id="consecutivo" ></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label"><spring:message code="lblNombre" />:</label>
					<div class="col-sm-6">
						<p class="form-control-static" id="nombre" ></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label"><spring:message code="lblEstado" />:</label>
					<div class="col-sm-6">
						<p class="form-control-static" id="estado" ></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label"><spring:message code="lblfechaCreacion" />:</label>
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
					<label class="col-sm-4 control-label"><spring:message code="lblUsuarioEdita" />:</label>
					<div class="col-sm-6">
						<p class="form-control-static" id="usu_mod" ></p>
					</div>
				</div>
				</form>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	<script type="text/javascript"
		src="<%=request.getContextPath() %>/resources/js/parametros/tipoParametro.js"></script>
	<script type="text/javascript">

      </script>
</body>
	<hr />
	<footer class="text-center">
		<p><spring:message code="footer" /></p>
	</footer>

</html>