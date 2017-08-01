$(document).ready(function() {
	
	$("#edit_indicadores").hide();

	validator = $('#frm_indicadores').validate({
		debug: true,
		//Definir reglas para los campos
		rules:{
			"nombre":{
				required:true,
				maxlength:100
			},
			"fase":{
				required:true,
				maxlength: 100
			},
			"formula":{
				required:true,
				maxlength: 255
			},
			"periodicidad":{
				required:true,
				maxlength: 100
			},
			"valorMinimo":{
				required:true,
				number: true
			},
			"valorMaximo":{
				required:true,
				number: true
			},
			
		},
		
     // Specify the validation error messages
        messages: {
        	nombre: {
            	required:"Por favor ingrese el nombre",
  			  maxlength: "Este campo tiene un límite de {0} caracteres"
  			},
  			fase: {
            	required:"Por favor ingrese la fase",
    			  maxlength: "Este campo tiene un límite de {0} caracteres"
    			},
    		formula: {
                	required:"Por favor ingrese la fórmula",
        			  maxlength: "Este campo tiene un límite de {0} caracteres"
        			},
        	periodicidad: {
                    	required:"Por favor ingrese la periodicidad",
            			  maxlength: "Este campo tiene un límite de {0} caracteres"
            			},
            valorMinimo: {
                        required:"Por favor ingrese el valor mínimo",
                        	number:"Este campo debe ser numérico"
                		},
             valorMaximo: {
                           required:"Por favor ingrese el valor máximo",
                        	   number:"Este campo debe ser numérico"
                        },
        },
	
		
	});
	//Evento Guardar
	$("#sv_indicadores").on("click", function() {
		if($("#frm_indicadores").valid()){
			fn_guardar();
		}
		
	});
	//Evento Actualizar
	$("#edit_indicadores").on("click", function() {
		
     if($("#frm_indicadores").valid()){
     fn_actualizar();
     }
		
	});
	
	$("button:reset").on("click", function(){
		clearFormIndicadores();
	
	});

});
function fn_guardar(){
	
		var dtoIndicador = {
				nombre : $("#nombre").val(),
				fase: $("#fase").val(),
				formula: $("#formula").val(),
				periodicidad: $("#periodicidad").val(),
				valorMinimo: $("#valorMinimo").val(),
				valorMaximo: $("#valorMaximo").val(),
				numeroEstado : $("#estado").val(),

		};
		$.ajax({
			url : "/sgp/indicadores/guardar",
			type : "post",
			data : JSON.stringify(dtoIndicador),
			contentType : "application/json; charset=utf-8",
			async: false

		}).done(function(data) {
			fn_resultado(data);
			clearFormIndicadores();
			
		}).fail(function(jqXHR, textStatus, errorThrown){
			fn_error(jqXHR, textStatus, errorThrown);
		});
		
}
function fn_actualizar(){
		var dtoIndicador = {
			id : $("#idIndicador").val(),
			nombre : $("#nombre").val(),
			fase: $("#fase").val(),
			formula: $("#formula").val(),
			periodicidad: $("#periodicidad").val(),
			valorMinimo: $("#valorMinimo").val(),
			valorMaximo: $("#valorMaximo").val(),
			numeroEstado : $("#estado").val()
			
		};
		$.ajax({
			url : "/sgp/indicadores/editar",
			type : "post",
			data : JSON.stringify(dtoIndicador),
			contentType : "application/json; charset=utf-8",
			async: false

		}).done(function(data) {
			clearFormIndicadores();
			fn_resultado(data);
		}).fail(function(jqXHR, textStatus, errorThrown){
			fn_error(jqXHR, textStatus, errorThrown);
		});
}
function fn_editar(id) {
	clearFormValidate();
	$("#sv_indicadores").hide();
	$("#edit_indicadores").show();
	jQuery.getJSON("/sgp/indicadores/getIndicador", {
		id : id
	}, function(data) {
		 $('html, body').animate({scrollTop: '0px'}, 800);
		$("#idIndicador").val(data.id);
		$("#nombre").val(data.nombre);
		$("#fase").val(data.fase);
		$("#formula").val(data.formula);
		$("#periodicidad").val(data.periodicidad);
		$("#valorMinimo").val(data.valorMinimo);
		$("#valorMedio").val(data.valorMedio);
		$("#valorMaximo").val(data.valorMaximo);
		$("#estado").val(data.numeroEstado);
		
	});

}
function fn_show(id){
	
	jQuery.getJSON("/sgp/indicadores/getIndicador", {
		id : id
	}, function(data) {
		
		$("#modal_show p").clear();
        $("#modal_show #consecutivo").text(data.id);
		$("#modal_show #nombre").text(data.nombre);	
		$("#modal_show #estado").text(data.indActivo);
		$("#fec_creacion").text(data.fechaCreacionString);
		$("#fec_mod").text(data.fechaEditaString);
		$("#usu_crea").text(data.usuarioCreacion);
		$("#usu_mod").text(data.usuarioEdita);
	});
	
 $("#modal_show").modal();
}
function fn_columnFilter(){
	oTable.columnFilter({
		aoColumns: [  null,
		             { type: "number" },
		             { type: "text" },
		             { type: "text" },
		             { type: "text" },
				     { type: "text" },
				     null,
null,

				     { type: "select", values: [ "Activo", "Inactivo"]  }
				]

	});
}

function clearFormIndicadores(){
	clearFormValidate();
	$("#frm_indicadores").reset();
	$("#edit_indicadores").hide();
	$("#sv_indicadores").show();
}
