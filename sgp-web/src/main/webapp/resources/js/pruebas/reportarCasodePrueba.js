$(document).ready(function() {
	
	$("#edit_reportHallazgo").hide();
	getProyecto();

	validator = $('#form_reportarHallazgo').validate({
		//Definir reglas para los campos
		rules:{
			"nombreProyecto":{
				required: true,
				maxlength: 50
			},
			"nombreMapaPruebas":{
				required: true
			},
			"nombreArtefacto":{
				required: true
			},
			"descripcion":{
				maxlength: 255
			}
		},
		// Specify the validation error messages
		messages: {
			nombreProyecto:{
				required: "Por favor ingrese el nombre del proyecto",
				maxlength: "Este campo tiene un límite de {0} caracteres"
			},
			nombreMapaPruebas:{
				required: "Por favor ingrese el nombre del mapa de pruebas",
				maxlength: "Este campo tiene un límite de {0} caracteres"
			},
			nombreArtefacto:{
				required: "Por favor ingrese el nombre del artefacto",
				maxlength: "Este campo tiene un límite de {0} caracteres"
			},
			
			descripcion: {
			  maxlength: "Este campo tiene un límite de {0} caracteres"
			}
		},


	});
	//Evento Guardar
	$("#sv_param").on("click", function() {
		if($("#frm_proyecto").valid()){
			fn_guardarProyecto();
		}

	});
	//Evento Actualizar
	$("#edit_param").on("click", function() {

		if($("#frm_proyecto").valid()){
			fn_modificarProyecto();
		}

	});
    //Carga el modal de seleccionar empresa
	$("#empresa").on("click", function(){
		fn_getEmpresas();
	});
	
	$('#tabs li:eq(0) a').on("click", function() {
		loadProyectoDataTable();

	});
	
	$('#tabs li:eq(1) a').on("click", function() {
		loadArtefactoDataTable();

	});
	
	$('#tabs li:eq(2) a').on("click", function() {
		loadUsuarioProyectoDataTable();

	});
	
	$("button:reset").on("click", function(){
		clearFormProyecto();
	
	});

});

function fn_guardarProyecto(){
	var empresaDTO = {
			id : $("#empresa").attr("data-id"),
			nombre : $("#empresa").val()
	};
	var proyectoDTO = {
			nombre : $("#nombre").val(),
			descripcion : $("#descripcion").val(),
			empresa : empresaDTO,
			numeroEstado: $("#estado option:selected").val()
	};
	$.ajax({
		url : "/sgp/proyectos/guardar",
		type : "post",
		data : JSON.stringify(proyectoDTO),
		contentType : "application/json; charset=utf-8",
		async: false

	}).done(function(data) {
		
		fn_resultado(data);
		loadProyectoDataTable();
		clearFormProyecto();

	}).fail(function(jqXHR, textStatus, errorThrown){
		fn_error(jqXHR, textStatus, errorThrown);
	});
}
function fn_modificarProyecto(){
	
	var empresaDTO = {
			id : $("#empresa").attr("data-id"),
			nombre : $("#empresa").val()
	};
	var proyectoDTO = {
			id: $("#id").val(),
			nombre : $("#nombre").val(),
			descripcion : $("#descripcion").val(),
			empresa : empresaDTO,
			numeroEstado: $("#estado option:selected").val()
	};
	$.ajax({
		url : "/sgp/proyectos/editar",
		type : "post",
		data : JSON.stringify(proyectoDTO),
		contentType : "application/json; charset=utf-8",
		async: false

	}).done(function(data) {
		
		clearFormProyecto();
		fn_resultado(data);
	}).fail(function(jqXHR, textStatus, errorThrown){
		fn_error(jqXHR, textStatus, errorThrown);
	});
}
function fn_editarProyecto(id) {
	clearFormValidate();
	$("#sv_param").hide();
	$("#edit_param").show();
	jQuery.getJSON("/sgp/proyectos/getProyecto", {
		id : id
	}, function(data) {

		 $('html, body').animate({scrollTop: '0px'}, 800);
		$("#id").val(data.id);
		$("#empresa").val(data.empresa.nombre+" - "+data.empresa.ciudad.nombre);
		$("#empresa").attr("data-id", data.empresa.id);
		$("#nombre").val(data.nombre);
		$("#descripcion").val(data.descripcion);
		$("#estado").val(data.numeroEstado);
	});

}
function fn_showProyecto(id){
	
	jQuery.getJSON("/sgp/proyectos/getProyecto", {
		id : id
	}, function(data) {
		
   
        $("#modal_proyecto #consecutivo").text(data.id);
		$("#modal_proyecto #nombre").text(data.nombre);
		
		$("#modal_proyecto #estado").text(data.indActivo);
		$("#modal_proyecto #fec_creacion").text(data.fechaCreacionString);
		$("#modal_proyecto #fec_mod").text(data.fechaEditaString);
		$("#modal_proyecto #usu_crea").text(data.usuarioCreacion);
		$("#modal_proyecto #usu_mod").text(data.usuarioEdita);
	});
	
 $("#modal_proyecto").modal();
}

