package com.premize.sgp.dao.gestion.pruebas.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.dao.impl.HibernateDaoImpl;
import com.premize.sgp.dao.gestion.pruebas.TipoPrioridadDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.Search;
import com.premize.sgp.dto.SortField;
import com.premize.sgp.dto.pruebas.TipoPrioridadDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoHallazgo;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoPrioridad;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoSeveridad;
import com.premize.sgp.utils.NumericUtils;

/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
* @project sgp-dao-hbxml
* @class SgpTipoHallazgoDaoImpl
* @date 13/06/2014
*
 */
@Repository
public class SgpTipoPrioridadDaoImpl extends HibernateDaoImpl<SgpTipoPrioridad, Integer> implements TipoPrioridadDao {
	private static final String TIPOSEVERIDAD_ID = "tipoSeveridad.id";

	private static final String TIPOSEVERIDAD = "tipoSeveridad";

	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.dao.gestion.pruebas.TipoHallazgoDao#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<TipoPrioridadDTO> getRecords(PagingCriteria criteria)
			throws AppBaseException {

		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		String searchLike = criteria.getSearch();
		List<Search> searchs = criteria.getSearchFields();
		List<SortField> sortFields = criteria.getSortFields();
		
		DetachedCriteria cq = DetachedCriteria.forClass(SgpTipoPrioridad.class).createAlias("tipoSeveridad", "tipoSeveridad")
				.createAlias("tipoSeveridad.tipoHallazgo", "tipoHallazgo");
//		 .createAlias("students").createCriteria("books").setProjection(Projections.property("name"))
		if ((searchLike != null) && (!(searchLike.isEmpty()))) {
			Disjunction disyuctionConsulta = Restrictions.disjunction();
			disyuctionConsulta.add(Restrictions.like("nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("tipoHallazgo.nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("tipoSeveridad.nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
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
		
		List<SgpTipoPrioridad> listaReturn = findByCriteria(cq, new Paginator(displayStart, displaySize));
		
		List<TipoPrioridadDTO> resultList = new ArrayList<TipoPrioridadDTO>();
		for (SgpTipoPrioridad sgpTipoPrioridad : listaReturn) {
			TipoPrioridadDTO tipoPrioridadDTO = new TipoPrioridadDTO(sgpTipoPrioridad);
			resultList.add(tipoPrioridadDTO);
			
		}
		return new ResultSet<TipoPrioridadDTO>(resultList, countTotalRegistros.longValue(), listaReturn.size());
	}
/*
 * (non-Javadoc)
 * @see com.premize.sgp.dao.gestion.pruebas.TipoHallazgoDao#validarTipoHallazgo(com.premize.sgp.dto.pruebas.TipoHallazgoDTO)
 */
	@Override
	public boolean validarTipoPrioridad(TipoPrioridadDTO tipoPrioridadDTO)
			throws AppBaseException {
		Boolean result = false;

		DetachedCriteria criteria = DetachedCriteria.forClass(SgpTipoPrioridad.class);
		criteria.add(Restrictions.eq("nombre", tipoPrioridadDTO.getNombre().trim()).ignoreCase());
		criteria.add(Restrictions.or(Restrictions.eq(TIPOSEVERIDAD_ID, tipoPrioridadDTO.getTipoSeveridad().getId()), 
				 Restrictions.isNull(TIPOSEVERIDAD)));
		criteria.add(Restrictions.eq("indActivo", 1));
		if(tipoPrioridadDTO.getId() != null){
			criteria.add(Restrictions.ne("id", tipoPrioridadDTO.getId()));
		}
		
		if (!findByCriteria(criteria).isEmpty()) {
			result = true;
		}
		
	return result;
}
	
	@Override
	public List<SgpTipoPrioridad> consultarTipoPrioridad()
			throws AppBaseException {
		List<SgpTipoPrioridad> result = null;

		DetachedCriteria criteria = DetachedCriteria.forClass(SgpTipoPrioridad.class);
		criteria.add(Restrictions.eq("indActivo", 1));
		criteria.addOrder(Order.asc("nombre"));
		if(!findByCriteria(criteria).isEmpty()){
		result = findByCriteria(criteria);
		}

	return result;
	}
	@Override
	public List<TipoPrioridadDTO> consultaPrioridadPorTipoHallazgo(
			Integer idTipoSeveridad, Paginator page) throws AppBaseException {
		
		List<SgpTipoPrioridad> causaSeverids = this.findByCriteria(listPrioridadPorTipoSeveridad(idTipoSeveridad), page);
		
		List<TipoPrioridadDTO> causaPrioridadDTOs = new ArrayList<TipoPrioridadDTO>();
		for (SgpTipoPrioridad tipoPrioridad : causaSeverids) {
			TipoPrioridadDTO tipoPrioridadDTO = new TipoPrioridadDTO(tipoPrioridad);
			causaPrioridadDTOs.add(tipoPrioridadDTO);
		}
		return causaPrioridadDTOs;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014
	 * @description 
	 * @param idTipoHallazgo
	 * @return
	 * @throws AppBaseException
	 */
	@Override
	public List<TipoPrioridadDTO> consultarPrioridadPorTipoHallazgo(Integer idTipoHallazgo) throws AppBaseException {
		StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT prioridad ");
		queryString.append("FROM "+SgpTipoPrioridad.class.getName() + " As prioridad,");
		queryString.append(SgpTipoSeveridad.class.getName() + " As severidad, ");
		queryString.append(SgpTipoHallazgo.class.getName() + " As thallazgo ");
		queryString.append("WHERE thallazgo.id = :idTipoHallazgo AND thallazgo = severidad.tipoHallazgo ");
		queryString.append("AND severidad = prioridad.tipoSeveridad ");
		
		Query query = (Query) createQuery(queryString.toString());
		query.setInteger("idTipoHallazgo", idTipoHallazgo);
//		query.setResultTransformer(Transformers.aliasToBean(TipoPrioridadDTO.class));
		
		List<SgpTipoPrioridad> consulta = findByQuery(query);
		List<TipoPrioridadDTO> result = new ArrayList<TipoPrioridadDTO>();
		TipoPrioridadDTO dto;
		for(SgpTipoPrioridad entity : consulta) {
			dto = new TipoPrioridadDTO(entity);
			result.add(dto);
		}
		
		return result;
	}
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 18/06/2014
	* @param idTipoHallazgo
	* @return
	 */
		private DetachedCriteria listPrioridadPorTipoSeveridad(Integer idTipoSeveridad) {
			DetachedCriteria consulta = DetachedCriteria.forClass(SgpTipoPrioridad.class);
			consulta.add(Restrictions.or(Restrictions.eq(TIPOSEVERIDAD_ID, idTipoSeveridad), Restrictions.isNull(TIPOSEVERIDAD)));
			consulta.add(Restrictions.eq("indActivo", 1));
			return consulta;
		}
	
	
}
