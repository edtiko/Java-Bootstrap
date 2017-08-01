var ultimoCumple = null;
var idCaso = null;
var nuevoCumple = null;
var r = {
	'special' : /[\W]/g
};


$(document)
		.ready(
				function() {

					restriccionesAnexo = null;
					var filesize = null;
					var fileExtension = null;
					var caracterEspecial=null;

					$('#fechaSolicitud').datetimepicker();

					fn_getRestriccionesAnexo().done(
							function(data) {
								restriccionesAnexo = data.data;

								$("#anexo").attr("accept",
										restriccionesAnexo.allowedfiles);

							}).fail(function(jqXHR, textStatus, errorThrown) {
						fn_error(jqXHR, textStatus, errorThrown);
					});

					jQuery.validator.addMethod("fileMaxSize", function(value,
							element, param) {
						return this.optional(element) || filesize < param[0];
					}, "El peso del archivo seleccionado no es permitido");
					
					jQuery.validator.addMethod("caracterEspecial", function(value,
							element, param) {
					   
						return this.optional(element) || caracterEspecial < param;
					}, "El caracter '<' no es permitido");
					
					getTipoHallazgo("1");

					// Evento BuscaProyectos
					$(document).on("click", "#buscarProyecto", function() {
						fn_getProyectos();
					});

					// Evento BuscaMapaDePruebas
					$("#proyecto").on("change", function() {

						var idProyecto = $(this).val();
					});
					// Evento BuscaCasosDePrueba
					$("#mapaPruebas").on("change", function() {

						fn_filter($("#mapaPruebas option:selected").text());
					});

					$(document).on("change", "#tipoHallazgo", function() {

						getTipoSeveridad($(this).val());
						getCausaGeneracion($(this).val());
						getTieneCausaGeneracion($(this).val());
						pideFechaSolicitud($('#tipoHallazgo option:selected').text());

					});

				

					$(document).on("change", "#severidad", function() {

						getTipoPrioridad($(this).val());

					});
					$(document).on("change", "#titulo", function() {

						caracterEspecial=ValidarCadenaExpReg($(this).val());

					});
					
					$(document).on("change", "#descripcion", function() {

						caracterEspecial=ValidarCadenaExpReg($(this).val());

					});
					// Evento Guardar
					$("#sv_reportarHallazgoManual").on("click", function() {
						if ($("#form_reportarHallazgoManual").valid()) {
							fn_guardar();
						}

					});
					$(document).on(	"change","#anexo",function() {
										if (this.files[0]) {
											filesize = (this.files[0].size) / 1024;

											$('#form_reportarHallazgoManual')
													.validate(
															{
																rules : {
																	"anexo" : {
																		accept : restriccionesAnexo.allowedfiles,
																		fileMaxSize : restriccionesAnexo.filesize
																	}
																},
																messages : {
																	anexo : {
																		accept : "El tipo de archivo seleccionado no es permitido",
																		fileMaxSize : "El peso del archivo seleccionado no es permitido"
																	}
																}
															});
											$("##form_reportarHallazgoManual").valid();
										}
									});
					/**
					 * Definir reglas para los campos
					 */

					validator = $('#form_reportarHallazgoManual')
							.validate(
									{
										rules : {
											"proyecto" : {
												required : true
											},		
											"artefacto" : {
												required : true
											},
											"tipoHallazgo" : {
												required : true
											},
											"causaGeneracion" : {
												required : true
											},
											"severidad" : {
												required : true
											},
											"prioridad" : {
												required : true
											},
											"descripcion" : {
												maxlength : 5000,
												required : true,
												caracterEspecial: true
											},
											"asignar" : {
												required : true
											},
											"fechaSolicitud" : {
												required : true
											},"titulo" : {
												required : true,
												caracterEspecial : true
											},
											
											"anexo" : {
												accept : restriccionesAnexo.allowedfiles,
												fileMaxSize : [ restriccionesAnexo.filesize ]
											}
										},
										messages : {
											proyecto : "Por favor seleccione un proyecto",
											artefacto : "Por favor seleccione el artefacto",
											proyecto : "Por favor seleccione un proyecto",
											tipoHallazgo : "Por favor seleccione un tipo de hallazgo",
											causaGeneracion : "Por favor seleccione la causa de generación",
											severidad : "Por favor seleccione la severidad",
											prioridad : "Por favor seleccione la prioridad",
											asignar : "Por favor seleccione un usuario",
											fechaSolicitud: "Por favor ingrese un valor en fecha solicitud",
											
											titulo :{
												required:"Por favor ingrese un título",
												caracterEspecial : "El carácter '<' no es permitido",
											},
											
											descripcion : {
												maxlength : "Este campo tiene un límite de {0} caracteres",
												required : "Por favor ingrese la descripción del hallazgo",
												caracterEspecial: "El carácter '<' no es permitido",
											},
											anexo : {
												accept : "El tipo de archivo seleccionado no es permitido",
												fileMaxSize : "El peso del archivo seleccionado no es permitido"
											}
										}
									});

				});

