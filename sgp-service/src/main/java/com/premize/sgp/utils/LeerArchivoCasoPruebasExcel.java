package com.premize.sgp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.sgp.dto.parametros.ArtefactoDTO;
import com.premize.sgp.dto.parametros.ParametroDTO;
import com.premize.sgp.dto.pruebas.CargueCasoPruebaDTO;
import com.premize.sgp.dto.pruebas.CasoDePruebaDTO;
import com.premize.sgp.facade.parametros.ArtefactoFacade;
import com.premize.sgp.facade.pruebas.MapaPruebaFacade;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class LeerArchivoCasoPruebasExcel
 * @since 27/03/2014
 *
 */
// TODO Mar 21, 2014 LeerArchivoCasoPruebasExcel revisar optimizar esta logica
@Service
public class LeerArchivoCasoPruebasExcel {
	
	protected final Log logger = LogFactory.getLog(getClass());
	private final Integer CARGUE_VALIDO = 1;
	private final Integer CARGUE_NO_VALIDO = 0;
	
	// TODO Mar 21, 2014 LeerArchivoCasoPruebasExcel PILAS!!!! Se esta usando un facade desde una supuesta clase de
	// service
	@Autowired
	private MapaPruebaFacade mapaPruebaFacade;
	
	// TODO Mar 21, 2014 LeerArchivoCasoPruebasExcel PILAS!!!! Se esta usando un facade desde una supuesta clase de
	// service
	@Autowired
	private ArtefactoFacade artefactoFacade;
	
	/**
	 * si es de tipo 1 es String, 0 es numerico.
	 */
	public final static int CELL_DESCRIPCION_TIPO = 1;
	public final static int CELL_ARTEFACTO_TIPO = 0;
	public final static int CELL_RESULTADO_TIPO = 1;
	public final static int CELL_TIPO_PRUEBA_TIPO = 0;
	/**
	 * Campos requeridos
	 */
	public final static boolean CELL_DESCRIPCION_REQUIRED = true;
	public final static boolean CELL_ARTEFACTO_REQUIRED = true;
	public final static boolean CELL_RESULTADO_REQUIRED = true;
	public final static boolean CELL_TIPO_PRUEBA_REQUIRED = true;
	/***
	 * orden de las columnas del archivo de excel
	 */
	public final static int CANTIDAD_COLUMNAS = 4;
	/***
	 * orden de las columnas del archivo de excel
	 */
	public final static int COL_CELL_ARTEFACTO = 1;
	public final static int COL_CELL_TIPO_PRUEBA = 2;
	public final static int COL_CELL_DESCRIPCION = 3;
	public final static int COL_CELL_RESULTADO = 4;
	
	private final static String MSG_FALLO_CANTIDAD_COLUMNAS = "El número de columnas es incorrecto se esperaban: ";
	
	private static final String MSG_FALLO_REQUIERED = "El valor es requerido para la columna número ";
	
	private static final String MSG_FALLLO_FORMATO = "El formato ingresado no es válido para la columna número ";
	
	private static final String MSG_FALLLO_FORMATO_NUMERICO = "El formato ingresado debe de ser del tipo numérico para el campo ";
	
	private static final String MSG_FALLO_CAMPO_NO_EXISTE = "No se encontró registro para el código ";
	
	private static final String COL_DESCRIPCION = "Descripción";
	private static final String COL_ARTEFACTO = "Artefacto";
	private static final String COL_MAPA_PRUEBAS = "Mapa pruebas";
	private static final String COL_RESULTADO = "Resultado";
	private static final String COL_TIPO_PRUEBA = "Tipo prueba";
	private static final String COL_CUMPLE = "Cumple";
	
