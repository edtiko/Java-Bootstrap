<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="table-responsive">
	<div class="container col-md-10 col-md-offset-1">
		<fieldset>
			<div>
				<strong>Mensaje!</strong> <span>${mensaje}</span>
			</div>
			<c:if test="${not empty registrosLeidos}">
				<br />
				<table class="table table-bordered table-condensed"
					style="font-size: 12px">
					<thead>
						<th>Total Registros Válidos</th>
						<th>Total Registros con Errores</th>
						<th>Total Registros Leídos</th>
					</thead>
					<tbody>
						<tr>
							<td>${registrosExito}</td>
							<td>${registrosFallo}</td>
							<td>${registrosLeidos}</td>
						</tr>
					</tbody>
				</table>
			</c:if>
			<c:if test="${not empty lists}">
				<table class="table table-bordered table-condensed">
					<tr>
						<td class="col-sm-1"><label class="control-label">No.
								Línea</label></td>
						<td class="col-sm-5"><label class="control-label">Errores</label></td>
					</tr>
					<c:forEach var="listValue" items="${lists}">
						<tr>
							<td class="col-sm-1">${listValue.numeroRegistro}</td>
							<td class="col-sm-5"><c:forEach var="listError"
									items="${listValue.erroresCargue}">
								${listError}
								<br />
								</c:forEach></td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</fieldset>
	</div>
</div>
