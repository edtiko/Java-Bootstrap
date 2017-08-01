var restriccionesAnexo = null; //json
var fileSize = null;
var fileExtension= null;


/**
 * @author Edwin Gómez
 * @desc Gestionar Hallazgo
 * @since 25/03/2013
 */
$(document).ready(function(){

	cargar();

	fn_getRestriccionesAnexo().done(function (data){
		restriccionesAnexo = data.data;
		$("#anexo").attr("accept",restriccionesAnexo.allowedfiles);
	}).fail(function (jqXHR, textStatus, errorThrown){
		fn_error(jqXHR, textStatus, errorThrown);
	});

	jQuery.validator.addMethod("fileMaxSize", function(value, element, param) {
		return this.optional(element) || fileSize < param[0];
	}, "El peso del archivo seleccionado no es permitido");
	
//	jQuery.validator.addMethod("allowedFiles", function(value, element, param) {
//		var ext = "." + value.split(".").pop();
//		ext = ext.toLocaleLowerCase();
//		return param.indexOf(ext) > -1;
//	}, "El tipo de archivo seleccionado no es permitido");
	
	
	validator = $('#form_hallazgo').validate({

		//Definir reglas para los campos
		rules:{
			"asignar":{
				required: true
			},
			"estadoHallazgo":{
				required: true
			},
			"motivoHallazgo":{
				required: true
			},
			"observacion":{
				required: true,
				maxlength: 5000
			},
			"causaAnula":{
				required: true
			}
	
		},
		// Specify the validation error messages
		messages: {
			asignar: {
				required: "Por favor ingrese el usuario"
			},
			estadoHallazgo: "Por favor ingrese el estado",
			motivoHallazgo: "Por favor ingrese un motivo",
			causaAnula: "Por favor ingrese la causa de anulación",
			observacion: {
				required: "Por favor ingrese la observación",
				maxlength: "Este campo tiene un límite de {0} caracteres"
			}
		}
	});

	//Evento Actualizar
	$("#edit_hallazgo").on("click", function(){
		if($("#form_hallazgo").valid()) {
			loadingDialog.openSgpLoading();
			fn_gestionarHallazgo();
		}

	});

	//Evento Modificar Datos Hallazgo
	$(document).on("click","#mod_hallazgo", function(){

		$('#frmDatosHallazgo').validate({

			//Definir reglas para los campos
			rules:{
				"severidad":{
					required: true
				},
				"causaGeneracion":{
					required: true
				},
				"prioridad":{
					required: true
				},
				"tipoHallazgo":{
					required: true
				},
				"titulo":{
					required: true
				}
			},
			// Specify the validation error messages
			messages: {
				severidad:    		"Por favor seleccione una severidad",
				causaGeneracion: 	"Por favor seleccione una causa de generación",
				prioridad:    		"Por favor seleccione una prioridad",
				tipoHallazgo: 		"Por favor seleccione un tipo de hallazgo",
				titulo:       		"Por favor ingrese el título"
			}
		});

		if($("#frmDatosHallazgo").valid()){
			fn_modificarDatosHallazgo();
		}

	});

	//Evento Anexar Archivo
	$(document).on("click","#adjuntar_anexo", function() {

		$('#frm_anexo').validate({
			rules:{
				"anexo":{
					required: true,
					accept: restriccionesAnexo.allowedfiles,
					fileMaxSize: [restriccionesAnexo.filesize]
				}
			},
			messages: {
				anexo: {
					required: "Por favor seleccione un anexo",
					accept: "El tipo de archivo seleccionado no es permitido",
					fileMaxSize: "El peso del archivo seleccionado no es permitido"
				}
			}
		});

		if($("#frm_anexo").valid()){

			$("#adjuntar_anexo").button('loading');
			fn_guardarAnexo();
		}

	});

	$(document).on("click",".eliminar", function(){
		var id = $(this).attr("id");
		$("#divConfirma").load("/sgp/utils/confirmaEliminar",{id: id});
		$("#divConfirma").modal();
	});

	$(document).on("click","#si", function(){
		fn_eliminar($("#valor").val());
	});


	$("#estadoHallazgo").on("change", function(){
		if($("#estadoHallazgo option:selected").text() == 'Anulado'){
			$("#divCausaAnula").show();
			$("#causaAnula").getParametrosPorTipoParametro(5);
		}
		else{
			$("#divCausaAnula").hide();
			
		}
		ocultaUsuario($("#estadoHallazgo option:selected").text());
	});

	$("#show_edit").on("click",function(){
		if($("#ind_edit").val() == 'true'){
			$("#show_edit").attr("href","javascript:cargarModificar()");
			$("#ind_edit").val("false");
		}
		else{
			$("#show_edit").attr("href","javascript:cargarSinModificar()");
			$("#ind_edit").val("true");
		}

	});

	$(document).on("click","#edit_observacion",function(){
		$('#frm_edit_flujo').validate({
			rules:{
				"observacionFlujo":{
					required: true
				}
			},
			messages: {
				observacionFlujo: "Por favor ingrese una observación"

			}
		});

		if($('#frm_edit_flujo').valid()){
			fn_editarObservacion();
		}
	});

	$("#checkSi").on("change", function(){

		
		fn_marcarReasignado();	   
	});
	
	$("#checkNo").on("change", function(){
		$("#divEstadoHallazgo").show();
		$("#divReasignaHallazgo").hide(); 
		$("#motivoHallazgo").val("");   
	});
	
	$("#checkNo").attr("checked","checked");
	$( "#checkNo" ).trigger( "change" );
	
	$(document).on("click","#aceptar",function(){
		window.location = "/sgp/hallazgos/getBandeja";
	});
	
	$(document).on("click","#cancelar",function(){
		$("#checkNo").attr("checked","checked");
		$( "#checkNo" ).trigger( "change" );
	});
	
	$(document).on("change", "#anexo", function() {
		//retorna el size del archivo en bytes, se convierte a KB.
		if(this.files[0]) {
			fileSize = (this.files[0].size) / 1024;
			
			$('#frm_anexo').validate({
				rules:{
					"anexo":{
						accept: restriccionesAnexo.allowedfiles,
						fileMaxSize: [restriccionesAnexo.filesize]
					}
				},
				messages: {
					anexo: {
						accept: "El tipo de archivo seleccionado no es permitido",
						fileMaxSize: "El peso del archivo seleccionado no es permitido"
					}
				}
			});
			
			$("#frm_anexo").valid();
		}
	});
	
	$(document).on("click","#anexo",function(){
		$("#anexo").attr("accept",restriccionesAnexo.allowedfiles);
	});
	
	$(document).on("change", "#tipoHallazgo", function() {
		getCausaGeneracion($(this).val());
		getTipoSeveridad($(this).val());
		getTieneCausaGeneracion($(this).val());
	});
	$(document).on("change", "#severidad", function() {
	
		getTipoPrioridad($(this).val());
		
	});
	

});

