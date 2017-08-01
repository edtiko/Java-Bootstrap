<%@ include file="/pages/templates/layout.jsp"%>
<html>
<head>
<script
	src="<%=request.getContextPath()%>/resources/js/pruebas/mapaPrueba.js"></script>
<style type="text/css">
</style>
<title><spring:message code="mapa-titulo" /></title>
</head>
<body>
	<div class="container">
		<div class="alert alert-success" style="display: none;" id="alerta">
			<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>Mensaje!</strong>
			<span id="msg"><ul></ul></span>
		</div>
		<fieldset>
				<legend>
				<h5><strong><spring:message code="mapa-titulo" /> </strong></h5>
				</legend>
			<form class="form-horizontal frm" method="post"
				action='gestionarPrueba/guardarMapa' id='frm_mapaPruebas'>
				<input type="hidden" id="id" value="" />
				<!-- empieza el buscar Proyecto -->			
					<div class="form-group data-proyecto" >
					<label class="col-sm-2 control-label"><spring:message code="lblProyecto"/>:</label>
					<div class="col-sm-4">
						<div class="input-group">
							<input type="text" id="proyecto" name="proyecto" class="form-control input-sm" placeholder="Seleccione un Proyecto" title="Proyecto" readonly>
							<div class="input-group-btn btn-group-sm">
								<button type="button" class="btn btn-default btn-group-sm"
									id="buscarProyecto">Buscar</button>
							</div>
						</div>
					</div>
				</div>
		<!-- Termina el buscar proyecto -->
				<div class="form-group">
					<label class="col-sm-2 control-label"><spring:message code="lblNombre" />:</label>
						<div class="col-sm-4">
							<input type="text" class="form-control input-sm" name="nombre" id="nombre" placeHolder="Este campo es requerido" title="Nombre"/>
						</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><spring:message code="lblEstado" />:</label>
					<div class="col-sm-4">
						<select class="form-control input-sm" id="estado" name="estado" title="Estado">
							<option value="1">Activo</option>
							<option value="0">Inactivo</option>
						</select>
					</div>
				</div>
				<br />
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-5">
						<button type="button" id="sv_mapa"   class="btn btn-default"> <spring:message code="lblGuardar" /></button>
						<button type="button" id="edit_mapa" class="btn btn-default"><spring:message code="lblModificar" /></button>
						<button type="reset"  class="btn btn-default"><spring:message code="lblLimpiar" /></button>
					</div>
				</div>
			</form>
	</div>
	<br />
	 <div class="wrap panel ">
			 <div class="container panel-default">
				 <!-- Nav tabs -->
					<ul class="nav nav-tabs " id="tabs">
					  <li class="active"><a href="#tabMapaPruebas" data-toggle="tab">Mapas de Pruebas</a></li>
					  <li><a href="#tabArtefactosMapaPruebas" data-toggle="tab" >Artefactos Mapa de Pruebas</a></li>
					</ul>
					
					<!-- Tab panes -->
						<div class="tab-content panel-body">
							<div class="tab-pane active" id="tabMapaPruebas"></div>
							<div class="tab-pane" id="tabArtefactosMapaPruebas"></div>
						</div>
				</div>
	<div class="modal fade" id="asociarArtefactoDiv"></div>
	<div class="modal fade" style="display: none" id="modal_show">
    <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h5 class="modal-title"><strong>Detalle Mapa de Prueba</strong></h5>
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
					<label class="col-sm-4 control-label">Nombre:</label>
					<div class="col-sm-6">
						<p class="form-control-static" id="nombre" ></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Estado:</label>
					<div class="col-sm-6">
						<p class="form-control-static" id="estado" ></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Fecha Creación:</label>
					<div class="col-sm-6">
						<p class="form-control-static" id="fec_creacion" ></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Usuario Creación:</label>
					<div class="col-sm-6">
						<p class="form-control-static" id="usu_crea" ></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Fecha Modificación:</label>
					<div class="col-sm-6">
						<p class="form-control-static" id="fec_mod" ></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">Usuario Modificación:</label>
					<div class="col-sm-6">
						<p class="form-control-static" id="usu_mod" ></p>
					</div>
				</div>
				</form>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</div>

</body>
	<footer class="text-center">
		<p><spring:message code="footer" /></p>
	</footer>
</html>