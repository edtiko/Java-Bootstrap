package com.premize.sgp.modelo.abstracts.parametros;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;

import com.premize.sgp.modelo.entities.TableSequences;
import com.premize.sgp.modelo.entities.parametros.SgpTipoParametro;


/**
 * AbstractSgpParametro entity provides the base persistence definition of the
 * SgpParametro entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSgpParametro implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -778227319605039302L;
	private Integer id;
	private SgpTipoParametro sgpTipoParametro;
	private String usuarioCrea;
	private String usuarioEdita;
	private String nombre;
	private String descripcion;
	private String valor;
	private Integer indActivo;
	private Date fecCreacion;
	private Date fecEdita;

static final String TABLE_NAME = "sgp_parametro";
	// Constructors

	/** default constructor */
	public AbstractSgpParametro() {
	}

	/** minimal constructor */
	public AbstractSgpParametro(Integer idParametro,
			SgpTipoParametro sgpTipoParametro,
			String usuarioCrea, String valor,
			Integer indActivo, Date fecCreacion) {
		this.id = idParametro;
		this.sgpTipoParametro = sgpTipoParametro;
		this.usuarioCrea = usuarioCrea;
		this.valor = valor;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
	}

	/** full constructor */
	public AbstractSgpParametro(Integer idParametro,
			SgpTipoParametro sgpTipoParametro,
			String usuarioCrea,
			String usuarioEdita, String nombre,
			String descripcion, String valor, Integer indActivo,
			Date fecCreacion, Date fecEdita) {
		this.id = idParametro;
		this.sgpTipoParametro = sgpTipoParametro;
		this.usuarioCrea = usuarioCrea;
		this.usuarioEdita = usuarioEdita;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.valor = valor;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
		this.fecEdita = fecEdita;
	}

	// Property accessors
	@Id
	@TableGenerator(name = TableSequences.NAME, table = TableSequences.NAME, pkColumnName = TableSequences.PK_COLUMN_NAME, valueColumnName = TableSequences.VALUE_COLUMN_NAME, pkColumnValue = TABLE_NAME, initialValue = TableSequences.INITIAL_VALUE, allocationSize = TableSequences.ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = TableSequences.NAME)
	@Column(name = "id_parametro", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer idParametro) {
		this.id = idParametro;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_parametro", nullable = false)
	public SgpTipoParametro getSgpTipoParametro() {
		return this.sgpTipoParametro;
	}

	public void setSgpTipoParametro(SgpTipoParametro sgpTipoParametro) {
		this.sgpTipoParametro = sgpTipoParametro;
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

	@Column(name = "nombre")
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

	@Column(name = "valor", nullable = false, length = 1000)
	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
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



}