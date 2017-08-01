<%@ include file="/pages/templates/layout.jsp" %>
<html>
	<head>
		<title>Gestionar Hallazgo</title>
		<style type="text/css">
		.datos td{
   text-align: left !important;
        }
       		
		</style>
	</head>
	<body>
    <div class="container col-md-10 col-md-offset-1">
    <input type="hidden" id="idProyecto" value="${proyecto.id}"/>
    <input type="hidden" id="idHallazgo" value="${hallazgo.id}"/>
    <input type="hidden" id="rolesUsuario" value='${roles}'/>
    <input type="hidden" id="rolesAdmin" value='${rolesAdmin}'/>
    <input type="hidden" id="rolesDesa" value='${rolesDesa}'/>
    <input type="hidden" id="idtipoHallazgo" value="${hallazgo.tipoHallazgo.id}"/>
    <input type="hidden" id="idseveridad" value="${hallazgo.severidad.id}"/>
    <input type="hidden" id="idcausaGenera" value="${hallazgo.causaGeneracion.id}"/>
    <input type="hidden" id="idprioridad" value="${hallazgo.prioridad.id}"/>
    <input type="hidden" id="idcausaAnula" value="${hallazgo.causaAnulacion.id}"/>
     <input type="hidden" id="manual" value="${manual}"/>
    <input type="hidden" id="ind_edit" value="true"/> 
    <input type="hidden" id="permiso_editar" value="false"/>
		<div class="alert alert-success" style="display: none;" id="alerta">
			<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>Mensaje!</strong>
			<span id="msg"><ul></ul></span>
		</div>
    <div class="panel panel-default ">
    <div class="panel-heading">
    
 <h5>
 <a data-toggle="collapse"  href="#collapseOne">
         <strong>Datos Básicos del Hallazgo</strong>
        </a>
 <a  style="display: none; float: right;" id="show_edit" href="javascript: cargarModificar()" title="Editar Hallazgo"><i  class="glyphicon glyphicon-edit"></i></a>

 </h5>
 
  </div>
   <div id="collapseOne" class="panel-collapse collapse in">
    <div class="panel-body" id="datos">
    <table class="table table-bordered table-condensed datos">
    <tr>
    <td class="col-sm-2"><label class="control-label">Id del Hallazgo:</label></td>
    <td><p class="form-control-static">${hallazgo.id}</p></td>
    <td class="col-sm-2"><label class="control-label">Proyecto:</label></td>
    <td ><p class="form-control-static">${proyecto.nombre}</p></td>
    <td class="col-sm-2"><label class="control-label">Estado:</label></td>
    <td><p class="form-control-static" id="estado">${estadoHallazgo}</p></td>
    </tr>
    <tr>
    <td class="col-sm-2"><label class="control-label">Mapa de Pruebas:</label></td>
    <td colspan="2"><p class="form-control-static">${mapaPrueba}</p></td>
    <td class="col-sm-2"><label class="control-label">Artefacto:</label></td>
    <td colspan="2" ><p class="form-control-static">${artefacto}</p></td>
    </tr>
     <tr>
    <td class="col-sm-2"><label class="control-label">Usuario Actual:</label></td>
    <td colspan="2"><p class="form-control-static">${usuario.nombre}</p></td>
    <td class="col-sm-2"><label class="control-label">Título:</label></td>
    <td colspan="2" ><p class="form-control-static">${hallazgo.titulo}</p></td>
    </tr>
     <tr>
    <td class="col-sm-2"><label class="control-label">Tipo de Hallazgo:</label></td>
    <td><p class="form-control-static">${hallazgo.tipoHallazgo.nombre}</p></td>
    <td class="col-sm-2"><label class="control-label">Empresa:</label></td>
    <td><p class="form-control-static">${empresa}</p></td>
    <td class="col-sm-2"><label class="control-label">Causa de Generación:</label></td>
    <td><p class="form-control-static">${hallazgo.causaGeneracion.nombre}</p></td>
    </tr>
      <tr>
    <td class="col-sm-2"><label class="control-label">Severidad:</label></td>
    <td><p class="form-control-static">${hallazgo.severidad.nombre}</p></td>
    <td class="col-sm-2"><label class="control-label">Prioridad:</label></td>
    <td><p class="form-control-static">${hallazgo.prioridad.nombre}</p></td>
    <td class="col-sm-2"><label class="control-label" >Causa de Anulación:</label></td>
    <td><p class="form-control-static" id="anulacion">${hallazgo.causaAnulacion.nombre}</p></td>
    </tr>
   </table>
  </div>
  </div>
