/**
 * Archivo Utils que contiene Funcionalides Utiles para toda la aplicación
 */
$(document).ready(function() {

	var timeout = null;

	$(document).on('mousemove', function() {
		if (timeout !== null) {

			clearTimeout(timeout);
		}

		timeout = setTimeout(function() {
			ajaxSessionTimeout();
		}, 3300000);
	});

	$('#timeOut').on('hidden.bs.modal', function(e) {
		window.location = "/sgp/logout";
	});

	// jQuery.validator.addMethod("fileMaxSize", function(value, element, param)
	// {
	// var fileSize, maxFileSize;
	// fileSize = param[0];
	// maxFileSize = param[1];
	// return this.optional(element) || fileZise < maxFileSize;
	// }, "El peso del archivo no es permitido");

});

function ajaxSessionTimeout() {
	$('#timeOut').load("/sgp/timeout");
	$('#timeOut').modal('toggle');
}

// Traducción al español calendario jQueryUI
$(function($) {
	$.datepicker.regional['es'] = {
		closeText : 'Cerrar',
		prevText : '<Ant',
		nextText : 'Sig>',
		currentText : 'Hoy',
		monthNames : [ 'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
				'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre',
				'Diciembre' ],
		monthNamesShort : [ 'Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul',
				'Ago', 'Sep', 'Oct', 'Nov', 'Dic' ],
		dayNames : [ 'Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves',
				'Viernes', 'Sábado' ],
		dayNamesShort : [ 'Dom', 'Lun', 'Mar', 'Mié', 'Juv', 'Vie', 'Sáb' ],
		dayNamesMin : [ 'Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sá' ],
		weekHeader : 'Sm',
		dateFormat : 'yy/mm/dd',
		firstDay : 1,
		isRTL : false,
		showMonthAfterYear : false,
		yearSuffix : ''
	};
	$.datepicker.setDefaults($.datepicker.regional['es']);
});
$.fn.dataTableExt.oApi.fnResetAllFilters = function(oSettings, bDraw/*
																	 * default
																	 * true
																	 */) {
	for (iCol = 0; iCol < oSettings.aoPreSearchCols.length; iCol++) {
		oSettings.aoPreSearchCols[iCol].sSearch = '';
	}
	oSettings.oPreviousSearch.sSearch = '';

	if (typeof bDraw === 'undefined')
		bDraw = true;
	if (bDraw)
		this.fnDraw();
}
jQuery.fn.reset = function() {
	$(this).each(function() {
		this.reset();
	});
};

jQuery.fn.clear = function() {
	$(this).each(function() {
		$(this).text("");
	});
};

/*
 * @author Edwin Gómez @desc limpia los filtros que salen con la grilla
 */
function fn_clearFilters() {

	$("tfoot input").each(function(i) {
		$(this).val(asInitVals[$("tfoot input").index(this)]);
		$(this).addClass('form-control input-min search_init');
	});

}
/**
 * @author Edwin Gómez
 * @desc Sobreescribe un metodo para usar Bootstrap en las validaciones
 * @since 30/01/2014
 */
// override jquery validate plugin defaults
$.validator.setDefaults({
	highlight : function(element) {
		$(element).closest('.form-group').addClass('has-error');
	},
	unhighlight : function(element) {
		$(element).closest('.form-group').removeClass('has-error');
	},
	errorElement : 'span',
	errorClass : 'help-block',
	errorPlacement : function(error, element) {
		if (element.parent('.input-group').length) {
			error.insertAfter(element.parent());
		} else {
			error.insertAfter(element);
		}
	}
});

/**
 * @author Edwin Gómez
 * @desc Limpia los errores de validación en el formulario
 * @since 20/02/2014
 */
function clearFormValidate() {
	validator.resetForm();
	$('.form-group').each(function() {
		$(this).removeClass('has-error');
	});
}

/**
 * 
 * @author Edwin Gómez - Premize SAS
 * @desc
 * @since 27/01/2014
 */
