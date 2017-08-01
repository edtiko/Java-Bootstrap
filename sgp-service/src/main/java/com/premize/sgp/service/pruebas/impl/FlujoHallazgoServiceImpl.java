/**
 * 
 */
package com.premize.sgp.service.pruebas.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.service.AbstractEntityService;
import com.premize.sgp.dao.gestion.pruebas.FlujoHallazgoDao;
import com.premize.sgp.dao.gestion.pruebas.HallazgoDao;
import com.premize.sgp.dao.parametros.SgpParametroDao;
import com.premize.sgp.dao.parametros.UsuarioDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.pruebas.FlujoHallazgoDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpFlujoHallazgo;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpHallazgo;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;
import com.premize.sgp.modelo.entities.parametros.SgpUsuario;
import com.premize.sgp.service.pruebas.FlujoHallazgoService;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class FlujoHallazgoServiceImpl
 * @since 3/03/2014
 */
@Service
public class FlujoHallazgoServiceImpl extends AbstractEntityService<SgpFlujoHallazgo, Integer> implements
		FlujoHallazgoService {
	
	private static Integer ACTIVO = 1;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private SgpParametroDao parametroDao;
	
	@Autowired
	private FlujoHallazgoDao flujoHallazgoDao;
	
	@Autowired
	private HallazgoDao hallazgoDao;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @return
	 * @throws AppBaseException 
	 * @throws Exception
	 * @since 3/03/2014
	 * @see com.premize.sgp.service.pruebas.FlujoHallazgoService#guardarFlujoHallazgo(com.premize.sgp.dto.pruebas.FlujoHallazgoDTO)
	 */
	@Override
	public void guardarFlujoHallazgo(FlujoHallazgoDTO flujoHallazgo, SgpHallazgo hallazgo,
			SgpFlujoHallazgo sgpFlujoHallazgo) throws AppBaseException {
		
			SgpUsuario usuarioAsigna = usuarioDao.getUsuarioByLogin(flujoHallazgo.getUsuarioCreacion());
			SgpUsuario usuarioAsignado = usuarioDao.get(flujoHallazgo.getUsuarioAsignado().getId());
			SgpParametro estadoInicial = parametroDao.get(19);
			
			sgpFlujoHallazgo.setSgpHallazgo(hallazgo);
			sgpFlujoHallazgo.setUsuarioAsigna(usuarioAsigna);
			sgpFlujoHallazgo.setUsuarioAsignado(usuarioAsignado);
			sgpFlujoHallazgo.setAccion(estadoInicial);
			sgpFlujoHallazgo.setObservacion(hallazgo.getDescripcion());
			sgpFlujoHallazgo.setIndActivo(ACTIVO);
			sgpFlujoHallazgo.setUsuarioCrea(flujoHallazgo.getUsuarioCreacion());
			sgpFlujoHallazgo.setFecCreacion(Calendar.getInstance().getTime());
			flujoHallazgoDao.save(sgpFlujoHallazgo);
			flujoHallazgo.setId(sgpFlujoHallazgo.getId());
			
		
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 3/03/2014
	 * @see com.premize.pmz.service.AbstractEntityService#getDao()
	 */
	@Override
	public Dao<SgpFlujoHallazgo, Integer> getDao() {
		
		return flujoHallazgoDao;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 10/03/2014
	 * @see com.premize.sgp.service.pruebas.FlujoHallazgoService#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<FlujoHallazgoDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		return flujoHallazgoDao.getRecords(criteria);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 25/03/2014
	 * @see com.premize.sgp.service.pruebas.FlujoHallazgoService#editarHallazgo(com.premize.sgp.dto.pruebas.FlujoHallazgoDTO, com.premize.sgp.modelo.entities.gestion.pruebas.SgpFlujoHallazgo)
	 */
	@Override
	public void editarHallazgo(FlujoHallazgoDTO flujoHallazgo, SgpFlujoHallazgo sgpFlujoHallazgo) throws Exception {
		SgpUsuario usuarioAsignado=null;
		try {
			SgpHallazgo hallazgo = hallazgoDao.get(flujoHallazgo.getHallazgo().getId());
			SgpUsuario usuarioAsigna = usuarioDao.getUsuarioByLogin(flujoHallazgo.getUsuarioCreacion());
			if(null!=flujoHallazgo.getUsuarioAsignado().getId()){
				 usuarioAsignado = usuarioDao.get(flujoHallazgo.getUsuarioAsignado().getId());
			}
			SgpParametro estadoHallazgo = null;
			
			if(flujoHallazgo.getEstadoHallazgo() != null)
			estadoHallazgo = parametroDao.get(flujoHallazgo.getEstadoHallazgo().getId());
			else 
			{
				SgpFlujoHallazgo flujoHallazgoAnterior = flujoHallazgoDao.obtenerFlujoPorHallazgo(flujoHallazgo.getHallazgo().getId());
				estadoHallazgo = parametroDao.get(flujoHallazgoAnterior.getAccion().getId());				
			}

			if ( flujoHallazgo.getHallazgo().getMotivoReasignacion() != null ) {
				SgpParametro motivo = parametroDao.get(flujoHallazgo.getHallazgo().getMotivoReasignacion().getId());
				hallazgo.setMotivoReasignacion(motivo);

			}

			if (flujoHallazgo.getHallazgo().getCausaAnulacion() != null) {
				SgpParametro causaAnula = parametroDao.get(flujoHallazgo.getHallazgo().getCausaAnulacion().getId());
				hallazgo.setCausaAnulacion(causaAnula);
				hallazgo.setFecEdita(Calendar.getInstance().getTime());
			}
			
			//hallazgo.setDescripcion(flujoHallazgo.getObservacion());
			//hallazgoDao.update(hallazgo);
			sgpFlujoHallazgo.setSgpHallazgo(hallazgo);
			sgpFlujoHallazgo.setUsuarioAsigna(usuarioAsigna);
			if(null!=usuarioAsignado){
				sgpFlujoHallazgo.setUsuarioAsignado(usuarioAsignado);
			}
			sgpFlujoHallazgo.setAccion(estadoHallazgo);
			sgpFlujoHallazgo.setObservacion(flujoHallazgo.getObservacion());
			sgpFlujoHallazgo.setIndActivo(ACTIVO);
			sgpFlujoHallazgo.setUsuarioCrea(flujoHallazgo.getUsuarioCreacion());
			sgpFlujoHallazgo.setFecCreacion(Calendar.getInstance().getTime());
			flujoHallazgoDao.save(sgpFlujoHallazgo);
			flujoHallazgo.setId(sgpFlujoHallazgo.getId());
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 25/03/2014
	 * @see com.premize.sgp.service.pruebas.FlujoHallazgoService#getFlujoHallazgo(java.lang.Integer)
	 */
	@Override
	public FlujoHallazgoDTO getFlujoHallazgo(Integer idFlujo)
			throws AppBaseException {
		SgpFlujoHallazgo sgpFlujoHallazgo = flujoHallazgoDao.get(idFlujo);
		return new FlujoHallazgoDTO(sgpFlujoHallazgo);
	}

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 25/03/2014
	 * @see com.premize.sgp.service.pruebas.FlujoHallazgoService#editarFlujoHallazgo(com.premize.sgp.dto.pruebas.FlujoHallazgoDTO)
	 */
	@Override
	public void editarFlujoHallazgo(FlujoHallazgoDTO flujoHallazgoDTO)
			throws AppBaseException {
		
		SgpFlujoHallazgo sgpFlujoHallazgo = flujoHallazgoDao.get(flujoHallazgoDTO.getId());
		sgpFlujoHallazgo.setObservacion(flujoHallazgoDTO.getObservacion());
		flujoHallazgoDao.update(sgpFlujoHallazgo);
		
	}

}