	/**
	 * Permite leer el archivo de Caso de prueba de una ruta especifica, valida si los campos de calas celdas
	 * corresponden al tipo de valor correspondiente si es el caso lo toma segun su tipo y lo consulta en la BD si es
	 * necesario para obetener la realcion y la relacion no corresponde la consulta return un null y se pone un mensaje
	 * fallo.
	 * 
	 * @author Hernan David Ramirez Mejia
	 * @since 19/02/2014
	 * @param urlFile
	 * @return List<CasoDePruebaDTO>
	 */
	public CargueCasoPruebaDTO leerArchivoCasoPrueba(InputStream archivoImportar, String login) throws AppBaseException {
		
//		InputStream fis = null;
		CargueCasoPruebaDTO datosCargadosDTO = new CargueCasoPruebaDTO();
		List<CasoDePruebaDTO> listaCasoPruebasDTO = new ArrayList<CasoDePruebaDTO>();
		List<CasoDePruebaDTO> listaCasoPruebasLogDTO = new ArrayList<CasoDePruebaDTO>();
		boolean estadoRegistro;
		
		datosCargadosDTO.setEstadoCargueArchivo(CARGUE_VALIDO);
		try {
			
			// ByteArrayInputStream byteArrayInputStream = new
			// ByteArrayInputStream(fileData);
//			fis = archivoImportar;
			Workbook wb = WorkbookFactory.create(archivoImportar);
			Sheet sheet = wb.getSheetAt(0);
			Iterator rows = sheet.rowIterator();
			
			// datos del cargue del archivo
			Integer filasLeidas = 0;
			Integer filasCargadasBien = 0;
			Integer filasConFallos = 0;
			
			
			// validacion de estructura de archivo
			while (rows.hasNext()) {
				Row row = ((Row) rows.next());
				if (row.getRowNum() == 0) {
					// valida que todas las filas del archivo existan
					if (row.getLastCellNum() != CANTIDAD_COLUMNAS) {
						datosCargadosDTO.setEstadoCargueArchivo(CARGUE_NO_VALIDO);
						datosCargadosDTO.setMsgFalloCargue(MSG_FALLO_CANTIDAD_COLUMNAS + CANTIDAD_COLUMNAS);
					}
					break;
				}
			}
			
			if (datosCargadosDTO.getEstadoCargueArchivo() == CARGUE_VALIDO) {
				
				// Recorre todas las filas
				while (rows.hasNext()) {
					
					Row row = ((Row) rows.next());
					Iterator cells = row.cellIterator();
					List<Cell> cellTempList = new ArrayList<Cell>();
					int currentCell = 0;
					int nextCell = 1;
					int numeroCol = 1;
					CasoDePruebaDTO casoPrueba = new CasoDePruebaDTO();
					List<String> listaErroresPorCasoPrueba = new ArrayList<String>();
					estadoRegistro = true;
					
					// llenamos una lista temporal con los valores de cada
					// columna
					while (cells.hasNext()) {
						Cell cell = (Cell) cells.next();
						currentCell = cell.getColumnIndex();
						
						if (currentCell < nextCell) {
							// Condicion satisfecha
						}
						// adicionando columnas nulas
						else {
							int loop = currentCell - nextCell;
							for (int k = 0; k < loop + 1; k++) {
								cellTempList.add(null);
								nextCell = nextCell + 1;
							}
						}
						
						cellTempList.add(cell);
						nextCell = nextCell + 1;
					}
					
					// Recorre las Columnas
					for (Cell cell : cellTempList) {
						
						// Valida la estructura del campo
						if (!validarRequiredCell(cell, numeroCol)) {
							listaErroresPorCasoPrueba.add(MSG_FALLO_REQUIERED + numeroCol);
							estadoRegistro = false;
						} else if (!validarTipoCampoCell(cell, numeroCol)) {
							listaErroresPorCasoPrueba.add(MSG_FALLLO_FORMATO + numeroCol);
							estadoRegistro = false;
						}
						
						// Valida el dato
						if (estadoRegistro && cell != null) {
							
							if (numeroCol == 1) {
								// Campo Artefacto
								Integer idArtefacto = (int) cell.getNumericCellValue();
								ArtefactoDTO artefactoDTO = null;
								artefactoDTO = artefactoFacade.obtenerArtefacto(idArtefacto);
								
								if (artefactoDTO == null || artefactoDTO.getId() == null) {
									listaErroresPorCasoPrueba.add(MSG_FALLO_CAMPO_NO_EXISTE + " " + idArtefacto + " "
											+ COL_ARTEFACTO);
									estadoRegistro = false;
								}
								casoPrueba.setArtefacto(artefactoDTO);
							}
							
							else if (numeroCol == 2) {
								// Campo Tipo Prueba
								Integer idTipoPrueba = (int) cell.getNumericCellValue();
								casoPrueba.setTipoPrueba(new ParametroDTO(idTipoPrueba));
							}
							
							else if (numeroCol == 3) {
								// Campo Descripcion
								String descripcion = cell.getStringCellValue();
								casoPrueba.setDescripcion(descripcion);
							}
							
							else if (numeroCol == 4) {
								// Campo Resultado
								String resultado = cell.getStringCellValue();
								casoPrueba.setResultado(resultado);
							}
							
						}
						
						numeroCol++;
					}
					// Fin ciclo de las columnas
					
					filasLeidas++;
					casoPrueba.setNumeroRegistro(filasLeidas);
					casoPrueba.setUsuarioCreacion(login);
					
					if (estadoRegistro) {
						filasCargadasBien++;
						listaCasoPruebasDTO.add(casoPrueba);
					} else {
						filasConFallos++;
						listaCasoPruebasLogDTO.add(casoPrueba);
						casoPrueba.setErroresCargue(listaErroresPorCasoPrueba);
					}
					
				}
				// fin ciclo de las filas
				
				// Resumen del cargue
				datosCargadosDTO.setListaDatosCargados(listaCasoPruebasDTO);
				datosCargadosDTO.setListaDatosCargadosFallidos(listaCasoPruebasLogDTO);
				datosCargadosDTO.setRegistrosCargadosBien(filasCargadasBien);
				datosCargadosDTO.setRegistrosConFallos(filasConFallos);
				datosCargadosDTO.setRegistrosLeidos(filasLeidas);
				// este estado nos permitira saber si enviamos a guardar los
				// registros o no
				if (filasConFallos > 0) {
					datosCargadosDTO.setEstadoCargueArchivo(CARGUE_NO_VALIDO);
				} else {
					datosCargadosDTO.setEstadoCargueArchivo(CARGUE_VALIDO);
				}
			}
			
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new AppBaseException("El sistema no puede encontrar el archivo especificado", "0");
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			
		}
		
		return datosCargadosDTO;
	}
	
