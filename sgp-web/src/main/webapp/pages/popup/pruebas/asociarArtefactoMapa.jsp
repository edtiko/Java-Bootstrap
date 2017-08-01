<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="modal-dialog" style="width: 700px">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h5 class="modal-title">
				<strong><spring:message code="mapa-title-popup" />:</strong> &nbsp;
				${nombreMapa}
			</h5>
		</div>

		<div class="modal-body">
			<input type='hidden' id='idMapa' name='idMapa' value="${idMapa}">
			<table class="table">
				<tr>
					<td><spring:message code="mapa-lbl-en-proyecto" /></td>
					<td colspan='2'><spring:message code="mapa-lbl-en-pruebas" /></td>
				</tr>
				<tr>
					<td>
						<div class="col-md-12">
							<select name="backlog[]" id="backlog" multiple
								class="form-control" size='8' style="width: 250px">
							</select>
						</div>
					</td>
					<td><input type="button" value=" << " onclick="dellAll();"
						class="btn btn-default btn-sm" /><br /> <input type="button"
						value="< " onclick="delIt();" class="btn btn-default btn-sm" /><br />
						<input type="button" value="> " onclick="addIt();"
						class="btn btn-default btn-sm" /><br /> <input type="button"
						value=">>" onclick="addAll();" class="btn btn-default btn-sm" /></td>
					<td>
						<div class="col-md-12">
							<select name="picklist[]" id="picklist" multiple
								class="form-control" size='8' style="width: 250px"></select>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="3"><button type="button" id="asociar_artefactos"
							onclick="asociarArtefactos()" class="btn btn-default">
							<spring:message code="mapa-buttom-asociar-artefactos" />
						</button></td>
				</tr>
			</table>
		</div>

	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->