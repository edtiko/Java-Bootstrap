/**
 * 
 */
package com.premize.sgp.dto.pruebas;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.NotBlank;

import com.premize.sgp.dto.BaseEntity;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoHallazgo;

/**
 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
 * @project sgp-modelo
 * @class TipoHallazgoDTO
 * @since 16/06/2014
 */
public class TipoHallazgoDTO extends BaseEntity {
	
	private static final long serialVersionUID = 8172766632059686397L;
	@JsonSerialize
	private Integer id;
	@NotNull
	@Size(min = 1, max = 100)
	private String nombre;
	private String descripcion;
	private String esPuntuado;
	
	private String tieneCausaGeneracion;
	
	private Integer numeroEsPuntuado;
	private Integer numeroTieneCausaGeneracion;
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 16/06/2014
	 */
	public TipoHallazgoDTO() {
		super();
	}

/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
* @date 16/06/2014
* @param id
* @param nombre
* @param descripcion
 */
	public TipoHallazgoDTO(Integer id, String nombre, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public TipoHallazgoDTO(String nombre, String descripcion) {
		super();

		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 16/06/2014
	* @param id
	* @param nombre
	* @param descripcion
	 */
	public TipoHallazgoDTO(SgpTipoHallazgo tipoHallazgo ) {
		super();
		this.id = tipoHallazgo.getId();
		this.nombre = tipoHallazgo.getNombre();
		this.descripcion =(!tipoHallazgo.getDescripcion().equals("")) ? tipoHallazgo.getDescripcion() : "";
		setEsPuntuado(tipoHallazgo.getEsPuntuado());
		setTieneCausaGeneracion(tipoHallazgo.getTieneCausaGeneracion());
		setIndActivo(tipoHallazgo.getIndActivo());
		setNumeroEstado(tipoHallazgo.getIndActivo());
		setNumeroEsPuntuado(tipoHallazgo.getEsPuntuado());
		setNumeroTieneCausaGeneracion(tipoHallazgo.getTieneCausaGeneracion());
		setFecCreacion(tipoHallazgo.getFecCreacion());
		setUsuarioCreacion(tipoHallazgo.getUsuarioCrea());	
		if (tipoHallazgo.getFecEdita() != null) {
			setFechaEdita(tipoHallazgo.getFecEdita());
		}
		if (tipoHallazgo.getUsuarioEdita() != null) {
			setUsuarioEdita(tipoHallazgo.getUsuarioEdita());
		}
//		setEsPuntuadoString(tipoHallazgo.getEsPuntuado());
//		setTieneCausaGeneracionString(tipoHallazgo.getTieneCausaGeneracion());
	}


	/**
	 * @return the esPuntuado
	 */
	public String getEsPuntuado() {
		return esPuntuado;
	}

	/**
	 * @param esPuntuado the esPuntuado to set
	 */
	public void setEsPuntuado(Integer esPuntuado) {

		this.esPuntuado = (esPuntuado == 1) ? "Sí" : "No";
	}

	/**
	 * @return the tieneCausaGeneracion
	 */
	public String getTieneCausaGeneracion() {
		return tieneCausaGeneracion;
	}

	/**
	 * @param tieneCausaGeneracion the tieneCausaGeneracion to set
	 */
	public void setTieneCausaGeneracion(Integer tieneCausaGeneracion) {
		this.tieneCausaGeneracion = (tieneCausaGeneracion == 1) ? "Sí" : "No";
	}

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 16/06/2014
	* @return
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 16/06/2014
	* @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}


	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 16/06/2014
	* @return
	 */
	public String getNombre() {
		return nombre;
	}


/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
* @date 16/06/2014
* @param nombre
 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 16/06/2014
	* @return
	 */
	public String getDescripcion() {
		return descripcion;
	}


	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 16/06/2014
	* @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 22/07/2014
	* @return
	 */
	public Integer getNumeroEsPuntuado() {
		return numeroEsPuntuado;
	}

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 22/07/2014
	* @param numeroEsPuntuado
	 */
	public void setNumeroEsPuntuado(Integer numeroEsPuntuado) {
		this.numeroEsPuntuado = numeroEsPuntuado;
	}

	/**
	 * @return the numeroTieneCausaGeneracion
	 */
	public Integer getNumeroTieneCausaGeneracion() {
		return numeroTieneCausaGeneracion;
	}

	/**
	 * @param numeroTieneCausaGeneracion the numeroTieneCausaGeneracion to set
	 */
	public void setNumeroTieneCausaGeneracion(Integer numeroTieneCausaGeneracion) {
		this.numeroTieneCausaGeneracion = numeroTieneCausaGeneracion;
	}

	@Override
	public String toString() {
		return "TipoHallazgoDTO [id=" + id + ", nombre=" + nombre
				+ ", descripcion=" + descripcion + "]";
	}

	




	

	


	

	
}