function getTiposParametros() {
	jQuery.getJSON("/sgp/utils/tiposParametro", function(data) {

		$.each(data, function(index, value) {
			$("#tipoParametro").append(
					'<option value="' + value.id + '">' + value.nombre
							+ '</option>');
		});
	});
}

/**
 * @author Edwin Gómez
 * @desc carga los usuarios asociados al proyecto
 * @param idProyecto
 */
function cargarUsuariosAsociados(idProyecto) {

	jQuery.getJSON("/sgp/proyectos/obtenerUsuariosAsociados", {
		idProyecto : idProyecto
	},
			function(data) {
				$("#asignar").html("");
				$("#asignar").append(
						'<option value="">Seleccione un Usuario</option>');
				$.each(data, function(index, value) {
					$("#asignar").append(
							'<option value="' + value.id + '">' + value.nombre
									+ '</option>');
				});
			});
}

/**
 * 
 * @author Gustavo Soto - Premize SAS
 * @desc
 * @since 29/01/2014
 */
function getPaises() {
	jQuery.getJSON("/sgp/utils/paises", function(data) {

		$("#pais").append('<option value="">Seleccione un país</option>');
		$.each(data, function(index, value) {
			$("#pais").append(
					'<option value="' + value.id + '">' + value.nombre
							+ '</option>');
		});
	});
}

/**
 * 
 * @author Gustavo Soto - Premize SAS
 * @desc
 * @since 29/01/2014
 */
function getDepartamentos(idPais) {
	$("#departamento").attr("readonly", false);
	$
			.ajax({
				url : "/sgp/utils/departamentos",
				type : "get",
				data : {
					idPais : idPais
				},
				dataType : "json",
				async : false
			})
			.done(
					function(data) {

						$("#departamento").html("");
						$("#departamento")
								.append(
										'<option value="">Seleccione un departamento</option>');
						$.each(data, function(index, value) {

							$("#departamento").append(
									'<option value="' + value.id + '">'
											+ value.nombre + '</option>');
						});
					});
}

/**
 * 
 * @author Gustavo Soto - Premize SAS
 * @desc
 * @since 29/01/2014
 */
function getCiudades(idDepartamento) {

	$.ajax({
		url : "/sgp/utils/ciudades",
		type : "get",
		data : {
			idDepartamento : idDepartamento
		},
		dataType : "json",
		async : false
	}).done(
			function(data) {
				$("#ciudad").attr("readonly", false);
				$("#ciudad").html("");
				$("#ciudad").append(
						'<option value="">Seleccione una ciudad</option>');
				$.each(data, function(index, value) {

					$("#ciudad").append(
							'<option value="' + value.id + '">' + value.nombre
									+ '</option>');
				});
			});
}

/**
 * 
 * @author Gustavo Soto - Premize SAS
 * @desc
 * @since 04/02/2014
 */
function getEmpresasPorCiudad(idCiudad) {
	$.ajax({
		url : "/sgp/utils/empresas",
		type : "get",
		data : {
			idCiudad : idCiudad
		},
		dataType : "json",
		async : false
	}).done(
			function(data) {

				$("#empresa").html("");
				$("#empresa").append(
						'<option value="">Seleccione una empresa</option>');
				$.each(data, function(index, value) {

					$("#empresa").append(
							'<option value="' + value.id + '">' + value.nombre
									+ '</option>');
				});
			});
}
/**
 * 
 * @author Gustavo Soto - Premize SAS
 * @desc
 * @since 04/02/2014
 */
function getProyectoPorEmpresa(idEmpresa) {
	$.ajax({
		url : "/sgp/utils/proyectos",
		type : "get",
		data : {
			idEmpresa : idEmpresa
		},
		dataType : "json",
		async : false
	}).done(
			function(data) {

				$("#proyecto").html("");
				$("#proyecto").append(
						'<option value="">Seleccione un proyecto</option>');
				$.each(data, function(index, value) {

					$("#proyecto").append(
							'<option value="' + value.id + '">' + value.nombre
									+ '</option>');
				});
			});
}

