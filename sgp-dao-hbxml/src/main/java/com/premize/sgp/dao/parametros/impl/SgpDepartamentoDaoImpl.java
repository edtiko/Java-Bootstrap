package com.premize.sgp.dao.parametros.impl;

import com.premize.sgp.dto.ResultSet;
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
import com.premize.sgp.dao.parametros.DepartamentoDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.Search;
import com.premize.sgp.dto.SortField;
import com.premize.sgp.dto.parametros.DepartamentoDTO;
import com.premize.sgp.modelo.entities.parametros.SgpDepartamento;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class SgpDepartamentoDaoImpl
 * @since 27/03/2014
 *
 */
@Repository
public class SgpDepartamentoDaoImpl extends HibernateDaoImpl<SgpDepartamento, Integer> implements DepartamentoDao {
	
	/**
	 * Ruta para acceder a la propiedad de un parÃ¡metro.
	 * 
	 * @author <a href="mailto:julioc.chaves@premize.com">Julio C&eacute;sar Chaves</a>
	 * @date 13/06/2012
	 */
	private static final String PAIS_ID = "sgpPais.id";
	
	/**
	 * Ruta para acceder a la propiedad tipo de un parÃ¡metro.
	 * 
	 * @author <a href="mailto:julioc.chaves@premize.com">Julio C&eacute;sar Chaves</a>
	 * @date 13/06/2012
	 */
	private static final String PAIS = "sgpPais";
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.dao.parametros.DepartamentoDao#consultarDepartamentos()
	 */
	@Override
	public List<SgpDepartamento> consultarDepartamentos() throws AppBaseException{
	
			DetachedCriteria criteria = DetachedCriteria.forClass(SgpDepartamento.class);
			
			criteria.add(Restrictions.eq("indActivo", 1));

		return findByCriteria(criteria);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.dao.parametros.DepartamentoDao#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<DepartamentoDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		String searchLike = criteria.getSearch();
		List<Search> searchs = criteria.getSearchFields();
		List<SortField> sortFields = criteria.getSortFields();
		
		DetachedCriteria cq = DetachedCriteria.forClass(SgpDepartamento.class).createAlias("sgpPais", "pais");
		
		if ((searchLike != null) && (!(searchLike.isEmpty()))) {
			Disjunction disyuctionConsulta = Restrictions.disjunction();
			disyuctionConsulta.add(Restrictions.like("nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("pais.nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			cq.add(disyuctionConsulta);
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
		List<SgpDepartamento> listaReturn = findByCriteria(cq, new Paginator(displayStart, displaySize));
		
		List<DepartamentoDTO> resultList = new ArrayList<DepartamentoDTO>();
		for (SgpDepartamento sgpDepartamento : listaReturn) {
			DepartamentoDTO departamentoDTO = new DepartamentoDTO(sgpDepartamento);
			resultList.add(departamentoDTO);
			
		}
		return new ResultSet<DepartamentoDTO>(resultList, countTotalRegistros.longValue(), listaReturn.size());
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 27/01/2014
	 * @see com.premize.sgp.dao.parametros.DepartamentoDao#obtenerDepartamentos()
	 */
	
	private DetachedCriteria listDepartamentosPorPais(Integer idPais) {
		DetachedCriteria consulta = DetachedCriteria.forClass(SgpDepartamento.class);
		consulta.add(Restrictions.or(Restrictions.eq(PAIS_ID, idPais), Restrictions.isNull(PAIS)));
		return consulta;
	}
	
	/**
	 * ImplementaciÃ³n con hibernate del mÃ©todo
	 * {@link ParametroDao# consultarParametrosAsociables(java.lang.Long, com.premize.pmz.api.dto.Paginator)} .
	 */
	public List<DepartamentoDTO> consultarDepartamentosPorPais(Integer idPais, Paginator page) throws AppBaseException {
		List<SgpDepartamento> departamentos = this.findByCriteria(listDepartamentosPorPais(idPais), page);
		
		List<DepartamentoDTO> departamentoDTOs = new ArrayList<DepartamentoDTO>();
		for (SgpDepartamento sgpDepartamento : departamentos) {
			DepartamentoDTO departamentoDTO = new DepartamentoDTO(sgpDepartamento);
			departamentoDTOs.add(departamentoDTO);
		}
		return departamentoDTOs;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException 
	 * @since 7/03/2014
	 * @see com.premize.sgp.dao.parametros.DepartamentoDao#validarDepartamento(com.premize.sgp.dto.parametros.DepartamentoDTO)
	 */
	@Override
	public Boolean validarDepartamento(DepartamentoDTO depto) throws AppBaseException {
		Boolean result = false;
	
			DetachedCriteria criteria = DetachedCriteria.forClass(SgpDepartamento.class).createAlias("sgpPais", "pais");
			criteria.add(Restrictions.eq("pais.id", depto.getPais().getId()));
			criteria.add(Restrictions.eq("nombre", depto.getNombre()));
			criteria.add(Restrictions.eq("indActivo", 1));
			
			if (!findByCriteria(criteria).isEmpty()) {
				result = true;
			}
	
		return result;
	}
	
}
