package com.premize.sgp.modelo.abstracts.anexo;

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

/**
 * AbstractSgpAnexoHallazgo entity provides the base persistence definition of the SgpAnexoHallazgo entity. @author
 * MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSgpAnexoHallazgo implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1819418265382649789L;
	// Fields
	
	static final String TABLE_NAME = "sgp_anexo_hallazgo";
	private Integer id;
	private String usuarioCrea;
	private SgpHallazgo sgpHallazgo;
	private String usuarioEdita;
	private String ruta;
	private Integer indActivo;
	private Date fecCreacion;
	private Date fecEdita;
	
	// Constructors
	
	/** default constructor */
	public AbstractSgpAnexoHallazgo() {
	}
	
	/** minimal constructor */
	public AbstractSgpAnexoHallazgo(Integer idAnexoHallazgo, String usuarioCrea, SgpHallazgo sgpHallazgo, String ruta,
			Integer indActivo, Date fecCreacion) {
		this.id = idAnexoHallazgo;
		this.usuarioCrea = usuarioCrea;
		this.sgpHallazgo = sgpHallazgo;
		this.ruta = ruta;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
	}
	
	/** full constructor */
	public AbstractSgpAnexoHallazgo(Integer idAnexoHallazgo, String usuarioCrea, SgpHallazgo sgpHallazgo,
			String usuarioEdita, String ruta, Integer indActivo, Date fecCreacion, Date fecEdita) {
		this.id = idAnexoHallazgo;
		this.usuarioCrea = usuarioCrea;
		this.sgpHallazgo = sgpHallazgo;
		this.usuarioEdita = usuarioEdita;
		this.ruta = ruta;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
		this.fecEdita = fecEdita;
	}
	
	// Property accessors
	@Id
	@TableGenerator(name = TableSequences.NAME, table = TableSequences.NAME, pkColumnName = TableSequences.PK_COLUMN_NAME, valueColumnName = TableSequences.VALUE_COLUMN_NAME, pkColumnValue = TABLE_NAME, initialValue = TableSequences.INITIAL_VALUE, allocationSize = TableSequences.ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = TableSequences.NAME)
	@Column(name = "id_anexo_hallazgo", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer idAnexoHallazgo) {
		this.id = idAnexoHallazgo;
	}
	
	@Column(name = "usuario_crea", nullable = false)
	public String getUsuarioCrea() {
		return this.usuarioCrea;
	}
	
	public void setUsuarioCrea(String usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_hallazgo", nullable = false)
	public SgpHallazgo getSgpHallazgo() {
		return this.sgpHallazgo;
	}
	
	public void setSgpHallazgo(SgpHallazgo sgpHallazgo) {
		this.sgpHallazgo = sgpHallazgo;
	}
	
	@Column(name = "usuario_edita")
	public String getUsuarioEdita() {
		return this.usuarioEdita;
	}
	
	public void setUsuarioEdita(String usuarioEdita) {
		this.usuarioEdita = usuarioEdita;
	}
	
	@Column(name = "ruta", nullable = false, length = 1000)
	public String getRuta() {
		return this.ruta;
	}
	
	public void setRuta(String ruta) {
		this.ruta = ruta;
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