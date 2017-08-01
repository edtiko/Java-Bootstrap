package com.premize.sgp.modelo.abstracts.artefacto;

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
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpMapaPrueba;
import com.premize.sgp.modelo.entities.parametros.SgpArtefacto;

/**
 * AbstractSgpMapaArtefacto entity provides the base persistence definition of the SgpMapaArtefacto entity. @author
 * MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSgpMapaArtefacto implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2133844715643666597L;
	static final String TABLE_NAME = "sgp_mapa_artefacto";
	// Fields
	
	private Integer id;
	private String usuarioCrea;
	private SgpArtefacto sgpArtefacto;
	private SgpMapaPrueba sgpMapaPrueba;
	private String usuarioEdita;
	private Integer indActivo;
	private Date fecCreacion;
	private Date fecEdita;
	
	// Constructors
	
	/** default constructor */
	public AbstractSgpMapaArtefacto() {
	}
	
	/** minimal constructor */
	public AbstractSgpMapaArtefacto(Integer idMapaArtefacto, String usuarioCrea, SgpArtefacto sgpArtefacto,
			SgpMapaPrueba sgpMapaPrueba, Integer indActivo, Date fecCreacion) {
		this.id = idMapaArtefacto;
		this.usuarioCrea = usuarioCrea;
		this.sgpArtefacto = sgpArtefacto;
		this.sgpMapaPrueba = sgpMapaPrueba;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
	}
	
	/** full constructor */
	public AbstractSgpMapaArtefacto(Integer idMapaArtefacto, String usuarioCrea, SgpArtefacto sgpArtefacto,
			SgpMapaPrueba sgpMapaPrueba, String usuarioEdita, Integer indActivo, Date fecCreacion, Date fecEdita) {
		this.id = idMapaArtefacto;
		this.usuarioCrea = usuarioCrea;
		this.sgpArtefacto = sgpArtefacto;
		this.sgpMapaPrueba = sgpMapaPrueba;
		this.usuarioEdita = usuarioEdita;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
		this.fecEdita = fecEdita;
	}
	
	// Property accessors
	@Id
	@TableGenerator(name = TableSequences.NAME, table = TableSequences.NAME, pkColumnName = TableSequences.PK_COLUMN_NAME, valueColumnName = TableSequences.VALUE_COLUMN_NAME, pkColumnValue = TABLE_NAME, initialValue = TableSequences.INITIAL_VALUE, allocationSize = TableSequences.ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = TableSequences.NAME)
	@Column(name = "id_mapa_artefacto", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer idMapaArtefacto) {
		this.id = idMapaArtefacto;
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