/**
 * Filtra los casos de prueba por mapa de prueba
 * 
 * @param value
 */
function fn_filter(value) {
	oTable.fnFilter(value, 6, true, false);
}

function fn_showHallazgos(id) {
	// getHallazgosPorCasoPrueba(id);
	$("#modalHallazgo").empty().load("/sgp/hallazgos/list?idCasoPrueba=" + id);
	$("#modalHallazgo").modal();
}

function fn_ejecutarCaso(id, cumple) {
	var dtocasodepruebas = {
		id : id,
		cumple : cumple
	};

	$.ajax({
		url : "/sgp/casodepruebas/ejecutarCaso",
		type : "post",
		data : JSON.stringify(dtocasodepruebas),
		contentType : "application/json; charset=utf-8",
		async : false

	}).done(function(data) {
		fn_resultado2(data);
		if (cumple == 'No') {
			$("#modal_hallazgo").load("/sgp/hallazgos/reportarHallazgo/" + id);
			$("#modal_hallazgo").modal();
		}

	}).fail(function(jqXHR, textStatus, errorThrown) {
		fn_error(jqXHR, textStatus, errorThrown);
	});

}

/**
 * 
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana
 *         Cardona</a>
 * @date 14/05/2014 15:01:11
 * @description
 * @param id
 * @param cumple
 * @param lastCumple
 */
function fn_ejecutarCaso2(id, cumple, lastCumple) {
	console.log("fn_ejecutarCaso2");
	idCaso = id;
	ultimoCumple = lastCumple;
	var dtocasodepruebas = {
		id : id,
		cumple : cumple
	};
	$.ajax({
		url : "/sgp/casodepruebas/ejecutarCaso",
		type : "post",
		data : JSON.stringify(dtocasodepruebas),
		contentType : "application/json; charset=utf-8",
		async : false

	}).done(function(data) {
		fn_resultado2(data);
		if (cumple == 'No') {
			$("#modal_hallazgo").load("/sgp/hallazgos/reportarHallazgo/" + id);
			$("#modal_hallazgo").modal({
				backdrop : 'static'
			});
		}

	}).fail(function(jqXHR, textStatus, errorThrown) {
		fn_error(jqXHR, textStatus, errorThrown);
	});

}

/**
 * 
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana
 *         Cardona</a>
 * @date 14/05/2014 16:37:34
 * @description
 * @param id
 * @param cumple
 * @param ultimoCumple
 */
function fn_handleNoCumple(id, cumple, ultimoCumple) {
	idCaso = id;
	ultimoCumple = ultimoCumple;
	nuevoCumple = cumple;
	$("#modal_hallazgo").load("/sgp/ejecutarPrueba/checkNoCumple");
	$("#modal_hallazgo").modal();
}

/**
 * 
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana
 *         Cardona</a>
 * @date 14/05/2014 15:00:55
 * @description
 * @param idCaso
 * @param ultimoCumple
 */
function fn_deshacerOperacion(idCaso, ultimoCumple) {

	if (idCaso != null) {
		if (ultimoCumple == 'null') {
			ultimoCumple = null;
		}
		var dtocasodepruebas = {
			id : idCaso,
			cumple : ultimoCumple
		};
		$.ajax({
			url : "/sgp/casodepruebas/ejecutarCaso",
			type : "post",
			data : JSON.stringify(dtocasodepruebas),
			contentType : "application/json; charset=utf-8",
			async : false

		}).done(function(data) {
			fn_resultado2(data);

		}).fail(function(jqXHR, textStatus, errorThrown) {
			fn_error(jqXHR, textStatus, errorThrown);
		});

		// $("#modal_hallazgo").modal('hide');
	}

}

