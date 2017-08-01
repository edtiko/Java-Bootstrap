/**
 * 
 */
package com.premize.sgp.dto.parametros;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.premize.sgp.dto.BaseEntity;
import com.premize.sgp.modelo.entities.parametros.SgpPais;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-modelo
 * @class ArtefactoDTO
 * @since 23/01/2014
 */
public class PaisDTO extends BaseEntity {
	
	private static final long serialVersionUID = 8172766632059686397L;
	@JsonSerialize
	private Integer id;
	private String nombre;
	private String descripcion;
	private Long totalDepartamentos;
	
	public PaisDTO() {
		super();
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 24/01/2014
	 * @return the idPais
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 24/01/2014
	 * @param idPais
	 *            the idPais to set
	 */
	public void setId(Integer idPais) {
		this.id = idPais;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 24/01/2014
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 24/01/2014
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 24/01/2014
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 24/01/2014
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public PaisDTO(SgpPais pais) {
		
		this.id = pais.getId();
		this.descripcion = (!pais.getDescripcion().equals("")) ? pais.getDescripcion() : "";
		this.nombre = pais.getNombre();
		setIndActivo(pais.getIndActivo());
		setNumeroEstado(pais.getIndActivo());
		setFecCreacion(pais.getFecCreacion());
		setUsuarioCreacion(pais.getUsuarioCrea());
		if (pais.getFecEdita() != null) {
			setFechaEdita(pais.getFecEdita());
		}
		if (pais.getUsuarioEdita() != null) {
			setUsuarioEdita(pais.getUsuarioEdita());
		}
		
	}
	
	public PaisDTO(String descripcion, String nombre, Integer indActivo, Integer id) {
		super();
		this.descripcion = descripcion;
		this.nombre = nombre;
		setIndActivo(indActivo);
		this.id = id;
		
	}
	
	/**
	 * @author <a href="mailto:juanm.caicedo@premize.com">Juan Manuel Caicedo</a>
	 * @date Mar 6, 2014
	 * @return the totalDepartamentos
	 */
	public Long getTotalDepartamentos() {
		return totalDepartamentos;
	}
	
	/**
	 * @author <a href="mailto:juanm.caicedo@premize.com">Juan Manuel Caicedo</a>
	 * @date Mar 6, 2014
	 * @param totalDepartamentos
	 *            the totalDepartamentos to set
	 */
	public void setTotalDepartamentos(Long totalDepartamentos) {
		this.totalDepartamentos = totalDepartamentos;
	}
	
}
