package com.premize.sgp.dao.parametros.impl;

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
import com.premize.sgp.dao.parametros.UsuarioDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.Search;
import com.premize.sgp.dto.SortField;
import com.premize.sgp.dto.parametros.UsuarioDTO;
import com.premize.sgp.modelo.entities.parametros.SgpUsuario;

/**
 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
 * @project sgp-dao-hbxml
 * @class SgpUsuarioDaoImpl
 * @since 14/02/2014
 */
@Repository
public class SgpUsuarioDaoImpl extends HibernateDaoImpl<SgpUsuario, Integer> implements UsuarioDao {
	
	private static final Integer ACTIVO = 1;
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 30/01/2014
	 * @see com.premize.sgp.dao.parametros.UsuarioDao#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<UsuarioDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		String searchLike = criteria.getSearch();
		List<Search> searchs = criteria.getSearchFields();
		List<SortField> sortFields = criteria.getSortFields();
		
		DetachedCriteria cq = DetachedCriteria.forClass(SgpUsuario.class);
		
		if ((searchLike != null) && (!(searchLike.isEmpty()))) {
			
			Disjunction disyuctionConsulta = Restrictions.disjunction();
			disyuctionConsulta.add(Restrictions.like("nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("empresa", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("login", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("cargo", searchLike, MatchMode.ANYWHERE).ignoreCase());
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
		
		List<SgpUsuario> listaReturn = findByCriteria(cq, new Paginator(displayStart, displaySize));
		
		List<UsuarioDTO> resultList = new ArrayList<UsuarioDTO>();
		for (SgpUsuario sgpUsuario : listaReturn) {
			UsuarioDTO usuarioDTO = new UsuarioDTO(sgpUsuario);
			resultList.add(usuarioDTO);
		}
		
		return new ResultSet<UsuarioDTO>(resultList, countTotalRegistros.longValue(), listaReturn.size());
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 13/02/2014
	 * @see com.premize.sgp.dao.parametros.UsuarioDao#obtenerUsuarioPorProyecto(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioDTO> obtenerUsuarioPorProyecto(Integer idProyecto) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("Select usuario.nombre as nombre ,usuario.cargo as cargo, usuario.empresa as empresa,usuario.id as id From com.premize.sgp.modelo.entities.parametros.SgpUsuario usuario, com.premize.sgp.modelo.entities.gestion.pruebas.SgpUsuarioProyecto usuPro");
		builder.append(" WHERE usuario.id = usuPro.sgpUsuario.id ");
		builder.append(" AND usuPro.sgpProyecto.id = :proyecto");
		builder.append(" AND usuPro.indActivo = 1 ");
		builder.append(" ORDER BY usuario.nombre");
		
		Query query = createQuery(builder.toString());
		query.setParameter("proyecto", idProyecto);
		
		query.setResultTransformer(Transformers.aliasToBean(UsuarioDTO.class));
		
		return findByOtherQuery(query);
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 13/02/2014
	 * @see com.premize.sgp.dao.parametros.UsuarioDao#obtenerUsuariosParaAsociar(java.lang.Integer)
	 */
	@Override
	public List<UsuarioDTO> obtenerUsuariosParaAsociar(Integer idProyecto) throws Exception {
		
		StringBuilder builder = new StringBuilder();
		builder.append("Select usuario.nombre as nombre ,usuario.empresa as empresa,usuario.id as id From com.premize.sgp.modelo.entities.parametros.SgpUsuario usuario");
		builder.append(" WHERE usuario.indActivo = 1 ");
		
		if (idProyecto != null) {
			builder.append(" AND NOT EXISTS(select id FROM com.premize.sgp.modelo.entities.gestion.pruebas.SgpUsuarioProyecto usuPro WHERE ");
			builder.append("  usuPro.sgpProyecto.id = :proyecto ");
			builder.append(" AND usuPro.sgpUsuario.id = usuario.id ");
			builder.append(" AND usuPro.indActivo = 1 )");
			builder.append(" ORDER BY usuario.nombre");
		}
		
		Query query = createQuery(builder.toString());
		if (idProyecto != null) {
			query.setParameter("proyecto", idProyecto);
		}
		
		query.setResultTransformer(Transformers.aliasToBean(UsuarioDTO.class));
		
		return findByOtherQuery(query);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException 
	 * @since 3/03/2014
	 * @see com.premize.sgp.dao.parametros.UsuarioDao#getUsuarioByLogin(java.lang.String)
	 */
	@Override
	public SgpUsuario getUsuarioByLogin(String usuarioCreacion) throws AppBaseException {
		
		SgpUsuario salida = null;
		List<SgpUsuario> list = new ArrayList<SgpUsuario>();
			DetachedCriteria criteria = DetachedCriteria.forClass(SgpUsuario.class);
			
			criteria.add(Restrictions.eq("indActivo", ACTIVO));
			criteria.add(Restrictions.eq("login", usuarioCreacion));
			list = findByCriteria(criteria);
			if (!list.isEmpty()) {
				salida = list.get(0);
			}
	
		return salida;
	}
	
}
