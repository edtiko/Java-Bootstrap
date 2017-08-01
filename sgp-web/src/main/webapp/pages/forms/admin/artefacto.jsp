<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="/pages/templates/layout.jsp" %>
<html>
	<head>
		<title><spring:message code="lblArtefacto-titulo" /></title>
	</head>
	<body>
		<div class="container">
			<div class="alert alert-success" style="display: none;" id="alerta">
				<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>Mensaje!</strong>
				<span id="msg"><ul></ul></span>
			</div>
			<fieldset>
				<legend><spring:message code="lblArtefacto-titulo" /></legend>
				<form class="form-horizontal frm" id="formArtefacto" name="formArtefacto" method="post" action="">
					<input type="hidden" id="idArtefacto" name="idArtefacto" value="">
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message code="lblNombre" /></label>
						<div class="col-sm-4">
							<input type="text" class="form-control input-sm" placeholder="Este Campo es Requerido" name="nombre" 
								id="nombre" title="<spring:message code="lblNombre" />" value="" />			
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message code="lblDescripcion" /></label>
						<div class="col-sm-4">
							<textarea class="form-control textarea-sm" name="descripcion"
								id="descripcion" title="<spring:message code="lblDescripcion" />" rows="" cols="">
							</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message code="lblTipoArtefacto" /></label>
						<div class="col-sm-4">
							<select class="form-control input-sm" placeholder="Este Campo es Requerido"
								id="tipoArtefacto" name="tipoArtefacto">								
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message code="lblEstado" /></label>
						<div class="col-sm-4">
							<select class="form-control input-sm" id="estado" name="estado" placeholder="Este Campo es Requerido">
								<option value="1"><spring:message code="activo" /></option>
								<option value="0"><spring:message code="inactivo" /></option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-5">
							<button type="button" class="btn" id="btnGuardar"><spring:message code="lblGuardar" /></button>
<%-- 							<button type="button" class="btn" id="btnConsultar"><spring:message code="lblConsultar" /></button> --%>
							<button type="button" class="btn" id="btnModificar"><spring:message code="lblModificar" /></button>
							<button type="reset" class="btn" id="btnLimpiar"><spring:message code="lblLimpiar" /></button>
						</div>
					</div>
				</form>
			</fieldset>
			<pmz:datatables datasource="/artefactos/getDataTable" id="artefactos">
		        <pmz:column label="lblAccion" field="id"/>
		        <pmz:column label="lblConsecutivo" field="id" />
		        <pmz:column label="lblTipoArtefacto" field="tipoArtefacto.nombre"/>
		        <pmz:column label="lblNombre" field="nombre"/>
		        <pmz:column label="lblDescripcion" field="descripcion"/>		        
		        <pmz:column label="lblEstado" field="indActivo"/>
			</pmz:datatables>
			<hr />
			<div  style="text-align: center">
			<footer>
			<p><spring:message code="footer" /></p>
			</footer>
			</div>
			<!-- Div para mostrar los datos de auditoria del registro seleccionado -->
		    <div class="modal fade" style="display: none" id="modal_show">
				 <div class="modal-dialog">
					<div class="modal-content">
				   		<div class="modal-header">
				        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				        	<h4 class="modal-title"><spring:message code="lblDetalleArtefacto" /></h4>
				      	</div>
				      	<div class="modal-body">
				      		<form class="form-horizontal" >
					    		<div class="form-group">
									<label class="col-sm-4 control-label"><spring:message code="lblConsecutivo" /></label>
									<div class="col-sm-6">
										<input type="text" class="form-control input-sm" name="consecutivoReg" 
											id="consecutivoReg" title="<spring:message code="lblConsecutivo" />" value="" />			
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"><spring:message code="lblNombre" /></label>
									<div class="col-sm-6">
										<input type="text" class="form-control input-sm" name="nombreReg" 
											id="nombreReg" title="<spring:message code="lblNombre" />" value="" />			
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"><spring:message code="lblEstado" /></label>
									<div class="col-sm-6">
										<input type="text" class="form-control input-sm" name="estadoReg" 
											id="estadoReg" title="<spring:message code="lblEstado" />" value="" />			
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"><spring:message code="lblfechaCreacion" /></label>
									<div class="col-sm-6">
										<input type="text" class="form-control input-sm" name="fechaCreaReg" 
											id="fechaCreaReg" title="<spring:message code="lblfechaCreacion" />" value="" />			
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"><spring:message code="lblUsuarioCreacion" /></label>
									<div class="col-sm-6">
										<input type="text" class="form-control input-sm" name="usuarioCreaReg" 
											id="usuarioCreaReg" title="<spring:message code="lblUsuarioCreacion" />" value="" />			
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"><spring:message code="lblFechaEdita" /></label>
									<div class="col-sm-6">
										<input type="text" class="form-control input-sm" name="fechaEditaReg" 
											id="fechaEditaReg" title="<spring:message code="lblFechaEdita" />" value="" />			
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label"><spring:message code="lblUsuarioEdita" /></label>
									<div class="col-sm-6">
										<input type="text" class="form-control input-sm" name="usuarioEditaReg" 
											id="usuarioEditaReg" title="<spring:message code="lblUsuarioEdita" />" value="" />			
									</div>
								</div>
							</form>
      					</div> 
				  	</div><!-- /.modal-content -->
				</div><!-- /.modal-dialog -->
			</div><!-- /.modal -->
		<hr />
	    <footer>
			<p><spring:message code="footer" /></p>
		</footer>

	    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/artefacto.js"></script>

	</body>
</html>