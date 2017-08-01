/**
 * 
 */
package com.premize.sgp.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.api.util.Filter;
import com.premize.pmz.service.AbstractEntityService;
import com.premize.sgp.dao.parametros.SgpParametroDao;
import com.premize.sgp.dao.parametros.TipoParametroDao;
import com.premize.sgp.dao.parametros.UsuarioDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.ParametroDTO;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;
import com.premize.sgp.modelo.entities.parametros.SgpTipoParametro;
import com.premize.sgp.service.ParametroService;
import com.premize.sgp.utils.LogUtil;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class ParametroServiceImpl
 * @since 15/01/2014
 */
@Service
public class ParametroServiceImpl extends AbstractEntityService<SgpParametro, Integer> implements ParametroService {
	
	@Autowired
	private SgpParametroDao parametroDao;
	
	@Autowired
	private TipoParametroDao tipoParametroDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException 
	 * @since 15/01/2014
	 * @see com.premize.sgp.service.ParametroService#getParametros()
	 */
	@Override
	public List<ParametroDTO> getListParametros() throws AppBaseException {
		
		List<ParametroDTO> resultList = new ArrayList<ParametroDTO>();
		List<SgpParametro> parametros = parametroDao.getListParametros();
		for (SgpParametro sgpParametro : parametros) {
			ParametroDTO parametroDTO = new ParametroDTO(sgpParametro);
			
			resultList.add(parametroDTO);
		}
		
		return resultList;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 17/02/2014
	 * @see com.premize.sgp.service.ParametroService#guardarParametro(com.premize.sgp.dto.parametros.ParametroDTO)
	 */
	public void guardarParametro(ParametroDTO parametro) throws AppBaseException {
		
		SgpParametro sgpParametro = new SgpParametro();
		SgpTipoParametro sgpTipoParametro = tipoParametroDao.get(parametro.getTipoParametro().getId());
		
		sgpParametro.setDescripcion(parametro.getDescripcion());
		sgpParametro.setNombre(parametro.getNombre());
		sgpParametro.setValor(parametro.getValor());
		sgpParametro.setSgpTipoParametro(sgpTipoParametro);
		sgpParametro.setFecCreacion(Calendar.getInstance().getTime());
		sgpParametro.setIndActivo(parametro.getNumeroEstado());
		sgpParametro.setUsuarioCrea(parametro.getUsuarioCreacion());
		parametroDao.save(sgpParametro);
		
	}
	
	@Override
	public SgpParametroDao getDao() {
		return parametroDao;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.ParametroService#findAllParametrosByFilter(com.premize.pmz.api.util.Filter[], com.premize.pmz.api.dto.Paginator)
	 */
	@Override
	public List<SgpParametro> findAllParametrosByFilter(Filter[] filters, Paginator paginator) {
		return null;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.ParametroService#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<ParametroDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		return parametroDao.getRecords(criteria);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 17/02/2014
	 * @see com.premize.sgp.service.ParametroService#editarParametro(com.premize.sgp.dto.parametros.ParametroDTO)
	 */
	@Override
	public void editarParametro(ParametroDTO parametro) throws AppBaseException {
		
		SgpParametro sgpParametro = parametroDao.get(parametro.getId());
		SgpTipoParametro sgpTipoParametro = tipoParametroDao.get(parametro.getTipoParametro().getId());
		
		sgpParametro.setDescripcion(parametro.getDescripcion());
		sgpParametro.setNombre(parametro.getNombre());
		sgpParametro.setValor(parametro.getValor());
		sgpParametro.setSgpTipoParametro(sgpTipoParametro);
		sgpParametro.setFecEdita(Calendar.getInstance().getTime());
		sgpParametro.setIndActivo(parametro.getNumeroEstado());
		sgpParametro.setUsuarioEdita(parametro.getUsuarioEdita());
		parametroDao.update(sgpParametro);
		
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException
	 * @since 28/01/2014
	 * @see com.premize.sgp.service.ParametroService#getParametro(java.lang.Integer)
	 */
	@Override
	public ParametroDTO getParametro(Integer idParametro) throws AppBaseException {
		
		SgpParametro sgpParametro = parametroDao.get(idParametro);
		ParametroDTO parametroDTO = new ParametroDTO(sgpParametro);
		return parametroDTO;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 6/06/2014
	 * @description 
	 * @param parametroKey
	 * @return
	 * @throws AppBaseException
	 */
	@Override
	public ParametroDTO getParametro(String parametroKey) throws AppBaseException {
		if(null == parametroKey || parametroKey.isEmpty()) {
			throw new AppBaseException("El nombre del parametro no es valido", "NO_PARAMETRO");
		}
		
		SgpParametro sgpParametro = parametroDao.findByProperty(ParametroService.PARAMETRO_FIELD_CLAVE, parametroKey);
		if(null == sgpParametro) {
			LogUtil.log(getClass(),Level.INFO,
					"consultar parametro retorna null con parametroKey "+parametroKey,null);
			throw new AppBaseException("El parametro no existe", "NO_PARAMETRO");
		}
		
		ParametroDTO parametroDTO = new ParametroDTO(sgpParametro);
		return parametroDTO;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.ParametroService#listarParametrosPorTipoParametro(java.lang.Integer, com.premize.pmz.api.dto.Paginator)
	 */
	@Override
	public List<ParametroDTO> listarParametrosPorTipoParametro(Integer idTipoParametro, Paginator page)
			throws AppBaseException {
		
		return getDao().listarParametrosPorTipoParametro(idTipoParametro, page);
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 29/05/2014
	 * @description 
	 * @return
	 * @throws AppBaseException
	 */
	@Override
	public Map<String,Object> getMapRestriccionesAnexo() throws AppBaseException {
		Map<String,Object> restricciones = new HashMap<String,Object>();
		
		SgpParametro parametroMaxSizeAnexo = parametroDao
				.findByProperty(ParametroService.PARAMETRO_FIELD_CLAVE, ParametroService.CODIGO_MAX_SIZE_ANEXO);	
		Integer maxSizeAnexo = Integer.parseInt(parametroMaxSizeAnexo.getValor());
		
		SgpParametro parametroTypeAllowed = parametroDao
				.findByProperty(ParametroService.PARAMETRO_FIELD_CLAVE, ParametroService.CODIGO_ALLOWED_FILE_TYPES);
		String allowedFileTypes =parametroTypeAllowed.getValor();
		
		restricciones.put(ANEXO_REST_MAP_KEY_ALLOWED_FILES, allowedFileTypes);
		restricciones.put(ANEXO_REST_MAP_KEY_FILE_SIZE, maxSizeAnexo);
		
		return restricciones;
		
	}
/*
 * (non-Javadoc)
 * @see com.premize.sgp.service.ParametroService#getEstadoNoSolicitaUsuario(java.lang.String)
 */
	@Override
	public boolean getEstadoNoSolicitaUsuario(String estado) throws AppBaseException {
		
		SgpParametro parametrosEstadoNoSolicitanUsuario = parametroDao
				.findByProperty(ParametroService.PARAMETRO_FIELD_CLAVE, ParametroService.ESTADO_NO_ACTUALIZA_USUARIO_KEY);
		
		String estados=parametrosEstadoNoSolicitanUsuario.getValor();

			if(estados.contains(estado)){
				return true;
			}
		
		
		
		return false;
	}

/*
 * (non-Javadoc)
 * @see com.premize.sgp.service.ParametroService#getMapRestriccionesLogo()
 */
	@Override
	public Map<String, Object> getMapRestriccionesLogo()
			throws AppBaseException {
	Map<String,Object> restricciones = new HashMap<String,Object>();
		
		
		SgpParametro parametroTypeAllowed = parametroDao
				.findByProperty(ParametroService.PARAMETRO_FIELD_CLAVE, ParametroService.CODIGO_ALLOWED_LOGO_TYPES);
		String allowedFileTypes =parametroTypeAllowed.getValor();
		
		restricciones.put(ANEXO_REST_MAP_KEY_ALLOWED_LOGO_FILES, allowedFileTypes);
	
		
		return restricciones;
		
	}
	
}
