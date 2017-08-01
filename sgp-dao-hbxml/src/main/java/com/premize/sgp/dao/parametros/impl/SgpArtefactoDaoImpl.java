/**
 * 
 */
package com.premize.sgp.dao.parametros.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.loader.criteria.CriteriaLoader;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.dao.impl.HibernateDaoImpl;
import com.premize.sgp.dao.parametros.ArtefactoDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.Search;
import com.premize.sgp.dto.SortField;
import com.premize.sgp.dto.parametros.ArtefactoDTO;
import com.premize.sgp.modelo.entities.parametros.SgpArtefacto;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class SgpArtefactoDaoImpl
 * @since 22/01/2014
 */
@Repository
public class SgpArtefactoDaoImpl extends HibernateDaoImpl<SgpArtefacto, Integer> implements ArtefactoDao {
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 28/01/2014
	 * @see com.premize.sgp.dao.parametros.ArtefactoDao#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<ArtefactoDTO> getRecords(PagingCriteria criteria) throws Exception {
		
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		String searchLike = criteria.getSearch();
		List<Search> searchs = criteria.getSearchFields();
		List<SortField> sortFields = criteria.getSortFields();
		
		DetachedCriteria cq = DetachedCriteria.forClass(SgpArtefacto.class).createAlias("proyecto", "proyecto")
				.createAlias("sgpTipoArtefacto", "tipoArtefacto").createAlias("usuario", "usuario",Criteria.LEFT_JOIN);
		
		// Filtering and Searching
		if ((searchLike != null) && (!(searchLike.isEmpty()))) {
			
			Disjunction disyuctionConsulta = Restrictions.disjunction();
			disyuctionConsulta.add(Restrictions.like("nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("proyecto.nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("tipoArtefacto.nombre", searchLike, MatchMode.ANYWHERE)
					.ignoreCase());
			
			
			cq.add(Restrictions.or(disyuctionConsulta, Restrictions.like("usuario.nombre", searchLike, MatchMode.ANYWHERE)
					.ignoreCase()));
		}
		
		for (Search search : searchs) {
			String field = search.getField();
			String value = search.getValue();
			
			if (field.equals("id") || field.equals("indActivo")) {
				if (value != null)
					cq.add(Restrictions.eq(field, Integer.parseInt(value)));
			} else {
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
		
		List<SgpArtefacto> listaReturn = findByCriteria(cq, new Paginator(displayStart, displaySize));
		
		List<ArtefactoDTO> resultList = new ArrayList<ArtefactoDTO>();
		for (SgpArtefacto sgpArtefacto : listaReturn) {
			ArtefactoDTO artefactoDTO = new ArtefactoDTO(sgpArtefacto);
			resultList.add(artefactoDTO);
		}
		
		return new ResultSet<ArtefactoDTO>(resultList, countTotalRegistros.longValue(), listaReturn.size());
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 8/02/2014
	 * @see com.premize.sgp.dao.parametros.ArtefactoDao#obtenerArtefactosPorMapaPrueba(Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ArtefactoDTO> obtenerArtefactosPorMapaPrueba(Integer mapaPrueba) throws Exception {
		
		StringBuilder builder = new StringBuilder();
		builder.append("Select art.nombre as nombre ,art.descripcion as descripcion,art.id as id From com.premize.sgp.modelo.entities.artefacto.SgpMapaArtefacto mapa, com.premize.sgp.modelo.entities.parametros.SgpArtefacto art");
		builder.append(" WHERE mapa.sgpMapaPrueba.id = :idmapa ");
		builder.append("AND mapa.sgpArtefacto.id = art.id  ");
		builder.append(" AND mapa.indActivo = 1 ");
		
		Query query = createQuery(builder.toString());
		query.setParameter("idmapa", mapaPrueba);
		query.setResultTransformer(Transformers.aliasToBean(ArtefactoDTO.class));
		
		return findByOtherQuery(query);
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 11/02/2014
	 * @see com.premize.sgp.dao.parametros.ArtefactoDao#artefactosSinAsociarAMapa(java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ArtefactoDTO> artefactosSinAsociarAMapa(Integer idProyecto, Integer idMapaPrueba) throws Exception {
		
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT art.nombre as nombre, art.descripcion as descripcion,art.id as id FROM com.premize.sgp.modelo.entities.parametros.SgpArtefacto art ");
		builder.append(" WHERE art.proyecto.id = :proyecto");
		builder.append(" AND art.indActivo = 1");
		
		if (idMapaPrueba != null) {
			
			builder.append(" AND NOT EXISTS(select id FROM com.premize.sgp.modelo.entities.artefacto.SgpMapaArtefacto map  WHERE map.sgpArtefacto.id = art.id ");
			builder.append(" AND map.sgpMapaPrueba.id = :mapa ");
			builder.append(" AND map.indActivo = 1)");
		}
		builder.append(" order by art.id ASC");
		Query query = createQuery(builder.toString());
		query.setParameter("proyecto", idProyecto);
		
		if (idMapaPrueba != null) {
			query.setParameter("mapa", idMapaPrueba);
		}
		query.setResultTransformer(Transformers.aliasToBean(ArtefactoDTO.class));
		
		return findByOtherQuery(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.dao.parametros.ArtefactoDao#obtenerArtefactosPorProyecto(java.lang.Integer)
	 */
	@Override
	public List<SgpArtefacto> obtenerArtefactosPorProyecto(Integer idProyecto)
			throws AppBaseException {
		
		DetachedCriteria criteria = DetachedCriteria
				.forClass(SgpArtefacto.class);
		criteria.add(Restrictions.eq("proyecto.id", idProyecto));
		criteria.add(Restrictions.eq("indActivo", 1));
	
		criteria.addOrder(Order.asc("id"));
		return findByCriteria(criteria);
	}
	
}
