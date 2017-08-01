package com.premize.sgp.dao.gestion.pruebas.impl;

import com.premize.sgp.dto.ResultSet;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
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
import com.premize.sgp.dao.gestion.pruebas.CasoPruebaDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.Search;
import com.premize.sgp.dto.SortField;
import com.premize.sgp.dto.pruebas.CasoDePruebaDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpCasoPrueba;
import com.premize.sgp.utils.LogUtil;
import com.premize.sgp.utils.NumericUtils;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-dao-hbxml
 * @class SgpCasoPruebaDaoImpl
 * @since 27/03/2014
 *
 */
@Repository
public class SgpCasoPruebaDaoImpl extends HibernateDaoImpl<SgpCasoPrueba, Integer> implements CasoPruebaDao {
	
	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	 * @date 11/02/2014
	 * @return
	 * @throws AppBaseException 
	 */
	public List<SgpCasoPrueba> getListCasosDePruebas() throws AppBaseException {
		List<SgpCasoPrueba> salida = null;

			DetachedCriteria criteria = DetachedCriteria.forClass(SgpCasoPrueba.class);
			criteria.addOrder(Order.desc("id"));
			
		  if(!findByCriteria(criteria).isEmpty()){
			  salida = findByCriteria(criteria);
		  }
		return salida;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.dao.gestion.pruebas.CasoPruebaDao#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<CasoDePruebaDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		Integer displaySize = criteria.getDisplaySize();
		Integer displayStart = criteria.getDisplayStart();
		String searchLike = criteria.getSearch();
		List<Search> searchs = criteria.getSearchFields();
		List<SortField> sortFields = criteria.getSortFields();
		
		DetachedCriteria cq = DetachedCriteria.forClass(SgpCasoPrueba.class).createAlias("sgpArtefacto", "artefacto")
				.createAlias("tipoPrueba", "tipoPrueba").createAlias("sgpMapaPrueba", "mapaPrueba");
		
		if ((searchLike != null) && (!(searchLike.isEmpty()))) {
			
			Disjunction disyuctionConsulta = Restrictions.disjunction();
			
			disyuctionConsulta.add(Restrictions.like("usuarioCrea", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("resultado", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("descripcion", searchLike, MatchMode.ANYWHERE).ignoreCase());
			disyuctionConsulta.add(Restrictions.like("artefacto.nombre", searchLike, MatchMode.ANYWHERE).ignoreCase());
			cq.add(disyuctionConsulta);
		}
		for (Search search : searchs) {
			String field = search.getField();
			String value = search.getValue();
			
			if (field.equals("id")|| field.equals("consecutivo") ) {
				if (value != null){
					 if(!NumericUtils.isNumeric(value)){
						 value="0";
					 }
					 cq.add(Restrictions.eq(field, Integer.parseInt(value)));
				}
			}else if( field.equals("indActivo") )	{
				
				if (value != null)
					cq.add(Restrictions.eq(field, Integer.parseInt(value)));
			} else {
				if (value != null)
					cq.add(Restrictions.like(field, value, MatchMode.ANYWHERE).ignoreCase());
			}
		}
		// Sorting
		Integer countTotalRegistros = findByCriteria(cq).size();
		
		for (SortField sortField : sortFields) {
			
			String field = sortField.getField();
			String direction = sortField.getDirection().getDirection();
			if (direction.equalsIgnoreCase("asc")) {
				cq.addOrder(Order.asc(field));
			} else if (direction.equalsIgnoreCase("desc")) {
				cq.addOrder(Order.desc(field));
				
			}
			
		}
		
		List<SgpCasoPrueba> listaReturn = findByCriteria(cq, new Paginator(displayStart, displaySize));
		List<CasoDePruebaDTO> resultList = new ArrayList<CasoDePruebaDTO>();
		for (SgpCasoPrueba sgpCasoPrueba : listaReturn) {
			CasoDePruebaDTO casoDePruebaDTO = new CasoDePruebaDTO(sgpCasoPrueba);
			resultList.add(casoDePruebaDTO);
		}
		
		ResultSet<CasoDePruebaDTO> rst = new ResultSet<CasoDePruebaDTO>(resultList, countTotalRegistros.longValue(),
				listaReturn.size());
		return rst;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 5/06/2014
	 * @description 
	 * @param idmapa
	 * @return
	 * @throws AppBaseException
	 */
	public Integer consultarConsecutivo(Integer idmapa) throws AppBaseException {
		Query query = createNamedQuery(CasoPruebaDao.NQ_CONSULTAR_MAXIMO_CONSECUTIVO);
		query.setInteger("idmapa", idmapa);
		Integer consecutivo = (Integer)query.uniqueResult();
		if(null == consecutivo) {
			return 0;
		}
		return consecutivo;
	}
	
}
