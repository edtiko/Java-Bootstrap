$(document).ready(function() {
	
	$("#edit_ciudad").hide();
	// carga lista de utils.js
	getPaises();

	validator = $('#form_ciudad').validate({
		debug: true,
		//Definir reglas para los campos
		rules:{
			"nombre":{
				required:true,
				maxlength: 50
			},
			"pais":{
				required:true
			},
			"departamento":{
				required:true
			},
			
			"descripcion":{
				maxlength: 255
			}

		},

		// Specify the validation error messages
		messages: {
			pais: "Por favor seleccione un país",
			departamento: "Por favor seleccione un departamento",
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
	$("#sv_ciudad").on("click", function() {
		if($("#form_ciudad").valid()){
			fn_guardar();
		}

	});
	//Evento Actualizar
	$("#edit_ciudad").on("click", function() {

		if($("#form_ciudad").valid()){
			fn_actualizar();
		}

	});

	//Evento BuscaDepartamentos
	$("#pais").on("change", function() {
        
		getDepartamentos($(this).val());

	});

	
	$("button:reset").on("click", function(){
		clearFormCiudad();
	
	});

});
function fn_guardar(){

	var dtodepartamento = {
			id : $("#departamento option:selected").val(),
			nombre : $("#departamento option:selected").text()
		};

	var dtociudad = {
			nombre : $("#nombre").val(),
			descripcion : $("#descripcion").val(),
			numeroEstado : $("#estado option:selected").val(),
			departamento : dtodepartamento
	};
	$.ajax({
		url : "/sgp/ciudades/guardar",
		type : "post",
		data : JSON.stringify(dtociudad),
		contentType : "application/json; charset=utf-8",
		async: false

	}).done(function(data) {
		
		clearFormCiudad();
		fn_resultado(data);
		
	}).fail(function(jqXHR, textStatus, errorThrown){
		fn_error(jqXHR, textStatus, errorThrown);
	});
}
function fn_actualizar(){
	
	var dtodepartamento = {
			id : $("#departamento option:selected").val(),
			nombre : $.trim($("#departamento option:selected").text())
		};
	var dtociudad = {
			id : $("#idCiudad").val(),
			nombre : $("#nombre").val(),
			descripcion : $("#descripcion").val(),
			numeroEstado : $("#estado").val(),
			departamento : dtodepartamento
			
			
	};
	$.ajax({
		url : "/sgp/ciudades/editar",
		type : "post",
		data : JSON.stringify(dtociudad),
		contentType : "application/json; charset=utf-8",
		async: false

	}).done(function(data) {
		clearFormCiudad();
		fn_resultado(data);
		
	}).fail(function(jqXHR, textStatus, errorThrown){
		fn_error(jqXHR, textStatus, errorThrown);
	});
}
function fn_editar(id) {
	clearFormValidate();
	$("#edit_ciudad").show();
	$("#sv_ciudad").hide();
	jQuery.getJSON("/sgp/ciudades/getCiudad", {
		id : id
	}, function(data) {
		 $('html, body').animate({scrollTop: '0px'}, 800);
		$("#idCiudad").val(data.id);
		$("#pais").val(data.pais.id);
		getDepartamentos(data.pais.id);
		$("#departamento").attr("readonly",false);
		$("#departamento").val(data.departamento.id);
		$("#nombre").val(data.nombre);
		$("#descripcion").val(data.descripcion);
		$("#estado").val(data.numeroEstado);
	});
	

}
function fn_show(id){

	jQuery.getJSON("/sgp/ciudades/getCiudad", {
		id : id
	}, function(data) {
		
		$("#modal_show p").clear();
			$("#modal_show #consecutivo").text(data.id);
			$("#modal_show #nombre").text(data.nombre);
			$("#modal_show #estado").text(data.indActivo);
			$("#fec_creacion").text(data.fechaCreacionString);
			$("#usu_crea").text(data.usuarioCreacion);
			$("#fec_mod").text(data.fechaEditaString);
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
function clearFormCiudad(){
	clearFormValidate();
	$("#idCiudad").val("");
	$("#departamento").html("");
	$("#departamento").attr("readonly",true);
	$("#edit_ciudad").hide();
	$("#sv_ciudad").show();
}