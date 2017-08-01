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
import com.premize.sgp.modelo.entities.parametros.SgpUsuario;

/**
 * AbstractSgpUsuarioProyecto entity provides the base persistence definition of
 * the SgpUsuarioProyecto entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSgpUsuarioProyecto implements
		java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1616644589451331040L;
	static final String TABLE_NAME = "sgp_usuario_proyecto";
	private Integer id;
	private SgpProyecto sgpProyecto;
	private String usuarioCrea;
	private SgpUsuario sgpUsuario;
	private String usuarioEdita;
	private Integer indActivo;
	private Date fecCreacion;
	private Date fecEdita;

	// Constructors

	/** default constructor */
	public AbstractSgpUsuarioProyecto() {
	}

	/** minimal constructor */
	public AbstractSgpUsuarioProyecto(Integer idUsuarioProyecto,
			SgpProyecto sgpProyecto, String usuarioCrea,
			SgpUsuario sgpUsuario, Integer indActivo,
			Date fecCreacion) {
		this.id = idUsuarioProyecto;
		this.sgpProyecto = sgpProyecto;
		this.usuarioCrea = usuarioCrea;
		this.sgpUsuario = sgpUsuario;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
	}

	/** full constructor */
	public AbstractSgpUsuarioProyecto(Integer idUsuarioProyecto,
			SgpProyecto sgpProyecto, String usuarioCrea,
			SgpUsuario sgpUsuario,
			String usuarioEdita, Integer indActivo,
			Date fecCreacion, Date fecEdita) {
		this.id = idUsuarioProyecto;
		this.sgpProyecto = sgpProyecto;
		this.usuarioCrea = usuarioCrea;
		this.sgpUsuario = sgpUsuario;
		this.usuarioEdita = usuarioEdita;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
		this.fecEdita = fecEdita;
	}

	// Property accessors
	@Id
	@TableGenerator(name = TableSequences.NAME, table = TableSequences.NAME, pkColumnName = TableSequences.PK_COLUMN_NAME, valueColumnName = TableSequences.VALUE_COLUMN_NAME, pkColumnValue = TABLE_NAME, initialValue = TableSequences.INITIAL_VALUE, allocationSize = TableSequences.ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = TableSequences.NAME)
	@Column(name = "id_usuario_proyecto", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer idUsuarioProyecto) {
		this.id = idUsuarioProyecto;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario", nullable = false)
	public SgpUsuario getSgpUsuario() {
		return this.sgpUsuario;
	}

	public void setSgpUsuario(SgpUsuario sgpUsuario) {
		this.sgpUsuario = sgpUsuario;
	}

	@Column(name = "usuario_edita")
	public String getUsuarioEdita() {
		return this.usuarioEdita;
	}

	public void setUsuarioEdita(
			String usuarioEdita) {
		this.usuarioEdita = usuarioEdita;
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