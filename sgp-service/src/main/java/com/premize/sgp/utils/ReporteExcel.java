package com.premize.sgp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.log4j.Level;
import org.apache.poi.hssf.record.CFRuleRecord.ComparisonOperator;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FontFormatting;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PatternFormatting;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.util.IOUtils;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.pruebas.ConsultaHallazgoDTO;
import com.premize.sgp.dto.pruebas.MapaPruebasDTO;
import com.premize.sgp.dto.pruebas.ReporteMapaPruebaDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpMapaPrueba;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-service
 * @class ReporteExcel
 * @description
 * @date 11/04/2014
 */
@Service
public class ReporteExcel {
    
    static {
//    	Properties props = System.getProperties(); 
//    	props.setProperty("java.awt.headless","true"); 
//    	System.setProperty("java.awt.headless", "true");
    	try {
			construirArchivoProperties();
		} catch (IOException e) {
			LogUtil.log(ReporteExcel.class.getName(), e.getMessage(), Level.ERROR, e);
		}
    }
    
    public static int POS_FILA_ENCABEZADO = 0;
    public static int POS_FILA_DATA = POS_FILA_ENCABEZADO + 1;
    
    private static Properties reporteProperties;
    
    @PostConstruct
    public void init() throws AppBaseException, IOException {
    	construirArchivoProperties();
    }
    