function fn_asociarArtefacto(id){
	$("#modalArtefacto").load("/sgp/asociarArtefactoProyecto/index");
	jQuery.getJSON("/sgp/proyectos/getProyecto", {
		id : id
	}, function(data) {
		$("#proyecto").val(data.nombre);
		$("#proyecto").attr("data-id",data.id);
		getTiposArtefacto();
	});
	$("#modalArtefacto").modal();
	}

function fn_columnFilterProyecto(){
	oTable.columnFilter({
		
		aoColumns: [  null,
		             { type: "number" },
				     { type: "text" },
				     { type: "text" },
				     { type: "select", values: [ "Activo", "Inactivo"]  }
				]

	});
}

function fn_columnFilterUsuario(){
oTable.columnFilter({
		
		aoColumns: [  null,
		             { type: "number" },
				     { type: "text" },
				     { type: "text" },
				     { type: "select", values: [ "Activo", "Inactivo"]  }
				]

	});
}
/**
 * @author Edwin Gómez
 * @desc carga la grilla de artefactos
 */
function loadArtefactoDataTable(){
	$("#tabProyectos").html("");
	$("#tabUsuarios").html("");
	$("#tabArtefactos").load("/sgp/asociarArtefactoProyecto/dataTable");
}

/**
 * @author Edwin Gómez
 * @desc carga la grilla de proyectos
 */
function loadProyectoDataTable(){
	$("#tabUsuarios").html("");
	$("#tabArtefactos").html("");
	$("#tabProyectos").load("/sgp/proyectos/dataTable");
}

/**
 * @author Edwin Gómez
 * @desc carga la grilla de usuario proyecto
 */
function loadUsuarioProyectoDataTable(){
	$("#tabArtefactos").html("");
	$("#tabProyectos").html("");
	$("#tabUsuarios").load("/sgp/proyectos/dataTableUsuarios");
}


/**
 * 
 * @author Daniel Saavedra - Premize SAS
 * @desc 
 * @since 14/02/2014
 */
function asociarUsuariosProyecto(){
	var arrayUsuarios = []; 
	
	var id = $("#idProyecto").val();
	$('#picklist option').each(function(i, selected){ 
		arrayUsuarios[i] =$(selected).val(); 
	});
	usuarios = JSON.stringify(arrayUsuarios);
	$.ajax({
	     type: "POST",
	     url: "/sgp/proyectos/actualizarUsuarios",
	     data: {usuarios:usuarios ,idProyecto:id},
		 async: false
	     
	}).done(function(data){
	
		$('#asociarUsuariosDiv').modal('hide');
		$("#tabs li:eq(2) a").tab('show');
		loadUsuarioProyectoDataTable();
		
	});
}

/**
 * @author Daniel Saavedra - Premize SAS
 * @desc 
 * @since 12/02/2014
 */
function fn_loadDivAsociarUsuario(id){
	$("#asociarUsuariosDiv").load('/sgp/proyectos/usuarios',{idProyecto:id});
	$("#asociarUsuariosDiv").modal();
	cargarUsuariosAsociados(id);
	cargarListadoBackLog(id);
	
}


/**
 * Metodo Encargado de Cargar los Usuarios Disponibles
 * @author Daniel Saavedra - Premize SAS
 * @desc 
 * @since 14/02/2014
 */
function cargarUsuariosAsociados(idProyecto){
	
	jQuery.getJSON("/sgp/proyectos/obtenerUsuariosAsociados",{idProyecto:idProyecto}, function(data) {
		$("#picklist").html("");
		$.each(data, function(index, value){
			$("#picklist").append('<option value="'+value.id+'">'+value.nombre+'</option>');
		});
	});
}
/**
 * 
 * @author Daniel Saavedra - Premize SAS
 * @desc 
 * @since 11/02/2014
 */
function cargarListadoBackLog(idProyecto){
	
	jQuery.getJSON("/sgp/proyectos/obtenerBackLog",{idProyecto:idProyecto}, function(data) {
		
		$("#backlog").html("");
		$.each(data, function(index, value){
			$("#backlog").append('<option value="'+value.id+'">'+value.nombre+'</option>');
		});
	});
}

/**
 * @author Edwin Gómez
 * @desc Elimina un registro de Usuario Proyecto
 * @param id
 */
function fn_eliminar(id){
	
	$.ajax({
		url: "/sgp/proyectos/eliminarUsuarioProyecto",
		type: "post",
		data: {id:id},
		async: false
	}).done(function(data){
		oTable.fnDraw();
	});
	
}
function clearFormProyecto(){
	clearFormValidate();
	$("#frm_proyecto").reset();
	$("#edit_param").hide();
	$("#sv_param").show();
}