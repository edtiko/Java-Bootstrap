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
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpHallazgo;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpMapaPrueba;
import com.premize.sgp.modelo.entities.parametros.SgpArtefacto;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;

/**
 * AbstractSgpCasoPrueba entity provides the base persistence definition of the SgpCasoPrueba entity. @author MyEclipse
 * Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSgpCasoPrueba implements java.io.Serializable {
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7029395353654188313L;
	private Integer id;
	private String usuarioCrea;
	private SgpArtefacto sgpArtefacto;
	private SgpMapaPrueba sgpMapaPrueba;
	private String usuarioEdita;
	private String descripcion;
	private String resultado;
	private SgpParametro tipoPrueba;
	private String cumple;
	private Date fecCreacion;
	private Date fecEdita;
	private String usuarioEjecuta;
	private Date fecEjecuta;
	private Set<SgpHallazgo> sgpHallazgos = new HashSet<SgpHallazgo>(0);
	
	private Integer consecutivo;
	
	static final String TABLE_NAME = "sgp_caso_prueba";
	
	// Constructors
	
	/** default constructor */
	public AbstractSgpCasoPrueba() {
	}
	
	/** minimal constructor */
	public AbstractSgpCasoPrueba(Integer id, String usuarioCrea, SgpArtefacto sgpArtefacto,
			SgpMapaPrueba sgpMapaPrueba, SgpParametro tipoPrueba, Date fecCreacion, Integer consecutivo) {
		this.id = id;
		this.usuarioCrea = usuarioCrea;
		this.sgpArtefacto = sgpArtefacto;
		this.sgpMapaPrueba = sgpMapaPrueba;
		this.tipoPrueba = tipoPrueba;
		this.fecCreacion = fecCreacion;
		this.consecutivo = consecutivo;
	}
	
	/** full constructor */
	public AbstractSgpCasoPrueba(Integer id, String usuarioCrea, SgpArtefacto sgpArtefacto,
			SgpMapaPrueba sgpMapaPrueba, String usuarioEdita, String descripcion, String resultado,
			SgpParametro tipoPrueba, String cumple, Date fecCreacion, Date fecEdita, String usuarioEjecuta,
			Date fecEjecuta, Integer consecutivo, Set<SgpHallazgo> sgpHallazgos) {
		this.id = id;
		this.usuarioCrea = usuarioCrea;
		this.sgpArtefacto = sgpArtefacto;
		this.sgpMapaPrueba = sgpMapaPrueba;
		this.usuarioEdita = usuarioEdita;
		this.descripcion = descripcion;
		this.resultado = resultado;
		this.tipoPrueba = tipoPrueba;
		this.cumple = cumple;
		this.fecCreacion = fecCreacion;
		this.fecEdita = fecEdita;
		this.usuarioEjecuta = usuarioEjecuta;
		this.fecEjecuta = fecEjecuta;
		this.sgpHallazgos = sgpHallazgos;
		this.consecutivo = consecutivo;
	}
	
	// Property accessors
	@Id
	@TableGenerator(name = TableSequences.NAME, table = TableSequences.NAME, pkColumnName = TableSequences.PK_COLUMN_NAME, valueColumnName = TableSequences.VALUE_COLUMN_NAME, pkColumnValue = TABLE_NAME, initialValue = TableSequences.INITIAL_VALUE, allocationSize = TableSequences.ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = TableSequences.NAME)
	@Column(name = "id_caso_prueba", unique = true, nullable = false)
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
	
	public void setUsuarioCrea(String usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_artefacto", nullable = false)
	public SgpArtefacto getSgpArtefacto() {
		return this.sgpArtefacto;
	}
	
	public void setSgpArtefacto(SgpArtefacto sgpArtefacto) {
		this.sgpArtefacto = sgpArtefacto;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_mapa_prueba", nullable = false)
	public SgpMapaPrueba getSgpMapaPrueba() {
		return this.sgpMapaPrueba;
	}
	
	public void setSgpMapaPrueba(SgpMapaPrueba sgpMapaPrueba) {
		this.sgpMapaPrueba = sgpMapaPrueba;
	}
	
	@Column(name = "usuario_edita")
	public String getUsuarioEdita() {
		return this.usuarioEdita;
	}
	
	public void setUsuarioEdita(String usuarioEdita) {
		this.usuarioEdita = usuarioEdita;
	}
	
	@Column(name = "descripcion", length = 5000)
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Column(name = "resultado", length = 5000)
	public String getResultado() {
		return this.resultado;
	}
	
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_prueba", nullable = false)
	public SgpParametro getTipoPrueba() {
		return this.tipoPrueba;
	}
	
	public void setTipoPrueba(SgpParametro tipoPrueba) {
		this.tipoPrueba = tipoPrueba;
	}
	
	@Column(name = "cumple")
	public String getCumple() {
		return this.cumple;
	}
	
	public void setCumple(String cumple) {
		this.cumple = cumple;
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
	
	@Column(name = "usuario_ejecuta")
	public String getUsuarioEjecuta() {
		return usuarioEjecuta;
	}
	
	public void setUsuarioEjecuta(String usuarioEjecuta) {
		this.usuarioEjecuta = usuarioEjecuta;
	}
	
	@Column(name = "fec_ejecuta", length = 29)
	public Date getFecEjecuta() {
		return fecEjecuta;
	}
	
	public void setFecEjecuta(Date fecEjecuta) {
		this.fecEjecuta = fecEjecuta;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "casoPrueba")
	public Set<SgpHallazgo> getSgpHallazgos() {
		return this.sgpHallazgos;
	}
	
	public void setSgpHallazgos(Set<SgpHallazgo> sgpHallazgos) {
		this.sgpHallazgos = sgpHallazgos;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/06/2014 
	 * @return the consecutivo 
	 */
	@Column(name = "consecutivo", nullable = false)
	public Integer getConsecutivo() {
		return consecutivo;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/06/2014 
	 * @param consecutivo the consecutivo to set 
	 */
	public void setConsecutivo(Integer consecutivo) {
		this.consecutivo = consecutivo;
	}
	
	
}