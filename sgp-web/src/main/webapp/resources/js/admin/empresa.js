var restriccionesLogo = null;

$(document).ready(function(){
	/**
	 * Ejecuta al cargar la pagina
	 */
	$("#ed_empresa").hide();
	getPaises();
	getDepartamentos(0);
	getCiudades(0);
	fn_getRestriccionesLogo().done(function (data){
		restriccionesLogo = data.data;
		$("#logo").attr("accept",restriccionesLogo.allowedfilesLogo);
	}).fail(function (jqXHR, textStatus, errorThrown){
		fn_error(jqXHR, textStatus, errorThrown);
	});
	
	validator = $('#formEmpresa').validate({
		debug: true,
		rules:{
			"nombre":{
				required : true,
				maxlength: 255
			},
			"pais":{
			    required : true
			    },
			"departamento":{
			   required : true
			    },
		    "ciudad":{
		    	required : true
		    },
//		    "logo":{
//		    	required : true,
//		    	maxlength: 1000
//		    },
		    "telefono":{
		    	number: true,
		    	maxlength: 50
		    },
		    "descripcion":{
		    	maxlength: 1000
		    },
		    "direccion":{
		    	maxlength: 255
		    }
		    ,
		    "nit":{
		    	maxlength: 255
		    }
		},
		 messages: {
			nombre: {
	            required:"Por favor ingrese el nombre",
	            maxlength: "Este campo tiene un límite de {0} caracteres"
	  		},
            pais: "Por favor seleccione un país",
            departamento:"Por favor seleccione un departamento",
            ciudad: "Por favor seleccione una ciudad",
            logo: {
            	required:"Por favor seleccione un logo",
            	maxlength:"Este campo tiene un límite de {0} caracteres",
            	accept: "El tipo de archivo seleccionado no es permitido"
            },
            telefono:{
            	number: "Por favor ingrese un valor numérico",
            	maxlength: "Este campo tiene un límite de {0} caracteres" 
            		},
            descripcion: "Este campo tiene un límite de {0} caracteres",
            direccion: "Este campo tiene un límite de {0} caracteres",
            nit: "Este campo tiene un límite de {0} caracteres"
            
        },
	});
	/**
	 * Eventos JQuery
	 */
$(document).on("change", "#logo", function() {

		
		$('#logo').validate({
			rules:{
				"logo":{
					accept: restriccionesLogo.allowedfilesLogo
				}
			},
			messages: {
				logo: {
					accept: "El tipo de archivo seleccionado no es permitido"
					
				}
			}
		});
		if($("#logo").valid()) {
		uploadFormData();
		}
});
//$(document).on("change","#logo",function(){
//	$("#logo").attr("accept",restriccionesLogo.allowedfilesLogo);
//});
	
	$('#sv_empresa').click(function(){
		if($("#formEmpresa").valid()){
			fn_guardar();
		}
	});
	
	$('#ed_empresa').click(function(){
		if($("#formEmpresa").valid()){
			fn_actualizar();
		}
	});
	
	$("button:reset").on("click", function(){
	 clearFormEmpresa();
	});
	
	$('#pais').change(function(){
		getDepartamentos($(this).val());
	});
	
	$('#departamento').change(function(){
		getCiudades($(this).val());
	});

//	$('#logo').change(function(){
//		$('#formEmpresa').validate({
//			rules:{
//				"logo":{
//
//					accept: restriccionesLogo.allowedfilesLogo
//				}
//			},
//			messages: {
//				logo: {
//					
//					accept: "El tipo de archivo seleccionado no es permitido"
//				}
//			}
//		});
//
//
//	});
	
});


/**
 * Funciones JQuery
 */
$.fn.construirEmpresaJson = function(){
	  
	  var idEmpresa = Number($("#idEmpresa").val());
	  
      var logoRuta = ($("#logo").val());
	  if(logoRuta == ""){
		  logoRuta= ($("#rutaHidden").val());
	  }
      
	  var empresaDto = 
	  	{   
	      id: idEmpresa,
		  nombre: $("#nombre").val(), 
		  descripcion: $("#descripcion").val(), 
		  nit: $("#nit").val(), 
		  telefono: $("#telefono").val(),
		  direccion: $("#direccion").val(),
		  rutaLogo: logoRuta,
		  numeroEstado: $("#estado").val(),
		  ciudad: {
			  id: $("#ciudad option:selected").val(), 
			  nombre: $("#ciudad option:selected").text()
		  },
	  	};
	  
	  return empresaDto;
};
/**
 * 
 * @author Daniel Saavedra - Premize SAS
 * @desc 
 * @since 13/02/2014
 */
