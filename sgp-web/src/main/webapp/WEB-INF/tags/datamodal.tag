<%@ tag language="java"%>
<%@ attribute name="id" required="true"%>
<%@ attribute name="datasource" required="true" rtexprvalue="true"%>
<%@ attribute name="data" required="true" rtexprvalue="true"%>
<%@ attribute name="actionColumn" required="false"
	type="java.lang.Integer"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:if test="${actionColumn == null}">
	<c:set var="actionColumn" value="0" />
</c:if>
<c:set var="org_languagetool_tags_table_outputmode" value="TABLE"
	scope="request" />
<div id="wrap">
	<div class="container table-responsive" style="max-width:600px">
		<table class="table table-hover table-bordered table-condensed" id="${id}" style="font-size: 12px" width="600">
			<thead>
				<tr class="text-center"><jsp:doBody /></tr>
			</thead>
			<tbody></tbody>
			<tfoot>
				<tr><jsp:doBody /></tr>
			</tfoot>
		</table>
	</div>
</div>
<c:set var="org_languagetool_tags_table_outputmode" value="SCRIPT"
	scope="request" />
<c:set var="org_languagetool_tags_table_firstcolumn" value="TRUE"
	scope="request" />
<script type="text/javascript">
$(document).ready(function() {
 oModal = $('#${id}').dataTable( {
	    "iDisplayLength": 5,
	    "aLengthMenu": [5, 10, 15, 25],
		"sAjaxSource": "<spring:url value="${datasource}" />",
		"bProcessing": true,
		"bServerSide": true,
		"sPaginationType": "bs_full",
		"fnRowCallback": function( nRow, aData, iDisplayIndex ) {
			
			<c:if test="${data == 'hallazgo'}">
			$('td:eq(0)', nRow).html("<a href='/sgp/hallazgos/editar/"+aData.id+"' title='Gestionar Hallazgo'>"+aData.id+"</a>");
			</c:if>
			$('td:eq(${actionColumn})', nRow).addClass("col-sm-1");
			<c:if test="${data != 'artefacto' && data != 'hallazgo'}">
			$('td:eq(${actionColumn})', nRow).html('');
			</c:if>
			<c:if test="${data == 'empresa'}">
			$('td:eq(${actionColumn})', nRow).append( '<a href="javascript:fn_seleccionEmpresa('+aData.id+')" ><i class="glyphicon glyphicon-download" data-toggle="tooltip" title="Seleccionar Empresa" ></i></a>&nbsp;' );
			</c:if>

			<c:if test="${data == 'proyecto'}">
			$('td:eq(${actionColumn})', nRow).append( '<a href="javascript:fn_seleccionProyecto('+aData.id+')" ><i class="glyphicon glyphicon-download" data-toggle="tooltip" title="Seleccionar Proyecto" ></i></a>&nbsp;' );
			</c:if>

			<c:if test="${data == 'usuario'}">
			$('td:eq(${actionColumn})', nRow).append( '<a href="javascript:fn_seleccionUsuario('+aData.id+')" ><i class="glyphicon glyphicon-download" data-toggle="tooltip" title="Seleccionar Usuario" ></i></a>&nbsp;' );
			</c:if>
	
		},
		"oLanguage": {
		    "sProcessing":     "Procesando...",
		    "sLengthMenu":     "Mostrar _MENU_ registros",
		    "sZeroRecords":    "No se encontraron resultados",
		    "sEmptyTable":     "Ningún dato disponible en esta tabla",
		    "sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
		    "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
		    "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
		    "sInfoPostFix":    "",
		    "sSearch":         "",
		    "sUrl":            "",
		    "sInfoThousands":  ",",
		    "sLoadingRecords": "Cargando...",
		    "oPaginate": {
		        "sFirst":    "Primero",
		        "sLast":     "Último",
		        "sNext":     "Siguiente",
		        "sPrevious": "Anterior"
		    },
		    "oAria": {
		        "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
		        "sSortDescending": ": Activar para ordenar la columna de manera descendente"
		    }
		},
		  "aaSorting": [[ 0, "asc" ]],
			"aoColumns":[
			             <jsp:doBody />
			            ]
		});
	
	  $('#${id}').each(function(){
	      var datatable = $(this);
	      // SEARCH - Add the placeholder for Search and Turn this into in-line form control
	      var search_input = datatable.closest('.dataTables_wrapper').find('div[id$=_filter] input');
	      search_input.attr('placeholder', 'Buscar');
	      search_input.addClass('form-control input-sm');
	      // LENGTH - Inline-Form control
	      var length_sel = datatable.closest('.dataTables_wrapper').find('div[id$=_length] select');
	      length_sel.addClass('form-control input-sm');
	});
});
</script>
