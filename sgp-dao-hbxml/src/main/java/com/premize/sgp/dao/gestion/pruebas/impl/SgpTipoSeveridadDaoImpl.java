package com.premize.sgp.dao.gestion.pruebas.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.dao.impl.HibernateDaoImpl;
import com.premize.sgp.dao.gestion.pruebas.TipoSeveridadDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.Search;
import com.premize.sgp.dto.SortField;
import com.premize.sgp.dto.pruebas.TipoSeveridadDTO;
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
public class SgpTipoSeveridadDaoImpl extends HibernateDaoImpl<SgpTipoSeveridad, Integer> implements TipoSeveridadDao {
	
	
	private static final String TIPOHALLAZGO_ID = "tipoHallazgo.id";

	private static final String TIPOHALLAZGO = "tipoHallazgo";
	
	
	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.dao.gestion.pruebas.TipoHallazgoDao#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<TipoSeveridadDTO> getRecords(PagingCriteria criteria)
			throws AppBaseException {

		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		String searchLike = criteria.getSearch();
		List<Search> searchs = criteria.getSearchFields();
		List<SortField> sortFields = criteria.getSortFields();
		
		DetachedCriteria cq = DetachedCriteria.forClass(SgpTipoSeveridad.class)
				.createAlias("tipoHallazgo", "tipoHallazgo");
	
		
		if ((searchLike != null) && (!(searchLike.isEmpty()))) {
			Disjunction disyuctionConsulta = Restrictions.disjunction();
			disyuctionConsulta.add(Restrictions.like("nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("tipoHallazgo.nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("color", searchLike, MatchMode.ANYWHERE).ignoreCase());
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
		 if(field.equals("tipoSeveridad.tipoHallazgo.nombre")){
			 field="tipoHallazgo.nombre";
		 }
			if (direction.equalsIgnoreCase("asc")) {
				cq.addOrder(Order.asc(field));
			} else if (direction.equalsIgnoreCase("desc")) {
				cq.addOrder(Order.desc(field));
				
			}
			
		}


		List<SgpTipoSeveridad> listaReturn = findByCriteria(cq, new Paginator(displayStart, displaySize));
		
		List<TipoSeveridadDTO> resultList = new ArrayList<TipoSeveridadDTO>();
		for (SgpTipoSeveridad sgpTipoSeveridad : listaReturn) {
			TipoSeveridadDTO tipoSeveridadDTO = new TipoSeveridadDTO(sgpTipoSeveridad);
			resultList.add(tipoSeveridadDTO);
			
		}
		return new ResultSet<TipoSeveridadDTO>(resultList, countTotalRegistros.longValue(), listaReturn.size());
	}
/*
 * (non-Javadoc)
 * @see com.premize.sgp.dao.gestion.pruebas.TipoHallazgoDao#validarTipoHallazgo(com.premize.sgp.dto.pruebas.TipoHallazgoDTO)
 */
	@Override
	public boolean validarTipoSeveridad(TipoSeveridadDTO tipoSeveridadDTO)
			throws AppBaseException {
		Boolean result = false;

		DetachedCriteria criteria = DetachedCriteria.forClass(SgpTipoSeveridad.class);
		criteria.add(Restrictions.eq("nombre", tipoSeveridadDTO.getNombre().trim()).ignoreCase());
		criteria.add(Restrictions.or(Restrictions.eq(TIPOHALLAZGO_ID, tipoSeveridadDTO.getTipoHallazgo().getId()), 
									 Restrictions.isNull(TIPOHALLAZGO)));
		criteria.add(Restrictions.eq("indActivo", 1));
		
		if (!findByCriteria(criteria).isEmpty()) {
			result = true;
		}
	return result;
}
	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.dao.gestion.pruebas.TipoSeveridadDao#consultarTipoSeveridad()
	 */
	@Override
	public List<SgpTipoSeveridad> consultarTipoSeveridad() throws AppBaseException {
		List<SgpTipoSeveridad> result = null;

		DetachedCriteria criteria = DetachedCriteria.forClass(SgpTipoSeveridad.class);
		criteria.add(Restrictions.eq("indActivo", 1));
		criteria.addOrder(Order.asc("nombre"));
		if(!findByCriteria(criteria).isEmpty()){
		result = findByCriteria(criteria);
		}

	return result;
	}

	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.dao.gestion.pruebas.TipoSeveridadDao#consultaSeveridadPorTipoHallazgo(java.lang.Integer, com.premize.pmz.api.dto.Paginator)
	 */
	@Override
	public List<TipoSeveridadDTO> consultaSeveridadPorTipoHallazgo(Integer idTipoHallazgo, Paginator page) throws AppBaseException {
		
		List<SgpTipoSeveridad> causaSeverids = this.findByCriteria(listSeveridadPorTipoHallazgo(idTipoHallazgo), page);
		
		List<TipoSeveridadDTO> causaSeveridDTOs = new ArrayList<TipoSeveridadDTO>();
		for (SgpTipoSeveridad tipoSeveridad : causaSeverids) {
			TipoSeveridadDTO tipoSeveridadDTO = new TipoSeveridadDTO(tipoSeveridad);
			causaSeveridDTOs.add(tipoSeveridadDTO);
		}
		return causaSeveridDTOs;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 8/07/2014
	 * @description 
	 * @param tipoHallazgoId
	 * @return
	 * @throws AppBaseException
	 */
	@Override
	public List<TipoSeveridadDTO> consultarSeveridadPorTipoHallazgo(Integer tipoHallazgoId) throws AppBaseException {
		DetachedCriteria criteria = DetachedCriteria.forClass(SgpTipoSeveridad.class);
		criteria.add(Restrictions.eq("tipoHallazgo.id", tipoHallazgoId));
		criteria.add(Restrictions.eq("indActivo", 1));
		criteria.addOrder(Order.asc("id"));
		List<SgpTipoSeveridad> listSeveridad = findByCriteria(criteria);
		
		List<TipoSeveridadDTO> result = new ArrayList<TipoSeveridadDTO>();
		if(listSeveridad != null && !listSeveridad.isEmpty()) {
			for(SgpTipoSeveridad tipoSeveridad : listSeveridad) {
				TipoSeveridadDTO tipoSeveridadDTO = new TipoSeveridadDTO(tipoSeveridad);
				result.add(tipoSeveridadDTO);
			}
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
	private DetachedCriteria listSeveridadPorTipoHallazgo(Integer idTipoHallazgo) {
		DetachedCriteria consulta = DetachedCriteria.forClass(SgpTipoSeveridad.class);
		consulta.add(Restrictions.or(Restrictions.eq(TIPOHALLAZGO_ID, idTipoHallazgo), Restrictions.isNull(TIPOHALLAZGO)));
		consulta.add(Restrictions.eq("indActivo", 1));
		return consulta;
	}
@Override
public List<TipoSeveridadDTO> tipoHallazgos() throws AppBaseException {
	DetachedCriteria consulta = DetachedCriteria.forClass(SgpTipoSeveridad.class);
	consulta.add(Restrictions.eq("indActivo", 1));
	
	List<SgpTipoSeveridad> listSeveridad = findByCriteria(consulta);
	
	List<TipoSeveridadDTO> result = new ArrayList<TipoSeveridadDTO>();
	if(listSeveridad != null && !listSeveridad.isEmpty()) {
		for(SgpTipoSeveridad tipoSeveridad : listSeveridad) {
			TipoSeveridadDTO tipoSeveridadDTO = new TipoSeveridadDTO(tipoSeveridad);
			result.add(tipoSeveridadDTO);
		}
	}
	
	return result;
}
}
