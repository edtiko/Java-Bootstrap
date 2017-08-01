    // Load the Visualization API and the piechart package.
    google.load('visualization', '1', {'packages':['corechart']});
    google.setOnLoadCallback(drawVisualizationBarChart);
    google.setOnLoadCallback(drawVisualizationPieChart);
    
    function drawVisualizationBarChart() {
    	
    	/* jQuery.getJSON("/sgp/hallazgos/getHallazgosPorMapaPruebas",{idProyecto:1}, function(result) {

    			var array=new Array();
    			$.each(result, function(index, value){
    				array.push([value.nombreMapaPruebas,value.cantidad]);
    			});
    			
      		var data = new google.visualization.DataTable();
      		data.addColumn('string', '');
      		data.addColumn('number', 'Total');
      		data.addRows(array);
      		
        	  new google.visualization.BarChart(document.getElementById('barchart_div')).
           draw(data, {title:"Total de Hallazgos por Mapas de Pruebas", width:600, height:400});

      	});*/
    	 
    	 
    	 
    	 var data = google.visualization.arrayToDataTable([
    	                                                   ['Year', 'Austria', ],
    	                                                   ['2003342434353453453453479547575876586 2003342434353453453453479547575876586 2003342434353453453453479547575876586 2003342434353453453453479547575876586',  1336060  ],
    	                                                   ['2004',  1538156  ],
    	                                                   ['2005',  1576579],
    	                                                   ['2006',  1600652 ],
    	                                                   ['2007',  1968113   ],
    	                                                   ['2008',  1901067   ]
    	                                                 ]);

   
             new google.visualization.BarChart(document.getElementById('barchart_div')).
             draw(data,
                  {title:"Yearly Coffee Consumption by Country",
            	 width: 1400,
                 height: 600,
                 chartArea: { left: 800, width: "100%", height: "70%" }}
             );
      }
    
    function drawVisualizationPieChart() {
    	
     	 jQuery.getJSON("/sgp/hallazgos/getHallazgosPorSeveridad",{idProyecto:1}, function(result) {

   			var array=new Array();
   			$.each(result, function(index, value){
   				array.push([value.nombreSeveridad,value.cantidad]);
   			});
   			
     		var data = new google.visualization.DataTable();
     		data.addColumn('string', '');
     		data.addColumn('number', 'y');
     		data.addRows(array);
     		
       	  new google.visualization.PieChart(document.getElementById('piechart_div')).
          draw(data, {title:"Total de Hallazgos por Severidad", width:600, height:400});

     	});
     }


