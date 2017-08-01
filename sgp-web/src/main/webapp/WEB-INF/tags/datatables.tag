<%@ tag language="java"%>
<%@ attribute name="id" required="true"%>
<%@ attribute name="datasource" required="true" rtexprvalue="true"%>
<%@ attribute name="dataUrlShow" required="false" rtexprvalue="true"%>
<%@ attribute name="dataUrlAsoc" required="false" rtexprvalue="true"%>
<%@ attribute name="dataUrlDelete" required="false" rtexprvalue="true"%>
<%@ attribute name="dataUrlMap" required="false" rtexprvalue="true"%>
<%@ attribute name="actionColumn" required="false"
	type="java.lang.Integer"%>
<%@ attribute name="colorColumn" required="false"
	type="java.lang.String"%>
<%@ attribute name="disableSortColumns" required="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:if test="${actionColumn == null}">
	<c:set var="actionColumn" value="0" />
</c:if>

	<c:set var="colorColumn" value="4" />

<c:set var="org_languagetool_tags_table_outputmode" value="TABLE"
	scope="request" />
<div id="wrap">
	<div class="container table-responsive">
		<table class="table table-striped table-bordered table-condensed"
			id="${id}" style="font-size: 12px">
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
		"bAutoWidth": false , 
		"bDestroy": true,
		"bStateSave": true,
		"fnRowCallback": function( nRow, aData, iDisplayIndex ) {
			 <c:if test="${dataUrlAsoc == 'avance'}">
		if(!(aData.sumatoriaPruebasConstruidas == null))
		{
			$('tfoot').html('');
			htmlFoot = '<tr>';
			htmlFoot+= '<th rowspan="1" colspan="1">Totales</th>';
			htmlFoot+= '<th rowspan="1" colspan="1">'+aData.sumatoriaPruebasConstruidas+'</th>';
			htmlFoot+= '<th rowspan="1" colspan="1">'+aData.sumatoriaPruebasEjecutadas+'</th>';
			htmlFoot+= '<th rowspan="1" colspan="1">'+aData.sumatoriaPruebasSatisfactorias+'</th>';
			htmlFoot+= '<th rowspan="1" colspan="1">'+aData.sumatoriaPruebasInsatisfactorias+'</th>';
			htmlFoot+= '<th rowspan="1" colspan="1">'+aData.sumatoriaPruebasAnuladas+'</th>';
			htmlFoot+= '<th rowspan="1" colspan="1">'+aData.sumatoriaPruebasSinEjecutar+'</th>';		
			htmlFoot+= '<th rowspan="1" colspan="1">'+aData.sumatoriaPorcentajeAvance+'</th>';
			htmlFoot+= '<th rowspan="1" colspan="1">'+aData.sumatoriaPorcentajeIndicador+'</th>';
			htmlFoot+= '</tr>';
			$('tfoot').html(htmlFoot);
		}
		</c:if>
		    <c:if test="${dataUrlAsoc != 'avance'}">		
			$('td:eq(${actionColumn})', nRow).html(''); 
			$('td:eq(1)', nRow).addClass("col-sm-1"); 
			$('td:eq(0)', nRow).addClass("col-sm-1");
	    	</c:if>

		    <c:if test="${dataUrlAsoc == 'ejecutarPrueba'}">
		    	$('td:eq(${actionColumn})', nRow).append( '<a href="javascript: fn_ejecutarCaso('+aData.id+",'Si'"+')" ><i class="glyphicon glyphicon-thumbs-up" data-toggle="tooltip" title="Si Cumple"></i></a>&nbsp;' );
		    	$('td:eq(${actionColumn})', nRow).append( '<a href="javascript: fn_ejecutarCaso2('+aData.id+",'No','"+aData.cumple+"'"+')" ><i class="glyphicon glyphicon-thumbs-down" data-toggle="tooltip" title="No Cumple"></i></a>&nbsp;' );
		    	$('td:eq(${actionColumn})', nRow).append( '<a href="javascript: fn_ejecutarCaso('+aData.id+",'NA'"+')" ><i class="glyphicon glyphicon-ban-circle" data-toggle="tooltip" title="No Aplica"></i></a>&nbsp;' );
				$('td:eq(${actionColumn})', nRow).append( '<a href="javascript: fn_showHallazgos('+aData.id+')" ><i class="glyphicon glyphicon-tasks" data-toggle="tooltip" title="Ver Hallazgos"></i></a>&nbsp;' );
	    	</c:if>
	    
			<c:if test="${dataUrlAsoc != 'proyecto' && dataUrlAsoc != 'userProyecto' && dataUrlAsoc != 'mapaArtefacto'  && dataUrlAsoc != 'descargarMapa' && dataUrlAsoc != 'avance'}">
				$('td:eq(${actionColumn})', nRow).append( '<a href="javascript:fn_show('+aData.id+')" ><i class="glyphicon glyphicon-search" data-toggle="tooltip" title="Ver Detalle"></i></a>&nbsp;' );

			<c:if test="${dataUrlAsoc != 'ejecutarPrueba' && dataUrlAsoc != 'mapaArtefacto' && dataUrlAsoc != 'descargarMapa' && dataUrlAsoc != 'avance'}">
				$('td:eq(${actionColumn})', nRow).append( '<a href="javascript: fn_editar('+aData.id+')" ><i class="glyphicon glyphicon-pencil" data-toggle="tooltip" title="Editar Registro"></i></a>&nbsp;' );
			</c:if>
				
			</c:if>
			<c:if test="${ dataUrlAsoc == 'casoPrueba' || dataUrlAsoc == 'mapaArtefacto'}">
				$('td:eq(${actionColumn})', nRow).append( '<a href="javascript:javascript:void(0)" id="'+aData.id+'" class="eliminar"><i class="glyphicon glyphicon-trash" data-toggle="tooltip" title="Eliminar Registro"></i></a>&nbsp;' );
			</c:if>
			<c:if test="${dataUrlAsoc == 'userProyecto'}">
			$('td:eq(${actionColumn})', nRow).append( '<a href="javascript:javascript:void(0)" id="'+aData.id+'" class="eliminar" data-elemento="usuario"><i class="glyphicon glyphicon-trash" data-toggle="tooltip" title="Eliminar Registro"></i></a>&nbsp;' );
		    </c:if>
			<c:if test="${dataUrlAsoc == 'artefacto'}">
			$('td:eq(${actionColumn})', nRow).append( '<a href="javascript:javascript:void(0)" id="'+aData.id+'" class="eliminar" data-elemento="artefacto"><i class="glyphicon glyphicon-trash" data-toggle="tooltip" title="Eliminar Registro"></i></a>&nbsp;' );
		    </c:if>
			<c:if test="${dataUrlAsoc == 'proyecto'}">
				$('td:eq(${actionColumn})', nRow).append( '<a href="javascript:fn_showProyecto('+aData.id+')" ><i class="glyphicon glyphicon-search" data-toggle="tooltip" title="Ver Detalle"></i></a>&nbsp;' );
				$('td:eq(${actionColumn})', nRow).append( '<a href="javascript: fn_editarProyecto('+aData.id+')"><i class="glyphicon glyphicon-pencil" data-toggle="tooltip" title="Editar Proyecto"></i></a>&nbsp;' );
				$('td:eq(${actionColumn})', nRow).append( '<a href="javascript: fn_loadDivAsociarUsuario('+aData.id+')"><i class="glyphicon glyphicon-user" data-toggle="tooltip" title="Asociar Usuario"></i></a>&nbsp;' );
				$('td:eq(${actionColumn})', nRow).append( '<a href="javascript: fn_asociarArtefacto('+aData.id+')"><i class="glyphicon glyphicon-link" data-toggle="tooltip" title="Asociar Artefacto"></i></a>&nbsp;' );
		    </c:if>

		    <c:if test="${dataUrlAsoc == 'mapa'}">
				$('td:eq(${actionColumn})', nRow).append( '<a href="javascript: fn_asociarArtefacto('+aData.id+')"><i class="glyphicon glyphicon-link" data-toggle="tooltip" title="Asociar Artefacto"></i></a>&nbsp;' );
		    </c:if>
		    <c:if test="${not empty dataUrlMap}">
				$('td:eq(${actionColumn})', nRow).append( '<a href="javascript: fn_asociarCasoPrueba('+aData.id+')"><i class="glyphicon glyphicon-tasks" data-toggle="tooltip" title="Asociar Caso de Prueba"></i></a>&nbsp;' );
		    </c:if>
		    
		    <c:if test="${dataUrlAsoc == 'descargarMapa'}">
				$('td:eq(${actionColumn})', nRow).append( '<a href="/sgp/gestionarPrueba/descargarMapaExcel/'+aData.id+'"><i class="glyphicon glyphicon-download" data-toggle="tooltip" title="Descargar en Excel"></i></a>&nbsp;' );
	    	</c:if>   
	    	 <c:if test="${dataUrlAsoc == 'tipoSeveridades'}">
	    	
				$('td:eq(${colorColumn})',nRow).css('background-color',aData.color);
					
	    	</c:if>   
		},
		  "stateSaveCallback": function (settings, data) {
		        // save the filter settings without connecting it to a unique url
		        localStorage.setItem("dataTables_filterSettings", JSON.stringify(data));
		    },
		    "stateLoadCallback": function (settings) {
		        // read out the filter settings and apply
		        return JSON.parse(localStorage.getItem("dataTables_filterSettings"));
		    },
		"oLanguage": {
		    "sProcessing":     "Procesando..",
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
			            ],
			"aoColumnDefs" : [{'bSortable': false, 'aTargets': [${disableSortColumns}]}]
		});
        <c:if test="${dataUrlAsoc != 'artefacto'  && dataUrlAsoc != 'proyecto' && dataUrlAsoc != 'userProyecto' && dataUrlAsoc != 'mapaArtefacto' && dataUrlAsoc != 'avance'}">  
          fn_columnFilter();
         </c:if>
         <c:if test="${dataUrlAsoc == 'artefacto'}">   
	      fn_columnFilterArtefacto();
	     </c:if>
	    <c:if test="${dataUrlAsoc == 'proyecto'}">
	      fn_columnFilterProyecto();
	    </c:if>
	    <c:if test="${dataUrlAsoc == 'userProyecto'}">
	      fn_columnFilterUsuario();
	    </c:if>
	    <c:if test="${dataUrlAsoc == 'mapaArtefacto'}">
	      fn_columnFilterAsociar();
	    </c:if>

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

	  /*
		 * Support functions to provide a little bit of 'user friendlyness' to the textboxes in 
		 * the footer
		 */
		$("tfoot input").each( function (i) {
			asInitVals[i] = this.value;
		} );
		
		$("tfoot input").focus( function () {
			if ( this.className == "search_init" )
			{
				this.className = "";
				this.value = "";
			}
		} );
		
		$("tfoot input").blur( function (i) {
			if ( this.value == "" )
			{
				this.className = "search_init";
				this.value = asInitVals[$("tfoot input").index(this)];
			}
		} );
});
</script>
