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
import com.premize.sgp.dao.parametros.EmpresaDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.Search;
import com.premize.sgp.dto.SortField;
import com.premize.sgp.dto.parametros.EmpresaDTO;
import com.premize.sgp.modelo.entities.parametros.SgpEmpresa;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class SgpEmpresaDaoImpl
 * @since 27/03/2014
 *
 */
@Repository
public class SgpEmpresaDaoImpl extends HibernateDaoImpl<SgpEmpresa, Integer> implements EmpresaDao {
	/**
	 * Ruta para acceder a la propiedad de un parÃ¡metro.
	 * 
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo a soto</a>
	 * @date 04/02/2014
	 */
	private static final String CIUDAD_ID = "sgpCiudad.id";
	
	/**
	 * Ruta para acceder a la propiedad de un parÃ¡metro.
	 * 
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo a soto</a>
	 * @date 04/02/2014
	 */
	private static final String CIUDAD = "sgpCiudad";
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 30/01/2014
	 * @see com.premize.sgp.dao.parametros.EmpresaDao#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<EmpresaDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		String searchLike = criteria.getSearch();
		List<Search> searchs = criteria.getSearchFields();
		List<SortField> sortFields = criteria.getSortFields();
		
		DetachedCriteria cq = DetachedCriteria.forClass(SgpEmpresa.class).createAlias("sgpCiudad", "ciudad")
				.createAlias("ciudad.sgpDepartamento", "departamento").createAlias("departamento.sgpPais", "pais");
		
		if ((searchLike != null) && (!(searchLike.isEmpty()))) {
			
			Disjunction disyuctionConsulta = Restrictions.disjunction();
			disyuctionConsulta.add(Restrictions.like("nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("nit", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("ciudad.nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("departamento.nombre", searchLike, MatchMode.ANYWHERE)
					.ignoreCase());
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
		
		List<SgpEmpresa> listaReturn = findByCriteria(cq, new Paginator(displayStart, displaySize));
		
		List<EmpresaDTO> resultList = new ArrayList<EmpresaDTO>();
		for (SgpEmpresa sgpEmpresa : listaReturn) {
			EmpresaDTO empresaDTO = new EmpresaDTO(sgpEmpresa);
			resultList.add(empresaDTO);
		}
		
		return new ResultSet<EmpresaDTO>(resultList, countTotalRegistros.longValue(), listaReturn.size());
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @param idCiudad
	 * @return
	 */
	private DetachedCriteria listEmpresasPorCiudad(Integer idCiudad) {
		DetachedCriteria consulta = DetachedCriteria.forClass(SgpEmpresa.class);
		consulta.add(Restrictions.or(Restrictions.eq(CIUDAD_ID, idCiudad), Restrictions.isNull(CIUDAD)));
		return consulta;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.dao.parametros.EmpresaDao#consultarEmpresaPorCiudad(java.lang.Integer, com.premize.pmz.api.dto.Paginator)
	 */
	@Override
	public List<EmpresaDTO> consultarEmpresaPorCiudad(Integer idCiudad, Paginator page) throws AppBaseException {
		List<SgpEmpresa> empresas = this.findByCriteria(listEmpresasPorCiudad(idCiudad), page);
		
		List<EmpresaDTO> empresaDTOs = new ArrayList<EmpresaDTO>();
		for (SgpEmpresa sgpEmpresa : empresas) {
			EmpresaDTO empresaDTO = new EmpresaDTO(sgpEmpresa);
			empresaDTOs.add(empresaDTO);
		}
		return empresaDTOs;
	}
	
}
