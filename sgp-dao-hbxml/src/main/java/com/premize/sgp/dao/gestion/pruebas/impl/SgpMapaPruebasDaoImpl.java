package com.premize.sgp.dao.gestion.pruebas.impl;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.dao.impl.HibernateDaoImpl;
import com.premize.sgp.dao.gestion.pruebas.MapaPruebasDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.Search;
import com.premize.sgp.dto.SortField;
import com.premize.sgp.dto.pruebas.MapaPruebasDTO;
import com.premize.sgp.dto.pruebas.ReporteMapaPruebaDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndReprocesoConstruccionPruebasDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpCasoPrueba;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpHallazgo;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpMapaPrueba;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpProyecto;
import com.premize.sgp.utils.NumericUtils;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class SgpMapaPruebasDaoImpl
 * @since 27/03/2014
 *
 */
@Service
public class SgpMapaPruebasDaoImpl extends HibernateDaoImpl<SgpMapaPrueba, Integer> implements MapaPruebasDao {
	
	/**
	 * Ruta para acceder a la propiedad de un parametro.
	 * 
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo a soto</a>
	 * @date 04/02/2014
	 */
	private static final String PROYECTO_ID = "sgpProyecto.id";
	private static final String formatoFecha = "yyyy/MM/dd";
	
