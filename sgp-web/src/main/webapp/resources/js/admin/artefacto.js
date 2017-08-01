$(document).ready(function(){
	
	/**
	 * Ejecuta al cargar la pagina
	 */
	cargarTiposArtefactos();
	
	
	/**
	 * Eventos jQuery
	 */
	$('#btnGuardar').click(function(){
		if($("#formArtefacto").valid()){
			$().guardarArtefacto();
		}
	});
	
	$('#btnModificar').click(function(){
		if($("#formArtefacto").valid()){
			$().modificarArtefacto();
		}
	});
	
	$('#btnLimpiar').click(function(){
        $('#idArtefacto').val('');
	});
	
	
	/**
	 * Definir reglas para los campos
	 */
	$('#formArtefacto').validate({
		debug: true,
		rules:{
			"nombre":{
			      required: true
			},
			"tipoArtefacto":{
			      required: true
			},
			"estado":{
			      required: true
			},
			"descripcion":{
				maxlength: 255
			}
		},
        messages: {
            nombre: {
            	required: "Por favor ingrese el nombre",
            	maxlength: "Este campo tiene un limite de 50 caracteres"
            		},
            tipoArtefacto: "Por favor seleccione el tipo",
            estado: "Por favor seleccione el estado"
        }
	});

});


/**
 * Funciones JQuery
 */
$.fn.construirArtefactoJson = function(){

	  var idArtefacto = Number($("#idArtefacto").val());
	  var idTipo = Number($("#tipoArtefacto option:selected").val());
	  
	  var artefactoDto = 
	  	{ id: idArtefacto,
		  nombre: $("#nombre").val(), 
		  descripcion: $("#descripcion").val(),
		  indActivo: Number($('#estado').val()),
		  tipoArtefacto: { id: idTipo }
	  	};
	  
	  return artefactoDto;
};

$.fn.guardarArtefacto = function(){
	
	var artefactoDto = $().construirArtefactoJson();
	
	$.ajax({
		  url: "/sgp/artefactos/guardar.htm",
		  type: "POST",
		  data: JSON.stringify(artefactoDto),
		  contentType: "application/json; charset=utf-8",
		  async: false
		})
		.done(function(data){
			 $("#alerta span").text(data);
			 $("#alerta").show(); 
		})
		.fail(function (data,status,er){
			  showError("error");
		});
	
};

$.fn.modificarArtefacto = function(){
	
	var artefacto = $().construirArtefactoJson();
	
	$.ajax({
		  url: "/sgp/artefactos/actualizar.htm",
		  type: "POST",
		  data: JSON.stringify(artefacto),
		  contentType: "application/json; charset=utf-8",
		  async: false
		})
		.done(function(data){
			 $("#alerta span").text(data); 
			 $("#alerta").show(); 

		})
		.fail(function (data,status,er){
			  showError("error");
		});
};


function fn_editar(id) {
	
	jQuery.getJSON("/sgp/artefactos/obtenerArtefacto", {
		id : id
	}, function(data) {
		
		$("#idArtefacto").val(data.id);
		$("#tipoArtefacto").val(data.tipoArtefacto.id);
		$("#nombre").val(data.nombre);
		$("#descripcion").val(data.descripcion);
		$("#estado").val(data.indActivo);
	});
}

function fn_show(id) {
	
	jQuery.getJSON("/sgp/artefactos/obtenerArtefacto", {
		id : id
	}, function(data) {
		
		$("#modal_show p").clear();
		$("#consecutivoReg").val(data.id);
		$("#nombreReg").val(data.nombre);
		$("#estadoReg").val(data.nombreEstado);
		$("#fechaCreaReg").val(data.fechaCreacion);
		$("#usuarioCreaReg").val(data.usuarioCreacion.nombre);
		$("#fechaEditaReg").val(data.fechaEdita);
		$("#usuarioEditaReg").val(data.usuarioEdita.nombre);
		
	});
	
	 $("#modal_show").modal();
}





