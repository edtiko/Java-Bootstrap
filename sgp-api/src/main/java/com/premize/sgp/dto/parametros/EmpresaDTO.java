/**
 * 
 */
package com.premize.sgp.dto.parametros;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.premize.sgp.dto.BaseEntity;
import com.premize.sgp.dto.pruebas.ProyectoDTO;
import com.premize.sgp.modelo.entities.parametros.SgpEmpresa;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-modelo
 * @class EmpresaDTO
 * @since 23/01/2014
 */
public class EmpresaDTO extends BaseEntity {
	
	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = -3108473331911258813L;
	@JsonSerialize
	private Integer id;
	private CiudadDTO ciudad;
	private String nombre;
	private String descripcion;
	private String rutaLogo;
	private String imgLogo;
	private String direccion;
	private String telefono;
	private String nit;
	private PaisDTO pais;
	private DepartamentoDTO departamento;
	private ProyectoDTO proyecto;
	
	public EmpresaDTO() {
		super();
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 30/01/2014
	 * @param sgpEmpresa
	 */
	public EmpresaDTO(SgpEmpresa sgpEmpresa) {
		
		this.id = sgpEmpresa.getId();
		this.ciudad = new CiudadDTO(sgpEmpresa.getSgpCiudad());
		this.nombre = sgpEmpresa.getNombre();
		this.descripcion = sgpEmpresa.getDescripcion();
		this.rutaLogo = sgpEmpresa.getRutaLogo();
		// TODO Mar 20, 2014 EmpresaDTO EmpresaDTO Convertir a constante
		this.imgLogo = "<img src='" + sgpEmpresa.getRutaLogo() + "' width='60' height='42'/>";
		this.direccion = sgpEmpresa.getDireccion();
		this.telefono = sgpEmpresa.getTelefono();
		this.nit = sgpEmpresa.getNit();
		if (this.ciudad != null) {
			this.departamento = new DepartamentoDTO(sgpEmpresa.getSgpCiudad().getSgpDepartamento());
		}
		if (this.departamento != null) {
			this.pais = new PaisDTO(sgpEmpresa.getSgpCiudad().getSgpDepartamento().getSgpPais());
		}
		setIndActivo(sgpEmpresa.getIndActivo());
		setNumeroEstado(sgpEmpresa.getIndActivo());
		setFecCreacion(sgpEmpresa.getFecCreacion());
		setUsuarioCreacion(sgpEmpresa.getUsuarioCrea());
		if (sgpEmpresa.getFecEdita() != null) {
			setFechaEdita(sgpEmpresa.getFecEdita());
		}
		if (sgpEmpresa.getUsuarioEdita() != null) {
			setUsuarioEdita(sgpEmpresa.getUsuarioEdita());
		}
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 28/01/2014
	 * @return the idEmpresa
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 28/01/2014
	 * @param idEmpresa
	 *            the idEmpresa to set
	 */
	public void setId(Integer idEmpresa) {
		this.id = idEmpresa;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 23/01/2014
	 * @return the sgpCiudad
	 */
	public CiudadDTO getCiudad() {
		return ciudad;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 23/01/2014
	 * @param sgpCiudad
	 *            the sgpCiudad to set
	 */
	public void setCiudad(CiudadDTO ciudadDto) {
		this.ciudad = ciudadDto;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 23/01/2014
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 23/01/2014
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 23/01/2014
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 23/01/2014
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 23/01/2014
	 * @return the rutaLogo
	 */
	public String getRutaLogo() {
		return rutaLogo;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 23/01/2014
	 * @param rutaLogo
	 *            the rutaLogo to set
	 */
	public void setRutaLogo(String rutaLogo) {
		this.rutaLogo = rutaLogo;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 23/01/2014
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 23/01/2014
	 * @param direccion
	 *            the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 23/01/2014
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 23/01/2014
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 23/01/2014
	 * @return the nit
	 */
	public String getNit() {
		return nit;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 23/01/2014
	 * @param nit
	 *            the nit to set
	 */
	public void setNit(String nit) {
		this.nit = nit;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 31/01/2014
	 * @return the pais
	 */
	public PaisDTO getPais() {
		return pais;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 31/01/2014
	 * @param pais
	 *            the pais to set
	 */
	public void setPais(PaisDTO pais) {
		this.pais = pais;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 31/01/2014
	 * @return the departamento
	 */
	public DepartamentoDTO getDepartamento() {
		return departamento;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 31/01/2014
	 * @param departamento
	 *            the departamento to set
	 */
	public void setDepartamento(DepartamentoDTO departamento) {
		this.departamento = departamento;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 13/02/2014
	 * @return the proyecto
	 */
	public ProyectoDTO getProyecto() {
		return proyecto;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 13/02/2014
	 * @param proyecto
	 *            the proyecto to set
	 */
	public void setProyecto(ProyectoDTO proyecto) {
		this.proyecto = proyecto;
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 13/02/2014
	 * @return the imgLogo
	 */
	public String getImgLogo() {
		return imgLogo;
	}
	
	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 13/02/2014
	 * @param imgLogo
	 *            the imgLogo to set
	 */
	public void setImgLogo(String imgLogo) {
		this.imgLogo = imgLogo;
	}
	
}
