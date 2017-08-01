/**
 * 
 */
package com.premize.sgp.service.parametros.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.Dao;
import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.service.AbstractEntityService;
import com.premize.sgp.dao.gestion.pruebas.MapaArtefactoDao;
import com.premize.sgp.dao.parametros.ArtefactoDao;
import com.premize.sgp.dao.parametros.ProyectoDao;
import com.premize.sgp.dao.parametros.TipoArtefactoDao;
import com.premize.sgp.dao.parametros.UsuarioDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.ArtefactoDTO;
import com.premize.sgp.dto.parametros.MapaArtefactoDTO;
import com.premize.sgp.modelo.entities.artefacto.SgpMapaArtefacto;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpProyecto;
import com.premize.sgp.modelo.entities.parametros.SgpArtefacto;
import com.premize.sgp.modelo.entities.parametros.SgpTipoArtefacto;
import com.premize.sgp.modelo.entities.parametros.SgpUsuario;
import com.premize.sgp.service.parametros.ArtefactoService;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-service
 * @class ArtefactoServiceImpl
 * @since 20/01/2014
 */
@Service
public class ArtefactoServiceImpl extends AbstractEntityService<SgpArtefacto, Integer> implements ArtefactoService {
	
	/**
	 * 
	 */
	private static final Integer ACTIVO = 1;
	@Autowired
	private ArtefactoDao artefactoDao;
	
	@Autowired
	private TipoArtefactoDao tipoArtefactoDao;
	
	@Autowired
	private ProyectoDao proyectoDao;
	
