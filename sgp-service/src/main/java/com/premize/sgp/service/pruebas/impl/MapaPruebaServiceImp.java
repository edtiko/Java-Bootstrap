/**
 * 
 */
package com.premize.sgp.service.pruebas.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.service.AbstractEntityService;
import com.premize.sgp.dao.gestion.pruebas.IndicadoresDao;
import com.premize.sgp.dao.gestion.pruebas.MapaArtefactoDao;
import com.premize.sgp.dao.gestion.pruebas.MapaPruebasDao;
import com.premize.sgp.dao.parametros.ArtefactoDao;
import com.premize.sgp.dao.parametros.ProyectoDao;
import com.premize.sgp.dao.parametros.SgpParametroDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.IndicadoresDTO;
import com.premize.sgp.dto.pruebas.MapaPruebasDTO;
import com.premize.sgp.dto.pruebas.ProyectoDTO;
import com.premize.sgp.dto.pruebas.ReporteMapaPruebaDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndReprocesoConstruccionPruebasDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndicadorDTO;
import com.premize.sgp.modelo.entities.artefacto.SgpMapaArtefacto;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpMapaPrueba;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpProyecto;
import com.premize.sgp.modelo.entities.parametros.SgpArtefacto;
import com.premize.sgp.service.pruebas.MapaPruebaService;
import com.premize.sgp.service.pruebas.ProyectoService;
import com.premize.sgp.utils.DateUtils;
import com.premize.sgp.utils.IndicadoresUtils;
import com.premize.sgp.utils.ListSelector;
import com.premize.sgp.utils.LogUtil;
import com.premize.sgp.utils.ReporteExcel;
import com.premize.sgp.utils.ReporteExcelCons;
import com.premize.sgp.utils.ZipBuilder;

/**
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-service
 * @class MapaPruebaServiceImp
 * @since 22/01/2014
 *
 */
@Service
public class MapaPruebaServiceImp extends AbstractEntityService<SgpMapaPrueba,Integer> implements MapaPruebaService {

	private static final Integer ACTIVO = 1;
	private static final int INDICADOR_REPROCESO_CONSTRUCCION_PRUEBAS_ID = 6;
	
	@Autowired
	private MapaPruebasDao mapaPruebasDao;
	
	@Autowired
	private ArtefactoDao artefactoDao;
	
	@Autowired
	private MapaArtefactoDao mapaArtefactoDao;
	
	@Autowired
	private ProyectoDao proyectoDao;
	
	@Autowired
	private SgpParametroDao parametroDao;
	
	@Autowired
	private ReporteExcel reporteExcel;
	
	@Autowired
	private ProyectoService proyectoService;
	
	@Autowired
	private IndicadoresDao indicadoresDao;
	
	private static final Logger LOG = Logger.getLogger(MapaPruebaServiceImp.class);
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 22/01/2014
	 * @see com.premize.pmz.service.AbstractEntityService#getDao()
	 */
	@Override
	public MapaPruebasDao getDao() {
		return mapaPruebasDao;
	}
	
	/**
	 * Metodo a partir del cual se crear un Mapa de Prueba 
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 22/01/2014
	 * @see com.premize.sgp.service.pruebas.MapaPruebaService#guardarMapaPrueba(com.premize.sgp.dto.pruebas.MapaPruebasDTO)
	 */
	@Override
	public void guardarMapaPrueba(MapaPruebasDTO mapaPruebasDTO)
			throws AppBaseException {
		
		try {
			SgpProyecto proyecto = proyectoDao.get(mapaPruebasDTO.getProyecto().getId());
			SgpMapaPrueba nuevoMapa = new SgpMapaPrueba();
			nuevoMapa.setNombre(mapaPruebasDTO.getNombre());
			nuevoMapa.setFecCreacion(Calendar.getInstance().getTime());
			nuevoMapa.setIndActivo(mapaPruebasDTO.getNumeroEstado());
			nuevoMapa.setUsuarioCrea(mapaPruebasDTO.getUsuarioCreacion());
			nuevoMapa.setSgpProyecto(proyecto);
			
			mapaPruebasDao.save(nuevoMapa);
		} catch (AppBaseException e) {
			throw e;
		}		
	}



