<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="table table-hover table-bordered table-condensed"  id="flujoHallazgo" style="font-size: 12px">
      <thead>
				<tr>
				<th>Descripción</th>
				<th>Estado</th>
				<th>Usuario</th>
				<th>Fecha</th>
				<th class="edit_hallazgo">Acciones</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="flujo" items="${flujos}">
				<tr>
				
				<td class="hallazgo-observacion">${flujo.observacion}</td>
				<td>${flujo.estadoHallazgo.nombre}</td>
				<td>${flujo.usuarioCreacion}</td>
				<td>${flujo.fechaCreacionString}</td>
				<td >
				<a href="javascript: fn_showEditarObservacion(${flujo.id})" class="edit_hallazgo" ><i class="glyphicon glyphicon-pencil" title="Editar Observación"></i></a>
				</td>
				</tr>
			</c:forEach>
			</tbody>
      </table>