function cargar(){

	var idProyecto = $("#idProyecto").val(),
	rolesUsuario = jQuery.parseJSON($("#rolesUsuario").val()),
	rolesAdmin =   jQuery.parseJSON($("#rolesAdmin").val()),
	flagRol = false;

	$.each(rolesUsuario, function(i,v){
		if($.inArray(rolesUsuario[i], rolesAdmin)> -1 ){
			flagRol = true;
		}

	});

	if(flagRol){
		$("#show_edit").show();
		fn_getSeguimientoHallazgo();
	}
	else{
		fn_getSeguimientoHallazgo(function(){
			
			//Ocultamos la cabecera de las columas                    
			$('#flujoHallazgo th:eq(4)').hide();

			//Ocultamos todo los detalles de esa columna                    
			$('#flujoHallazgo td:nth-child(5)').hide(); 
		});	


	}

	cargarUsuariosAsociados(idProyecto);
	getEstadosHallazgo();
	fn_getAnexosHallazgo();

}


function cargarModificar(){

	var idHallazgo = $("#idHallazgo").val();
	var manual=$("#manual").val();

	
	$("#datos").load("/sgp/hallazgos/getDatosHallazgo", {idHallazgo:idHallazgo, accion:"editar"},function(){
		var tipoHallazgo=$("#idTipoHallazgo").val();
	   if(manual== "Si"){
		   getTipoHallazgo("1");
		 
	   }else{
		   getTipoHallazgo("0");

		
	   }
	   $("#tipoHallazgo").val(tipoHallazgo);
		getTipoPrioridad($("#idTipoSeveridad").val());
		 $("#prioridad").val($("#idTipoPrioridad").val());
		getTipoSeveridad(tipoHallazgo);
		   $("#severidad").val($("#idTipoSeveridad").val());
		
		  getCausaGeneracion(tipoHallazgo);
		   $("#causaGeneracion").val($("#idCausaGeneracion").val());
		   getTieneCausaGeneracion(tipoHallazgo);
		
		if($("#idcausaAnula").val() != ""){
			$("#causaAnula").html("");
		}
		$("#causaAnula").getParametrosPorTipoParametro(5, function(){

			$("#causaAnula").val($("#idcausaAnula").val());
		});


	});

}

