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
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpTipoSeveridad;
import com.premize.sgp.modelo.entities.parametros.SgpCiudad;

/**
 * AbstractSgpDepartamento entity provides the base persistence definition of
 * the SgpDepartamento entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSgpTipoPrioridad implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1604619518693425103L;
	// Fields

	private Integer id;
	private SgpTipoSeveridad tipoSeveridad;
	private String nombre;
	private String descripcion;
	private Integer indActivo;
	private Date fecCreacion;
	private Date fecEdita;
	private String usuarioCrea;
	private String usuarioEdita;
	private Float puntaje;
	static final String TABLE_NAME = "sgp_tipo_prioridad";

	// Constructors

	/** default constructor */
	public AbstractSgpTipoPrioridad() {
	}

	/** minimal constructor */
	public AbstractSgpTipoPrioridad(Integer idTipoPrioridad, SgpTipoSeveridad sgpTipoSeveridad,
			String nombre, Integer indActivo, Date fecCreacion,
			String usuarioCrea) {
		this.id = idTipoPrioridad;
		this.tipoSeveridad = sgpTipoSeveridad;
		this.nombre = nombre;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
		this.usuarioCrea = usuarioCrea;
	}

	/** full constructor */
	public AbstractSgpTipoPrioridad(Integer idTipoPrioridad, SgpTipoSeveridad sgpTipoSeveridad,
			String nombre, String descripcion, Integer indActivo,Float puntaje,
			Date fecCreacion, Date fecEdita, String usuarioCrea,
			String usuarioEdita, Set<SgpCiudad> sgpCiudads) {
		this.id= idTipoPrioridad;
		this.tipoSeveridad = sgpTipoSeveridad;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
		this.fecEdita = fecEdita;
		this.usuarioCrea = usuarioCrea;
		this.usuarioEdita = usuarioEdita;
		this.puntaje=puntaje;
	}

	// Property accessors
	
	
	@Id
	@TableGenerator(name = TableSequences.NAME, table = TableSequences.NAME, pkColumnName = TableSequences.PK_COLUMN_NAME, valueColumnName = TableSequences.VALUE_COLUMN_NAME, pkColumnValue = TABLE_NAME, initialValue = TableSequences.INITIAL_VALUE, allocationSize = TableSequences.ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = TableSequences.NAME)
	@Column(name = "id_tipo_prioridad", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer idTipoPrioridad) {
		this.id = idTipoPrioridad;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_severidad", nullable = false)
	public SgpTipoSeveridad getTipoSeveridad() {
		return this.tipoSeveridad;
	}

	public void setTipoSeveridad(SgpTipoSeveridad sgpTipoSeveridad) {
		this.tipoSeveridad = sgpTipoSeveridad;
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
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 1/07/2014
	* @return
	 */
	@Column(name = "puntaje", nullable = false)
	public Float getPuntaje() {
		return this.puntaje;
	}
/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
* @date 1/07/2014
* @param indActivo
 */
	public void setPuntaje(Float puntaje) {
		this.puntaje = puntaje;
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



}