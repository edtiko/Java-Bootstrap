<div class="modal-dialog" style="width: 700px">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h5 class="modal-title"><strong>Asociar Usuarios a Proyecto:</strong>
			<span  data-id="${proyecto.id}" id="proyecto">${proyecto.nombre}</span>
			</h5>
		</div>
		
		<div class="modal-body">
			<input type='hidden' id='idProyecto' name='idProyecto' value="${idProyecto}">
			<table class="table">
				<tr>
					<td>Disponibles</td>
					<td colspan='2'>Asignados</td>
				</tr>
				<tr>
					<td>
					<div class="col-md-12">
					<select name="backlog[]" id="backlog" multiple class="form-control" size='8' style="width: 250px"> </select>
					</div>
					</td>
					<td><input type="button" value=" << " onclick="dellAll();" class="btn btn-default btn-sm" /><br /> 
						<input type="button" value="< " onclick="delIt();" class="btn btn-default btn-sm" /><br />
						<input type="button" value="> " onclick="addIt();" class="btn btn-default btn-sm" /><br /> 
						<input type="button" value=">>" onclick="addAll();" class="btn btn-default btn-sm" /></td>
					<td>
					<div class="col-md-12">
					<select name="picklist[]" id="picklist" multiple class="form-control" size='8' style="width: 250px"></select>
					</div>
					</td>
				</tr>
				<tr>
					<td colspan="3"><button type="button" id="asociar_usuarios" onclick="asociarUsuariosProyecto()" class="btn btn-default"> Asociar Usuarios</button></td>
				</tr>
			</table>
		</div>

	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->
<script>
$(document).ready(function(){
   var idProyecto = $("#idProyecto").val();
	cargarUsuariosAsociados(idProyecto);
	cargarListadoBackLog(idProyecto);
});
</script>