$(document).ready(function(){
	getTiposHallazgo();
	$("#causaGenera").getParametrosPorTipoParametro(4);
	$("#causaAnula").getParametrosPorTipoParametro(5);
	$("#fechaFrom").datepicker();
	$("#fechaTo").datepicker();

	//oculta algunas columnas de la grilla
	oTable.fnSetColumnVis( 9,  false);
	oTable.fnSetColumnVis( 10,  false);
	oTable.fnSetColumnVis( 11,  false);
	oTable.fnSetColumnVis( 12,  false);
	oTable.fnSetColumnVis( 13,  false);
	oTable.fnSetColumnVis( 14,  false);
	oTable.fnSetColumnVis( 15,  false);

	// Evento BuscaProyectos
	$("#buscarProyecto").on("click", function() {
		fn_getProyectos();

	});
	
	// Evento Busca Usuarios Asigna
	$("#buscarUsuario").on("click", function() {
		fn_getUsuarios();
	});

	$("#proyecto").on("change", function() {
		oTable.fnFilter($(this).val(),9,true);

	});

	$("#mapaPruebas").on("change", function() {
		
		var obj = $("#mapaPruebas option:selected");
		
		if ( obj.val() == "" ){
			oTable.fnFilter("",10,true);
		} else {
			oTable.fnFilter(obj.text(),10,true);
		}	
	});

	$("#causaGeneracion").on("change", function() {
		
		var obj = $("#causaGeneracion option:selected");
		
		if ( obj.val() == "" ){
			oTable.fnFilter("",12,true);
		} else {
			oTable.fnFilter(obj.val(),12,true);
		}
	});
	

	
	$('#tipoHallazgo').change(function(){
		getCausaGeneracion($(this).val());
		var obj = $("#tipoHallazgo option:selected");
		
		if ( obj.val() == "" ){
			oTable.fnFilter("",1,true);
		} else {
			oTable.fnFilter(obj.val(),1,true);
		}
	});
	
	$("#causaAnula").on("change", function() {
		
		var obj = $("#causaAnula option:selected");
		
		if ( obj.val() == "" ){
			oTable.fnFilter("",11,true);
		} else {
			oTable.fnFilter(obj.val(),11,true);
		}
	});
	
	// Busca por usuario asigna
	$("#usuario").on("change", function() {
		var usuarioAsigna = $("#usuario").attr("data-id");
		oTable.fnFilter(usuarioAsigna,13,true);
	});

	$("#fechaFrom").on("change", function() {
		var valor = $("#fechaFrom").val();
		oTable.fnFilter(valor,14,true);
	});
	$("#fechaTo").on("change", function() {
		var valor = $("#fechaTo").val();
		oTable.fnFilter(valor,15,true);
	});
	
	$("button:reset").on("click", function(){
		
		$("#mapaPruebas").html("");
		$("#mapaPruebas").append("<option value=''>Seleccione un Mapa de Pruebas</option>");
		
		$("#causaGeneracion").html("");
		$("#causaGeneracion").append("<option value='0'>Seleccione una Causa de Generación</option>");
		
		oTable.fnResetAllFilters();
		fn_clearFilters();
	});
	
	$("#frmHallazgo").submit(function () {
		console.log("estado filtro = "+$("#cFiltroEstado").val());
		var $filtro;
		
		$filtro = $("#cFiltroId");
		if($filtro.val() != $filtro.attr("placeholder"))
			$("#gf_idHallazgo").val($("#cFiltroId").val());
		
		$filtro = $("#cFiltroArtefacto");
		if($filtro.val() != $filtro.attr("placeholder"))
			$("#gf_artefacto").val($("#cFiltroArtefacto").val());
		
		$filtro = $("#cFiltroSeveridad");
		if($filtro.val() != $filtro.attr("placeholder"))
			$("#gf_severidad").val($("#cFiltroSeveridad").val());
		
		$filtro = $("#cFiltroPrioridad");
		if($filtro.val() != $filtro.attr("placeholder"))
			$("#gf_prioridad").val($("#cFiltroPrioridad").val());
		
		$filtro = $("#cFiltroTipoHallazgo");
		if($filtro.val() != $filtro.attr("placeholder"))
			$("#gf_tipoHallazgo").val($("#cFiltroTipoHallazgo").val());
		
		$filtro = $("#cFiltroUsuAsignado");
		var value = $("#cFiltroUsuAsignado").val();
		if($filtro.val() != $filtro.attr("placeholder"))
			$("#gf_usuAsignado").val($("#cFiltroUsuAsignado").val());
		
		$filtro = $("#cFiltroTitulo");
		if($filtro.val() != $filtro.attr("placeholder"))
			$("#gf_titulo").val($("#cFiltroTitulo").val());
		
		$filtro = $("#cFiltroEstado");
		var est = $filtro.val();
		if($filtro.val() != $filtro.attr("placeholder"))
			$("#gf_estado").val($filtro.val());
		
//		var oSettings = oTable.fnSettings();
//		$("tfoot input").each(function(i){
//			console.log("val1 = "+oSettings.aoPreSearchCols[i]['sSearch']);
//			if($(this).val() != "") {
//				console.log("val2 = "+$(this).val());
//			}
//			if(oSettings.aoPreSearchCols[i]['sSearch']!=''){
//				console.log("val2 = "+$(this).val());
//			}
//		});

//		var dTable = $("#hallazgos").DataTable();
//		console.log("filtro estado = "+oTable);
		return true;
	});
	setDataTableFilters();
	
});




function fn_columnFilter(){
	oTable.columnFilter({
		aoColumns: [ { type: "number" },
		             { type: "text" },
				     { type: "text" },
				     { type: "text" },
				     { type: "text" },
				     { type: "text" },
				     { type: "text" },
				     null,
				     { type: "text" },
				     null,
				     null,
				     null,
				     null,
				     null
				]

	});
}

