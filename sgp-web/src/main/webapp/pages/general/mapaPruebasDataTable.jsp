<%@ taglib prefix="pmz" tagdir="/WEB-INF/tags"%>
<pmz:datatables datasource="/gestionarPrueba/getMapasPrueba" id="mapa" dataUrlAsoc="mapa" dataUrlMap="true">
		<pmz:column label="lblAccion" field="id"/>
		<pmz:column label="lblConsecutivo" field="id" width="5%" />
		<pmz:column label="lblNombre" field="nombre" />
		<pmz:column label="lblProyecto" field="proyecto.nombre" />
		<pmz:column label="lblTotalPruebasConstruidas" field="totalPruebasConstruidas" width="10%"/>
		<pmz:column label="lblTotalPruebasEjecutadas" field="totalPruebasEjecutadas"   width="10%" />
		<pmz:column label="lblEstado" field="indActivo" width="7%"/>
	</pmz:datatables>