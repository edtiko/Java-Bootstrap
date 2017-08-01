/**
 * 
 */
package com.premize.sgp.dto.parametros;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.premize.sgp.dto.BaseEntity;
import com.premize.sgp.modelo.entities.parametros.SgpCiudad;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-modelo
 * @class ArtefactoDTO
 * @since 23/01/2014
 */
public class CiudadDTO extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1582247375027912017L;
	@JsonSerialize
	private Integer id;
	private DepartamentoDTO departamento;
	private PaisDTO pais;
	private String nombre;
	private String descripcion;
	
	public CiudadDTO() {
		super();
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public CiudadDTO(SgpCiudad ciudad) {
		
		this.id = ciudad.getId();
		
		this.descripcion = (ciudad.getDescripcion() != "") ? ciudad.getDescripcion() : "";
		this.nombre = ciudad.getNombre();
		setIndActivo(ciudad.getIndActivo());
		setNumeroEstado(ciudad.getIndActivo());
		setDepartamento(new DepartamentoDTO(ciudad.getSgpDepartamento()));
		setFecCreacion(ciudad.getFecCreacion());
		setUsuarioCreacion(ciudad.getUsuarioCrea());
		setPais(new PaisDTO(ciudad.getSgpDepartamento().getSgpPais()));
		if (ciudad.getFecEdita() != null) {
			setFechaEdita(ciudad.getFecEdita());
		}
		if (ciudad.getUsuarioEdita() != null) {
			setUsuarioEdita(ciudad.getUsuarioEdita());
		}
		
	}
	
	public CiudadDTO(String descripcion, String nombre, Integer indActivo, Integer id) {
		super();
		this.descripcion = descripcion;
		this.nombre = nombre;
		setIndActivo(indActivo);
		this.id = id;
		
	}
	
	public DepartamentoDTO getDepartamento() {
		return departamento;
	}
	
	public void setDepartamento(DepartamentoDTO departamento) {
		this.departamento = departamento;
	}
	
	public PaisDTO getPais() {
		return pais;
	}
	
	public void setPais(PaisDTO pais) {
		this.pais = pais;
	}
	
}