/**
 * 
 * @author Gustavo Soto - Premize SAS
 * @desc
 * @since 04/02/2014
 */
function getMapaPruebasPorProyecto(idProyecto) {
	$
			.ajax({
				url : "/sgp/utils/mapaPruebas",
				type : "get",
				data : {
					idProyecto : idProyecto
				},
				dataType : "json",
				async : false
			})
			.done(
					function(data) {
						$("#mapaPruebas").attr("readonly", false);
						$("#mapaPruebas").html("");
						$("#mapaPruebas")
								.append(
										'<option value="">Seleccione un Mapa de Pruebas</option>');
						$.each(data, function(index, value) {

							$("#mapaPruebas").append(
									'<option value="' + value.id + '">'
											+ value.nombre + '</option>');
						});
					});
}

/**
 * 
 * @author Gustavo Soto - Premize SAS
 * @desc
 * @since 04/02/2014
 */
function getArtefactoPorMapaPruebas(idMapaPruebas) {
	$.ajax({
		url : "/sgp/utils/artefactoActivoMapaPruebas",
		type : "get",
		data : {
			idMapaPruebas : idMapaPruebas
		},
		dataType : "json",
		async : false
	}).done(
			function(data) {
				$("#artefacto").attr("readonly", false);
				$("#artefacto").html("");
				$("#artefacto").append(
						'<option value="">Seleccione un Artefacto</option>');
				$.each(data, function(index, value) {

					$("#artefacto").append(
							'<option value="' + value.artefacto.id + '">'
									+ value.artefacto.nombre + '</option>');
				});
			});
}

/**
 * 
 * @author Gustavo Soto - Premize SAS
 * @desc
 * @since 04/02/2014
 */
function makeMenuBar() {

	jQuery
			.getJSON(
					"/sgp/menubar/getMenu",
					function(menu) {

						$('#login').append(
								"<strong>" + menu.usuario + "</strong>")
								.append('<b class="caret"></b>');
						$
								.each(
										menu.opcion,
										function(i, v) {

											$('#menuBar')
													.append(
															'<li class="dropdown" id="dropdown'
																	+ i
																	+ '">'
																	+ '<a href="#" class="dropdown-toggle" data-toggle="dropdown"><strong>'
																	+ menu.opcion[i].nombre
																	+ '</strong><b class="caret"></b>');
											$('#dropdown' + i).append(
													'<ul class="dropdown-menu" id="mdrown'
															+ i + '" >');

											$
													.each(
															menu.opcion[i].listFuncionalidad,
															function(a, c) {
																$('#mdrown' + i)
																		.append(
																				'<li><a href="'
																						+ menu.opcion[i].listFuncionalidad[a].url
																						+ '#">'
																						+ menu.opcion[i].listFuncionalidad[a].nombre
																						+ '');

															});

										});
					});
}

function fn_getEmpresas() {
	$("#modalEmpresa").load('/sgp/empresas/list');
	$("#modalEmpresa").modal();

}
function fn_getProyectos() {
	$("#modalProyecto").load('/sgp/proyectos/list');
	$("#modalProyecto").modal();

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
		if (document.getElementById("mapaPruebas") != null) {
			var idProyecto = $("#proyecto").attr("data-id");
			getMapaPruebasPorProyecto(idProyecto);
		}

	});
}

function fn_seleccionEmpresa(id) {

	$.getJSON("/sgp/empresas/obtenerEmpresa", {
		id : id
	}, function(data) {
		$("#empresa").val(data.nombre + " - " + data.ciudad.nombre);
		$("#empresa").attr("data-id", data.id);
		$("#modalEmpresa").modal("hide");
		$("#empresa").closest('.form-group').removeClass('has-error');
		$(".data-empresa span").remove();

	});
}
/**
 * @author Edwin Gómez
 * @desc Gestiona el resultado de una petición ajax
 * @param data
 */
