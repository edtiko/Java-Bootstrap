<form class="form-horizontal" id="frmDatosHallazgo" name="frmDatosHallazgo" >
    <input type="hidden" id="idTipoHallazgo" name="idTipoHallazgo" value="${hallazgo.tipoHallazgo.id}"/>
       <input type="hidden" id="idTipoSeveridad" name="idTipoSeveridad" value="${hallazgo.severidad.id}"/>
              <input type="hidden" id="idCausaGeneracion" name="idCausaGeneracion" value="${hallazgo.causaGeneracion.id}"/>
<input type="hidden" id="idTipoPrioridad" name="idTipoPrioridad" value="${hallazgo.prioridad.id}"/>
	<table class="table table-bordered table-condensed datos">
		<tr>
			<td class="col-sm-2"><label class="control-label">Id del Hallazgo:</label></td>
			<td><p class="form-control-static">${hallazgo.id}</p></td>
			<td class="col-sm-2"><label class="control-label">Proyecto:</label></td>
			<td><p class="form-control-static">${proyecto.nombre}</p></td>
			<td class="col-sm-2"><label class="control-label">Estado:</label></td>
			<td><p class="form-control-static" id="estado">${estadoHallazgo}</p></td>
		</tr>
		<tr>
			<td class="col-sm-2"><label class="control-label">Mapa de Pruebas:</label></td>
			<td><p class="form-control-static">${mapaPrueba}</p></td>
			<td class="col-sm-2"><label class="control-label">Artefacto:</label></td>
			<td ><p class="form-control-static">${artefacto}</p></td>
			<td class="col-sm-2"><label class="control-label">Empresa:</label></td>
			<td><p class="form-control-static">${empresa}</p></td>
		</tr>

       <tr>
       <td class="col-sm-2">			
	<label class="control-label">Tipo de Hallazgo:</label>
					</td>
					<td colspan="2" class="col-sm-4 form-group">
						<select class="form-control input-sm" id="tipoHallazgo"
							name="tipoHallazgo" title="Tipo de Hallazgo">
						</select>
					</td>
       
		<td class="col-sm-2">
		<label class="control-label">Causa de Generación:</label>
		</td>
		<td colspan="2" class="col-sm-4 form-group">	
		<div id="divCausaGeneracion">
			<select class="form-control input-sm" id="causaGeneracion"
							name="causaGeneracion" title="Causa de Generación ">
						</select>
		</div>
		</td>
       </tr>
       <tr>
       <!-- Severidad Inicio -->   
       <td class="col-sm-2">
		<label class="control-label">Severidad:</label>
	   </td>
	   <td colspan="2" class="col-sm-4 form-group">
			<select id="severidad" name="severidad" class="form-control input-sm">
			</select>
		</td>
	<!-- Severidad FIn -->
		
		<td class="col-sm-2">			
	<label class="control-label">Causa de Anulación:</label>
					</td>
					<td colspan="2" class="col-sm-4">
						<select class="form-control input-sm" id="causaAnula"
							name="causaAnula" title="Causa de Anulación">
							<option value=""></option>
						</select>
					</td>

	</tr>
	<tr>
	
 
	
	   <!-- Prioridad Inicio -->
       <td class="col-sm-2">
		<label class="control-label">Prioridad:</label>
		</td>
		<td colspan="2" class="col-sm-4 form-group">		
						<select class="form-control input-sm" id="prioridad"
							name="prioridad" title="Prioridad">
						</select>
		</td>
		       <!-- Prioridad Fin -->
	
	<td class="col-sm-2">
	<label class="control-label">Título:</label>
	</td>
	<td colspan="2" class="form-group">
	<input type="text" class="form-control input-sm" id="titulo" name="titulo" value="${hallazgo.titulo}"/>
	</td>
	</tr>
	<tr>
	<td colspan="6">
	<div align="center">
	<button type="button" class="btn btn-default" id="mod_hallazgo">Editar</button>
	</div>
	</td>
	</tr>
		</table>
</form>