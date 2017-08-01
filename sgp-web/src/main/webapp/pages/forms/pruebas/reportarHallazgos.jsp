<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="modal-dialog" >
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" id="btnCloseModal" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h5><strong> <spring:message code="lblTituloHallazgo" /></strong></h5>
      </div>
      <div class="modal-body">
				<form class="form-horizontal" id="form_reportarHallazgo" >
				<input type="hidden" id="idCaso" value="${caso.id}">
				<input type="hidden" id="idProyecto" value="${caso.mapaPrueba.proyecto.id}">
				<input type="hidden" id="idReportarHallazgo" name="idReportarHallazgo"> 
				
					<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message
								code="lblTipoHallazgo" />:</label>
						<div class="col-sm-8">
							<select class="form-control input-sm" id="tipoHallazgo" name="tipoHallazgo">
							<option value="">Seleccione un Tipo de Hallazgo</option>
							</select>
						</div>
					</div> 
					<div class="form-group" id="divCausaGeneracion">
						<label class="col-sm-3 control-label"><spring:message
								code="lblCausaGeneracion" />:</label>
						<div class="col-sm-8">
							<select class="form-control input-sm" id="causaGeneracion" name="causaGeneracion">
							<option value="0">Seleccione una Causa de Generación</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message
								code="lblSeveridad" />:</label>
						<div class="col-sm-8">
							<select class="form-control input-sm" id="severidad" name="severidad">
							<option value="">Seleccione una Severidad</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message
								code="lblPrioridad" />:</label>
						<div class="col-sm-8">
							<select class="form-control input-sm" id="prioridad" name="prioridad">
							<option value="">Seleccione una Prioridad</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message
								code="lblAsignar" />:</label>
						<div class="col-sm-8">
							<select class="form-control input-sm" id="asignar" name="asignar">
							<option value="">Seleccione un Recurso</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"> <spring:message code="lblTitulo"/>:</label>
						<div class="col-sm-8">
							<input type="text" class="form-control input-sm" name="titulo"
								id="titulo" placeholder="Este campo es requerido"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message
								code="lblDescripcionHallazgo" />:</label>
						<div class="col-sm-8">
							<textarea class="form-control" rows="3" name="descripcion"
								id="descripcion"></textarea>
						</div>
					</div>
						<div class="form-group">
						<label class="col-sm-3 control-label"> <spring:message code="lblAdjuntar"/>:</label>
							 <div class="col-sm-8">
						      <input type="file" id="anexo" name="anexo" class="form-control input-sm">
	
						  </div>
						
					</div>
						
				</form>

		</div>
		<div class="modal-footer">
			<div class="form-group">
				<button type="button" id="sv_reportarHallazgo"
					class="btn btn-primary">
					<spring:message code="lblRegistrarHallazgo" />
				</button>
				<button type="button" id="cancelarOperacion" data-dismiss="modal"
				     title="Cancelar y deshacer operación"
					class="btn btn-default">Cancelar</button>
			</div>
		</div>
<!-- 		<div class="modal fade" style="display: none" id="modal_msg"></div> -->
	</div>
		</div>
		<script type="text/javascript">
		$(document).ready(function() {
			
			var maxFileSize = null;
			var restriccionesAnexo = null;
			var fileSize = null;

			jQuery.validator.addMethod("fileMaxSize", function(value, element, param) {
				return fileSize < param[0];
			}, "El peso del archivo seleccionado no es permitido");

			/* jQuery.validator.addMethod("allowedFiles", function(value, element, param) {
				var ext = "." + value.split(".").pop();
				ext = ext.toLocaleLowerCase();
				return param.indexOf(ext) > -1;
			}, "El tipo de archivo seleccionado no es permitido"); */
			
			fn_getRestriccionesAnexo().done(function (data){
				restriccionesAnexo = data.data;
				$("#anexo").attr("accept",restriccionesAnexo.allowedfiles);
				loadValidations(restriccionesAnexo);
			}).fail(function (jqXHR, textStatus, errorThrown){
				fn_error(jqXHR, textStatus, errorThrown);
			});
			getTipoHallazgo(0);
			getCausaGeneracion(0);
			getTipoSeveridad(0);
			getTipoPrioridad(0);
//  			$("#tipoHallazgo").getParametrosPorTipoParametro(3);
// 			$("#causaGeneracion").getParametrosPorTipoParametro(4);
// 			$("#severidad").getParametrosPorTipoParametro(1);
// 			$("#prioridad").getParametrosPorTipoParametro(2);
			
			cargarUsuariosAsociados($("#idProyecto").val());
			
			
			//Evento Guardar
			$("#sv_reportarHallazgo").on("click", function() {
				if($("#form_reportarHallazgo").valid()){
					fn_guardar();
				}

			});
			//evento cambiar tipo de hallazgo
			$(document).on("change", "#tipoHallazgo", function() {
				getCausaGeneracion($(this).val());
				getTipoSeveridad($(this).val());
				getTieneCausaGeneracion($(this).val());
			});

			$(document).on("change", "#severidad", function() {
				
				getTipoPrioridad($(this).val());
			
			});

			
			//Evento cerrar modal dialog
			$("#btnCloseModal").on("click", function(){
				/*
				 * Este evento se ejecuta cuando se cierra el modal dialog.
				 * Cuando el modal dialog se carga, carga las variables
				 * idCaso y ultimoCumple.
				 */
				fn_deshacerOperacion(idCaso, ultimoCumple);
			});
			
			//Evento cancelar
			$("#cancelarOperacion").on("click", function() {
				console.log("Operacion cancelada");
				console.log("ultimo cumple: "+ultimoCumple);
				fn_deshacerOperacion(idCaso, ultimoCumple);
			});
		 
			//Evento seleccionar archivo anexo
			$("#anexo").on("change", function() {
				//retorna el size del archivo en bytes, se convierte a KB.
				if(this.files[0]) {
					fileSize = (this.files[0].size) / 1024;
					$("#form_reportarHallazgo").valid();
				} else {
					fileSize = 0;
				}
			});

		});
</script>
