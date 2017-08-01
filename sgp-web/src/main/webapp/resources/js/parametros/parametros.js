$(document).ready(function() {
	$("#edit_param").hide();
	// carga lista de utils.js
	getTiposParametros();
    
	validator = $('#frm_parametro').validate({
		
		//Definir reglas para los campos
		rules:{
			"tipoParametro":{
				required: true
			},
			"nombre":{
				required: true,
				maxlength: 255
			},
			"valor":{
				required: true,
				maxlength: 1000
			},
			"descripcion":{
				maxlength: 255
			}
		},
     // Specify the validation error messages
        messages: {
            nombre: {
            	required: "Por favor ingrese el nombre",
            	maxlength: "Este campo tiene un límite de {0} caracteres"
            	},
            tipoParametro: "Por favor seleccione un tipo de parámetro",
            valor: {
            	required: "Por favor ingrese el valor",
            	maxlength: "Este campo tiene un límite de {0} caracteres"
            	},
            descripcion: {
  			    maxlength: "Este campo tiene un límite de {0} caracteres"
  			}
        },
	
		
	});
	//Evento Guardar
	$("#sv_param").on("click", function(){
		if($("#frm_parametro").valid()){
		fn_guardar();
		}
		
	});
	//Evento Actualizar
	$("#edit_param").on("click", function() {
		
     if($("#frm_parametro").valid()){
     fn_actualizar();
     }
		
	});
	
	$("button:reset").on("click", function(){
		clearFormParametro();
		
	});

});
function fn_guardar(){

	var dtotipo = {
			id : $("#tipoParametro option:selected").val(),
			nombre : $("#tipoParametro option:selected").text()
		};
		var dtoparametro = {
			nombre : $("#nombre").val(),
			descripcion : $("#descripcion").val(),
			valor : $("#valor").val(),
			tipoParametro : dtotipo,
			numeroEstado: $("#estado option:selected").val()
		};
		$.ajax({
			url : "/sgp/parametros/guardar",
			type : "post",
			data : JSON.stringify(dtoparametro),
			contentType : "application/json; charset=utf-8",
			async: false

		}).done(function(data) {
			clearFormParametro();
			fn_resultado(data);
		}).fail(function(jqXHR, textStatus, errorThrown){
			fn_error(jqXHR, textStatus, errorThrown);
		});
}
function fn_actualizar(){

	var dtotipo = {
			id : $("#tipoParametro option:selected").val(),
			nombre : $("#tipoParametro option:selected").text()
		};
		var dtoparametro = {
			id : $("#idParametro").val(),
			nombre : $("#nombre").val(),
			descripcion : $("#descripcion").val(),
			valor : $("#valor").val(),
			tipoParametro : dtotipo,
			numeroEstado: $("#estado option:selected").val()
		};
		$.ajax({
			url : "/sgp/parametros/editar",
			type : "post",
			data : JSON.stringify(dtoparametro),
			contentType : "application/json; charset=utf-8",
			async: false

		}).done(function(data) {

			clearFormParametro();
			fn_resultado(data);

		}).fail(function(jqXHR, textStatus, errorThrown){
			fn_error(jqXHR, textStatus, errorThrown);
		});
}
function fn_editar(id) {
	clearFormValidate();
	$("#sv_param").hide();
	$("#edit_param").show();
	jQuery.getJSON("/sgp/parametros/getParametro", {
		id : id
	}, function(data) {
		
		 $('html, body').animate({scrollTop: '0px'}, 800);
		$("#idParametro").val(data.id);
		$("#tipoParametro").val(data.tipoParametro.id);
		$("#nombre").val(data.nombre);
		$("#valor").val(data.valor);
		$("#descripcion").val(data.descripcion);
		$("#estado").val(data.numeroEstado);
	});

}
function fn_show(id){
	
	jQuery.getJSON("/sgp/parametros/getParametro", {
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
				     { type: "select", values: [ "Activo", "Inactivo"]  }
				]

	});
}

function clearFormParametro(){
	clearFormValidate();
	$("#frm_parametro").reset();
	$("#edit_param").hide();
	$("#sv_param").show();
}
