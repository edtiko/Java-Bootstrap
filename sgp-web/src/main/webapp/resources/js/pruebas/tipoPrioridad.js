$(document).ready(function() {
	
	$("#edit_tipoPrioridad").hide();

	// carga lista de utils.js
	getTiposHallazgo();
//mask


	validator = $('#form_tipoPrioridad').validate({
		//Definir reglas para los campos
		rules:{
			"nombre":{
				required:true,
				maxlength: 50
			},
			"severidad":{
				required:true
			},
			"tipoHallazgo":{
				required:true
			}
			,
			"descripcion":{
				maxlength: 255
			},
			"puntaje":{
				required:true,
				number:true,
				maxlength: 10 ,
				min:0 ,
				max:999999
			}
			
		},
        messages: {
            nombre: {
            	required:"Por favor ingrese el nombre",
  			  maxlength: "Este campo tiene un límite de {0} caracteres"
  			},
  			severidad: {
             	required:"Por favor seleccione un tipo de severidad",
   			 
   			},
  			tipoHallazgo: {
             	required:"Por favor seleccione un tipo de hallazgo",
   			 
   			}
  			,
   			puntaje: {
             	required:"Por favor ingrese el puntaje",
             	number:"Por favor ingrese un dato numérico",
             	max:"Puntuación tiene un valor un máximo de {0}",
             	min:"Puntuación tiene un valor un mínimo de {0}"
   			},
            pais: "Por favor seleccione un país",
            descripcion: {
    			  maxlength: "Este campo tiene un límite de {0} caracteres"
    			}
        },
	
		
	});

	//Evento Guardar
	$("#sv_tipoPrioridad").on("click", function() {
		if($("#form_tipoPrioridad").valid()){
		fn_guardar();
		}
		
	});
	//Evento Actualizar
	$("#edit_tipoPrioridad").on("click", function() {
		
     if($("#form_tipoPrioridad").valid()){
     fn_actualizar();
     }
		
	});
	
	$("button:reset").on("click", function(){
		clearFormTipoPrioridad();
	
	});
	$(document).on("change", "#tipoHallazgo", function() {
		getEsPuntuado($(this).val());
		getTipoSeveridad($(this).val());
		
	});

});
function fn_guardar(){
	var dtotiposeveridad = {
			id : $("#severidad option:selected").val(),
			nombre : $("#severidad option:selected").text()
		};
	
		var dtotipoprioridad = {
			nombre : $("#nombre").val(),
			descripcion : $("#descripcion").val(),
			numeroEstado : $("#estado option:selected").val(),
			puntaje: $("#puntaje").val(),
			tipoSeveridad: dtotiposeveridad
		};
		$.ajax({
			url : "/sgp/tipoPrioridad/guardar",
			type : "post",
			data : JSON.stringify(dtotipoprioridad),
			contentType : "application/json; charset=utf-8",
			async: false

		}).done(function(data) {
			fn_resultado(data);
			clearFormTipoPrioridad();
			$("#divPuntuaje").hide();
		}).fail(function(jqXHR, textStatus, errorThrown){
			fn_error(jqXHR, textStatus, errorThrown);
		});
		}

function fn_actualizar(){
	var dtotiposeveridad = {
			id : $("#severidad option:selected").val(),
			nombre :  $.trim($("#severidad option:selected").text())
		};
		var dtotipoprioridad = {
			id : $("#idTipoPrioridad").val(),
			nombre : $("#nombre").val(),
			descripcion : $("#descripcion").val(),
			numeroEstado : $("#estado").val(),
			puntaje: $("#puntaje").val(),
			tipoSeveridad: dtotiposeveridad
		};
		$.ajax({
			url : "/sgp/tipoPrioridad/editar",
			type : "post",
			data : JSON.stringify(dtotipoprioridad),
			contentType : "application/json; charset=utf-8",
			async: false

		}).done(function(data) {
			clearFormTipoPrioridad();
			$("#divPuntuaje").hide();
			fn_resultado(data);
		}).fail(function(jqXHR, textStatus, errorThrown){
			fn_error(jqXHR, textStatus, errorThrown);
		});
		
}
function fn_editar(id) {
	clearFormValidate();
	$("#sv_tipoPrioridad").hide();
	$("#edit_tipoPrioridad").show();
	jQuery.getJSON("/sgp/tipoPrioridad/getTipoPrioridad", {
		id : id
	}, function(data) {
		
		$('html, body').animate({scrollTop: '0px'}, 800);
		$("#idTipoPrioridad").val(data.id);
		$("#tipoHallazgo").val(data.tipoSeveridad.tipoHallazgo.id);
		getTipoSeveridad(data.tipoSeveridad.tipoHallazgo.id);
		getEsPuntuado(data.tipoSeveridad.tipoHallazgo.id);
		$("#severidad").val(data.tipoSeveridad.id);
		$("#nombre").val(data.nombre);
		$("#descripcion").val(data.descripcion);
		$("#puntaje").val(data.puntaje);
		$("#estado").val(data.numeroEstado);
	});

}
function fn_show(id){
	
	jQuery.getJSON("/sgp/tipoPrioridad/getTipoPrioridad", {
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
				     null,
				     { type: "select", values: [ "Activo", "Inactivo"]  }
				]

	});
}
function clearFormTipoPrioridad(){
	clearFormValidate();
	$("#form_tipoPrioridad").reset();
	$("#puntaje").val(0.0);
	$("#edit_tipoPrioridad").hide();
	$("#sv_tipoPrioridad").show();
	getTipoSeveridad(0);

}

function getEsPuntuado(idTipoHallazgo){
	
	$("#tipoHallazgo").attr("readonly",false);
	$.ajax({
    url: "/sgp/tipoHallazgo/getTipoHallazgo",
    type: "get",
    data: {id:idTipoHallazgo},
    dataType: "json",
    async: false
	}).done(function(data){
		
		if(data.numeroEsPuntuado== 1){
			
			$("#divPuntuaje").show();
		}else{
			$("#puntaje").val(0.0);
			$("#divPuntuaje").hide();
		}
		
	});
}	

