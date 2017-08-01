$(document).ready(function() {
	
	$("#edit_tipohallazgo").hide();

	validator = $('#form_tipoHallazgo').validate({
		debug: true,
		//Definir reglas para los campos
		rules:{
			"nombre":{
				required:true,
				maxlength: 50
			},
			"descripcion":{
				maxlength: 255
			}
			
		},
		
     // Specify the validation error messages
        messages: {
        	nombre: {
            	required:"Por favor ingrese el nombre",
  			  maxlength: "Este campo tiene un límite de {0} caracteres"
  			},
            descripcion: {
    			  maxlength: "Este campo tiene un límite de {0} caracteres"
    			}
        },
	
		
	});
	//Evento Guardar
	$("#sv_tipohallazgo").on("click", function() {
		if($("#form_tipoHallazgo").valid()){
			fn_guardar();
		}
		
	});
	//Evento Actualizar
	$("#edit_tipohallazgo").on("click", function() {
		
     if($("#form_tipoHallazgo").valid()){
     fn_actualizar();
     }
		
	});
	
	$("button:reset").on("click", function(){
		clearFormPais();
	
	});

});
function fn_guardar(){
	
		var dtotipohallazgo = {
			nombre : $("#nombre").val(),
			descripcion : $("#descripcion").val(),
			numeroEstado : $("#estado option:selected").val(),
			numeroEsPuntuado : $("#esPuntuado option:selected").val(),
			numeroTieneCausaGeneracion: $("#tieneCausaGeneracion option:selected").val()
		};
		$.ajax({
			url : "/sgp/tipoHallazgo/guardar",
			type : "post",
			data : JSON.stringify(dtotipohallazgo),
			contentType : "application/json; charset=utf-8",
			async: false

		}).done(function(data) {
			fn_resultado(data);
			clearFormPais();
			
		}).fail(function(jqXHR, textStatus, errorThrown){
			fn_error(jqXHR, textStatus, errorThrown);
		});
		
}
function fn_actualizar(){
		var dtotipohallazgo = {
			id : $("#idTipoHallazgo").val(),
			nombre : $("#nombre").val(),
			descripcion : $("#descripcion").val(),
			numeroEstado : $("#estado").val(),
			numeroEsPuntuado : $("#esPuntuado").val(),
			numeroTieneCausaGeneracion : $("#tieneCausaGeneracion").val() 

		};
		$.ajax({
			url : "/sgp/tipoHallazgo/editar",
			type : "post",
			data : JSON.stringify(dtotipohallazgo),
			contentType : "application/json; charset=utf-8",
			async: false

		}).done(function(data) {
			clearFormPais();
			fn_resultado(data);
		}).fail(function(jqXHR, textStatus, errorThrown){
			fn_error(jqXHR, textStatus, errorThrown);
		});
}
function fn_editar(id) {
	clearFormValidate();
	$("#sv_tipohallazgo").hide();
	$("#edit_tipohallazgo").show();
	jQuery.getJSON("/sgp/tipoHallazgo/getTipoHallazgo", {
		id : id
	}, function(data) {
		 $('html, body').animate({scrollTop: '0px'}, 800);
		$("#idTipoHallazgo").val(data.id);
		$("#nombre").val(data.nombre);
		$("#descripcion").val(data.descripcion);
		$("#estado").val(data.numeroEstado);
		$("#esPuntuado").val(data.numeroEsPuntuado);
		$("#tieneCausaGeneracion").val(data.numeroTieneCausaGeneracion);
		
	});

}
function fn_show(id){
	
	jQuery.getJSON("/sgp/tipoHallazgo/getTipoHallazgo", {
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
		$("#esPuntuado_mod").text(data.esPuntuado);
		$("#tieneCausa_mod").text(data.tieneCausaGeneracion);
	});
	
 $("#modal_show").modal();
}
function fn_columnFilter(){
	oTable.columnFilter({
		aoColumns: [  null,
		             { type: "number" },
				     { type: "text" },
				     
				     { type: "select", values: [ "No", "Sí"]  },
				     { type: "select", values: [ "No", "Sí"]  },
				     { type: "select", values: [ "Activo", "Inactivo"]  }
				]

	});
}

function clearFormPais(){
	clearFormValidate();
	$("#form_tipoHallazgo").reset();
	$("#edit_tipohallazgo").hide();
	$("#sv_tipohallazgo").show();
}
