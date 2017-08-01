<%@ include file="/pages/templates/layout.jsp"%>
<html>
<legend><spring:message code="lblCasoPrueba"/></legend>
<head>

<style type="text/css">

.bordeInput{

	background-color: #FFFFFF;
	border-width:0;
}
</style>

</head>
<body>

	<div class="container">
		<div class="alert alert-success" style="display: none;" id="alerta">
			<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>Mensaje!</strong>
			<span id="msg"></span>
		</div>

		<form class="form-inline frm" method="post" id="frm_EjecutarcasoPrueba" name="frm_EjecutarcasoPrueba">
			<input type="hidden" id="idEjecutarcasoPrueba" value="" />
					<table >
					
				
				
					<div class="form-group">
					<label class="control-label"><spring:message code="lblProyecto" /></label>
						<select id="proyecto" name="proyecto" class="form-control input-sm"></select>
					</div>				
				
					<div class="form-group">
						<label class="control-label"><spring:message code="lblMapaPruebas" /></label>
					
					<div >
						<select id="mapaPruebas" name="mapaPruebas" class="form-control input-sm"></select>
					</div>				
				</div>
					</table>
			</form>
				<br>
				<div class="form-group">			
					<pmz:datatables datasource="/casodepruebas/getcasosdeprueba"
						id="casosdeprueba" dataUrlAsoc="casoPrueba">	
						<pmz:column label="lblAccion" field="id" />
						<pmz:column label="lblId" field="id" />
						<pmz:column label="lblArtefacto" field="artefacto.nombre" />
						<pmz:column label="lblDescripcion" field="descripcion" />
						<pmz:column label="lblTipoprueba" field="tipoPrueba.nombre" />
						<pmz:column label="lblMapaPruebas" field="mapaPrueba.nombre" />
						<pmz:column label="lblResultado" field="resultado" />
						<pmz:column label="lblfechacreacion" field="fechaCreacionString" />
						<pmz:column label="lblUsuariocreacion" field="usuarioCreacion" />
					</pmz:datatables>
				</div>	
			</div>
			</div>
			</div>
			</fieldset>
	</form>
</div>
	
	
<div class="modal fade" style="display: none" id="modal_show">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title"><spring:message code="detallecasoprueba" /></h4>
      </div>
      <div class="modal-body text-left">
      			<form class="form-horizontal" role="form">
	        		<div class="form-group">
						<label class="col-sm-4 control-label"><spring:message code="lblId"/>:</label>
						<div class="col-sm-6">
						 <p class="form-control-static" id="consecutivo" ></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><spring:message code="lblDescripcion1"/>:</label>
						<div class="col-sm-6">
							<p class="form-control-static" id="descripcion" ></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><spring:message code="lblResultado"/>:</label>
						<div class="col-sm-6">
							<p class="form-control-static" id="resultado" ></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><spring:message code="lblfechacreacion"/>:</label>
						<div class="col-sm-6">
							<p class="form-control-static" id="fec_creacion" ></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><spring:message code="lblUsuariocreacion"/>:</label>
						<div class="col-sm-6">
							<p class="form-control-static" id="usu_crea" ></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><spring:message code="lblfechamodificacion"/>:</label>
						<div class="col-sm-6">
							<p class="form-control-static" id="fec_mod" ></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label"><spring:message code="lblusuariomodificacion"/>:</label>
						<div class="col-sm-6">
							<p class="form-control-static" id="usu_mod" ></p>
						</div>
					</div>
				</form>
      </div>
    
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	



<div class="modal fade" style="display: none" id="modal_File_show">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title"><spring:message code="tltCargueCasosPruebas" /></h4>
      </div>
      <div class="modal-body text-left">
      			<form class="form-horizontal" role="form">
	        		<div class="form-group">
					  	<label class="col-sm-4 control-label"><spring:message code="tltCargueCasosPruebas" /></label>
					  	<div class="col-sm-7">
							<input name="logo" id="logo" type="file" multiple accept='application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel'/><br />
							<br /><br />
								<div class="col-sm-7" id="result"></div>
						</div>
					</div>
					
				</form>
      </div>
    	<div class="modal-footer">
				 		<button type="button" class="btn btn-default" id="upload_action"><spring:message code="btnCargarArchivo" /></button>
		</div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<footer class="text-center">
	<p><spring:message code="footer" /></p>
	</footer>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/pruebas/reportarCasodePrueba.js"></script>
	
</body>
</html>
