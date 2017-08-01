/**
 * 
 */
package com.premize.sgp.service.impl;

import com.premize.sgp.dto.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.service.AbstractEntityService;
import com.premize.sgp.dao.parametros.TipoParametroDao;
import com.premize.sgp.dao.parametros.UsuarioDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.parametros.TipoParametroDTO;
import com.premize.sgp.modelo.entities.parametros.SgpTipoParametro;
import com.premize.sgp.service.TipoParametroService;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class TipoParametroServiceImpl
 * @since 16/01/2014
 */
@Service
public class TipoParametroServiceImpl extends AbstractEntityService<SgpTipoParametro, Integer> implements
		TipoParametroService {
	
	@Autowired
	TipoParametroDao tipoParametroDao;
	
	@Autowired
	UsuarioDao usuarioDao;
	
	@Override
	public Dao<SgpTipoParametro, Integer> getDao() {
		return tipoParametroDao;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException 
	 * @since 17/02/2014
	 * @see com.premize.sgp.service.TipoParametroService#getTipos()
	 */
	@Override
	public List<TipoParametroDTO> getTipos() throws AppBaseException {
		
		List<TipoParametroDTO> resultList = new ArrayList<TipoParametroDTO>();
		List<SgpTipoParametro> tipos = tipoParametroDao.getTipos();
		for (SgpTipoParametro sgpTipoParametro : tipos) {
			TipoParametroDTO tipoParametroDTO = new TipoParametroDTO(sgpTipoParametro);
			
			resultList.add(tipoParametroDTO);
		}
		
		return resultList;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 29/01/2014
	 * @see com.premize.sgp.service.TipoParametroService#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<TipoParametroDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		return tipoParametroDao.getRecords(criteria);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 30/01/2014
	 * @see com.premize.sgp.service.TipoParametroService#guardarTipoParametro()
	 */
	@Override
	public void guardarTipoParametro(TipoParametroDTO tipoParametro) throws AppBaseException {
		
		SgpTipoParametro sgpTipoParametro = new SgpTipoParametro();
		sgpTipoParametro.setDescripcion(tipoParametro.getDescripcion());
		sgpTipoParametro.setNombre(tipoParametro.getNombre());
		sgpTipoParametro.setFecCreacion(Calendar.getInstance().getTime());
		sgpTipoParametro.setIndActivo(tipoParametro.getNumeroEstado());
		sgpTipoParametro.setUsuarioCrea(tipoParametro.getUsuarioCreacion());
		tipoParametroDao.save(sgpTipoParametro);
		
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 30/01/2014
	 * @see com.premize.sgp.service.TipoParametroService#editarTipoParametro()
	 */
	@Override
	public void editarTipoParametro(TipoParametroDTO tipoParametro) throws AppBaseException {
		
		SgpTipoParametro sgpTipoParametro = tipoParametroDao.get(tipoParametro.getId());
		sgpTipoParametro.setDescripcion(tipoParametro.getDescripcion());
		sgpTipoParametro.setNombre(tipoParametro.getNombre());
		sgpTipoParametro.setFecEdita(Calendar.getInstance().getTime());
		sgpTipoParametro.setIndActivo(tipoParametro.getNumeroEstado());
		sgpTipoParametro.setUsuarioEdita(tipoParametro.getUsuarioEdita());
		tipoParametroDao.update(sgpTipoParametro);
		
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException
	 * @since 30/01/2014
	 * @see com.premize.sgp.service.TipoParametroService#getTipoParametro(java.lang.Integer)
	 */
	@Override
	public TipoParametroDTO getTipoParametro(Integer idTipoParametro) throws AppBaseException {
		SgpTipoParametro sgpTipoParametro = tipoParametroDao.get(idTipoParametro);
		TipoParametroDTO tipoParametroDTO = new TipoParametroDTO(sgpTipoParametro);
		return tipoParametroDTO;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.TipoParametroService#listarParametrosPorTipoHallazgo(java.lang.Integer, com.premize.pmz.api.dto.Paginator)
	 */
	@Override
	public List<TipoParametroDTO> listarParametrosPorTipoHallazgo(Integer idTipoHallazgo, Paginator page)
			throws AppBaseException {
		return null;
	}
	
}
