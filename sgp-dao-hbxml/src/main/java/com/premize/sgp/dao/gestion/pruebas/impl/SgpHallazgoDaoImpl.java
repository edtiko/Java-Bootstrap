package com.premize.sgp.dao.gestion.pruebas.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.dao.impl.HibernateDaoImpl;
import com.premize.sgp.dao.gestion.pruebas.FlujoHallazgoDao;
import com.premize.sgp.dao.gestion.pruebas.HallazgoDao;
import com.premize.sgp.dao.parametros.ProyectoDao;
import com.premize.sgp.dao.parametros.SgpParametroDao;
import com.premize.sgp.dao.parametros.UsuarioDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.Search;
import com.premize.sgp.dto.SortField;
import com.premize.sgp.dto.parametros.ParametroDTO;
import com.premize.sgp.dto.pruebas.ConsultaHallazgoDTO;
import com.premize.sgp.dto.pruebas.ReporteHallazgoDTO;
import com.premize.sgp.dto.pruebas.TotalHallazgoDTO;
import com.premize.sgp.dto.pruebas.TotalesHallazgoDTO;
import com.premize.sgp.dto.pruebas.indicadores.CalidadDocumentacionDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndCalidadFuncionalidadDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndEfectividadHallazgosReportadosDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndMejorasIdentificadasDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndTiemposRespuestaGarantiaDTO;
import com.premize.sgp.dto.pruebas.indicadores.TiemposRespuestaGarantiaDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpCasoPrueba;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpEstadoFlujo;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpFlujoHallazgo;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpHallazgo;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpMapaPrueba;
import com.premize.sgp.modelo.entities.parametros.SgpArtefacto;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;
import com.premize.sgp.utils.NumericUtils;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class SgpHallazgoDaoImpl
 * @since 27/03/2014
 *
 */
