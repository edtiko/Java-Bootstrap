<%@ include file="/pages/templates/layout.jsp" %>
<html>
<head>
<title><spring:message code ="lblGestionDepartamento" /></title>

<%-- 	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/util/jquery-1.11.0.min.js"></script> --%>
<%-- 	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/util/jquery-ui-1.10.4.custom.min.js"></script> --%>
<%-- 	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/jquery-ui-1.10.4.custom.min.css" /> --%>
	
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/util/pivot.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/pivot.css" />
	<style>
.ui-widget-header {
	border: 1px solid #ffffff;
/* 	background: #ffffff url("../resource/images/ui-bg_highlight-soft_15_cc0000_1x100.png") 50% 50% repeat-x; */
	color: #ffffff;
	font-weight: bold;
}


</style>

</head>
<body>
<div class="container">
		<div class="alert alert-success" style="display: none;" id="alerta">
			<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>Mensaje!</strong>
			<span id="msg"><ul></ul></span>
		</div>
			<fieldset>
				<legend>
				<h5>
					<strong><spring:message code="lblTituloDepartamento" /></strong>
				</h5>
			</legend>
				<form class="form-horizontal"  id="form_departamento"  name="form_departamento">
					<input type="hidden" id="idDepartamento" name="idDepartamento">
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message code ="lblNombre" />:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control input-sm" name="nombre"
								id="nombre" title="Nombre del Departamento" placeholder ="Este campo es requerido" />
						</div>
					</div>
					<div class="form-group">
					<input type="hidden" id="id">
						<label class="col-sm-2 control-label"><spring:message code ="lblDescripcionDepartamento" /></label>
						<div class="col-sm-4">
						<textarea class="form-control" rows="3" name="descripcion" title="Descripción" id="descripcion"></textarea>
						</div>
					</div>
							<div class="form-group">
								<label class="col-sm-2 control-label"><spring:message code ="lblNombrePaisDepartamento"/>:</label>
								<div class="col-sm-4">
									<select id="pais" title="País" name="pais" class="form-control input-sm">
									</select>
								</div>
				</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message code ="lblEstadoDepartamento" /></label>
						<div class="col-sm-4">
							<select class="form-control input-sm" name ="estado" id="estado" title="Estado">
							<option value="1">Activo</option>
							<option value="0">Inactivo</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-5">
							<button type="button" id="sv_departamento" class="btn btn-default"><spring:message code="lblGuardar" /></button>
							<button type="button" id="edit_departamento" class="btn btn-default"><spring:message code="lblModificar" /></button>
							<button type="reset" class="btn btn-default" ><spring:message code="lblLimpiar" /></button>
						</div>
					</div>
				</form>
				
			</fieldset>
	</div>
		<!-- Aqui empieza la DataTable -->
	<pmz:datatables datasource="/departamentos/getDepartamentos" id="departamentos">
		<pmz:column label="lblAccion" field="id" />
		<pmz:column label="lblConsecutivo" field="id" />
		<pmz:column label="lblNombrePaisDepartamento" field="pais.nombre" />
		<pmz:column label="lblNombreDepartamento" field="nombre" />
		<pmz:column label="lblEstado" field="indActivo" />
	</pmz:datatables>
	<!-- Aqui termina  la dataTable -->

<div id="tablaSeveridad"></div>
<div id="tablaSeveridad1"></div>
<!-- Modal -->
<div class="modal fade" style="display: none" id="modal_show">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h5 class="modal-title"><strong>Detalle Departamento</strong></h5>
      </div>
      <div class="modal-body text-left">
      <form class="form-horizontal" role="form">
        <div class="form-group">
					<label class="col-sm-4 control-label">Consecutivo:</label>
					<div class="col-sm-6">
					 <p class="form-control-static" id="consecutivo" ></p>
				
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label"><spring:message code="lblNombre" />:</label>
					<div class="col-sm-6">
						<p class="form-control-static" id="nombre" ></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label"><spring:message code="lblEstado" />:</label>
					<div class="col-sm-6">
						<p class="form-control-static" id="estado" ></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label"><spring:message code="lblfechaCreacion"/>:</label>
					<div class="col-sm-6">
						<p class="form-control-static" id="fec_creacion" ></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label"><spring:message code="lblUsuarioCreacion" />:</label>
					<div class="col-sm-6">
						<p class="form-control-static" id="usu_crea" ></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label"><spring:message code="lblFechaEdita" />:</label>
					<div class="col-sm-6">
						<p class="form-control-static" id="fec_mod" ></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label"><spring:message code="lblUsuarioEdita"/>:</label>
					<div class="col-sm-6">
						<p class="form-control-static" id="usu_mod" ></p>
					</div>
				</div>
				</form>
      </div>
    
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	<!-- /.modal -->
      <!-- Termina el body del popup -->
        
	
                <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/parametros/departamento.js"></script>
</body>
	<hr>
	<footer class="text-center">
	<p><spring:message code="footer" /></p>
	</footer>
</html>