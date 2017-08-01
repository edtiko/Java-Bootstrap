package com.premize.sgp.utils;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-service
 * @class ReporteExcelCons
 * @description Esta interface almacena todas los campos estaticos o constantes de la clase ReporteExcel
 * @date 21/04/2014
 */
public interface ReporteExcelCons {
	
	String RUTA_PROPERTIES = "/src/main/resources/sgp_reportes_excel.properties";
	String RUTA_PROPERTIES_2 = "/resources/sgp_reportes_excel.properties";
	String FILE_NAME = "sgp_reportes_excel.properties";
	String LOGO_FILE_NAME = "/images/logoPremizeReporteExcel.png";
	
	String FORMATO_FECHA = "dd/mm/yyyy hh:mm:ss";
	
	String FORMATO_FECHA_NOMBRE_ARCHIVO = "yyyyMMdd";
	String PROYECTO = "SGP";
	String PREFIJO_MP = "MP";
	
	String STRING_CELDA_VACIA = "---";
	
	// EF = EXCEL FORMULA
	String EF_SUMA = "SUM";
	String EF_PROMEDIO = "AVERAGE";
	String EF_IF = "IF";
	
	String PATRON_PORCENTAJE = "0.0#%";
	
	// Extension
	String EXT_XLS = ".xls";
	
	String KEY_MAP_REPORTE = "reporte";
	String KEY_MAP_FILENAME = "fileName";
	
    int POS_FILA_ENCABEZADO = 5;
    int POS_FILA_INICIAL = 0;
    int POS_FILA_DATA_INICIAL = 1;
    int POS_FILA_DATA = POS_FILA_ENCABEZADO + 1;
    
    int POS_FILA_ENCABEZADO_AVANCE_MAPAS = 0;
    int POS_FILA_DATA_ENCABEZADO_AVANCE_MAPAS = POS_FILA_ENCABEZADO_AVANCE_MAPAS + 1;
    
    int ANCHO_CELDA_FECHA = 19 * 256;
	
}
