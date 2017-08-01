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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

import org.hibernate.validator.constraints.NotBlank;

import com.premize.sgp.modelo.entities.TableSequences;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpProyecto;
import com.premize.sgp.modelo.entities.parametros.SgpCiudad;

/**
 * AbstractSgpEmpresa entity provides the base persistence definition of the
 * SgpEmpresa entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSgpEmpresa implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -3840004180422109364L;
	static final String TABLE_NAME = "sgp_empresa";
	private Integer id;
	private String usuarioCrea;
	private SgpCiudad sgpCiudad;
	private String usuarioEdita;
	private String nombre;
	private String descripcion;
	private String rutaLogo;
	private String direccion;
	private String telefono;
	private String nit;
	private Integer indActivo;
	private Date fecCreacion;
	private Date fecEdita;
	private Set<SgpProyecto> sgpProyectos = new HashSet<SgpProyecto>(0);

	// Constructors

	/** default constructor */
	public AbstractSgpEmpresa() {
	}

	/** minimal constructor */
	public AbstractSgpEmpresa(Integer id,
			String usuarioCrea, SgpCiudad sgpCiudad,
			String nombre, String rutaLogo, Integer indActivo,
			Date fecCreacion) {
		this.id = id;
		this.usuarioCrea = usuarioCrea;
		this.sgpCiudad = sgpCiudad;
		this.nombre = nombre;
		this.rutaLogo = rutaLogo;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
	}

	/** full constructor */
	public AbstractSgpEmpresa(Integer id,
			String usuarioCrea, SgpCiudad sgpCiudad,
			String usuarioEdita, String nombre,
			String descripcion, String rutaLogo, String direccion,
			String telefono, String nit, Integer indActivo,
			Date fecCreacion, Date fecEdita,
			Set<SgpProyecto> sgpProyectos) {
		this.id = id;
		this.usuarioCrea = usuarioCrea;
		this.sgpCiudad = sgpCiudad;
		this.usuarioEdita = usuarioEdita;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.rutaLogo = rutaLogo;
		this.direccion = direccion;
		this.telefono = telefono;
		this.nit = nit;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
		this.fecEdita = fecEdita;
		this.sgpProyectos = sgpProyectos;
	}

	// Property accessors
	@Id
	@TableGenerator(name = TableSequences.NAME, table = TableSequences.NAME, pkColumnName = TableSequences.PK_COLUMN_NAME, valueColumnName = TableSequences.VALUE_COLUMN_NAME, pkColumnValue = TABLE_NAME, initialValue = TableSequences.INITIAL_VALUE, allocationSize = TableSequences.ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = TableSequences.NAME)
	@Column(name = "id_empresa", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "usuario_crea", nullable = false)
	public String getUsuarioCrea() {
		return this.usuarioCrea;
	}

	public void setUsuarioCrea(
			String usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ciudad", nullable = false)
	public SgpCiudad getSgpCiudad() {
		return this.sgpCiudad;
	}

	public void setSgpCiudad(SgpCiudad sgpCiudad) {
		this.sgpCiudad = sgpCiudad;
	}

	@Column(name = "usuario_edita")
	public String getUsuarioEdita() {
		return this.usuarioEdita;
	}

	public void setUsuarioEdita(
			String usuarioEdita) {
		this.usuarioEdita = usuarioEdita;
	}

	@Column(name = "nombre", nullable = false)
	@NotBlank
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion", length = 1000)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "ruta_logo", nullable = false, length = 1000)
	public String getRutaLogo() {
		return this.rutaLogo;
	}

	public void setRutaLogo(String rutaLogo) {
		this.rutaLogo = rutaLogo;
	}

	@Column(name = "direccion")
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Column(name = "telefono", length = 50)
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "nit")
	public String getNit() {
		return this.nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sgpEmpresa")
	public Set<SgpProyecto> getSgpProyectos() {
		return this.sgpProyectos;
	}

	public void setSgpProyectos(Set<SgpProyecto> sgpProyectos) {
		this.sgpProyectos = sgpProyectos;
	}

}