function fn_resultado(data) {
	$('html, body').animate({
		scrollTop : '0px'
	}, 800);
	if (data.status == "SUCCESS") {
		$("form").reset();
	}
	fn_resultado2(data);
}

function fn_resultado2(data) {
	if (data.status == "SUCCESS") {

		$("#msg ul").html("<li>" + data.result + "</li>");
		$("#alerta").removeClass("alert-danger");
		$("#alerta").addClass("alert-success");
		$("#alerta").show();
		$("#alerta").delay(200).addClass("in").fadeOut(10000);
		// oTable.fnDraw();
		try {
			oTable.fnDraw();
		} catch (ex) {
			console.log(ex.toString());
		}

	} else {
		$("#msg ul").html("");
		if ($.isArray(data.result)) {
			$.each(data.result, function(i, v) {
				$("#msg ul").append("<li>" + data.result[i] + "</li>");
			});
		} else {
			$("#msg ul").append("<li>" + data.result + "</li>");
		}
		$("#alerta").removeClass("alert-success");
		$("#alerta").addClass("alert-danger");
		$("#alerta").show();
		$("#alerta").delay(200).addClass("in").fadeOut(20000);
	}
}

/**
 * @author Edwin Gómez
 * @desc Gestiona los errores de una petición ajax
 * @param data
 */
function fn_error(jqXHR, textStatus, errorThrown) {
	$("#msg ul").html("<li>" + errorThrown + "</li>");
	$("#alerta").removeClass("alert-success");
	$("#alerta").addClass("alert-danger");
	$("#alerta").show();
	$("#alerta").delay(200).addClass("in").fadeOut(4000);

}
function fn_info(jqXHR, textStatus, errorThrown) {
	$("#msg ul").html("<li>" + errorThrown + "</li>");
	$("#alerta").removeClass("alert-success");
	$("#alerta").addClass("alert-info");
	$("#alerta").show();
	$("#alerta").delay(200).addClass("in").fadeOut(4000);

}
var singleSelect = true; // Permite que un elemento a seleccionar una sola
							// vez
var sortSelect = true; // Solo es efectivo si la bandera por encima el valor
						// true
var sortPick = true; // Ordenara la lista de selección en la secuencia de
						// ordenacion
/**
 * @author Daniel Saavedra - Premize SAS
 * @desc Añade un elemento seleccionado en la lista de selección
 * @since 08/02/2014
 */
function addIt() {
	var selectList = document.getElementById("backlog");
	var selectIndex = selectList.selectedIndex;
	var selectOptions = selectList.options;
	var pickList = document.getElementById("picklist");
	var pickOptions = pickList.options;
	var pickOLength = pickOptions.length;
	// Un elemento debe seleccionarse
	while (selectIndex > -1) {
		pickOptions[pickOLength] = new Option(selectList[selectIndex].text);
		pickOptions[pickOLength].title = selectList[selectIndex].text;
		pickOptions[pickOLength].value = selectList[selectIndex].value;
		// Si sola selección, eliminar el elemento de la lista de selección
		if (singleSelect) {
			selectOptions[selectIndex] = null;
		}
		if (sortPick) {
			var tempText;
			var tempValue;
			// Ordenar la lista de selección
			while (pickOLength > 0
					&& pickOptions[pickOLength].value < pickOptions[pickOLength - 1].value) {
				tempText = pickOptions[pickOLength - 1].text;
				tempValue = pickOptions[pickOLength - 1].value;
				pickOptions[pickOLength - 1].text = pickOptions[pickOLength].text;

				pickOptions[pickOLength - 1].value = pickOptions[pickOLength].value;
				pickOptions[pickOLength].text = tempText;
				pickOptions[pickOLength].value = tempValue;
				pickOLength = pickOLength - 1;
			}
		}
		selectIndex = selectList.selectedIndex;
		pickOLength = pickOptions.length;
	}
	selectOptions[0].selected = true;
}

