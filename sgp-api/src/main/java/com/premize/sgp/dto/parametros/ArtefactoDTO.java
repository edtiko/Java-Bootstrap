/**
 * 
 */
package com.premize.sgp.dto.parametros;

import javax.validation.constraints.Size;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.NotEmpty;

import com.premize.sgp.dto.BaseEntity;
import com.premize.sgp.dto.pruebas.ProyectoDTO;
import com.premize.sgp.modelo.entities.parametros.SgpArtefacto;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-modelo
 * @class ArtefactoDTO
 * @since 23/01/2014
 */
public class ArtefactoDTO extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2221251812780660562L;
	@JsonSerialize
	private Integer id;
	private TipoArtefactoDTO tipoArtefacto;
	
	@NotEmpty
	@Size(min = 1, max = 100)
	private String nombre;
	
	private String descripcion;
	private ProyectoDTO proyecto;
	@JsonSerialize
	private UsuarioDTO usuario;
	
	public ArtefactoDTO() {
		super();
	}
	
	public ArtefactoDTO(SgpArtefacto sgpArtefacto) {
		if (sgpArtefacto != null) {
			this.id = sgpArtefacto.getId();
			this.nombre = sgpArtefacto.getNombre();
			this.descripcion = sgpArtefacto.getDescripcion();
			setProyecto(new ProyectoDTO(sgpArtefacto.getProyecto()));
			setIndActivo(sgpArtefacto.getIndActivo());
			setNumeroEstado(sgpArtefacto.getIndActivo());
			this.tipoArtefacto = new TipoArtefactoDTO(sgpArtefacto.getSgpTipoArtefacto());
			setFecCreacion(sgpArtefacto.getFecCreacion());
			setUsuarioCreacion(sgpArtefacto.getUsuarioCrea());
			if (sgpArtefacto.getFecEdita() != null) {
				setFechaEdita(sgpArtefacto.getFecEdita());
			}
			if (sgpArtefacto.getUsuarioEdita() != null) {
				setUsuarioEdita(sgpArtefacto.getUsuarioEdita());
			}
			
			if(sgpArtefacto.getUsuario() != null) {
				usuario = new UsuarioDTO(sgpArtefacto.getUsuario());
			}
			else{
				usuario = new UsuarioDTO();
				usuario.setNombre(" ");
			}
		}
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @return the idArtefacto
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @param idArtefacto
	 *            the idArtefacto to set
	 */
	public void setId(Integer idArtefacto) {
		this.id = idArtefacto;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @return the tipoArtefactoDto
	 */
	public TipoArtefactoDTO getTipoArtefacto() {
		return tipoArtefacto;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @param tipoArtefactoDto
	 *            the tipoArtefactoDto to set
	 */
	public void setTipoArtefacto(TipoArtefactoDTO tipoArtefactoDto) {
		this.tipoArtefacto = tipoArtefactoDto;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 28/01/2014
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 13/02/2014
	 * @return the proyecto
	 */
	public ProyectoDTO getProyecto() {
		return proyecto;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 13/02/2014
	 * @param proyecto
	 *            the proyecto to set
	 */
	public void setProyecto(ProyectoDTO proyecto) {
		this.proyecto = proyecto;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 17/06/2014 
	 * @return the usuario 
	 */
	public UsuarioDTO getUsuario() {
		return usuario;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 17/06/2014 
	 * @param usuario the usuario to set 
	 */
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	
	
}
