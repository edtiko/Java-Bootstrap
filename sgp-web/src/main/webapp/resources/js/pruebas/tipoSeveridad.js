$(document).ready(function() {
	
	$("#edit_tipoSeveridad").hide();
	// carga lista de utils.js
	getTiposHallazgo();

	validator = $('#form_tipoSeveridad').validate({
		//Definir reglas para los campos
		rules:{
			"nombre":{
				required:true,
				maxlength: 50
			},
			"tipoHallazgo":{
				required:true
			},
			"descripcion":{
				maxlength: 255
			},
			"color":{
				required:true
			},
			
		},
        messages: {
            nombre: {
            	required:"Por favor ingrese el nombre",
  			  maxlength: "Este campo tiene un límite de {0} caracteres"
  			},
            pais: "Por favor seleccione un país",
            tipoHallazgo:"Por favor ingrese el tipo de hallazgo",
            descripcion: {
    			  maxlength: "Este campo tiene un límite de {0} caracteres"
    			},
   			 color: {
				 required:"Por favor seleccione un color"
   			}
        },
	
		
	});

	//Evento Guardar
	$("#sv_tipoSeveridad").on("click", function() {
		if($("#form_tipoSeveridad").valid()){
		fn_guardar();
		}
		
	});
	//Evento Actualizar
	$("#edit_tipoSeveridad").on("click", function() {
		
     if($("#form_tipoSeveridad").valid()){
     fn_actualizar();
     }
		
	});
	
	$("button:reset").on("click", function(){
		clearFormTipoSeveridad();
	
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
			color:$("#color").val(),
			tipoHallazgo: dtotipohallazgo
		};
		$.ajax({
			url : "/sgp/tipoSeveridad/guardar",
			type : "post",
			data : JSON.stringify(dtocausageneracion),
			contentType : "application/json; charset=utf-8",
			async: false

		}).done(function(data) {
			fn_resultado(data);
			clearFormTipoSeveridad();
			
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
			id : $("#idTipoSeveridad").val(),
			nombre : $("#nombre").val(),
			descripcion : $("#descripcion").val(),
			numeroEstado : $("#estado").val(),
			color:$("#color").val(),
			tipoHallazgo: dtotipohallazgo
		};
		$.ajax({
			url : "/sgp/tipoSeveridad/editar",
			type : "post",
			data : JSON.stringify(dtocausageneracion),
			contentType : "application/json; charset=utf-8",
			async: false

		}).done(function(data) {
			clearFormTipoSeveridad();
			fn_resultado(data);
		}).fail(function(jqXHR, textStatus, errorThrown){
			fn_error(jqXHR, textStatus, errorThrown);
		});
		
}
function fn_editar(id) {
	clearFormValidate();
	$("#sv_tipoSeveridad").hide();
	$("#edit_tipoSeveridad").show();
	jQuery.getJSON("/sgp/tipoSeveridad/getTipoSeveridad", {
		id : id
	}, function(data) {
		
		$('html, body').animate({scrollTop: '0px'}, 800);
		$("#idTipoSeveridad").val(data.id);
		$("#tipoHallazgo").val(data.tipoHallazgo.id);
		$("#nombre").val(data.nombre);
		$("#descripcion").val(data.descripcion);
		$("#estado").val(data.numeroEstado);
		$("#color").val(data.color);
	});

}
function fn_show(id){
	
	jQuery.getJSON("/sgp/tipoSeveridad/getTipoSeveridad", {
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
function clearFormTipoSeveridad(){
	clearFormValidate();
	$("#form_tipoSeveridad").reset();
	$("#edit_tipoSeveridad").hide();
	$("#sv_tipoSeveridad").show();
}

