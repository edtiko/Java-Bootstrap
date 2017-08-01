package com.premize.sgp.dto.pruebas;

import javax.validation.constraints.Size;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.premize.sgp.dto.BaseEntity;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoSeveridad;

/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
* @project sgp-api
* @class CausaGeneracionDTO
* @date 17/06/2014
*
 */
public class TipoSeveridadDTO extends BaseEntity{
	
	
	private static final long serialVersionUID = 2537746632272447152L;
	@JsonSerialize
	private Integer id;
	@Size(min = 1, max = 100)
	private String nombre;
	@Size(min = 0, max = 500)
	private String descripcion;
	private String color;
	private TipoHallazgoDTO tipoHallazgo;
	
	
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	 */
	public TipoSeveridadDTO() {
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
	public TipoSeveridadDTO(Integer id, String nombre, String descripcion,String color,
			TipoHallazgoDTO tipoHallazgo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipoHallazgo = tipoHallazgo;
		this.color=color;
	}
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @param tipoSeveridad
	 */
	public TipoSeveridadDTO(SgpTipoSeveridad tipoSeveridad ) {
		super();
		if(null != tipoSeveridad){
		this.id = tipoSeveridad.getId();
		this.nombre = tipoSeveridad.getNombre();
		this.descripcion =(!tipoSeveridad.getDescripcion().equals("")) ? tipoSeveridad.getDescripcion() : "";
		setIndActivo(tipoSeveridad.getIndActivo());
		setNumeroEstado(tipoSeveridad.getIndActivo());
		setFecCreacion(tipoSeveridad.getFecCreacion());
		setUsuarioCreacion(tipoSeveridad.getUsuarioCrea());	
		this.tipoHallazgo=new TipoHallazgoDTO(tipoSeveridad.getTipoHallazgo());
		this.color=tipoSeveridad.getColor();
		
		if (tipoSeveridad.getFecEdita() != null) {
			setFechaEdita(tipoSeveridad.getFecEdita());
		}
		if (tipoSeveridad.getUsuarioEdita() != null) {
			setUsuarioEdita(tipoSeveridad.getUsuarioEdita());
		}
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
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	

	
}
