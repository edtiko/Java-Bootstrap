<%@ include file="/pages/templates/layout.jsp"%>
<html>
<head>
<title><spring:message code="indicadores-titulo" /></title>
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
			<strong>
			<spring:message code="indicadores-titulo" />
			</strong>
			</h5>
			</legend>
			<form class="form-horizontal frm" id="frm_indicadores" name="frm_indicadores">
			<input type="hidden" id="idIndicador" >
				<div class="form-group">
					<label class="col-sm-2 control-label"><spring:message code="lblNombreProyecto" />:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control input-sm" name="nombre"
							id="nombre" title="Nombre" placeholder="Este campo es requerido"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><spring:message code="lblFase" />:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control input-sm" name="fase"
							id="fase" title="Fase" placeholder="Este campo es requerido"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><spring:message code="lblFormula" />:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control input-sm" name="formula"
							id="formula" title="Fórmula" placeholder="Este campo es requerido"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><spring:message code="lblPeriodicidad" />:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control input-sm" name="periodicidad"
							id="periodicidad" title="Periodicidad" placeholder="Este campo es requerido"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><spring:message code="lblValorMinimo" />:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control input-sm" name="valorMinimo"
							id="valorMinimo" title="Valor Mínimo" placeholder="Este campo es requerido"/>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label"><spring:message code="lblValorMaximo" />:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control input-sm" name="valorMaximo"

							id="valorMaximo" title="Valor Máximo"  placeholder="Este campo es requerido"/>
					</div>
				</div>
					<div class="form-group">
					<label class="col-sm-2 control-label">Estado:</label>
					<div class="col-sm-4">
						<select class="form-control input-sm" id="estado" name="estado" title="Estado">
							<option value="1">Activo</option>
							<option value="0">Inactivo</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-5">
						<button type="button" class="btn btn-default" id="sv_indicadores"><spring:message code="lblGuardar" /></button>
						<button type="button" class="btn btn-default" id="edit_indicadores"><spring:message code="lblModificar" /></button>
						<button type="reset" class="btn btn-default"><spring:message code="lblLimpiar" /></button>
					</div>
				</div>
				
			</form>


			
		</fieldset>
	</div>
<!-- Aqui empieza la DataTable -->
	<pmz:datatables datasource="/indicadores/getIndicadores" id="indicadores">
		<pmz:column label="lblAccion" field="id" />
		<pmz:column label="lblConsecutivo" field="id" />
		<pmz:column label="lblNombre" field="nombre" />
		<pmz:column label="lblFormula" field="formula" />
		<pmz:column label="lblFase" field="fase" />
		<pmz:column label="lblPeriodicidad" field="periodicidad" />
		<pmz:column label="lblValorMinimo" field="valorMinimo" />
		<pmz:column label="lblValorMaximo" field="valorMaximo" />
		<pmz:column label="lblEstado" field="indActivo" />

	</pmz:datatables>
	<hr />
	<!-- Aqui termina  la dataTable -->


<!--inicio modal -->

<!-- Modal -->
	<div class="modal fade" style="display: none" id="modal_show">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h5 class="modal-title"><strong><spring:message code="lblPopupDetalleIndicadores" /></strong></h5>
      </div>
      <div class="modal-body text-left">
      <form class="form-horizontal" role="form">
        <div class="form-group">
					<label class="col-sm-4 control-label"><spring:message code="lblConsecutivoTitle" />:</label>
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
			
			
				</div>
				</form>
      </div>
</div><!-- /.modal -->
<!-- Fin Modal -->


<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/util/validarCampo.js"></script>
		<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/parametros/indicadores.js"></script>
<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/util/jquery.mask.js"></script>	
		<script type="text/javascript">
           $(function(){
                //Para escribir solo letras
                $('#valorMaximo').validCampo('1234567890.-');
                $('#valorMinimo').validCampo('1234567890.-');
            });
        </script> 
</body>

	<footer class="text-center">
		<p><spring:message code="footer" /></p>
	</footer>
</html>