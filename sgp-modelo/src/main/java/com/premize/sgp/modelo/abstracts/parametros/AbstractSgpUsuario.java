package com.premize.sgp.modelo.abstracts.parametros;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

import com.premize.sgp.modelo.entities.TableSequences;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpFlujoHallazgo;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpUsuarioProyecto;

/**
 * AbstractSgpUsuario entity provides the base persistence definition of the
 * SgpUsuario entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSgpUsuario implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4640840580501209101L;
	static final String TABLE_NAME = "sgp_usuario";
	// Fields

	private Integer id;
	private String nombre;
	private String empresa;
	private String cargo;
	private String login;
	private String correo;
	private String telefono;
	private Integer indActivo;
	private Date fecCreacion;
	private Date fecEdita;
	private String usuarioCrea;
	private String usuarioEdita;
	
	private Set<SgpFlujoHallazgo> sgpFlujoHallazgos = new HashSet<SgpFlujoHallazgo>(
			0);

	private Set<SgpUsuarioProyecto> sgpUsuario = new HashSet<SgpUsuarioProyecto>(
			0);

	// Constructors

	/** default constructor */
	public AbstractSgpUsuario() {
	}

	/** minimal constructor */
	public AbstractSgpUsuario(Integer id, String nombre, String empresa,
			String cargo, String login, String correo, Integer indActivo,
			Date fecCreacion, String usuarioCrea) {
		this.id = id;
		this.nombre = nombre;
		this.empresa = empresa;
		this.cargo = cargo;
		this.login = login;
		this.correo = correo;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
		this.usuarioCrea = usuarioCrea;
	}

	/** full constructor */
	public AbstractSgpUsuario(Integer id, String nombre, String empresa,
			String cargo, String login, String correo, String telefono,
			Integer indActivo, Date fecCreacion, Date fecEdita,
			String usuarioCrea, String usuarioEdita,
			Set<SgpFlujoHallazgo> sgpFlujoHallazgos,
			Set<SgpUsuarioProyecto> sgpUsuario) {
		this.id = id;
		this.nombre = nombre;
		this.empresa = empresa;
		this.cargo = cargo;
		this.login = login;
		this.correo = correo;
		this.telefono = telefono;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
		this.fecEdita = fecEdita;
		this.usuarioCrea = usuarioCrea;
		this.sgpFlujoHallazgos = sgpFlujoHallazgos;
		this.sgpUsuario = sgpUsuario;
	}
	
	/** customized constructor for id */
	public AbstractSgpUsuario(Integer id){
		this.id = id;
	}
	
	// Property accessors
	@Id
	@TableGenerator(name = TableSequences.NAME, table = TableSequences.NAME, pkColumnName = TableSequences.PK_COLUMN_NAME, valueColumnName = TableSequences.VALUE_COLUMN_NAME, pkColumnValue = TABLE_NAME, initialValue = TableSequences.INITIAL_VALUE, allocationSize = TableSequences.ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = TableSequences.NAME)
	@Column(name = "id_usuario", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "empresa", nullable = false)
	public String getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	@Column(name = "cargo", nullable = false)
	public String getCargo() {
		return this.cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@Column(name = "login", nullable = false)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "correo", nullable = false)
	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Column(name = "telefono", length = 50)
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "ind_activo", nullable = false)
	public Integer getIndActivo() {
		return this.indActivo;
	}

	public void setIndActivo(Integer indActivo) {
		this.indActivo = indActivo;
	}

	@Column(name = "fec_creacion", nullable = false, length = 29)
	public Date getFecCreacion() {
		return this.fecCreacion;
	}

	public void setFecCreacion(Date fecCreacion) {
		this.fecCreacion = fecCreacion;
	}

	@Column(name = "fec_edita", length = 29)
	public Date getFecEdita() {
		return this.fecEdita;
	}

	public void setFecEdita(Date fecEdita) {
		this.fecEdita = fecEdita;
	}

	@Column(name = "usuario_crea", nullable = false)
	public String getUsuarioCrea() {
		return this.usuarioCrea;
	}

	public void setUsuarioCrea(String usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}

	@Column(name = "usuario_edita")
	public String getUsuarioEdita() {
		return this.usuarioEdita;
	}

	public void setUsuarioEdita(String usuarioEdita) {
		this.usuarioEdita = usuarioEdita;
	}

	

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuarioAsignado")
	public Set<SgpFlujoHallazgo> getSgpFlujoHallazgos() {
		return this.sgpFlujoHallazgos;
	}

	public void setSgpFlujoHallazgos(Set<SgpFlujoHallazgo> sgpFlujoHallazgos) {
		this.sgpFlujoHallazgos = sgpFlujoHallazgos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sgpUsuario")
	public Set<SgpUsuarioProyecto> getSgpUsuario() {
		return sgpUsuario;
	}


	public void setSgpUsuario(
			Set<SgpUsuarioProyecto> sgpUsuario) {
		this.sgpUsuario = sgpUsuario;
	}

}