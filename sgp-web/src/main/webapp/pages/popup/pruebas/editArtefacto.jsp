<%@ taglib prefix="pmz" tagdir="/WEB-INF/tags"%>
<div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h5 class="modal-title"><strong>Editar Artefacto de Proyecto:</strong>
        <span  data-id="" id="proyecto"></span>
        </h5>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" id="frm_asoArtefactoPro">
          <input type="hidden" id="id" >
				<div class="form-group">
					<label class="col-sm-3 control-label">Tipo Artefacto:</label>
					<div class="col-sm-6">
						<select class="form-control input-sm" name="tipoArtefacto"
							id="tipoArtefacto" title="Tipo Artefacto" >
							<option value="">Seleccione una opción..</option>
							</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">Nombre Artefacto:</label>
					<div class="col-sm-6">
						<input class="form-control input-sm" name="artefacto"
							id="artefacto" title="Artefacto" placeholder="Este campo es requerido" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">Usuario:</label>
					<div class="col-sm-6">
						<select class="form-control input-sm" name="tipoArtefacto"
							id="usuariosProyecto" title="Usuarios del Proyecto" >
							<option value="">Seleccione un Usuario</option>
							</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">Descripción:</label>
					<div class="col-sm-6">
						<textarea rows="3" class="form-control"  name="descripcion" id="descripcion"
							title="Descripción">
						</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">Estado:</label>
					<div class="col-sm-6">
						<select class="form-control input-sm" id="estado" title="Estado">
							<option value="1">Activo</option>
							<option value="0">Inactivo</option>
						</select>
					</div>
					
				</div>
				<div align="right">
			        <button type="button" class="btn btn-primary" id="edit_asoc">Editar</button>
			        <button type="reset" class="btn btn-default">Limpiar</button>
     			</div>
      </form>
      
      </div>
      
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
  <script type="text/javascript">
  $(document).ready(function(){

	
		/**
		 * Definir reglas para los campos
		 */
		$('#frm_asoArtefactoPro').validate({
			rules:{
				"proyecto":{
					required: true
				},
				"artefacto":{
				      required: true
				},
				"tipoArtefacto":{
				      required: true
				},
				"descripcion":{
					maxlength: 255
				}
			},
	        messages: {
	        	artefacto: "Por favor ingrese el nombre",
	        	proyecto:  "Por favor seleccione un proyecto",
	            tipoArtefacto: "Por favor seleccione el tipo de artefacto",
	            descripcion: {
	    			  maxlength: "Este campo tiene un límite de {0} caracteres"
	    			}
	        }
		});

		//Evento Actualizar
		$("#edit_asoc").on("click", function() {

			if($("#frm_asoArtefactoPro").valid()){
				fn_modificarArtefacto();
			}

		});

		$("button:reset").on("click", function() {
			clearFormValidate();
			$("#frm_asoArtefactoPro #descripcion").text("");

		});
		
	});
  </script>