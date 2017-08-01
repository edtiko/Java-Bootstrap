/**
 * 
 */
package com.premize.sgp.dto.pruebas;

import com.premize.sgp.dto.BaseEntity;
import com.premize.sgp.dto.parametros.UsuarioDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpUsuarioProyecto;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-api
 * @class UsuarioProyectoDTO
 * @since 17/02/2014
 */
public class UsuarioProyectoDTO extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8314817092147987371L;
	
	private Integer id;
	private UsuarioDTO usuario;
	private ProyectoDTO proyecto;
	
	public UsuarioProyectoDTO() {
		super();
	}
	
	public UsuarioProyectoDTO(SgpUsuarioProyecto sgpUsuarioProyecto) {
		
		this.id = sgpUsuarioProyecto.getId();
		setUsuario(new UsuarioDTO(sgpUsuarioProyecto.getSgpUsuario()));
		setProyecto(new ProyectoDTO(sgpUsuarioProyecto.getSgpProyecto()));
		setIndActivo(sgpUsuarioProyecto.getIndActivo());
		setNumeroEstado(sgpUsuarioProyecto.getIndActivo());
		setFecCreacion(sgpUsuarioProyecto.getFecCreacion());
		setUsuarioCreacion(sgpUsuarioProyecto.getUsuarioCrea());
		if (sgpUsuarioProyecto.getFecEdita() != null) {
			setFechaEdita(sgpUsuarioProyecto.getFecEdita());
		}
		if (sgpUsuarioProyecto.getUsuarioEdita() != null) {
			setUsuarioEdita(sgpUsuarioProyecto.getUsuarioEdita());
		}
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 17/02/2014
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 17/02/2014
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 17/02/2014
	 * @return the usuario
	 */
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 17/02/2014
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 17/02/2014
	 * @return the proyecto
	 */
	public ProyectoDTO getProyecto() {
		return proyecto;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 17/02/2014
	 * @param proyecto
	 *            the proyecto to set
	 */
	public void setProyecto(ProyectoDTO proyecto) {
		this.proyecto = proyecto;
	}
	
}
