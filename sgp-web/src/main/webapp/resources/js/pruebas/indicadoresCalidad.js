/**
 * 
 */

estadosHallazgoGarantias = []; 

$(document).ready(function() {

	
	// Evento BuscaProyectos
	$("#buscarProyecto").on("click", function() {
		fn_getProyectos();
	});

	$("#proyecto, #fechaFrom, #fechaTo, #causaGeneracionHallazgosGarantia").on("change", function() {
		$("#fechaFrom").datepicker();
		$("#fechaTo").datepicker();
		$("#fechaFrom").attr("readonly",false);
		$("#fechaTo").attr("readonly",false);
		var proyecto = $("#proyecto").attr("data-id");
		var fechaFrom = $("#fechaFrom").val();
		var fechaTo = $("#fechaTo").val();
		
		
		
		
		$("#indicadoresContent")
			.load("/sgp/indicadores/getIndicadoresCalidad",{idProyecto:proyecto, fechaFrom:fechaFrom, fechaTo:fechaTo,estadosHallazgoGarantias:estadosHallazgoGarantias},function(){
				getListasHallazgosPorRecurso();	
				getHallazgosPorGarantias();
			});
		
		
	});
	
	$(document).on("change","#estadoHallazgo, #causaGeneracion",function(){
		getHallazgosPorRecurso();
	
});
	$(document).on("change","#causaGeneracionHallazgosGarantia",function(){
		getHallazgosPorGarantias();
	
});
	
});



 function getListasHallazgosPorRecurso(){
	
	 $("#estadoHallazgo").getParametrosPorTipoParametro(8,function(){
		 $("#estadoHallazgo").append("<option value='0'>Ninguno</option>");
		 $("#estadoHallazgo").val([24, 25]);
	 });
	 //para hallazgo por garantia
//	 $("#estadoHallazgosGarantia").getParametrosPorTipoParametro(8,function(){
//		 $("#estadoHallazgosGarantia").append("<option value='0'>Ninguno</option>");
//		 $("#estadoHallazgosGarantia").val([0]);
//	 });
	 $("#causaGeneracionHallazgosGarantia").getCausaGeneracionPorTipoHallazgo(5,function(){
		 $("#causaGeneracionHallazgosGarantia").append("<option value='0'>Ninguno</option>");
		 $("#causaGeneracionHallazgosGarantia").val([16]);
	 });
	 
	 
	getCausaGeneracion(3);
    $("#causaGeneracion").val(9);
    getHallazgosPorRecurso();
 }
 
 function getHallazgosPorRecurso(){
		var proyecto = $("#proyecto").attr("data-id"),
	    fechaFrom = $("#fechaFrom").val(),
	    fechaTo = $("#fechaTo").val();
	
	var estados = []; 
	$('#estadoHallazgo :selected').each(function(i, selected){ 
		estados[i] = $(selected).val(); 
	});
	
	 var   causas = [];
	 $('#causaGeneracion :selected').each(function(i, selected){ 
		 causas[i] = $(selected).val(); 
		});
	 
	$("#hallazgosRecurso").load("/sgp/indicadores/getHallazgoPorRecurso", {idProyecto:proyecto, fechaFrom:fechaFrom, fechaTo:fechaTo, estados: JSON.stringify(estados), causas: JSON.stringify(causas) });
 
 }

/**
 * 
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @date 8/07/2014 18:40:29
 * @description
 */
function loadPageIndicadores() {
	var proyecto = $("#proyecto").attr("data-id");
	var fechaFrom = $("#fechaFrom").val();
	var fechaTo = $("#fechaTo").val();
	$("#indicadoresContent")
		.load("/sgp/indicadores/getIndicadoresCalidad",{idProyecto:proyecto, fechaFrom:fechaFrom, fechaTo:fechaTo});
}

/**
 * 
 */
function getHallazgosPorGarantias(){
	var proyecto = $("#proyecto").attr("data-id"),
    fechaFrom = $("#fechaFrom").val(),
    fechaTo = $("#fechaTo").val();

var causasGeneracion = []; 
$('#causaGeneracionHallazgosGarantia :selected').each(function(i, selected){ 
	causasGeneracion[i] = $(selected).text(); 
});


$("#hallazgosPorGarantias").load("/sgp/indicadores/getHallazgoPorGarantia", {idProyecto:proyecto, fechaFrom:fechaFrom, fechaTo:fechaTo, causasGeneracion: JSON.stringify(causasGeneracion)});

}