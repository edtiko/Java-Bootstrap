/**
 * 
 */
package com.premize.sgp.dto.parametros;

import javax.validation.constraints.Size;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.NotEmpty;

import com.premize.sgp.dto.BaseEntity;
import com.premize.sgp.modelo.entities.parametros.SgpTipoArtefacto;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-modelo
 * @class SgpTipoArtefactoDTO
 * @since 24/01/2014
 */
public class TipoArtefactoDTO extends BaseEntity {
	
	private static final long serialVersionUID = 944031732699437801L;
	@JsonSerialize
	private Integer id;
	
	@NotEmpty
	@Size(min = 1, max = 50)
	private String nombre;
	
	@Size(min = 1, max = 255)
	private String descripcion;
	
	public TipoArtefactoDTO() {
		super();
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 28/01/2014
	 * @param sgpTipoArtefacto
	 */
	public TipoArtefactoDTO(SgpTipoArtefacto sgpTipoArtefacto) {
		this.id = sgpTipoArtefacto.getIdTipoArtefacto();
		this.nombre = sgpTipoArtefacto.getNombre();
		this.descripcion = sgpTipoArtefacto.getDescripcion();
		setIndActivo(sgpTipoArtefacto.getIndActivo());
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @return the idTipoArtefacto
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @param idTipoArtefacto
	 *            the idTipoArtefacto to set
	 */
	public void setId(Integer idTipoArtefacto) {
		this.id = idTipoArtefacto;
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
	 * @since 27/01/2014
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
