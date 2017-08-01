package com.premize.sgp.modelo.abstracts.gestion.pruebas;

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
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpProyecto;

/**
 * AbstractSgpMapaPrueba entity provides the base persistence definition of the
 * SgpMapaPrueba entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSgpMapaPrueba implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3475863294614187484L;
	// Fields

	private Integer id;
	private SgpProyecto sgpProyecto;
	private String usuarioCrea;
	private String usuarioEdita;
	private String nombre;
	private Integer indActivo;
	private Date fecCreacion;
	private Date fecEdita;

	static final String TABLE_NAME = "sgp_mapa_pruebas";

	// Constructors

	/** default constructor */
	public AbstractSgpMapaPrueba() {
	}

	/** minimal constructor */
	public AbstractSgpMapaPrueba(Integer id, SgpProyecto sgpProyecto,
			String usuarioCrea, String nombre,
			Integer indActivo, Date fecCreacion) {
		this.id = id;
		this.sgpProyecto = sgpProyecto;
		this.usuarioCrea = usuarioCrea;
		this.nombre = nombre;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
	}

	/** full constructor */
	public AbstractSgpMapaPrueba(Integer id, SgpProyecto sgpProyecto,
			String usuarioCrea,
			String usuarioEdita, String nombre,
			Integer indActivo, Date fecCreacion, Date fecEdita)

	
	{
		this.id = id;
		this.sgpProyecto = sgpProyecto;
		this.usuarioCrea = usuarioCrea;
		this.usuarioEdita = usuarioEdita;
		this.nombre = nombre;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
		this.fecEdita = fecEdita;

	}

	// Property accessors
	@Id
	@TableGenerator(name = TableSequences.NAME, table = TableSequences.NAME, pkColumnName = TableSequences.PK_COLUMN_NAME, valueColumnName = TableSequences.VALUE_COLUMN_NAME, pkColumnValue = TABLE_NAME, initialValue = TableSequences.INITIAL_VALUE, allocationSize = TableSequences.ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = TableSequences.NAME)
	@Column(name = "id_mapa_prueba", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_proyecto", nullable = false)
	public SgpProyecto getSgpProyecto() {
		return this.sgpProyecto;
	}

	public void setSgpProyecto(SgpProyecto sgpProyecto) {
		this.sgpProyecto = sgpProyecto;
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