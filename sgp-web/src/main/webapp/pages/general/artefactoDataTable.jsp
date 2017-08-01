<%@ taglib prefix="pmz" tagdir="/WEB-INF/tags"%>
<pmz:datatables datasource="/asociarArtefactoProyecto/getDataTable"
	id="artefactos" dataUrlAsoc="artefacto">
	<pmz:column label="lblAccion" field="id" />
	<pmz:column label="lblConsecutivo" field="id" />
	<pmz:column label="lblTipoArtefacto" field="tipoArtefacto.nombre" />
	<pmz:column label="lblNombre" field="nombre" />
	<pmz:column label="lblNombreUsuario" field="usuario.nombre"></pmz:column>
	<pmz:column label="lblProyecto" field="proyecto.nombre" />
	<pmz:column label="lblEstado" field="indActivo" />
</pmz:datatables>