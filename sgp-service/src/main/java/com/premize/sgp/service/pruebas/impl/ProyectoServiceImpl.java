/**
 * 
 */
package com.premize.sgp.service.pruebas.impl;

import com.premize.sgp.dto.ResultSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.api.util.Filter;
import com.premize.pmz.service.AbstractEntityService;
import com.premize.sgp.dao.parametros.EmpresaDao;
import com.premize.sgp.dao.parametros.ProyectoDao;
import com.premize.sgp.dao.parametros.SgpParametroDao;
import com.premize.sgp.dao.parametros.UsuarioDao;
import com.premize.sgp.dao.usuarios.UsuarioProyectoDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.pruebas.ProyectoDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpProyecto;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpUsuarioProyecto;
import com.premize.sgp.modelo.entities.parametros.SgpEmpresa;
import com.premize.sgp.modelo.entities.parametros.SgpUsuario;
import com.premize.sgp.service.pruebas.ProyectoService;
import com.premize.sgp.utils.ListUtils;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class ParametroServiceImpl
 * @since 15/01/2014
 *
 */
@Service
public class ProyectoServiceImpl extends AbstractEntityService<SgpProyecto, Integer> implements ProyectoService {

	private final Integer ACTIVO = 1;
	//private final Integer INACTIVO = 0;

	@Autowired
	private ProyectoDao proyectoDao;

	@Autowired
	private EmpresaDao EmpresaDao;

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private UsuarioProyectoDao usuarioProyectoDao;
	
	@Autowired
	private SgpParametroDao parametroDao;

	@Override
	public ProyectoDao getDao() {
		return proyectoDao;
	}
	
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException 
	 * @since 15/01/2014
	 * @see com.premize.sgp.service.ParametroService#getParametros()
	 */
	@Override
	public List<ProyectoDTO> getListProyectos() throws AppBaseException {

		List<ProyectoDTO> resultList = new ArrayList<ProyectoDTO>();
		List<SgpProyecto> parametros = proyectoDao.getListProyectos();
		for (SgpProyecto sgpProyecto: parametros) {
			ProyectoDTO ProyectoDTO = new ProyectoDTO(sgpProyecto);

			resultList.add(ProyectoDTO);
		}

		return resultList;
	}


	public void guardarProyecto(ProyectoDTO proyecto) throws AppBaseException{

		SgpProyecto sgpProyecto = new SgpProyecto();
		SgpEmpresa sgpEmpresa = EmpresaDao.get(proyecto.getEmpresa().getId());
		
		sgpProyecto.setDescripcion(proyecto.getDescripcion());
		sgpProyecto.setNombre(proyecto.getNombre());
		sgpProyecto.setSgpEmpresa(sgpEmpresa);
		sgpProyecto.setIndActivo(proyecto.getNumeroEstado());
		sgpProyecto.setUsuarioCrea(proyecto.getUsuarioCreacion());
		sgpProyecto.setFecCreacion (Calendar.getInstance().getTime());
		proyectoDao.save(sgpProyecto);

	}


	@Override
	public List<SgpProyecto> findAllProyectoByFilter(Filter[] filters,
			Paginator paginator) {
		return null;
	}


	@Override
	public ResultSet<ProyectoDTO> getRecords(PagingCriteria criteria,Boolean mostraActivoInactivo) throws AppBaseException{

		return proyectoDao.getRecords(criteria,mostraActivoInactivo);
	}


	@Override
	public void editarProyecto(ProyectoDTO proyecto) throws AppBaseException {

		SgpProyecto sgpProyecto = proyectoDao.get(proyecto.getId());
		SgpEmpresa sgpEmpresa = EmpresaDao.get(proyecto.getEmpresa().getId());
		
		sgpProyecto.setDescripcion(proyecto.getDescripcion());
		sgpProyecto.setNombre(proyecto.getNombre());
		sgpProyecto.setSgpEmpresa(sgpEmpresa);
		sgpProyecto.setIndActivo(proyecto.getNumeroEstado());
		sgpProyecto.setUsuarioEdita(proyecto.getUsuarioEdita());
		sgpProyecto.setFecEdita(Calendar.getInstance().getTime());
		proyectoDao.update(sgpProyecto);

	}


	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException 
	 * @since 28/01/2014
	 * @see com.premize.sgp.service.ParametroService#getParametro(java.lang.Integer)
	 */
	@Override
	public ProyectoDTO getProyecto(Integer idProyecto ) throws AppBaseException {

		ProyectoDTO proyectoDTO = null;
		if(idProyecto != null){
		SgpProyecto sgpProyecto = proyectoDao.get(idProyecto);
		proyectoDTO = new ProyectoDTO(sgpProyecto);
		}
		
		return proyectoDTO;
	}


