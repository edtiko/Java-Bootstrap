$(document).ready(function() {

     oProyecto = $('#proyectos').dataTable( {
		"sAjaxSource": "/sgp/proyectos/getProyectos",
		"bProcessing": true,
		"bServerSide": true,
		"sPaginationType": "bs_full",
		"bAutoWidth": false , 
		"bDestroy": true,
		"fnRowCallback": function( nRow, aData, iDisplayIndex ) {

			$('td:eq(0)', nRow).html(''); 
			$('td:eq(1)', nRow).addClass("col-sm-1"); 
			$('td:eq(0)', nRow).addClass("col-sm-1");

			
			$('td:eq(0)', nRow).append( '<a href="javascript:fn_showProyecto('+aData.id+')" ><i class="glyphicon glyphicon-search" data-toggle="tooltip" title="Ver Detalle"></i></a>&nbsp;' );
			$('td:eq(0)', nRow).append( '<a href="javascript: fn_editarProyecto('+aData.id+')"><i class="glyphicon glyphicon-pencil" data-toggle="tooltip" title="Editar Proyecto"></i></a>&nbsp;' );
			$('td:eq(0)', nRow).append( '<a href="javascript: fn_asociarArtefacto('+aData.id+')"><i class="glyphicon glyphicon-link" data-toggle="tooltip" title="Asociar Artefacto"></i></a>&nbsp;' );
			$('td:eq(0)', nRow).append( '<a href="javascript: fn_loadDivAsociarUsuario('+aData.id+')"><i class="glyphicon glyphicon-user" data-toggle="tooltip" title="Asociar Usuario"></i></a>&nbsp;' );
		    

		    
		    
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
			             
    {"mDataProp":"id",
     "sWidth"   :''}
      ,
    {"mDataProp":"id",
     "sWidth"   :''}
      ,
    
    {"mDataProp":"nombre",
     "sWidth"   :''}
      ,
    
    {"mDataProp":"empresa.nombre",
     "sWidth"   :''}
      ,
    
    {"mDataProp":"indActivo",
     "sWidth"   :''}
  

			
			            ]
		}).columnFilter({
			
			aoColumns: [  null,
			             { type: "number" },
					     { type: "text" },
					     { type: "text" },
					     { type: "select", values: [ "Activo", "Inactivo"]  }
					]

		});
	    
	    
	    

	  $('#proyectos').each(function(){
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
});