function fn_guardar(){

	var empresaDto = $().construirEmpresaJson();
	
	$.ajax({
		  url: "/sgp/empresas/guardar",
		  type: "POST",
		  data: JSON.stringify(empresaDto),
		  contentType: "application/json; charset=utf-8",
		  async: false
		}).done(function(data) {
			fn_resultado(data);
			clearFormEmpresa();
		});
};
/**
 * 
 * @author Daniel Saavedra - Premize SAS
 * @desc 
 * @since 13/02/2014
 */
function fn_actualizar()  {
  var empresa = $().construirEmpresaJson();
  $.ajax({
	  url: "/sgp/empresas/actualizar",
	  type: "POST",
	  data: JSON.stringify(empresa),
	  contentType: "application/json; charset=utf-8",
	  async: false
	}).done(function(data){
		fn_resultado(data);	
		clearFormEmpresa();
	});
};
/**
 * 
 * @author Daniel Saavedra - Premize SAS
 * @desc 
 * @since 13/02/2014
 */
function fn_editar(id) {
	
	clearFormValidate();
	$("#departamento").attr("readonly",false);
	$("#ciudad").attr("readonly",false);
	$("#ed_empresa").show();
	$("#sv_empresa").hide();
	jQuery.getJSON("/sgp/empresas/obtenerEmpresa", {
		id : id
	}, function(data) {
		 $('html, body').animate({scrollTop: '0px'}, 800);
		$("#result").html(data.imgLogo);
		$("#idEmpresa").val(data.id);
		$("#nombre").val(data.nombre);
		$("#nit").val(data.nit);
		$("#telefono").val(data.telefono);
		$("#direccion").val(data.direccion);
		$("#estado").val(data.numeroEstado);
		$("#pais").val(data.pais.id);
		$("#descripcion").val(data.descripcion);
		getDepartamentos(data.pais.id);
		$("#departamento").val(data.departamento.id);
		getCiudades(data.departamento.id);
		$("#ciudad").val(data.ciudad.id);
		$("#rutaHidden").val(data.rutaLogo);	
	});
}


function fn_show(id) {
	
	jQuery.getJSON("/sgp/empresas/obtenerEmpresa", {
		id : id
	}, function(data) {
		
		$("#modal_show p").clear();
		$("#consecutivoReg").text(data.id);
		$("#nombreReg").text(data.nombre);
		$("#logoReg").html(data.imgLogo);
		$("#estadoReg").text(data.indActivo);
		$("#fechaCreaReg").text(data.fechaCreacionString);
		$("#fechaEditaReg").text(data.fechaEditaString);
		$("#usuarioCreaReg").text(data.usuarioCreacion);
		$("#usuarioEditaReg").text(data.usuarioEdita);
		
	});
	 $("#modal_show").modal();
}


function uploadFormData(){
	//$('#result').html('');
	var oMyForm = new FormData();
	oMyForm.append("file", logo.files[0]);
	$.ajax({
		url: '/sgp/empresas/upload',
	    data: oMyForm,
	    dataType: 'text',
	    processData: false,
	    contentType: false,
	    type: 'POST',
	    async: false
	}).done(function(data) {
		 $('#result').html(data);
	});
}




//{ type: "number" },
function fn_columnFilter(){
	oTable.columnFilter({
		aoColumns: [  null,
		     { type: "text" },
		     { type: "text" },
		     { type: "text" },
		     { type: "text" },
		     { type: "text" },
		     { type: null },
		     { type: "text" },
		     { type: "select", values: [ "Activo", "Inactivo"]  }
		]
	});
}
function clearFormEmpresa(){
	clearFormValidate();
	$('#idEmpresa').val('');
	$("#formEmpresa").reset();
	$("#ed_empresa").hide();
	$("#sv_empresa").show();
	$("#departamento").html("");
	$("#departamento").attr("readonly",true);
	$("#ciudad").html("");
	$("#ciudad").attr("readonly",true);
	$("#result").html("");
	$('#rutaHidden').val('');
	getDepartamentos(0);
	getCiudades(0);
}
