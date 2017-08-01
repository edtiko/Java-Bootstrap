/**
 * 
 */
package com.premize.sgp.service.pruebas.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.MailService;
import com.premize.pmz.api.dto.EMailInfo;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.api.exception.MailServiceException;
import com.premize.pmz.service.AbstractEntityService;
import com.premize.sgp.dao.anexo.AnexoHallazgoDao;
import com.premize.sgp.dao.gestion.pruebas.CasoPruebaDao;
import com.premize.sgp.dao.gestion.pruebas.CausaGeneracionDao;
import com.premize.sgp.dao.gestion.pruebas.FlujoHallazgoDao;
import com.premize.sgp.dao.gestion.pruebas.HallazgoDao;
import com.premize.sgp.dao.gestion.pruebas.IndicadoresDao;
import com.premize.sgp.dao.gestion.pruebas.TipoHallazgoDao;
import com.premize.sgp.dao.gestion.pruebas.TipoPrioridadDao;
import com.premize.sgp.dao.gestion.pruebas.TipoSeveridadDao;
import com.premize.sgp.dao.parametros.ArtefactoDao;
import com.premize.sgp.dao.parametros.SgpParametroDao;
import com.premize.sgp.dao.parametros.UsuarioDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.IndicadoresDTO;
import com.premize.sgp.dto.parametros.ParametroDTO;
import com.premize.sgp.dto.pruebas.AnexoHallazgoDTO;
import com.premize.sgp.dto.pruebas.ConsultaHallazgoDTO;
import com.premize.sgp.dto.pruebas.FlujoHallazgoDTO;
import com.premize.sgp.dto.pruebas.HallazgoDTO;
import com.premize.sgp.dto.pruebas.ReporteHallazgoDTO;
import com.premize.sgp.dto.pruebas.TipoPrioridadDTO;
import com.premize.sgp.dto.pruebas.TipoSeveridadDTO;
import com.premize.sgp.dto.pruebas.TotalHallazgoDTO;
import com.premize.sgp.dto.pruebas.TotalesHallazgoDTO;
import com.premize.sgp.dto.pruebas.indicadores.CalidadDocumentacionDTO;
import com.premize.sgp.dto.pruebas.indicadores.HallazgoPuntuacionDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndCalidadDocumentacionDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndCalidadFuncionalidadDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndEfectividadHallazgosReportadosDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndMejorasIdentificadasDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndTiemposRespuestaGarantiaDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndicadorDTO;
import com.premize.sgp.dto.pruebas.indicadores.TiemposRespuestaGarantiaDTO;
import com.premize.sgp.modelo.entities.anexo.SgpAnexoHallazgo;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpCasoPrueba;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpCausaGeneracion;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpFlujoHallazgo;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpHallazgo;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpIndicadores;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoHallazgo;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoPrioridad;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoSeveridad;
import com.premize.sgp.modelo.entities.parametros.SgpArtefacto;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;
import com.premize.sgp.service.ParametroService;
import com.premize.sgp.service.pruebas.HallazgoService;
import com.premize.sgp.utils.DateUtils;
import com.premize.sgp.utils.FilePmz;
import com.premize.sgp.utils.IndicadoresUtils;
import com.premize.sgp.utils.ReporteExcel;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class HallazgoServiceImpl
 * @since 24/02/2014
 */
@Service
public class HallazgoServiceImpl extends AbstractEntityService<SgpHallazgo, Integer> implements HallazgoService {
	
	private static Integer ACTIVO = 1;
	private static final String RUTA_GUARDAR_ARCHIVO_KEY = "RUTA_ANEXOS";
	private static final String PARAMETRO_ENLACE_HALLAZGO = "ENLACE_HALLAZGO";
	private static final String PARAMETRO_PUNTOS_POSIBLES_DOC = "CANTIDAD_PUNTOS_INICIALES_DOC";
	private static final String ANULADO = "Anulado";
	private static final String CERRADO = "Invalidado";
	private static final String INVALIDADO = "Cerrado";
	private static final String PARAMETRO_FIELD_CLAVE="nombre";
	private static final String FORMATO_FECHA = "yyyy/MM/dd";
	private static final Integer NO_CONFORMIDAD_ANALISIS = 1;
	
	private static final int INDICADOR_MEJORAS_IDENTIFICADAS_ID = 8;
	private static final int INDICADOR_CALIDAD_POR_FUNCIONALIDAD_ID = 4;
	private static final int INDICADOR_EFECTIVIDAD_HALLAZGOS_REPORTADOS_ID = 7;
	private static final int INDICADOR_CALIDAD_DOCUMENTACION_ID = 2;
	private static final int INDICADOR_TIEMPOS_RESPUESTA_GARANTIA_ID = 10;
	
	@Autowired
	private HallazgoDao hallazgoDao;
	
	@Autowired
	private IndicadoresDao indicadorDao;
	
	@Autowired
	private FlujoHallazgoDao flujoHallazgoDao;
	
	@Autowired
	private AnexoHallazgoDao anexoHallazgoDao;
	
	@Autowired
	private CasoPruebaDao casoPruebaDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private SgpParametroDao parametroDao;
	
	@Autowired
	private TipoHallazgoDao tipoHallazgoDao;
	
	@Autowired
	private CausaGeneracionDao causaGeneracionDao;
	
	@Autowired
	private TipoSeveridadDao tipoSeveridadDao;
	
	@Autowired
	private TipoPrioridadDao tipoPrioridadDao;
	
	@Autowired
	private ArtefactoDao artefactoDao;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private ReporteExcel reporteExcel;
	
	@Autowired
	private ParametroService parametroService;
	
