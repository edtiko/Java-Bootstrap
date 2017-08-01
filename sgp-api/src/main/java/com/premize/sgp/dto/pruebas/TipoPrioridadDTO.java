package com.premize.sgp.dto.pruebas;

import javax.validation.constraints.Size;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.premize.sgp.dto.BaseEntity;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoPrioridad;

/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
* @project sgp-api
* @class CausaGeneracionDTO
* @date 17/06/2014
*
 */
public class TipoPrioridadDTO extends BaseEntity{
	
	
	private static final long serialVersionUID = 2537746632272447152L;
	@JsonSerialize
	private Integer id;
	@Size(min = 1, max = 100)
	private String nombre;
	@Size(min = 0, max =500)
	private String descripcion;
	private Float puntaje;
	private TipoSeveridadDTO tipoSeveridad;
	private TipoHallazgoDTO tipoHallazgo;
	
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	 */
	public TipoPrioridadDTO() {
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
	public TipoPrioridadDTO(Integer id, String nombre, String descripcion,
			TipoSeveridadDTO tipoSeveridad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipoSeveridad = tipoSeveridad;
	}
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 17/06/2014
	* @param tipoPrioridad
	 */
	public TipoPrioridadDTO(SgpTipoPrioridad tipoPrioridad ) {
		super();
		this.id = tipoPrioridad.getId();
		this.nombre = tipoPrioridad.getNombre();
		this.descripcion =(!tipoPrioridad.getDescripcion().equals("")) ? tipoPrioridad.getDescripcion() : "";
		setIndActivo(tipoPrioridad.getIndActivo());
		setNumeroEstado(tipoPrioridad.getIndActivo());
		setFecCreacion(tipoPrioridad.getFecCreacion());
		setUsuarioCreacion(tipoPrioridad.getUsuarioCrea());	
		setTipoHallazgo(new TipoHallazgoDTO(tipoPrioridad.getTipoSeveridad().getTipoHallazgo()));
		this.puntaje=tipoPrioridad.getPuntaje();
		if(null != tipoPrioridad.getTipoSeveridad()){
			this.tipoSeveridad=new TipoSeveridadDTO(tipoPrioridad.getTipoSeveridad());
		}else{
			this.tipoSeveridad=new TipoSeveridadDTO();
		}
		if (tipoPrioridad.getFecEdita() != null) {
			setFechaEdita(tipoPrioridad.getFecEdita());
		}
		if (tipoPrioridad.getUsuarioEdita() != null) {
			setUsuarioEdita(tipoPrioridad.getUsuarioEdita());
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
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 26/06/2014
	* @return
	 */
	public TipoSeveridadDTO getTipoSeveridad() {
		return tipoSeveridad;
	}
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 26/06/2014
	* @param tipoSeveridad
	 */
	public void setTipoSeveridad(TipoSeveridadDTO tipoSeveridad) {
		this.tipoSeveridad = tipoSeveridad;
	}

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 27/06/2014
	* @return
	 */
	public Float getPuntaje() {
		return puntaje;
	}
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 27/06/2014
	* @param puntuaje
	 */
	public void setPuntaje(Float puntaje) {
		this.puntaje = puntaje;
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
