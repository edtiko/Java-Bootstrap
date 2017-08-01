<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="pmz" tagdir="/WEB-INF/tags"%>
<div class="modal-dialog" style="width: 700px">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
					<input type="hidden" id="idCasoPrueba" name="idCasoPrueba"
						value="${idCasoPrueba}" />
					<input type="hidden" id="usuarioActual" name="usuarioActual"
						value="${usuarioActual}" />
			<h5 class="modal-title">
				<strong><spring:message code="lblTituloHallazgosPorCaso" />:</strong>
				${idCasoPrueba}
			</h5>
		</div>
		<div class="modal-body">
			<form class="form-horizontal frm" method="post"
				id="frm_modalHallazgos" name="frm_modalHallazgos">

			
			</form>
			<pmz:datamodal datasource="/hallazgos/getHallazgos"
				id="modalHallazgos" data="hallazgo">
				<pmz:column label="lblConsecutivo" field="id" />
				<pmz:column label="lblTitulo" field="titulo" />
				<pmz:column label="lblSeveridad" field="severidad" />
				<pmz:column label="lblEstado" field="accion" />
				<pmz:column label="lblCasoDePrueba" field="casoPruebaId" />
				<pmz:column label="lblfechacreacion" field="fechaCreacionString" />
				<pmz:column label="lblUsuarioActual" field="usuarioAsignado" />
			</pmz:datamodal>
		</div>

	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->

<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/popup/pruebas/listHallazgos.js"></script> --%>