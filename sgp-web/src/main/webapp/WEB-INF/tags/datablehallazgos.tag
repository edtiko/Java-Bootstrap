<%@ tag language="java"%>
<%@ attribute name="id" required="true"%>
<%@ attribute name="datasource" required="true" rtexprvalue="true"%>
<%@ attribute name="dataUrlAsoc" required="false" rtexprvalue="true"%>
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
	<div class="container table-responsive">
		<table class="table table-bordered table-condensed" id="${id}" style="font-size: 12px" >
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
var asInitVals = new Array();
$(document).ready(function() {
	oTable = $('#${id}').dataTable( {
		"sAjaxSource": "<spring:url value="${datasource}" />",
		"bProcessing": true,
		"bServerSide": true,
		"sPaginationType": "bs_full",
		"fnRowCallback": function( nRow, aData, iDisplayIndex ) {


			<c:if test="${dataUrlAsoc == 'bandejaHallazgos'}">
				
			 switch(aData.severidad){
				
	           case 'Fncnl':
	            //    $('td', nRow).css('background-color','#d9edf7');
	               break;
// 	            case 'Bloqueante':
// 	                $('td', nRow).addClass('danger');
// 	                break;
// 	            case 'Presentación':
// 	                $('td', nRow).addClass('success');
// 	                break;
	           default:
	           	$('td', nRow).css('background-color', aData.color );
               break;
	        }
			</c:if>

			<c:if test="${dataUrlAsoc == 'hallazgos'}">
			  switch(aData.accion){
			  
	            case 'Abierta':
	                $('td', nRow).addClass('danger');
	                break;
	            case 'Desarrollo':
	                $('td', nRow).addClass('success');
	                break;
	            case 'Devuelta':
	                $('td', nRow).css('background-color','#E6D1E4');
	                break;
	           
	            case 'Reproceso':
	                $('td', nRow).addClass('warning');
	                break;
	            case 'Cerrado':
	                $('td', nRow).addClass('active');
	                break;
	            case 'Anulado':
	                $('td', nRow).css('background-color','#F8FAB5');
	                break;
	            case 'Invalidado':
	                $('td', nRow).css('background-color','#d9edf7');
	                break;
	        }
			</c:if>
			
			$('td:eq(0)', nRow).addClass("col-sm-1");
			$('td:eq(0)', nRow).html("<a href='/sgp/hallazgos/editar/"+aData.id+"' title='Gestionar Hallazgo'>"+aData.id+"</a>");
	
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
	  fn_columnFilter();
	  $('#${id}').each(function(){
	      var datatable = $(this);
	      // SEARCH - Add the placeholder for Search and Turn this into in-line form control
	      var search_input = datatable.closest('.dataTables_wrapper').find('div[id$=_filter] input');
	      search_input.attr('placeholder', 'Buscar');
	      search_input.addClass('form-control input-sm');
	      // LENGTH - Inline-Form control
	      var searchs = datatable.closest('.dataTables_wrapper').find('.search_init');
	      searchs.addClass('form-control input-min');

	      var length_sel = datatable.closest('.dataTables_wrapper').find('select');
	      length_sel.addClass('form-control input-min');
	});

	  $("tfoot input").each( function (i) {
			asInitVals[i] = this.value;
		} );


});
</script>