function cargarSinModificar(){
	var idHallazgo = $("#idHallazgo").val();
	$("#datos").load("/sgp/hallazgos/getDatosHallazgo", {idHallazgo:idHallazgo, accion:"info"});
}

function getEstadosHallazgo(){
	$("#estadoHallazgo").html("");
	$("#estadoHallazgo").append("<option value=''>Seleccione un Estado</option>");
	var idHallazgo = $("#idHallazgo").val();
	jQuery.getJSON("/sgp/hallazgos/getEstadosHallazgo",{idHallazgo:idHallazgo}, function(data) {
		$.each(data, function(index, value){
			$("#estadoHallazgo").append('<option value="'+value.id+'">'+value.nombre+'</option>');
		});
	});

}

function fn_gestionarHallazgo(){

		var idanula = $("#causaAnula option:selected").val(),
	    textAnula =  $("#causaAnula option:selected").text(),
	    idMotivo = $("#motivoHallazgo option:selected").val(),
		idEstado = $("#estadoHallazgo option:selected").val(),
		causaAnulacion = null,
		motivo = null,
		estado = null;
	
		if(idanula != ""){
			causaAnulacion = {
					id: idanula,
					nombre:textAnula 
			};   
		}
	
		if(idMotivo != "")
			motivo = {id: idMotivo};				
		
		
		if(idEstado != "")
			estado = {id: idEstado};				
		
		var usuarioAsignado = {
				id : $("#asignar option:selected").val(),
				nombre :$("#asignar option:selected").text()
		};
	
		var hallazgodto = {
				id: $("#idHallazgo").val(),
				tipoHallazgo:{id: $("#tipoHallazgo").val()},
				causaAnulacion: causaAnulacion,
				motivoReasignacion: motivo
		};
	
		var flujohallazgodto = 
		{
				hallazgo : hallazgodto,
				usuarioAsignado: usuarioAsignado,
				estadoHallazgo :estado,
				observacion: $("#observacion").val()
		};
	
		$.ajax({
			url : "/sgp/hallazgos/editar",
			type : "post",
			data : JSON.stringify(flujohallazgodto),
			contentType : "application/json; charset=utf-8",
			async: true //false, alex
	
		}).done(function(data) {
			
			cargarSinModificar();
			cargar();
	
			if(idanula != "")
				$("#divCausaAnula").hide();
			
			fn_redirigir();
			loadingDialog.hideSgpLoading();
			fn_resultado(data);
	
		}).fail(function(jqXHR, textStatus, errorThrown){
			fn_error(jqXHR, textStatus, errorThrown);
		});
}

function fn_guardarAnexo(){

	loadingDialog.openSgpLoading();
	var formData = new FormData();


	var hallazgodto = {
			id: $("#idHallazgo").val()
	};


	formData.append("anexo", anexo.files[0]);
	formData.append("dto", JSON.stringify(hallazgodto));
	$.ajax({
		url : "/sgp/hallazgos/guardarAnexo",
		type : "post",
		data : formData,
		processData: false,
		contentType: false,
		async: true //false, alex

	}).done(function(data) {
		$("#adjuntar_anexo").button('reset');
		fn_getAnexosHallazgo();
		loadingDialog.hideSgpLoading();
		fn_resultadoAnexo(data);

	}).fail(function(jqXHR, textStatus, errorThrown){
		fn_error(jqXHR, textStatus, errorThrown);
	});

}