	/**
	 * Ruta para acceder a la propiedad de un parametro.
	 * 
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo a soto</a>
	 * @date 04/02/2014
	 */
	private static final String PROYECTO = "sgpProyecto";
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.dao.gestion.pruebas.MapaPruebasDao#consultarMapaPruebasPorProyecto(java.lang.Integer, com.premize.pmz.api.dto.Paginator)
	 */
	@Override
	public List<MapaPruebasDTO> consultarMapaPruebasPorProyecto(Integer idProyecto, Paginator page)
			throws AppBaseException {
		List<SgpMapaPrueba> mapaPruebas = this.findByCriteria(listMapaPruebaPorProyecto(idProyecto), page);
		
		List<MapaPruebasDTO> mapaPruebasDTOs = new ArrayList<MapaPruebasDTO>();
		for (SgpMapaPrueba mapaPrueba : mapaPruebas) {
			MapaPruebasDTO mapaPruebasDTO = new MapaPruebasDTO(mapaPrueba);
			mapaPruebasDTOs.add(mapaPruebasDTO);
		}
		return mapaPruebasDTOs;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param idProyecto
	 * @return
	 */
	private DetachedCriteria listMapaPruebaPorProyecto(Integer idProyecto) {
		
		DetachedCriteria consulta = DetachedCriteria.forClass(SgpMapaPrueba.class);
		consulta.add(Restrictions.or(Restrictions.eq(PROYECTO_ID, idProyecto), Restrictions.isNull(PROYECTO)));
		consulta.addOrder(Order.asc("id"));
		return consulta;
	}
	

	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 12/02/2014
	 * @see com.premize.sgp.dao.gestion.pruebas.MapaPruebasDao#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<MapaPruebasDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		String searchLike = criteria.getSearch();
		List<Search> searchs = criteria.getSearchFields();
		List<SortField> sortFields = criteria.getSortFields();
		
		// Eliminamos los campos que no existen en la entidad
		for (SortField sortField : sortFields) {
			String field = sortField.getField();
			
			if (field.equals("totalPruebasConstruidas")) {
				sortFields.remove(sortField);
			} else if (field.equals("totalPruebasEjecutadas")) {
				sortFields.remove(sortField);
			} else if (field.equals("totalPruebasAnuladas")) {
				sortFields.remove(sortField);
			}else if(field.equals("totaHallazgos")) {
				sortFields.remove(sortField);
			}
		}
		
		DetachedCriteria cq = DetachedCriteria.forClass(SgpMapaPrueba.class).createAlias(PROYECTO, "proyecto");
		
		// Filtering and Searching
		
		if ((searchLike != null) && (!(searchLike.isEmpty()))) {
			
			Disjunction disyuctionConsulta = Restrictions.disjunction();
			disyuctionConsulta.add(Restrictions.like("nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("proyecto.nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			cq.add(disyuctionConsulta);
		}
		
		for (Search search : searchs) {
			String field = search.getField();
			String value = search.getValue();
			
			if(value != null){
				if (field.equals("indActivo")) {
					if (value != null)
						cq.add(Restrictions.eq(field, Integer.parseInt(value)));
				}else if (field.equals("id")){
				
						if(!NumericUtils.isNumeric(value)){
							value="0";
						}
						cq.add(Restrictions.eq(field, Integer.parseInt(value)));
					
				}else if (field.equalsIgnoreCase("fromFechaFilter")) {
				try {
					Date fechaInicio = getDateFromString(value, true);
					cq.add(Restrictions.ge("fecCreacion", fechaInicio));
				} catch (ParseException pex) {
				}
			} else if (field.equalsIgnoreCase("toFechaFilter")) {
				try {
					Date fechaFin = getDateFromString(value, false);
					cq.add(Restrictions.le("fecCreacion", fechaFin));
				} catch (Exception e) {

				}

			} else {
					cq.add(Restrictions.like(field, value, MatchMode.ANYWHERE)
							.ignoreCase());
			}
			}
		}
		
		// Sorting
		for (SortField sortField : sortFields) {
			String field = sortField.getField();
			String direction = sortField.getDirection().getDirection();
			if (direction.equalsIgnoreCase("asc")) {
				cq.addOrder(Order.asc(field));
			} else if (direction.equalsIgnoreCase("desc")) {
				cq.addOrder(Order.desc(field));
			}
		}
		Integer countTotalRegistros = findByCriteria(cq).size();
		List<SgpMapaPrueba> listaReturn = findByCriteria(cq, new Paginator(displayStart, displaySize));
		
		List<MapaPruebasDTO> resultList = new ArrayList<MapaPruebasDTO>();
		for (SgpMapaPrueba mapaPrueba : listaReturn) {
           MapaPruebasDTO mapaPruebaDTO = new MapaPruebasDTO(mapaPrueba);
	
		// Buffer's para query's
		StringBuffer buffer = new StringBuffer();
		buffer.append("SELECT COUNT(casoprueba.id) as totalPruebasConstruidas ");
		buffer.append("FROM com.premize.sgp.modelo.entities.gestion.pruebas.SgpCasoPrueba casoprueba ");
		buffer.append("WHERE casoprueba.sgpMapaPrueba.id = :mapa_id ");
		Query query = (Query) createQuery(buffer.toString());
		query.setParameter("mapa_id", mapaPruebaDTO.getId());
		Integer construidas = Integer.valueOf(query.list().get(0).toString());
		
		buffer = new StringBuffer();
		buffer.append("SELECT COUNT(casoprueba.id) as totalPruebasConstruidas ");
		buffer.append("FROM com.premize.sgp.modelo.entities.gestion.pruebas.SgpCasoPrueba casoprueba ");
		buffer.append("WHERE casoprueba.sgpMapaPrueba.id = :mapa_id AND casoprueba.cumple is not null AND casoprueba.cumple <> 'NA'");
		query = (Query) createQuery(buffer.toString());
		query.setParameter("mapa_id", mapaPruebaDTO.getId());
		Integer ejecutadas = Integer.valueOf(query.list().get(0).toString());
		
		//Alex: consultar la cantidad o total de hallazgos.
        buffer = new StringBuffer();
        buffer.append("SELECT COUNT(hallazgo.id) as totalHallazgos ");
        buffer.append("FROM "+SgpHallazgo.class.getName()+" hallazgo, "
				+ SgpCasoPrueba.class.getName()+" cp, "+SgpMapaPrueba.class.getName()+" mp ");
        buffer.append("WHERE hallazgo.casoPrueba.id = cp.id ");
        buffer.append("AND cp.sgpMapaPrueba.id = mp.id AND mp.id =:mapa_id");
        query = (Query) createQuery(buffer.toString());
		query.setParameter("mapa_id", mapaPruebaDTO.getId());
		Integer totalHallazgos = Integer.valueOf(query.list().get(0).toString());
		
		mapaPruebaDTO.setTotalPruebasConstruidas(construidas);
		mapaPruebaDTO.setTotalPruebasEjecutadas(ejecutadas);
		mapaPruebaDTO.setTotalHallazgos(totalHallazgos);
		resultList.add(mapaPruebaDTO);
		}
	
		return new ResultSet<MapaPruebasDTO>(resultList, countTotalRegistros.longValue(), listaReturn.size());
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 30/04/2014
	 * @see com.premize.sgp.dao.gestion.pruebas.MapaPruebasDao#getRecordsEjecucion(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<MapaPruebasDTO> getRecordsEjecucion(PagingCriteria criteria) throws AppBaseException {
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		String searchLike = criteria.getSearch();
		List<Search> searchs = criteria.getSearchFields();
		List<SortField> sortFields = criteria.getSortFields();
		
		DetachedCriteria cq = DetachedCriteria.forClass(SgpMapaPrueba.class).createAlias(PROYECTO, "proyecto");
		
		// Filtering and Searching		
		if ((searchLike != null) && (!(searchLike.isEmpty()))) {
			
			Disjunction disyuctionConsulta = Restrictions.disjunction();
			disyuctionConsulta.add(Restrictions.like("nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("proyecto.nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			cq.add(disyuctionConsulta);
		}
		
		for (Search search : searchs) {
			String field = search.getField();
			String value = search.getValue();
			if(field.equals("proyecto.nombre") && value == null)
				cq.add(Restrictions.eq(field, ""));
			if(field.equals("idProyecto") && value == null){
				cq.add(Restrictions.eq(field, "0"));
			}
			if(value != null){
			if (field.equals("id") || field.equals("indActivo")) {
					cq.add(Restrictions.eq(field, Integer.parseInt(value)));
			} else if (field.equalsIgnoreCase("fromFechaFilter")) {
				try {
					Date fechaInicio = getDateFromString(value, true);
					cq.add(Restrictions.ge("fecCreacion", fechaInicio));
				} catch (ParseException pex) {
				}
			} else if (field.equalsIgnoreCase("toFechaFilter")) {
				try {
					Date fechaFin = getDateFromString(value, false);
					cq.add(Restrictions.le("fecCreacion", fechaFin));
				} catch (Exception e) {

				}

			} else {
					cq.add(Restrictions.like(field, value, MatchMode.ANYWHERE)
							.ignoreCase());
			}
			}
		}
		
		// Sorting
		for (SortField sortField : sortFields) {
			String field = sortField.getField();
			String direction = sortField.getDirection().getDirection();
			if (direction.equalsIgnoreCase("asc")) {
				cq.addOrder(Order.asc(field));
			} else if (direction.equalsIgnoreCase("desc")) {
				cq.addOrder(Order.desc(field));
			}
		}
		
		Integer countTotalRegistros = findByCriteria(cq).size();
		List<SgpMapaPrueba> listaTodos =  findByCriteria(cq);
		List<SgpMapaPrueba> listaReturn = findByCriteria(cq, new Paginator(displayStart, displaySize));
		int lastPost = listaReturn.size() - 1; 
		int sumaPruebasConstruidas = 0;
		int sumaPruebasEjecutadas = 0;
		int sumaPruebasSatisfactorias = 0;
		int sumaPruebasInsatisfactorias = 0;
		int sumaPruebasSinEjecutar = 0;
		int sumaPruebasAnuladas = 0;
		int cant=0;
		double sumaIndicador = 0.0;
		String sumaAvance = "";
		String sumaIndicadorStr ="";
	
		// Buffer's para query's
		StringBuffer bufferConstruidas = new StringBuffer();
		bufferConstruidas.append("SELECT COUNT(casoprueba.id) as totalPruebasConstruidas ");
		bufferConstruidas.append("FROM com.premize.sgp.modelo.entities.gestion.pruebas.SgpCasoPrueba casoprueba ");
		bufferConstruidas.append("WHERE casoprueba.sgpMapaPrueba.id = :mapa_id ");
		
		StringBuffer bufferEjecutadas = new StringBuffer();
		bufferEjecutadas.append("SELECT COUNT(casoprueba.id) as totalPruebasConstruidas ");
		bufferEjecutadas.append("FROM com.premize.sgp.modelo.entities.gestion.pruebas.SgpCasoPrueba casoprueba ");
		bufferEjecutadas.append("WHERE casoprueba.sgpMapaPrueba.id = :mapa_id AND casoprueba.cumple is not null AND casoprueba.cumple <> 'NA'");
		
		//Alex: consultar la cantidad o total de hallazgos.
		StringBuffer bufferTotalHallazgos = new StringBuffer();
		bufferTotalHallazgos.append("SELECT COUNT(hallazgo.id) as totalHallazgos ");
		bufferTotalHallazgos.append("FROM "+SgpHallazgo.class.getName()+" hallazgo, "
				+ SgpCasoPrueba.class.getName()+" cp, "+SgpMapaPrueba.class.getName()+" mp ");
		bufferTotalHallazgos.append("WHERE hallazgo.casoPrueba.id = cp.id ");
		bufferTotalHallazgos.append("AND cp.sgpMapaPrueba.id = mp.id AND mp.id =:mapa_id");
		
		//Jonathan: consultar la cantidad de pruebas anuladas
		StringBuffer bufferTotalAnuladas = new StringBuffer();
		bufferTotalAnuladas.append("SELECT COUNT(casoprueba.id) as totalPruebasConstruidas ");
		bufferTotalAnuladas.append("FROM com.premize.sgp.modelo.entities.gestion.pruebas.SgpCasoPrueba casoprueba ");
		bufferTotalAnuladas.append("WHERE casoprueba.sgpMapaPrueba.id = :mapa_id AND casoprueba.cumple = 'NA'");
		
		//Jonathan: consultar la cantidad de pruebas satisfactorias
		StringBuffer bufferTotalSatisfactorias = new StringBuffer();
		bufferTotalSatisfactorias.append("SELECT COUNT(casoprueba.id) as totalPruebasConstruidas ");
		bufferTotalSatisfactorias.append("FROM com.premize.sgp.modelo.entities.gestion.pruebas.SgpCasoPrueba casoprueba ");
		bufferTotalSatisfactorias.append("WHERE casoprueba.sgpMapaPrueba.id = :mapa_id AND casoprueba.cumple = 'Si'");
		
		//Jonathan: consultar la cantidad de pruebas satisfactorias
		StringBuffer bufferTotalInsatisfactorias = new StringBuffer();
		bufferTotalInsatisfactorias.append("SELECT COUNT(casoprueba.id) as totalPruebasConstruidas ");
		bufferTotalInsatisfactorias.append("FROM com.premize.sgp.modelo.entities.gestion.pruebas.SgpCasoPrueba casoprueba ");
		bufferTotalInsatisfactorias.append("WHERE casoprueba.sgpMapaPrueba.id = :mapa_id AND casoprueba.cumple = 'No'");
		
		//
		
		// Calcular Valores sumatoria
		for (SgpMapaPrueba mapaPrueba : listaTodos) {
			
			MapaPruebasDTO mapaPruebaDTO = new MapaPruebasDTO(mapaPrueba);
			// Calculamos los totales de las pruebas contruidas y ejecutadas
			Query query = (Query) createQuery(bufferConstruidas.toString());
			query.setParameter("mapa_id", mapaPruebaDTO.getId());
			sumaPruebasConstruidas+= Integer.valueOf(query.list().get(0).toString());

			if(Integer.valueOf(query.list().get(0).toString()) != 0)
			cant++;
			
			query = (Query) createQuery(bufferEjecutadas.toString());
			query.setParameter("mapa_id", mapaPruebaDTO.getId());
			sumaPruebasEjecutadas+= Integer.valueOf(query.list().get(0).toString());
			
			query = (Query) createQuery(bufferTotalAnuladas.toString());
			query.setParameter("mapa_id", mapaPruebaDTO.getId());
			sumaPruebasAnuladas+= Integer.parseInt(query.uniqueResult().toString());

			query = (Query) createQuery(bufferTotalSatisfactorias.toString());
			query.setParameter("mapa_id", mapaPruebaDTO.getId());
			sumaPruebasSatisfactorias+= Integer.parseInt(query.uniqueResult().toString());

			query = (Query) createQuery(bufferTotalInsatisfactorias.toString());
			query.setParameter("mapa_id", mapaPruebaDTO.getId());
			sumaPruebasInsatisfactorias+= Integer.parseInt(query.uniqueResult().toString());
		}
		
		sumaPruebasSinEjecutar+= sumaPruebasConstruidas - ( sumaPruebasEjecutadas + sumaPruebasAnuladas);
		
		//Jonathan: (%) sumatoria porcentaje de avance pruebas
		sumaAvance = "0";
		if( (sumaPruebasConstruidas - sumaPruebasAnuladas) != 0)
		{
		   double result = ( ( (double)sumaPruebasEjecutadas / (sumaPruebasConstruidas - sumaPruebasAnuladas) ) * 100);
		   if(result >= 1.0)
		   {
			   DecimalFormat df = new DecimalFormat("0.00");
			   sumaAvance = df.format(result);
		   }
		}
		
		sumaAvance+= "%"; 
	
		List<MapaPruebasDTO> resultList = new ArrayList<MapaPruebasDTO>();
		for (SgpMapaPrueba mapaPrueba : listaReturn) {
			MapaPruebasDTO mapaPruebaDTO = new MapaPruebasDTO(mapaPrueba);
			// Calculamos los totales de las pruebas contruidas y ejecutadas
			Query query = (Query) createQuery(bufferConstruidas.toString());
			query.setParameter("mapa_id", mapaPruebaDTO.getId());
			Integer construidas = Integer.valueOf(query.list().get(0).toString());
			
			query = (Query) createQuery(bufferEjecutadas.toString());
			query.setParameter("mapa_id", mapaPruebaDTO.getId());
			Integer ejecutadas = Integer.valueOf(query.list().get(0).toString());
			
			query = (Query) createQuery(bufferTotalHallazgos.toString());
			query.setParameter("mapa_id", mapaPruebaDTO.getId());
			Integer totalHallazgos = Integer.parseInt(query.uniqueResult().toString());

			query = (Query) createQuery(bufferTotalAnuladas.toString());
			query.setParameter("mapa_id", mapaPruebaDTO.getId());
			Integer totalAnuladas = Integer.parseInt(query.uniqueResult().toString());

			query = (Query) createQuery(bufferTotalSatisfactorias.toString());
			query.setParameter("mapa_id", mapaPruebaDTO.getId());
			Integer totalSatisfactorias = Integer.parseInt(query.uniqueResult().toString());

			query = (Query) createQuery(bufferTotalInsatisfactorias.toString());
			query.setParameter("mapa_id", mapaPruebaDTO.getId());
			Integer totalInsatisfactorias = Integer.parseInt(query.uniqueResult().toString());
			
			//Jonathan: consultar la cantidad de pruebas sin ejecutarse
			Integer sinEjecutar = construidas - ( ejecutadas + totalAnuladas);
			
			
			//Jonathan: (%) porcentaje de avance pruebas
			String porcentaje = "0";
			if( (construidas - totalAnuladas) != 0)
			{
			   double result = ( ( (double)ejecutadas / (construidas - totalAnuladas) ) * 100);
			   if(result >= 1.0)
			   {
				   DecimalFormat df = new DecimalFormat("0.00");
			       porcentaje = df.format(result);
			   }
			}
			
			porcentaje+= "%";     
			//

			//Jonathan: (%) porcentaje de avance pruebas
			String indicadorEfectividad = "0";
		
			if( (ejecutadas) != 0)
			{
				
			   double result = (( (double)totalSatisfactorias / ejecutadas )  * 100);
			   sumaIndicador += result;
			   if(result >= 1.0)
			   {
				   DecimalFormat df = new DecimalFormat("0.00");
				   indicadorEfectividad = df.format(result);
			   }
			   
			   
			}
			
			indicadorEfectividad+= "%";
			//
			
			mapaPruebaDTO.setTotalPruebasConstruidas(construidas);
			mapaPruebaDTO.setTotalPruebasEjecutadas(ejecutadas);
			mapaPruebaDTO.setTotalHallazgos(totalHallazgos);
			mapaPruebaDTO.setTotalPruebasAnuladas(totalAnuladas);
			mapaPruebaDTO.setTotalPruebasSinEjecutar(sinEjecutar); 
			mapaPruebaDTO.setTotalPruebasSatisfactorias(totalSatisfactorias);
			mapaPruebaDTO.setTotalPruebasInsatisfactorias(totalInsatisfactorias);
			mapaPruebaDTO.setPorcentajeAvance(porcentaje);
			mapaPruebaDTO.setPorcentajeIndicador(indicadorEfectividad);
			resultList.add(mapaPruebaDTO); 
		}
		//Jonathan: (%) sumatoria indicador 
				System.out.println(sumaIndicador);
				if(countTotalRegistros != 0)
					sumaIndicador =  ( (double)sumaIndicador / countTotalRegistros );
				
				if(sumaIndicador > 1.0){
					   DecimalFormat df = new DecimalFormat("0.00");
					   sumaIndicadorStr = df.format(sumaIndicador)+" %"; 
				   }
		
		if(resultList.size() > 0){
		resultList.get(lastPost).setSumatoriaPruebasConstruidas(sumaPruebasConstruidas);
		resultList.get(lastPost).setSumatoriaPruebasEjecutadas(sumaPruebasEjecutadas);
		resultList.get(lastPost).setSumatoriaPruebasSatisfactorias(sumaPruebasSatisfactorias); 
		resultList.get(lastPost).setSumatoriaPruebasInsatisfactorias(sumaPruebasInsatisfactorias); 
		resultList.get(lastPost).setSumatoriaPruebasSinEjecutar(sumaPruebasSinEjecutar);
		resultList.get(lastPost).setSumatoriaPruebasAnuladas(sumaPruebasAnuladas);
		resultList.get(lastPost).setSumatoriaPorcentajeAvance(sumaAvance);
		resultList.get(lastPost).setSumatoriaPorcentajeIndicador(sumaIndicadorStr);
		}
		return new ResultSet<MapaPruebasDTO>(resultList, countTotalRegistros.longValue(), listaReturn.size());
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 27/06/2014
	 * @description 
	 * @param criteria
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException
	 */
	@Override
	public ResultSet<MapaPruebasDTO> getRecordsAvanceEjecucion(PagingCriteria criteria) 
			throws AppBaseException, ParseException {
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		List<Search> searchs = criteria.getSearchFields();
		String searchLike = criteria.getSearch();
		List<SortField> sortFields = criteria.getSortFields();
		
		Query namedQuery = createNamedQuery(NQ_REPORTE_EJECUCION_AVANCE_MAPAS_GRILLA);
		String queryString = namedQuery.getQueryString();
		StringBuilder queryBuilder = new StringBuilder(queryString);
		
		Integer idProyecto = null;
		Date fechaInicial = null;
		Date fechaFinal = null;
		String fecFinal;
		
		
		  if ((searchLike != null) && (!(searchLike.isEmpty()))) {
				
			  queryBuilder.append(" AND ( (mp.nombre ILIKE '%"+searchLike+"%')");
			  queryBuilder.append(" OR (pr.NOMBRE ILIKE '%"+searchLike+"%')) ");
			 
			}
		
		
		for(Search search : searchs) {
			String field = search.getField();
			String value = search.getValue();
			
	
		
				if(field.equals("idProyecto")) {
					if(value != null) {
					idProyecto = Integer.parseInt(value);
					}
				} else if (field.equalsIgnoreCase("fromFechaFilter")) {
					if(value != null) {
					fechaInicial = getDateFromString(value, true);
					}
				} else if (field.equalsIgnoreCase("toFechaFilter")) {
					if(value != null) {
					fechaFinal = getDateFromString(value, false);
					
					}
				} else if (field.equals("nombre")){
					if(value != null) {
						queryBuilder.append((" AND (mp.nombre ILike '%"+value+"%')"));
					}
				}else if (field.equals("proyecto.nombre")){
					if(value != null) {
						queryBuilder.append((" AND (pr.nombre ILike '%"+value+"%')"));
					}
				}else if (field.equals("id")){
					if(!NumericUtils.isNumeric(value)){
						value="0";
					}
					if(value != null) {
						queryBuilder.append((" AND (mp.id_mapa_prueba ="+value+")"));
					}
				}
				  
		}
		
		queryBuilder.append(" GROUP BY mp.id_mapa_prueba, mp.nombre ,pr.nombre ");
		
		for(SortField sortField : sortFields) {
			String field = sortField.getField();
			String direction = sortField.getDirection().getDirection();

			if (direction.equalsIgnoreCase("asc")) {
				if(field.equals("fechaCreacionString")){
					queryBuilder.append(" ORDER BY fec_creacion ASC");
				} else if (field.equals("proyecto.nombre")){
					queryBuilder.append(" ORDER BY pr.nombre ASC");
				}
				
				else
				{
					queryBuilder.append(" ORDER BY "+field+" ASC");
				}
				
			} else if (direction.equalsIgnoreCase("desc")) {
				
				if(field.equals("fechaCreacionString")){
					queryBuilder.append(" ORDER BY fec_creacion DESC");
				
				}else if (field.equals("proyecto.nombre")){
					queryBuilder.append(" ORDER BY pr.nombre DESC");
				}
				
				else{
					queryBuilder.append(" ORDER BY "+field+" DESC");
				}
			}
		}
		
		queryString = queryBuilder.toString();
		Query query = createSQLQuery(queryString);
		query.setParameter(P_ID_PROYECTO, idProyecto, StandardBasicTypes.INTEGER);
		query.setTimestamp(P_FECHA_FROM, fechaInicial);
		query.setTimestamp(P_FECHA_TO, fechaFinal);
		Integer countTotalRegistros = findByOtherQuery(query).size();
		
		query = createSQLQuery(queryString, new Paginator(displayStart, displaySize));
		query.setParameter(P_ID_PROYECTO, idProyecto, StandardBasicTypes.INTEGER);	
		query.setTimestamp(P_FECHA_FROM, fechaInicial);
		query.setTimestamp(P_FECHA_TO, fechaFinal);
		
		query.setResultTransformer(Transformers.aliasToBean(MapaPruebasDTO.class));
		List<MapaPruebasDTO> resultList = findByOtherQuery(query);
		
		if(!resultList.isEmpty()) {
			namedQuery = createNamedQuery(NQ_SUMATORIA_REPORTE_EJECUCION_AVANCE_MAPAS);	
			namedQuery.setParameter(P_ID_PROYECTO, idProyecto, StandardBasicTypes.INTEGER);
			namedQuery.setTimestamp(P_FECHA_FROM, fechaInicial);
			namedQuery.setTimestamp(P_FECHA_TO, fechaFinal);
			namedQuery.setResultTransformer(Transformers.aliasToBean(MapaPruebasDTO.class));
			MapaPruebasDTO sumatoria = (MapaPruebasDTO) namedQuery.uniqueResult();
			
			DecimalFormat df = new DecimalFormat("0.00");
			String porcentaje = "";
			String strPorcentajeICF = "";
			Double porcentajeICF = 0d;
			Double sumaICF = 0d;
			for(MapaPruebasDTO mapa : resultList) {
				mapa.setTotalPruebasSinEjecutar(mapa.getCalculatedPruebasSinEjecutar());
				
				porcentaje = df.format(mapa.getCalculatedPorcentajeAvance(true));
				mapa.setPorcentajeAvance(porcentaje+"%");
				
				porcentajeICF = mapa.getCalculatedIndicadorCalidadICF(true);
				sumaICF += porcentajeICF;
				strPorcentajeICF = df.format(porcentajeICF);
				mapa.setPorcentajeIndicador(strPorcentajeICF+"%");
			}
			
			int last = resultList.size() - 1;
			MapaPruebasDTO lastDTO = resultList.get(last);
			lastDTO.setSumatoriaPruebasConstruidas(sumatoria.getSumatoriaPruebasConstruidas());
			lastDTO.setSumatoriaPruebasEjecutadas(sumatoria.getSumatoriaPruebasEjecutadas());
			lastDTO.setSumatoriaPruebasSatisfactorias(sumatoria.getSumatoriaPruebasSatisfactorias());
			lastDTO.setSumatoriaPruebasInsatisfactorias(sumatoria.getSumatoriaPruebasInsatisfactorias());
			lastDTO.setSumatoriaPruebasAnuladas(sumatoria.getSumatoriaPruebasAnuladas());
			lastDTO.setSumatoriaPruebasSinEjecutar(calcularSumatoriaPruebasSinEjecutar(sumatoria));
			
			porcentaje = df.format(calcularSumatoriaPorcentajeAvance(sumatoria,true));
			lastDTO.setSumatoriaPorcentajeAvance(porcentaje+"%");
			
			Double sumatoriaICF = sumaICF / resultList.size();
			porcentaje = df.format(sumatoriaICF);
			lastDTO.setSumatoriaPorcentajeIndicador(porcentaje+"%");
		}
		
		StringBuffer bufferTotalHallazgos = new StringBuffer();
		bufferTotalHallazgos.append("SELECT COUNT(hallazgo.id) as totalHallazgos ");
		bufferTotalHallazgos.append("FROM "+SgpHallazgo.class.getName()+" hallazgo, "
				+ SgpCasoPrueba.class.getName()+" cp, "+SgpMapaPrueba.class.getName()+" mp ");
		bufferTotalHallazgos.append("WHERE hallazgo.casoPrueba.id = cp.id ");
		bufferTotalHallazgos.append("AND cp.sgpMapaPrueba.id = mp.id AND mp.id =:mapa_id");
		if(null!=fechaFinal){
			bufferTotalHallazgos.append(" AND hallazgo.fecCreacion <= :fecha");

		}
	
		
		for(MapaPruebasDTO mapaPruebaDTO: resultList){
			query = (Query) createQuery(bufferTotalHallazgos.toString());
			query.setParameter("mapa_id", mapaPruebaDTO.getId());
			
			if(null!=fechaFinal){

				query.setParameter("fecha", fechaFinal);
			}
			
			Integer totalHallazgos = Integer.parseInt(query.uniqueResult().toString());
			mapaPruebaDTO.setTotalHallazgos(totalHallazgos);
		}
		

		
		return new ResultSet<MapaPruebasDTO>(resultList, countTotalRegistros.longValue(),
				resultList.size());
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 11/04/2014
	 * @description 
	 * @param idMapaPrueba
	 * @return
	 * @throws AppBaseException
	 */
	@Override
	public List<ReporteMapaPruebaDTO> consultarCasosDePrueba(Integer idMapaPrueba) throws AppBaseException {
		
		Query query = createNamedQuery("consultarCasosDePrueba");
		query.setInteger("idMapaPrueba", idMapaPrueba);
		query.setResultTransformer(Transformers.aliasToBean(ReporteMapaPruebaDTO.class));
		List<ReporteMapaPruebaDTO> resultList = findByOtherQuery(query);
		
		return resultList;
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 21/04/2014
	 * @description 
	 * @param idMapaPrueba
	 * @return
	 * @throws AppBaseException
	 */
	public List<ReporteMapaPruebaDTO> consultarCasosPruebaHallazgos(Integer idMapaPrueba) throws AppBaseException {
		Query query = createNamedQuery(NQ_REPORTE_MAPA_PRUEBAS_FILTROS);
		query.setInteger(P_ID_MAPA_PRUEBA, idMapaPrueba);
		query.setParameter(P_ID_PROYECTO, null, StandardBasicTypes.INTEGER);
		query.setDate(P_FECHA_FROM,null);
		query.setDate(P_FECHA_TO, null);
		query.setResultTransformer(Transformers.aliasToBean(ReporteMapaPruebaDTO.class));
		List<ReporteMapaPruebaDTO> resultList = findByOtherQuery(query);
		return resultList;
	}

	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 30/04/2014
	 * @description 
	 * @param filtros
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException
	 */
	public List<ReporteMapaPruebaDTO> consultarMapaPruebasReporte(Map<String,Object> filtros) 
			throws AppBaseException, ParseException {
		
		Query query = createNamedQuery(NQ_REPORTE_MAPA_PRUEBAS_FILTROS);
		query.setParameter(P_ID_MAPA_PRUEBA, null, StandardBasicTypes.INTEGER);
		query.setInteger(P_ID_PROYECTO, (Integer)filtros.get("idProyecto"));
		
		
		Date fechaInicial = null;
		Date fechaFinal = null;
		
		Object fechaFrom = filtros.get("fechaFrom");
		if(fechaFrom != null && !fechaFrom.toString().isEmpty()) {
			fechaInicial = getDateFromString(fechaFrom.toString(), true);
		}
		
		Object fechaTo = filtros.get("fechaTo");
		if(fechaTo != null && !fechaTo.toString().isEmpty()) {
			fechaFinal = getDateFromString(fechaTo.toString(), false);
		}
		
		query.setTimestamp(P_FECHA_FROM, fechaInicial);
		query.setTimestamp(P_FECHA_TO, fechaFinal);
		query.setResultTransformer(Transformers.aliasToBean(ReporteMapaPruebaDTO.class));
		List<ReporteMapaPruebaDTO> resultList = findByOtherQuery(query);
		return resultList;
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

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws ParseException 
	 * @since 14/04/2014
	 * @see com.premize.sgp.dao.gestion.pruebas.MapaPruebasDao#getTotalesMapaPruebaProyecto(java.lang.Integer)
	 */
	@Override
	public MapaPruebasDTO getTotalesMapaPruebaProyecto(Integer idProyecto, String fechaFrom, String fechaTo, String tipoHallazgo)
			throws AppBaseException, ParseException {
		
		MapaPruebasDTO mapaPrueba = new MapaPruebasDTO();
		StringBuffer buffer = new StringBuffer();
		Date fechaIn = null;
		Date fechaFin = null;
		
		if(validarCampo(fechaFrom)){
			fechaIn = getDateFromString(fechaFrom, true);
		}
		if(validarCampo(fechaTo)){
			fechaFin = getDateFromString(fechaTo, false);
		}
		buffer.append("SELECT COUNT(casoprueba.id) as totalPruebasConstruidas ");
		buffer.append("FROM "+SgpCasoPrueba.class.getName()+" casoprueba ");
		buffer.append("WHERE casoprueba.sgpMapaPrueba.sgpProyecto.id = :proyecto");
		
		if(validarCampo(fechaFrom))
			buffer.append(" AND casoprueba.fecCreacion >= :fechaFrom"); 

		if(validarCampo(fechaTo))
			buffer.append(" AND casoprueba.fecCreacion <= :fechaTo"); 

		Query query = createQuery(buffer.toString());
		query.setParameter("proyecto", idProyecto);

		if(validarCampo(fechaFrom))
			query.setTimestamp("fechaFrom", fechaIn);
		if(validarCampo(fechaTo))
			query.setTimestamp("fechaTo", fechaFin);
		
		Integer construidas = Integer.valueOf(query.list().get(0).toString());
		mapaPrueba.setTotalPruebasConstruidas(construidas);
		
		buffer = new StringBuffer();
		buffer.append("SELECT COUNT(casoprueba.id) as totalPruebasEjecutadas ");
		buffer.append("FROM "+SgpCasoPrueba.class.getName()+" casoprueba ");
		buffer.append("WHERE casoprueba.sgpMapaPrueba.sgpProyecto.id = :proyecto ");
		buffer.append(" AND casoprueba.cumple IN ('Si','No')");
		if(validarCampo(fechaFrom))
			buffer.append(" AND casoprueba.fecEjecuta >= :fechaFrom"); 

		if(validarCampo(fechaTo))
			buffer.append(" AND casoprueba.fecEjecuta <= :fechaTo"); 

		query = createQuery(buffer.toString());
		query.setParameter("proyecto", idProyecto);

		if(validarCampo(fechaFrom))
			query.setTimestamp("fechaFrom", fechaIn);
		if(validarCampo(fechaTo))
			query.setTimestamp("fechaTo", fechaFin);
		Integer ejecutadas = Integer.valueOf(query.list().get(0).toString());
		mapaPrueba.setTotalPruebasEjecutadas(ejecutadas);
		
		buffer = new StringBuffer();
		buffer.append("SELECT COUNT(casoprueba.id) as totalPruebasAnuladas ");
		buffer.append("FROM "+SgpCasoPrueba.class.getName()+" casoprueba ");
		buffer.append("WHERE casoprueba.sgpMapaPrueba.sgpProyecto.id = :proyecto AND casoprueba.cumple = 'NA'");
		if(validarCampo(fechaFrom))
			buffer.append(" AND casoprueba.fecEjecuta >= :fechaFrom"); 

		if(validarCampo(fechaTo))
			buffer.append(" AND casoprueba.fecEjecuta <= :fechaTo"); 

		query = createQuery(buffer.toString());
		query.setParameter("proyecto", idProyecto);

		if(validarCampo(fechaFrom))
			query.setTimestamp("fechaFrom", fechaIn);
		if(validarCampo(fechaTo))
			query.setTimestamp("fechaTo", fechaFin);
		Integer anuladas = Integer.valueOf(query.list().get(0).toString());
		mapaPrueba.setTotalPruebasAnuladas(anuladas);
		return mapaPrueba;
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
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 24/04/2014
	 * @description 
	 * @param parametros
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException
	 */
	public List<MapaPruebasDTO> consultarAvanceEjecucionMapas(Map<String,Object> parametros) 
			throws AppBaseException, ParseException {
		Query query = createNamedQuery(NQ_REPORTE_EJECUCION_AVANCE_MAPAS_FECHA_CORTE);
		query.setInteger(P_ID_PROYECTO, (Integer)parametros.get(P_ID_PROYECTO));

		Date fechaInicial = null;
		Date fechaFinal = null;
		
		Object fechaFrom = parametros.get("fechaFrom");
		if(fechaFrom != null && !fechaFrom.toString().isEmpty()) {
			fechaInicial = getDateFromString(fechaFrom.toString(), true);
		}
		
		Object fechaTo = parametros.get("fechaTo");
		if(fechaTo != null && !fechaTo.toString().isEmpty()) {
			fechaFinal = getDateFromString(fechaTo.toString(), false);
		}
		
		query.setTimestamp(P_FECHA_FROM, fechaInicial);
		query.setTimestamp(P_FECHA_TO, fechaFinal);
		query.setResultTransformer(Transformers.aliasToBean(MapaPruebasDTO.class));
		List<MapaPruebasDTO> resultList = findByOtherQuery(query);
		return resultList;
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
	@Override
	public IndReprocesoConstruccionPruebasDTO consultarIndicadorReprocesoConstruccionPruebas(Integer idProyecto,
			Date fechaFrom, Date fechaTo) throws AppBaseException {
		Query query = createNamedQuery(NQ_CONSULTA_INDI_REPROCESO_CONSTRUCCION_PRUEBAS);
		query.setInteger("idProyecto", idProyecto);
		query.setTimestamp(P_FECHA_FROM, fechaFrom);
		query.setTimestamp(P_FECHA_TO, fechaTo);
		query.setResultTransformer(Transformers.aliasToBean(IndReprocesoConstruccionPruebasDTO.class));
		IndReprocesoConstruccionPruebasDTO result = (IndReprocesoConstruccionPruebasDTO) query.uniqueResult();
		return result;
	}
	
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 1/07/2014
	 * @description 
	 * @param mapa
	 * @return
	 */
	private Integer calcularSumatoriaPruebasSinEjecutar(MapaPruebasDTO mapa) {
		Integer result = 0;
		Integer sPruebasConstruidas = mapa.getSumatoriaPruebasConstruidas(),
				sPruebasEjecutadas = mapa.getSumatoriaPruebasEjecutadas(),
				sPruebasAnuladas = mapa.getSumatoriaPruebasAnuladas();
		if(sPruebasConstruidas != null && sPruebasEjecutadas != null && sPruebasAnuladas != null) {
			result = sPruebasConstruidas - (sPruebasEjecutadas + sPruebasAnuladas);
		}
		return result;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 1/07/2014
	 * @description Si el parametro porcentaje = true entonces devuelve el calculo como una representacion
	 *              porcentual(%) de lo contrario devuelve la probabilidad.
	 * @param mapa
	 * @param porcentaje
	 * @return
	 */
	private Double calcularSumatoriaPorcentajeAvance(MapaPruebasDTO mapa, Boolean porcentaje) {
		Integer sPruebasConstruidas = mapa.getSumatoriaPruebasConstruidas(),
				sPruebasEjecutadas = mapa.getSumatoriaPruebasEjecutadas(),
				sPruebasAnuladas = mapa.getSumatoriaPruebasAnuladas();
		if(sPruebasConstruidas != null && sPruebasAnuladas != null) {
			Integer divisor = sPruebasConstruidas - sPruebasAnuladas;
			if(divisor != 0) {
				Double val = sPruebasEjecutadas.doubleValue() / divisor;
				Double resultado = val.doubleValue();
				return porcentaje ? resultado * 100 : resultado;
			}
		}
		return 0d;
	}
}