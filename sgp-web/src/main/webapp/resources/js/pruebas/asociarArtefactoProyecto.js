

/**
 * @author Edwin Gómez
 * @desc Funcion que registra un artefacto a un proyecto
 */


function fn_asociar(){
	
	var tipoArtefactoDTO = {
			id : $("#tipoArtefacto option:selected").val(),
			nombre : $("#tipoArtefacto option:selected").text()
	};
	var proyectoDTO = {
		id: $("#proyecto").attr("data-id"),
		nombre: $("#proyecto").text()
	};
	
	var usuarioDTO = {
			id : $("#usuariosProyecto option:selected").val()
	}
	
	var artefactoDTO = {
			nombre : $("#artefacto").val(),
			tipoArtefacto : tipoArtefactoDTO,
			proyecto: proyectoDTO,
			usuario: usuarioDTO,
			numeroEstado: $("#frm_asoArtefactoPro #estado option:selected").val()
	};
	$.ajax({
		url : "/sgp/asociarArtefactoProyecto/guardar",
		type : "post",
		data : JSON.stringify(artefactoDTO),
		contentType : "application/json; charset=utf-8",
		async: false

	}).done(function(data) {
		$("#frm_asoArtefactoPro").reset();
		oModal.fnFilter($("#proyecto").text(), 3 , true);

	}).fail(function(jqXHR, textStatus, errorThrown){
		alert(errorThrown);
	});
}

/**
 * @author Edwin Gómez
 * @desc Modifica un artefacto de la grilla
 */
function fn_modificarArtefacto(){
	
	var tipoArtefactoDTO = {
			id : $("#tipoArtefacto option:selected").val(),
			nombre : $("#tipoArtefacto option:selected").text()
	};
	
	var usuarioDTO = {
			id : $("#usuariosProyecto option:selected").val()
	}

	var artefactoDTO = {
			id: $("#frm_asoArtefactoPro #id").val(),
			nombre : $("#artefacto").val(),
			descripcion : $("#frm_asoArtefactoPro #descripcion").val(),
			tipoArtefacto : tipoArtefactoDTO,
			usuario : usuarioDTO,
			numeroEstado: $("#frm_asoArtefactoPro #estado option:selected").val()
	};
	$.ajax({
		url : "/sgp/asociarArtefactoProyecto/editar",
		type : "post",
		data : JSON.stringify(artefactoDTO),
		contentType : "application/json; charset=utf-8",
		async: false

	}).done(function(data) {
		loadArtefactoDataTable();
		$("#modalArtefacto").modal("hide");
		$("#tabs li:eq(1) a").tab('show');
	}).fail(function(jqXHR, textStatus, errorThrown){
		alert(errorThrown);
	});
}
function fn_editar(id) {
	
	$("#modalArtefacto").load("/sgp/pages/popup/pruebas/editArtefacto.jsp");

	getTiposArtefacto();
	jQuery.getJSON("/sgp/asociarArtefactoProyecto/getArtefacto", {
		id : id
	}, function(data) {
		
		$.each(data.usuariosProyecto, function(index,value){
			$("#frm_asoArtefactoPro #usuariosProyecto").append('<option value="'+value.id+'" title="'+value.nombre+'">'+value.nombre+'</option>');
		});
		
		/*
		 * Es probable que un usuario ya no este asociado a un proyecto, pero en el artefacto
		 * el usuario sigue estando asociado al artefacto, asi que primero eliminamos el 
		 * usuario si se encuentra en el select y agregamos el usuario del artefacto al mismo select.
		 */
		if(data.artefacto.usuario) {
			var idUsuario = data.artefacto.usuario.id
			$("#frm_asoArtefactoPro #usuariosProyecto option[value='"+idUsuario+"']").remove();
			$("#frm_asoArtefactoPro #usuariosProyecto").append('<option value="'+idUsuario+'" title="'+data.artefacto.usuario.nombre+'">'+data.artefacto.usuario.nombre+'</option>');
			$("#frm_asoArtefactoPro #usuariosProyecto").val(idUsuario);
		}
		
		$("#frm_asoArtefactoPro #id").val(data.artefacto.id);
		$("#proyecto").text(data.artefacto.proyecto.nombre);
		$("#proyecto").attr("data-id",data.artefacto.proyecto.id);
		$("#frm_asoArtefactoPro #artefacto").val(data.artefacto.nombre);
		$("#frm_asoArtefactoPro #tipoArtefacto").val(data.artefacto.tipoArtefacto.id);
		$("#frm_asoArtefactoPro #descripcion").text(data.artefacto.descripcion);
		$("#frm_asoArtefactoPro #estado").val(data.artefacto.numeroEstado);
		
		$("#sv_asoc").hide();
		$("#edit_asoc").show();
	});
	$("#modalArtefacto").modal();
	

}
function fn_show(id){
	
	jQuery.getJSON("/sgp/asociarArtefactoProyecto/getArtefacto", {
		id : id
	}, function(data) {
		
		$("#modal_artefacto p").clear();
        $("#modal_artefacto #consecutivo").text(data.artefacto.id);
		$("#modal_artefacto #nombre").text(data.artefacto.nombre);
		$("#modal_artefacto #nomProyecto").text(data.artefacto.proyecto.nombre);
		$("#modal_artefacto #estado").text(data.artefacto.indActivo);
		$("#modal_artefacto #fec_creacion").text(data.artefacto.fechaCreacionString);
		$("#modal_artefacto #fec_mod").text(data.artefacto.fechaEditaString);
		$("#modal_artefacto #usu_crea").text(data.artefacto.usuarioCreacion);
		$("#modal_artefacto #usu_mod").text(data.artefacto.usuarioEdita);
	});
	
 $("#modal_artefacto").modal();
}

function fn_columnFilterArtefacto(){
	oTable.columnFilter({
		aoColumns: [  null,
		             { type: "number" },
				     { type: "text" },
				     { type: "text" },
				     { type: "text" },
				     { type: "text" },
				     { type: "select", values: [ "Activo", "Inactivo"]  }
				]

	});
}
