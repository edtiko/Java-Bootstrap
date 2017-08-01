package com.premize.sgp.dao.parametros.impl;

import com.premize.sgp.dto.ResultSet;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.dao.impl.HibernateDaoImpl;
import com.premize.sgp.dao.parametros.ProyectoDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.Search;
import com.premize.sgp.dto.SortField;
import com.premize.sgp.dto.pruebas.ProyectoDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpProyecto;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class SgpProyectoDaoImpl
 * @since 27/03/2014
 *
 */
@Repository
public class SgpProyectoDaoImpl extends HibernateDaoImpl<SgpProyecto, Integer> implements ProyectoDao {
	
	private static final String ID_PROYECTO = "idProyecto";
	
	/**
	 * Ruta para acceder a la propiedad de un parÃ¡metro.
	 * 
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo a soto</a>
	 * @date 04/02/2014
	 */
	private static final String EMPRESA_ID = "sgpEmpresa.id";
	
	/**
	 * Ruta para acceder a la propiedad de un parÃ¡metro.
	 * 
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo a soto</a>
	 * @date 04/02/2014
	 */
	private static final String EMPRESA = "sgpEmpresa";
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException 
	 * @since 27/03/2014
	 * @see com.premize.sgp.dao.parametros.ProyectoDao#getListProyectos()
	 */
	@Override
	public List<SgpProyecto> getListProyectos() throws AppBaseException {
		List<SgpProyecto> salida = null;
		
			DetachedCriteria criteria = DetachedCriteria.forClass(SgpProyecto.class);
			criteria.addOrder(Order.desc(ID_PROYECTO));
			if(!findByCriteria(criteria).isEmpty()){
			salida = findByCriteria(criteria);
			}
	
		
		return salida;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.dao.parametros.ProyectoDao#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<ProyectoDTO> getRecords(PagingCriteria criteria,Boolean mostraActivoInactivo) throws AppBaseException {
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		String searchLike = criteria.getSearch();
		List<Search> searchs = criteria.getSearchFields();
		List<SortField> sortFields = criteria.getSortFields();
		
		DetachedCriteria cq = DetachedCriteria.forClass(SgpProyecto.class).createAlias("sgpEmpresa", "empresa");
		
		// Filtering and Searching
		if(!mostraActivoInactivo){
			cq.add(Restrictions.eq("indActivo", ACTIVO));
		}
		if ((searchLike != null) && (!(searchLike.isEmpty()))) {
			
			Disjunction disyuctionConsulta = Restrictions.disjunction();
			disyuctionConsulta.add(Restrictions.like("nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("descripcion", searchLike, MatchMode.ANYWHERE).ignoreCase());
			
			disyuctionConsulta.add(Restrictions.like("empresa.nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			cq.add(disyuctionConsulta);
		}
		
		for (Search search : searchs) {
			String field = search.getField();
			String value = search.getValue();
			
			if (field.equals("id") || field.equals("indActivo")) {
				if (value != null)
					cq.add(Restrictions.eq(field, Integer.parseInt(value)));
			}else {
				if (value != null)
					cq.add(Restrictions.like(field, value, MatchMode.ANYWHERE).ignoreCase().ignoreCase());
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
		
		List<SgpProyecto> listaReturn = findByCriteria(cq, new Paginator(displayStart, displaySize));
		
		List<ProyectoDTO> resultList = new ArrayList<ProyectoDTO>();
		for (SgpProyecto sgpParametro : listaReturn) {
			ProyectoDTO parametroDTO = new ProyectoDTO(sgpParametro);
			resultList.add(parametroDTO);
		}
		
		return new ResultSet<ProyectoDTO>(resultList, countTotalRegistros.longValue(), listaReturn.size());
	}
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 4/02/2014
	 * @param idCiudad
	 * @return
	 */
	private DetachedCriteria listProyectoPorEmpresa(Integer idEmpresa) {
		DetachedCriteria consulta = DetachedCriteria.forClass(SgpProyecto.class);
		consulta.add(Restrictions.or(Restrictions.eq(EMPRESA_ID, idEmpresa), Restrictions.isNull(EMPRESA)));
		return consulta;
	}
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 4/02/2014
	 * @param idCiudad
	 * @return
	 */
	@Override
	public List<ProyectoDTO> consultarProyectoPorEmpresa(Integer idEmpresa, Paginator page) throws AppBaseException {
		List<SgpProyecto> proyectos = this.findByCriteria(listProyectoPorEmpresa(idEmpresa), page);
		
		List<ProyectoDTO> proyectoDTOs = new ArrayList<ProyectoDTO>();
		for (SgpProyecto sgpProyecto : proyectos) {
			ProyectoDTO proyectoDTO = new ProyectoDTO(sgpProyecto);
			proyectoDTOs.add(proyectoDTO);
		}
		return proyectoDTOs;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 24/04/2014
	 * @description 
	 * @param idProyecto
	 * @return
	 * @throws AppBaseException
	 */
	public String consultarProyectoNombre(Integer idProyecto) throws AppBaseException {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT proyecto.nombre ");
		queryBuilder.append("FROM "+SgpProyecto.class.getName()+" proyecto");
		queryBuilder.append("WHERE proyecto.id = :idProyecto");
		Query query = createQuery(queryBuilder.toString());
		query.setInteger("idProyecto", idProyecto);
		return (String)query.uniqueResult();
	}
}