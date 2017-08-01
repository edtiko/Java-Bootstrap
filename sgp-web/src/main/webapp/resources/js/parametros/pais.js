$(document).ready(function() {
	
	$("#edit_pais").hide();

	validator = $('#form_pais').validate({
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
	$("#sv_pais").on("click", function() {
		if($("#form_pais").valid()){
			fn_guardar();
		}
		
	});
	//Evento Actualizar
	$("#edit_pais").on("click", function() {
		
     if($("#form_pais").valid()){
     fn_actualizar();
     }
		
	});
	
	$("button:reset").on("click", function(){
		clearFormPais();
	
	});

});
function fn_guardar(){
	
		var dtopais = {
			nombre : $("#nombre").val(),
			descripcion : $("#descripcion").val(),
			numeroEstado : $("#estado option:selected").val()
		};
		$.ajax({
			url : "/sgp/paises/guardar",
			type : "post",
			data : JSON.stringify(dtopais),
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
		var dtopais = {
			id : $("#idPais").val(),
			nombre : $("#nombre").val(),
			descripcion : $("#descripcion").val(),
			numeroEstado : $("#estado").val()
		};
		$.ajax({
			url : "/sgp/paises/editar",
			type : "post",
			data : JSON.stringify(dtopais),
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
	$("#sv_pais").hide();
	$("#edit_pais").show();
	jQuery.getJSON("/sgp/paises/getPais", {
		id : id
	}, function(data) {
		 $('html, body').animate({scrollTop: '0px'}, 800);
		$("#idPais").val(data.id);
		$("#nombre").val(data.nombre);
		$("#descripcion").val(data.descripcion);
		$("#estado").val(data.numeroEstado);
	});

}
function fn_show(id){
	
	jQuery.getJSON("/sgp/paises/getPais", {
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
				     { type: "select", values: [ "Activo", "Inactivo"]  }
				]

	});
}

function clearFormPais(){
	clearFormValidate();
	$("#form_pais").reset();
	$("#edit_pais").hide();
	$("#sv_pais").show();
}
