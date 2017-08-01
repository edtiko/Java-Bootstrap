$(document).ready(function(){
	
	$("#edit_mapa").hide();
	loadMapaPruebasDataTable();
	validator = $('#frm_mapaPruebas').validate({
		rules:{
			"nombre":{
				required : true,
				maxlength: 100
			},
		    "proyecto":{
		    	required : true
		    },
			"estado":{
		    	required : true
		    }
		},
		 messages: {
            nombre:{ 
            	required: "Por favor ingrese el nombre",
            	maxlength: "Este campo tiene un límite de {0} caracteres"
            	},
            proyecto: "Por favor seleccione el proyecto",
            estado: "Por favor ingrese el estado",
        },
	});
	//Evento Guardar
	$("#sv_mapa").on("click", function() { 
		if($("#frm_mapaPruebas").valid()){
			fn_guardar();
		}
		
	});
	$("#edit_mapa").on("click", function() {
		if($("#frm_mapaPruebas").valid()){
			fn_actualizar();
		}
	});

	
	$("#buscarProyecto").on("click",function(){
		fn_getProyectos();
	});
	
	$("button:reset").on("click", function(){
		clearFormMapa(true);
	
	});
	$('#tabs li:eq(0) a').on("click", function() {
		loadMapaPruebasDataTable();

	});
	
	$('#tabs li:eq(1) a').on("click", function() {
		loadArtefactoMapaPruebasDataTable();
	});
	
	$(document).on("click",".eliminar", function(){
		var id = $(this).attr("id");
		$("#divConfirma").load("/sgp/utils/confirmaEliminar",{id: id});
		$("#divConfirma").modal();
	});
	
	$(document).on("click","#si", function(){
	  fn_eliminar($("#valor").val());
	});
	
	
});


/**
 * 
 * @author Daniel Saavedra - Premize SAS
 * @desc Enviar el Formulario al servidor 
 * @since 22/01/2014
 */
function fn_guardar(){
	var dtoProyecto = {
			id : $("#proyecto").attr("data-id"),
			nombre : $("#proyecto").val()
		};
		var dtomapaPrueba = {
			nombre   : $("#nombre").val(),
			proyecto : dtoProyecto,
			numeroEstado   : $("#estado").val()
		};
		var ajax = $.ajax({
			url : "/sgp/gestionarPrueba/guardar",
			type : "post",
			data : JSON.stringify(dtomapaPrueba),
			contentType : "application/json; charset=utf-8",
			async: true

		}).done(function(data) {
			fn_resultado2(data);
			clearFormMapa(false);
		});
}
/**
 * 
 * @author Daniel Saavedra - Premize SAS
 * @desc Metodo Encargado de Cargar los Artefactos que pertenecen a un mapa de Prueba
 * @since 11/02/2014
 */
function cargarArtefactosAsociados(idMapa){
	
	jQuery.getJSON("/sgp/gestionarPrueba/obtenerArtefactos",{idMapaPrueba:idMapa}, function(data) {
		$("#picklist").html("");
		$.each(data, function(index, value){
			$("#picklist").append('<option value="'+value.id+'" title="'+value.nombre+'" >'+value.nombre+'</option>');
		});
	});
}
/**
 * 
 * @author Daniel Saavedra - Premize SAS
 * @desc 
 * @since 11/02/2014
 */
function cargarListadoBackLog(idMapa){
	
	jQuery.getJSON("/sgp/gestionarPrueba/obtenerBackLog",{idMapaPrueba:idMapa}, function(data) {
		
		$("#backlog").html("");
		$.each(data, function(index, value){
			$("#backlog").append('<option value="'+value.id+'" title="'+value.nombre +'">'+value.nombre+'</option>');
		});
	});
}

/**
 * 
 * @author Daniel Saavedra - Premize SAS
 * @desc Procedimiento encargado de Asociar los Artefactos a un Mapa de Prueba
 * @since 12/02/2014
 */
function asociarArtefactos(){
	var arrayArtefactos = []; 
	
	var id = $("#idMapa").val();
	$('#picklist option').each(function(i, selected){ 
		arrayArtefactos[i] =$(selected).val(); 
	});
	artefactos = JSON.stringify(arrayArtefactos);
	$.ajax({
	     type: "POST",
	     url: "/sgp/gestionarPrueba/actualizarArtefactos",
	     data: {artefactos:artefactos ,idMapa:id},
		 async: false
	}).done(function(data){
		fn_resultado(data);
		$('#asociarArtefactoDiv').modal('hide');
		
	});
}


/**
 * 
 * @author Daniel Saavedra - Premize SAS
 * @desc 
 * @since 12/02/2014
 */
