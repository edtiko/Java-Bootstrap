<form class="form-horizontal">
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
    <td><p class="form-control-static">${tipoHallazgo.nombre}</p></td>
    <td class="col-sm-2"><label class="control-label">Empresa:</label></td>
    <td><p class="form-control-static">${empresa}</p></td>
    <td class="col-sm-2"><label class="control-label">Causa de Generación:</label></td>
    <td><p class="form-control-static">${causaGeneracion.nombre}</p></td>
    </tr>
      <tr>
    <td class="col-sm-2"><label class="control-label">Severidad:</label></td>
    <td><p class="form-control-static">${severidad.nombre}</p></td>
    <td class="col-sm-2"><label class="control-label">Prioridad:</label></td>
    <td><p class="form-control-static">${prioridad.nombre}</p></td>
    <td class="col-sm-2"><label class="control-label" >Causa de Anulación:</label></td>
    <td><p class="form-control-static" id="anulacion">${causaAnulacion.nombre}</p></td>
    </tr>
   </table>
    </form>