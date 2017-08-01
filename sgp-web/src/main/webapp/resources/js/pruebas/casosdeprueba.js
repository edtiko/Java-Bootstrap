$(document).ready(function() {

	cargar(); 
	
	// Validar Campos
	validator = $('#frm_casosdeprueba').validate({
		//Definir reglas para los campos
		rules:{
			"proyecto":{
				required: true
			},
			"mapaPruebas":{
				required: true
			},
			"artefacto":{
				required: true
			},
			"tipoPruebas":{
				required: true
			},
			"descripcion":{
				required: true,
				maxlength: 5000
			},
			"resultadoEsperado":{
				required: true,
				maxlength: 5000
			}
		},
		// Specify the validation error messages
		messages: {
			proyecto: "Por favor seleccione un proyecto",
			mapaPruebas: "Por favor seleccione un mapa de pruebas",
			artefacto: "Por favor seleccione un artefacto",
			tipoPruebas: "Por favor seleccione el tipo de prueba",
			descripcion:{
				required: "Por favor ingrese la descripción de la prueba",
				maxlength: "Este campo tiene un límite de {0} caracteres"
			},
			resultadoEsperado:{
				required: "Por favor ingrese el resultado esperado",
				maxlength: "Este campo tiene un límite de {0} caracteres"
			}
		},
	});
	
	// Evento BuscaProyectos
	$("#buscarProyecto").on("click", function() {
		fn_getProyectos();
	});

	// Evento BuscaArtefacto
	$("#mapaPruebas").on("change", function() {
		
		var idMapaPrueba = $(this).val();
		var nombreMapaPrueba = $("#mapaPruebas option:selected").text();
		getArtefactoPorMapaPruebas(idMapaPrueba);
		$("#nombreMapaPruebas").text(nombreMapaPrueba);
		fn_filter(nombreMapaPrueba);	
	});
	// Evento Guardar
	$("#sv_casoprueba").on("click", function() {
		if ($("#frm_casosdeprueba").valid()) {
			fn_guardar();
		}
	
	});
	//Evento Abrir Modal para File
	$("#upload_casoprueba").on("click", function() {	
		validator.element( "#proyecto" ); 
		if(validator.element( "#mapaPruebas" )){
			$("#resultImport").empty();
			$("#upload_action").removeAttr('disabled');
			$("#upload_action").show();
			fn_showFileUpload();
		}
		
	});
	//Evento ejecutar accion de cargue
	$("#upload_action").on("click", function() {
		uploadFormData();	
	});
	// Evento Actualizar
	$("#edit_casoprueba").on("click", function() {
	
		if ($("#frm_casosdeprueba").valid()) {
			fn_actualizar();
		}
	});
	// Evento Actualizar
	$("#limpiar").on("click", function() {
		fn_limpiar(true);
	});
	
	$('#modal_File_show').on('hidden.bs.modal', function (e) {
		 $("#archivo").val("");
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
 * Inicializa los campos al cargar la pagina
 */
function cargar() {
	var idMapaPrueba = $("#idMapaPrueba_default").val();
	
	$("#edit_casoprueba").hide();
	oTable.fnSetColumnVis( 5,  false);
//	oTable.fnSetColumnVis( 1,  false);
	fn_filter(null);
	 $("#tipoPruebas").getParametrosPorTipoParametro(9);
	
	// Si ya viene el idMapaPrueba al cargar la pagina
	if ( idMapaPrueba != "" ){
		
		fn_getMapaPrueba(idMapaPrueba);
		getArtefactoPorMapaPruebas(idMapaPrueba);
		$(".mapa_container").hide();
	}
}
/**
 * Limpia los campos del formulario
 */
function fn_limpiar(reset){
	
	clearFormValidate();
	$("#edit_casoprueba").hide();
	$("#sv_casoprueba").show();
	$("#artefacto").attr("readonly",true);
	$("#mapaPruebas").attr("readonly",true);
	// Si hay que limpiar todos los campos 
	// y no es llamada desde Gestionar Mapas de Pruebas
	if (reset && $("#idMapaPrueba_default").val() == ""){
		$("#frm_casosdeprueba").reset();
	} else {
		$("#artefacto").val("");
		$("#tipoPruebas").val("");
		$("#descripcion").val("");
		$("#resultadoEsperado").val("");
	}
}

/**
 * Guarda un caso de prueba
 */
function fn_guardar() {

	var dtoArtefacto = {
		id : $("#artefacto option:selected").val(),
		nombre : $("#artefacto option:selected").text()
	};
	var dtoMapaPruebas = {
		id : $("#mapaPruebas option:selected").val(),
		nombre : $("#mapaPruebas option:selected").text()
	};
	var dtoParametroTipoPrueba = {
			id : $("#tipoPruebas option:selected").val(),
			nombre : $("#tipoPruebas option:selected").text()
		};
	var dtocasodepruebas = {
		descripcion : $("#descripcion").val(),
		resultado : $("#resultadoEsperado").val(),
		tipoPrueba : dtoParametroTipoPrueba,
		artefacto : dtoArtefacto,
		mapaPrueba : dtoMapaPruebas
	};
	$.ajax({
		url : "/sgp/casodepruebas/guardar",
		type : "post",
		data : JSON.stringify(dtocasodepruebas),
		contentType : "application/json; charset=utf-8",
		async: false

	}).done(function(data) {

		fn_resultado2(data);
		fn_limpiar(false);

	}).fail(function(jqXHR, textStatus, errorThrown) {
		fn_error(jqXHR, textStatus, errorThrown);
	});
	
}

function fn_actualizar() {

	var dtoArtefacto = {
		id : $("#artefacto option:selected").val(),
		nombre : $("#artefacto option:selected").text()
	};
	var dtoMapaPruebas = {
		id : $("#mapaPruebas option:selected").val(),
		nombre : $("#mapaPruebas option:selected").text()
	};
	var dtoParametroTipoPrueba = {
			id : $("#tipoPruebas option:selected").val(),
			nombre : $("#tipoPruebas option:selected").text()
		};
	var dtocasodepruebas = {
		id : $("#idCasoPrueba").val(),
		descripcion : $("#descripcion").val(),
		resultado : $("#resultadoEsperado").val(),
		tipoPrueba : dtoParametroTipoPrueba,
		artefacto : dtoArtefacto,
		mapaPrueba : dtoMapaPruebas
	};
	$.ajax({
		url : "/sgp/casodepruebas/editar",
		type : "post",
		data : JSON.stringify(dtocasodepruebas),
		contentType : "application/json; charset=utf-8",
		async: false

	}).done(function(data) {
		fn_resultado2(data);
		fn_limpiar(false);
		
	}).fail(function(jqXHR, textStatus, errorThrown) {
		fn_error(jqXHR, textStatus, errorThrown);
	});
}
function fn_editar(id) {
	clearFormValidate();
	$("#sv_casoprueba").hide();
	$("#edit_casoprueba").show();
	jQuery.getJSON("/sgp/casodepruebas/getCasoPrueba", {
		id : id
	}, function(data) {
		
		$('html, body').animate({scrollTop: '0px'}, 800);
		$("#idCasoPrueba").val(data.id);
		$("#proyecto").val(data.mapaPrueba.proyecto.nombre);
		$("#proyecto").attr("data-id", data.mapaPrueba.proyecto.id);
		getMapaPruebasPorProyecto(data.mapaPrueba.proyecto.id);
		$("#mapaPruebas").val(data.mapaPrueba.id);
		getArtefactoPorMapaPruebas(data.mapaPrueba.id);
		$("#artefacto").val(data.artefacto.id);
		$("#tipoPruebas").val(data.tipoPrueba.id);
		$("#descripcion").val(data.descripcion);
		$("#resultadoEsperado").val(data.resultado);
	});

}
function fn_columnFilter() {
	oTable.columnFilter({
		aoColumns : [ null, {
			type : "text"
		}, {
			type : "text"
		}, {
			type : "text"
		}, {
			type : null
		}, {
			type : null // columna filtro mapa de prueba
		}, {
			type : "text"
		}/*, {
			type : "text"
		}*/

		]

	});
}
function fn_show(id) {

	jQuery.getJSON("/sgp/casodepruebas/getCasoPrueba", {
		id : id
	}, function(data) {
		
		$("#modal_show p").clear();
		$("#modal_show #consecutivo").text(data.consecutivo);
		$("#modal_show #id_caso").text(data.id);
		$("#modal_show #descripcion").text(data.descripcion);
		$("#modal_show #resultado").text(data.resultado);
		$("#fec_creacion").text(data.fechaCreacionString);
		$("#fec_ejecuta").text(data.fechaEjecutaString);
		$("#usu_ejecuta").text(data.usuarioEjecuta);
		$("#usu_crea").text(data.usuarioCreacion);
		$("#fec_mod").text(data.fechaEditaString);
		$("#usu_mod").text(data.usuarioEdita);
	});

	$("#modal_show").modal();
}

function uploadFormData(){
	
	var idMapaPruebas = $("#mapaPruebas").val();
	var oMyForm = new FormData();
	oMyForm.append("file", archivo.files[0]);
	oMyForm.append("idMapaPruebas", idMapaPruebas);
	$.ajax({
		url: '/sgp/casodepruebas/upload',
	    data: oMyForm,
	    dataType: 'text',
	    processData: false,
	    contentType: false,
	    type: 'POST',
	    async: false,
	    beforeSend: function() {
	    	$("#upload_action").attr('disabled','disabled');
	    },
	    complete: function(){
			 $("#upload_action").hide();
	    }
	}).done(function(data) {
		 $('#resultImport').empty().html(data);
		 oTable.fnDraw();
	});
}
	
function fn_showFileUpload(){
	$("#modal_File_show").modal();
}

/**
 * Obtiene el mapa prueba por id
 * @param id
 */
function fn_getMapaPrueba(id) {
	jQuery.getJSON("/sgp/gestionarPrueba/getMapa", {
		id : id
	}, function(data) {		
		$("#mapaPruebas").html("");
		$("#mapaPruebas").append('<option value="'+data.id+'">'+data.nombre+'</option>');	
		$("#nombreMapaPruebas").text(data.nombre);
		$("#proyecto").attr("data-id", data.proyecto.id);
		fn_filter(data.nombre);
	});
}

/**
 * Filtra los casos de prueba por mapa de prueba
 * @param value
 */
function fn_filter(value) {
	oTable.fnFilter(value, 5, true, false);
}
/**
 * Elimina un caso de prueba por id
 * @param id
 */
function fn_eliminar(id){
	$.ajax({
		url : "/sgp/casodepruebas/eliminar",
		type : "post",
		data : {id:id}
	
	}).done(function(data) {
		$("#divConfirma").modal('hide');
		fn_resultado(data);
	});
}

