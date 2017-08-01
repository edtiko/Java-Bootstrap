/**
 * 
 */
package com.premize.sgp.dto.pruebas;

import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.premize.sgp.dto.BaseEntity;
import com.premize.sgp.dto.parametros.EmpresaDTO;
import com.premize.sgp.dto.parametros.PaisDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpProyecto;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-api
 * @class ParametroDTO
 * @since 21/01/2014
 */
public class ProyectoDTO extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -349018818090205525L;
	
	private Integer id;
	@NotNull
	private String nombre;
	private String descripcion;
	@Valid
	private EmpresaDTO empresa;

	
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
	 */
	public ProyectoDTO() {
		
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 21/01/2014
	 * @param descripcion
	 * @param nombre
	 * @param valor
	 */
	public ProyectoDTO(String descripcion, String nombre, String valor) {
		super();
		this.descripcion = descripcion;
		this.nombre = nombre;
	}
	
	public ProyectoDTO(String descripcion, String nombre, String valor, PaisDTO pais, Integer id) {
		super();
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.id = id;
	}
	
	public ProyectoDTO(SgpProyecto proyecto) {
		
		this.id = proyecto.getId();
		this.descripcion = (!proyecto.getDescripcion().equals("")) ? proyecto.getDescripcion() : "";
		this.nombre = proyecto.getNombre();
		this.empresa = new EmpresaDTO(proyecto.getSgpEmpresa());
		setIndActivo(proyecto.getIndActivo());
		setNumeroEstado(proyecto.getIndActivo());
		setUsuarioCreacion(proyecto.getUsuarioCrea());
		setFecCreacion(proyecto.getFecCreacion());
		if (proyecto.getFecEdita() != null) {
			setFechaEdita(proyecto.getFecEdita());
		}
		if (proyecto.getUsuarioEdita() != null) {
			setUsuarioEdita(proyecto.getUsuarioEdita());
		}
		
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 31/01/2014
	 * @return the empresa
	 */
	public EmpresaDTO getEmpresa() {
		return empresa;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 31/01/2014
	 * @param empresa
	 *            the empresa to set
	 */
	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}


}
