	google.load('visualization', '1', {'packages':['corechart']});

	$(document).ready(function(){
		$("fechaFrom").hide();
		validator = $('#frm_seguimiento').validate({

			rules:{
				"proyecto":{
					required: true
				},
		"fechaFrom":{
			date: true
		},
			"fechaTo":{
			date: true
		}
			},
	        messages: {
	            proyecto: "Por favor seleccione un proyecto",
	            	fechaFrom:"Ingrese un formato de fecha válido",
	            	fechaTo:"Ingrese un formato de fecha válido"
	        },
		
			
		});	
		
	jQuery.getJSON("/sgp/utils/tiposHallazgo", function(data) {
			
			$.each(data, function(index, value){
				$("#tipoHallazgo").append('<option data-id="'+value.id+'" value="'+value.nombre+'">'+value.nombre+'</option>');
			});
		});	
	
	// Evento BuscaProyectos
	$("#buscarProyecto").on("click", function() {
		fn_getProyectos();

	});
	
	$("#proyecto").on("change", function() {
		$("#tipoHallazgo").attr("disabled",false);
		if($("#tipoHallazgo").val() != ""){
			$("#tipoHallazgo").trigger("change");
		}
			
	});
	$("#fechaFrom, #fechaTo, #tipoHallazgo").on("change", function() {
//		$("#fechaFrom").datepicker();
		$("#fechaTo").datepicker();
		$("#fechaFrom").attr("readonly",false);
		$("#fechaTo").attr("readonly",false);
       clearFormValidate();
     var idProyecto = $("#proyecto").attr("data-id"),
         fechaFrom  = $("#fechaFrom").val(),
         fechaTo    = $("#fechaTo").val(),
         idTipoHallazgo= $("#tipoHallazgo").attr("data-id"),
         tipoHallazgo = $("#tipoHallazgo").val();
        
		$("#divInforme").load("/sgp/reportes/getInformeSeguimiento",{idProyecto: idProyecto, fechaFrom: "", fechaTo: fechaTo, tipoHallazgo: tipoHallazgo}, 
		 function(){
			tabla_pivot(idProyecto, "", fechaTo, tipoHallazgo);
			pintarGraficas(idProyecto, "", fechaTo, tipoHallazgo,idTipoHallazgo);
		});

	});

	$("#pdf").on("click",function(){
		if($('#frm_seguimiento').valid()){
		 window.print();
		}else{
			$("#fechaFrom").css("border-color","#cccccc");
			$("#fechaTo").css("border-color","#cccccc");
			$("#lblfecha").css("color","#000");
		}
	});
	
	});
	
	function pintarGraficas(idProyecto, fechaFrom, fechaTo, tipoHallazgo,idTipoHallazgo){
		
		drawVisualizationBarChart(idProyecto, fechaFrom, fechaTo, tipoHallazgo,idTipoHallazgo);
		
		drawVisualizationPieChart(idProyecto, fechaFrom, fechaTo, tipoHallazgo);
	}
	
	function drawVisualizationBarChart(idProyecto, fechaFrom, fechaTo, tipoHallazgo,idTipoHallazgo) {
		var traeDatos=0;
		var tiposHallazgoMapa = jQuery.parseJSON($("#tiposMapa").val()),
		tiposHallazgoArtefacto =   jQuery.parseJSON($("#tiposArtefacto").val()),
		tipoHallazgoId = $("#tipoHallazgo option:selected").attr('data-id');
        

		if(tipoHallazgoId == '3')
		{
		  	$("#avanceEjecucion").show();
		}
			if($.inArray(tipoHallazgoId, tiposHallazgoMapa)> -1 )
				
			{
				$('#hallazgoPorMapa').show();
				$('#hallazgoPorMapa1').show();
				jQuery.getJSON("/sgp/hallazgos/getHallazgosPorMapaPruebas",
						{idProyecto:idProyecto, fechaFrom: fechaFrom, fechaTo: fechaTo, tipoHallazgo: tipoHallazgo}, function(result) {
						
					var array=new Array();
					$.each(result, function(index, value){
						array.push([value.nombreMapaPruebas,value.cantidad]);
				
					});
					
		  		var data = new google.visualization.DataTable();
		  		data.addColumn('string', '');
		  		data.addColumn('number', 'Total');
		  		data.addRows(array);
		  	
		  	  var view = new google.visualization.DataView(data);
			  
			  view.setColumns([{
		          label: '',
		          type: 'string',
		          calc: function (dt, row) {
		              var str = dt.getValue(row, 0);
		              return { v: str.toString(), f:str.toString() };
		          }
		      },
		      {
		          label: 'Total',
		          type: 'number',
		          calc: function (dt, row) {
		              var str = dt.getValue(row, 1);
		              return { v: str, f: str.toString() };
		          }
		      },
		      {
		          role: 'annotation',
		          type: 'number',
		          calc: function (dt, row) {
		              var res = dt.getValue(row, 1);
		              return { v: res, f: res.toString() };
		          }
		      }]);
			      	  
		    	  var chart = new google.visualization.BarChart(document.getElementById('hallazgoPorMapa'));
		  		traeDatos=1;
		          chart.draw(view, {title:"Total de Hallazgos por Mapas de Pruebas", width:800, height:data.getNumberOfRows() * 25,
		        	  chartArea:{left:300,width:"50%",height:"70%"}
		   	   });
		          
		  	});
				if(traeDatos==0){
							
					$('#hallazgoPorMapa').html('<div style="text-align: center;font-family:Arial; font-size:11;font-weight:bold ">No hay datos</div>');
				}
			
			}else{
				$('#hallazgoPorMapa').hide();
				$('#hallazgoPorMapa1').hide();
//				$('#hallazgoPorMapa').html('<div style="text-align: center">No hay datos</div>');
			}
			traeDatos=0;
			if($.inArray(tipoHallazgoId, tiposHallazgoArtefacto)> -1 )
				
			{
				$('#hallazgoPorArtefacto').html('');
				jQuery.getJSON("/sgp/hallazgos/getHallazgosPorArtefacto",{idProyecto:idProyecto, fechaFrom: fechaFrom, fechaTo: fechaTo, tipoHallazgo: tipoHallazgo}, function(result) {
				
					var array=new Array();
					$.each(result, function(index, value){
						array.push([value.nombreArtefacto,value.cantidad]);
					});
					
		  		var data = new google.visualization.DataTable();
		  		data.addColumn('string', '');
		  		data.addColumn('number', 'Total');
		  		data.addRows(array);
		  	
		  	  var view = new google.visualization.DataView(data);
			  
			  view.setColumns([{
		          label: '',
		          type: 'string',
		          calc: function (dt, row) {
		              var str = dt.getValue(row, 0);
		              return { v: str.toString(), f:str.toString() };
		          }
		      },
		      {
		          label: 'Total',
		          type: 'number',
		          calc: function (dt, row) {
		              var str = dt.getValue(row, 1);
		              return { v: str, f: str.toString() };
		          }
		      },
		      {
		          role: 'annotation',
		          type: 'number',
		          calc: function (dt, row) {
		              var res = dt.getValue(row, 1);
		              return { v: res, f: res.toString() };
		          }
		      }]);
			      	  
		    	  var chart = new google.visualization.BarChart(document.getElementById('hallazgoPorArtefacto'));
		    		traeDatos=1;
		          chart.draw(view, {title:"Total de Hallazgos por Artefacto", width:800, height:data.getNumberOfRows() * 25,
		        	  chartArea:{left:300,width:"50%",height:"70%"}
		   	   });
		          
		  	});	
				if(traeDatos==0){
					$('#hallazgoPorArtefacto').html('<div style="text-align: center;font-family:Arial; font-size:11;font-weight:bold ">No hay datos</div>');
				}
			}else{
				$('#hallazgoPorArtefacto').html('<div style="text-align: center ;font-family:Arial; font-size:11;font-weight:bold">No hay datos</div>');
			}

  }

