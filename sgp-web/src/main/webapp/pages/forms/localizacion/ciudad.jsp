<%@ include file="/pages/templates/layout.jsp"%>
<html>
<!-- prueba commit -->
<head>
<title><spring:message code="lblGestionCiudad" /></title>
</head>
<body>

	<div class="container">
		<div class="alert alert-success" style="display: none;" id="alerta">
			<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>Mensaje!</strong>
			<span id="msg"><ul></ul></span>
		</div>
			<fieldset>
				<legend>
				<h5><strong> <spring:message code="lblTituloCiudad" /></strong></h5>
				</legend>
				<form class="form-horizontal" id="form_ciudad">
					<div class="form-group">
						<input type="hidden" id="idCiudad" name="idCiudad"> 
						<label class="col-sm-2 control-label"> <spring:message code="lblCiudadNombre"/></label>
						<div class="col-sm-4">
							<input type="text" class="form-control input-sm" name="nombre"
								id="nombre" title="Nombre de la Ciudad" placeholder="Este campo es requerido" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message
								code="lblDescripcionCiudad" /></label>
						<div class="col-sm-4">
							<textarea class="form-control" rows="3" name="descripcion" title="Descripci�n" id="descripcion"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message
								code="lblPais" />:</label>
						<div class="col-sm-4">
							<select class="form-control input-sm" id="pais" name="pais" title="Pa�s">
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message
								code="lblDepartamento" />:</label>
						<div class="col-sm-4">
							<select class="form-control input-sm" id="departamento" title="Departamento"
								name="departamento" readonly>

							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message
								code="lblEstadoCiudad" /></label>
						<div class="col-sm-4">
							<select class="form-control input-sm" id="estado" name="estado" title="Estado">
								<option value="1">Activo</option>
								<option value="0">Inactivo</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-5">
							<button type="button" id="sv_ciudad" class="btn btn-default">
								<spring:message code="lblGuardar" />
							</button>
							<button type="button" id="edit_ciudad" class="btn btn-default">
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
		<pmz:datatables datasource="/ciudades/getCiudades" id="ciudades">
			<pmz:column label="lblAccion" field="id" />
			<pmz:column label="lblConsecutivo" field="id" />
			<pmz:column label="lblNombrePaisDepartamento" field="pais.nombre" />
			<pmz:column label="lblNombreDepartamentoCiudad" field="departamento.nombre" />
			<pmz:column label="lblNombreCiudad" field="nombre" />
			<pmz:column label="lblEstado" field="indActivo" />
		</pmz:datatables>
			<hr />
		<!-- Aqui termina  la dataTable -->

		<!-- Modal -->
<div class="modal fade" style="display: none" id="modal_show">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h5 class="modal-title"><strong>Detalle Ciudad</strong></h5>
      </div>
      <div class="modal-body text-left">
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
				</form>
      </div>
    
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	<!-- /.modal -->
	<footer class="text-center">
		<p><spring:message code="footer" /></p>
	</footer>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/parametros/ciudad.js"></script>
</body>
</html>