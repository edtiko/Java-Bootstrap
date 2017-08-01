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

import com.premize.sgp.modelo.entities.TableSequences;
import com.premize.sgp.modelo.entities.parametros.SgpDepartamento;
import com.premize.sgp.modelo.entities.parametros.SgpEmpresa;



/**
 * AbstractSgpCiudad entity provides the base persistence definition of the
 * SgpCiudad entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSgpCiudad implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1423992797828923648L;
	// Fields

	private Integer id;
	private SgpDepartamento sgpDepartamento;
	private String nombre;
	private String descripcion;
	private Integer indActivo;
	private Date fecCreacion;
	private Date fecEdita;
	private String usuarioCrea;
	private String usuarioEdita;
	static final String TABLE_NAME = "sgp_ciudad";
	private Set<SgpEmpresa> sgpEmpresas = new HashSet<SgpEmpresa>(0);

	// Constructors

	/** default constructor */
	public AbstractSgpCiudad() {
	}

	/** minimal constructor */
	public AbstractSgpCiudad(Integer idCiudad, SgpDepartamento sgpDepartamento,
			String nombre, Integer indActivo, Date fecCreacion,
			String usuarioCrea) {
		this.id = idCiudad;
		this.sgpDepartamento = sgpDepartamento;
		this.nombre = nombre;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
		this.usuarioCrea = usuarioCrea;
	}

	/** full constructor */
	public AbstractSgpCiudad(Integer idCiudad, SgpDepartamento sgpDepartamento,
			String nombre, String descripcion, Integer indActivo,
			Date fecCreacion, Date fecEdita, String usuarioCrea,
			String usuarioEdita, Set<SgpEmpresa> sgpEmpresas) {
		this.id = idCiudad;
		this.sgpDepartamento = sgpDepartamento;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
		this.fecEdita = fecEdita;
		this.usuarioCrea = usuarioCrea;
		this.usuarioEdita = usuarioEdita;
		this.sgpEmpresas = sgpEmpresas;
	}
	
	/** customized constructor for id*/
	public AbstractSgpCiudad(Integer idCiudad) {
		this.id= idCiudad;
	}

	// Property accessors
	@Id
	@TableGenerator(name = TableSequences.NAME, table = TableSequences.NAME, pkColumnName = TableSequences.PK_COLUMN_NAME, valueColumnName = TableSequences.VALUE_COLUMN_NAME, pkColumnValue = TABLE_NAME, initialValue = TableSequences.INITIAL_VALUE, allocationSize = TableSequences.ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = TableSequences.NAME)
	@Column(name = "id_ciudad", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer idCiudad) {
		this.id = idCiudad;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_departamento", nullable = false)
	public SgpDepartamento getSgpDepartamento() {
		return this.sgpDepartamento;
	}

	public void setSgpDepartamento(SgpDepartamento sgpDepartamento) {
		this.sgpDepartamento = sgpDepartamento;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion")
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

	public void setFecCreacion(Date date) {
		this.fecCreacion = date;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sgpCiudad")
	public Set<SgpEmpresa> getSgpEmpresas() {
		return this.sgpEmpresas;
	}

	public void setSgpEmpresas(Set<SgpEmpresa> sgpEmpresas) {
		this.sgpEmpresas = sgpEmpresas;
	}

}