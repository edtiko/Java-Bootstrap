	<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h5 class="modal-title">
						<strong>Editar Observación</strong>
					</h5>
				</div>
				<div class="modal-body">
				<form class="form-horizontal" id="frm_edit_flujo" name="frm_edit_flujo">
				<div class="form-group">
				<label class="col-sm-1 control-label">Observación:</label>
				<div class="col-sm-12">
				<input type="hidden" id="idFlujoHallazgo" value="${flujo.id}"/>
			     <textarea rows="3" cols="" class="form-control input-sm" id="observacionFlujo" name="observacionFlujo">${flujo.observacion}</textarea>
				</div>
				</div>
				</form>
				</div>
				<div class="modal-footer">
					
					<button type="button" class="btn btn-primary" id="edit_observacion">Editar</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->