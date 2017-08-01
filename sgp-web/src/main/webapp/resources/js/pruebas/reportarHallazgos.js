 function fn_guardar(){
	
	var formData = new FormData();

	var usuarioAsignado = {
			id : $("#asignar option:selected").val(),
			nombre :$("#asignar option:selected").text()
		};


	var hallazgodto = {
			
			casoPrueba: {id: $("#idCaso").val()},
			tipoHallazgo: {id: $("#tipoHallazgo option:selected").val()},
			causaGeneracion: {id: $("#causaGeneracion option:selected").val()},
			severidad: {id: $("#severidad option:selected").val()},
			prioridad: {id:$("#prioridad option:selected").val()},
			titulo: $("#titulo").val(),
			descripcion : $("#descripcion").val(),
			fechaSolicitudString:''

	};
	
	var flujohallazgodto = {
			
		hallazgo : hallazgodto,
		usuarioAsignado: usuarioAsignado
	};
	
	formData.append("anexo", anexo.files[0]);
	formData.append("dto", JSON.stringify(flujohallazgodto));
	$.ajax({
		url : "/sgp/hallazgos/guardar",
		type : "post",
		data : formData,
		processData: false,
	    contentType: false,
	    async: true

	}).done(function(data) {
		if(data.status == "SUCCESS"){
			$("#modal_hallazgo").modal('hide');
		}
		$("#modal_msg").load("/sgp/hallazgos/confirmaHallazgo",{result:data.result});
		
		$("#modal_msg").modal();
		
	}).fail(function(jqXHR, textStatus, errorThrown){
		fn_error(jqXHR, textStatus, errorThrown);
	});
	
}

function loadValidations(jsonRestriccionesAnexo) {
	validator = $('#form_reportarHallazgo').validate({
		debug: true,
		//Definir reglas para los campos
		rules:{
			"tipoHallazgo":{
				required:true
			},
			"causaGeneracion":{
				required:true
			},
			"severidad":{
				required:true
			},
			"prioridad":{
				required:true
			},
			"asignar":{
				required:true
			},
			"titulo":{
				required:true,
				maxlength: 1000
			},
			"descripcion":{
				required:true,
				maxlength: 5000
			},
			"anexo":{
				accept: jsonRestriccionesAnexo.allowedfiles,
				fileMaxSize: [jsonRestriccionesAnexo.filesize]
			}

		},

		// Specify the validation error messages
		messages: {
			tipoHallazgo: "Por favor seleccione un tipo de hallazgo",	
			causaGeneracion: "Por favor seleccione una causa de generación",
			severidad:"Por favor seleccione la severidad",
			prioridad:"Por favor seleccione la prioridad",
			asignar:"Por favor seleccione un recurso",
			titulo:{
				required: "Por favor seleccione un recurso",
				maxlength: "Este campo tiene un límite de {0} caracteres"
			  		},
			titulo:{
				required:"Por favor ingrese un título",
				maxlength: "Este campo tiene un límite de {0} caracteres"
					},
			descripcion: {
				required :"Por favor ingrese la descripción del hallazgo",
  			  	maxlength: "Este campo tiene un límite de {0} caracteres"
  			},
  			anexo: {
  				accept: "El tipo de archivo seleccionado no es permitido",
  				fileMaxSize: "El peso del archivo seleccionado no es permitido"
  			}
		}


	});
}