/**
 * @author Daniel Saavedra - Premize SAS
 * @desc Elimina un elemento de la lista de selección
 * @since 08/02/2014
 */
function delIt() {
	var selectList = document.getElementById("backlog");
	var selectOptions = selectList.options;
	var selectOLength = selectOptions.length;
	var pickList = document.getElementById("picklist");
	var pickIndex = pickList.selectedIndex;
	var pickOptions = pickList.options;
	while (pickIndex > -1) {
		// Si solo la selección, vuelva a colocar el elemento en la lista de
		// selección
		if (singleSelect) {
			selectOptions[selectOLength] = new Option(pickList[pickIndex].text);
			selectOptions[selectOLength].title = pickList[pickIndex].text;
			selectOptions[selectOLength].value = pickList[pickIndex].value;
		}
		pickOptions[pickIndex] = null;
		if (singleSelect && sortSelect) {
			var tempText;
			var tempValue;
			// Vuelva a ordenar la lista de selección
			while (selectOLength > 0
					&& selectOptions[selectOLength].value < selectOptions[selectOLength - 1].value) {
				tempText = selectOptions[selectOLength - 1].text;
				tempValue = selectOptions[selectOLength - 1].value;
				selectOptions[selectOLength - 1].text = selectOptions[selectOLength].text;
				selectOptions[selectOLength - 1].title = selectOptions[selectOLength].text;
				selectOptions[selectOLength - 1].value = selectOptions[selectOLength].value;
				selectOptions[selectOLength].text = tempText;
				selectOptions[selectOLength].title = tempText;
				selectOptions[selectOLength].value = tempValue;
				selectOLength = selectOLength - 1;
			}
		}
		pickIndex = pickList.selectedIndex;
		selectOLength = selectOptions.length;
	}
}
/**
 * 
 * @author Daniel Saavedra - Premize SAS
 * @desc Elimina Todos los elementos de la Lista de Seleccion
 * @since 8/02/2014
 */
function dellAll() {
	$('#picklist option').each(function() {
		$(this).remove().appendTo('#backlog');
	});
}

/**
 * Aniade Todos los elementos a la lista de Seleccion
 * 
 * @author Daniel Saavedra - Premize SAS
 * @desc
 * @since 8/02/2014
 */
function addAll() {
	$('#backlog option').each(function() {
		$(this).remove().appendTo('#picklist');
	});
}

/**
 * @author Edwin Gómez
 * @desc Obtiene los parametros de un tipo de parametro en una lista
 */
jQuery.fn.getParametrosPorTipoParametro = function(idTipoParametro, callback) {
	var elemento = $(this);
	$.ajax({
		url : "/sgp/utils/parametroPorTiposParametro",
		  dataType: 'json',
		  data: {id:idTipoParametro },
		  async: false
		
	}).done(function(data){
		
		$.each(data, function(index, value){
			$(elemento).append('<option value="'+value.id+'">'+value.nombre+'</option>');
		});
		
		if(callback != null)
		  callback();
	});
};

/**
 * @author Edwin Gómez
 * @desc Obtiene los tipos de artefacto en una lista
 */
function getTiposArtefacto() {
	jQuery.getJSON("/sgp/tiposartefactos/obtenerTipos", function(data) {

		$.each(data, function(index, value) {
			$("#tipoArtefacto").append(
					'<option value="' + value.id + '">' + value.nombre
							+ '</option>');
		});

	});
}

/**
 * @author Carolina Diez
 * @desc Obtiene los usuarios
 */
function fn_getUsuarios(idProyecto) {
	$("#modalUser").empty().load('/sgp/usuarios/list');
	$("#modalUser").modal();
}

function fn_seleccionUsuario(id) {
	$.getJSON("/sgp/usuarios/obtenerUsuario", {
		id : id
	}, function(data) {
		$("#usuario").val(data.nombre);
		$("#usuario").attr("data-id", data.id);
		$("#modalUser").modal("hide");
		$("#usuario").closest('.form-group').removeClass('has-error');
		$(".data-usuario span").remove();
		$("#usuario").trigger('change');
	});
}