    /**
     * 
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
     * @date 21/04/2014
     * @description 
     * @throws IOException
     */
    private static void construirArchivoProperties() throws IOException {
    	reporteProperties = new Properties();
		InputStream input = ReporteExcel.class.getClassLoader().getResourceAsStream(ReporteExcelCons.FILE_NAME);
		reporteProperties.load(input);
		input.close();
    }

    
    /**
     * 
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
     * @date 28/04/2014
     * @description 
     * @param nombreHoja
     * @param titulo
     * @return
     */
    private Workbook createFormatoReportes(String titulo, String nombreHoja) {
    	Workbook libro = new HSSFWorkbook();
    	Sheet hoja = libro.createSheet(nombreHoja);

    	Row fila;
    	Cell columna;
    	CellStyle estilo = libro.createCellStyle();

    	hoja.setColumnWidth(0, 100 * 12);
    	hoja.setColumnWidth(1, 100 * 60);
    	hoja.setColumnWidth(2, 100 * 120);
    	hoja.setColumnWidth(4, 100 * 120);

    	fila = hoja.createRow(1);
    	estilo = libro.createCellStyle();
    	estilo.setFillForegroundColor(HSSFColor.DARK_RED.index);
    	estilo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    	estilo.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
    	estilo.setAlignment(CellStyle.ALIGN_CENTER);

    	//Titulo mayusculas
    	columna = fila.createCell(1);
    	columna.setCellValue(titulo.toUpperCase());

    	Font fuente = libro.createFont();
    	fuente.setFontHeightInPoints((short) 20);
    	fuente.setFontName("Calibri");
    	fuente.setBoldweight(Font.BOLDWEIGHT_BOLD);
    	fuente.setColor(HSSFColor.WHITE.index);
    	estilo.setFont(fuente);

    	columna.setCellStyle(estilo);
    	fila.setHeightInPoints(25.50f);

    	hoja.addMergedRegion(CellRangeAddress.valueOf("$B$2:$G$2"));

    	// BORDE TITULO MAYUSCULAS
    	RegionUtil.setBorderBottom(CellStyle.BORDER_THIN,
    			CellRangeAddress.valueOf("$B$2:$G$2"), hoja, libro);
    	RegionUtil.setBorderTop(CellStyle.BORDER_THIN,
    			CellRangeAddress.valueOf("$B$2:$G$2"), hoja, libro);
    	RegionUtil.setBorderLeft(CellStyle.BORDER_THIN,
    			CellRangeAddress.valueOf("$B$2:$G$2"), hoja, libro);
    	RegionUtil.setBorderRight(CellStyle.BORDER_THIN,
    			CellRangeAddress.valueOf("$B$2:$G$2"), hoja, libro);
    	
    	POS_FILA_ENCABEZADO = 3;
    	
    	return libro;
    }
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/04/2014
	 * @description 
	 * @param mapaPruebaDTO
	 * @return
	 * @throws AppBaseException
	 * @throws IOException
	 */
	public Workbook construirReporteMapaPruebas(List<ReporteMapaPruebaDTO> mapaPruebaDTO, String nombreMapa) 
			throws AppBaseException, IOException {
		
		Properties prop = getArchivoProperties();
		Workbook libro = createFormatoReportes(prop.getProperty("tituloReporteMapas"),nombreMapa);
		
		List<String> listaEncabezados = new ArrayList<String>();
		listaEncabezados.add(prop.getProperty("No"));
		listaEncabezados.add(prop.getProperty("artefacto"));
		listaEncabezados.add(prop.getProperty("descripcionPrueba"));
		listaEncabezados.add(prop.getProperty("tipoPrueba"));
		listaEncabezados.add(prop.getProperty("resultado"));
		listaEncabezados.add(prop.getProperty("fechaCreacion"));
		listaEncabezados.add(prop.getProperty("usuarioCreador"));
		listaEncabezados.add(prop.getProperty("usuarioEjecutor"));
		listaEncabezados.add(prop.getProperty("fechaEjecucion"));
		listaEncabezados.add(prop.getProperty("cumple"));
        listaEncabezados.add(prop.getProperty("numeroHallazgo"));
        listaEncabezados.add(prop.getProperty("tituloHallazgo"));
        listaEncabezados.add(prop.getProperty("descripcionHallazgo"));
        listaEncabezados.add(prop.getProperty("tipoHallazgo"));
        listaEncabezados.add(prop.getProperty("severidad"));
        listaEncabezados.add(prop.getProperty("prioridad"));
        listaEncabezados.add(prop.getProperty("responsableRealizarAjustes"));
        listaEncabezados.add(prop.getProperty("causaGeneracion"));
        listaEncabezados.add(prop.getProperty("causaAnulacion"));
        listaEncabezados.add(prop.getProperty("estadoHallazgo"));
        listaEncabezados.add(prop.getProperty("fechaEstadoHallazgo"));
		
		libro = construirEncabezado(libro, listaEncabezados);
		llenarReporteMapaPruebas(libro, mapaPruebaDTO);
		
		return libro;
	}
	

	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 30/04/2014
	 * @description Metodo exclusivo para la funcionalidad de descargar los mapas en zip.
	 * @param mapaPruebaDTO
	 * @param selector
	 * @param nombreMapa
	 * @return
	 * @throws AppBaseException
	 * @throws IOException
	 */
	public Workbook construirReporteMapaPruebasZip(List<ReporteMapaPruebaDTO> mapaPruebaDTO, ListSelector selector,
			String nombreMapa) throws AppBaseException, IOException {
		
		Properties prop = getArchivoProperties();
		Workbook libro = createFormatoReportes(prop.getProperty("tituloReporteMapas"),nombreMapa);
		
		List<String> listaEncabezados = new ArrayList<String>();
		listaEncabezados.add(prop.getProperty("No"));
		listaEncabezados.add(prop.getProperty("artefacto"));
		listaEncabezados.add(prop.getProperty("descripcionPrueba"));
		listaEncabezados.add(prop.getProperty("tipoPrueba"));
		listaEncabezados.add(prop.getProperty("resultado"));
		listaEncabezados.add(prop.getProperty("fechaCreacion"));
		listaEncabezados.add(prop.getProperty("usuarioCreador"));
		listaEncabezados.add(prop.getProperty("usuarioEjecutor"));
		listaEncabezados.add(prop.getProperty("fechaEjecucion"));
		listaEncabezados.add(prop.getProperty("cumple"));
        listaEncabezados.add(prop.getProperty("numeroHallazgo"));
        listaEncabezados.add(prop.getProperty("tituloHallazgo"));
        listaEncabezados.add(prop.getProperty("descripcionHallazgo"));
        listaEncabezados.add(prop.getProperty("tipoHallazgo"));
        listaEncabezados.add(prop.getProperty("severidad"));
        listaEncabezados.add(prop.getProperty("prioridad"));
        listaEncabezados.add(prop.getProperty("responsableRealizarAjustes"));
        listaEncabezados.add(prop.getProperty("causaGeneracion"));
        listaEncabezados.add(prop.getProperty("causaAnulacion"));
        listaEncabezados.add(prop.getProperty("estadoHallazgo"));
        listaEncabezados.add(prop.getProperty("fechaEstadoHallazgo"));
		
		libro = construirEncabezado(libro, listaEncabezados);
		llenarReporteMapaPruebasZip(libro, mapaPruebaDTO, selector);
		
		return libro;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014
	 * @description 
	 * @param mapas
	 * @param nombreProyecto
	 * @return
	 * @throws AppBaseException
	 */
	public Workbook construirReporteAvanceEjcucionMapas(List<MapaPruebasDTO> mapas, String nombreProyecto) 
			throws AppBaseException {
		List<String> listaEncabezados = new ArrayList<String>();
		listaEncabezados.add(reporteProperties.getProperty("nombreMapaPruebas"));
		listaEncabezados.add(reporteProperties.getProperty("pruebasConstruidas"));
		listaEncabezados.add(reporteProperties.getProperty("pruebasEjecutadas"));
		listaEncabezados.add(reporteProperties.getProperty("pruebasSatisfactorias"));
		listaEncabezados.add(reporteProperties.getProperty("pruebasInsatisfactorias"));
		listaEncabezados.add(reporteProperties.getProperty("pruebasNoAplican"));
		listaEncabezados.add(reporteProperties.getProperty("pruebasSinEjecutar"));
		listaEncabezados.add(reporteProperties.getProperty("porcentajeAvancePruebas"));
		listaEncabezados.add(reporteProperties.getProperty("indicadorCalidadICF"));
		
		Workbook libro = new HSSFWorkbook();
		libro.createSheet(nombreProyecto);
		construirEncabezadoAvanceMapas(libro, listaEncabezados);
		llenarReporteAvanceEjecucionMapas(libro, mapas);
		
		return libro;
	}

	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/04/2014
	 * @description 
	 * @param libro
	 * @param listaEncabezado
	 * @return
	 */
	private Workbook construirEncabezado(Workbook libro, List<String> listaEncabezado) {

		Sheet hoja = libro.getSheetAt(0);
		Row filaEncabezado = hoja.createRow(POS_FILA_ENCABEZADO);
		filaEncabezado.setHeightInPoints(45f);

		HSSFPalette paleta = ((HSSFWorkbook) libro).getCustomPalette();
		paleta.setColorAtIndex(HSSFColor.DARK_RED.index, 
				(byte) 192, 
				(byte) 0,
				(byte) 0);

		CellStyle estilo = libro.createCellStyle();
		estilo.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		estilo.setAlignment(CellStyle.ALIGN_CENTER);
		estilo.setFillForegroundColor(HSSFColor.DARK_RED.index);
		estilo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		estilo.setWrapText(true);

		Font fuente = libro.createFont();
		fuente.setFontHeightInPoints((short) 8);
		fuente.setFontName("Arial");
		fuente.setBoldweight(Font.BOLDWEIGHT_BOLD);
		fuente.setColor(HSSFColor.WHITE.index);
		estilo.setFont(fuente);

		// Bordes:
		estilo.setBorderBottom(CellStyle.BORDER_THIN);
		estilo.setBorderLeft(CellStyle.BORDER_THIN);
		estilo.setBorderRight(CellStyle.BORDER_THIN);
		estilo.setBorderTop(CellStyle.BORDER_THIN);

		/// filaEncabezado.setRowStyle(estilo);
		int i = 0;
		for (String nombre : listaEncabezado) {
			Cell celda = filaEncabezado.createCell(i);
			celda.setCellValue(nombre);
			celda.setCellStyle(estilo);
			if(i > 10) {
				hoja.setColumnWidth(i, 25 * 256);
			}
			i++;
		}

		return libro;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 21/04/2014
	 * @param libro
	 * @param listaEncabezado
	 * @return
	 */
	private Workbook construirEncabezadoHallazgos(Workbook libro, List<String> listaEncabezado) {
		
		Sheet hoja = libro.getSheetAt(0);
		Row filaEncabezado = hoja.createRow(POS_FILA_ENCABEZADO);
		filaEncabezado.setHeightInPoints(45f);

		HSSFPalette paleta = ((HSSFWorkbook) libro).getCustomPalette();
		paleta.setColorAtIndex(HSSFColor.DARK_RED.index, (byte) 192, (byte) 0,
				(byte) 0);

		CellStyle estilo = libro.createCellStyle();
		estilo.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		estilo.setAlignment(CellStyle.ALIGN_CENTER);
		estilo.setFillForegroundColor(HSSFColor.DARK_RED.index);
		estilo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		estilo.setWrapText(true);

		Font fuente = libro.createFont();
		fuente.setFontHeightInPoints((short) 8);
		fuente.setFontName("Arial");
		fuente.setBoldweight(Font.BOLDWEIGHT_BOLD);
		fuente.setColor(HSSFColor.WHITE.index);
		estilo.setFont(fuente);

		// Bordes:
		estilo.setBorderBottom(CellStyle.BORDER_THIN);
		estilo.setBorderLeft(CellStyle.BORDER_THIN);
		estilo.setBorderRight(CellStyle.BORDER_THIN);
		estilo.setBorderTop(CellStyle.BORDER_THIN);

		/// filaEncabezado.setRowStyle(estilo);
		int i = 0;
		for (String nombre : listaEncabezado) {
			Cell celda = filaEncabezado.createCell(i);
			celda.setCellValue(nombre);
			celda.setCellStyle(estilo);
			i++;
		}
		hoja.setColumnWidth(8, 40 * 256);
		hoja.setColumnWidth(9, 30 * 256);
		hoja.setColumnWidth(16, 20 * 256);
		hoja.setColumnWidth(14, 20 * 256);
		hoja.setColumnWidth(13, 30 * 256);
		hoja.setColumnWidth(12, 20 * 256);
		hoja.setColumnWidth(11, 20 * 256);
		hoja.setColumnWidth(10, 20 * 256);
		hoja.setColumnWidth(17, 30 * 256);
		hoja.setColumnWidth(2, 30 * 256);
		hoja.setColumnWidth(3, 30 * 256);
		hoja.setColumnWidth(4, 20 * 256);
		hoja.setColumnWidth(5, 20 * 256);
		hoja.setColumnWidth(7, 20 * 256);
		return libro;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/04/2014
	 * @description 
	 * @param libro
	 * @param data
	 * @throws AppBaseException
	 */
	private void llenarReporteMapaPruebas(Workbook libro, List<ReporteMapaPruebaDTO> data) throws AppBaseException {
		int filaCnt = POS_FILA_ENCABEZADO+1, registro = 1, columnaCnt = 0;
		Sheet hoja = libro.getSheetAt(0);
		
		//Configurando el estilo de las celdas
		CellStyle estilo = libro.createCellStyle();
		setEstiloGenerico(estilo);
		
		CellStyle estiloFecha = libro.createCellStyle();
		setEstiloGenerico(estiloFecha);
		CreationHelper ch = libro.getCreationHelper();
		estiloFecha.setDataFormat(ch.createDataFormat().getFormat(ReporteExcelCons.FORMATO_FECHA));
		
		//Estilo para las celdas: C,E,L,M
		CellStyle estiloAlineacionIzq = libro.createCellStyle();
		estiloAlineacionIzq.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		estiloAlineacionIzq.setAlignment(CellStyle.ALIGN_LEFT);
		estiloAlineacionIzq.setWrapText(true);
		 // Bordes:
		estiloAlineacionIzq.setBorderBottom(CellStyle.BORDER_THIN);
		estiloAlineacionIzq.setBorderLeft(CellStyle.BORDER_THIN);
		estiloAlineacionIzq.setBorderRight(CellStyle.BORDER_THIN);
		estiloAlineacionIzq.setBorderTop(CellStyle.BORDER_THIN);
		
		
		Row fila;
		Cell celda;int idRegistroAnterior = -1;
        for (ReporteMapaPruebaDTO prueba : data) {
            fila = hoja.createRow(filaCnt);

            if (prueba.getId() != idRegistroAnterior) {
                //No.
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(registro);

                //artefacto:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(prueba.getArtefacto());

                //decripcionPrueba:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estiloAlineacionIzq);
                celda.setCellValue(prueba.getDescripcionCasoPrueba());

                //tipoPrueba
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(prueba.getTipoPrueba());

                //resultadoEsperado:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estiloAlineacionIzq);
                celda.setCellValue(prueba.getResultadoEsperado());

                //fechaCreacion:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estiloFecha);
                if(prueba.getFechaCreacion() != null) {
                	celda.setCellValue(prueba.getFechaCreacion());
                	hoja.setColumnWidth(columnaCnt, ReporteExcelCons.ANCHO_CELDA_FECHA);
                }

                //usuarioCreador:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(prueba.getUsuarioCrea());

                //usuarioEjecutor:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(prueba.getUsuarioEjecutor());

                //fechaEjecucion:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estiloFecha);
                if (prueba.getFechaEjecucion() != null) {
                    celda.setCellValue(prueba.getFechaEjecucion());
                    hoja.setColumnWidth(columnaCnt, ReporteExcelCons.ANCHO_CELDA_FECHA);
                }

                //cumple:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(prueba.getCumple());
                
                registro++;
            } else {
                columnaCnt = createStringCells(fila, columnaCnt, estilo, 0, 10);
                columnaCnt--; //el metodo createEmptyCells aumenta columnaCnt al final.
            }
            
            //Numero del hallazgo:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            if(prueba.getNumeroHallazgo() != null) {
            	celda.setCellValue(prueba.getNumeroHallazgo());
            }
            
            //Titulo del Hallazgo:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estiloAlineacionIzq);
            celda.setCellValue(prueba.getTituloHallazgo());
            
            //Descripcion del Hallazgo:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estiloAlineacionIzq);
            celda.setCellValue(prueba.getDescripcionHallazgo());
            
            //Tipo de Hallazgo:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            celda.setCellValue(prueba.getTipoHallazgo());
            
            //Severidad:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            celda.setCellValue(prueba.getSeveridad());
            
            //Prioridad:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            celda.setCellValue(prueba.getPrioridad());
            
            //Responsable realizar ajustes:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            celda.setCellValue(prueba.getResponsableRealizarAjuestes());
            
            //Causa generacion:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            celda.setCellValue(prueba.getCausaGeneracion());
            
            //Causa anulacion:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            celda.setCellValue(prueba.getCausaAnulacion());
            
            //Estado del Hallazgo:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            celda.setCellValue(prueba.getEstadoHallazgo());
            
            //Fecha estado del Hallazgo:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estiloFecha);
            if(prueba.getFechaEstadoHallazgo() != null) {
            	celda.setCellValue(prueba.getFechaEstadoHallazgo());
            	hoja.setColumnWidth(columnaCnt, ReporteExcelCons.ANCHO_CELDA_FECHA);
            }
            
            idRegistroAnterior = prueba.getId();

            columnaCnt = 0;
            filaCnt++;
		}
		
		//Configurando el ancho de las celdas fechas:
        /*
         * I M P O R T A N T E 
         * el metodo autoSizeColum de la clase Sheet estaba
         * casusando una Excepcion en el servidor de pruebas:
         * java.lang.NoClassDefFoundError: Could not initialize class sun.awt.X11GraphicsEnvironment
         */
//		hoja.autoSizeColumn(5);
//		hoja.autoSizeColumn(8);
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 30/04/2014
	 * @description 
	 * @param libro
	 * @param data
	 * @param selector
	 * @throws AppBaseException
	 */
	private void llenarReporteMapaPruebasZip(Workbook libro, List<ReporteMapaPruebaDTO> data, ListSelector selector) 
			throws AppBaseException {
		int filaCnt = POS_FILA_ENCABEZADO+1, registro = 1, columnaCnt = 0;
		Sheet hoja = libro.getSheetAt(0);
		
		//Configurando el estilo de las celdas
		CellStyle estilo = libro.createCellStyle();
		setEstiloGenerico(estilo);
		
		CellStyle estiloFecha = libro.createCellStyle();
		setEstiloGenerico(estiloFecha);
		CreationHelper ch = libro.getCreationHelper();
		estiloFecha.setDataFormat(ch.createDataFormat().getFormat(ReporteExcelCons.FORMATO_FECHA));
		
		//Estilo para las celdas: C,E,L,M
		CellStyle estiloAlineacionIzq = libro.createCellStyle();
		setEstiloGenerico(estiloAlineacionIzq);
		estiloAlineacionIzq.setAlignment(CellStyle.ALIGN_LEFT);
		
		Row fila;
		Cell celda;int idRegistroAnterior = -1;
        for (int i = selector.getStart(); i < selector.getEnd(); i++) {
        	ReporteMapaPruebaDTO prueba = data.get(i);
            fila = hoja.createRow(filaCnt);

            if (prueba.getId() != idRegistroAnterior) {
                //No.
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(registro);

                //artefacto:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(prueba.getArtefacto());

                //decripcionPrueba:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estiloAlineacionIzq);
                celda.setCellValue(prueba.getDescripcionCasoPrueba());

                //tipoPrueba
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(prueba.getTipoPrueba());

                //resultadoEsperado:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estiloAlineacionIzq);
                celda.setCellValue(prueba.getResultadoEsperado());

                //fechaCreacion:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estiloFecha);
                if(prueba.getFechaCreacion() != null) {
                	celda.setCellValue(prueba.getFechaCreacion());
                	hoja.setColumnWidth(columnaCnt, ReporteExcelCons.ANCHO_CELDA_FECHA);
                }

                //usuarioCreador:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(prueba.getUsuarioCrea());

                //usuarioEjecutor:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(prueba.getUsuarioEjecutor());

                //fechaEjecucion:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estiloFecha);
                if (prueba.getFechaEjecucion() != null) {
                    celda.setCellValue(prueba.getFechaEjecucion());
                    hoja.setColumnWidth(columnaCnt, ReporteExcelCons.ANCHO_CELDA_FECHA);
                }

                //cumple:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(prueba.getCumple());
                
                registro++;
            } else {
                columnaCnt = createStringCells(fila, columnaCnt, estilo, 0, 10);
                columnaCnt--; //el metodo createEmptyCells aumenta columnaCnt al final.
            }
            
            //Numero del hallazgo:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            if(prueba.getNumeroHallazgo() != null) {
            	celda.setCellValue(prueba.getNumeroHallazgo());
            }
            
            //Titulo del Hallazgo:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estiloAlineacionIzq);
            celda.setCellValue(prueba.getTituloHallazgo());
            
            //Descripcion del Hallazgo:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estiloAlineacionIzq);
            celda.setCellValue(prueba.getDescripcionHallazgo());
            
            //Tipo de Hallazgo:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            celda.setCellValue(prueba.getTipoHallazgo());
            
            //Severidad:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            celda.setCellValue(prueba.getSeveridad());
            
            //Prioridad:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            celda.setCellValue(prueba.getPrioridad());
            
            //Responsable realizar ajustes:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            celda.setCellValue(prueba.getResponsableRealizarAjuestes());
            
            //Causa generacion:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            celda.setCellValue(prueba.getCausaGeneracion());
            
            //Causa anulacion:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            celda.setCellValue(prueba.getCausaAnulacion());
            
            //Estado del Hallazgo:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            celda.setCellValue(prueba.getEstadoHallazgo());
            
            //Fecha estado del Hallazgo:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estiloFecha);
            if(prueba.getFechaEstadoHallazgo() != null) {
            	celda.setCellValue(prueba.getFechaEstadoHallazgo());
            	hoja.setColumnWidth(columnaCnt, ReporteExcelCons.ANCHO_CELDA_FECHA);
            }
            
            idRegistroAnterior = prueba.getId();

            columnaCnt = 0;
            filaCnt++;
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 30/04/2014
	 * @description 
	 * @param libro
	 * @param mapaDto
	 * @return
	 * @throws AppBaseException
	 */
	public Workbook addRowReporteMapaPruebas(Workbook libro, ReporteMapaPruebaDTO mapaDto) 
			throws AppBaseException {
		int columnaCnt = 0;
		Sheet hoja = libro.getSheetAt(0);
		return null;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 25/04/2014
	 * @description 
	 * @param libro
	 * @param data
	 * @throws AppBaseException
	 */
	private void llenarReporteAvanceEjecucionMapas(Workbook libro, List<MapaPruebasDTO> data) 
			throws AppBaseException {
		
		int filaCnt = ReporteExcelCons.POS_FILA_DATA_ENCABEZADO_AVANCE_MAPAS,
		    columnaCnt = 0;
		
		Sheet hoja = libro.getSheetAt(0);
		
		//Configurando el estilo de las celdas
		CellStyle estilo = libro.createCellStyle();
		setEstiloGenerico(estilo);
		
		//Estilo celdas porcentaje
		DataFormat format = libro.createDataFormat();
		CellStyle estiloPorcentaje =  libro.createCellStyle();
		setEstiloGenerico(estiloPorcentaje);
		estiloPorcentaje.setDataFormat(format.getFormat(ReporteExcelCons.PATRON_PORCENTAJE));
		
		//Estilo para las celdas con alineacion a la izquierda.
		CellStyle estiloAlineacionIzq = libro.createCellStyle();
		setEstiloGenerico(estiloAlineacionIzq);
		estiloAlineacionIzq.setAlignment(CellStyle.ALIGN_LEFT);
		
		Row fila;
		Cell celda;
		for(MapaPruebasDTO mapa : data) {	
			
			fila = hoja.createRow(filaCnt);
			
			//Nombre mapa de prueba:
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estiloAlineacionIzq);
            celda.setCellValue(mapa.getNombre());            
            
            //Pruebas construidas:
            columnaCnt++;
            String cellPruConstruidas = cellName(filaCnt+1, columnaCnt);
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            if(mapa.getTotalPruebasConstruidas() != null) {
            	celda.setCellValue(mapa.getTotalPruebasConstruidas());
            }
            
            //Pruebas ejecutadas:
            columnaCnt++;
            String cellPruEjecutadas = cellName(filaCnt+1, columnaCnt);
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            if(mapa.getTotalPruebasConstruidas() != null) {
            	celda.setCellValue(mapa.getTotalPruebasEjecutadas());
            }
            
            //Pruebas satisfactorias:
            columnaCnt++;
            String cellPruSatisfactorias = cellName(filaCnt+1, columnaCnt);
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            if(mapa.getTotalPruebasSatisfactorias() != null) {
            	celda.setCellValue(mapa.getTotalPruebasSatisfactorias());
            }
            
            //Pruebas insatisfactorias:
            columnaCnt++;
            String cellPruInsatisfactorias = cellName(filaCnt+1, columnaCnt);
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            if(mapa.getTotalPruebasInsatisfactorias() != null) {
            	celda.setCellValue(mapa.getTotalPruebasInsatisfactorias());
            }
            
            //Pruebas N.A.:
            columnaCnt++;
            String cellPruNA = cellName(filaCnt+1, columnaCnt);
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            if(mapa.getTotalPruebasAnuladas() != null) {
            	celda.setCellValue(mapa.getTotalPruebasAnuladas());
            }
            
            //Pruebas sin ejecutar:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            celda.setCellFormula(formulaPruebasSinEjecutar(cellPruConstruidas, cellPruEjecutadas, cellPruNA));
            
            //% Avance pruebas:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estiloPorcentaje);
            if(mapa.getTotalPruebasConstruidas() != 0) {
            	celda.setCellFormula(formulaPorcentajeAvance(cellPruConstruidas, cellPruEjecutadas, cellPruNA));
            } else {
            	celda.setCellValue(0);
            }
            
            
            //Indicador de Calidad ICF:
            columnaCnt++;
            celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estiloPorcentaje);
            //Crear esta condicion como una formula de excel.
            if(mapa.getTotalPruebasSatisfactorias() != 0) {
            	String formulaIndicadorCalidad = formulaIndicadorCalidad(cellPruInsatisfactorias, cellPruEjecutadas);
            	celda.setCellFormula(formulaIndicadorCalidad);
            } else {
            	celda.setCellValue(0d);
            }
            
            columnaCnt = 0;
            filaCnt++;
		}
		
		if(data.isEmpty()) {
			fila = hoja.createRow(filaCnt);
			createEmptyCells(fila, columnaCnt, estilo, 0, 9);
			filaCnt++;
		}

		SheetConditionalFormatting sheetCF = hoja.getSheetConditionalFormatting();
		ConditionalFormattingRule rule1 = sheetCF.createConditionalFormattingRule("MOD(ROW(),2)");

		PatternFormatting patternFmt = rule1.createPatternFormatting();
		patternFmt.setFillBackgroundColor(HSSFColor.ROSE.index);
		patternFmt.setFillPattern(PatternFormatting.SOLID_FOREGROUND);
		
		String range = buildRange(0, 
				ReporteExcelCons.POS_FILA_DATA_ENCABEZADO_AVANCE_MAPAS+1, 
				8,
				filaCnt);
		CellRangeAddress regions [] = {
				CellRangeAddress.valueOf(range)
		};
		sheetCF.addConditionalFormatting(regions, rule1);
		
		construirTotalesAvanceEjecucionMapas(libro, filaCnt);
		
	}

	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander
	 *         Filigrana Cardona</a>
	 * @date 14/04/2014
	 * @description
	 * @return
	 * @throws IOException
	 */
	public Properties getArchivoProperties() throws AppBaseException, IOException {
		return reporteProperties;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 16/04/2014
	 * @description 
	 * @param mapaPruebaDTO
	 * @return
	 * @throws AppBaseException
	 */
	@Deprecated
	public String construirNombreArchivoExcel(MapaPruebasDTO mapaPruebaDTO) throws AppBaseException {
		StringBuilder archivoNombre = new StringBuilder();
		DateFormat dateFormat = new SimpleDateFormat(ReporteExcelCons.FORMATO_FECHA_NOMBRE_ARCHIVO);
		archivoNombre.append(ReporteExcelCons.PREFIJO_MP);
		archivoNombre.append(ReporteExcelCons.PROYECTO);
		archivoNombre.append(dateFormat.format(mapaPruebaDTO.getFecCreacion()));
		archivoNombre.append("_");
		archivoNombre.append(mapaPruebaDTO.getNombre());
		return archivoNombre.toString();
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/05/2014
	 * @description 
	 * @param nombreMapa
	 * @param prefijoProyecto
	 * @param fechaCreacionMapa
	 * @return
	 * @throws AppBaseException
	 */
	public String construirNombreArchivoExcel(String nombreMapa, String prefijoProyecto, Date fechaCreacionMapa) 
			throws AppBaseException {
		StringBuilder archivoNombre = new StringBuilder();
		DateFormat dateFormat = new SimpleDateFormat(ReporteExcelCons.FORMATO_FECHA_NOMBRE_ARCHIVO);
		archivoNombre.append(ReporteExcelCons.PREFIJO_MP);
		archivoNombre.append(prefijoProyecto);
		archivoNombre.append(dateFormat.format(fechaCreacionMapa));
		archivoNombre.append("_");
		archivoNombre.append(nombreMapa);
		return archivoNombre.toString();
	}

	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander
	 *         Filigrana Cardona</a>
	 * @date 14/04/2014
	 * @description
	 * @param libro
	 */
	private void cargarLogo(Workbook libro) {
		Sheet hoja = libro.getSheetAt(0);
		InputStream inputStream = getClass()
				.getResourceAsStream(ReporteExcelCons.LOGO_FILE_NAME);

		if(inputStream != null) {
			try {
				// Se obtiene el contenido de un InputStream como un byte[]
				byte[] bytes = IOUtils.toByteArray(inputStream);
				// Agrega la imagen al workbook
				int pictureIdx = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
				inputStream.close();
				CreationHelper helper = libro.getCreationHelper();
				Drawing drawing = hoja.createDrawingPatriarch();
				// Crea un ancla que esta unida al worksheet
				ClientAnchor anchor = helper.createClientAnchor();
				// Establece la esquina superior izquierda de la imagen
				anchor.setCol1(1);
				anchor.setRow1(0);

				// Crea la imagen
				Picture pict = drawing.createPicture(anchor, pictureIdx);
				// Restablece la imagen al tamanio original
				pict.resize();
			} catch (IOException ioex) {
				LogUtil.log(getClass().getName(),
						"No se pudo cargar el archivo logoPremizeReporteExcel.png",
						Level.WARN, ioex);
			}
		}
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/04/2014
	 * @description 
	 * @param estilo
	 * @return
	 */
	private CellStyle setEstiloGenerico(CellStyle estilo) {
		estilo.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		estilo.setAlignment(CellStyle.ALIGN_CENTER);
		estilo.setWrapText(true);
		 // Bordes:
		estilo.setBorderBottom(CellStyle.BORDER_THIN);
		estilo.setBorderLeft(CellStyle.BORDER_THIN);
		estilo.setBorderRight(CellStyle.BORDER_THIN);
		estilo.setBorderTop(CellStyle.BORDER_THIN);
		return estilo;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014
	 * @description 
	 * @param fila
	 * @param columnaCnt
	 * @param estilo
	 * @param start
	 * @param size
	 * @return
	 */
	private int createStringCells(Row fila, Integer columnaCnt, CellStyle estilo, int start, int size) {
        for (int i = start; i < size; i++) {
            Cell celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            celda.setCellValue(ReporteExcelCons.STRING_CELDA_VACIA);
            columnaCnt++;
        }
        return columnaCnt;
    }
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 26/06/2014
	 * @description 
	 * @param fila
	 * @param columnaCnt
	 * @param estilo
	 * @param start
	 * @param size
	 * @return
	 */
	private int createEmptyCells(Row fila, Integer columnaCnt, CellStyle estilo, int start, int size) {
		for (int i = start; i < size; i++) {
            Cell celda = fila.createCell(columnaCnt);
            celda.setCellStyle(estilo);
            columnaCnt++;
        }
        return columnaCnt;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014
	 * @description 
	 * @param libro
	 * @param listaEncabezado
	 * @return
	 * @throws AppBaseException
	 */
	private void construirEncabezadoAvanceMapas(Workbook libro, List<String> listaEncabezado) 
			throws AppBaseException {
		
		Sheet hoja = libro.getSheetAt(0);
		Row filaEncabezado = hoja.createRow(ReporteExcelCons.POS_FILA_ENCABEZADO_AVANCE_MAPAS);
		filaEncabezado.setHeightInPoints(22.5f);

		HSSFPalette paleta = ((HSSFWorkbook) libro).getCustomPalette();
		paleta.setColorAtIndex(HSSFColor.DARK_RED.index, 
				(byte) 192, 
				(byte) 0,
				(byte) 0);
		
		paleta.setColorAtIndex(HSSFColor.ROSE.index,
				(byte) 242,
				(byte) 220,
				(byte) 219);

		CellStyle estilo = libro.createCellStyle();
		estilo.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		estilo.setAlignment(CellStyle.ALIGN_CENTER);
		estilo.setFillForegroundColor(HSSFColor.DARK_RED.index);
		estilo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		estilo.setWrapText(true);

		Font fuente = libro.createFont();
		fuente.setFontHeightInPoints((short) 8);
		fuente.setFontName("Arial");
		fuente.setBoldweight(Font.BOLDWEIGHT_BOLD);
		fuente.setColor(HSSFColor.WHITE.index);
		estilo.setFont(fuente);

		// Bordes:
		estilo.setBorderBottom(CellStyle.BORDER_THIN);
		estilo.setBorderLeft(CellStyle.BORDER_THIN);
		estilo.setBorderRight(CellStyle.BORDER_THIN);
		estilo.setBorderTop(CellStyle.BORDER_THIN);
		
		int i = 0;
		for (String nombre : listaEncabezado) {
			Cell celda = filaEncabezado.createCell(i);
			celda.setCellValue(nombre);
			celda.setCellStyle(estilo);
//			if(i > 0)
		    hoja.setColumnWidth(i, 13 * 256);
			
			i++;
		}
		hoja.setColumnWidth(0, (71 * 256));
		
//		return libro;
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 25/04/2014
	 * @description 
	 * @param libro
	 * @param rowIndex
	 * @throws AppBaseException
	 */
	private void construirTotalesAvanceEjecucionMapas(Workbook libro, int rowIndex) throws AppBaseException {
		Sheet hoja = libro.getSheetAt(0);
		
		int columnaCnt = 0, lastRowData = rowIndex - 1;
		
		CellStyle estilo = buildHeaderStyle(libro, rowIndex, 22.5f);
		
		//Estilo porcentaje:
		CellStyle estiloPorcentaje = buildHeaderStyle(libro, rowIndex, 22.5f);
		estiloPorcentaje.setDataFormat(libro.createDataFormat().getFormat(ReporteExcelCons.PATRON_PORCENTAJE));
		Row fila = hoja.getRow(rowIndex);
		Cell celda;
		
		// Label totales:
		celda =  fila.createCell(columnaCnt);
		celda.setCellValue(reporteProperties.getProperty("totalAvance"));
		celda.setCellStyle(estilo);
		
		String formula = null;
		String rango = null;
		
		// Total pruebas construidas;
		columnaCnt++;
		String tCellPC= cellName(rowIndex+1, columnaCnt);
		celda = fila.createCell(columnaCnt);
		celda.setCellStyle(estilo);
		rango = buildRange(columnaCnt, 
				(ReporteExcelCons.POS_FILA_DATA_ENCABEZADO_AVANCE_MAPAS+1), columnaCnt, (lastRowData+1));
		formula = ReporteExcelCons.EF_SUMA + "(" + rango + ")";
		celda.setCellFormula(formula);
		
		// Total pruebas ejecutadas;
		columnaCnt++;
		String tCellPE= cellName(rowIndex+1, columnaCnt);
		celda = fila.createCell(columnaCnt);
		celda.setCellStyle(estilo);
		rango = buildRange(columnaCnt, 
				(ReporteExcelCons.POS_FILA_DATA_ENCABEZADO_AVANCE_MAPAS+1), columnaCnt, (lastRowData+1));
		formula = ReporteExcelCons.EF_SUMA + "(" + rango + ")";
		celda.setCellFormula(formula);
		
		// Total pruebas satisfactorias;
		columnaCnt++;
		celda = fila.createCell(columnaCnt);
		celda.setCellStyle(estilo);
		rango = buildRange(columnaCnt, 
				(ReporteExcelCons.POS_FILA_DATA_ENCABEZADO_AVANCE_MAPAS+1), columnaCnt, (lastRowData+1));
		formula = ReporteExcelCons.EF_SUMA + "(" + rango + ")";
		celda.setCellFormula(formula);
		
		// Total pruebas insatisfactorias;
		columnaCnt++;
		String tCellPI= cellName(rowIndex+1, columnaCnt);
		celda = fila.createCell(columnaCnt);
		celda.setCellStyle(estilo);
		rango = buildRange(columnaCnt, 
				(ReporteExcelCons.POS_FILA_DATA_ENCABEZADO_AVANCE_MAPAS+1), columnaCnt, (lastRowData+1));
		formula = ReporteExcelCons.EF_SUMA + "(" + rango + ")";
		celda.setCellFormula(formula);
		
		// Total pruebas N.A.;
		columnaCnt++;
		String tCellPNA= cellName(rowIndex+1, columnaCnt);
		celda = fila.createCell(columnaCnt);
		celda.setCellStyle(estilo);
		rango = buildRange(columnaCnt, 
				(ReporteExcelCons.POS_FILA_DATA_ENCABEZADO_AVANCE_MAPAS+1), columnaCnt, (lastRowData+1));
		formula = ReporteExcelCons.EF_SUMA + "(" + rango + ")";
		celda.setCellFormula(formula);
		
		// Total pruebas sin ejecutar;
		columnaCnt++;
		celda = fila.createCell(columnaCnt);
		celda.setCellStyle(estilo);
		rango = buildRange(columnaCnt, 
				(ReporteExcelCons.POS_FILA_DATA_ENCABEZADO_AVANCE_MAPAS+1), columnaCnt, (lastRowData+1));
		formula = ReporteExcelCons.EF_SUMA + "(" + rango + ")";
		celda.setCellFormula(formula);
		
		// Total % avance pruebas;
		columnaCnt++;
		celda = fila.createCell(columnaCnt);
		celda.setCellStyle(estiloPorcentaje);
//		rango = buildRange(columnaCnt,(ReporteExcelCons.POS_FILA_DATA_ENCABEZADO_AVANCE_MAPAS+1), columnaCnt, (lastRowData+1));
		formula = formulaPorcentajeAvance(tCellPC, tCellPE, tCellPNA);
		celda.setCellFormula(formula);
		
		// Indicador de calidad ICF;
		columnaCnt++;
		celda = fila.createCell(columnaCnt);
		celda.setCellStyle(estiloPorcentaje);
		rango = buildRange(columnaCnt,
				(ReporteExcelCons.POS_FILA_DATA_ENCABEZADO_AVANCE_MAPAS+1), columnaCnt, (lastRowData+1));
		formula = ReporteExcelCons.EF_PROMEDIO + "(" + rango + ")";
		celda.setCellFormula(formula);
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 22/04/2014
	 * @description crea el estilo con el fondo rojo y los bordes
	 * @param libro
	 * @param rowHeight
	 * @return
	 * @throws AppBaseException
	 */
	private CellStyle buildHeaderStyle(Workbook libro, int rowIndex, float rowHeight) throws AppBaseException {
		Sheet hoja = libro.getSheetAt(0);
		Row filaEncabezado = hoja.createRow(rowIndex);
		filaEncabezado.setHeightInPoints(rowHeight);

		HSSFPalette paleta = ((HSSFWorkbook) libro).getCustomPalette();
		paleta.setColorAtIndex(HSSFColor.DARK_RED.index, 
				(byte) 192, 
				(byte) 0,
				(byte) 0);

		CellStyle estilo = libro.createCellStyle();
		estilo.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		estilo.setAlignment(CellStyle.ALIGN_CENTER);
		estilo.setFillForegroundColor(HSSFColor.DARK_RED.index);
		estilo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		estilo.setWrapText(true);

		Font fuente = libro.createFont();
		fuente.setFontHeightInPoints((short) 8);
		fuente.setFontName("Arial");
		fuente.setBoldweight(Font.BOLDWEIGHT_BOLD);
		fuente.setColor(HSSFColor.WHITE.index);
		estilo.setFont(fuente);

		// Bordes:
		estilo.setBorderBottom(CellStyle.BORDER_THIN);
		estilo.setBorderLeft(CellStyle.BORDER_THIN);
		estilo.setBorderRight(CellStyle.BORDER_THIN);
		estilo.setBorderTop(CellStyle.BORDER_THIN);
		
		return estilo;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 22/04/2014
	 * @description Crea un rango de celdas de excel. Ej: A1:B4
	 * @param col1
	 * @param fila1
	 * @param col2
	 * @param fila2
	 * @return
	 */
	private String buildRange(int col1, int fila1, int col2, int fila2) {
		StringBuilder str = new StringBuilder();
		str.append(CellReference.convertNumToColString(col1));
		str.append(fila1);
		str.append(":");
		str.append(CellReference.convertNumToColString(col2));
		str.append(fila2);
		return str.toString();
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 24/04/2014
	 * @description Devueve el nombre de la celada; Ej: "A1" o "D10"
	 * @param row
	 * @param column
	 * @return
	 */
	private String cellName(int row, int column) {
		return CellReference.convertNumToColString(column) + row;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 25/04/2014
	 * @description 
	 * @param pruebasConstruidas
	 * @param pruebasEjecutadas
	 * @param pruebasNA
	 * @return
	 */
	private String formulaPruebasSinEjecutar(String pruebasConstruidas, String pruebasEjecutadas,
			String pruebasNA) {
		StringBuilder str = new StringBuilder();
		// str = pruebasConstruidas - (pruebasEjecutadas + pruebasNA)
		str.append(pruebasConstruidas);
		str.append("-(");
		str.append(pruebasEjecutadas);
		str.append("+");
		str.append(pruebasNA);
		str.append(")");
		return str.toString();
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 25/04/2014
	 * @description 
	 * @param pruebasConstruidas
	 * @param pruebasEjecutadas
	 * @param pruebasNA
	 * @return
	 */
	private String formulaPorcentajeAvance(String pruebasConstruidas, String pruebasEjecutadas,
			String pruebasNA) {
		StringBuilder str = new StringBuilder();
		/*
		 * pruebasEjecutadas / (pruebasConstruidas - pruebasNA) ) * 100
		 * En este caso no se multiplica po 100 porque el formato de la
		 * celda viene con el formato porcentaje (%)
		 */
		str.append(pruebasEjecutadas);
		str.append("/(");
		str.append(pruebasConstruidas);
		str.append("-");
		str.append(pruebasNA);
		str.append(")");
		return str.toString();
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 28/04/2014
	 * @description Simula la condicion "SI" de Excel.
	 * @param condicion
	 * @param trueValue
	 * @param falseValue
	 * @return
	 */
	@Deprecated
	private String formulaIf(String condicion, Object trueValue, Object falseValue) {
		StringBuilder str = new StringBuilder();
		/*
		 * SI(condicion;trueValue;falseValue)
		 */
//	    SheetConditionalFormatting sheetCF = hoja.getSheetConditionalFormatting();
		str.append(ReporteExcelCons.EF_IF);
		str.append("(");
		str.append(condicion);
		str.append(";");
		str.append(trueValue);
		str.append(";");
		str.append(falseValue);
		str.append(")");
		return str.toString();
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 28/04/2014
	 * @description 
	 * @param pruebasInsatisfactorias
	 * @param pruebasEjecutadas
	 * @return
	 */
	private String formulaIndicadorCalidad(String pruebasInsatisfactorias, String pruebasEjecutadas) {
		StringBuilder str = new StringBuilder();
		/*
		 * (1 - (pruebasInsatisfactorias / pruebasEjecutasdas)) * 100
		 * En este caso no se multiplica po 100 porque el formato de la
		 * celda viene con el formato porcentaje (%)
		 */
		int n = 1;
		str.append("(");
		str.append(n);
		str.append("-(");
		str.append(pruebasInsatisfactorias);
		str.append("/");
		str.append(pruebasEjecutadas);
		str.append("))");
		return str.toString();
	}

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 21/04/2014
	 * @param listaHallazgos
	 * @return
	 */
	public Workbook construirReporteHallazgos(
			List<ConsultaHallazgoDTO> listaHallazgos) throws AppBaseException, IOException {
		Properties prop = getArchivoProperties();
		Workbook libro = createFormatoReportes(
				prop.getProperty("tituloHallazgos"),
				prop.getProperty("tituloHoja"));
		
		List<String> listaEncabezados = new ArrayList<String>();
		listaEncabezados.add(prop.getProperty("idHallazgo"));
		listaEncabezados.add(prop.getProperty("proyecto"));
		listaEncabezados.add(prop.getProperty("mapaPrueba"));
		listaEncabezados.add(prop.getProperty("artefacto"));
		listaEncabezados.add(prop.getProperty("usuarioArtefacto"));
		listaEncabezados.add(prop.getProperty("tipoHallazgo"));
		listaEncabezados.add(prop.getProperty("severidad"));
		listaEncabezados.add(prop.getProperty("prioridad"));
		listaEncabezados.add(prop.getProperty("titulo"));
		listaEncabezados.add(prop.getProperty("descripcion"));
		listaEncabezados.add(prop.getProperty("causaGeneracion"));
        listaEncabezados.add(prop.getProperty("usuarioCreador"));
        listaEncabezados.add(prop.getProperty("fechaCreacion"));
        listaEncabezados.add(prop.getProperty("usuarioSoluciona"));
		listaEncabezados.add(prop.getProperty("motivoReasigna"));
        listaEncabezados.add(prop.getProperty("usuarioActual"));
		listaEncabezados.add(prop.getProperty("estado"));
		listaEncabezados.add(prop.getProperty("fechaEstado"));
		listaEncabezados.add(prop.getProperty("causaAnulacion"));
		
     
    	libro = construirEncabezadoHallazgos(libro, listaEncabezados);
		llenarReporteHallazgos(libro, listaHallazgos);
		
		return libro;
	}

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 21/04/2014
	 * @param libro
	 * @param listaHallazgos
	 */
	private void llenarReporteHallazgos(Workbook libro,
			List<ConsultaHallazgoDTO> listaHallazgos)  throws AppBaseException{
		int filaCnt = POS_FILA_ENCABEZADO+1, registro = 1, columnaCnt = 0;
		Sheet hoja = libro.getSheetAt(0);
		
		//Configurando el estilo de las celdas
		CellStyle estilo = libro.createCellStyle();
		setEstiloGenerico(estilo);
		
		CellStyle estiloFecha = libro.createCellStyle();
		setEstiloGenerico(estiloFecha);
		CreationHelper ch = libro.getCreationHelper();
		estiloFecha.setDataFormat(ch.createDataFormat().getFormat(ReporteExcelCons.FORMATO_FECHA));
		
		CellStyle alignLeft = libro.createCellStyle();
		setEstiloGenerico(alignLeft);
		alignLeft.setAlignment(CellStyle.ALIGN_LEFT);
		
		Row fila;
		Cell celda;int idRegistroAnterior = -1;
        for (ConsultaHallazgoDTO hallazgo : listaHallazgos) {
            fila = hoja.createRow(filaCnt);

            if (hallazgo.getId() != idRegistroAnterior) {

                //idHallazgo:
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(hallazgo.getId());

                //proyecto:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(hallazgo.getProyecto());

                //mapaPrueba
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(hallazgo.getMapaprueba());
                
               //Artefacto
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(hallazgo.getArtefacto());
                
                //Usuario Artefacto
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(hallazgo.getUsuarioArtefacto());

                //tipoHallazgo:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(hallazgo.getTipoHallazgo());

                //severidad:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(hallazgo.getSeveridad());
                
                //prioridad:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(hallazgo.getPrioridad());

                //titulo:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(hallazgo.getTitulo());
                
                //descripcion:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(alignLeft);
                celda.setCellValue(hallazgo.getDescripcion());
                
                //causaGeneracion:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(hallazgo.getCausagenerastring());
                
                //usuarioCreacion:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(hallazgo.getUsuariocrea());
                
                //fechaCreacion:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estiloFecha);
                if (hallazgo.getFechaCreacionString() != null) {
                    celda.setCellValue(hallazgo.getFechaCreacionString());
                }
                
                //usuarioSoluciona:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estiloFecha);
                celda.setCellValue(hallazgo.getUsuarioSoluciona());
                

                //motivo:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(hallazgo.getMotivoreasignacion());
                
                //usuarioActual:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estiloFecha);
                celda.setCellValue(hallazgo.getUsuarioasignado());
                
                //estado:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(hallazgo.getAccion());
                
               //fechaActualizacion:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estiloFecha);
                if (hallazgo.getFechaEditaString() != null) {
                    celda.setCellValue(hallazgo.getFechaEditaString());
                }
                
               //causaAnulacion:
                columnaCnt++;
                celda = fila.createCell(columnaCnt);
                celda.setCellStyle(estilo);
                celda.setCellValue(hallazgo.getCausaanulastring());
                
               
               
                registro++;
            } else {
                columnaCnt = createStringCells(fila, columnaCnt, estilo, 0, 10);
                columnaCnt--; //el metodo createEmptyCells aumenta columnaCnt al final.
            }
         
            columnaCnt = 0;
            filaCnt++;
		}
		
		//Configurando en acho de la celda fechaCreacion:
		//hoja.autoSizeColumn(5);
		//hoja.autoSizeColumn(8);
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 30/04/2014
	 * @description 
	 * @param nombreMapa
	 * @return
	 * @throws AppBaseException
	 * @throws IOException
	 */
	public Workbook createWorkbookReporteMapaPruebas(String nombreMapa) throws AppBaseException, IOException {
		Properties prop = getArchivoProperties();
		Workbook libro = createFormatoReportes(prop.getProperty("tituloReporteMapas"),nombreMapa);
		
		List<String> listaEncabezados = new ArrayList<String>();
		listaEncabezados.add(prop.getProperty("No"));
		listaEncabezados.add(prop.getProperty("artefacto"));
		listaEncabezados.add(prop.getProperty("descripcionPrueba"));
		listaEncabezados.add(prop.getProperty("tipoPrueba"));
		listaEncabezados.add(prop.getProperty("resultado"));
		listaEncabezados.add(prop.getProperty("fechaCreacion"));
		listaEncabezados.add(prop.getProperty("usuarioCreador"));
		listaEncabezados.add(prop.getProperty("usuarioEjecutor"));
		listaEncabezados.add(prop.getProperty("fechaEjecucion"));
		listaEncabezados.add(prop.getProperty("cumple"));
        listaEncabezados.add(prop.getProperty("numeroHallazgo"));
        listaEncabezados.add(prop.getProperty("tituloHallazgo"));
        listaEncabezados.add(prop.getProperty("descripcionHallazgo"));
        listaEncabezados.add(prop.getProperty("tipoHallazgo"));
        listaEncabezados.add(prop.getProperty("severidad"));
        listaEncabezados.add(prop.getProperty("prioridad"));
        listaEncabezados.add(prop.getProperty("responsableRealizarAjustes"));
        listaEncabezados.add(prop.getProperty("causaGeneracion"));
        listaEncabezados.add(prop.getProperty("causaAnulacion"));
        listaEncabezados.add(prop.getProperty("estadoHallazgo"));
        listaEncabezados.add(prop.getProperty("fechaEstadoHallazgo"));
		
		libro = construirEncabezado(libro, listaEncabezados);
		
		return libro;
	}

}
