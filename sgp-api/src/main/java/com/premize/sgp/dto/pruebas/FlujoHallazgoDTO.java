/**
 * 
 */
package com.premize.sgp.dto.pruebas;

import com.premize.sgp.dto.BaseEntity;
import com.premize.sgp.dto.parametros.ParametroDTO;
import com.premize.sgp.dto.parametros.UsuarioDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpFlujoHallazgo;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-api
 * @class FlujoHallazgoDTO
 * @since 24/02/2014
 */
public class FlujoHallazgoDTO extends BaseEntity {
	
	private static final long serialVersionUID = -4856569151604953269L;
	
	private Integer id;
	private HallazgoDTO hallazgo;
	private UsuarioDTO usuarioAsigna;
	private UsuarioDTO usuarioAsignado;
	private String observacion;
	private ParametroDTO estadoHallazgo;
	
	public FlujoHallazgoDTO(HallazgoDTO hallazgo, UsuarioDTO usuarioAsignado) {
		this.hallazgo = hallazgo;
		this.usuarioAsignado = usuarioAsignado;
	}
	
	public FlujoHallazgoDTO() {
		super();
	}
	
	public FlujoHallazgoDTO(SgpFlujoHallazgo sgpFlujoHallazgo) {
		this.id = sgpFlujoHallazgo.getId();
		this.hallazgo = new HallazgoDTO(sgpFlujoHallazgo.getSgpHallazgo());
		if(sgpFlujoHallazgo.getUsuarioAsigna() != null){
		this.usuarioAsigna = new UsuarioDTO(sgpFlujoHallazgo.getUsuarioAsigna());
		}
		
		if(null != sgpFlujoHallazgo.getUsuarioAsignado()){
			this.usuarioAsignado = new UsuarioDTO(sgpFlujoHallazgo.getUsuarioAsignado());
		}
		this.observacion = sgpFlujoHallazgo.getObservacion();
		this.estadoHallazgo = new ParametroDTO(sgpFlujoHallazgo.getAccion());
		setIndActivo(sgpFlujoHallazgo.getIndActivo());
		setNumeroEstado(sgpFlujoHallazgo.getIndActivo());
		setUsuarioCreacion(sgpFlujoHallazgo.getUsuarioCrea());
		setFecCreacion(sgpFlujoHallazgo.getFecCreacion());
		if (sgpFlujoHallazgo.getFecEdita() != null) {
			setFechaEdita(sgpFlujoHallazgo.getFecEdita());
		}
		if (sgpFlujoHallazgo.getUsuarioEdita() != null) {
			setUsuarioEdita(sgpFlujoHallazgo.getUsuarioEdita());
		}
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @return the hallazgo
	 */
	public HallazgoDTO getHallazgo() {
		return hallazgo;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @param hallazgo
	 *            the hallazgo to set
	 */
	public void setHallazgo(HallazgoDTO hallazgo) {
		this.hallazgo = hallazgo;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @return the usuarioAsigna
	 */
	public UsuarioDTO getUsuarioAsigna() {
		return usuarioAsigna;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @param usuarioAsigna
	 *            the usuarioAsigna to set
	 */
	public void setUsuarioAsigna(UsuarioDTO usuarioAsigna) {
		this.usuarioAsigna = usuarioAsigna;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @return the usuarioAsignado
	 */
	public UsuarioDTO getUsuarioAsignado() {
		return usuarioAsignado;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @param usuarioAsignado
	 *            the usuarioAsignado to set
	 */
	public void setUsuarioAsignado(UsuarioDTO usuarioAsignado) {
		this.usuarioAsignado = usuarioAsignado;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @param observacion
	 *            the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 9/03/2014
	 * @return the estadoHallazgo
	 */
	public ParametroDTO getEstadoHallazgo() {
		return estadoHallazgo;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 9/03/2014
	 * @param estadoHallazgo
	 *            the estadoHallazgo to set
	 */
	public void setEstadoHallazgo(ParametroDTO estadoHallazgo) {
		this.estadoHallazgo = estadoHallazgo;
	}
	
}
