package com.premize.sgp.modelo.abstracts.gestion.pruebas;

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

import com.premize.sgp.modelo.entities.TableSequences;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpMapaPrueba;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpUsuarioProyecto;
import com.premize.sgp.modelo.entities.parametros.SgpEmpresa;

/**
 * AbstractSgpProyecto entity provides the base persistence definition of the
 * SgpProyecto entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSgpProyecto implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5420126213775892304L;
	private Integer id;
	private SgpEmpresa sgpEmpresa;
	private String usuarioCrea;
	private String usuarioEdita;
	private String nombre;
	private String descripcion;
	private Integer indActivo;
	private Date fecCreacion;
	private Date fecEdita;
	private Set<SgpMapaPrueba> sgpMapaPruebas = new HashSet<SgpMapaPrueba>(0);
	
	private Set<SgpUsuarioProyecto> sgpUsuarioProyectos = new HashSet<SgpUsuarioProyecto>(
			0);
	static final String TABLE_NAME = "sgp_proyecto";
	// Constructors

	/** default constructor */
	public AbstractSgpProyecto() {
	}

	/** minimal constructor */
	public AbstractSgpProyecto(Integer id, SgpEmpresa sgpEmpresa,
			String usuarioCrea, String nombre,
			Integer indActivo, Date fecCreacion) {
		this.id = id;
		this.sgpEmpresa = sgpEmpresa;
		this.usuarioCrea = usuarioCrea;
		this.nombre = nombre;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
	}

	/** full constructor */
	public AbstractSgpProyecto(Integer id, SgpEmpresa sgpEmpresa,
			String usuarioCrea,
			String usuarioEdita, String nombre,
			String descripcion, Integer indActivo, Date fecCreacion,
			Date fecEdita, Set<SgpMapaPrueba> sgpMapaPruebas,
			Set<SgpUsuarioProyecto> sgpUsuarioProyectos) {
		this.id = id;
		this.sgpEmpresa = sgpEmpresa;
		this.usuarioCrea = usuarioCrea;
		this.usuarioEdita = usuarioEdita;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
		this.fecEdita = fecEdita;
		this.sgpMapaPruebas = sgpMapaPruebas;
		this.sgpUsuarioProyectos = sgpUsuarioProyectos;
	}

	// Property accessors
	@Id
	@TableGenerator(name = TableSequences.NAME, table = TableSequences.NAME, pkColumnName = TableSequences.PK_COLUMN_NAME, valueColumnName = TableSequences.VALUE_COLUMN_NAME, pkColumnValue = TABLE_NAME, initialValue = TableSequences.INITIAL_VALUE, allocationSize = TableSequences.ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = TableSequences.NAME)
	@Column(name = "id_proyecto", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer idProyecto) {
		this.id = idProyecto;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_empresa", nullable = false)
	public SgpEmpresa getSgpEmpresa() {
		return this.sgpEmpresa;
	}

	public void setSgpEmpresa(SgpEmpresa sgpEmpresa) {
		this.sgpEmpresa = sgpEmpresa;
	}

	@Column(name = "usuario_crea", nullable = false)
	public String getUsuarioCrea() {
		return this.usuarioCrea;
	}

	public void setUsuarioCrea(
			String usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}

	@Column(name = "usuario_edita")
	public String getUsuarioEdita() {
		return this.usuarioEdita;
	}

	public void setUsuarioEdita(
			String usuarioEdita) {
		this.usuarioEdita = usuarioEdita;
	}

	@Column(name = "nombre", nullable = false, length = 100)
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sgpProyecto")
	public Set<SgpMapaPrueba> getSgpMapaPruebas() {
		return this.sgpMapaPruebas;
	}

	public void setSgpMapaPruebas(Set<SgpMapaPrueba> sgpMapaPruebas) {
		this.sgpMapaPruebas = sgpMapaPruebas;
	}
	

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sgpProyecto")
	public Set<SgpUsuarioProyecto> getSgpUsuarioProyectos() {
		return this.sgpUsuarioProyectos;
	}

	public void setSgpUsuarioProyectos(
			Set<SgpUsuarioProyecto> sgpUsuarioProyectos) {
		this.sgpUsuarioProyectos = sgpUsuarioProyectos;
	}

}