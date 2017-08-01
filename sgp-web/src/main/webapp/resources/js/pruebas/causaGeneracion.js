$(document).ready(function() {
	
	$("#edit_causageneracion").hide();
	// carga lista de utils.js
	getTiposHallazgo();
	validator = $('#form_causageneracion').validate({
		//Definir reglas para los campos
		rules:{
			"nombre":{
				required:true,
				maxlength: 50
			},
			"pais":{
				required:true
			},
			"descripcion":{
				maxlength: 255
			},
			"tipoHallazgo":{
				required:true
			}
			
		},
        messages: {
            nombre: {
            	required:"Por favor ingrese el nombre",
  			  maxlength: "Este campo tiene un límite de {0} caracteres"
  			},
            pais: "Por favor seleccione un país",
            descripcion: {
    			  maxlength: "Este campo tiene un límite de {0} caracteres"
    			},
    		tipoHallazgo: "Por favor seleccione el tipo de hallazgo",
                
        },
	
		
	});

	//Evento Guardar
	$("#sv_causageneracion").on("click", function() {
		if($("#form_causageneracion").valid()){
		fn_guardar();
		}
		
	});
	//Evento Actualizar
	$("#edit_causageneracion").on("click", function() {
		
     if($("#form_causageneracion").valid()){
     fn_actualizar();
     }
		
	});
	
	$("button:reset").on("click", function(){
		clearFormCausaGeneracion();
	
	});
	$(document).on("change", "#tipoHallazgo", function() {
		getTieneCausaGeneracion($(this).val());
		
		
	});
});
function fn_guardar(){
	var dtotipohallazgo = {
			id : $("#tipoHallazgo option:selected").val(),
			nombre : $("#tipoHallazgo option:selected").text()
		};
	
		var dtocausageneracion = {
			nombre : $("#nombre").val(),
			descripcion : $("#descripcion").val(),
			numeroEstado : $("#estado option:selected").val(),
			tipoHallazgo: dtotipohallazgo
		};
		$.ajax({
			url : "/sgp/causaGeneracion/guardar",
			type : "post",
			data : JSON.stringify(dtocausageneracion),
			contentType : "application/json; charset=utf-8",
			async: false

		}).done(function(data) {
			fn_resultado(data);
			clearFormCausaGeneracion();
			
		}).fail(function(jqXHR, textStatus, errorThrown){
			fn_error(jqXHR, textStatus, errorThrown);
		});
		}

function fn_actualizar(){
	var dtotipohallazgo = {
			id : $("#tipoHallazgo option:selected").val(),
			nombre :  $.trim($("#tipoHallazgo option:selected").text())
		};
		var dtocausageneracion = {
			id : $("#idCausaGeneracion").val(),
			nombre : $("#nombre").val(),
			descripcion : $("#descripcion").val(),
			numeroEstado : $("#estado").val(),
			tipoHallazgo: dtotipohallazgo
		};
		$.ajax({
			url : "/sgp/causaGeneracion/editar",
			type : "post",
			data : JSON.stringify(dtocausageneracion),
			contentType : "application/json; charset=utf-8",
			async: false

		}).done(function(data) {
			clearFormCausaGeneracion();
			fn_resultado(data);
		}).fail(function(jqXHR, textStatus, errorThrown){
			fn_error(jqXHR, textStatus, errorThrown);
		});
		
}
function fn_editar(id) {
	clearFormValidate();
	$("#sv_causageneracion").hide();
	$("#edit_causageneracion").show();
	jQuery.getJSON("/sgp/causaGeneracion/getCausaGeneracion", {
		id : id
	}, function(data) {
		
		$('html, body').animate({scrollTop: '0px'}, 800);
		$("#idCausaGeneracion").val(data.id);
		$("#tipoHallazgo").val(data.tipoHallazgo.id);
		$("#nombre").val(data.nombre);
		$("#descripcion").val(data.descripcion);
		$("#estado").val(data.numeroEstado);
	});

}
function fn_show(id){
	
	jQuery.getJSON("/sgp/causaGeneracion/getCausaGeneracion", {
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
				     { type: "select", values: [ "Activo", "Inactivo"]  }
				]

	});
}
function clearFormCausaGeneracion(){
	clearFormValidate();
	$("#form_causageneracion").reset();
	$("#edit_causageneracion").hide();
	$("#sv_causageneracion").show();
}