/**
 * 
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana
 *         Cardona</a>
 * @date 15/05/2014 16:59:43
 * @description
 */
var loadingDialog;
loadingDialog = loadingDialog || (function() {
	var container = $("<div id='div_container' title='Procesando...' ></div>");
	var sgpProcesando = $(container).load("/sgp/utils/loadingDialog");

	container.dialog({
		autoOpen : false,
		closeOnEscape : true,
		open : function(event, ui) {
			$(".ui-dialog-titlebar-close").hide();
		},
		modal : true,
		resizable : false
	});
	
	return {
		showSgpLoading : function() {
			container.dialog("open");

		},
		hideSgpLoading : function() {
			container.dialog("close");

		},
		openSgpLoading : function() {
			container.dialog("open");
		}

	};
})();

/*
 * Muestra el dialog procesando en pantalla cuando una peticion ajax inicia
 */
$(document).ajaxStart(function() {
	loadingDialog.showSgpLoading();
});

/*
 * Cierra el dialog procesando cuando la peticion ajax finaliza.
 */
$(document).ajaxStop(function() {
	loadingDialog.hideSgpLoading();
});

/**
 * 
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana
 *         Cardona</a>
 * @date 28/05/2014 12:08:37
 * @description Consulta el peso o tamanio maximo permitido para un archivo que
 *              se desea adjuntar como anexo.
 */
function fn_getMaxSizeAnexo() {
	var ajax = $.ajax({
		url : "/sgp/hallazgos/getMaxSizeAnexo",
		type : "get",
		contentType : "application/json charset=utf-8"
	});

	return ajax;
}

/**
 * 
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana
 *         Cardona</a>
 * @date 29/05/2014 14:35:28
 * @description
 * @returns
 */
function fn_getRestriccionesAnexo() {
	var ajax = $.ajax({
		async : false,
		url : "/sgp/hallazgos/getRestriccionesAnexo",
		type : "get",
		contentType : "application/json charset=utf-8"
	});

	return ajax;
}
/**
 * 
 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
 * @date 11/06/2014 14:35:28
 * @description
 * @returns
 */
function fn_getRestriccionesLogo() {
	var ajax = $.ajax({
		url : "/sgp/empresas/getRestriccionesLogo",
		type : "get",
		contentType : "application/json charset=utf-8"
	});

	return ajax;
}

/**
 * 
 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
 * @date 17/06/2014 14:35:28
 * @description
 * @returns
 */
function getTipoHallazgo(tipoIngresoHallazgo) {

	$.ajax({
		url : "/sgp/utils/tipoHallazgos",
		type : "get",
		data : {
			tipoIngresoHallazgo : tipoIngresoHallazgo
				},
		dataType : "json",
		async : false
	})
	.done(function(data) {
		$("#tipoHallazgo").html("");
		$("#tipoHallazgo").append(
				'<option value="">Seleccione un Tipo de Hallazgo</option>');
		$.each(data, function(index, value) {
			$("#tipoHallazgo").append(
					'<option value="' + value.id + '">' + value.nombre
							+ '</option>');
		});
	});
}

/**
 * 
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana
 *         Cardona</a>
 * @date 9/07/2014 18:03:28
 * @description
 */
function getTiposHallazgo() {
	jQuery.getJSON("/sgp/utils/tiposHallazgo", function(data) {
		$("#tipoHallazgo").html("");

		$("#tipoHallazgo").append(
				'<option value="">Seleccione un Tipo de Hallazgo</option>');
		$.each(data, function(index, value) {
			$("#tipoHallazgo").append(
					'<option value="' + value.id + '">' + value.nombre
							+ '</option>');
		});
	});
}

/**
 * 
 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
 * @date 17/06/2014 14:35:28
 * @description
 * @returns
 */
