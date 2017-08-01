<%@ taglib prefix="pmz" tagdir="/WEB-INF/tags"%>
<div class="modal-dialog" style="width:700px">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h5 class="modal-title"><strong>Seleccionar Usuario</strong></h5>
		</div>
		<div class="modal-body">
			<pmz:datamodal datasource="/usuarios/obtenerUsuarios" id="modalUsuarios" data="usuario" >
		    <pmz:column label="lblAccion" field="id" />
		    <pmz:column label="lblConsecutivo" field="id" />
		    <pmz:column label="lblNombre" field="nombre" />
			</pmz:datamodal>
		</div>

	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->