	@Override
	public List<MapaPruebasDTO> consultarMapaPruebasPorProyecto(
			Integer idProyecto, Paginator page) throws AppBaseException {

		return  getDao().consultarMapaPruebasPorProyecto(idProyecto, page);
	}
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 11/02/2014
	 * @see com.premize.sgp.service.pruebas.MapaPruebaService#actualizarArtefactosPorMapa(List, java.lang.String, String)
	 */
	@Override
	public void actualizarArtefactosPorMapa(List<String> artefactos,String idMapa, String login) throws Exception {
		try{
			List<SgpMapaArtefacto> listadoMapaArtefactos = new ArrayList<SgpMapaArtefacto>();
			Integer mapa = new Integer(idMapa);
			SgpMapaPrueba mapaPrueba =  mapaPruebasDao.get(mapa);
			//Inactivamos todas los artefactos que ya esten asociados a un mapa de prueba.
			mapaArtefactoDao.desasociarArtefactosMapa(mapa,login);
			
			// A partir del listado de DTOS armo los mapa Artefactos que se van a guardar,
			// Se deja el save en el dao para evitar viajes innecesarios en el service.
			for (String idArtefacto : artefactos) {
				SgpArtefacto artefacto = new SgpArtefacto();
				artefacto = artefactoDao.get(new Integer(idArtefacto));
				
				SgpMapaArtefacto mapaArtefacto = new SgpMapaArtefacto();
				mapaArtefacto.setFecCreacion(Calendar.getInstance().getTime());
				mapaArtefacto.setIndActivo(ACTIVO);
				mapaArtefacto.setUsuarioCrea(login);
				mapaArtefacto.setSgpMapaPrueba(mapaPrueba);
				mapaArtefacto.setSgpArtefacto(artefacto);
				
				listadoMapaArtefactos.add(mapaArtefacto);
			}
			
			mapaArtefactoDao.asociarArtefactosPorMapa(listadoMapaArtefactos);
		}catch(Exception e){
			throw e;
		}
		
	}

	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 12/02/2014
	 * @see com.premize.sgp.service.pruebas.MapaPruebaService#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<MapaPruebasDTO> getRecords(PagingCriteria criteria)
			throws AppBaseException {
		return mapaPruebasDao.getRecords(criteria);
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 2/07/2014
	 * @description 
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException
	 */
	@Override
	public ResultSet<MapaPruebasDTO> getRecordsEjecucion(PagingCriteria criteria)
			throws AppBaseException, ParseException {
		
		ResultSet<MapaPruebasDTO> mapasPruebas= mapaPruebasDao.getRecordsAvanceEjecucion(criteria);

		for(MapaPruebasDTO pruebas:mapasPruebas.getRows()){
			SgpProyecto proyecto=proyectoDao.get(pruebas.getIdProyecto());
			pruebas.setProyecto(new ProyectoDTO(proyecto));
		}
		
		
//		for (MapaPruebasDTO pruebas : mapasPruebas) {
//			
//		}
		return mapasPruebas;
	}

	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 13/02/2014
	 * @see com.premize.sgp.service.pruebas.MapaPruebaService#getMapaDTO(java.lang.Integer)
	 */
	@Override
	public MapaPruebasDTO getMapaDTO(Integer idMapa) throws AppBaseException {
		SgpMapaPrueba mapaPrueba = mapaPruebasDao.get(idMapa);
		MapaPruebasDTO dto = new MapaPruebasDTO(mapaPrueba); 
				
		return dto;
	}

	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 13/02/2014
	 * @see com.premize.sgp.service.pruebas.MapaPruebaService#editarMapaPrueba(com.premize.sgp.dto.pruebas.MapaPruebasDTO)
	 */
	@Override
	public void editarMapaPrueba(MapaPruebasDTO mapaDTO) throws AppBaseException {
		try {
			SgpMapaPrueba mapa = mapaPruebasDao.get(mapaDTO.getId());
			SgpProyecto proyecto = proyectoDao.get(mapaDTO.getProyecto().getId());
			
			mapa.setUsuarioEdita(mapaDTO.getUsuarioEdita());
			mapa.setFecEdita(Calendar.getInstance().getTime());
			mapa.setIndActivo(mapaDTO.getNumeroEstado());
			mapa.setNombre(mapaDTO.getNombre());
			mapa.setSgpProyecto(proyecto);
			
			mapaPruebasDao.update(mapa);
		} catch (AppBaseException e) {
			throw e;
		}
	}

	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 8/03/2014
	 * @see com.premize.sgp.service.pruebas.MapaPruebaService#eliminarArtefactoMapa(java.lang.Integer)
	 */
	@Override
	public void eliminarArtefactoMapa(Integer id) throws AppBaseException {
		mapaArtefactoDao.deleteById(id);
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 11/04/2014
	 * @description 
	 * @param idMapaPrueba
	 * @return
	 * @throws AppBaseException
	 * @throws IOException
	 */
	@Override
	public Map<String,Object> generarMatrizContruccionPruebasExcel(Integer idMapaPrueba) throws AppBaseException, IOException {
		
		if(idMapaPrueba == null) {
			throw new AppBaseException("El id del mapa de prueba es nulo","NO_PARAMETRO");
		}
		List<ReporteMapaPruebaDTO> listaCasosPrueba = mapaPruebasDao.consultarCasosPruebaHallazgos(idMapaPrueba);
		SgpMapaPrueba mapaPruebaModel = mapaPruebasDao.get(idMapaPrueba);
		String nombreMapaPrueba = mapaPruebaModel.getNombre();
		Workbook libro = reporteExcel.construirReporteMapaPruebas(listaCasosPrueba, nombreMapaPrueba);
		String prefijoProyecto = proyectoService.construirPrefijoProyecto(mapaPruebaModel.getSgpProyecto().getNombre());
		String reporteFileName = reporteExcel
				.construirNombreArchivoExcel(nombreMapaPrueba, prefijoProyecto, mapaPruebaModel.getFecCreacion())
				+ ReporteExcelCons.EXT_XLS;
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put(ReporteExcelCons.KEY_MAP_REPORTE, libro);
		map.put(ReporteExcelCons.KEY_MAP_FILENAME, reporteFileName);
		return map;
	}

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws ParseException 
	 * @since 14/04/2014
	 * @see com.premize.sgp.service.pruebas.MapaPruebaService#getTotalesMapaPruebaProyecto(java.lang.Integer)
	 */
	@Override
	public MapaPruebasDTO getTotalesMapaPruebaProyecto(Integer idProyecto, String fechaFrom, String fechaTo, String tipoHallazgo)
			throws AppBaseException, ParseException {
		return mapaPruebasDao.getTotalesMapaPruebaProyecto(idProyecto, fechaFrom, fechaTo, tipoHallazgo);
	}

	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 24/04/2014
	 * @description 
	 * @param parametros
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException
	 */
	@Override
	public Map<String,Object> generarReporteAvanceEjecucionMapas(Map<String,Object> parametros) 
			throws AppBaseException, ParseException {
		String keyIdProyecto = "idProyecto";
		
		Object idProyecto = parametros.get(keyIdProyecto);
		if(idProyecto == null) {
			throw new AppBaseException("El id de proyecto es nulo", "ID_PROYECTO_NULO");
		}
		
		Integer idProyectoInt = Integer.parseInt(idProyecto.toString());
		parametros.put(keyIdProyecto, idProyectoInt);
		
		List<MapaPruebasDTO> mapas = mapaPruebasDao.consultarAvanceEjecucionMapas(parametros);
		String nombreProyecto = proyectoDao.get(idProyectoInt).getNombre();
		Workbook libro = reporteExcel.construirReporteAvanceEjcucionMapas(mapas, nombreProyecto);
		StringBuilder builder = new StringBuilder();
		try {
			builder.append(reporteExcel.getArchivoProperties().getProperty("fileNameReporteAvanceEjecucionMapas"));
			builder.append(nombreProyecto);
			builder.append(ReporteExcelCons.EXT_XLS);
		} catch (IOException e) {
			// modificar esta linea:
			builder.append(nombreProyecto+ReporteExcelCons.EXT_XLS);
			LOG.error(e.getMessage(), e);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(ReporteExcelCons.KEY_MAP_REPORTE, libro);
		map.put(ReporteExcelCons.KEY_MAP_FILENAME, builder.toString());
		return map;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 30/04/2014
	 * @description 
	 * @param filtros
	 * @param directorio
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException
	 * @throws IOException
	 */
	@Override
	public Map<String, Object> generarMapasZip(Map<String, Object> filtros, String directorio) 
			throws AppBaseException, ParseException, IOException {
		String keyIdProyecto = "idProyecto";
		if(filtros == null) {
			throw new AppBaseException("El Map filtros es nulo","NO_PARAMETRO");
		}
		
		Object idProyecto = filtros.get(keyIdProyecto);
		if(idProyecto == null) {
			throw new AppBaseException("El id de proyecto es nulo", "NO_PARAMETRO");
		}
		Integer idProyectoInt = Integer.parseInt(idProyecto.toString());
		filtros.put(keyIdProyecto, idProyectoInt);
		
		if(directorio == null || directorio.isEmpty()) {
			throw new AppBaseException("El directorio para almacenar los mapas comprimidos es nulo", "NO_PARAMETRO");
		}
		directorio = directorio + File.separator + ZIP_FOLDER;
		
		List<ReporteMapaPruebaDTO> listaMapas = mapaPruebasDao.consultarMapaPruebasReporte(filtros);
		List<ListSelector> selectorListaMapas = getListSelectors(listaMapas);
		
		Workbook libro = null;
		String zipFolder = ZipBuilder.createString();
		String nombreProyecto = proyectoDao.get(idProyectoInt).getNombre();
		String prefijoProyecto = proyectoService.construirPrefijoProyecto(nombreProyecto);
		ZipBuilder zipBuilder = new ZipBuilder(directorio, zipFolder, nombreProyecto);
		String nombreMapa;
		Date fechaCreacionMapa;
		ReporteMapaPruebaDTO mapaDto;
		for(ListSelector ls : selectorListaMapas) {
			mapaDto = listaMapas.get(ls.getStart());
			nombreMapa = mapaDto.getNombreMapa();
			fechaCreacionMapa = mapaDto.getFechaCreacionMapa();
			libro = reporteExcel.construirReporteMapaPruebasZip(listaMapas, ls, nombreMapa);
			String excelFileName = reporteExcel.construirNombreArchivoExcel(nombreMapa, prefijoProyecto, fechaCreacionMapa);
			excelFileName = excelFileName + ReporteExcelCons.EXT_XLS;
			String location = zipBuilder.getSourceFolder() + File.separator + excelFileName;
			saveWorkbook(libro, location);
			zipBuilder.compressFile(excelFileName, true);
		}
		zipBuilder.done();
		
		Map<String, Object> zip = new HashMap<String, Object>();
		zip.put("zip", zipBuilder.getZipStream());
		zip.put("zipName", nombreProyecto + ZipBuilder.EXT_ZIP);
		return zip;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 30/04/2014
	 * @description Este metodo es reemplazado por el metodo: getListSelectors(...)
	 * @param listaReporte
	 * @return
	 * @throws AppBaseException
	 */
	@Deprecated
	private List<ListSelector> getIdMapas(List<ReporteMapaPruebaDTO> listaReporte) throws AppBaseException  {
		List<ListSelector> listaMapas = new ArrayList<ListSelector>();
		Integer lastIdMapa = null;
		Integer idMapa = null;
		Boolean inicio = true;
		if(!listaReporte.isEmpty()) {
			lastIdMapa = listaReporte.get(0).getIdMapaPrueba();
		}
//		Workbook libro = null;
		ListSelector ls = new ListSelector();
		for(int i = 0; i < listaReporte.size(); i++) {
			idMapa = listaReporte.get(i).getIdMapaPrueba();
			
			if(lastIdMapa == idMapa) {
				if(inicio) {
					ls = new ListSelector(idMapa,i,null);
					listaMapas.add(ls);
					inicio = false;
				}
			} else {
				ls.setEnd(i);
				inicio = true;
				i--;
			}
			lastIdMapa = idMapa;
		}
		
		return listaMapas;
	}
	

	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 2/05/2014
	 * @description De la lista que contiene todos los registros para crear todos los reportes en excel que
	 * seran comprimidos, este metodo crea una proyecccion o una seleccion para iterar la busqueda para cada
	 * mapa de prueba y poder generar los reportes individuales en excel.
	 * @param listaReporte
	 * @return
	 * @throws Exception
	 */
	@Override
    public List<ListSelector> getListSelectors(List<ReporteMapaPruebaDTO> listaReporte) throws AppBaseException {
        List<ListSelector> selectors = new ArrayList<ListSelector>();
        ListSelector ls = new ListSelector();
        Integer lastIdMapa = -1;
        Integer idMapa;
        int lastIndex;
        for(int i = 0; i < listaReporte.size(); i++) {
            idMapa = listaReporte.get(i).getIdMapaPrueba();
            if(!idMapa.equals(lastIdMapa)) {
                ls.setEnd(i);
                ls = new ListSelector(idMapa,i,null);
                selectors.add(ls);
            }
            /*
             * Es necesaria esta validacion para que el ultimo selector
             * tenga end, ya que el siclo finaliza cuando i < listaReporte.size().
             */
            lastIndex = i + 1;
            if(lastIndex == listaReporte.size())
            	ls.setEnd(lastIndex);
            
            lastIdMapa = idMapa;
        }
        return selectors;
    }
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 2/05/2014
	 * @description 
	 * @param directory
	 * @throws AppBaseException
	 * @throws IOException
	 */
	@Override
	public void deleteZipFolder(String directory) throws AppBaseException, IOException {
		if(directory == null || directory.isEmpty()) {
			throw new AppBaseException("El parametro del directorio de los zip files es nulo","NO_PARAMETRO");
		}
		directory = directory +  File.separator + ZIP_FOLDER;
		File zipFolder = new File(directory);
		if(zipFolder.exists() && zipFolder.isDirectory()) {
			//FileUtils.deleteDirectory(zipFolder);
			FileUtils.deleteQuietly(zipFolder);
			LogUtil.log(MapaPruebaServiceImp.class, Level.INFO,
					"zip folder ["+zipFolder.getAbsolutePath()+"] eliminado correctamente",  null);
		} else {
			LogUtil.log(MapaPruebaServiceImp.class, Level.INFO,
					"zip folder ["+zipFolder.getAbsolutePath()+"] no existe, no se pudo eliminar el directorio", null);
		}
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014
	 * @description 
	 * @param idProyecto
	 * @param fechaFrom
	 * @param fechaTo
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException
	 * @throws NumberFormatException
	 */
	@Override
	public IndReprocesoConstruccionPruebasDTO getIndicadorReprocesoConstruccionPruebas(String idProyecto,
			String fechaFrom, String fechaTo) throws AppBaseException, ParseException, NumberFormatException {
		if(null == idProyecto || idProyecto.isEmpty()) {
			throw new AppBaseException("El id del proyecto es nulo","ID_PROYECTO_NULO");
		}
		Integer idProyectoInt = Integer.parseInt(idProyecto);
		
		Date fecFrom = null;
		Date fecTo = null;
		if(fechaFrom != null && !fechaFrom.isEmpty()) {
			fecFrom = DateUtils.getDateFromString(IndicadoresUtils.FORMATO_FECHA, fechaFrom, true);
		}
		
		if(fechaTo != null && !fechaTo.isEmpty()) {
			fecTo = DateUtils.getDateFromString(IndicadoresUtils.FORMATO_FECHA, fechaTo, false);
		}
		
		IndReprocesoConstruccionPruebasDTO result = mapaPruebasDao
				.consultarIndicadorReprocesoConstruccionPruebas(idProyectoInt, fecFrom, fecTo);
		
		if(result != null) {
			IndicadoresDTO indicadorParametro = new IndicadoresDTO(indicadoresDao
					.get(INDICADOR_REPROCESO_CONSTRUCCION_PRUEBAS_ID));
			Double indicador = result.calculateIndicadorReprocesoConstruccionPruebas();
			result.setIndicador(IndicadoresUtils.getIndicadorDTO(indicadorParametro, indicador));
			result.setParametroIndicador(indicadorParametro);
		}
		
		
		
		return result;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 30/04/2014
	 * @description 
	 * @param libro
	 * @param location
	 * @throws AppBaseException
	 */
	private void saveWorkbook(Workbook libro, String location) throws AppBaseException {
        try {
            FileOutputStream fileOut = new FileOutputStream(location);
            libro.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException ex) {
        	new AppBaseException("Error al ejecutar el metodo saveWorkbook; location = "+location,"FILE_NOT_FOUND");
        } catch (IOException ex) {
        	new AppBaseException("Error al ejcutar el metodo saveWorkbook: "+ex.getMessage(), "IO_EXCEPTION");
        }
		
	}
	
}