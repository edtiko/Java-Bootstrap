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
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpHallazgo;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;
import com.premize.sgp.modelo.entities.parametros.SgpUsuario;

/**
 * AbstractSgpFlujoHallazgo entity provides the base persistence definition of
 * the SgpFlujoHallazgo entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSgpFlujoHallazgo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5362796701779377614L;
	static final String TABLE_NAME = "sgp_flujo_hallazgo";
	private Integer id;
	private SgpUsuario usuarioAsigna;
	private SgpHallazgo sgpHallazgo;
	private SgpUsuario usuarioAsignado;
	private String observacion;
	private Integer indActivo;
	private Date fecCreacion;
	private Date fecEdita;
	private String usuarioCrea;
	private String usuarioEdita;
	private SgpParametro accion;

	// Constructors

	/** default constructor */
	public AbstractSgpFlujoHallazgo() {
	}

	/** minimal constructor */
	public AbstractSgpFlujoHallazgo(Integer idFlujoHallazgo,
			SgpHallazgo sgpHallazgo, Integer indActivo, Date fecCreacion,
			String usuarioCrea) {
		this.id = idFlujoHallazgo;
		this.sgpHallazgo = sgpHallazgo;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
		this.usuarioCrea = usuarioCrea;
	}

	/** full constructor */
	public AbstractSgpFlujoHallazgo(Integer idFlujoHallazgo,
			SgpUsuario usuarioAsigna, SgpHallazgo sgpHallazgo,
			SgpUsuario usuarioAsignado, String observacion, 
			Integer indActivo, Date fecCreacion,
			Date fecEdita, String usuarioCrea, String usuarioEdita,
			SgpParametro accion) {
		this.id = idFlujoHallazgo;
		this.usuarioAsigna = usuarioAsigna;
		this.sgpHallazgo = sgpHallazgo;
		this.usuarioAsignado = usuarioAsignado;
		this.observacion = observacion;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
		this.fecEdita = fecEdita;
		this.usuarioCrea = usuarioCrea;
		this.usuarioEdita = usuarioEdita;
		this.accion = accion;
	}

	// Property accessors

	@Id
	@TableGenerator(name = TableSequences.NAME, table = TableSequences.NAME, pkColumnName = TableSequences.PK_COLUMN_NAME, valueColumnName = TableSequences.VALUE_COLUMN_NAME, pkColumnValue = TABLE_NAME, initialValue = TableSequences.INITIAL_VALUE, allocationSize = TableSequences.ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = TableSequences.NAME)
	@Column(name = "id_flujo_hallazgo", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer idFlujoHallazgo) {
		this.id = idFlujoHallazgo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_asigna")
	public SgpUsuario getUsuarioAsigna() {
		return this.usuarioAsigna;
	}

	public void setUsuarioAsigna(SgpUsuario sgpUsuario) {
		this.usuarioAsigna = sgpUsuario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_hallazgo", nullable = false)
	public SgpHallazgo getSgpHallazgo() {
		return this.sgpHallazgo;
	}

	public void setSgpHallazgo(SgpHallazgo sgpHallazgo) {
		this.sgpHallazgo = sgpHallazgo;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_asignado")
	public SgpUsuario getUsuarioAsignado() {
		return this.usuarioAsignado;
	}

	public void setUsuarioAsignado(SgpUsuario idUsuarioAsignado) {
		this.usuarioAsignado = idUsuarioAsignado;
	}

	@Column(name = "observacion", length = 5000)
	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@Column(name = "ind_activo")
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accion")
	public SgpParametro getAccion() {
		return this.accion;
	}

	public void setAccion(SgpParametro accion) {
		this.accion = accion;
	}

}