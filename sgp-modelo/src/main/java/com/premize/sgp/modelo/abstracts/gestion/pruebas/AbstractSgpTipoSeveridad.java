package com.premize.sgp.modelo.abstracts.gestion.pruebas;

import java.util.Date;
import java.util.Set;

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
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoHallazgo;
import com.premize.sgp.modelo.entities.parametros.SgpCiudad;

/**
 * AbstractSgpDepartamento entity provides the base persistence definition of
 * the SgpDepartamento entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSgpTipoSeveridad implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1604619518693425103L;
	// Fields

	private Integer id;
	private SgpTipoHallazgo tipoHallazgo;
	private String nombre;
	private String descripcion;
	private Integer indActivo;
	private Date fecCreacion;
	private Date fecEdita;
	private String usuarioCrea;
	private String usuarioEdita;
	private String color;

	static final String TABLE_NAME = "sgp_tipo_severidad";

	// Constructors

	/** default constructor */
	public AbstractSgpTipoSeveridad() {
	}

	/** minimal constructor */
	public AbstractSgpTipoSeveridad(Integer idTipoSeveridad, SgpTipoHallazgo sgpTipoHallazgo,
			String nombre, Integer indActivo, Date fecCreacion,
			String usuarioCrea) {
		this.id = idTipoSeveridad;
		this.tipoHallazgo = sgpTipoHallazgo;
		this.nombre = nombre;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
		this.usuarioCrea = usuarioCrea;
	}

	/** full constructor */
	public AbstractSgpTipoSeveridad(Integer idTipoSeveridad, SgpTipoHallazgo sgpTipoHallazgo,
			String nombre, String descripcion, Integer indActivo,
			Date fecCreacion, Date fecEdita, String usuarioCrea,
			String usuarioEdita, Set<SgpCiudad> sgpCiudads) {
		this.id= idTipoSeveridad;
		this.tipoHallazgo = sgpTipoHallazgo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
		this.fecEdita = fecEdita;
		this.usuarioCrea = usuarioCrea;
		this.usuarioEdita = usuarioEdita;
		
	}

	// Property accessors
	
	
	@Id
	@TableGenerator(name = TableSequences.NAME, table = TableSequences.NAME, pkColumnName = TableSequences.PK_COLUMN_NAME, valueColumnName = TableSequences.VALUE_COLUMN_NAME, pkColumnValue = TABLE_NAME, initialValue = TableSequences.INITIAL_VALUE, allocationSize = TableSequences.ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = TableSequences.NAME)
	@Column(name = "id_tipo_severidad", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer idTipoSeveridad) {
		this.id = idTipoSeveridad;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_hallazgo", nullable = false)
	public SgpTipoHallazgo getTipoHallazgo() {
		return this.tipoHallazgo;
	}

	public void setTipoHallazgo(SgpTipoHallazgo tipoHallazgo) {
		this.tipoHallazgo = tipoHallazgo;
	}

	@Column(name = "nombre", nullable = false)
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
	
	@Column(name = "color")
	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}


}