	@Autowired
	private IndicadoresDao indicadoresDao;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws ParseException 
	 * @since 24/02/2014
	 * @see com.premize.sgp.service.pruebas.HallazgoService#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<ConsultaHallazgoDTO> getRecords(PagingCriteria criteria) throws AppBaseException, ParseException {
		return hallazgoDao.getRecords(criteria);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @see com.premize.pmz.service.AbstractEntityService#getDao()
	 */
	@Override
	public Dao<SgpHallazgo, Integer> getDao() {
		return hallazgoDao;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException 
	 * @throws ParseException 
	 * @since 6/03/2014
	 * @see com.premize.sgp.service.pruebas.HallazgoService#guardarHallazgo(com.premize.sgp.dto.pruebas.FlujoHallazgoDTO,
	 *      com.premize.sgp.modelo.entities.gestion.pruebas.SgpHallazgo)
	 */
	@Override
	public void guardarHallazgo(FlujoHallazgoDTO flujoHallazgo, SgpHallazgo sgpHallazgo) throws AppBaseException, ParseException  
	

		{
			
				
				SgpCasoPrueba sgpCasoPrueba=null;
				if(null !=flujoHallazgo.getHallazgo().getCasoPrueba()){
					sgpCasoPrueba= casoPruebaDao.get(flujoHallazgo.getHallazgo().getCasoPrueba().getId());
					sgpHallazgo.setCasoPrueba(sgpCasoPrueba);
				}
				SgpArtefacto artefacto=null;
				
				if(null != flujoHallazgo.getHallazgo().getArtefacto()){
					artefacto=artefactoDao.get(flujoHallazgo.getHallazgo().getArtefacto().getId());
					sgpHallazgo.setArtefacto(artefacto);
				}else{
					artefacto=artefactoDao.get(sgpCasoPrueba.getSgpArtefacto().getId());
					sgpHallazgo.setArtefacto(artefacto);
				}
				
				
				SgpTipoHallazgo tipoHallazgo = tipoHallazgoDao.get(flujoHallazgo.getHallazgo().getTipoHallazgo().getId());
				SgpCausaGeneracion causaGenera =null;
				if(tipoHallazgo.getTieneCausaGeneracion()==1){
					causaGenera= causaGeneracionDao
							.get(flujoHallazgo.getHallazgo().getCausaGeneracion().getId());
					sgpHallazgo.setCausaGeneracion(causaGenera);
				}
				
				SgpTipoSeveridad severidad = tipoSeveridadDao.get(flujoHallazgo.getHallazgo().getSeveridad().getId());
				SgpTipoPrioridad prioridad = tipoPrioridadDao.get(flujoHallazgo.getHallazgo().getPrioridad().getId());
							
				sgpHallazgo.setTipoHallazgo(tipoHallazgo);
				
				
				
				sgpHallazgo.setDescripcion(flujoHallazgo.getHallazgo().getDescripcion());
				sgpHallazgo.setSeveridad(severidad);
				sgpHallazgo.setPrioridad(prioridad);
				sgpHallazgo.setTitulo(flujoHallazgo.getHallazgo().getTitulo());
				sgpHallazgo.setIndActivo(ACTIVO);
				sgpHallazgo.setFecCreacion(Calendar.getInstance().getTime());
				sgpHallazgo.setUsuarioCrea(flujoHallazgo.getUsuarioCreacion());
				String fechaSolicitud=flujoHallazgo.getHallazgo().getFechaSolicitudString();
				
				if(null != fechaSolicitud  ){
					if(!fechaSolicitud.equals("")){
						sgpHallazgo.setFecSolicitud(DateUtils.getDateFromString("yyyy/MM/dd hh:mm",flujoHallazgo.getHallazgo().getFechaSolicitudString() ));
					}
				}
				
				hallazgoDao.save(sgpHallazgo);
				flujoHallazgo.getHallazgo().setId(sgpHallazgo.getId());
			
		}
	
