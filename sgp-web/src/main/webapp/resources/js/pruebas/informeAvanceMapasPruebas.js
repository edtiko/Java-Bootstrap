$(document).ready(function(){
	cargar();
	validator = $('#formAvanceMapa').validate({
		rules:{
			"proyecto":{
				required: true
			}
		},
			messages: {
				proyecto: "Por favor seleccione un proyecto"
				
			}
		
	});
	
//	oTable.dataTable({
//		"aoColumnDefs" : [
//			{'bSortable': false, 'aTargets': [11]}
//		]
//	});
	
	// Evento BuscaProyectos
	$("#buscarProyecto").on("click", function() {
		fn_getProyectos();
	});

	$("#proyecto").on("change", function() {
		var idProyecto = $(this).attr("data-id");
		$("#proyectoId").val(idProyecto);
		oTable.fnFilter(idProyecto,2,true);
		$("#fechaFrom").datepicker();
		$("#fechaTo").datepicker();
		$("#fechaFrom").attr("readonly",false);
		$("#fechaTo").attr("readonly",false);
		clearFormValidate();

	});
	
	
	$("#fechaFrom").on("change", function() {
		var valor = $("#fechaFrom").val();
		oTable.fnFilter(valor,11,true);
	});
	
	$("#fechaTo").on("change", function() {
		var valor = $("#fechaTo").val();
		oTable.fnFilter(valor,12,true);
	});
	
	$("#generarReporteAvance").on("click", function() {
		$('#formAvanceMapa').valid();
	});
	
});

function cargar(){
	oTable.fnSetColumnVis( 0,  false);
	oTable.fnSetColumnVis( 2,  false);
	oTable.fnSetColumnVis(11, false);
	oTable.fnSetColumnVis(12, false);
	oTable.fnFilter(-1,2,true);
}
function fn_generarReporte(){
	var proyectoId = $("#proyecto").attr("data-id");
	var fecFrom = $("#fechaFrom").val();
	var fecTo = $("#fechaTo").val();
	var parametros = proyectoId+"/"+fecFrom+"/"+fecTo;
	var url = "/sgp/reportes/descargarReporteAvanceMapas/"+parametros;
	console.log(url);
	window.location.href = url; 
}

function fn_columnFilter(){
	oTable.columnFilter({
		aoColumns: [
		            null,
				     { type: "text" },
				     { type: "text" },
				     null,
				     null,
				     null,				     
				     { type: "text" }
				]

	});
}
