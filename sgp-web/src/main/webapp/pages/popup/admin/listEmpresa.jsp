<%@ taglib prefix="pmz" tagdir="/WEB-INF/tags"%>
<div class="modal-dialog" style="width:700px">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h5 class="modal-title"><strong>Seleccionar Empresa</strong></h5>
		</div>
		<div class="modal-body">
			<pmz:datamodal datasource="/empresas/obtenerEmpresas" id="modalEmpresas" data="empresa">
			<pmz:column label="lblAccion" field="id"/>
				<pmz:column label="lblPais" field="pais.nombre" />
				<pmz:column label="lblDepartamento" field="departamento.nombre" />
				<pmz:column label="lblCiudad" field="ciudad.nombre" />
				<pmz:column label="lblEmpresa" field="nombre" />
			</pmz:datamodal>
		</div>

	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->