</diV>
	<c:if test="${ocultarGestion == 'false'}">
<div class="panel panel-default" id="gestionDelHallazgo">
  <div class="panel-heading">
 <h5 ><strong>Gestión del Hallazgo</strong></h5>
  </div>
<div class="panel-body">
	<form class="form-horizontal" id="form_hallazgo">
		<div class="form-group">
			<label class="col-sm-3 control-label">Reasignar Hallazgo:</label>
				<div >&nbsp;&nbsp;&nbsp;
						<label> Si <input type="radio" name="radioHallazgos"
							id="checkSi">
						</label>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label> No <input type="radio" name="radioHallazgos"
							id="checkNo">
						</label>
					</div>
		</div>
	<div class="form-group" id="divEstadoHallazgo">
						<label class="col-sm-3 control-label">Enviar Este Hallazgo en el Estado:</label>
						<div class="col-lg-4">
							<select id="estadoHallazgo" name="estadoHallazgo" title="Estado"
								class="form-control input-sm">
								<option value="">Seleccione un Estado</option>
							</select>
						</div>
	</div>

			

	<div id="divUsuarioHallazgo" style="display: block" class="form-group">
		<label class="col-sm-3 control-label">Enviar Este Hallazgo al Usuario:</label>
		<div class="col-sm-4">
			<select id="asignar" name="asignar" title="Usuario"
						class="form-control input-sm">
				<option value="">Seleccione un Usuario</option>
			</select>
		</div>
	</div>

			<div id="divReasignaHallazgo" style="display: none"
				class="form-group">
				<label class="col-sm-3 control-label">Motivo de Reasignación</label>
				<div class="col-sm-4">
					<select id="motivoHallazgo" name="motivoHallazgo" title="Motivo"
						class="form-control input-sm">
						<option value="">Seleccione un Motivo</option>
					</select>
				</div>
			</div>
			<div class="form-group" style="display: none" id="divCausaAnula">
				<label class="col-sm-3 control-label">Causa de Anulación:</label>
				<div class="col-sm-4">
					<select id="causaAnula" name="causaAnula"
						class="form-control input-sm">
						<option value="">Seleccione una Causa de Anulación</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">La Observación es:</label>
				<div class="col-sm-4">
					<textarea class="form-control" rows="3" name="observacion"
						id="observacion" title="Observación"></textarea>
				</div>
			</div>
	</form>	
			<div class="form-actions col-sm-offset-3 col-sm-5">
					<button type="button" class="btn btn-default" id="edit_hallazgo">Actualizar</button>
				</div>
      </div>
      </div>
  </c:if>
      <div class="panel panel-default">
  <div class="panel-heading">
 <h5 ><strong>Detalle y Seguimiento del Hallazgo</strong></h5>
  </div>
  <div class="panel-body" id="detalle">      
      
       </div>
       </div>
       <div class="panel panel-default">
  <div class="panel-heading">
 <h5 ><strong>Anexos del Hallazgo</strong></h5>
  </div>
  <div class="panel-body" id="anexos">
            
       </div>
       </div>
    
         <footer class="text-center">
	<p><spring:message code="footer" /></p>
	</footer>
       </div>
       <div id="divConfirma" class="modal fade" style="display: none"></div>
     <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/pruebas/gestionarHallazgo.js"></script>
	</body>
</html>
