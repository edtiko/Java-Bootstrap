/**
 * 
 */
package com.premize.sgp.facade.parametros.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.facade.AbstractEntityFacade;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.ArtefactoDTO;
import com.premize.sgp.dto.parametros.MapaArtefactoDTO;
import com.premize.sgp.facade.parametros.ArtefactoFacade;
import com.premize.sgp.modelo.entities.parametros.SgpArtefacto;
import com.premize.sgp.service.parametros.ArtefactoService;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-web
 * @class ArtefactoFacadeImpl
 * @since 21/01/2014
 */
@Service("artefactoFacade")
public class ArtefactoFacadeImpl extends AbstractEntityFacade<SgpArtefacto, Integer> implements ArtefactoFacade {
	
	@Autowired
	private ArtefactoService artefactoService;
	
	@Override
	public EntityService<SgpArtefacto, Integer> getEntityService() {
		return artefactoService;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @see com.premize.sgp.facade.parametros.ArtefactoFacade#guardar(com.premize.sgp.modelo.entities.parametros.SgpArtefacto)
	 */
	@Override
	public void guardar(ArtefactoDTO pArtefacto) throws AppBaseException {
		
		artefactoService.guardar(pArtefacto);
		
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @see com.premize.sgp.facade.parametros.ArtefactoFacade#actualizar(com.premize.sgp.modelo.entities.parametros.SgpArtefacto)
	 */
	@Override
	public void actualizar(ArtefactoDTO pArtefacto) throws AppBaseException {
		
		artefactoService.actualizar(pArtefacto);
		
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 28/01/2014
	 * @see com.premize.sgp.facade.parametros.ArtefactoFacade#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<ArtefactoDTO> getRecords(PagingCriteria criteria) throws Exception {
		return artefactoService.getRecords(criteria);
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 28/01/2014
	 * @see com.premize.sgp.facade.parametros.ArtefactoFacade#obtenerArtefacto(java.lang.Integer)
	 */
	@Override
	public ArtefactoDTO obtenerArtefacto(Integer idArtefacto) throws Exception {
		return artefactoService.obtenerArtefacto(idArtefacto);
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 8/02/2014
	 * @see com.premize.sgp.facade.parametros.ArtefactoFacade#obtenerArtefactoPorMapaPrueba(Integer)
	 */
	@Override
	public List<ArtefactoDTO> obtenerArtefactoPorMapaPrueba(Integer mapaPrueba) throws Exception {
		return artefactoService.obtenerArtefactosPorMapaPrueba(mapaPrueba);
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 8/02/2014
	 * @see com.premize.sgp.facade.parametros.ArtefactoFacade#getArtefactos()
	 */
	@Override
	public List<ArtefactoDTO> getArtefactos() throws Exception {
		return artefactoService.getArtefactos();
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 11/02/2014
	 * @see com.premize.sgp.facade.parametros.ArtefactoFacade#artefactosSinAsociarAMapa(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@Override
	public List<ArtefactoDTO> artefactosSinAsociarAMapa(Integer idProyecto, Integer idMapaPrueba) throws Exception {
		return artefactoService.artefactosSinAsociarAMapa(idProyecto, idMapaPrueba);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.parametros.ArtefactoFacade#getMapaArtefactos(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<MapaArtefactoDTO> getMapaArtefactos(PagingCriteria criteria) throws Exception {
		return artefactoService.getMapaArtefactos(criteria);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 7/03/2014
	 * @see com.premize.sgp.facade.parametros.ArtefactoFacade#eliminarArtefacto(java.lang.Integer)
	 */
	@Override
	public void eliminarArtefacto(Integer idArtefacto) throws AppBaseException {
		
		artefactoService.eliminarArtefacto(idArtefacto);
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.facade.parametros.ArtefactoFacade#obtenerArtefactosPorProyecto(java.lang.Integer)
	 */
	@Override
	public List<ArtefactoDTO> obtenerArtefactosPorProyecto(Integer idProyecto)
			throws AppBaseException {
		
		List<ArtefactoDTO> sgpArtefactos= artefactoService.obtenerArtefactosPorProyecto(idProyecto);
		
		return sgpArtefactos;
	}
	
}