function drawVisualizationPieChart(idProyecto, fechaFrom, fechaTo, tipoHallazgo) {
 	 jQuery.getJSON("/sgp/hallazgos/getHallazgosPorSeveridad",{idProyecto:idProyecto, fechaFrom: fechaFrom, fechaTo: fechaTo, tipoHallazgo: tipoHallazgo}, function(result) {

			var array=new Array();
			$.each(result, function(index, value){
				array.push([value.nombreSeveridad,value.cantidad]);
			});
			
 		var data = new google.visualization.DataTable();
 		data.addColumn('string', '');
 		data.addColumn('number', 'y');
 		data.addRows(array);
   	  new google.visualization.PieChart(document.getElementById('hallazgoPorSeveridad')).
      draw(data, {title:"Total de Hallazgos por Severidad", width:450, height:250
    	  });

 	});
 }


function tabla_pivot(idProyecto, fechaFrom, fechaTo, tipoHallazgo){

$.ajax({
    url: "/sgp/reportes/getReporteSeveridad",
    type: "get",
    data: {idProyecto:idProyecto, fechaFrom: fechaFrom, fechaTo: fechaTo, tipoHallazgoNom: tipoHallazgo},
    dataType: "json",
    async: false
	}).done(function(severidad) {
		$.grep(severidad, function(value, index){
			$('#tablaSeveridad').append('<div id="outputss'+index+'"></div>');
			$('#tablaSeveridad').append('<div id="outputs'+index+'"></div>');
			var obj = jQuery.parseJSON(value);
			$.each(obj,function(i,v){
//				$('#outputss'+index).append('<br><strong>'+v.tipoHallazgo+'</strong>');
				return true;
			});
			$('#outputs'+index).pivot(obj, {
				cols: ["tipoSeveridad"], rows: ["usuarioAsignado"],colsTitle:'Tipo Severidad',rowsTitle:'Usuario Asignado'
			})	;
		});
	});

}


