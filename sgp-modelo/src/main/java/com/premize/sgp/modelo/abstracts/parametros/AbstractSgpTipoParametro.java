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
import com.premize.sgp.modelo.entities.parametros.SgpParametro;

/**
 * AbstractSgpTipoParametro entity provides the base persistence definition of
 * the SgpTipoParametro entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSgpTipoParametro implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6697100687693018594L;
	static final String TABLE_NAME = "sgp_tipo_parametro";
	private Integer id;
	private String nombre;
	private String descripcion;
	private Integer indActivo;
	private Date fecCreacion;
	private Date fecEdita;
	private String usuarioCrea;
	private String usuarioEdita;
	private Set<SgpParametro> sgpParametros = new HashSet<SgpParametro>(0);

	// Constructors

	/** default constructor */
	public AbstractSgpTipoParametro() {
	}

	/** minimal constructor */
	public AbstractSgpTipoParametro(Integer idTipoParametro, String nombre,
			Integer indActivo, Date fecCreacion, String usuarioCrea) {
		this.id = idTipoParametro;
		this.nombre = nombre;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
		this.usuarioCrea = usuarioCrea;
	}

	/** full constructor */
	public AbstractSgpTipoParametro(Integer idTipoParametro, String nombre,
			String descripcion, Integer indActivo, Date fecCreacion,
			Date fecEdita, String usuarioCrea, String usuarioEdita,
			Set<SgpParametro> sgpParametros) {
		this.id = idTipoParametro;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
		this.fecEdita = fecEdita;
		this.usuarioCrea = usuarioCrea;
		this.usuarioEdita = usuarioEdita;
		this.sgpParametros = sgpParametros;
	}

	// Property accessors
	@Id
	@TableGenerator(name = TableSequences.NAME, table = TableSequences.NAME, pkColumnName = TableSequences.PK_COLUMN_NAME, valueColumnName = TableSequences.VALUE_COLUMN_NAME, pkColumnValue = TABLE_NAME, initialValue = TableSequences.INITIAL_VALUE, allocationSize = TableSequences.ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = TableSequences.NAME)
	@Column(name = "id_tipo_parametro", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer idTipoParametro) {
		this.id = idTipoParametro;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sgpTipoParametro")
	public Set<SgpParametro> getSgpParametros() {
		return this.sgpParametros;
	}

	public void setSgpParametros(Set<SgpParametro> sgpParametros) {
		this.sgpParametros = sgpParametros;
	}

}