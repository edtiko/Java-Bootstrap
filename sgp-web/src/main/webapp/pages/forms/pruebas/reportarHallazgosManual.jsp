
<html>
<head>
<%@ include file="/pages/templates/layout.jsp"%>
<title><spring:message code="lblGestionReportarHallazgo" /></title>
    <link href="<%=request.getContextPath() %>/resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
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
					<strong><spring:message code="lblTituloReportarHallazgo" /></strong>
				</h5>
			</legend>
			<form class="form-horizontal" id="form_reportarHallazgoManual">
		
				<input type="hidden" id="idReportarHallazgo" name="idReportarHallazgo"> 
				
				<div class="form-group">
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
				</div>
				<div class="form-group">
					
					<label class="col-sm-2 control-label"><spring:message
							code="lblArtefacto" />:</label>
					<div class="col-sm-4">
						<select class="form-control input-sm" id="artefacto" name="artefacto">
							<option value="">Seleccione un Artefacto</option>
						</select>
					</div>
				</div>
		
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message
								code="lblTipoHallazgo" />:</label>
						<div class="col-sm-4">
							<select class="form-control input-sm" id="tipoHallazgo" id="tipoHallazgo" name="tipoHallazgo">
							<option value="">Seleccione un Tipo de Hallazgo</option>
							</select>
						</div>
					</div>
						<div class="form-group" id="divCausaGeneracion">
					
							<label class="col-sm-2 control-label"><spring:message
								code="lblCausaGeneracion" />:</label>
							<div class="col-sm-4">
								<select class="form-control input-sm" id="causaGeneracion" name="causaGeneracion">
								<option value="0">Seleccione una Causa de GeneraciÛn</option>
								</select>
							</div>
					
					</div> 
					
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message
								code="lblSeveridad" />:</label>
						<div class="col-sm-4">
							<select class="form-control input-sm" id="severidad" name="severidad">
							<option value="">Seleccione una Severidad</option>
							</select>
						</div>
					</div>
						<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message
								code="lblPrioridad" />:</label>
						<div class="col-sm-4">
							<select class="form-control input-sm" id="prioridad" name="prioridad">
							<option value="">Seleccione una Prioridad</option>
							</select>
						</div>
					</div>
					<div class="form-group" id="divFechaSolicitud" style="display: none;">
						<label class="col-sm-2 control-label"><spring:message
								code="lblFechaSolicitud" />:</label>
					<div class="col-sm-4">
					 	<div c>
                    	<input class="form-control" id="fechaSolicitud" name="fechaSolicitud" size="16" type="text" value="" readonly>
                    
					</div>
					</div>
                </div>

				
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message
								code="lblAsignar" />:</label>
						<div class="col-sm-4">
							<select class="form-control input-sm" id="asignar" name="asignar">
							<option value="">Seleccione un Usuario</option>
							</select>
						</div>
						</div>
						<div class="form-group">
						<label class="col-sm-2 control-label"> <spring:message code="lblTitulo"/>:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control input-sm titulo" name="titulo" id="titulo" placeholder="Este campo es requerido"/>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message
								code="lblDescripcionHallazgo" />:</label>
						<div class="col-sm-4">
							<textarea class="form-control" rows="3" name="descripcion" id="descripcion"></textarea>
						</div>
					</div>
						<div class="form-group">
						<label class="col-sm-2 control-label"> <spring:message code="lblAdjuntar"/>:</label>
							 <div class="col-sm-4">
						      <input type="file" id="anexo" name="anexo" class="form-control input-sm">
	
						  </div>
					</div>
							<div class="modal-footer">
			<div class="form-group">
			<div class="col-sm-offset-2 col-sm-5">
				<button type="button" id="sv_reportarHallazgoManual"
					class="btn btn-primary">
					<spring:message code="lblRegistrarHallazgo" />
				</button>
			</div>
			</div>
		</div>
						
			</form>

		</fieldset>
	</div>
	<!-- Aqui empieza la DataTable -->
	
	<hr />

	<footer class="text-center">
  </div><!-- /.modal-dialog -->
  

	<p><spring:message code="footer" /></p>
	</footer>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/pruebas/reportarHallazgosManual.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/util/jquery-ui-timepicker-addon.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/util/validarCampo.js"></script>
	<script type="text/javascript">
            $(function(){
                //Para escribir solo letras
//                 $('#titulo').validCampo(' abcdefghijklmnÒopqrstuvwxyz·ÈÌÛ˙¡…Õ”⁄1234567890  -_%&/()#@?!°ø{}[]');
//                 $('#descripcion').validCampo(' abcdefghijklmnÒopqrstuvwxyz·ÈÌÛ˙¡…Õ”⁄1234567890  -_%&/()#@?!°ø{}[]');
            });
        </script> 
</body>
</html>



