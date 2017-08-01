package com.premize.sgp.dto.pruebas;

import javax.validation.constraints.Size;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.premize.sgp.dto.BaseEntity;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpCausaGeneracion;

/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
* @project sgp-api
* @class CausaGeneracionDTO
* @date 17/06/2014
*
 */
public class CausaGeneracionDTO extends BaseEntity{
	
	
	private static final long serialVersionUID = 2537746632272447152L;
	@JsonSerialize
	private Integer id;
	@Size(min = 1, max = 100)
	private String nombre;
	@Size(min = 0, max = 500)
	private String descripcion;
	private TipoHallazgoDTO tipoHallazgo;
	
	
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	 */
	public CausaGeneracionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @param id
	* @param nombre
	* @param descripcion
	* @param tipoHallazgoDTO
	 */
	public CausaGeneracionDTO(Integer id, String nombre, String descripcion,
			TipoHallazgoDTO tipoHallazgo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipoHallazgo = tipoHallazgo;
	}
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @param causaGeneracion
	 */
	public CausaGeneracionDTO(SgpCausaGeneracion causaGeneracion ) {
		super();
		this.id = causaGeneracion.getId();
		this.nombre = causaGeneracion.getNombre();
		this.descripcion =(!causaGeneracion.getDescripcion().equals("")) ? causaGeneracion.getDescripcion() : "";
		setIndActivo(causaGeneracion.getIndActivo());
		setNumeroEstado(causaGeneracion.getIndActivo());
		setFecCreacion(causaGeneracion.getFecCreacion());
		setUsuarioCreacion(causaGeneracion.getUsuarioCrea());	
		this.tipoHallazgo=new TipoHallazgoDTO(causaGeneracion.getSgpTipoHallazgo());
		if (causaGeneracion.getFecEdita() != null) {
			setFechaEdita(causaGeneracion.getFecEdita());
		}
		if (causaGeneracion.getUsuarioEdita() != null) {
			setUsuarioEdita(causaGeneracion.getUsuarioEdita());
		}
	}

	
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @return
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @return
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @return
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the tipoHallazgo
	 */
	public TipoHallazgoDTO getTipoHallazgo() {
		return tipoHallazgo;
	}
	/**
	 * @param tipoHallazgo the tipoHallazgo to set
	 */
	public void setTipoHallazgo(TipoHallazgoDTO tipoHallazgo) {
		this.tipoHallazgo = tipoHallazgo;
	}
	

	
}
