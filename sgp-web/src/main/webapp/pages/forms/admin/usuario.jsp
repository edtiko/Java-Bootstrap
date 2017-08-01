<%@ include file="/pages/templates/layout.jsp" %>
<html>
	<head>
		<title><spring:message code="usuario-titulo" /></title>
	</head>
	<body>
		<div class="container">
			<div class="alert alert-success" style="display: none;" id="alerta">
				<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>Mensaje!</strong>
				<span id="msg"><ul></ul></span>
			</div>
			<fieldset>
				<legend><h5><strong><spring:message code="usuario-titulo" /></strong></h5></legend>
				<form class="form-horizontal" id="formUsuario" name="formUsuario" >
					<input type="hidden" id="idUsuario" name="idUsuario" value="">
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message code="lblNombreCompleto" />:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control input-sm" placeholder="Este campo es requerido" name="nombre" 
								id="nombre" title="Nombre de Usuario" value="" />			
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message code="lblCargo" />:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control input-sm" placeholder="Este campo es requerido" name="cargo"
								id="cargo" title="Cargo" value="" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message code="lblEmpresa" />:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control input-sm" placeholder="Este campo es requerido" name="empresa"
								id="empresa" title="Empresa" value="" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message code="lblCorreo" />:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control input-sm" placeholder="Este campo es requerido" name="correo"
								id="correo" title="Correo Electrónico" value="" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message code="lblTelefono" />:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control input-sm" name="telefono"
								id="telefono" title="Teléfono/Celular" value="" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message code="lblLogin" />:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control input-sm" placeholder="Este campo es requerido" name="login_id"
								id="login_id" title="Login/Usuario" value="" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message code="lblEstado" />:</label>
						<div class="col-sm-4">
							<select class="form-control input-sm" id="indActivo" name="indActivo" title="Estado">
								<option value="1"><spring:message code="activo" /></option>
								<option value="0"><spring:message code="inactivo" /></option>
							</select>
						</div>
					</div>
					<div class="col-sm-offset-2 col-sm-5">
						<button type="button" class="btn btn-default" id="btnGuardar"><spring:message code="lblGuardar" /></button>
						<button type="button" class="btn btn-default" id="btnModificar"><spring:message code="lblModificar" /></button>
						<button type="reset" class="btn btn-default" id="btnLimpiar"><spring:message code="lblLimpiar" /></button>
					</div>
				</form>
			</fieldset>
			<pmz:datatables datasource="/usuarios/obtenerUsuarios" id="usuarios" >
		        <pmz:column label="lblAccion" field="id"/>
		        <pmz:column label="lblConsecutivo" field="id" />
		        <pmz:column label="lblNombre" field="nombre"/>
		        <pmz:column label="lblCargo" field="cargo"/>
		        <pmz:column label="lblEmpresa" field="empresa"/>		        
		        <pmz:column label="lblCorreo" field="correo"/>
		        <pmz:column label="lblTelefono" field="telefono"/>
		        <pmz:column label="lblLogin" field="login"/>
		        <pmz:column label="lblEstado" field="indActivo"/>
			</pmz:datatables>
			<!-- Div para mostrar los datos de auditoria del registro seleccionado -->
		    <div class="modal fade" style="display: none" id="modal_show_usuario">
				 <div class="modal-dialog">
					<div class="modal-content">
				   		<div class="modal-header">
				        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        	<h5 class="modal-title"><strong>Detalle Usuario</strong></h5>
				      	</div>
				      	<div class="modal-body">
				      		<form class="form-horizontal">
					    		<div class="form-group">
									<label class="col-sm-4 control-label">Consecutivo:</label>
									<div class="col-sm-6">
									<p class="form-control-static" id="consReg"></p>			
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"><spring:message code="lblNombre" />:</label>
									<div class="col-sm-6">
									<p class="form-control-static" id="nombreReg"></p>		
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"><spring:message code="lblEstado" />:</label>
									<div class="col-sm-6">
									<p class="form-control-static" id="estadoReg"></p>		
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"><spring:message code="lblfechaCreacion"/>:</label>
									<div class="col-sm-6">
									<p class="form-control-static" id="fechaCreaReg"></p>			
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"><spring:message code="lblUsuarioCreacion" />:</label>
									<div class="col-sm-6">
									<p class="form-control-static" id="usuarioCreaReg"></p>			
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"><spring:message code="lblFechaEdita" />:</label>
									<div class="col-sm-6">
									<p class="form-control-static" id="fechaEditaReg"></p>			
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"><spring:message code="lblUsuarioEdita"/>:</label>
									<div class="col-sm-6">
									<p class="form-control-static" id="usuarioEditaReg"></p>		
									</div>
								</div>
							</form>
      					</div> 
				  	</div><!-- /.modal-content -->
				</div><!-- /.modal-dialog -->
			</div><!-- /.modal -->
		</div>
	    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/usuario.js"></script>
	</body>
	<hr>
	<footer class="text-center">
	<p><spring:message code="footer" /></p>
	</footer>
</html>