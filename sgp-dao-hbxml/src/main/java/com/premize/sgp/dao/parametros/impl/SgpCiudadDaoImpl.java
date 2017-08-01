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
import com.premize.sgp.dao.parametros.CiudadDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.Search;
import com.premize.sgp.dto.SortField;
import com.premize.sgp.dto.parametros.CiudadDTO;
import com.premize.sgp.modelo.entities.parametros.SgpCiudad;
import com.premize.sgp.modelo.entities.parametros.SgpDepartamento;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class SpgCiudadDaoImpl
 * @since 27/03/2014
 *
 */
@Repository
public class SgpCiudadDaoImpl extends HibernateDaoImpl<SgpCiudad, Integer> implements CiudadDao {
	/**
	 * 
	 */
	private static final String DEPARTAMENTO_ID = "sgpDepartamento.id";
	/**
	 * 
	 */
	private static final String DEPARTAMENTO = "sgpDepartamento";
	
	/**
	 * @author <a href="mailto:johnh.bernal@premize.com">John Hawer Bernal</a>
	 * @date 05/02/2014
	 * @return
	 * @throws AppBaseException
	 */
	@Override
	public List<SgpCiudad> consultarCiudades() throws AppBaseException {
		List<SgpCiudad> result = null;

			DetachedCriteria criteria = DetachedCriteria.forClass(SgpCiudad.class);
			
			criteria.add(Restrictions.eq("indActivo", 1));
			criteria.addOrder(Order.asc("nombre"));
			result = findByCriteria(criteria);
			/*if(!result.isEmpty()){
			return result;
			}*/
	
		return result;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.dao.parametros.CiudadDao#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<CiudadDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		String searchLike = criteria.getSearch();
		List<Search> searchs = criteria.getSearchFields();
		List<SortField> sortFields = criteria.getSortFields();
		
		/**
		 * Se cambia de posicion la variable countTotalRegistros para mejorar el paginador
		 */
		DetachedCriteria cq = DetachedCriteria.forClass(SgpCiudad.class).createAlias("sgpDepartamento", "departamento")
				.createAlias("departamento.sgpPais", "pais");
		
		if ((searchLike != null) && (!(searchLike.isEmpty()))) {
			Disjunction disyuctionConsulta = Restrictions.disjunction();
			disyuctionConsulta.add(Restrictions.like("nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("pais.nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("departamento.nombre", searchLike, MatchMode.ANYWHERE)
					.ignoreCase());
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
		List<SgpCiudad> listaReturn = findByCriteria(cq, new Paginator(displayStart, displaySize));
		
		List<CiudadDTO> resultList = new ArrayList<CiudadDTO>();
		for (SgpCiudad sgpCiudad : listaReturn) {
			CiudadDTO ciudadDTO = new CiudadDTO(sgpCiudad);
			resultList.add(ciudadDTO);
			
		}
		return new ResultSet<CiudadDTO>(resultList, countTotalRegistros.longValue(), listaReturn.size());
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.dao.parametros.CiudadDao#getDepartamentoByCiudad(java.lang.Integer)
	 */
	@Override
	public SgpDepartamento getDepartamentoByCiudad(Integer idCiudad) {
		SgpDepartamento result = null;
		
			DetachedCriteria criteria = DetachedCriteria.forClass(SgpDepartamento.class);
			
			criteria.add(Restrictions.eq("sgpCiudads.id", idCiudad));
			criteria.addOrder(Order.asc("nombre"));
	
		return result;
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 27/01/2014
	 * @see com.premize.sgp.dao.parametros.DepartamentoDao#obtenerDepartamentos()
	 */
	
	private DetachedCriteria listCiudadesPorDepartamento(Integer idDepartamento) {
		DetachedCriteria consulta = DetachedCriteria.forClass(SgpCiudad.class);
		consulta.add(Restrictions.or(Restrictions.eq(DEPARTAMENTO_ID, idDepartamento),
				Restrictions.isNull(DEPARTAMENTO)));
		consulta.addOrder(Order.asc("nombre"));
		return consulta;
	}
	
	/**
	 * ImplementaciÃƒÂ³n con hibernate del mÃƒÂ©todo
	 * {@link ParametroDao# consultarParametrosAsociables(java.lang.Long, com.premize.pmz.api.dto.Paginator)} .
	 */
	public List<CiudadDTO> consultarCiudadPorDepartamento(Integer idDepartamento, Paginator page)
			throws AppBaseException {
		List<SgpCiudad> ciudades = this.findByCriteria(listCiudadesPorDepartamento(idDepartamento), page);
		
		List<CiudadDTO> ciudadDTOs = new ArrayList<CiudadDTO>();
		for (SgpCiudad sgpCiudad : ciudades) {
			CiudadDTO ciudadDTO = new CiudadDTO(sgpCiudad);
			ciudadDTOs.add(ciudadDTO);
		}
		return ciudadDTOs;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException 
	 * @since 7/03/2014
	 * @see com.premize.sgp.dao.parametros.CiudadDao#validarCiudad(com.premize.sgp.dto.parametros.CiudadDTO)
	 */
	@Override
	public Boolean validarCiudad(CiudadDTO ciudad) throws AppBaseException {
		
		Boolean result = false;
			DetachedCriteria criteria = DetachedCriteria.forClass(SgpCiudad.class).createAlias("sgpDepartamento",
					"departamento");
			criteria.add(Restrictions.eq("departamento.id", ciudad.getDepartamento().getId()));
			criteria.add(Restrictions.eq("nombre", ciudad.getNombre()));
			criteria.add(Restrictions.eq("indActivo", 1));
			
			if (!findByCriteria(criteria).isEmpty()) {
				result = true;
			}
	
		return result;
		
	}
	
}