/**
 * 
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @date 12/06/2014 9:23:27
 * @description Configura los filtros activos de la grilla para poder usarlos en la consulta de generar reporte.
 *  Si se modifica el datatable de la pantalla consultar hallazgos se debe ajustar este metodo.
 */
function setDataTableFilters() {	
	/*
	 * guardamos en la variable filtros[] los filtros activos de la grilla.
	 */
	var filtros = $("tfoot input");
	
	var consecutivo = filtros[0];
	$(consecutivo).attr("placeholder",$(consecutivo).val());
	$(consecutivo).attr("id","cFiltroId");
	$(consecutivo).removeAttr("value");
	
	var artefacto = filtros[1];
	$(artefacto).attr("placeholder",$(artefacto).val());
	$(artefacto).attr("id","cFiltroArtefacto");
	$(artefacto).removeAttr("value");
	
	var severidad = filtros[2];
	$(severidad).attr("placeholder",$(severidad).val());
	$(severidad).attr("id","cFiltroSeveridad");
	$(severidad).removeAttr("value");
	
	var prioridad = filtros[3];
	$(prioridad).attr("placeholder",$(prioridad).val());
	$(prioridad).attr("id","cFiltroPrioridad");
	$(prioridad).removeAttr("value");
	
	var tipoHallazgo = filtros[1];
	$(tipoHallazgo).attr("placeholder",$(tipoHallazgo).val());
	$(tipoHallazgo).attr("id","cFiltroTipoHallazgo");
	$(tipoHallazgo).removeAttr("value");
	
	//Filtro Usuario Asignado
	var usuAsignado = filtros[5];
	$(usuAsignado).attr("placeholder",$(usuAsignado ).val());
	$(usuAsignado).attr("id","cFiltroUsuAsignado");
	$(usuAsignado).removeAttr("value");
	
	var titulo = filtros[6];
	$(titulo).attr("placeholder",$(titulo).val());
	$(titulo).attr("id","cFiltroTitulo");
	$(titulo).removeAttr("value");
	
	var estado = filtros[7];
	$(estado).attr("placeholder",$(estado).val());
	$(estado).attr("id","cFiltroEstado");
	$(estado).removeAttr("value");
	
}

function jsonDatosFiltrosConsulta() {
	var proyecto =  null,
	    mapaPrueba = null,
	    causaAnulacion = null,
	    causaGeneracion = null,
	    usuarioAsigna = null;
	
	//Alex: Filtros consulta:
	var idHallazgo = null,
	    artefacto = null,
	    severidad = null,
	    prioridad = null,
	    tipoHallazgo = null,
	    usuAsignado = null,
	    titulo = null,
	    estado = null;
	
	if($("#proyecto").attr("data-id") != "")
	  proyecto =  $("#proyecto").attr("data-id");
	if($("#mapaPruebas option:selected").val() != "")
	  mapaPrueba = $("#mapaPruebas option:selected").val();
	if($("#causaAnula option:selected").val() != "")
	  causaAnulacion = $("#causaAnula option:selected").val();
	if($("#causaGeneracion option:selected").val() != "")
	  causaGeneracion = $("#causaGeneracion option:selected").val();
	if($("#usuario").attr("data-id") != "")
	  usuarioAsigna = $("#usuario").attr("data-id");
	
	//Validaciones filtros grilla. Id generados en la funcion setDataTableFilters();
	if($("#cFiltroId").val() != "")
		idHallazgo = $("#cFiltroId").val();
	if($("#cFiltroArtefacto").val() != "")
		artefacto = $("#cFiltroArtefacto").val();
	if($("#cFiltroSeveridad").val() != "")
		severidad = $("#cFiltroSeveridad").val();
	if($("#cFiltroPrioridad").val() != "")
		prioridad = $("#cFiltroPrioridad").val();
	if($("#cFiltroTipoHallazgo").val() != "")
		tipoHallazgo = $("#cFiltroTipoHallazgo").val();
	if($("#cFiltroUsuAsignado").val() != "")
		usuAsignado = $("#cFiltroUsuAsignado").val();
	if($("#cFiltroTitulo").val() != "")
		titulo = $("#cFiltroTitulo").val();
	if($("#cFiltroEstado").val() != "")
		estado = $("#cFiltroEstado").val();
		
	var datos = {
		"hallazgo": {
			"proyecto": {"id": proyecto},
			"mapaPrueba" : {"id": mapaPrueba},
			"causaAnulacion" : {"id": causaAnulacion},
			"causaGeneracion" : {"id": causaGeneracion},
			"idHallazgo" : {"id": idHallazgo}
			},
		
		"usuarioAsigna" : {"id":usuarioAsigna},
		"fromFechaFilter" : $("#fechaFrom").val(),
		"toFechaFilter" : $("#fechaTo").val()
	};
	return datos;
}

function generarReporteExcel() {
	var flujohallazgodto = jsonDatosFiltrosConsulta();
	$('body').load("/sgp/hallazgos/reporteHallazgosXls",{dto:JSON.stringify(flujohallazgodto)}, function(data){
		window.open('data:application/vnd.ms-excel,' + data);
	    e.preventDefault();
	});
	
	
	/*
	$.ajax({
		type: "POST",
		url: "/sgp/hallazgos/reporteHallazgosXls",
		data: JSON.stringify(flujohallazgodto),
		contentType : "application/json; charset=utf-8",
		async: false
	}).done(function (data){
		
	}).fail(function (jqXHR, textStatus, errorThrown){
		fn_error(jqXHR, textStatus, errorThrown);
	});*/
	
}