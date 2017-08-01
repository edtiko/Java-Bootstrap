<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
						<input type="hidden" id="valor" value="${id}"/>
						<input type="hidden" id="elemento" value="${elemento}"/>
					<h5 class="modal-title">
						<strong>Confirmación de Eliminar</strong>
					</h5>
				</div>
				<div class="modal-body">
			   <p>
			   ¿Esta seguro de eliminar este registro?
			   </p>
				</div>
				<div class="modal-footer">
					<button type="button" id="si" class="btn btn-primary">Si</button>
					<button type="button" id="no" class="btn btn-default" data-dismiss="modal">No</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->