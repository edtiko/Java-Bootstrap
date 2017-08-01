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
import com.premize.sgp.modelo.entities.anexo.SgpAnexoHallazgo;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpCasoPrueba;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpCausaGeneracion;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpFlujoHallazgo;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoHallazgo;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoPrioridad;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoSeveridad;
import com.premize.sgp.modelo.entities.parametros.SgpArtefacto;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;

/**
 * AbstractSgpHallazgo entity provides the base persistence definition of the
 * SgpHallazgo entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSgpHallazgo implements java.io.Serializable {

	// Fields
  
	/**
	 * 
	 */
	private static final long serialVersionUID = -5901772228520501698L;
	static final String TABLE_NAME = "sgp_hallazgo";
	private Integer id;
	private String usuarioCrea;
	private SgpCasoPrueba casoPrueba;
	private String usuarioEdita;
	private SgpTipoHallazgo tipoHallazgo;
	private SgpCausaGeneracion causaGeneracion;
	private SgpTipoSeveridad severidad;
	private SgpTipoPrioridad prioridad;
	private SgpParametro causaAnulacion;
	private SgpParametro motivoReasignacion;	
	private String titulo;
	private String descripcion;
	private Integer indActivo;
	private Date fecCreacion;
	private Date fecEdita;
	private SgpArtefacto artefacto;
	private Date fecSolicitud;
	private Set<SgpAnexoHallazgo> sgpAnexoHallazgos = new HashSet<SgpAnexoHallazgo>(
			0);
	private Set<SgpFlujoHallazgo> sgpFlujoHallazgos = new HashSet<SgpFlujoHallazgo>(
			0);

	// Constructors

	/** default constructor */
	public AbstractSgpHallazgo() {
	}

	/** minimal constructor */
	public AbstractSgpHallazgo(Integer idHallazgo,
			String usuarioCrea, SgpCasoPrueba sgpCasoPrueba,
			SgpTipoHallazgo tipoHallazgo, SgpCausaGeneracion causaGeneracion, SgpTipoSeveridad severidad,
			SgpTipoPrioridad prioridad, String titulo, Integer indActivo,
			Date fecCreacion) {
		this.id = idHallazgo;
		this.usuarioCrea = usuarioCrea;
		this.casoPrueba = sgpCasoPrueba;
		this.tipoHallazgo = tipoHallazgo;
		this.causaGeneracion = causaGeneracion;
		this.severidad = severidad;
		this.prioridad = prioridad;
		this.titulo = titulo;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
	}

	/** full constructor */
	public AbstractSgpHallazgo(Integer idHallazgo,
			String usuarioCrea, SgpCasoPrueba sgpCasoPrueba,
			String usuarioEdita, SgpTipoHallazgo tipoHallazgo,
			SgpCausaGeneracion causaGeneracion, SgpTipoSeveridad severidad, SgpTipoPrioridad prioridad,
			SgpParametro causaAnulacion, String titulo, String descripcion,
			Integer indActivo, Date fecCreacion, Date fecEdita,SgpArtefacto artefacto,
			Set<SgpAnexoHallazgo> sgpAnexoHallazgos,
			Set<SgpFlujoHallazgo> sgpFlujoHallazgos) {
		this.id = idHallazgo;
		this.usuarioCrea = usuarioCrea;
		this.casoPrueba = sgpCasoPrueba;
		this.usuarioEdita = usuarioEdita;
		this.tipoHallazgo = tipoHallazgo;
		this.causaGeneracion = causaGeneracion;
		this.severidad = severidad;
		this.prioridad = prioridad;
		this.causaAnulacion = causaAnulacion;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
		this.fecEdita = fecEdita;
		this.sgpAnexoHallazgos = sgpAnexoHallazgos;
		this.sgpFlujoHallazgos = sgpFlujoHallazgos;
		this.artefacto=artefacto;
	}

	// Property accessors
	@Id
	@TableGenerator(name = TableSequences.NAME, table = TableSequences.NAME, pkColumnName = TableSequences.PK_COLUMN_NAME, valueColumnName = TableSequences.VALUE_COLUMN_NAME, pkColumnValue = TABLE_NAME, initialValue = TableSequences.INITIAL_VALUE, allocationSize = TableSequences.ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = TableSequences.NAME)
	@Column(name = "id_hallazgo", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer idHallazgo) {
		this.id = idHallazgo;
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
	@JoinColumn(name = "id_caso_prueba", nullable = false)
	public SgpCasoPrueba getCasoPrueba() {
		return this.casoPrueba;
	}

	public void setCasoPrueba(SgpCasoPrueba sgpCasoPrueba) {
		this.casoPrueba = sgpCasoPrueba;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_artefacto", nullable = false)
	public SgpArtefacto getArtefacto() {
		return this.artefacto;
	}

	public void setArtefacto(SgpArtefacto artefacto) {
		this.artefacto = artefacto;
	}
	
	
	
	@Column(name = "usuario_edita")
	public String getUsuarioEdita() {
		return this.usuarioEdita;
	}

	public void setUsuarioEdita(
			String usuarioEdita) {
		this.usuarioEdita = usuarioEdita;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_hallazgo", nullable = false)
	public SgpTipoHallazgo getTipoHallazgo() {
		return this.tipoHallazgo;
	}

	public void setTipoHallazgo(SgpTipoHallazgo tipoHallazgo) {
		this.tipoHallazgo = tipoHallazgo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "motivo_reasignacion")
	public SgpParametro getMotivoReasignacion() {
		return this.motivoReasignacion;
	}

	public void setMotivoReasignacion(SgpParametro motivoReasignacion) {
		this.motivoReasignacion = motivoReasignacion;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "causa_generacion", nullable = false)
	public SgpCausaGeneracion getCausaGeneracion() {
		return this.causaGeneracion;
	}

	public void setCausaGeneracion(SgpCausaGeneracion causaGeneracion) {
		this.causaGeneracion = causaGeneracion;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "severidad", nullable = false)
	public SgpTipoSeveridad getSeveridad() {
		return this.severidad;
	}

	public void setSeveridad(SgpTipoSeveridad severidad) {
		this.severidad = severidad;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prioridad", nullable = false)
	public SgpTipoPrioridad getPrioridad() {
		return this.prioridad;
	}

	public void setPrioridad(SgpTipoPrioridad prioridad) {
		this.prioridad = prioridad;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "causa_anulacion", nullable = false)
	public SgpParametro getCausaAnulacion() {
		return this.causaAnulacion;
	}

	public void setCausaAnulacion(SgpParametro causaAnulacion) {
		this.causaAnulacion = causaAnulacion;
	}

	@Column(name = "titulo", nullable = false, length = 1000)
	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Column(name = "descripcion", length = 5000)
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

	@Column(name = "fec_solicitud", length = 29)
	public Date getFecSolicitud() {
		return this.fecSolicitud;
	}

	public void setFecSolicitud(Date fecSolicitud) {
		this.fecSolicitud = fecSolicitud;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sgpHallazgo")
	public Set<SgpAnexoHallazgo> getSgpAnexoHallazgos() {
		return this.sgpAnexoHallazgos;
	}

	public void setSgpAnexoHallazgos(Set<SgpAnexoHallazgo> sgpAnexoHallazgos) {
		this.sgpAnexoHallazgos = sgpAnexoHallazgos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sgpHallazgo")
	public Set<SgpFlujoHallazgo> getSgpFlujoHallazgos() {
		return this.sgpFlujoHallazgos;
	}

	public void setSgpFlujoHallazgos(Set<SgpFlujoHallazgo> sgpFlujoHallazgos) {
		this.sgpFlujoHallazgos = sgpFlujoHallazgos;
	}

}