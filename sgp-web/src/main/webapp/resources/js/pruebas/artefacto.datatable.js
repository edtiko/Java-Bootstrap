$(document).ready(function() {

	oArtefacto= $('#artefactos').dataTable( {
		"sAjaxSource": "/sgp/asociarArtefactoProyecto/getDataTable",
		"bProcessing": true,
		"bServerSide": true,
		"sPaginationType": "bs_full",
		"bAutoWidth": false , 
		"bDestroy": true,
		"fnInitComplete": function () {
	        this.fnAdjustColumnSizing(true);
	    },
		"fnRowCallback": function( nRow, aData, iDisplayIndex ) {

			$('td:eq(0)', nRow).html(''); 
			$('td:eq(1)', nRow).addClass("col-sm-1"); 
			$('td:eq(0)', nRow).addClass("col-sm-1");
			$('td:eq(0)', nRow).append( '<a href="javascript:fn_show('+aData.id+')" ><i class="glyphicon glyphicon-search" data-toggle="tooltip" title="Ver Detalle"></i></a>&nbsp;' );
			$('td:eq(0)', nRow).append( '<a href="javascript: fn_editar('+aData.id+')" ><i class="glyphicon glyphicon-pencil" data-toggle="tooltip" title="Editar Registro"></i></a>&nbsp;' );
			$('td:eq(0)', nRow).append( '<a href="javascript:javascript:void(0)" id="'+aData.id+'" class="eliminar" data-elemento="artefacto"><i class="glyphicon glyphicon-remove" data-toggle="tooltip" title="Eliminar Registro"></i></a>&nbsp;' );


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

		             {"mDataProp":"tipoArtefacto.nombre",
		            	 "sWidth"   :''}
		             ,

		             {"mDataProp":"nombre",
		            	 "sWidth"   :''}
		             ,

		             {"mDataProp":"proyecto.nombre",
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
					     { type: "text" },
					     { type: "select", values: [ "Activo", "Inactivo"]  }
					]

		});


	$('#artefactos').each(function(){
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
