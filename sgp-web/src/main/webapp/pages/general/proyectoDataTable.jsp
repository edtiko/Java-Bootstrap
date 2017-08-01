<%@ taglib prefix="pmz" tagdir="/WEB-INF/tags"%>
<pmz:datatables datasource="/proyectos/getProyectos" id="proyectos" dataUrlAsoc="proyecto">
				<pmz:column label="lblAccion" field="id" />
				<pmz:column label="lblConsecutivo" field="id" />
				<pmz:column label="lblProyecto" field="nombre" />
				<pmz:column label="lblEmpresa" field="empresa.nombre" />
				<pmz:column label="lblEstado" field="indActivo" />
			</pmz:datatables>
