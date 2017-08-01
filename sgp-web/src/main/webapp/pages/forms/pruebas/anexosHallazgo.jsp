 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <form id="frm_anexo">
 <table class="table table-hover table-bordered table-condensed"  style="font-size: 12px">
      <thead>
				<tr>
				<th>Subir Archivo:</th>
				<td>
				<div class="form-group">
				<div class="col-sm-8">
				<input type="file" id="anexo" name="anexo" class="form-control input-sm">
				</div>
				
				<div class="col-sm-2">
				<button  type="button" class="btn btn-default btn-sm" id="adjuntar_anexo" data-loading-text="Cargando...">Adjuntar</button>
				</div>
				</div>
				</td>
				<th>Fecha</th>
				<th>Usuario</th>
				<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="anexo" items="${anexos}">
				<tr>
				<th>Adjunto</th>
				<td>${anexo.ruta}</td>
				<td>${anexo.fechaCreacionString}</td>
				<td>${anexo.usuarioCreacion}</td>
				<td>
				<a href='/sgp/hallazgos/getAnexo/${anexo.id}' ><i class="glyphicon glyphicon-download" title="Descargar"></i></a>
				<c:if test="${anexo.usuarioActual == anexo.usuarioCreacion}">
					<a href="javascript:void(0)" class="eliminar" id="${anexo.id}"><i class="glyphicon glyphicon-trash" title="Eliminar"></i></a>
				</c:if>
				</td>
				</tr>
			</c:forEach>
			</tbody>
      </table>
      </form>