<%@ taglib prefix="pmz" tagdir="/WEB-INF/tags"%>
<pmz:datatables datasource="/proyectos/dataUsuarios" id="userProyecto" dataUrlAsoc="userProyecto">
				<pmz:column label="lblAccion" field="id" />
				<pmz:column label="lblConsecutivo" field="id" />
				<pmz:column label="lblUsuario" field="usuario.nombre" />
				<pmz:column label="lblCargoUsuario" field="usuario.cargo" />
				<pmz:column label="lblProyecto" field="proyecto.nombre" />
				<pmz:column label="lblEstado" field="indActivo" />
</pmz:datatables>