	@Autowired
	private MapaArtefactoDao mapaArtefactoDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	public Dao<SgpArtefacto, Integer> getDao() {
		return artefactoDao;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @see com.premize.sgp.service.parametros.ArtefactoService#guardar(com.premize.sgp.modelo.entities.parametros.SgpArtefacto)
	 */
	@Override
	public void guardar(ArtefactoDTO pArtefacto) throws AppBaseException {
		SgpTipoArtefacto tipoArtefacto = tipoArtefactoDao.get(pArtefacto.getTipoArtefacto().getId());
		SgpProyecto proyecto = proyectoDao.get(pArtefacto.getProyecto().getId());

		SgpUsuario usuario = null;
		if(pArtefacto.getUsuario().getId() != null) {
			usuario = usuarioDao.get(pArtefacto.getUsuario().getId());
		}

		SgpArtefacto artefacto = new SgpArtefacto();
		artefacto.setNombre(pArtefacto.getNombre());
		artefacto.setDescripcion(pArtefacto.getDescripcion());
		artefacto.setSgpTipoArtefacto(tipoArtefacto);
		artefacto.setProyecto(proyecto);
		artefacto.setIndActivo(pArtefacto.getNumeroEstado());
		artefacto.setFecCreacion(Calendar.getInstance().getTime());
		artefacto.setUsuarioCrea(pArtefacto.getUsuarioCreacion());
		artefacto.setUsuario(usuario);

		artefactoDao.save(artefacto);
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @see com.premize.sgp.service.parametros.ArtefactoService#actualizar(com.premize.sgp.modelo.entities.parametros.SgpArtefacto)
	 */
	@Override
	public void actualizar(ArtefactoDTO pArtefacto) throws AppBaseException {
		SgpArtefacto artefacto = artefactoDao.get(pArtefacto.getId());
		SgpTipoArtefacto tipoArtefacto = tipoArtefactoDao.get(pArtefacto.getTipoArtefacto().getId());

		SgpUsuario usuario = null;
		if(pArtefacto.getUsuario().getId() != null) {
			usuario = usuarioDao.get(pArtefacto.getUsuario().getId());
		}

		artefacto.setNombre(pArtefacto.getNombre());
		artefacto.setDescripcion(pArtefacto.getDescripcion());
		artefacto.setSgpTipoArtefacto(tipoArtefacto);
		artefacto.setIndActivo(pArtefacto.getNumeroEstado());
		artefacto.setFecEdita(Calendar.getInstance().getTime());
		artefacto.setUsuarioEdita(pArtefacto.getUsuarioEdita());
		artefacto.setUsuario(usuario);
		artefactoDao.update(artefacto);
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 28/01/2014
	 * @see com.premize.sgp.service.parametros.ArtefactoService#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<ArtefactoDTO> getRecords(PagingCriteria criteria) throws Exception {
		
		return artefactoDao.getRecords(criteria);
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 28/01/2014
	 * @see com.premize.sgp.service.parametros.ArtefactoService#obtenerArtefacto(java.lang.Integer)
	 */
	@Override
	public ArtefactoDTO obtenerArtefacto(Integer idArtefacto) throws Exception {
		
		SgpArtefacto sgpArtefacto = artefactoDao.get(idArtefacto);
		ArtefactoDTO artefactoDTO = new ArtefactoDTO(sgpArtefacto);
		return artefactoDTO;
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 8/02/2014
	 * @see com.premize.sgp.service.parametros.ArtefactoService#obtenerArtefactosPorMapaPrueba(Integer)
	 */
	@Override
	public List<ArtefactoDTO> obtenerArtefactosPorMapaPrueba(Integer mapaPrueba) throws Exception {
		
		return artefactoDao.obtenerArtefactosPorMapaPrueba(mapaPrueba);
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 8/02/2014
	 * @see com.premize.sgp.service.parametros.ArtefactoService#getArtefactos()
	 */
	@Override
	public List<ArtefactoDTO> getArtefactos() throws Exception {
		List<SgpArtefacto> listArtefactos = artefactoDao.findAllEntries(Paginator.getDefault());
		List<ArtefactoDTO> listDTO = new ArrayList<ArtefactoDTO>();
		for (SgpArtefacto sgpArtefacto : listArtefactos) {
			if (sgpArtefacto.getIndActivo() == ACTIVO) {
				ArtefactoDTO nuevoArtefacto = new ArtefactoDTO();
				nuevoArtefacto.setNombre(sgpArtefacto.getNombre());
				nuevoArtefacto.setDescripcion(sgpArtefacto.getDescripcion());
				nuevoArtefacto.setId(sgpArtefacto.getId());
				
				listDTO.add(nuevoArtefacto);
			}
		}
		return listDTO;
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 11/02/2014
	 * @see com.premize.sgp.service.parametros.ArtefactoService#artefactosSinAsociarAMapa(java.lang.Integer,
	 *      java.lang.Integer)
	 */
	@Override
	public List<ArtefactoDTO> artefactosSinAsociarAMapa(Integer idProyecto, Integer idMapaPrueba) throws Exception {
		return artefactoDao.artefactosSinAsociarAMapa(idProyecto, idMapaPrueba);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.parametros.ArtefactoService#getMapaArtefactos(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<MapaArtefactoDTO> getMapaArtefactos(PagingCriteria criteria) throws AppBaseException {
		
		return mapaArtefactoDao.getMapaArtefactos(criteria);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 7/03/2014
	 * @see com.premize.sgp.service.parametros.ArtefactoService#eliminarArtefacto(java.lang.Integer)
	 */
	@Override
	public void eliminarArtefacto(Integer idArtefacto) throws AppBaseException {
		
		// verifica si tiene mapas de prueba relacionados
				List<SgpMapaArtefacto> mapaArtefactos = mapaArtefactoDao.getMapaArtefactoByArtefacto(idArtefacto);
				if (!mapaArtefactos.isEmpty()) {
					throw new AppBaseException("No se puede eliminar: Existen mapas de prueba relacionados.", "ARTEFACTO_ASOCIADO");
				}
		artefactoDao.deleteById(idArtefacto);
	}
/*
 * (non-Javadoc)
 * @see com.premize.sgp.service.parametros.ArtefactoService#obtenerArtefactosPorProyecto(java.lang.Integer)
 */
	@Override
	public List<ArtefactoDTO> obtenerArtefactosPorProyecto(Integer idProyecto)throws AppBaseException {
		
		List<ArtefactoDTO> artefactoDTOs=new ArrayList<ArtefactoDTO>();
		
		List<SgpArtefacto> sgpArtefactos=artefactoDao.obtenerArtefactosPorProyecto(idProyecto);
		
		for (SgpArtefacto sgpArtefacto : sgpArtefactos) {
			ArtefactoDTO artefactoDTO=new ArtefactoDTO(sgpArtefacto);
			artefactoDTOs.add(artefactoDTO);
			
		}
		return artefactoDTOs;
	}
	
}