/**
 * 
 */
package com.premize.sgp.dto.parametros;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.premize.sgp.dto.BaseEntity;
import com.premize.sgp.modelo.entities.parametros.SgpTipoParametro;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-api
 * @class TipoParametroDTO
 * @since 21/01/2014
 */
public class TipoParametroDTO extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5989567016128357910L;
	
	private Integer id;
	private String descripcion;
	
	@NotEmpty
	@Size(min = 1, max = 100)
	private String nombre;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 21/01/2014
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 21/01/2014
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 21/01/2014
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 21/01/2014
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 21/01/2014
	 */
	public TipoParametroDTO() {
		super();
	}
	
	public TipoParametroDTO(String descripcion) {
		super();
		this.descripcion = descripcion;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 28/01/2014
	 * @param sgpTipoParametro
	 */
	public TipoParametroDTO(SgpTipoParametro tipoParametro) {
		this.id = tipoParametro.getId();
		this.descripcion = tipoParametro.getDescripcion();
		this.nombre = tipoParametro.getNombre();
		setNumeroEstado(tipoParametro.getIndActivo());
		setIndActivo(tipoParametro.getIndActivo());
		setUsuarioCreacion(tipoParametro.getUsuarioCrea());
		setFecCreacion(tipoParametro.getFecCreacion());
		if (tipoParametro.getFecEdita() != null) {
			setFechaEdita(tipoParametro.getFecEdita());
		}
		if (tipoParametro.getUsuarioEdita() != null) {
			setUsuarioEdita(tipoParametro.getUsuarioEdita());
		}
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 29/01/2014
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 29/01/2014
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