function fn_asociarArtefacto(id){
	
	jQuery.getJSON("/sgp/gestionarPrueba/getMapa", {
		id : id
	}, function(data) {			
		$("#asociarArtefactoDiv").load('/sgp/gestionarPrueba/artefactos',{idmapa:data.id,nombreMapa:data.nombre});
		$("#asociarArtefactoDiv").modal();
		cargarArtefactosAsociados(data.id);
		cargarListadoBackLog(data.id);
	});	
}
/**
 * 
 * @author Daniel Saavedra - Premize SAS
 * @desc 
 * @since 13/02/2014
 */
function fn_columnFilter(){
	oTable.columnFilter({
		aoColumns: [  null,
		             { type: "number" },
				     { type: "text" },
				     { type: "text" },
				     null,
				     null,
				     { type: "select", values: [ "Activo", "Inactivo"]  }
				]

	});
}
/**
 * @author John Hawer Bernal-Premize SAS
 * @desc grilla para asociados mapa de pruebas
 * @since 06/03/2014
 */
function fn_columnFilterAsociar(){
	oTable.columnFilter({
		aoColumns: [  null,
		             { type: "number" },
				     { type: "text" },
				     { type: "text" },
				     { type: "text" },
				     { type: "text" },
				     { type: "select", values: [ "Activo", "Inactivo"]  }
				]

	});
}
/**
 * 
 * @author Daniel Saavedra - Premize SAS
 * @desc 
 * @since 13/02/2014
 */
function fn_editar(id) {
	clearFormValidate();
	$("#edit_mapa").show();
	$("#sv_mapa").hide();
	jQuery.getJSON("/sgp/gestionarPrueba/getMapa", {
		id : id
	}, function(data) {		
		$('html, body').animate({scrollTop: '0px'}, 800);
		$("#id").val(data.id);	
		$("#nombre").val(data.nombre);		
		$("#estado").val(data.numeroEstado);
		$("#proyecto").val(data.proyecto.nombre);
		$("#proyecto").attr("data-id", data.proyecto.id);

	});

}

/**
 * 
 * @author Daniel Saavedra - Premize SAS
 * @desc 
 * @since 13/02/2014
 */
function fn_actualizar(){
	var dtoProyecto = {
			id : $("#proyecto").attr("data-id"),
			nombre : $("#proyecto").val()
		};
		var dtomapaPrueba = {
			id 		 : $("#id").val(),	
			nombre   : $("#nombre").val(),
			proyecto : dtoProyecto,
			numeroEstado   : $("#estado").val()
		};
		$.ajax({
			url : "/sgp/gestionarPrueba/editar",
			type : "post",
			data : JSON.stringify(dtomapaPrueba),
			contentType : "application/json; charset=utf-8",
			async: false

		}).done(function(data) {
			clearFormMapa(true);
			fn_resultado2(data);
		});
}
/**
 * 
 * @author Daniel Saavedra - Premize SAS
 * @desc 
 * @since 13/02/2014
 */
function fn_show(id){
	
	jQuery.getJSON("/sgp/gestionarPrueba/getMapa", {
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

/**
 * 
 * @author Daniel Saavedra - Premize SAS
 * @desc 
 * @since 14/02/2014
 */
function fn_asociarCasoPrueba(id){

	window.location.href="../../sgp/casodepruebas/index?idMapaPrueba="+id;
}

function clearFormMapa(reset){
	
	if (reset){
		$("#frm_mapaPruebas").reset();
	} else {
		$("#nombre").val('');
		$("#proyecto").val('');
	}
	clearFormValidate();
	$("#edit_mapa").hide();
	$("#sv_mapa").show();
}
/**
 * @author John Hawer Bernal
 * @desc carga la grilla de artefactos por mapa de pruebas en gestionar pruebas
 */
function loadArtefactoMapaPruebasDataTable(){
	$("#tabMapaPruebas").html("");
	$("#tabArtefactosMapaPruebas").load("/sgp/asociarArtefactoProyecto/dataTableArtefactosMapaPruebas");
}

/**
 * @author John Hawer Bernal
 * @desc carga la grilla de mapas de pruebas por proyecto en gestionar pruebas(mapaPruebaController)
 */
function loadMapaPruebasDataTable(){
	$("#tabMapaPruebas").load("/sgp/gestionarPrueba/dataTableMapaPruebas");
	$("#tabArtefactosMapaPruebas").html("");
}

/**
 * @author Edwin Gómez
 * @desc Desasociar Artefacto del Proyecto al Mapa de Pruebas
 * @param id
 */
function fn_eliminar(id){
	$.ajax({
		url: "/sgp/gestionarPrueba/eliminarArtefactoMapa",
		type: "post",
		data: {id:id},
		async: false
	}).done(function(data){
		$("#divConfirma").modal('hide');
		fn_resultado(data);
	});
}
