$(document).ready(function(){
	$("#btnModificar").hide();
	
	/**
	 * Definir reglas para los campos
	 */
	validator =  $('#formUsuario').validate({
		debug: true,
		rules:{
			"nombre":{
			      required: true,
			      maxlength: 255
			},
			"cargo":{
			      required: true,
			      maxlength: 255
			},
			"empresa":{
			      required: true,
			      maxlength: 255
			},
			"correo":{
			      required: true,
			      email: true,
			      maxlength: 255
			},
			"telefono":{
		    	number: true,
		    	maxlength: 50
		    },
			"login_id":{
			      required: true,
			      maxlength: 255
			},
			"estado":{
			      required: true
			}
		},
        messages: {
            nombre: {
            	required: "Por favor ingrese el nombre",
            	maxlength: "Este campo tiene un límite de {0} caracteres"
            	},
            cargo: {
            	required: "Por favor ingrese el cargo",
            	maxlength: "Este campo tiene un límite de {0} caracteres"
            		},
            empresa: {
            	required: "Por favor ingrese la empresa",
            	maxlength: "Este campo tiene un límite de {0} caracteres"	
                   },
            correo: {
            	required: "Por favor ingrese el correo", 
            	email: "Correo inválido",
            	maxlength: "Este campo tiene un límite de {0} caracteres"	
            		},
            telefono:{
                    number: "Por favor ingrese un valor numérico",
                    maxlength: "Este campo tiene un límite de {0} caracteres" 
                    },
            login_id: {
            	required: "Por favor ingrese el login o usuario para ingreso al sistema",
            	maxlength: "Este campo tiene un límite de {0} caracteres"		
            }
        }
	});
	
	/**
	 * Eventos JQuery
	 */
	$('#btnGuardar').click(function(){
		if($("#formUsuario").valid()){
			$().guardarUsuario();
		}
	});
	
	$('#btnModificar').click(function(){
		if($("#formUsuario").valid()){
			$().modificarUsuario();
		}
	});
	
	//limpia el formulario
	$("button:reset").on("click", function(){
		clearFormUsuario();
	});
	
	
});

/**
 * Funciones JQuery
 */
$.fn.construirUsuarioJson = function(){

	  var idUsuario = $("#idUsuario").val();
	  
	  var usuarioDto = 
	  	{ id: idUsuario,
		  nombre: $("#nombre").val(), 
		  empresa: $("#empresa").val(), 
		  cargo: $("#cargo").val(), 
		  login: $("#login_id").val(),
		  correo: $("#correo").val(),
		  telefono: $("#telefono").val(),
		  numeroEstado: $("#indActivo option:selected").val()
	  	};  
	  
	  return usuarioDto;
};


$.fn.guardarUsuario = function(){
	
	var usuarioDto = $().construirUsuarioJson();
	
	$.ajax({
		  url: "/sgp/usuarios/guardar",
		  type: "POST",
		  data: JSON.stringify(usuarioDto),
		  contentType: "application/json; charset=utf-8",
		  async: false
		}).done(function(data) {
			if(data.status != 'FAIL'){
				clearFormUsuario();
			}	
			fn_resultado2(data);
		}).fail(function(jqXHR, textStatus, errorThrown){
			fn_error(jqXHR, textStatus, errorThrown);
		});
};


$.fn.modificarUsuario = function(){
	
	var usuarioDto = $().construirUsuarioJson();
	
	$.ajax({
		  url: "/sgp/usuarios/actualizar",
		  type: "POST",
		  data: JSON.stringify(usuarioDto),
		  contentType: "application/json; charset=utf-8",
		  async: false
		}).done(function(data) {
			if(data.status != 'FAIL'){
				clearFormUsuario();
			}	
			fn_resultado2(data);
		}).fail(function(jqXHR, textStatus, errorThrown){
			fn_error(jqXHR, textStatus, errorThrown);
		});
};


function fn_editar(id) {
	clearFormValidate();
	$("#btnModificar").show();
	$("#btnGuardar").hide();
	jQuery.getJSON("/sgp/usuarios/obtenerUsuario", {
		id : id
	}, function(data) {
		 $('html, body').animate({scrollTop: '0px'}, 800);
		$("#idUsuario").val(data.id);
		$("#nombre").val(data.nombre);
		$("#empresa").val(data.empresa);
		$("#cargo").val(data.cargo);
		$("#correo").val(data.correo);
		$("#telefono").val(data.telefono);
		$("#login_id").val(data.login);
		$("#indActivo").val(data.numeroEstado);
	});
}

function fn_show(id) {
	
	jQuery.getJSON("/sgp/usuarios/obtenerUsuario", {
		id : id
	}, function(data) {
		
		$("#modal_show_usuario p").clear();
		$("#consReg").text(data.id);
		$("#nombreReg").text(data.nombre);
		$("#estadoReg").text(data.indActivo);
		$("#fechaCreaReg").text(data.fechaCreacionString);
		$("#fechaEditaReg").text(data.fechaEditaString);
		$("#usuarioCreaReg").text(data.usuarioCreacion);
		$("#usuarioEditaReg").text(data.usuarioEdita);		
		
	});
	
	 $("#modal_show_usuario").modal();
}

function fn_columnFilter(){
	oTable.columnFilter({
		aoColumns: [  null,
		             { type: "number" },
				     { type: "text" },
				     { type: "text" },
				     { type: "text" },
				     { type: "text" },
				     { type: "text" },
				     { type: "text" },
				     { type: "select", values: [ "Activo", "Inactivo"]  }
				]

	});
}
function clearFormUsuario(){
	clearFormValidate();
	$("#idUsuario").val("");
	$("#formUsuario").reset();
	$("#btnModificar").hide();
	$("#btnGuardar").show();
}

