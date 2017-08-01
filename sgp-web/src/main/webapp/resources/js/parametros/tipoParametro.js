$(document).ready(function() {
	$("#edit_param").hide();
	validator = $('#frm_tipoparametro').validate({
		
		//Definir reglas para los campos
		rules:{
			"nombre":{
				required: true,
				maxlength: 100
			},
			"descripcion":{
				maxlength: 1000
			}
		},
     // Specify the validation error messages
        messages: {
            nombre: {
            	required: "Por favor ingrese el nombre",
            	maxlength: "Este campo tiene un límite de {0} caracteres"
            	},
            descripcion: {
    			maxlength: "Este campo tiene un límite de {0} caracteres"
    			}
        },
	
		
	});
	//Evento Guardar
	$("#sv_param").on("click", function() {
		if($("#frm_tipoparametro").valid()){
		fn_guardar();
		}
		
	});
	//Evento Actualizar
	$("#edit_param").on("click", function() {
	     if($("#frm_tipoparametro").valid()){
	    	 fn_actualizar();
	     }	
	});
	
	$("button:reset").on("click", function(){
		clearFormTipoParametro();
		
	});

});
function fn_guardar(){

		var tipoparametro = {
			nombre : $("#nombre").val(),
			descripcion : $("#descripcion").val(),
			numeroEstado: $("#estado option:selected").val()
		};
		$.ajax({
			url : "/sgp/tipoParametros/guardar",
			type : "post",
			data : JSON.stringify(tipoparametro),
			contentType : "application/json; charset=utf-8",
			async: false

		}).done(function(data) {

			fn_resultado(data);

		}).fail(function(jqXHR, textStatus, errorThrown){
			fn_error(jqXHR, textStatus, errorThrown);
		});
}
function fn_actualizar(){

		var dtoparametro = {
			id : $("#id").val(),
			nombre : $("#nombre").val(),
			descripcion : $("#descripcion").val(),
			numeroEstado: $("#estado option:selected").val()
		};
		$.ajax({
			url : "/sgp/tipoParametros/editar",
			type : "post",
			data : JSON.stringify(dtoparametro),
			contentType : "application/json; charset=utf-8",
			async: false

		}).done(function(data) {
			$("#sv_param").show();
			$("#edit_param").hide();
			fn_resultado(data);

		}).fail(function(jqXHR, textStatus, errorThrown){
			fn_error(jqXHR, textStatus, errorThrown);
		});
}
function fn_editar(id) {
	
	clearFormValidate();
	$("#sv_param").hide();
	$("#edit_param").show();
	jQuery.getJSON("/sgp/tipoParametros/getTipoParametro", {
		id : id
	}, function(data) {
		 $('html, body').animate({scrollTop: '0px'}, 800);
		$("#id").val(data.id);
		$("#nombre").val(data.nombre);
		$("#descripcion").val(data.descripcion);
		$("#estado").val(data.numeroEstado);
	});

}
function fn_show(id){
	
	jQuery.getJSON("/sgp/tipoParametros/getTipoParametro", {
		id : id
	}, function(data) {
		
		$("#modal_show p").clear();
        $("#modal_show #consecutivo").text(id);
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
				     { type: "select", values: [ "Activo", "Inactivo"]  }
				]

	});
}
function clearFormTipoParametro(){
	clearFormValidate();
	$("#frm_tipoparametro").reset();
	$("#edit_param").hide();
	$("#sv_param").show();
}