package com.premize.sgp.dao.usuarios.impl;

import com.premize.sgp.dto.ResultSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.dao.impl.HibernateDaoImpl;
import com.premize.sgp.dao.parametros.SgpParametroDao;
import com.premize.sgp.dao.usuarios.UsuarioProyectoDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.Search;
import com.premize.sgp.dto.SortField;
import com.premize.sgp.dto.pruebas.UsuarioProyectoDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpUsuarioProyecto;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class SgpUsuarioProyectoDaoImpl
 * @since 27/03/2014
 *
 */
@Repository
public class SgpUsuarioProyectoDaoImpl extends HibernateDaoImpl<SgpUsuarioProyecto, Integer> implements
		UsuarioProyectoDao {
	
	/**
	 * 
	 */
	private static final String PROYECTO = "sgpProyecto.id";
	
	private static final Integer INACTIVO = 0;
	
	@Autowired
	private SgpParametroDao parametroDao;
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 14/02/2014
	 * @see com.premize.sgp.dao.usuarios.UsuarioProyectoDao#asociarArtefactosPorMapa(java.util.List)
	 */
	@Override
	public void asociarArtefactosPorMapa(List<SgpUsuarioProyecto> usuariosProyecto) throws AppBaseException {
		
		for (SgpUsuarioProyecto usuarioProyecto : usuariosProyecto) {
			save(usuarioProyecto);
		}
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 14/02/2014
	 * @see com.premize.sgp.dao.usuarios.UsuarioProyectoDao#desasociarUsuariosProyecto(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void desasociarUsuariosProyecto(Integer idProyecto, String login) throws AppBaseException {
		DetachedCriteria consulta = DetachedCriteria.forClass(SgpUsuarioProyecto.class);
		consulta.add(Restrictions.eq(PROYECTO, idProyecto));
		
		List<SgpUsuarioProyecto> listUsuariosProyectos = findByOtherCriteria(consulta);
		
		for (SgpUsuarioProyecto usuProyecto : listUsuariosProyectos) {
			usuProyecto.setIndActivo(INACTIVO);
			usuProyecto.setUsuarioEdita(login);
			usuProyecto.setFecEdita(Calendar.getInstance().getTime());
			saveOrUpdate(usuProyecto);
		}
		
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException
	 * @since 17/02/2014
	 * @see com.premize.sgp.dao.usuarios.UsuarioProyectoDao#getUsuarioProyectoRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<UsuarioProyectoDTO> getUsuarioProyectoRecords(PagingCriteria criteria) throws AppBaseException {
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		String searchLike = criteria.getSearch();
		List<Search> searchs = criteria.getSearchFields();
		List<SortField> sortFields = criteria.getSortFields();
		
		DetachedCriteria cq = DetachedCriteria.forClass(SgpUsuarioProyecto.class).createAlias("sgpUsuario", "usuario")
				.createAlias("sgpProyecto", "proyecto");
		
		if ((searchLike != null) && (!(searchLike.isEmpty()))) {
			
			Disjunction disyuctionConsulta = Restrictions.disjunction();
			disyuctionConsulta.add(Restrictions.like("usuario.nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("usuario.cargo", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("proyecto.nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			cq.add(disyuctionConsulta);
		}
		for (Search search : searchs) {
			String field = search.getField();
			String value = search.getValue();
			
			if (field.equals("id") || field.equals("indActivo") || field.equals("proyecto.id")) {
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
		
		List<SgpUsuarioProyecto> listaReturn = findByCriteria(cq, new Paginator(displayStart, displaySize));
		
		List<UsuarioProyectoDTO> resultList = new ArrayList<UsuarioProyectoDTO>();
		for (SgpUsuarioProyecto sgpUsuarioProyecto : listaReturn) {
			UsuarioProyectoDTO usuarioProyectoDTO = new UsuarioProyectoDTO(sgpUsuarioProyecto);
			resultList.add(usuarioProyectoDTO);
		}
		
		return new ResultSet<UsuarioProyectoDTO>(resultList, countTotalRegistros.longValue(), listaReturn.size());
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 19/06/2014
	 * @description 
	 * @param idProyecto
	 * @return
	 * @throws AppBaseException
	 */
	@Override
	public List<SgpUsuarioProyecto> consultarUsuariosAsociados(Integer idProyecto) throws AppBaseException {
		DetachedCriteria consulta = DetachedCriteria.forClass(SgpUsuarioProyecto.class);
		consulta.add(Restrictions.eq(PROYECTO, idProyecto));
		List<SgpUsuarioProyecto> listUsuariosProyectos = findByOtherCriteria(consulta);
		
		return listUsuariosProyectos;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 20/06/2014
	 * @description 
	 * @param proyectoId
	 * @param idUsuarios
	 * @param login
	 * @throws AppBaseException
	 */
	@Override
	public void desasociarUsuariosProyecto(Integer proyectoId, List<Integer> idUsuarios, String login) throws AppBaseException {
		StringBuilder queryString = new StringBuilder();
		queryString.append("UPDATE ");
		queryString.append(SgpUsuarioProyecto.class.getName());
		queryString.append(" SET indActivo = :inactivo, ");
		queryString.append("usuarioEdita = :login, ");
		queryString.append("fecEdita = :fecha ");
		queryString.append("WHERE sgpProyecto.id = :proyectoId ");
		queryString.append("AND id IN (:idUsuarios) ");
		
		Query query = createQuery(queryString.toString());
		query.setInteger("inactivo", INACTIVO);
		query.setString("login", login);
		query.setDate("fecha", Calendar.getInstance().getTime());
		query.setInteger("proyectoId", proyectoId);
		query.setParameterList("idUsuarios", idUsuarios);
		query.executeUpdate();
	}
	
	
}
