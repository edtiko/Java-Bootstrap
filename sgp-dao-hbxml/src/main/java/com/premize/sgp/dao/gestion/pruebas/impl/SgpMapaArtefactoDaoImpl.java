package com.premize.sgp.dao.gestion.pruebas.impl;

import com.premize.sgp.dto.ResultSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.dao.impl.HibernateDaoImpl;
import com.premize.sgp.dao.gestion.pruebas.MapaArtefactoDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.Search;
import com.premize.sgp.dto.SortField;
import com.premize.sgp.dto.parametros.MapaArtefactoDTO;
import com.premize.sgp.modelo.entities.artefacto.SgpMapaArtefacto;
import com.premize.sgp.utils.NumericUtils;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class SgpMapaArtefactoDaoImpl
 * @since 27/03/2014
 *
 */
@Repository
public class SgpMapaArtefactoDaoImpl extends HibernateDaoImpl<SgpMapaArtefacto, Integer> implements MapaArtefactoDao {
	
	private static final Integer INACTIVO = 0;
	private static final Integer ACTIVO = 1;
	
	/**
	 * Ruta para acceder a la propiedad de un parÃ¡metro.
	 * 
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo a soto</a>
	 * @date 04/02/2014
	 */
	
	private static final String ID_MAPA_PRUEBA = "sgpMapaPrueba.id";
	
