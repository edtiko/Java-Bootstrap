var ultimoCumple = null;
var idCaso = null;
var nuevoCumple = null;

$(document).ready(function() {
	// Funcion para cargar proyecto,mapa de pruebas,artefacto
	
	fn_cargar();
	getTipoHallazgo("0");
	
	// Evento BuscaProyectos
	$("#buscarProyecto").on("click", function() {
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
	
//	$("#modal_hallazgo").on('hidden.bs.modal', function(){});
	
});

function fn_columnFilter() {
	oTable.columnFilter({
		aoColumns : [ null, {
			type : "text"
		}, {
			type : "text"
		}, {
			type : "text"
		}, {
			type : "text"
		}, {
			type : null
		}, {
			type : null
		}, {
			type : "text"
		}, {
			type : null
		}

		]

	});
}

function fn_cargar() {
	oTable.fnSetColumnVis( 6,  false);
//	oTable.fnFilter(null, 6, true, false);
	
	oTable.fnSetColumnVis(2, false);
	oTable.fnFilter(null, 6, true);
}
/**
 * Filtra los casos de prueba por mapa de prueba
 * @param value
 */
function fn_filter(value) {
	oTable.fnFilter(value, 6, true, false);
}

function fn_showHallazgos(id) {
	//getHallazgosPorCasoPrueba(id);
	$("#modalHallazgo").empty().load("/sgp/hallazgos/list?idCasoPrueba="+id,function(){
		oModal.fnSetColumnVis( 4,  false);
		oModal.fnFilter(id, 4, true);
	});
	$("#modalHallazgo").modal();
}

function fn_ejecutarCaso(id,cumple) {
	var dtocasodepruebas = {
		id : id,
		cumple : cumple
	};
	
	$.ajax({
		url : "/sgp/casodepruebas/ejecutarCaso",
		type : "post",
		data : JSON.stringify(dtocasodepruebas),
		contentType : "application/json; charset=utf-8",
		async:false

	}).done(function(data) {
		fn_resultado2(data);
		if(cumple == 'No'){
			$("#modal_hallazgo").load("/sgp/hallazgos/reportarHallazgo/"+id);
			$("#modal_hallazgo").modal();
			}
		
	}).fail(function(jqXHR, textStatus, errorThrown) {
		fn_error(jqXHR, textStatus, errorThrown);
	});
	
	
}

/**
 * 
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
 * @date 14/05/2014 15:01:11
 * @description 
 * @param id
 * @param cumple
 * @param lastCumple
 */
function fn_ejecutarCaso2(id,cumple,lastCumple) {
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
		async:false
		
	}).done(function(data) {
		fn_resultado2(data);
		if(cumple == 'No'){
			$("#modal_hallazgo").load("/sgp/hallazgos/reportarHallazgo/"+id);
			$("#modal_hallazgo").modal({
				backdrop: 'static'
			});
		}
		
	}).fail(function(jqXHR, textStatus, errorThrown) {
		fn_error(jqXHR, textStatus, errorThrown);
	});
	
}

/**
 * 
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
 * @date 14/05/2014 16:37:34
 * @description 
 * @param id
 * @param cumple
 * @param ultimoCumple
 */
function fn_handleNoCumple(id,cumple,ultimoCumple) {
	idCaso = id;
	ultimoCumple = ultimoCumple;
	nuevoCumple = cumple;
	$("#modal_hallazgo").load("/sgp/ejecutarPrueba/checkNoCumple");
	$("#modal_hallazgo").modal();
}

/**
 * 
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
 * @date 14/05/2014 15:00:55
 * @description 
 * @param idCaso
 * @param ultimoCumple
 */
function fn_deshacerOperacion(idCaso,ultimoCumple) {
	
	if(idCaso != null) {
		if(ultimoCumple == 'null') {
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
			async:false

		}).done(function(data) {
			fn_resultado2(data);

		}).fail(function(jqXHR, textStatus, errorThrown) {
			fn_error(jqXHR, textStatus, errorThrown);
		});
		
		//$("#modal_hallazgo").modal('hide');
	}
	
}

function fn_show(id){
	
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