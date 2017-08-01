/**
 * 
 */
package com.premize.sgp.dto.parametros;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.premize.sgp.dto.BaseEntity;
import com.premize.sgp.modelo.entities.parametros.SgpUsuario;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-modelo
 * @class UsuarioDTO
 * @since 23/01/2014
 */
public class UsuarioDTO extends BaseEntity {
	
	// Fields
	
	private static final long serialVersionUID = -8784609396608241005L;
	@JsonSerialize
	private Integer id;
	private String nombre;
	private String empresa;
	private String cargo;
	private String login;
	private String correo;
	private String telefono;
	
	public UsuarioDTO() {
		super();
	}
	
	public UsuarioDTO(Integer idUsuario) {
		this.id = idUsuario;
	}
	
	public UsuarioDTO(SgpUsuario sgpUsuario) {
		
		this.id = sgpUsuario.getId();
		this.nombre = sgpUsuario.getNombre();
		this.empresa = sgpUsuario.getEmpresa();
		this.cargo = sgpUsuario.getCargo();
		this.login = sgpUsuario.getLogin();
		this.correo = sgpUsuario.getCorreo();
		this.telefono = sgpUsuario.getTelefono();
		setIndActivo(sgpUsuario.getIndActivo());
		setNumeroEstado(sgpUsuario.getIndActivo());
		setFecCreacion(sgpUsuario.getFecCreacion());
		setUsuarioCreacion(sgpUsuario.getUsuarioCrea());
		if (sgpUsuario.getFecEdita() != null) {
			setFechaEdita(sgpUsuario.getFecEdita());
		}
		if (sgpUsuario.getUsuarioEdita() != null) {
			setUsuarioEdita(sgpUsuario.getUsuarioEdita());
		}
		
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @return the idUsuario
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @param idUsuario
	 *            the idUsuario to set
	 */
	public void setId(Integer idUsuario) {
		this.id = idUsuario;
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
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @param empresa
	 *            the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @return the cargo
	 */
	public String getCargo() {
		return cargo;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @param cargo
	 *            the cargo to set
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @param correo
	 *            the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
