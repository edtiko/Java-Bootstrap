$(document).ready(function() {
	
	$("#edit_param").hide();
   
	loadProyectoDataTable();
	validator = $('#frm_proyecto').validate({
		//Definir reglas para los campos
		rules:{
			"nombre":{
				required: true,
				maxlength: 100
			},
			"pais":{
				required: true
			},
			"departamento":{
				required: true
			},
			"ciudad":{
				required: true
			},
			"empresa":{
				required: true
			},
			"descripcion":{
				maxlength: 1000
			}
		},
		// Specify the validation error messages
		messages: {
			nombre:{
				required: "Por favor ingrese el nombre del proyecto",
				maxlength: "Este campo tiene un límite de {0} caracteres"
			},
			pais: "Por favor seleccione el país",
			departamento: "Por favor seleccione el departamento",
			ciudad: "Por favor seleccione la ciudad",
			empresa: "Por favor seleccione una empresa",
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
	$("#buscarEmpresa").on("click", function(){
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
	
	$(document).on("click",".eliminar", function(){
		var id = $(this).attr("id");
		var elemento = $(this).attr("data-elemento");
		$("#divConfirma").load("/sgp/utils/confirmaEliminar",{id: id, elemento: elemento});
		$("#divConfirma").modal();
	});
	
	$(document).on("click","#si", function(){
	 var elemento = $("#elemento").val();
	 var id = $("#valor").val();
		if(elemento == 'artefacto')
			fn_eliminarArtefacto(id);
		else if(elemento == 'usuario')
			fn_eliminar(id);
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
			numeroEstado: $("#frm_proyecto #estado option:selected").val()
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
		loadArtefactoDataTable();
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
			numeroEstado: $("#frm_proyecto #estado option:selected").val()
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
		$("#frm_proyecto #estado").val(data.numeroEstado);
	});

}
function fn_showProyecto(id){
	
	jQuery.getJSON("/sgp/proyectos/getProyecto", {
		id : id
	}, function(data) {
		
		$("#modal_proyecto p").clear();
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
	$("#modalArtefacto").load("/sgp/asociarArtefactoProyecto/index",{id:id} ,function(){
		$(this).modal();
		
	});
	
	}

function fn_columnFilterProyecto(){
	oTable.columnFilter({
		
		aoColumns: [  null,
		             { type: "number" },
				     { type: "text" },
				     { type: "text" },
				     { type: "select", values: [ "Activo", "Inactivo"]  },
				     { type: "text" },
				]

	});
}

function fn_columnFilterUsuario(){
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
	     data: {usuarios:usuarios ,idProyecto:id}
	     
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
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @date 17/06/2014 14:42:42
 * @description Carga en un elemento(idElement) que soporte la etiqueta 'option' los usuarios 
 * asosciados al proyecto(idProyecto).
 * @param idProyecto
 * @param idElement
 */
function loadUsuariosAsociados(idProyecto, idElement) {
	jQuery.getJSON("/sgp/proyectos/obtenerUsuariosAsociados",{idProyecto:idProyecto}, function(data) {
		$.each(data, function(index, value) {
			$(idElement).append('<option value="'+value.id+'" title="'+value.nombre+'">'+value.nombre+'</option>');
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
			$("#backlog").append('<option value="'+value.id+'" title='+ value.nombre +'>'+value.nombre+'</option>');
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
		$("#divConfirma").modal('hide');
		fn_resultado(data);
	});
	
}
/**
 * @author Edwin Gómez
 * Elimina un registro en Artefacto Proyecto
 * @param id
 */
function fn_eliminarArtefacto(id){
	$.ajax({
		url: "/sgp/asociarArtefactoProyecto/eliminarArtefacto",
		type: "post",
		data: {id:id},
		async: false
	}).done(function(data){
		$("#divConfirma").modal('hide');
		fn_resultado(data);
	});
}
function clearFormProyecto(){
	clearFormValidate();
	$("#frm_proyecto").reset();
	$("#edit_param").hide();
	$("#sv_param").show();
}