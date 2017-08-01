$(document).ready(function(){
	getTiposHallazgo();
	getPintaTabla();
	fn_cargar();

	// Evento BuscaProyectos
	$("#buscarProyecto").on("click", function() {
		fn_getProyectos();
	});

	// Evento Busca Usuarios Asigna
	$("#buscarUsuario").on("click", function() {
		fn_getUsuarios();
	});
	
	// Busca en la grilla por proyecto
	$("#proyecto").on("change", function() {
		var nombreroyecto = $("#proyecto").val();
		oTable.fnFilter(nombreroyecto,8,true);
	});

	// Busca en la grilla por mapa de prueba
	$("#mapaPruebas").on("change", function() {
		
		var objMapa = $("#mapaPruebas option:selected");
		
		if ( objMapa.val() == "" ){
			oTable.fnFilter("",9,true);
		} else {
			oTable.fnFilter(objMapa.text(),9,true);
		}

	});

	// Busca en la grilla por causa de generación
	$("#causaGeneracion").on("change", function() {
		var causaGeneracion = $("#causaGeneracion").val();
		oTable.fnFilter(causaGeneracion,10,true);
	});
	
	$("#tipoHallazgo").on("change", function() {
		var tipoHallazgo = $("#tipoHallazgo").val();
		oTable.fnFilter(tipoHallazgo,1,true);
	});
	// Busca por usuario asigna
	$("#usuario").on("change", function() {
		var usuarioAsigna = $("#usuario").attr("data-id");
		oTable.fnFilter(usuarioAsigna,11,true);
	});
	
	$("button:reset").on("click", function(){
		$("#mapaPruebas").html("");
		$("#mapaPruebas").append("<option value=''>Seleccione un Mapa de Pruebas</option>");

		
		
		$("#causaGeneracion").html("");
		$("#causaGeneracion").append("<option value='0'>Seleccione una Causa de Generación</option>");
		
		oTable.fnResetAllFilters();
		oTable.fnFilter(1,15, true);
		oTable.fnFilter($("#userLogin").val(),14,true);
		fn_clearFilters();
	});
	
	$("#fechaFrom").on("change", function() {
		var valor = $("#fechaFrom").val();
		oTable.fnFilter(valor,12,true);
	});
	$("#fechaTo").on("change", function() {
		var valor = $("#fechaTo").val();
		oTable.fnFilter(valor,13,true);
	});
	$(document).on("change", "#tipoHallazgo", function() {
		getCausaGeneracion($(this).val());
		
	});
	
});

function fn_cargar() {
	 var indBandeja = 1;

	$("#fechaFrom").datepicker();
	$("#fechaTo").datepicker();
	oTable.fnFilter($("#userLogin").val(),14,true);
	oTable.fnFilter(indBandeja,15,true);
	//oculta algunas columnas de la grilla
	oTable.fnSetColumnVis( 9,  false);
	oTable.fnSetColumnVis( 10,  false);
	oTable.fnSetColumnVis( 11,  false);
	oTable.fnSetColumnVis( 12,  false);
	oTable.fnSetColumnVis( 13,  false);
	oTable.fnSetColumnVis( 14,  false);
	oTable.fnSetColumnVis( 15,  false);
}

function fn_columnFilter(){
	oTable.columnFilter({
		aoColumns: [ { type: "text" },
		             { type: "text" },
		             { type: "text" },
				     { type: "text" },
				     { type: "text" },
				     { type: "text" },
				     null,
				     { type: "text" },
				     { type: "text" }
				]

	});
}
function getPintaTabla(){
	$.ajax({
	    url: "/sgp/hallazgos/tipoSeveridadesColor",
	    type: "get",
	    dataType: "json",
	    async: false
		}).done(function(data){
			
			$("#tablas").html("");
			$("#tablas").append('<table class="table table-bordered table-condensed dataTable" id="color"><tr>');
			$.each(data, function(i,v){
				
				 $('#color').append('<td style="background-color:'+v.color+'" ><label class="control-label">'+v.severidad+'</label></td>'  );
						 
			
			});
			$('#color').append('</tr></table>');
		});
	
	

}
