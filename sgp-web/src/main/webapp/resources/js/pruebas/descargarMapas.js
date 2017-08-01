/**
 * @author Jhon Alexander Filigrana Cardona
 * @date 14/04/08
 */
$(document).ready(function() {
	// se filtra para que no cargue datos al inicio
	oTable.fnFilter(0,10,true);
	
	validator = $('#frm_descargarMapasPruebas').validate({
		rules:{
			"proyecto":{
				required: true
			},
			"fechaTo":{
				date: true
			}
		},
			messages: {
				proyecto: "Por favor seleccione un proyecto",
				fechaTo:"El formato de la fecha de corte no es válido"
			}
	});
	
	//Ocultar columnas de la grilla:
	oTable.fnSetColumnVis(8, false);
	oTable.fnSetColumnVis(9, false);
	oTable.fnSetColumnVis(10, false);
	// Evento BuscaProyectos
	$("#buscarProyecto").on("click", function() {
		fn_getProyectos();
	});
	
	$("#proyecto").on("change", function() {
		var idProyecto = $(this).attr("data-id");
		$("#idProyecto").val(idProyecto);
		//console.log($("#idProyecto").val());
		//console.log(idProyecto);
		oTable.fnFilter(idProyecto,10,true);
		$("#fechaFrom").datepicker();
		$("#fechaTo").datepicker();
		$("#fechaFrom").attr("readonly",false);
		$("#fechaTo").attr("readonly",false);
		clearFormValidate();

	});
	
	$("#fechaFrom").on("change", function() {
	
		var valor = $("#fechaFrom").val();
		
			oTable.fnFilter(valor,8,true);
		
	});
	
	$("#fechaTo").on("change", function() {
		var valor = $("#fechaTo").val();
		isFechaValidad=validarFecha(valor,"Fecha Final");
		if(isFechaValidad==true){
		
		oTable.fnFilter(valor,9,true);
		}
	});
	
	$("#btnDescargarMapasZip").on("click",function(){
		if(!($("#frm_descargarMapasPruebas").valid())){
			$("#fechaFrom").css("border-color","#cccccc");
			$("#fechaTo").css("border-color","#cccccc");
			$("#lblfecha").css("color","#000");
		}
	});
	
	

});


function fn_columnFilter(){
	oTable.columnFilter({
		aoColumns: [  null,
		             { type: "number" },
				     { type: "text" },
				     { type: "text" },
				     null,
				     null,
				    null
				]

	});
}