function fn_show(id) {

	jQuery.getJSON("/sgp/casodepruebas/getCasoPrueba", {
		id : id
	}, function(data) {

		$("#modal_show #consecutivo").empty().text(data.consecutivo);
		$("#modal_show #id_caso").empty().text(data.id);
		$("#modal_show #descripcion").empty().text(data.descripcion);
		$("#modal_show #fec_creacion").empty().text(data.fechaCreacionString);
		$("#modal_show #usu_crea").empty().text(data.usuarioCreacion);
		$("#modal_show #fec_mod").empty().text(data.fechaEditaString);
		$("#modal_show #usu_mod").empty().text(data.usuarioEdita);
		$("#modal_show #fec_ejecuta").empty().text(data.fechaEjecutaString);
		$("#modal_show #usu_ejecuta").empty().text(data.usuarioEjecuta);

	});

	$("#modal_show").modal();
}
function fn_seleccionProyecto(id) {
	$.getJSON("/sgp/proyectos/getProyecto", {
		id : id
	}, function(data) {
		$("#proyecto").val(data.nombre);
		$("#proyecto").attr("data-id", data.id);
		$("#modalProyecto").modal("hide");
		$("#proyecto").closest('.form-group').removeClass('has-error');
		$(".data-proyecto span").remove();
		$("#proyecto").trigger('change');
	}).done(function() {
		if (document.getElementById("artefacto") != null) {
			var idProyecto = $("#proyecto").attr("data-id");
			getArtefactoPorProyecto(idProyecto);
		}
		cargarUsuariosAsociados(idProyecto);

	});
}
function getArtefactoPorProyecto(idProyecto) {
	$.ajax({
		url : "/sgp/reportarhallazgomanual/artefacto",
		type : "get",
		data : {
			idProyecto : idProyecto
		},
		dataType : "json",
		async : false
	}).done(
			function(data) {
				$("#artefacto").html("");
				$("#artefacto").append(
						'<option value="">Seleccione un Artefacto</option>');
				$.each(data, function(index, value) {

					$("#artefacto").append(
							'<option value="' + value.id + '">' + value.nombre
									+ '</option>');
				});
			});
}

function fn_guardar() {

	var formData = new FormData();
	var causaGeneracions = $("#causaGeneracion option:selected").val();

	var usuarioAsignado = {
		id : $("#asignar option:selected").val(),
		nombre : $("#asignar option:selected").text()
	};

	var hallazgodto = {

		artefacto : {
			id : $("#artefacto option:selected").val()
		},
		tipoHallazgo : {
			id : $("#tipoHallazgo option:selected").val()
		},
		causaGeneracion : {
			id : causaGeneracions
		},
		severidad : {
			id : $("#severidad option:selected").val()
		},
		prioridad : {
			id : $("#prioridad option:selected").val()
		},
		titulo : $("#titulo").val(),
		descripcion : $("#descripcion").val(),
		fechaSolicitudString : $("#fechaSolicitud").val()

	};

	var flujohallazgodto = {

		hallazgo : hallazgodto,
		usuarioAsignado : usuarioAsignado
	};

	formData.append("anexo", anexo.files[0]);
	formData.append("dto", JSON.stringify(flujohallazgodto));
	$.ajax({
		url : "/sgp/hallazgos/guardar",
		type : "post",
		data : formData,
		processData : false,
		contentType : false,
		async : true

	}).done(function(data) {
		fn_resultado(data);
		if(data.status!="FAIL"){
			clearFormReportarHallazgoManual();
		}

	}).fail(function(jqXHR, textStatus, errorThrown) {
		fn_error(jqXHR, textStatus, errorThrown);
	});

}
function clearFormReportarHallazgoManual() {
	clearFormValidate();
	$("#form_reportarHallazgoManual").reset();
	$("#edit_tipoSeveridad").hide();
	$("#sv_tipoSeveridad").show();
	
	getTipoHallazgo("1");
	getArtefactoPorProyecto("0");
	getCausaGeneracion("0");
	getTipoSeveridad("0");
	getTipoPrioridad("0");
}

/**
 * valida caracteres especiales
 * 
 * @param o
 * @param w
 */
function valid(o, w) {
//	o.value = o.value.replace(r[w], '');
}




function pideFechaSolicitud(tipoHallazgo){
	
	$("#tipoHallazgo").attr("readonly",false);
	$.ajax({
    url: "/sgp/utils/parametro",
    type: "get",
    dataType: "json",
    async: false
	}).done(function(data){
		$.each(data, function(index, value){
		if(value == tipoHallazgo){
			
			$("#divFechaSolicitud").show();
			return false;
		}else{

			$("#divFechaSolicitud").hide();
			
		}
		});
	});
}

function ValidarCadenaExpReg(dato) {
    // Expresion regular que representa un Email válido
	 cadena = "(<)+"; 
	    re = new RegExp(cadena);
if(dato.match(re)){
	caracterEspecial=true;
}else{
	caracterEspecial=false;
}
     return caracterEspecial;
}