@Repository
public class SgpHallazgoDaoImpl extends HibernateDaoImpl<SgpHallazgo, Integer>
		implements HallazgoDao {

	private static final String formatoFecha = "yyyy/MM/dd";
	private static final String CODIGO_ERROR = "7";
	private static final String IND_BANDEJA = "1";
	private static final String PARAMETRO_FIELD_CLAVE="nombre";
	private static final String ESTADOS_NO_EN_BANDEJA_DE_HALLAZGO_KEY="ESTADOS_NO_EN_BANDEJA_DE_HALLAZGO";
	private static final String CODIGO_HALLAZGOS_POR_GARANTIA="HALLAZGO_POR_GARANTIA";
	private static final Integer TH_NO_CONFORMIDAD_ANALISIS = 1; //TH = Tipo Hallazgo.
	
	//NQ = Named Query
	private static final String NQ_CONSULTAR_INDI_CALIDAD_DOCUMENTACION = "consultarIndicadorCalidadDocumentacion";
	private static final String NQ_CONSULTAR_INDI_CALIDAD_FUNCIONALIDAD = "consultarIndicadorCalidadPorFuncionalidad";
	private static final String NQ_CONSULTAR_INDI_EFECTIVIDAD_HALLAZGOS_REPORTADOS = "consultarIndicadorEfectividadHallazgosReportados";
	private static final String NQ_CONSULTAR_INDI_MEJORAS_IDENTIFICADAS = "consultarIndicadorMejorasIdentificadas";
	private static final String NQ_CONSULTAR_INDI_TIEMPOS_RESPUESTA_GARANTIA = "consultarIndicadorTiemposRespuestaGarantia";
	
	@Autowired
	private SgpParametroDao parametroDao;
	
	@Autowired
	private ProyectoDao proyectoDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private FlujoHallazgoDao flujoHallazgoDao;

	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException
	 * @since 24/02/2014O
	 * @see com.premize.sgp.dao.gestion.pruebas.HallazgoDao#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<ConsultaHallazgoDTO> getRecords(PagingCriteria criteria)
			throws AppBaseException {

		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		String searchLike = criteria.getSearch();
		List<Search> searchs = criteria.getSearchFields();
		List<SortField> sortFields = criteria.getSortFields();
		
		
		
		SgpParametro estadosNoBandeja = parametroDao.findByProperty(PARAMETRO_FIELD_CLAVE, ESTADOS_NO_EN_BANDEJA_DE_HALLAZGO_KEY);//  get(32);
		


		Query query = createNamedQuery("consultaHallazgos");

		String consulta = query.getQueryString();
		StringBuilder builder = new StringBuilder();
		
	
		
	  if ((searchLike != null) && (!(searchLike.isEmpty()))) {
			
			builder.append(" AND ((artefacto.NOMBRE ILIKE '%"+searchLike+"%')");
			builder.append(" OR (severidad.NOMBRE ILIKE '%"+searchLike+"%')");
			builder.append(" OR (prioridad.NOMBRE ILIKE '%"+searchLike+"%')");
			builder.append(" OR (tipoHallazgo.NOMBRE ILIKE '%"+searchLike+"%')");
			builder.append(" OR (hallazgo.TITULO ILIKE '%"+searchLike+"%')");
			builder.append(" OR (estado.NOMBRE ILIKE '%"+searchLike+"%')");
			builder.append(" OR (proyecto.NOMBRE ILIKE '%"+searchLike+"%')");
			builder.append(" OR (mapaPrueba.NOMBRE ILIKE '%"+searchLike+"%')");
			builder.append(" OR (usuarioAsignado.NOMBRE ILIKE '%"+searchLike+"%'))");
		}
	  
		for (Search search : searchs) {
			String field = search.getField();
			String value = search.getValue();
			
			if(value != null){
				if (field.equalsIgnoreCase("fromFechaFilter")) 
					builder.append(" AND hallazgo.FEC_CREACION  >= '"+value+" 00:00:00'");
				if(field.equalsIgnoreCase("toFechaFilter"))
					builder.append(" AND hallazgo.FEC_CREACION  <= '"+value+" 23:59:59'");
				if(field.equals("id")){
					if(!NumericUtils.isNumeric(value)){
						value="0";
					}
					builder.append(" AND hallazgo.ID_HALLAZGO = "+value);
				}
				if(field.equals("causaGeneracion"))
					builder.append(" AND causaGeneracion.id_causa_de_generacion = "+value);
				if(field.equals("tipoHallazgo"))
					if(!NumericUtils.isNumeric(value)){
					builder.append(" AND tipoHallazgo.NOMBRE ILIKE '%"+value+"%'");
					}else{
						builder.append(" AND tipoHallazgo.id_tipo_hallazgo="+value);
					}
				if(field.equals("causaAnulacion"))
					builder.append(" AND causaAnulacion.ID_PARAMETRO  = "+value);
				if(field.equals("artefacto"))
					builder.append(" AND artefacto.NOMBRE ILIKE '%"+value+"%'");
				if(field.equals("severidad"))
					builder.append(" AND severidad.NOMBRE ILIKE  '%"+value+"%'");
				if(field.equals("prioridad"))
					builder.append(" AND prioridad.NOMBRE ILIKE  '%"+value+"%'");		
				if(field.equals("titulo"))
					builder.append(" AND hallazgo.TITULO ILIKE  '%"+value+"%'");
				if(field.equals("accion"))
					builder.append(" AND estado.NOMBRE ILIKE  '%"+value+"%'");
				if(field.equals("proyecto"))
					builder.append(" AND proyecto.NOMBRE ILIKE  '%"+value+"%' ");
				if(field.equals("mapaPrueba"))
					builder.append(" AND mapaPrueba.NOMBRE ILIKE  '%"+value+"%'");
				if(field.equals("usuarioAsignado"))
					builder.append(" AND usuarioAsignado.NOMBRE  ILIKE '%"+value+"%'");
				if(field.equals("usuarioAsigna"))
					builder.append(" AND usuarioAsigna.ID_USUARIO = "+value);
				if(field.equals("indBandeja")){
					if(value.equals(IND_BANDEJA))
		             builder.append(" AND estado.NOMBRE NOT IN ("+estadosNoBandeja.getValor()+") "); 
				}
				if(field.equals("casoPruebaId")){
					if(!NumericUtils.isNumeric(value)){
						value="0";
					}
		             builder.append(" AND casoPrueba.ID_CASO_PRUEBA = "+value); 
				}
		}
			
			
		}
	
		for (SortField sortField : sortFields) {

			String field = sortField.getField();
			String direction = sortField.getDirection().getDirection();
			
			if(field.equals("fechaCreacionString")){
				field = "hallazgo.FEC_CREACION";
			}
			if (direction.equalsIgnoreCase("asc")) {
				builder.append(" ORDER BY "+field+" ASC");
			} else if (direction.equalsIgnoreCase("desc")) {
				builder.append(" ORDER BY "+field+" DESC");

			}

		}
		consulta = consulta + builder.toString();
		Query qry = createSQLQuery(consulta);
		Integer countTotalRegistros = findByOtherQuery(qry).size();
		query = createSQLQuery(consulta, new Paginator(displayStart, displaySize));
		query.setResultTransformer(Transformers.aliasToBean(ConsultaHallazgoDTO.class));
		List<ConsultaHallazgoDTO> resultList = findByOtherQuery(query);


		return new ResultSet<ConsultaHallazgoDTO>(resultList, countTotalRegistros.longValue(),
				resultList.size());
	}


	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 22/02/2014
	 * @see com.premize.sgp.dao.gestion.pruebas.HallazgoDao#hallazgosPorCasoPrueba(java.lang.Integer)
	 */
	@Override
	public List<SgpHallazgo> getHallazgosPorCasoPrueba(Integer idCasoPrueba)
			throws AppBaseException {

		DetachedCriteria criteria = DetachedCriteria
				.forClass(SgpHallazgo.class);
		criteria.add(Restrictions.eq("casoPrueba.id", idCasoPrueba));

		return findByCriteria(criteria);
	}

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.dao.gestion.pruebas.HallazgoDao#getEstadosHallazgo(java.lang.Integer)
	 */
	@Override
	public List<ParametroDTO> getEstadosHallazgo(Integer idHallazgo) throws AppBaseException {

		List<ParametroDTO> list = new ArrayList<ParametroDTO>();
		
			StringBuilder builder = new StringBuilder();

			SgpFlujoHallazgo flujo = flujoHallazgoDao
					.obtenerFlujoPorHallazgo(idHallazgo);

			builder.append(" Select parametro.id as id, parametro.nombre as nombre");
			builder.append(" From " + SgpParametro.class.getName()
					+ " parametro, " + SgpEstadoFlujo.class.getName()
					+ " estadoFlujo ");
			builder.append(" WHERE estadoFlujo.flujoActual.id = :estado");
			builder.append(" AND parametro.id = estadoFlujo.flujoSiguiente.id ");
			builder.append(" AND parametro.indActivo = 1 ");
			builder.append(" ORDER BY parametro.nombre");

			Query query = createQuery(builder.toString());
			query.setParameter("estado", flujo.getAccion().getId());

			query.setResultTransformer(Transformers
					.aliasToBean(ParametroDTO.class));

			if(!findByOtherQuery(query).isEmpty()){
			list = findByOtherQuery(query);
			}


		return list;
	}
	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache
	 * @throws ParseException 
	 * @since 2/04/2014
	 * @see com.premize.sgp.dao.gestion.pruebas.HallazgoDao#getHallazgosPorSeveridad(java.lang.Integer)
	 */
	@Override
	public List<ReporteHallazgoDTO> getHallazgosPorSeveridad(Integer idProyecto, String fechaFrom, String fechaTo, String tipoHallazgo) throws AppBaseException, ParseException {

		Query query = createNamedQuery("hallazgosPorSeveridad");
		
		Date fechaIn = null;
		Date fechaFin = null;
		
		if(validarCampo(fechaFrom)){
			fechaIn = getDateFromString(fechaFrom, true);
		}
		if(validarCampo(fechaTo)){
			fechaFin = getDateFromString(fechaTo, false);
		}


		query.setParameter("proyecto", idProyecto);
		query.setString("tipoHallazgo", tipoHallazgo);
	    query.setTimestamp("fechaFrom", fechaIn);
	    query.setTimestamp("fechaTo", fechaFin);
			

		query.setResultTransformer(Transformers
				.aliasToBean(ReporteHallazgoDTO.class));



	return findByOtherQuery(query);
		
	}

	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache
	 </a>
	 * @throws ParseException 
	 * @since 14/04/2014
	 * @see com.premize.sgp.dao.gestion.pruebas.HallazgoDao#getHallazgosPorMapaPruebas(java.lang.Integer)
	 */
	@Override
	public List<ReporteHallazgoDTO> getHallazgosPorMapaPruebas(Integer idProyecto, String fechaFrom, String fechaTo, String tipoHallazgo) throws AppBaseException, ParseException {

		List<ReporteHallazgoDTO> list = null;
			StringBuilder builder = new StringBuilder();
			
			Date fechaIn = null;
			Date fechaFin = null;
			
			if(validarCampo(fechaFrom)){
				fechaIn = getDateFromString(fechaFrom, true);
			}
			if(validarCampo(fechaTo)){
				fechaFin = getDateFromString(fechaTo, false);
			}
			
			builder.append(" SELECT  mapa.nombre as nombreMapaPruebas, count(mapa.nombre) as cantidad");
			builder.append(" FROM "+ SgpMapaPrueba.class.getName() +" mapa, "
							+ SgpCasoPrueba.class.getName() +" caso_prueba, "
							+ SgpHallazgo.class.getName() + " hallazgo ");  
			builder.append(" WHERE mapa.sgpProyecto.id = :proyecto");
			builder.append(" AND caso_prueba.sgpMapaPrueba.id = mapa.id");
			builder.append(" AND caso_prueba.id = hallazgo.casoPrueba.id");
			if(validarCampo(fechaFrom))
				builder.append(" AND hallazgo.fecCreacion >= :fechaFrom"); 

			if(validarCampo(fechaTo))
				builder.append(" AND hallazgo.fecCreacion <= :fechaTo");
			if(validarCampo(tipoHallazgo))
				builder.append(" AND hallazgo.tipoHallazgo.nombre = :tipoHallazgo");
			builder.append(" GROUP BY mapa.nombre, mapa.id ORDER BY mapa.id ASC");

			Query query = createQuery(builder.toString()); 			
			query.setParameter("proyecto", idProyecto);
			if(validarCampo(fechaFrom))
				query.setTimestamp("fechaFrom", fechaIn);
			if(validarCampo(fechaTo))
				query.setTimestamp("fechaTo", fechaFin);
			if(validarCampo(tipoHallazgo))
				query.setString("tipoHallazgo", tipoHallazgo);
			
			query.setResultTransformer(Transformers
					.aliasToBean(ReporteHallazgoDTO.class));

			if(!findByOtherQuery(query).isEmpty()){
			list = findByOtherQuery(query);
			}

 
		return list;		
	}
	

	/**
	 * @author <a href="mailto:juanm.caicedo@premize.com">Juan Manuel
	 *         Caicedo</a>
	 * @date Mar 13, 2014
	 * @param value
	 * @param initial
	 * @return
	 * @throws ParseException
	 */
	private Date getDateFromString(String value, boolean initial) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(formatoFecha);
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(dateFormat.parse(value));
		
		if (initial) {
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
		} else {
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
		}
		return calendar.getTime();
	}
	