	@Override
	public List<ProyectoDTO> consultarProyectoPorEmpresa(Integer idEmpresa,
			Paginator page) throws AppBaseException {
		return  getDao().consultarProyectoPorEmpresa(idEmpresa, page);
	}


	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 14/02/2014
	 * @see com.premize.sgp.service.pruebas.ProyectoService#actualizarUsuariosPorProyecto(java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	public void actualizarUsuariosPorProyecto(List<String> usuarios,
			String idProyecto, String login) throws AppBaseException {

		Integer proyectoId = new Integer(idProyecto);
		SgpProyecto proyecto = proyectoDao.get(proyectoId);
		
		List<SgpUsuarioProyecto> usuariosProyecto = usuarioProyectoDao
				.consultarUsuariosAsociados(proyectoId);
		
		List<Integer> usuariosDesasociados = new ArrayList<Integer>();
		
		/*
		 * Buscamos si hay usuarios desasociados, si hay los agregamos
		 * a la lista usuariosDesasociados y los eliminamos de la lista usuariosProyecto.
		 */
		for(int i = 0; i < usuariosProyecto.size(); i++) {
			SgpUsuarioProyecto sgpUP = usuariosProyecto.get(i);
			if(!usuarios.contains(sgpUP.getSgpUsuario().getId().toString())) {
				usuariosDesasociados.add(sgpUP.getId());
				usuariosProyecto.remove(i);
				--i;
			}
		}
		
		//Desasociamos los usuarios que no se encuentran en la lista usuarios
		if(!usuariosDesasociados.isEmpty()) {
			usuarioProyectoDao.desasociarUsuariosProyecto(proyectoId, usuariosDesasociados, login);
		}		
		
		for(String usuarioId : usuarios) {
			Integer idUsuario = Integer.parseInt(usuarioId);
			if(usuariosProyecto.isEmpty()) {
				usuarioProyectoDao.save(createUsuarioProyecto(idUsuario, proyecto, login));
			} else {
				for(int i = 0; i < usuariosProyecto.size(); i++) {
					SgpUsuarioProyecto usuarioProyecto = usuariosProyecto.get(i);
					SgpUsuario user = usuarioProyecto.getSgpUsuario();
					if(user.getId() != idUsuario) {
						if((i+1) == usuariosProyecto.size()) {
							SgpUsuarioProyecto up = createUsuarioProyecto(idUsuario, proyecto, login);
							usuarioProyectoDao.save(up);
							break;
						}
					} else {
						if(usuarioProyecto.getIndActivo() != ACTIVO) {
							usuarioProyecto.setIndActivo(ACTIVO);
							usuarioProyecto.setFecEdita(Calendar.getInstance().getTime());
							usuarioProyecto.setUsuarioEdita(login);
							usuarioProyectoDao.update(usuarioProyecto);
						}
						break;
					}
				}
			}
			
		}
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 24/06/2014
	 * @description 
	 * @param idUsuario
	 * @param proyecto
	 * @param login
	 * @return
	 * @throws AppBaseException
	 */
	private SgpUsuarioProyecto createUsuarioProyecto(Integer idUsuario, SgpProyecto proyecto, String login) 
			throws AppBaseException {
		SgpUsuarioProyecto up = new SgpUsuarioProyecto();
		SgpUsuario usuario = usuarioDao.get(idUsuario);
		up.setFecCreacion(Calendar.getInstance().getTime());
		up.setIndActivo(ACTIVO);
		up.setSgpProyecto(proyecto);
		up.setSgpUsuario(usuario);
		up.setUsuarioCrea(login);
		usuarioProyectoDao.save(up);
		return up;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 2/05/2014
	 * @description 
	 * @param proyectoNombre
	 * @return
	 * @throws AppBaseException
	 */
	@Override
	public String construirPrefijoProyecto(String proyectoNombre) throws AppBaseException {
		if(proyectoNombre == null || proyectoNombre.isEmpty()) {
			throw new AppBaseException("El nombre del proyecto es nulo","NO_PARAMETRO");
		}
		
		if(proyectoNombre.length() < SIZE_PROYECTO_PREFIJO) {
			throw new AppBaseException("El nombre del proyecto debe tener mas de 3 caracteres","SIZE_NOMBRE_PROYECTO");
		}
		
		String prefijo = proyectoNombre.substring(0, SIZE_PROYECTO_PREFIJO);
		return prefijo.toUpperCase();
	}

}
