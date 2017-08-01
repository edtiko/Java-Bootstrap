/**
 * 
 */
package com.premize.sgp.dto.parametros;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.premize.sgp.dto.BaseEntity;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-api
 * @class ParametroDTO
 * @since 21/01/2014
 */
public class ParametroDTO extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -349018818090205525L;
	
	@JsonSerialize
	private Integer id;
	@Valid
	private TipoParametroDTO tipoParametro;
	
	@NotNull
	@Size(min = 1, max = 255)
	private String nombre;
	private String descripcion;
	
	@NotNull
	private String valor;
	
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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 21/01/2014
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 21/01/2014
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 21/01/2014
	 */
	public ParametroDTO() {
		
	}
	
	/**
	 * customized constructor
	 */
	public ParametroDTO(Integer id) {
		super();
		this.id = id;
	}
	
	/**
	 * customized constructor
	 */
	public ParametroDTO(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 21/01/2014
	 * @param descripcion
	 * @param nombre
	 * @param valor
	 */
	public ParametroDTO(String descripcion, String nombre, String valor) {
		super();
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.valor = valor;
	}
	
	public ParametroDTO(String descripcion, String nombre, String valor, TipoParametroDTO tipoParametro, Integer id) {
		super();
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.valor = valor;
		this.tipoParametro = tipoParametro;
		this.id = id;
	}
	
	public ParametroDTO(SgpParametro parametro) {
		
		this.id = parametro.getId();
		this.tipoParametro = new TipoParametroDTO(parametro.getSgpTipoParametro());
		this.descripcion = (parametro.getDescripcion() != "") ? parametro.getDescripcion() : "";
		this.nombre = parametro.getNombre();
		this.valor = parametro.getValor();
		setIndActivo(parametro.getIndActivo());
		setNumeroEstado(parametro.getIndActivo());
		setFecCreacion(parametro.getFecCreacion());
		setUsuarioCreacion(parametro.getUsuarioCrea());
		if (parametro.getFecEdita() != null) {
			setFechaEdita(parametro.getFecEdita());
		}
		if (parametro.getUsuarioEdita() != null) {
			setUsuarioEdita(parametro.getUsuarioEdita());
		}
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/01/2014
	 * @return the tipoParametro
	 */
	public TipoParametroDTO getTipoParametro() {
		return tipoParametro;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/01/2014
	 * @param tipoParametro
	 *            the tipoParametro to set
	 */
	public void setTipoParametro(TipoParametroDTO tipoParametro) {
		this.tipoParametro = tipoParametro;
	}
	
}