	@Override
	public void guardarAnexoHallazgo(SgpHallazgo Hallazgo, FilePmz anexo) throws AppBaseException, IOException {
		
		String ruta = null;
		SgpAnexoHallazgo sgpAnexoHallazgo = new SgpAnexoHallazgo();
		
		if (anexo != null) {
			Map<String, Object> restriccionesAnexo = parametroService.getMapRestriccionesAnexo();
			
			//Valida la extension del anexo.
			String anexoExtension = FilenameUtils.getExtension(anexo.getName());
			String allowedFiles = restriccionesAnexo.get(ParametroService.ANEXO_REST_MAP_KEY_ALLOWED_FILES).toString();
			if(anexoExtension.isEmpty() || !allowedFiles.contains(anexoExtension.toLowerCase())) {
				throw new AppBaseException("El tipo de archivo seleccionado no es permitido","ANEXO_EXTENSION");
			}
			
			//Valida el peso maximo permitido para un anexo
			Integer maxFileSize = (Integer)restriccionesAnexo.get(ParametroService.ANEXO_REST_MAP_KEY_FILE_SIZE);
			Integer anexoSize = anexo.getLength() / 1024; //KB
			if(anexoSize > maxFileSize) {
				throw new AppBaseException("El peso del archivo seleccionado no es permitido","ANEXO_MAX_SIZE");
			}
			
			//Valida si ya existe un anexo con el mismo nombre y extension
			List<AnexoHallazgoDTO> anexos = anexoHallazgoDao.getAnexosContainsName(anexo.getName().toLowerCase());
			if(anexos != null) {
				if(anexos.size() > 0) {
					StringBuffer str = new StringBuffer();
					str.append("El hallazgo ");
					str.append(anexos.get(0).getIdHallazgo());
					str.append(" ya cuenta con un Anexo \"");
					str.append(anexo.getName());
					str.append("\", por favor cambie el nombre del archivo e intente adjuntar nuevamente");
					throw new AppBaseException(str.toString(),"ANEXO_DUPLICADO");
				}
			}
			
			// Obtiene la ruta del servidor local
			SgpParametro paramRuta = parametroDao.findByProperty(ParametroService.PARAMETRO_FIELD_CLAVE,
					ParametroService.RUTA_ANEXOS);
			
			if (paramRuta == null || StringUtils.isEmpty(paramRuta.getValor())
					|| StringUtils.isBlank(paramRuta.getValor())) {
				
				throw new AppBaseException("No fue posible obtener la ruta del servidor para guardar el anexo.","RUTA_RECURSO_INVALIDA");
			}
			
			// Copia el archivo en el servidor local
			ruta = paramRuta.getValor()+File.separator+ anexo.getName();
			FileCopyUtils.copy(anexo.getBytes(), new FileOutputStream(ruta));
			
			sgpAnexoHallazgo.setSgpHallazgo(Hallazgo);
			sgpAnexoHallazgo.setRuta(anexo.getName());
			sgpAnexoHallazgo.setIndActivo(ACTIVO);
			sgpAnexoHallazgo.setUsuarioCrea(Hallazgo.getUsuarioCrea());
			sgpAnexoHallazgo.setFecCreacion(Calendar.getInstance().getTime());
			anexoHallazgoDao.save(sgpAnexoHallazgo);
		}
		
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 3/03/2014
	 * @see com.premize.sgp.service.pruebas.HallazgoService#getHallazgo(java.lang.Integer)
	 */
	@Override
	public HallazgoDTO getHallazgo(Integer idHallazgo,String login) throws AppBaseException {
		
		HallazgoDTO hallazgodto = null;
		if(idHallazgo != null ){
		SgpHallazgo sgpHallazgo = hallazgoDao.get(idHallazgo);
		SgpFlujoHallazgo flujoActual = flujoHallazgoDao.obtenerFlujoPorHallazgo(idHallazgo);
		List<SgpFlujoHallazgo> listFlujos = flujoHallazgoDao.getFlujosByHallazgo(idHallazgo);
		List<SgpAnexoHallazgo> listAnexos = anexoHallazgoDao.getAnexosByHallazgo(idHallazgo);
		List<AnexoHallazgoDTO> anexos = new ArrayList<AnexoHallazgoDTO>();
		List<FlujoHallazgoDTO> flujos = new ArrayList<FlujoHallazgoDTO>();
		for (SgpFlujoHallazgo sgpFlujoHallazgo : listFlujos) {
			
			FlujoHallazgoDTO flujoHallazgoDto = new FlujoHallazgoDTO(sgpFlujoHallazgo);
			flujos.add(flujoHallazgoDto);
		}
		for (SgpAnexoHallazgo sgpAnexoHallazgo : listAnexos) {
			
			AnexoHallazgoDTO anexoHallazgoDto = new AnexoHallazgoDTO(sgpAnexoHallazgo);
			anexoHallazgoDto.setUsuarioActual(login);
			anexos.add(anexoHallazgoDto);
		}
		hallazgodto = new HallazgoDTO(sgpHallazgo, flujos, anexos, flujoActual);
		}
		return hallazgodto;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 28/02/2014
	 * @see com.premize.sgp.service.pruebas.HallazgoService#getHallazgosPorCaso(java.lang.Integer)
	 */
	@Override
	public List<HallazgoDTO> getHallazgosPorCaso(Integer idCasoPrueba) throws AppBaseException {
		
		List<HallazgoDTO> listaHallazgosDto = new ArrayList<HallazgoDTO>();
		List<SgpHallazgo> listaHallazgos = hallazgoDao.getHallazgosPorCasoPrueba(idCasoPrueba);
		
		for (SgpHallazgo sgpHallazgo : listaHallazgos) {
			
			HallazgoDTO hallazgoDto = new HallazgoDTO(sgpHallazgo);
			listaHallazgosDto.add(hallazgoDto);
		}
		
		return listaHallazgosDto;
	}
	
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws MailServiceException
	 * @throws AppBaseException
	 * @since 7/03/2014
	 * @see com.premize.sgp.service.pruebas.HallazgoService#enviarEmailHallazgo(com.premize.sgp.modelo.entities.gestion.pruebas.SgpFlujoHallazgo)
	 */
	@Override
	@Async
	public void enviarEmailHallazgo(SgpFlujoHallazgo sgpFlujoHallazgo) throws MailServiceException, AppBaseException {
	
		String estado = sgpFlujoHallazgo.getAccion().getNombre();
		
		if( !estado.equals(ANULADO)  && !estado.equals(INVALIDADO) && !estado.equals(CERRADO)){
			
			EMailInfo eMailInfo = new EMailInfo();
			CommandMap.setDefaultCommandMap(new MailcapCommandMap());
			List<SgpFlujoHallazgo> listflujos = flujoHallazgoDao.getFlujosByHallazgo(sgpFlujoHallazgo.getSgpHallazgo()
					.getId());
			List<SgpAnexoHallazgo> listAnexos = anexoHallazgoDao.getAnexosByHallazgo(sgpFlujoHallazgo.getSgpHallazgo().getId());
			SgpParametro enlaceHallazgo = parametroDao.findByProperty(PARAMETRO_FIELD_CLAVE, PARAMETRO_ENLACE_HALLAZGO);
			SgpParametro paramRuta = parametroDao.findByProperty(ParametroService.PARAMETRO_FIELD_CLAVE,
					RUTA_GUARDAR_ARCHIVO_KEY);
			List<FlujoHallazgoDTO> flujos = new ArrayList<FlujoHallazgoDTO>();
			for (SgpFlujoHallazgo sgpFlujo : listflujos) {
				FlujoHallazgoDTO flujohallazgoDto = new FlujoHallazgoDTO(sgpFlujo);
				flujos.add(flujohallazgoDto);
			}
			eMailInfo.setEmails(new String[] { sgpFlujoHallazgo.getUsuarioAsignado().getCorreo() });
			for(SgpAnexoHallazgo anexo : listAnexos){
				File file = new File(paramRuta.getValor()+anexo.getRuta());
				eMailInfo.addAttachment(anexo.getRuta(), file);
			}
			// eMailInfo.setEmails(new String[]{"wisdom.dev@gmail.com"});
			eMailInfo.setFormato(EMailInfo.FORMATO_HTML);
			eMailInfo.setTemplateName("reportarHallazgo.vm");
			Map<String, Object> templateVariables = new HashMap<String, Object>();
			templateVariables.put("hallazgo", sgpFlujoHallazgo.getSgpHallazgo());
		if(null != sgpFlujoHallazgo.getSgpHallazgo().getCasoPrueba()){
			templateVariables.put("proyecto", sgpFlujoHallazgo.getSgpHallazgo().getCasoPrueba().getSgpMapaPrueba()
					.getSgpProyecto().getNombre());
			templateVariables.put("mapaPrueba", sgpFlujoHallazgo.getSgpHallazgo().getCasoPrueba().getSgpMapaPrueba()
					.getNombre());
			templateVariables.put("artefacto", sgpFlujoHallazgo.getSgpHallazgo().getCasoPrueba().getSgpArtefacto()
					.getNombre());
			templateVariables.put("empresa", sgpFlujoHallazgo.getSgpHallazgo().getCasoPrueba().getSgpMapaPrueba()
					.getSgpProyecto().getSgpEmpresa().getNombre());
		}else{
			templateVariables.put("proyecto", sgpFlujoHallazgo.getSgpHallazgo().getArtefacto().getProyecto().getNombre());
			templateVariables.put("artefacto", sgpFlujoHallazgo.getSgpHallazgo().getArtefacto().getNombre());
			templateVariables.put("empresa", sgpFlujoHallazgo.getSgpHallazgo().getArtefacto().getProyecto().getSgpEmpresa().getNombre());
			templateVariables.put("mapaPrueba","");
		}
					
			templateVariables.put("tipoHallazgo", sgpFlujoHallazgo.getSgpHallazgo().getTipoHallazgo().getNombre());
			templateVariables.put("estadoActual", sgpFlujoHallazgo.getAccion().getNombre());
			templateVariables.put("usuarioAsignado", sgpFlujoHallazgo.getUsuarioAsignado().getNombre());
			templateVariables.put("severidad", sgpFlujoHallazgo.getSgpHallazgo().getSeveridad().getNombre());
			templateVariables.put("prioridad", sgpFlujoHallazgo.getSgpHallazgo().getPrioridad().getNombre());
			if(sgpFlujoHallazgo.getSgpHallazgo().getTipoHallazgo().getTieneCausaGeneracion()==1){
				if(null!=sgpFlujoHallazgo.getSgpHallazgo().getCausaGeneracion()){
					templateVariables.put("causaGeneracion", sgpFlujoHallazgo.getSgpHallazgo().getCausaGeneracion().getNombre());
				}else{
					templateVariables.put("causaGeneracion","");
				}
			}else{
				templateVariables.put("causaGeneracion","");
			}
			
			
			templateVariables.put("date", new Date());

			if (sgpFlujoHallazgo.getSgpHallazgo().getCausaAnulacion() != null) {
				templateVariables.put("causaAnulacion", sgpFlujoHallazgo.getSgpHallazgo().getCausaAnulacion().getNombre());
			} else {
				templateVariables.put("causaAnulacion", "");
			}
			templateVariables.put("flujos", flujos);

			templateVariables.put("enlaceHallazgo", enlaceHallazgo.getValor()+sgpFlujoHallazgo.getSgpHallazgo().getId());
			eMailInfo.setTemplateVariables(templateVariables);
			eMailInfo.setSubject("Notificación del Hallazgo " + sgpFlujoHallazgo.getSgpHallazgo().getId() + " Asignado");
			mailService.enviarEMailTemplate(eMailInfo);
		}
		
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException 
	 * @since 11/03/2014
	 * @see com.premize.sgp.service.pruebas.HallazgoService#getEstadosHallazgo(java.lang.Integer)
	 */
	@Override
	public List<ParametroDTO> getEstadosHallazgo(Integer idHallazgo) throws AppBaseException {
		
		return hallazgoDao.getEstadosHallazgo(idHallazgo);
	}
/**
 * 
 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache
 </a>
 * @throws ParseException 
 * @since 14/04/2014
 * @see com.premize.sgp.service.pruebas.HallazgoService#getHallazgosPorSeveridad(java.lang.Integer)
 */
	@Override
	public List<ReporteHallazgoDTO> getHallazgosPorSeveridad(Integer idProyecto, String fechaFrom, String fechaTo, String idTipoHallazgo) throws AppBaseException, ParseException {
		
		return hallazgoDao.getHallazgosPorSeveridad(idProyecto, fechaFrom, fechaTo, idTipoHallazgo);
	}

	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache
	 </a>
	 * @throws ParseException 
	 * @since 14/04/2014
	 * @see com.premize.sgp.service.pruebas.HallazgoService#getHallazgosPorMapaPruebas(java.lang.Integer)
	 */
	@Override
	public List<ReporteHallazgoDTO> getHallazgosPorMapaPruebas(Integer idProyecto, String fechaFrom, String fechaTo, String idTipoHallazgo) throws AppBaseException, ParseException {
		
		return hallazgoDao.getHallazgosPorMapaPruebas(idProyecto, fechaFrom, fechaTo, idTipoHallazgo);
	}
	
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 11/03/2014
	 * @see com.premize.sgp.service.pruebas.HallazgoService#guardarAnexo(com.premize.sgp.dto.pruebas.HallazgoDTO,
	 *      com.premize.sgp.utils.File)
	 */
	@Override
	public void guardarAnexo(HallazgoDTO hallazgodto, FilePmz file) throws AppBaseException, IOException {
		SgpHallazgo sgpHallazgo = hallazgoDao.get(hallazgodto.getId());
		sgpHallazgo.setUsuarioCrea(hallazgodto.getUsuarioCreacion());
		guardarAnexoHallazgo(sgpHallazgo, file);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 11/03/2014
	 * @see com.premize.sgp.service.pruebas.HallazgoService#getAnexo(java.lang.Integer)
	 */
	@Override
	public AnexoHallazgoDTO getAnexo(Integer idAnexo) throws AppBaseException {
		SgpAnexoHallazgo sgpAnexo = anexoHallazgoDao.get(idAnexo);
		return new AnexoHallazgoDTO(sgpAnexo);
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/06/2014
	 * @description 
	 * @param idAnexo
	 * @return
	 * @throws AppBaseException
	 */
	@Override
	public File getArchivoAnexo(Integer idAnexo) throws AppBaseException {
		if(null == idAnexo || idAnexo < 0) {
			throw new AppBaseException("El id del anexo no es valido","ID_ANEXO_NULO");
		}
		
		SgpAnexoHallazgo anexo = anexoHallazgoDao.get(idAnexo);
		if(null == anexo) {
			throw new AppBaseException("No se encuentra un anexo en la base de datos con id = "+idAnexo,"SGP_ANEXO_NULO");
		}
		
		SgpParametro paramRuta = parametroDao.findByProperty(PARAMETRO_FIELD_CLAVE, RUTA_GUARDAR_ARCHIVO_KEY);
		String ruta = paramRuta.getValor() + anexo.getRuta();
		File fileAnexo = new File(ruta);
		
		if(!fileAnexo.exists()) {
			throw new AppBaseException("Lamentamos informarle que el archivo \"" +anexo.getRuta()+"\" no se encontró.",
					"ARCHIVO_ANEXO_NO_EXISTE",anexo.getRuta());
		}
		
		return fileAnexo;
	}

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 21/03/2014
	 * @see com.premize.sgp.service.pruebas.HallazgoService#modificarDatosHallazgo(com.premize.sgp.dto.pruebas.HallazgoDTO)
	 */
	@Override
	public void modificarDatosHallazgo(HallazgoDTO hallazgoDTO)
			throws AppBaseException {
       
		SgpHallazgo sgpHallazgo = hallazgoDao.get(hallazgoDTO.getId());
		SgpTipoHallazgo tipoHallazgo = tipoHallazgoDao.get(hallazgoDTO.getTipoHallazgo().getId());
		SgpCausaGeneracion causaGeneracion=null;
		if(tipoHallazgo.getTieneCausaGeneracion()==1){
			causaGeneracion = causaGeneracionDao.get(hallazgoDTO.getCausaGeneracion().getId());
		}
		
		
		SgpTipoPrioridad prioridad = tipoPrioridadDao.get(hallazgoDTO.getPrioridad().getId());
		SgpTipoSeveridad severidad = tipoSeveridadDao.get(hallazgoDTO.getSeveridad().getId());
	
		if(hallazgoDTO.getCausaAnulacion() != null){
		SgpParametro causaAnulacion = parametroDao.get(hallazgoDTO.getCausaAnulacion().getId());
		sgpHallazgo.setCausaAnulacion(causaAnulacion);
		}
		sgpHallazgo.setCausaGeneracion(causaGeneracion);
		sgpHallazgo.setPrioridad(prioridad);
		sgpHallazgo.setSeveridad(severidad);
		sgpHallazgo.setTipoHallazgo(tipoHallazgo);
		sgpHallazgo.setTitulo(hallazgoDTO.getTitulo());
		sgpHallazgo.setUsuarioEdita(hallazgoDTO.getUsuarioEdita());
		sgpHallazgo.setFecEdita(Calendar.getInstance().getTime());
		hallazgoDao.update(sgpHallazgo);
	}

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException 
	 * @throws ParseException 
	 * @since 14/04/2014
	 * @see com.premize.sgp.service.pruebas.HallazgoService#getTotalesHallazgoProyecto(java.lang.Integer)
	 */
	@Override
	public List<TotalesHallazgoDTO> getTotalesHallazgoProyecto(
			Integer idProyecto, String fechaFrom, String fechaTo, String idTipoHallazgo) throws AppBaseException, ParseException {
		return hallazgoDao.getTotalesHallazgoProyecto(idProyecto, fechaFrom, fechaTo, idTipoHallazgo);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.service.pruebas.HallazgoService#getUsuarios_Hallazgos(java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public List<TotalHallazgoDTO> getUsuarios_Hallazgos(Integer idProyecto,	String fechaFrom, String fechaTo, String tipoHallazgo) 
			throws AppBaseException,	ParseException {
		return hallazgoDao.getUsuarios_Hallazgos(idProyecto, fechaFrom, fechaTo, tipoHallazgo);
	}
	
    /**
     * 
     * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
     * @since 21/04/2014
     * @see com.premize.sgp.service.pruebas.HallazgoService#consultaHallazgosExcel(com.premize.sgp.dto.pruebas.FlujoHallazgoDTO)
     */
	@Override
	public Workbook consultaHallazgosExcel(Map<String, Object> filtros)
			throws AppBaseException, NumberFormatException, IOException {
		List<ConsultaHallazgoDTO> listaHallazgos = hallazgoDao.consultarHallazgos(filtros);
		Workbook libro = reporteExcel.construirReporteHallazgos(listaHallazgos);
		
		return libro;
	}

	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 3/07/2014
	 * @description 
	 * @param idProyecto
	 * @param fechaFrom
	 * @param fechaTo
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException
	 */
	@Override
	public Map<Integer, IndCalidadDocumentacionDTO> consultarIndicadorCalidadDocumentacion(String idProyecto,
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
		
		List<CalidadDocumentacionDTO> data = hallazgoDao
				.consultarIndicadorCalidadDocumentacion(idProyectoInt, fecFrom, fecTo);
		
		IndicadoresDTO parametroIndicadores = new IndicadoresDTO(indicadoresDao.get(INDICADOR_CALIDAD_DOCUMENTACION_ID));
		
		SgpParametro parametro = parametroDao.findByProperty(PARAMETRO_FIELD_CLAVE, PARAMETRO_PUNTOS_POSIBLES_DOC);
		if(parametro == null) {
			throw new AppBaseException("La consulta del parametro: puntos posibles por documento retorno null",
					"PARAMETRO_NULO",PARAMETRO_PUNTOS_POSIBLES_DOC);
		}
		Double puntosPosibles = Double.parseDouble(parametro.getValor());
		
		Map<Integer, IndCalidadDocumentacionDTO> result = transformQueryCalidadDocumentacion(data,parametroIndicadores,puntosPosibles);
		
		if(!result.isEmpty()) {
			result.entrySet().iterator().next().getValue().setParametroIndicador(parametroIndicadores);
		} else {
			result.put(0, new IndCalidadDocumentacionDTO(parametroIndicadores));
		}
		
		
		return result;
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014
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
	public List<IndCalidadFuncionalidadDTO> getIndicadorCalidadFuncionalidad(String idProyecto,
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
		
		List<IndCalidadFuncionalidadDTO> data = hallazgoDao
				.consultarIndicadorFuncionalidad(idProyectoInt, fecFrom, fecTo);
		
		IndicadoresDTO indicadorParametro = new IndicadoresDTO(indicadoresDao
				.get(INDICADOR_CALIDAD_POR_FUNCIONALIDAD_ID));
		
		if (!data.isEmpty()) {
			
			Integer totalPruebasEjecutadas = 0,
					totalHallazgosReportados = 0,
					totalHallazgosInvalidados = 0,
					totalHallazgosAnulados = 0;
			
			Double totalIndicador = 0d;
			Double indicador = 0d;
			
			data.get(0).setParametroIndicador(indicadorParametro);
			
			for (IndCalidadFuncionalidadDTO dto : data) {
				indicador = dto.calculateIndicadorCalidadFuncionalidad();
				dto.setIndicador(IndicadoresUtils.getIndicadorDTO(indicadorParametro, indicador));
				
				totalPruebasEjecutadas += dto.getPruebasEjecutadas();
				totalHallazgosReportados += dto.getHallazgosReportados();
				totalHallazgosInvalidados += dto.getHallazgosInvalidados();
				totalHallazgosAnulados += dto.getHallazgosAnulados();
				totalIndicador += indicador;
			}
			
			IndCalidadFuncionalidadDTO totales = new IndCalidadFuncionalidadDTO();
			totales.setPruebasEjecutadas(totalPruebasEjecutadas);
			totales.setHallazgosReportados(totalHallazgosReportados);
			totales.setHallazgosInvalidados(totalHallazgosInvalidados);
			totales.setHallazgosAnulados(totalHallazgosAnulados);
			
			indicador = totales.calculateIndicadorCalidadFuncionalidad();
			totales.setIndicador(IndicadoresUtils.getIndicadorDTO(indicadorParametro, indicador));
			data.add(totales);
		} else {
			data.add(new IndCalidadFuncionalidadDTO(indicadorParametro));
		}
		
		return data;
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
	public IndEfectividadHallazgosReportadosDTO getIndicadorEfectividadHallazgosReportados(String idProyecto,
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
		
		IndEfectividadHallazgosReportadosDTO result = hallazgoDao
				.consultarIndicadorEfectividadHallazgosReportados(idProyectoInt, fecFrom, fecTo);
		
		if(result != null) {
			IndicadoresDTO indicadorParametro = new IndicadoresDTO(indicadoresDao
					.get(INDICADOR_EFECTIVIDAD_HALLAZGOS_REPORTADOS_ID));
			Double indicador = result.calculateIndicadorEfectividadHallazgosReportados();
			result.setIndicador(IndicadoresUtils.getIndicadorDTO(indicadorParametro, indicador));
			result.setParametroIndicador(indicadorParametro);
		}
		
		return result;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/07/2014
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
	public List<IndMejorasIdentificadasDTO> getMejorasIndentifcadas(String idProyecto,
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
		
		List<IndMejorasIdentificadasDTO> data = hallazgoDao
				.consultarMejorasIndentificadas(idProyectoInt, fecFrom, fecTo);
		
		IndicadoresDTO indicadorParametro = new IndicadoresDTO(indicadoresDao.get(INDICADOR_MEJORAS_IDENTIFICADAS_ID));
		
		if(!data.isEmpty()) {
			Integer totalMejoras = 0,
					totalMejorasAnuladas = 0,
					totalMejorasInvalidadas = 0,
					totalMejorasAceptadas = 0;
			
			Double totalIndicador = 0d;
			Double indicador;
			
			data.get(0).setParametroIndicador(indicadorParametro);
			
			for(IndMejorasIdentificadasDTO dto : data) {
				indicador = dto.calculateIndicadorMejorasIndentificadas();
				dto.setIndicador(IndicadoresUtils.getIndicadorMejorasIdentificadas(indicadorParametro, indicador));
				
				totalMejoras += dto.getMejoras();
				totalMejorasAnuladas += dto.getMejorasAnuladas();
				totalMejorasInvalidadas += dto.getMejorasInvalidadas();
				totalMejorasAceptadas += dto.getMejorasAceptadas();
				totalIndicador += indicador;
			}
			
			IndMejorasIdentificadasDTO totales = new IndMejorasIdentificadasDTO();
			totales.setMejoras(totalMejoras);
			totales.setMejorasAnuladas(totalMejorasAnuladas);
			totales.setMejorasInvalidadas(totalMejorasInvalidadas);
			totales.setMejorasAceptadas(totalMejorasAceptadas);

			indicador = totales.calculateIndicadorMejorasIndentificadas();
			totales.setIndicador(IndicadoresUtils.getIndicadorMejorasIdentificadas(indicadorParametro, indicador));
			data.add(totales);
		} else {
			data.add(new IndMejorasIdentificadasDTO(indicadorParametro));
		}
		
		return data;
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014
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
	public IndTiemposRespuestaGarantiaDTO getIndicadorTiemposRespuestaGarantia(String idProyecto,
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
		
		List<TiemposRespuestaGarantiaDTO> data = hallazgoDao
				.consultarTiemposRespuestaGarantia(idProyectoInt, fecFrom, fecTo);
		
		IndTiemposRespuestaGarantiaDTO indicadorResult = new IndTiemposRespuestaGarantiaDTO();
		indicadorResult.setParametroIndicador(new IndicadoresDTO(indicadoresDao
				.get(INDICADOR_TIEMPOS_RESPUESTA_GARANTIA_ID)));
		
		Double indicador;
		for(TiemposRespuestaGarantiaDTO dto : data) {
			indicador = null;
			if(dto.getFechaRespuesta() != null) {
				indicador = IndicadoresUtils.calculateIndicadorTiemposRespuestaGarantia(dto.getTiempoPrioridad(),
						dto.getFechaSolicitud(), dto.getFechaRespuesta());
			}
			dto.setIndicador(IndicadoresUtils
					.getIndicadorTiempoRespuestaGarantia(dto.getTiempoPrioridad(), indicador));
		}
		indicadorResult.setListaIndicador(data);
		
		return indicadorResult;
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/07/2014
	 * @description 
	 * @param data
	 * @return
	 * @throws AppBaseException
	 */
	private Map<Integer, IndCalidadDocumentacionDTO> transformQueryCalidadDocumentacion(List<CalidadDocumentacionDTO> data,
			IndicadoresDTO parametroIndicadores, Double puntosPosibles) throws AppBaseException {
		DecimalFormat f=new DecimalFormat("#0.00");
		LinkedHashMap<Integer,IndCalidadDocumentacionDTO> consulta = new LinkedHashMap<Integer,IndCalidadDocumentacionDTO>();
		if (!data.isEmpty()) {
			int idRegAnterior = data.get(0).getArtefactoId();
			IndCalidadDocumentacionDTO indicador = consulta.get(idRegAnterior);
			IndCalidadDocumentacionDTO totalConsulta = new IndCalidadDocumentacionDTO("Totales", buildMapSeveridad());
			Double sumaIndicador = 0d;
			Double indicadorValue;
			
			for (int i = 0; i < data.size(); i++) {
				CalidadDocumentacionDTO dto = data.get(i);
				Map<String, HallazgoPuntuacionDTO> map;
				if (null == indicador) {
					map = buildMapSeveridad();
					indicador = new IndCalidadDocumentacionDTO(dto.getArtefactoNombre(), map);
					consulta.put(dto.getArtefactoId(), indicador);
				} else {
					map = indicador.getMapSeveridad();
				}
				if (dto.getArtefactoId() == idRegAnterior) {

					HallazgoPuntuacionDTO hp = map.get(dto.getSeveridadNombre());
					HallazgoPuntuacionDTO hpTotales = map.get("Totales");
					if(hp!=null){
						hp.setHallazgos(dto.getHallazgos());
						hp.setPuntuacion(dto.getPuntuacion());
						hp.setPuntuacionDouble(f.format(dto.getPuntuacion()));
						
						
						hpTotales.sumHallazgos(hp.getHallazgos());
						hpTotales.sumPuntuacion(hp.getPuntuacion());
				
					}
					//Totales por filas
					
					
					

					//Sumatoria(totales) por columnas
					HallazgoPuntuacionDTO hpSumatoria = totalConsulta.getMapSeveridad().get(dto.getSeveridadNombre());
					if(hpSumatoria!=null){
					
					hpSumatoria.sumHallazgos(hp.getHallazgos());
					hpSumatoria.sumPuntuacion(hp.getPuntuacion());
					}
				

					//Suma columna totales
					HallazgoPuntuacionDTO hpSumatoriaTotales = totalConsulta.getMapSeveridad().get("Totales");
					if(hp!=null){
						hpSumatoriaTotales.sumHallazgos(hp.getHallazgos());
						hpSumatoriaTotales.sumPuntuacion(hp.getPuntuacion());            
					}else{
						hpSumatoriaTotales.sumHallazgos(0);
						hpSumatoriaTotales.sumPuntuacion(0D);        
					}
					        

				} else {
					HallazgoPuntuacionDTO hpIndicador = indicador.getMapSeveridad().get("Totales");
					indicadorValue = IndicadoresUtils
							.calculateIndicadorCalidadDocumentacion(hpIndicador.getPuntuacion(), puntosPosibles);
					indicador.setIndicador(indicadorValue);
					indicador.setIndicadorString(IndicadoresUtils.decimalFormat().format(indicadorValue * 100) + "%");
					indicador.setSemaforoImage(IndicadoresUtils
							.getSemaforoImageComparisonBetween(parametroIndicadores, indicadorValue));
					
					sumaIndicador += indicadorValue; //Sumatoria indicadores
					idRegAnterior = dto.getArtefactoId();
					indicador = consulta.get(idRegAnterior);
					i--;
				}
				if (data.size() == (i + 1)) {
					HallazgoPuntuacionDTO hpIndicador = indicador.getMapSeveridad().get("Totales");
					indicadorValue = IndicadoresUtils
							.calculateIndicadorCalidadDocumentacion(hpIndicador.getPuntuacion(), puntosPosibles);
					indicador.setIndicador(indicadorValue);
					indicador.setIndicadorString(IndicadoresUtils.decimalFormat().format(indicadorValue * 100) + "%");
					indicador.setSemaforoImage(IndicadoresUtils
							.getSemaforoImageComparisonBetween(parametroIndicadores, indicadorValue));
					sumaIndicador += indicadorValue; //Sumatoria indicadores
				}
			}
//			HallazgoPuntuacionDTO hpSumatoriaTotales = totalConsulta.getMapSeveridad().get("Totales");
			Double promedio = sumaIndicador / consulta.size();
			totalConsulta.setIndicador(promedio);
			totalConsulta.setIndicadorString(IndicadoresUtils.decimalFormat().format(promedio * 100) + "%");
			totalConsulta.setSemaforoImage(IndicadoresUtils
					.getSemaforoImageComparisonBetween(parametroIndicadores, promedio));
			consulta.put(-1, totalConsulta); //Sumatoria columnas
		}
		
		return consulta;
	}
	
	/**
	 *   
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/07/2014
	 * @description 
	 * @return
	 * @throws AppBaseException
	 */
    private Map<String, HallazgoPuntuacionDTO> buildMapSeveridad() throws AppBaseException {
    	List<TipoSeveridadDTO> listSeveridad = tipoSeveridadDao
    			.consultarSeveridadPorTipoHallazgo(NO_CONFORMIDAD_ANALISIS);
    	Map<String,HallazgoPuntuacionDTO> map = new LinkedHashMap<String,HallazgoPuntuacionDTO>();
    	if(!listSeveridad.isEmpty()) {
    		for(TipoSeveridadDTO tipoSeveridad : listSeveridad) {
    			map.put(tipoSeveridad.getNombre(), new HallazgoPuntuacionDTO());
    		}
    		map.put("Totales", new HallazgoPuntuacionDTO(0,0d));
    	}
        return map;
    }
    
    
    /**
     * 
     * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
     * @throws ParseException 
     * @throws AppBaseException 
     * @since 8/07/2014
     * @see com.premize.sgp.service.pruebas.HallazgoService#getHallazgosPorArtefacto(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)
     */
	@Override
	public List<ReporteHallazgoDTO> getHallazgosPorArtefacto(
			Integer idProyecto, String fechaFrom, String fechaTo,
			String tipoHallazgo) throws AppBaseException, ParseException {
		return hallazgoDao.getHallazgosPorArtefacto(idProyecto, fechaFrom, fechaTo, tipoHallazgo);
	}

	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.service.pruebas.HallazgoService#getSeveridadColorHallazgo(java.lang.String)
	 */
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws ParseException 
	 * @throws AppBaseException 
	 * @since 11/07/2014
	 * @see com.premize.sgp.service.pruebas.HallazgoService#getHallazgosPorRecurso(java.lang.String, java.lang.String, java.lang.String, java.util.List, java.util.List)
	 */
	@Override
	public List<ReporteHallazgoDTO> getHallazgosPorRecurso(String proyecto,
			String fechaFrom, String fechaTo, List<Integer> listEstados,
			List<Integer> listCausas) throws AppBaseException, ParseException {
		
		SgpIndicadores indicador = indicadorDao.get(5);
		Integer total = 0;
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		List<ReporteHallazgoDTO> list = hallazgoDao.getHallazgosPorRecurso(proyecto, fechaFrom, fechaTo, listEstados, listCausas);
		List<ReporteHallazgoDTO> result  = new ArrayList<ReporteHallazgoDTO>();
		for (ReporteHallazgoDTO reporteHallazgoDTO : list) {
		    total += reporteHallazgoDTO.getTotalHallazgos(); 
		}
		
		for (ReporteHallazgoDTO reporteHallazgoDTO : list) {
			IndicadorDTO indicadordto = new IndicadorDTO();
			Double calculoIndicador = 0.0;
			if(total > 0){
				if(reporteHallazgoDTO.getPruebaEjecutada()==null || reporteHallazgoDTO.getPruebaEjecutada()==0 ){
					reporteHallazgoDTO.setPruebaEjecutada(0);
					calculoIndicador=0D;
				}else{
					calculoIndicador = 1-(reporteHallazgoDTO.getTotalHallazgos().doubleValue()/reporteHallazgoDTO.getPruebaEjecutada().doubleValue());
				}
			}
			reporteHallazgoDTO.setTotalIndicador(calculoIndicador);
			String semaforoImage = null;
 			if(calculoIndicador >= indicador.getValorMinimo() && calculoIndicador <= indicador.getValorMaximo()){
 				semaforoImage = "semaforo3.png";
 			}
 			else{
 				semaforoImage = "semaforo1.png";
 			}
			indicadordto.setIndicador(calculoIndicador);
			indicadordto.setIndicadorString(decimalFormat.format(calculoIndicador * 100) + "%");
	        indicadordto.setSemaforoImage(semaforoImage);
			reporteHallazgoDTO.setIndicador(indicadordto); 
			result.add(reporteHallazgoDTO);
		}
		
		
		
		return result;
	}


	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.service.pruebas.HallazgoService#getSeveridadColorHallazgo(java.lang.String)
	 */
	@Override
	public List<ConsultaHallazgoDTO> getSeveridadColorHallazgo(String usuario) throws AppBaseException{
		
		return hallazgoDao.getSeveridadColorHallazgo(usuario);
	}


}
