/**
 * 
 */
$(document).ready(function () {
	var id = $("#proyecto").val();
	console.log("cuerpo - proyecto = "+id);

	$
	
	var data = {
		    "72": {
		        "artefactoNombre": "Diseño de modelos matematicos",
		        "mapSeveridad": {
		            "Incorrecto": {
		                "hallazgos": 1,
		                "puntuacion": 4.5,
		                "semaforo": 0
		            },
		            "Incompleto": {
		                "hallazgos": 2,
		                "puntuacion": 6,
		                "semaforo": 0
		            },
		            "Ambiguo": {
		                "hallazgos": 0,
		                "puntuacion": 0,
		                "semaforo": 0
		            },
		            "Totales": {
		                "hallazgos": 3,
		                "puntuacion": 10.5,
		                "indicador": 0.3,
		                "semaforo": 0
		            }
		        }
		    },
		    "74": {
		        "artefactoNombre": "R235 Desarrollo algoritmos de IA",
		        "mapSeveridad": {
		            "Incorrecto": {
		                "hallazgos": 1,
		                "puntuacion": 4.5,
		                "semaforo": 0
		            },
		            "Incompleto": {
		                "hallazgos": 0,
		                "puntuacion": 0,
		                "semaforo": 0
		            },
		            "Ambiguo": {
		                "hallazgos": 1,
		                "puntuacion": 3,
		                "semaforo": 0
		            },
		            "Totales": {
		                "hallazgos": 2,
		                "puntuacion": 7.5,
		                "indicador": 0.5,
		                "semaforo": 0
		            }
		        }
		    },
		    "-1": {
		        "mapSeveridad": {
		            "Incorrecto": {
		                "hallazgos": 2,
		                "puntuacion": 9,
		                "semaforo": 0
		            },
		            "Incompleto": {
		                "hallazgos": 2,
		                "puntuacion": 6,
		                "semaforo": 0
		            },
		            "Ambiguo": {
		                "hallazgos": 1,
		                "puntuacion": 3,
		                "semaforo": 0
		            },
		            "Totales": {
		                "hallazgos": 5,
		                "puntuacion": 18,
		                "semaforo": 0
		            }
		        }
		    }
		};
	
	readJson(data);
});

function buildTableIndCalidadDoc() {
	$.ajax({
		url: "/sgp/hallazgos/getEstadoNoSolicitaUsuario",
	    type: "get",
	    data: {estado:estado},
	    dataType: "json",
	    async: true
	})
}

function readJson(data) {
	$.each(data, function(i, v){
		console.log(v.artefactoNombre);
		console.log(v.mapSeveridad.Incorrecto.hallazgos);
		console.log("i = "+i);
	});
}


