<%@ taglib prefix="pmz" tagdir="/WEB-INF/tags"%>
<div class="modal-dialog" style="width:700px; max-width: 800px">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <input type="hidden" id="proyectoFilter" value="${proyecto.nombre}"/>
        <h5 class="modal-title"><strong>Asociar Artefacto a Proyecto:</strong>
        <span  data-id="${proyecto.id}" id="proyecto">${proyecto.nombre}</span>
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
							<option value="">Seleccione un Tipo de Artefacto</option>
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
						<select class="form-control input-sm" name="usuariosProyecto"
							id="usuariosProyecto" title="Usuarios del proyecto" >
							<option value="">Seleccione un Usuario</option>
							</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">Estado:</label>
					<div class="col-sm-6">
						<select class="form-control input-sm" id="estado">
							<option value="1">Activo</option>
							<option value="0">Inactivo</option>
						</select>
					</div>
				</div>
        
       <div class="form-actions col-sm-offset-7 col-sm-5">
        <button type="button" class="btn btn-primary" id="sv_asoc">Guardar</button>
        <button type="reset" class="btn btn-default">Limpiar</button>
      </div>
      </form>
      </div>
      <br><br>

  <pmz:datamodal datasource="/asociarArtefactoProyecto/getDataTable" id="artefactos" data="artefacto">
	<pmz:column label="lblConsecutivo" field="id" />
	<pmz:column label="lblTipoArtefacto" field="tipoArtefacto.nombre" />
	<pmz:column label="lblNombre" field="nombre" />
	<pmz:column label="lblProyecto" field="proyecto.nombre" />
	<pmz:column label="lblNombreUsuario" field="usuario.nombre"></pmz:column>
	<pmz:column label="lblEstado" field="indActivo" width="10%" />
</pmz:datamodal>
     </div>
    </div><!-- /.modal-content -->

  <script type="text/javascript">
  $(document).ready(function(){
	  oModal.fnFilter($("#proyectoFilter").val(), 3 ,true);
	  getTiposArtefacto();
	  
	  idProyecto = $("#proyecto").attr("data-id");
	  if(idProyecto != "") {
		  loadUsuariosAsociados(idProyecto, "#usuariosProyecto");
	  }
	
	 oModal.fnSort( [ [0,'desc']] );
	
		/**
		 * Definir reglas para los campos
		 */
		$('#frm_asoArtefactoPro').validate({
			rules:{
				"proyecto":{
					required: true
				},
				"artefacto":{
				      required: true,
				      maxlength: 100
				},
				"tipoArtefacto":{
				      required: true
				}
			},
	        messages: {
	        	artefacto:{
		        	required: "Por favor ingrese el nombre",
                    maxlength: "Este campo tiene un límite de {0} caracteres"
		        	},
	        	proyecto:  "Por favor seleccione un proyecto",
	            tipoArtefacto: "Por favor seleccione el tipo"
	        }
		});

		//Evento Guardar
		$("#sv_asoc").on("click", function() {
			if($("#frm_asoArtefactoPro").valid()){
				fn_asociar();
			}

		});
		//Evento Actualizar
		$("#edit_asoc").on("click", function() {

			if($("#frm_asoArtefactoPro").valid()){
				fn_modificarArtefacto();
			}

		});

		
	});
  </script>