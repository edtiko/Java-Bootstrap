<%@ taglib prefix="pmz" tagdir="/WEB-INF/tags"%>
<pmz:datatables datasource="/asociarArtefactoProyecto/getMapaArtefactos" id="mapaArtefacto" dataUrlAsoc="mapaArtefacto" >
<pmz:column label="lblAccion" field="id" />
<pmz:column label="lblConsecutivo" field="id" />
<pmz:column label="lblTipoArtefacto" field="tipoArtefacto.nombre" />
<pmz:column label="lblArtefactoNombre" field="artefacto.nombre" />
<pmz:column label="lblNombreUsuario" field="artefacto.usuario.nombre"></pmz:column>
<pmz:column label="lblMapaPrueba" field="mapaPrueba.nombre" />				
<pmz:column label="lblEstado" field="indActivo" width="7%"/>
</pmz:datatables>