	/**
	 * Ruta para acceder a la propiedad de un parÃ¡metro.
	 * 
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo a soto</a>
	 * @date 04/02/2014
	 */
	private static final String MAPA_PRUEBA = "sgpMapaPrueba";
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.dao.gestion.pruebas.MapaArtefactoDao#consultarMapaPruebasPorProyecto(java.lang.Integer, com.premize.pmz.api.dto.Paginator)
	 */
	@Override
	public List<MapaArtefactoDTO> consultarMapaPruebasPorProyecto(Integer idMapaPruebas, Paginator page)
			throws AppBaseException {
		
		List<SgpMapaArtefacto> sgpMapaArtefactos = this.findByCriteria(listMapaPruebaPorProyecto(idMapaPruebas), page);
		
		List<MapaArtefactoDTO> mapaArtefactoDTOs = new ArrayList<MapaArtefactoDTO>();
		
		for (SgpMapaArtefacto sgpMapaArtefacto : sgpMapaArtefactos) {
			MapaArtefactoDTO mapaArtefactoDTO = new MapaArtefactoDTO(sgpMapaArtefacto);
			mapaArtefactoDTOs.add(mapaArtefactoDTO);
		}
		return mapaArtefactoDTOs;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param idMapaPruebas
	 * @return
	 */
	private DetachedCriteria listMapaPruebaPorProyecto(Integer idMapaPruebas) {
		DetachedCriteria consulta = DetachedCriteria.forClass(SgpMapaArtefacto.class);
		consulta.add(Restrictions.or(Restrictions.eq(ID_MAPA_PRUEBA, idMapaPruebas), Restrictions.isNull(MAPA_PRUEBA)));
		return consulta;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param idMapaPruebas
	 * @return
	 */
	private DetachedCriteria listMapaArtefactosActivos(Integer idMapaPruebas) {
		DetachedCriteria consulta = DetachedCriteria.forClass(SgpMapaArtefacto.class).createAlias("sgpArtefacto",
				"artefacto");
		consulta.add(Restrictions.or(Restrictions.eq(ID_MAPA_PRUEBA, idMapaPruebas), Restrictions.isNull(MAPA_PRUEBA)));
		consulta.add(Restrictions.eq("artefacto.indActivo", ACTIVO));
		consulta.add(Restrictions.eq("indActivo", ACTIVO));
		return consulta;
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 12/02/2014
	 * @see com.premize.sgp.dao.gestion.pruebas.MapaArtefactoDao#desasociarArtefactosMapa(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void desasociarArtefactosMapa(Integer idMapa, String login) throws AppBaseException {
		DetachedCriteria consulta = DetachedCriteria.forClass(SgpMapaArtefacto.class);
		consulta.add(Restrictions.eq(ID_MAPA_PRUEBA, idMapa));
		List<SgpMapaArtefacto> list = findByOtherCriteria(consulta);
		
		for (SgpMapaArtefacto mapaArtefacto : list) {
			mapaArtefacto.setIndActivo(INACTIVO);
			mapaArtefacto.setUsuarioEdita(login);
			mapaArtefacto.setFecEdita(Calendar.getInstance().getTime());
			saveOrUpdate(mapaArtefacto);
		}
		
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 12/02/2014
	 * @see com.premize.sgp.dao.gestion.pruebas.MapaArtefactoDao#asociarArtefactosPorMapa(java.util.List)
	 */
	@Override
	public void asociarArtefactosPorMapa(List<SgpMapaArtefacto> artefactos) throws AppBaseException {
		
		for (SgpMapaArtefacto mapaArtefacto : artefactos) {
			save(mapaArtefacto);
		}
	}
	
	/**
	 * @author <a href="mailto:johnh.bernal@premize.com">John Hawer Bernal</a>
	 * @since 11/02/2014
	 * @see com.premize.sgp.dao.parametros.ArtefactoDao#artefactosSinAsociarAMapa(java.lang.Integer, java.lang.Integer)
	 */
	/**
	 * @throws AppBaseException
	 */
	
	@Override
	public ResultSet<MapaArtefactoDTO> getMapaArtefactos(PagingCriteria criteria) throws AppBaseException {
		
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		String searchLike = criteria.getSearch();
		List<Search> searchs = criteria.getSearchFields();
		List<SortField> sortFields = criteria.getSortFields();
		
		DetachedCriteria cq = DetachedCriteria.forClass(SgpMapaArtefacto.class)
				.createAlias("sgpArtefacto", "artefacto").createAlias("artefacto.sgpTipoArtefacto", "tipoArtefacto")
				.createAlias("sgpMapaPrueba", "mapaPrueba");
		
		// Filtering and Searching
		if ((searchLike != null) && (!(searchLike.isEmpty()))) {
			
			Disjunction disyuctionConsulta = Restrictions.disjunction();
			disyuctionConsulta.add(Restrictions.like("artefacto.nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("mapaPrueba.nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("tipoArtefacto.nombre", searchLike, MatchMode.ANYWHERE)
					.ignoreCase());
			cq.add(disyuctionConsulta);
		}
		
		for (Search search : searchs) {
			String field = search.getField();
			String value = search.getValue();
			
			if (field.equals("indActivo")) {
				if (value != null)
					cq.add(Restrictions.eq(field, Integer.parseInt(value)));
			}else if (field.equals("id")){
				if (value != null){
					if(!NumericUtils.isNumeric(value)){
						value="0";
					}
					cq.add(Restrictions.eq(field, Integer.parseInt(value)));
				}
			}else {
				if (value != null)
					cq.add(Restrictions.like(field, value, MatchMode.ANYWHERE).ignoreCase());
			}
		}
		Integer countTotalRegistros = findByCriteria(cq).size();
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
		
		List<SgpMapaArtefacto> listaReturn = findByCriteria(cq, new Paginator(displayStart, displaySize));
		
		List<MapaArtefactoDTO> resultList = new ArrayList<MapaArtefactoDTO>();
		for (SgpMapaArtefacto sgpMapaArtefacto : listaReturn) {
			MapaArtefactoDTO mapaArtefactoDTO = new MapaArtefactoDTO(sgpMapaArtefacto);
			resultList.add(mapaArtefactoDTO);
		}
		
		return new ResultSet<MapaArtefactoDTO>(resultList, countTotalRegistros.longValue(), listaReturn.size());
	}
	
	/**
	 * Retorna una lista de artefactos activos por id de MapaPruebas
	 * 
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 10/03/2014
	 * @see com.premize.sgp.dao.gestion.pruebas.MapaArtefactoDao#getArtefactosActivosMapaDePruebas(java.lang.Integer,
	 *      com.premize.pmz.api.dto.Paginator)
	 */
	@Override
	public List<MapaArtefactoDTO> getArtefactosActivosMapaDePruebas(Integer idMapaPruebas, Paginator page)
			throws AppBaseException {
		
		List<SgpMapaArtefacto> sgpMapaArtefactos = this.findByCriteria(listMapaArtefactosActivos(idMapaPruebas), page);
		
		List<MapaArtefactoDTO> mapaArtefactoDTOs = new ArrayList<MapaArtefactoDTO>();
		
		for (SgpMapaArtefacto sgpMapaArtefacto : sgpMapaArtefactos) {
			MapaArtefactoDTO mapaArtefactoDTO = new MapaArtefactoDTO(sgpMapaArtefacto);
			mapaArtefactoDTOs.add(mapaArtefactoDTO);
		}
		return mapaArtefactoDTOs;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 13/03/2014
	 * @see com.premize.sgp.dao.gestion.pruebas.MapaArtefactoDao#getMapaArtefactoByArtefacto(java.lang.Integer)
	 */
	@Override
	public List<SgpMapaArtefacto> getMapaArtefactoByArtefacto(
			Integer idArtefacto) throws AppBaseException {
		
		List<SgpMapaArtefacto> list = new ArrayList<SgpMapaArtefacto>();
	
		DetachedCriteria consulta = DetachedCriteria
				.forClass(SgpMapaArtefacto.class);
		consulta.add(Restrictions.eq("sgpArtefacto.id", idArtefacto));
		
		if(!findByCriteria(consulta).isEmpty()){
		list = findByCriteria(consulta);
		}
	
		
		return list;
	}

}