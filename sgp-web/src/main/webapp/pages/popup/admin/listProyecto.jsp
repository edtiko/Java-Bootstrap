<%@ taglib prefix="pmz" tagdir="/WEB-INF/tags"%>
<div class="modal-dialog" style="width:700px">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h5 class="modal-title"><strong>Seleccionar Proyecto</strong></h5>
		</div>

		<div class="modal-body">
			<pmz:datamodal datasource="/proyectos/getProyectosModal" id="modalProyectos" data="proyecto" >
			<pmz:column label="lblAccion" field="id" />
		    <pmz:column label="lblConsecutivo" field="id" />
		    <pmz:column label="lblNombre" field="nombre" />
		    <pmz:column label="lblEmpresa" field="empresa.nombre" />
		    <pmz:column label="lblEstado" field="indActivo" width="7%" />
			</pmz:datamodal>
		</div>

	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->