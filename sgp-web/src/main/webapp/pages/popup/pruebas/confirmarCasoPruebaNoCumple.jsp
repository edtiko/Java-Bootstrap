<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h5 class="modal-title">
				<strong>Confirmar caso de prueba no cumple</strong>
			</h5>
		</div>
		<div class="modal-body">
			<p>¿Desea definir el caso de prueba como no cumple?</p>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-primary" id="check_aceptar">Aceptar</button>
			<button type="button" class="btn btn-default" data-dismiss="modal"
				id="check_cancelar">Cancelar</button>
		</div>
	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->
<script type="text/javascript">
	$(document).ready(function() {

		//Evento aceptar modal dialog confirmacion estado caso prueba no cumple
		$("#check_aceptar").on("click", function() {
			//console.log("#check_aceptar  on click");
			$("#modal_check").modal('hide');
			fn_ejecutarCaso2(idCaso,nuevoCumple,ultimoCumple);
		});
	});
</script>