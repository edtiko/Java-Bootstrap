<%@ include file="/pages/templates/layout.jsp" %>
<html>
	<head>
		<link rel="import" href="/pages/templates/layout.jsp" />
		<style type="text/css"></style>
		<title><spring:message code="empresa-titulo" /></title>
	</head>
	<body>
		<div class="container">
			<div class="alert alert-success" style="display: none;" id="alerta">
				<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>Mensaje!</strong>
				<span id="msg"><ul></ul></span>
			</div>
				<legend><h5><strong><spring:message code="empresa-titulo" /></strong></h5></legend>				
				<form class="form-horizontal frm" id="formEmpresa" name="formEmpresa" method="post" action=''>
					<div class="row">
					  <div class="col-sm-6 col-sm-offset">
					  			
						  		<table class="form-group "  style="font-size: 12px;margin-left: 20px">
						  		<tr>
							  		<td style="vertical-align:top;text-align:right; width: 130px">
										<label class="control-label" ><spring:message code="lblNombre" />:&nbsp;&nbsp;&nbsp;&nbsp;</label>
									</td>
									<td style="width: 350px" >	
										<input type="text" class="form-control input-sm" placeholder="Este campo es requerido" name="nombre" 
												id="nombre" title="Nombre de Empresa"  />			
									</td>
								</tr>
									
								</table>
								<table class="form-group "  style="font-size: 12px;margin-left: 20px">
								<tr><td style="vertical-align:top;text-align:right;width: 130px">
									<label class="control-label"><spring:message code="lblDescripcion" />:&nbsp;&nbsp;&nbsp;&nbsp;</label>
									</td><td style="width: 350px" >
									
										<textarea class="form-control textarea-sm" name="descripcion" id="descripcion" title="Descripción" rows="" cols=""></textarea>
									</td></tr>
								</table>
								<table class="form-group "  style="font-size: 12px;margin-left: 20px">
								<tr><td style="vertical-align:top;text-align:right;width: 130px">
									<label class="control-label"><spring:message code="lblNit" />:&nbsp;&nbsp;&nbsp;&nbsp;</label>
									</td>
									<td style="width: 350px" >
									
										<input type="text" class="form-control input-sm" name="nit" 
											id="nit" title="Nit"  />
									</td></tr>
									</table>
								
								<table class="form-group "  style="font-size: 12px;margin-left: 20px">
								<tr><td style="vertical-align:top;text-align:right;width: 130px">
									<label class="control-label"><spring:message code="lblTelefono" />:&nbsp;&nbsp;&nbsp;&nbsp;</label>
									</td><td style="width: 350px" >
									
										<input type="text" class="form-control input-sm" name="telefono"
											id="telefono" title="Teléfono/Celular" value="" />
									</td></tr>
									</table>
								
								<table class="form-group "  style="font-size: 12px;margin-left: 20px">
								<tr><td style="vertical-align:top;text-align:right;width: 130px">
									<label class="control-label"><spring:message code="lblDireccion" />:&nbsp;&nbsp;&nbsp;&nbsp;</label>
									</td><td style="width: 350px" >
									
										<input type="text" class="form-control input-sm" name="direccion"
											id="direccion" title="Dirección" value="" />
									</td>
									</tr>
									</table>
							
						</div>
						<input type="hidden" name="rutaHidden" id="rutaHidden" value="">
						<input type="hidden" id="idEmpresa" name="idEmpresa" value="">
					  	<div class="col-sm-6 col-sm-offset">
					  	
							 <table class="form-group "  style="font-size: 12px">
								<tr>
									<td style="vertical-align:top;text-align:right;width: 120px">
					  					<label style="vertical-align:top;" class="control-label"><spring:message code="lblLogo" />:&nbsp;&nbsp;&nbsp;&nbsp;</label>
					  				</td>
					  				<td>
										<input name="logo" id="logo" type="file" />
										<div  id="result"></div>
									</td>									
								</tr>
							</table>
							<table class="form-group"  style="font-size: 12px">
								 <tr>
									<td style="vertical-align:top;text-align:right;width: 120px">
									<label class="control-label"><spring:message code="lblPais" />:&nbsp;&nbsp;&nbsp;&nbsp;</label>
									</td>
									<td>
									
										<select class="form-control input-sm" id="pais" name="pais" title="País" >
										</select>
								
									</td>
									</tr>
								</table>
								<table class="form-group"  style="font-size: 12px">
								<tr>
									<td  style="vertical-align:top;text-align:right;width: 120px">
									<label class="control-label"><spring:message code="lblDepartamento" />:&nbsp;&nbsp;&nbsp;&nbsp;</label>
									</td>
									<td>
								
										<select class="form-control input-sm" id="departamento" name="departamento" readonly title="Departamento" >
										</select>
								
									</td>
									</tr>
								</table>	
									
									<table class="form-group "  style="font-size: 12px">
									<tr>
									<td style="vertical-align:top;text-align:right;width: 120px" >
									<label class="control-label"><spring:message code="lblCiudad" />:&nbsp;&nbsp;&nbsp;&nbsp;</label>
									</td>
									<td>
									
										<select class="form-control input-sm" id="ciudad" name="ciudad" readonly title="Ciudad" >
										</select>
									
									</td>
									</tr>
								</table>
									<table class="form-group "  style="font-size: 12px">
									<tr>
									<td style="vertical-align:top;text-align:right;width: 120px;float: left;" >
									<label class="control-label"><spring:message code="lblEstado" />:&nbsp;&nbsp;&nbsp;&nbsp;</label>
									</td>
									<td>
									
										<select class="form-control input-sm" id="estado" name="estado" title="Estado" >
											<option value="1">Activo</option>
											<option value="0">Inactivo</option>
										</select>
									</td>
									</tr>
								</table>
					  	</div>
					  	<div class="form-group">
						<div class="col-sm-offset-2 col-sm-5">
							<button type="button" id="sv_empresa"   class="btn btn-default"> <spring:message code="lblGuardar" /></button>
							<button type="button" id="ed_empresa" class="btn btn-default"><spring:message code="lblModificar" /></button>
							<button type="reset"  class="btn btn-default"><spring:message code="lblLimpiar" /></button>
						</div>
					</div>
					</div>
				</form>	
			</div>
			<pmz:datatables datasource="/empresas/obtenerEmpresas" id="empresas" >
		        <pmz:column label="lblAccion" field="id"/>
		        <pmz:column label="lblConsecutivo" field="id" />
		        <pmz:column label="lblNombre" field="nombre"/>
		        <pmz:column label="lblNit" field="nit"/>
		        <pmz:column label="lblTelefono" field="telefono"/>
		        <pmz:column label="lblDireccion" field="direccion"/>
		        <pmz:column label="lblLogo" field="imgLogo"/>
		        <pmz:column label="lblCiudad" field="ciudad.nombre"/>
		        <pmz:column label="lblEstado" field="indActivo"/>
			</pmz:datatables>
			
			<!-- Div para mostrar los datos de auditoria del registro seleccionado -->
		    <div class="modal fade" style="display: none" id="modal_show">
				 <div class="modal-dialog">
					<div class="modal-content">
				   		<div class="modal-header">
				        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        	<h5 class="modal-title"><strong><spring:message code="lblDetalle" /></strong></h5>
				      	</div>
				      	<div class="modal-body">
				      		<form class="form-horizontal" >
					    		<div class="form-group">
									<label class="col-sm-4 control-label"><spring:message code="lblConsecutivoEmpresa" />:</label>
									<div class="col-sm-6">
										<p class="form-control-static" id="consecutivoReg"  ></p>			
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"><spring:message code="lblNombre" />:</label>
									<div class="col-sm-6">
										<p class="form-control-static" id="nombreReg"  ></p>			
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"><spring:message code="lblLogo" />:</label>
									<div class="col-sm-6">
										<div id="logoReg"></div>			
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"><spring:message code="lblEstado" />:</label>
									<div class="col-sm-6">
										<p class="form-control-static" id="estadoReg"  ></p>			
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"><spring:message code="lblfechaCreacion" />:</label>
									<div class="col-sm-6">
										<p class="form-control-static" id="fechaCreaReg"  ></p>			
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"><spring:message code="lblUsuarioCreacion" />:</label>
									<div class="col-sm-6">
										<p class="form-control-static" id="usuarioCreaReg"   ></p>			
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"><spring:message code="lblFechaEdita" />:</label>
									<div class="col-sm-6">
										<p class="form-control-static" id="fechaEditaReg"   ></p>			
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"><spring:message code="lblUsuarioEdita" />:</label>
									<div class="col-sm-6">
										<p class="form-control-static" id="usuarioEditaReg"  ></p>			
									</div>
								</div>
							</form>
      					</div> 
				  	</div><!-- /.modal-content -->
				</div><!-- /.modal-dialog -->
			</div><!-- /.modal -->
	    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/util/util.js"></script>
	    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/empresa.js"></script>
	</body>
	<div  style="text-align: center">
	<footer>
	<hr>
	<p><spring:message code="footer" /></p>
	</footer>
	</div>
</html>