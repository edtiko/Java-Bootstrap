$(document).ready(function() {
	
	$("#edit_departamento").hide();
	// carga lista de utils.js
		getPaises();
	validator = $('#form_departamento').validate({
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
    			}
        },
	
		
	});

	//Evento Guardar
	$("#sv_departamento").on("click", function() {
		if($("#form_departamento").valid()){
		fn_guardar();
		}
		
	});
	//Evento Actualizar
	$("#edit_departamento").on("click", function() {
		
     if($("#form_departamento").valid()){
     fn_actualizar();
     }
		
	});
	
	$("button:reset").on("click", function(){
		clearFormDepto();
	
	});

});
function fn_guardar(){
	var dtopais = {
			id : $("#pais option:selected").val(),
			nombre : $("#pais option:selected").text()
		};
	
		var dtodepartamento = {
			nombre : $("#nombre").val(),
			descripcion : $("#descripcion").val(),
			numeroEstado : $("#estado option:selected").val(),
			pais: dtopais
		};
		$.ajax({
			url : "/sgp/departamentos/guardar",
			type : "post",
			data : JSON.stringify(dtodepartamento),
			contentType : "application/json; charset=utf-8",
			async: false

		}).done(function(data) {
			fn_resultado(data);
			clearFormDepto();
			
		}).fail(function(jqXHR, textStatus, errorThrown){
			fn_error(jqXHR, textStatus, errorThrown);
		});
		}

function fn_actualizar(){
	var dtopais = {
			id : $("#pais option:selected").val(),
			nombre :  $.trim($("#pais option:selected").text())
		};
		var dtodepartamento = {
			id : $("#idDepartamento").val(),
			nombre : $("#nombre").val(),
			descripcion : $("#descripcion").val(),
			numeroEstado : $("#estado").val(),
			pais: dtopais
		};
		$.ajax({
			url : "/sgp/departamentos/editar",
			type : "post",
			data : JSON.stringify(dtodepartamento),
			contentType : "application/json; charset=utf-8",
			async: false

		}).done(function(data) {
			clearFormDepto();
			fn_resultado(data);
		}).fail(function(jqXHR, textStatus, errorThrown){
			fn_error(jqXHR, textStatus, errorThrown);
		});
		
}
function fn_editar(id) {
	clearFormValidate();
	$("#sv_departamento").hide();
	$("#edit_departamento").show();
	jQuery.getJSON("/sgp/departamentos/getDepartamento", {
		id : id
	}, function(data) {
		
		$('html, body').animate({scrollTop: '0px'}, 800);
		$("#idDepartamento").val(data.id);
		$("#pais").val(data.pais.id);
		$("#nombre").val(data.nombre);
		$("#descripcion").val(data.descripcion);
		$("#estado").val(data.numeroEstado);
	});

}
function fn_show(id){
	tabla_pivot();
	jQuery.getJSON("/sgp/departamentos/getDepartamento", {
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
function clearFormDepto(){
	clearFormValidate();
	$("#form_departamento").reset();
	$("#edit_departamento").hide();
	$("#sv_departamento").show();
}
function tabla_pivot(){


	$.ajax({
	    url: "/sgp/reportes/getReporteSeveridad",
	    type: "get",
	    data: {},
	    dataType: "json",
	    async: false
		}).done(function(severidad) {
			$.grep(severidad, function(value, index){
				$('#tablaSeveridad').append('<div id="outputss'+index+'"></div>');
				$('#tablaSeveridad').append('<div id="outputs'+index+'"></div>');
				var obj = jQuery.parseJSON(value);
				$.each(obj,function(i,v){
					$('#outputss'+index).append('<br><br><br><strong>'+v.tipoHallazgo+'</strong>');
					return false;
				});
				$('#d'+index).pivot(obj, {
					cols: ["tipoSeveridad"], rows: ["usuarioAsignado"],
				})	;
			});
		});
	
	
	
	
}