function fn_getSeguimientoHallazgo(callback){

	var idHallazgo = $("#idHallazgo").val();
	$("#detalle").load("/sgp/hallazgos/getDetalle",{idHallazgo:idHallazgo},function(){
		if(callback != null)
			callback();
	});
}

function fn_getAnexosHallazgo(){

	var idHallazgo = $("#idHallazgo").val();
	$("#anexos").load("/sgp/hallazgos/getAnexos",{idHallazgo:idHallazgo});
}

function fn_eliminar(id){

	$.ajax({
		url: "/sgp/hallazgos/eliminarAnexo",
		type: "post",
		data: {id:id},
		async: false
	}).done(function(data){
		$("#divConfirma").modal('hide');
		fn_getAnexosHallazgo();
		fn_resultado(data);
	});
}

function fn_modificarDatosHallazgo(){

	var causaAnulacion = null;
	if($("#causaAnula option:selected").val() != ""){
		causaAnulacion = {id:$("#causaAnula option:selected").val()};
	}
	var hallazgodto = {
			id: $("#idHallazgo").val(),
			severidad: {id: $("#severidad option:selected").val()},
			prioridad: {id: $("#prioridad option:selected").val()},
			tipoHallazgo: {id:$("#tipoHallazgo option:selected").val()},
			causaAnulacion: causaAnulacion,
			causaGeneracion: {id:$("#causaGeneracion option:selected").val()},
			titulo: $("#titulo").val()
	};

	$.ajax({
		url : "/sgp/hallazgos/modificarDatos",
		type : "post",
		data : JSON.stringify(hallazgodto),
		contentType : "application/json; charset=utf-8",
		async: false

	}).done(function(data) {
		cargarSinModificar();
		$("#show_edit").trigger("click");
		fn_resultado2(data);

	}).fail(function(jqXHR, textStatus, errorThrown){
		fn_error(jqXHR, textStatus, errorThrown);
	});

}

function fn_showEditarObservacion(idFlujo){
	$("#modal").load("/sgp/hallazgos/editObservacion",{idFlujo:idFlujo},function(){
		$(this).modal();
	});
}

/**
 * 
 */
function fn_editarObservacion(){
	var flujohallazgodto = {
			id:$("#idFlujoHallazgo").val(),
			observacion: $("#observacionFlujo").val()
	};

	$.ajax({
		url:"/sgp/hallazgos/editarObservacion",
		method: "post",
		data : JSON.stringify(flujohallazgodto),
		contentType : "application/json; charset=utf-8",
		async: false
	}).done(function(data) {
		$("#modal").modal('hide');
		fn_getSeguimientoHallazgo();
		fn_resultado2(data);

	}).fail(function(jqXHR, textStatus, errorThrown){
		$("#modal").modal('hide');
		fn_error(jqXHR, textStatus, errorThrown);
	});
}

function fn_redirigir(){
	$("#modal").load("/sgp/hallazgos/vistaVolver",function(){
		$(this).modal();
	});
	
	$("#modal").on('hidden.bs.modal', function() {
		loadingDialog.hideSgpLoading();
	});
	
}
function fn_marcarReasignado(){
	$("#divReasignaHallazgo").show();
	$("#divUsuarioHallazgo").show();
	$("#divEstadoHallazgo").hide();	
	$("#divCausaAnula").hide();
	$("#causaAnula option:selected").val("");
	$("#estadoHallazgo").val("");
	$("#motivoHallazgo").html("<option value=''>Seleccione un Motivo</option>");
	$("#motivoHallazgo").getParametrosPorTipoParametro(15);		
}

function fn_getFileSze() {
	
}
function ocultaUsuario(estado){
//	$("#departamento").attr("readonly",false);
	$.ajax({
    url: "/sgp/hallazgos/getEstadoNoSolicitaUsuario",
    type: "get",
    data: {estado:estado},
    dataType: "json",
    async: false
	}).done(function(data){
		if(data.data == true){
			$("#divUsuarioHallazgo").hide();
			$("#asignar option:selected").val("");
			$("#asignar option:selected").text("");
		}else{
			$("#divUsuarioHallazgo").show();
		}

	});
}

function fn_resultadoAnexo(data) {
	$('html, body').animate({
		scrollTop : '0px'
	}, 800);
	
	fn_resultado2(data);
}
