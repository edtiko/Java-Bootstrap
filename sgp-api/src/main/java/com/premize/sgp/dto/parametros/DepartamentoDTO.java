/**
 * 
 */
package com.premize.sgp.dto.parametros;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.premize.sgp.dto.BaseEntity;
import com.premize.sgp.modelo.entities.parametros.SgpDepartamento;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-modelo
 * @class ArtefactoDTO
 * @since 23/01/2014
 */
public class DepartamentoDTO extends BaseEntity {
	
	private static final long serialVersionUID = 7045247697866367428L;
	
	@JsonSerialize
	private Integer id;
	private String nombre;
	private String descripcion;
	private PaisDTO pais;
	
	public DepartamentoDTO() {
		super();
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 24/01/2014
	 * @return the idDepartamento
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 24/01/2014
	 * @param idDepartamento
	 *            the idDepartamento to set
	 */
	public void setId(Integer idDepartamento) {
		this.id = idDepartamento;
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
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 24/01/2014
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public DepartamentoDTO(SgpDepartamento departamento) {
		
		this.id = departamento.getId();
		this.descripcion = (!departamento.getDescripcion().equals("")) ? departamento.getDescripcion() : "";
		this.nombre = departamento.getNombre();
		setIndActivo(departamento.getIndActivo());
		setNumeroEstado(departamento.getIndActivo());
		setFecCreacion(departamento.getFecCreacion());
		setUsuarioCreacion(departamento.getUsuarioCrea());
		setPais(new PaisDTO(departamento.getSgpPais()));
		if (departamento.getFecEdita() != null) {
			setFechaEdita(departamento.getFecEdita());
		}
		if (departamento.getUsuarioEdita() != null) {
			setUsuarioEdita(departamento.getUsuarioEdita());
		}
		
	}
	
	public DepartamentoDTO(String descripcion, String nombre, Integer indActivo, Integer id) {
		super();
		this.descripcion = descripcion;
		this.nombre = nombre;
		setIndActivo(indActivo);
		this.id = id;
		
	}
	
	/**
	 * @author <a href="mailto:johnh.bernal@premize.com">John Hawer Bernal</a>
	 * @date 04/02/2014
	 * @return the pais
	 */
	public PaisDTO getPais() {
		return pais;
	}
	
	/**
	 * @author <a href="mailto:johnh.bernal@premize.com">John Hawer Bernal</a>
	 * @date 04/02/2014
	 * @param pais
	 *            the pais to set
	 */
	public void setPais(PaisDTO pais) {
		this.pais = pais;
	}
	
}