//	public List<ConsultaHallazgoDTO> obtenerHallazgos() {
//		List<ConsutaHallazgoDTO> hallazgos;
//		Query query = createNamedQuery("consultaHallazos");
//		
//	}


	@SuppressWarnings("unchecked")
	@Override
	public List<TotalesHallazgoDTO> getTotalesHallazgoProyecto(
			Integer idProyecto,String fechaFrom, String fechaTo, String tipoHallazgo) throws AppBaseException, ParseException{

		if(idProyecto == null){
			throw new AppBaseException("El id de proyecto es nulo", "NO_PARAMETRO");
		}
		Query query = createNamedQuery("totalesHallazgo");

		Date fechaIn  = null;
		Date fechaFin = null;
		
		if(validarCampo(fechaFrom)){ 
			fechaIn  = getDateFromString(fechaFrom,true);
		}
		if(validarCampo(fechaTo)){
			fechaFin = getDateFromString(fechaTo,false);
		}
	
		query.setTimestamp("fechaFrom", fechaIn);
		query.setTimestamp("fechaTo", fechaFin);
		query.setInteger("proyecto", idProyecto);
		query.setString("tipoHallazgo", tipoHallazgo);
		query.setResultTransformer(Transformers.aliasToBean(TotalesHallazgoDTO.class));
		return findByOtherQuery(query);
	}

 
	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.dao.gestion.pruebas.HallazgoDao#getUsuarios_Hallazgos(java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public List<TotalHallazgoDTO> getUsuarios_Hallazgos(Integer idProyecto,
			String fechaFrom, String fechaTo, String tipoHallazgo) throws AppBaseException,
			ParseException {

		 Query query = createNamedQuery("hallazgosUsuarios");
	       
	       Date fechaIn = null;
			Date fechaFin = null;
			String tipoHallazgoStr = null;
			
			if(validarCampo(fechaFrom)) 
				fechaIn = getDateFromString(fechaFrom,true);
			if(validarCampo(fechaTo))
				fechaFin = getDateFromString(fechaTo,false);
			if(validarCampo(tipoHallazgo))
				tipoHallazgoStr = tipoHallazgo;
			
			query.setInteger("proyecto", idProyecto);
			query.setTimestamp("fechaFrom", fechaIn);
			query.setTimestamp("fechaTo", fechaFin);
			query.setString("tipoHallazgo", tipoHallazgoStr);
			

	        query.setResultTransformer(Transformers.aliasToBean(TotalHallazgoDTO.class));
			
			return findByOtherQuery(query);
	}
 
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 21/04/2014
	 * @see com.premize.sgp.dao.gestion.pruebas.HallazgoDao#consultarHallazgos(com.premize.sgp.dto.pruebas.FlujoHallazgoDTO)
	 */
	@Override
	public List<ConsultaHallazgoDTO> consultarHallazgos(Map<String, Object> filtros) 
			throws AppBaseException, NumberFormatException {
		
		Query query = createNamedQuery("consultaHallazgos");
		
		String consulta = query.getQueryString();
		StringBuilder builder = new StringBuilder();
		
			if (validarCampo(filtros.get("fechaFrom"))) {
				String value  = filtros.get("fechaFrom").toString();
				try{
					builder.append(" AND hallazgo.FEC_CREACION   >= '"+value+" 00:00:00'");
				}
				catch(Exception e){
					throw new AppBaseException(e.getMessage(), e, CODIGO_ERROR);
				}
			}
			if(validarCampo(filtros.get("fechaTo"))){
				String value = filtros.get("fechaTo").toString();
				try{
					builder.append(" AND hallazgo.FEC_CREACION <= '"+value+" 23:59:59'");
				}
				catch(Exception e){
					throw new AppBaseException(e.getMessage(), e, CODIGO_ERROR);
				}
			}

			if(validarCampo(filtros.get("causaGenera"))){
				Integer value = new Integer(filtros.get("causaGenera").toString());
				builder.append(" AND causaGeneracion.ID_PARAMETRO = "+value);
			}
			if(validarCampo(filtros.get("causaAnula"))){
				Integer value = new Integer(filtros.get("causaAnula").toString());
				builder.append(" AND causaAnulacion.ID_PARAMETRO  = "+value);
			}


			if(validarCampo(filtros.get("proyecto"))){
				String value = filtros.get("proyecto").toString();
				builder.append(" AND proyecto.NOMBRE = '"+value+"'");
			}
			
			if(validarCampo(filtros.get("mapaPrueba"))){
				Integer value = new Integer(filtros.get("mapaPrueba").toString());
				builder.append(" AND mapaPrueba.ID_MAPA_PRUEBA = '"+value+"'");
			}
			
			if(validarCampo(filtros.get("usuarioAsigna"))){
				String value = filtros.get("usuarioAsigna").toString();
				builder.append(" AND usuarioAsigna.NOMBRE = '"+value+"'");
			}

			// FILTROS DE LA GRILLA:
			Object valor = filtros.get("idHallazgo");
			//                        placeholder del filtro de la grilla
			if(validarCampo(valor) && !valor.toString().equals("#")){
				Integer value = new Integer (filtros.get("idHallazgo").toString());
				builder.append(" AND hallazgo.ID_HALLAZGO = "+value);
			}
			
			valor = filtros.get("artefacto");
			if(validarCampo(valor) && !valor.toString().equals("Artefacto")){
				String value = filtros.get("artefacto").toString();
				builder.append(" AND artefacto.NOMBRE ILIKE '%"+value+"%'");
			}
			
			valor = filtros.get("usuarioAsignado");
			if(validarCampo(valor) && !valor.toString().equals("Usuario Asignado")){
				String value = filtros.get("usuarioAsignado").toString();
				builder.append(" AND usuarioAsignado.NOMBRE  ILIKE '%"+value+"%'");
			}
			
			valor = filtros.get("severidad");
			if(validarCampo(valor) && !valor.toString().equals("Severidad")) {
				builder.append(" AND severidad.NOMBRE ILIKE '%"+valor.toString()+"%'");
			}
			
			valor = filtros.get("prioridad");
			if(validarCampo(valor) && !valor.toString().equals("Prioridad")) {
				builder.append(" AND prioridad.NOMBRE ILIKE '%"+valor.toString()+"%'");
			}
			
			valor = filtros.get("tipoHallazgo");
			if(validarCampo(valor) && !valor.toString().equals("Tipo de Hallazgo")) {
				builder.append(" AND tipoHallazgo.NOMBRE ILIKE '%"+valor.toString()+"%'");
			}
			
			valor = filtros.get("titulo");
			if(validarCampo(valor) && !valor.toString().equals("Título")) {
				builder.append(" AND hallazgo.TITULO ILIKE '%"+valor.toString()+"%'");
			}
			
			valor = filtros.get("estado");
			if(validarCampo(valor) && !valor.toString().equals("Estado")) {
				builder.append(" AND estado.NOMBRE ILIKE '%"+valor.toString()+"%'");
			}
			
	
		consulta = consulta + builder.toString();
		query = createSQLQuery(consulta);
		query.setResultTransformer(Transformers.aliasToBean(ConsultaHallazgoDTO.class));
		List<ConsultaHallazgoDTO> resultList = findByOtherQuery(query);
		

		return resultList;
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
	 */
	@Override
	public List<CalidadDocumentacionDTO> consultarIndicadorCalidadDocumentacion(Integer idProyecto,
			Date fechaFrom, Date fechaTo) throws AppBaseException {
		Query query = createNamedQuery(NQ_CONSULTAR_INDI_CALIDAD_DOCUMENTACION);
		query.setInteger("idProyecto", idProyecto);
		query.setInteger("idTipoHallazgo", TH_NO_CONFORMIDAD_ANALISIS);
		query.setTimestamp("fechaFrom", fechaFrom);
		query.setTimestamp("fechaTo", fechaTo);
		query.setResultTransformer(Transformers.aliasToBean(CalidadDocumentacionDTO.class));
		List<CalidadDocumentacionDTO> result = findByOtherQuery(query);
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
	 */
	@Override
	public List<IndCalidadFuncionalidadDTO> consultarIndicadorFuncionalidad(Integer idProyecto,
			Date fechaFrom, Date fechaTo) throws AppBaseException {
		Query query = createNamedQuery(NQ_CONSULTAR_INDI_CALIDAD_FUNCIONALIDAD);
		query.setInteger("idProyecto", idProyecto);
		query.setTimestamp("fechaFrom", fechaFrom);
		query.setTimestamp("fechaTo", fechaTo);
		query.setResultTransformer(Transformers.aliasToBean(IndCalidadFuncionalidadDTO.class));
		List<IndCalidadFuncionalidadDTO> result = findByOtherQuery(query);
		return result;
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
	 */
	public IndEfectividadHallazgosReportadosDTO consultarIndicadorEfectividadHallazgosReportados(Integer idProyecto,
			Date fechaFrom, Date fechaTo) throws AppBaseException {
		Query query = createNamedQuery(NQ_CONSULTAR_INDI_EFECTIVIDAD_HALLAZGOS_REPORTADOS);
		query.setInteger("idProyecto", idProyecto);
		query.setTimestamp("fechaFrom", fechaFrom);
		query.setTimestamp("fechaTo", fechaTo);
		query.setResultTransformer(Transformers.aliasToBean(IndEfectividadHallazgosReportadosDTO.class));
		IndEfectividadHallazgosReportadosDTO result = (IndEfectividadHallazgosReportadosDTO) query.uniqueResult();
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
	 */
	@Override
	public List<IndMejorasIdentificadasDTO> consultarMejorasIndentificadas(Integer idProyecto,
			Date fechaFrom, Date fechaTo) throws AppBaseException {
		Query query = createNamedQuery(NQ_CONSULTAR_INDI_MEJORAS_IDENTIFICADAS);
		query.setInteger("idProyecto", idProyecto);
		query.setTimestamp("fechaFrom", fechaFrom);
		query.setTimestamp("fechaTo", fechaTo);
		query.setResultTransformer(Transformers.aliasToBean(IndMejorasIdentificadasDTO.class));
		List<IndMejorasIdentificadasDTO> result = findByOtherQuery(query);
		return result;
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
	 */
	@Override
	public List<TiemposRespuestaGarantiaDTO> consultarTiemposRespuestaGarantia(Integer idProyecto,
			Date fechaFrom, Date fechaTo) throws AppBaseException {
		Query query = createNamedQuery(NQ_CONSULTAR_INDI_TIEMPOS_RESPUESTA_GARANTIA);
		query.setInteger("idProyecto", idProyecto);
		query.setTimestamp("fechaFrom", fechaFrom);
		query.setTimestamp("fechaTo", fechaTo);
		query.setResultTransformer(Transformers.aliasToBean(TiemposRespuestaGarantiaDTO.class));
		List<TiemposRespuestaGarantiaDTO> result = findByOtherQuery(query);
		return result;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/04/2014
	 * @param object
	 * @return
	 */
	private Boolean validarCampo(Object object){
		 Boolean res = false;
		 
		 if(object != null && !object.toString().isEmpty())
		   res = true;
		 
		return res;
		
	}


	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 8/07/2014
	 * @see com.premize.sgp.dao.gestion.pruebas.HallazgoDao#getHallazgosPorArtefacto(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<ReporteHallazgoDTO> getHallazgosPorArtefacto(
			Integer idProyecto, String fechaFrom, String fechaTo,
			String tipoHallazgo) throws AppBaseException, ParseException{
		List<ReporteHallazgoDTO> reporteHallazgoDTOs=new ArrayList<ReporteHallazgoDTO>();
		
		Query query = createNamedQuery("hallazgosPorArtefacto");
		Date fechaIn = null;
		Date fechaFin = null;
		
		if(validarCampo(fechaFrom)){
			fechaIn = getDateFromString(fechaFrom, true);
		}
		if(validarCampo(fechaTo)){
			fechaFin = getDateFromString(fechaTo, false);
		}
		
		query.setParameter("proyecto", idProyecto);
		query.setString("tipoHallazgo", tipoHallazgo);
		query.setTimestamp("fechaFrom", fechaIn);
		query.setTimestamp("fechaTo", fechaFin);
		
		query.setResultTransformer(Transformers
				.aliasToBean(ReporteHallazgoDTO.class));

		
		
		reporteHallazgoDTOs= findByOtherQuery(query);
		
		if(reporteHallazgoDTOs.isEmpty()){
			//para no pintar el grafico
			reporteHallazgoDTOs=null;
		}
		
	return reporteHallazgoDTOs;
	}

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @throws AppBaseException 
 * @since 11/07/2014
 * @see com.premize.sgp.dao.gestion.pruebas.HallazgoDao#getHallazgosPorArtefacto(java.lang.String, java.lang.String, java.lang.String, java.util.List, java.util.List)
 */
	@Override
	public List<ReporteHallazgoDTO> getHallazgosPorRecurso(String proyecto,
			String fechaFrom, String fechaTo, List<Integer> listEstados,
			List<Integer> listCausas) throws AppBaseException, ParseException {
		Query query = createNamedQuery("hallazgosPorRecurso");
		if(listEstados.isEmpty()){
			listEstados.add(0);
		}
		if(listCausas.isEmpty()){
			listCausas.add(0);
		}
		Date fechaIn = null;
		Date fechaFin = null;
		
		if(validarCampo(fechaFrom)){
			fechaIn = getDateFromString(fechaFrom, true);
		}
		if(validarCampo(fechaTo)){
			fechaFin = getDateFromString(fechaTo, false);
		}
	
		query.setInteger("proyecto", new Integer(proyecto));
		query.setTimestamp("fechaFrom", fechaIn);
		query.setTimestamp("fechaTo", fechaFin);
		query.setParameterList("estados", listEstados);
		query.setParameterList("causas", listCausas);
		query.setResultTransformer(Transformers.aliasToBean(ReporteHallazgoDTO.class));
		List<ReporteHallazgoDTO> result = findByOtherQuery(query);
		return result;
	}

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 11/07/2014
	* @param usuario
	* @return
	* @throws AppBaseException
	 */
	@Override
	public List<ConsultaHallazgoDTO> getSeveridadColorHallazgo(String usuario) throws AppBaseException{
		SgpParametro estadosNoBandeja = parametroDao.findByProperty(PARAMETRO_FIELD_CLAVE, ESTADOS_NO_EN_BANDEJA_DE_HALLAZGO_KEY);//  get(32);

		Query query = createNamedQuery("consultaHallazgosSeveridad");

		String consulta = query.getQueryString();
		StringBuilder builder = new StringBuilder();
		
		builder.append(" AND usuarioAsignado.NOMBRE  ILIKE '%"+usuario+"%'");
		 builder.append(" AND estado.NOMBRE NOT IN ("+estadosNoBandeja.getValor()+") "); 
		 consulta = consulta + builder.toString();
			Query qry = createSQLQuery(consulta);

			query = createSQLQuery(consulta);
			query.setResultTransformer(Transformers.aliasToBean(ConsultaHallazgoDTO.class));
			List<ConsultaHallazgoDTO> resultList = findByOtherQuery(query);
			return resultList;
	}
	
	
}
