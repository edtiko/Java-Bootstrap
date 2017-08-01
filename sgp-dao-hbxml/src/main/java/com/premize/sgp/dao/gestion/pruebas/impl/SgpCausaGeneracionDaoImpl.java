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
import com.premize.sgp.dao.gestion.pruebas.CausaGeneracionDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.Search;
import com.premize.sgp.dto.SortField;
import com.premize.sgp.dto.pruebas.CausaGeneracionDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpCausaGeneracion;
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
public class SgpCausaGeneracionDaoImpl extends HibernateDaoImpl<SgpCausaGeneracion, Integer> implements CausaGeneracionDao {

	
	private static final String TIPOHALLAZGO_ID = "sgpTipoHallazgo.id";

	private static final String TIPOHALLAZGO = "sgpTipoHallazgo";

	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.dao.gestion.pruebas.TipoHallazgoDao#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<CausaGeneracionDTO> getRecords(PagingCriteria criteria)
			throws AppBaseException {

		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		String searchLike = criteria.getSearch();
		List<Search> searchs = criteria.getSearchFields();
		List<SortField> sortFields = criteria.getSortFields();
		
		DetachedCriteria cq = DetachedCriteria.forClass(SgpCausaGeneracion.class)
				.createAlias("sgpTipoHallazgo", "tipoHallazgo");;
		
		if ((searchLike != null) && (!(searchLike.isEmpty()))) {
			Disjunction disyuctionConsulta = Restrictions.disjunction();
			disyuctionConsulta.add(Restrictions.like("nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("tipoHallazgo.nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
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
		List<SgpCausaGeneracion> listaReturn = findByCriteria(cq, new Paginator(displayStart, displaySize));
		
		List<CausaGeneracionDTO> resultList = new ArrayList<CausaGeneracionDTO>();
		for (SgpCausaGeneracion sgpCausaGeneracion : listaReturn) {
			CausaGeneracionDTO causaGeneracionDTO = new CausaGeneracionDTO(sgpCausaGeneracion);
			resultList.add(causaGeneracionDTO);
			
		}
		return new ResultSet<CausaGeneracionDTO>(resultList, countTotalRegistros.longValue(), listaReturn.size());
	}
/*
 * (non-Javadoc)
 * @see com.premize.sgp.dao.gestion.pruebas.TipoHallazgoDao#validarTipoHallazgo(com.premize.sgp.dto.pruebas.TipoHallazgoDTO)
 */
	@Override
	public boolean validarCausaGeneracion(CausaGeneracionDTO causaGeneracionDTO)
			throws AppBaseException {
		Boolean result = false;

		DetachedCriteria criteria = DetachedCriteria.forClass(SgpCausaGeneracion.class);
		criteria.add(Restrictions.eq("nombre", causaGeneracionDTO.getNombre().trim()).ignoreCase());
		criteria.add(Restrictions.eq("sgpTipoHallazgo.id", causaGeneracionDTO.getTipoHallazgo().getId()));
		criteria.add(Restrictions.eq("indActivo", 1));
		
		if (!findByCriteria(criteria).isEmpty()) {
			result = true;
		}
	return result;
}
	
	@Override
	public List<SgpCausaGeneracion> consultarCausaGeneracion()
			throws AppBaseException {
		List<SgpCausaGeneracion> result = null;

		DetachedCriteria criteria = DetachedCriteria.forClass(SgpCausaGeneracion.class);
		criteria.add(Restrictions.eq("indActivo", 1));
		criteria.addOrder(Order.asc("nombre"));
		if(!findByCriteria(criteria).isEmpty()){
		result = findByCriteria(criteria);
		}

	return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.dao.gestion.pruebas.CausaGeneracionDao#consultaCausaPorTipoHallazgo(java.lang.Integer, com.premize.pmz.api.dto.Paginator)
	 */
	@Override
	public List<CausaGeneracionDTO> consultaCausaPorTipoHallazgo(Integer idTipoHallazgo, Paginator page) throws AppBaseException {
		
		List<SgpCausaGeneracion> causaGeneracions = this.findByCriteria(listCausaGeneracionPorTipoHallazgo(idTipoHallazgo), page);
		
		List<CausaGeneracionDTO> causaGeneracionDTOs = new ArrayList<CausaGeneracionDTO>();
		for (SgpCausaGeneracion causaGeneracion : causaGeneracions) {
			CausaGeneracionDTO causaGeneracionDTO = new CausaGeneracionDTO(causaGeneracion);
			causaGeneracionDTOs.add(causaGeneracionDTO);
		}
		return causaGeneracionDTOs;
	}
	

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 18/06/2014
	* @param idPais
	* @return
	 */
	private DetachedCriteria listCausaGeneracionPorTipoHallazgo(Integer idTipoHallazgo) {
		DetachedCriteria consulta = DetachedCriteria.forClass(SgpCausaGeneracion.class);
		consulta.add(Restrictions.or(Restrictions.eq(TIPOHALLAZGO_ID, idTipoHallazgo), Restrictions.isNull(TIPOHALLAZGO)));
		consulta.add(Restrictions.eq("indActivo", 1));
		return consulta;
	}
	

}