function getCausaGeneracion(idTipoHallazgo) {
	$("#causaGeneracion").attr("readonly", false);
	$("#causaGeneracion").html("");
	$("#causaGeneracion").append('<option value="0">Seleccione una Causa de Generación</option>');
	$.ajax({url : "/sgp/utils/causaGeneracion",
			type : "get",
			data : {
					idTipoHallazgo : idTipoHallazgo
					},
				dataType : "json",
				async : false
			}).done(
					function(data) {

						$("#causaGeneracion").html("");
						$("#causaGeneracion").append('<option value="0">Seleccione una Causa de Generación</option>');
						$.each(data, function(index, value) {

							$("#causaGeneracion").append(
									'<option value="' + value.id + '">'
											+ value.nombre + '</option>');
						});
					});
}
/**
 * 
 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
 * @date 17/06/2014 14:35:28
 * @description
 * @returns
 */
function getTipoPrioridad(idTipoSeveridad) {
	$("#prioridad").attr("readonly", false);
	$
			.ajax({
				url : "/sgp/utils/tipoPrioridad",
				type : "get",
				data : {
					idTipoSeveridad : idTipoSeveridad
				},
				dataType : "json",
				async : false
			})
			.done(
					function(data) {

						$("#prioridad").html("");
						$("#prioridad")
								.append(
										'<option value="">Seleccione un Tipo de Prioridad</option>');
						$.each(data, function(index, value) {

							$("#prioridad").append(
									'<option value="' + value.id + '">'
											+ value.nombre + '</option>');
						});
					});
}
/**
 * 
 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
 * @date 17/06/2014 14:35:28
 * @description
 * @returns
 */
function getTipoSeveridad(idTipoHallazgo) {
	$("#severidad").attr("readonly", false);
	$
			.ajax({
				url : "/sgp/utils/tipoSeveridad",
				type : "get",
				data : {
					idTipoHallazgo : idTipoHallazgo
				},
				dataType : "json",
				async : false
			})
			.done(
					function(data) {

						$("#severidad").html("");
						$("#severidad").append('<option value="">Seleccione un Tipo de Severidad</option>');
						$.each(data, function(index, value) {
							$("#severidad").append('<option value="' + value.id + '">'+ value.nombre + '</option>');
						});
					});
}

function getTieneCausaGeneracion(idTipoHallazgo) {

	if(idTipoHallazgo != ""){
	$("#tipoHallazgo").attr("readonly", false);
	$.ajax({
				url : "/sgp/tipoHallazgo/getTipoHallazgo",
				type : "get",
				data : {
					id : idTipoHallazgo
				},
				dataType : "json",
				async : false
			})
			.done(
					function(data) {

						if (data.numeroTieneCausaGeneracion == 1) {

							$("#divCausaGeneracion").show();
						} else {
							$("#causaGeneracion").val(0);
							$("#divCausaGeneracion").hide();
							fn_info(
									"Prueba",
									"Prueba",
									"El tipo de hallazgo seleccionado tiene configurado no permitir crear una causa de generación");
						}

					});
	}
	else{
		$("#divCausaGeneracion").show();
	}
}

jQuery.fn.getCausaGeneracionPorTipoHallazgo = function(idTipoHallazgo, callback) {
	var elemento = $(this);
	$.ajax({
		url : "/sgp/utils/causaGeneracion",
		  dataType: 'json',
		  data: {idTipoHallazgo:idTipoHallazgo },
		  async: false
		
	}).done(function(data){
		
		$.each(data, function(index, value){
			$(elemento).append('<option value="'+value.id+'">'+value.nombre+'</option>');
		});
		
		if(callback != null)
		  callback();
	});
};
function validarFecha(fecha,campo){
	var rgx = /(\d{4})\/(\d{2})\/(\d{2})/;
	var s = fecha;
	var m = s.match(rgx);
if(m==null){

//	fn_error("jqXHR", "001", campo+" no es válido");
//	
	
	return false;
}
return true;
}