	/**
	 * Validacion por tipo formato campo, cada campo del archivo de excel como se va a leer posee un orden nosotros lo
	 * controlamos con COL_CELL_"NOMBRE DEL CAMPO" y el tipo que debe de llevar ese campo con
	 * CELL_"NOMBRE DEL CAMPO"_TIPO esto nos permite saber para que campo que tipo de valor se debe ingresar, si el
	 * campo ingresado no corresponde para la columna return un false.
	 * 
	 * @fecha 19/02/2014
	 * @autor Hernan David Ramirez Mejia
	 * @param cell
	 * @param campo
	 * @return
	 */
	private boolean validarTipoCampoCell(Cell cell, int campo) {
		boolean respuesta = false;
		
		if ((campo == COL_CELL_DESCRIPCION)) {
			if (cell != null && cell.getCellType() == CELL_DESCRIPCION_TIPO)
				respuesta = true;
		} else if ((campo == COL_CELL_ARTEFACTO)) {
			if (cell != null && cell.getCellType() == CELL_ARTEFACTO_TIPO)
				respuesta = true;
		} else if ((campo == COL_CELL_TIPO_PRUEBA)) {
			if (cell != null && cell.getCellType() == CELL_TIPO_PRUEBA_TIPO)
				respuesta = true;
		} else if ((campo == COL_CELL_RESULTADO)) {
			if (cell != null && cell.getCellType() == CELL_RESULTADO_TIPO)
				respuesta = true;
		}
		
		return respuesta;
	}
	
	/**
	 * Valida si el campo es requerido, cada campo del archivo de excel como
	 * 
	 * @fecha 19/02/2014
	 * @autor Hernan David Ramirez Mejia
	 * @param cell
	 * @param campo
	 * @return
	 */
	private boolean validarRequiredCell(Cell cell, int campo) {
		boolean respuesta = false;
		
		if ((campo == COL_CELL_DESCRIPCION)) {
			if (Boolean.toString(CELL_DESCRIPCION_REQUIRED).toLowerCase() == "true") {
				if (cell != null && cell.getCellType() != HSSFCell.CELL_TYPE_BLANK)
					respuesta = true;
			}
		} else if ((campo == COL_CELL_ARTEFACTO)) {
			if (Boolean.toString(CELL_ARTEFACTO_REQUIRED).toLowerCase() == "true") {
				if (cell != null && cell.getCellType() != HSSFCell.CELL_TYPE_BLANK)
					respuesta = true;
			}
		} else if ((campo == COL_CELL_TIPO_PRUEBA)) {
			if (Boolean.toString(CELL_TIPO_PRUEBA_REQUIRED).toLowerCase() == "true") {
				if (cell != null && cell.getCellType() != HSSFCell.CELL_TYPE_BLANK)
					respuesta = true;
			}
		} else if ((campo == COL_CELL_RESULTADO)) {
			if (Boolean.toString(CELL_RESULTADO_REQUIRED).toLowerCase() == "true") {
				if (cell != null && cell.getCellType() != HSSFCell.CELL_TYPE_BLANK)
					respuesta = true;
			}
		}
		
		return respuesta;
	}
	
}
