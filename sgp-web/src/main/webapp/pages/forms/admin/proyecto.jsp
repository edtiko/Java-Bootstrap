<%@ include file="/pages/templates/layout.jsp"%>
<html>
<head>
<title><spring:message code="proyecto-titulo" /></title>
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
			<spring:message code="proyecto-titulo" />
			</strong>
			</h5>
			</legend>
			<form class="form-horizontal frm" id="frm_proyecto" name="frm_proyecto">
			<input type="hidden" id="listProyectos" value="0">
			<input type="hidden" id="id" >
				<div class="form-group">
					<label class="col-sm-2 control-label"><spring:message code="lblNombreProyecto" />:</label>
					<div class="col-sm-4">
						<input type="text" class="form-control input-sm" name="nombre"
							id="nombre" title="Nombre" placeholder="Este campo es requerido"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><spring:message code="lblDescripcion" />:</label>
					<div class="col-sm-4">
						<textarea rows="3" class="form-control"  name="descripcion" id="descripcion" title="Descripción"></textarea>
					</div>
				</div>
	<!-- empieza el buscar empresa -->	
				<div class="form-group" data-empresa>
					<label class="col-sm-2 control-label"><spring:message code="lblEmpresa"/>:</label>
					<div class="col-sm-4">
						<div class="input-group">
							<input type="text" id="empresa" name="empresa"class="form-control input-sm" placeholder="Seleccione una Empresa" title="Empresa" readonly>
							<div class="input-group-btn btn-group-sm">
								<button type="button" class="btn btn-default btn-group-sm"
									id="buscarEmpresa">Buscar</button>
							</div>
						</div>
					</div>
				</div>
		<!-- Termina el buscar empresa -->			
				<div class="form-group">
					<label class="col-sm-2 control-label"><spring:message code="lblEstado"/>:</label>
					<div class="col-sm-4">
						<select class="form-control input-sm" id="estado" title="Estado">
							<option value="1">Activo</option>
							<option value="0">Inactivo</option>
						</select>
					</div>
				</div>
                </form>
				<div class="form-actions col-sm-offset-2 col-sm-5">
					<button type="button" class="btn btn-default" id="sv_param" ><spring:message code="lblGuardar" /></button>
					<button type="button" class="btn btn-default" id="edit_param"><spring:message code="lblModificar" /></button>
					<button type="reset" class="btn btn-default"><spring:message code="lblLimpiar" /></button>
					
				</div>

			
		</fieldset>
	</div>
 <br />
 <div class="wrap panel ">
 <div class="container panel-default">
 <!-- Nav tabs -->
<ul class="nav nav-tabs" id="tabs">
  <li class="active"><a href="#tabProyectos" data-toggle="tab">Proyectos</a></li>
  <li><a href="#tabArtefactos" data-toggle="tab" >Proyecto Artefactos</a></li>
  <li><a href="#tabUsuarios" data-toggle="tab">Proyecto Usuarios</a></li>
</ul>

<!-- Tab panes -->
	<div class="tab-content panel-body">
		<div class="tab-pane active" id="tabProyectos">
		<div id="wrap">
	<div class="container table-responsive">
		<table class="table table-striped table-bordered table-condensed"
			id="proyectos" style="font-size: 12px">
			<thead>
				<tr>
				<th><spring:message code="lblAccion" /></th>
				<th><spring:message code="lblConsecutivo" /></th>
				<th><spring:message code="lblProyecto" /></th>
				<th><spring:message code="lblEmpresa" /></th>
				<th><spring:message code="lblEstado" /></th>
				</tr>
			</thead>
			<tbody></tbody>
			<tfoot>
				<tr>
				<th><spring:message code="lblAccion" /></th>
				<th><spring:message code="lblConsecutivo" /></th>
				<th><spring:message code="lblProyecto" /></th>
				<th><spring:message code="lblEmpresa" /></th>
				<th><spring:message code="lblEstado" /></th>
				</tr>
			</tfoot>
		</table>
	</div>
</div>
		</div>
		<div class="tab-pane" id="tabArtefactos">
		<div id="wrap">
	<div class="container table-responsive">
		<table class="table table-striped table-bordered table-condensed"
			id="artefactos" style="font-size: 12px">
			<thead>
				<tr>
				<th><spring:message code="lblAccion" /></th>
				<th><spring:message code="lblConsecutivo" /></th>
				<th><spring:message code="lblTipoArtefacto" /></th>
				<th><spring:message code="lblNombre" /></th>
				<th><spring:message code="lblProyecto" /></th>
				<th><spring:message code="lblEstado" /></th>
				</tr>
			</thead>
			<tbody></tbody>
			<tfoot>
				<tr>
				<th><spring:message code="lblAccion" /></th>
				<th><spring:message code="lblConsecutivo" /></th>
				<th><spring:message code="lblTipoArtefacto" /></th>
				<th><spring:message code="lblNombre" /></th>
				<th><spring:message code="lblProyecto" /></th>
				<th><spring:message code="lblEstado" /></th>
				</tr>
			</tfoot>
		</table>
	</div>
</div>
		</div>
		<div class="tab-pane container" id="tabUsuarios">
		<div id="wrap">
	<div class="container table-responsive">
		<table class="table table-striped table-bordered table-condensed"
			id="userProyecto" style="font-size: 12px">
			<thead>
				<tr>
				<th><spring:message code="lblAccion" /></th>
				<th><spring:message code="lblConsecutivo" /></th>
				<th><spring:message code="lblUsuario" /></th>
				<th><spring:message code="lblCargoUsuario" /></th>
				<th><spring:message code="lblProyecto" /></th>
				<th><spring:message code="lblEstado" /></th>
				</tr>
			</thead>
			<tbody></tbody>
			<tfoot>
				<tr>
				<th><spring:message code="lblAccion" /></th>
				<th><spring:message code="lblConsecutivo" /></th>
				<th><spring:message code="lblUsuario" /></th>
				<th><spring:message code="lblCargoUsuario" /></th>
				<th><spring:message code="lblProyecto" /></th>
				<th><spring:message code="lblEstado" /></th>
				</tr>
			</tfoot>
		</table>
	</div>
</div>
		</div>
	</div>

	<div class="modal fade" id="asociarUsuariosDiv"></div>
</div>
	<div class="modal fade" id="modalArtefacto"></div>
	<div class="modal fade" style="display: none" id="modal_proyecto">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h5 class="modal-title"><strong>Detalle Proyecto</strong></h5>
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
      </div></div>
    
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" style="display: none" id="modal_artefacto">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h5 class="modal-title"><strong>Detalle Artefacto</strong></h5>
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
					<label class="col-sm-4 control-label"><spring:message code="lblProyecto"/>:</label>
					<div class="col-sm-6">
						<p class="form-control-static" id="nomProyecto" ></p>
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
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/pruebas/proyecto.js"></script>
  	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/pruebas/asociarArtefactoProyecto.js"></script>
</body>

	<footer class="text-center">
		<p><spring:message code="footer" /></p>
	</footer>
</html>