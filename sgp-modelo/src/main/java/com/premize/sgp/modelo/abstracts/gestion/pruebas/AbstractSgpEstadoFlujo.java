package com.premize.sgp.modelo.abstracts.gestion.pruebas;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.premize.sgp.modelo.entities.parametros.SgpParametro;

/**
 * AbstractSgpEstadoFlujo entity provides the base persistence definition of the SgpEstadoFlujo entity. @author
 * MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSgpEstadoFlujo implements java.io.Serializable {
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1283401051204569797L;
	private Integer id;
	private String usuarioCrea;
	private String usuarioEdita;
	private SgpParametro flujoActual;
	private SgpParametro flujoSiguiente;
	private SgpParametro tipoHallazgo;
	private Integer indActivo;
	private Date fecCreacion;
	private Date fecEdita;
	
	// Constructors
	
	/** default constructor */
	public AbstractSgpEstadoFlujo() {
	}
	
	/** minimal constructor */
	public AbstractSgpEstadoFlujo(Integer idEstadoFlujo, String usuarioCrea, SgpParametro flujoActual,
			SgpParametro flujoSiguiente, SgpParametro tipoHallazgo, Integer indActivo, Date fecCreacion) {
		this.id = idEstadoFlujo;
		this.usuarioCrea = usuarioCrea;
		this.flujoActual = flujoActual;
		this.flujoSiguiente = flujoSiguiente;
		this.tipoHallazgo = tipoHallazgo;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
	}
	
	/** full constructor */
	public AbstractSgpEstadoFlujo(Integer idEstadoFlujo, String usuarioCrea, String usuarioEdita,
			SgpParametro flujoActual, SgpParametro flujoSiguiente, SgpParametro tipoHallazgo, Integer indActivo,
			Date fecCreacion, Date fecEdita) {
		this.id = idEstadoFlujo;
		this.usuarioCrea = usuarioCrea;
		this.usuarioEdita = usuarioEdita;
		this.flujoActual = flujoActual;
		this.flujoSiguiente = flujoSiguiente;
		this.tipoHallazgo = tipoHallazgo;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
		this.fecEdita = fecEdita;
	}
	
	// Property accessors
	@Id
	@Column(name = "id_estado_flujo", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer idEstadoFlujo) {
		this.id = idEstadoFlujo;
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
	@JoinColumn(name = "flujo_actual", nullable = false)
	public SgpParametro getFlujoActual() {
		return this.flujoActual;
	}
	
	public void setFlujoActual(SgpParametro flujoActual) {
		this.flujoActual = flujoActual;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flujo_siguiente", nullable = false)
	public SgpParametro getFlujoSiguiente() {
		return this.flujoSiguiente;
	}
	
	public void setFlujoSiguiente(SgpParametro flujoSiguiente) {
		this.flujoSiguiente = flujoSiguiente;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_hallazgo", nullable = false)
	public SgpParametro getTipoHallazgo() {
		return this.tipoHallazgo;
	}
	
	public void setTipoHallazgo(SgpParametro tipoHallazgo) {
		this.tipoHallazgo = tipoHallazgo;
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