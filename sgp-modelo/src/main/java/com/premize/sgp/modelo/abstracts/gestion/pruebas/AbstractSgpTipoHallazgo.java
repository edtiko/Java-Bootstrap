package com.premize.sgp.modelo.abstracts.gestion.pruebas;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;

import com.premize.sgp.modelo.entities.TableSequences;

/**
 * AbstractSgpPais entity provides the base persistence definition of the
 * SgpPais entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSgpTipoHallazgo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7700449181888317861L;
	private Integer id;
	private String nombre;
	private String descripcion;
	private Integer indActivo;
	private Date fecCreacion;
	private Date fecEdita;
	private String usuarioCrea;
	private String usuarioEdita;
	private Integer esPuntuado;
	private Integer tieneCausaGeneracion;
	static final String TABLE_NAME = "sgp_tipo_hallazgo";
	

	// Constructors

	/** default constructor */
	public AbstractSgpTipoHallazgo() {
	}

	/** minimal constructor */
	public AbstractSgpTipoHallazgo(Integer idTipoHallazgo, String nombre, Integer indActivo,
			Date fecCreacion, String usuarioCrea) {
		this.id = idTipoHallazgo;
		this.nombre = nombre;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
		this.usuarioCrea = usuarioCrea;
	}

	/** full constructor */
	public AbstractSgpTipoHallazgo(Integer idTipoHallazgo, String nombre, String descripcion,
			Integer indActivo, Date fecCreacion, Date fecEdita,
			String usuarioCrea, String usuarioEdita,Integer esPuntuado) {
		this.id = idTipoHallazgo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
		this.fecEdita = fecEdita;
		this.usuarioCrea = usuarioCrea;
		this.usuarioEdita = usuarioEdita;
		this.esPuntuado=esPuntuado;
	}

	// Property accessors
	@Id
	@TableGenerator(name = TableSequences.NAME, table = TableSequences.NAME, pkColumnName = TableSequences.PK_COLUMN_NAME, valueColumnName = TableSequences.VALUE_COLUMN_NAME, pkColumnValue = TABLE_NAME, initialValue = TableSequences.INITIAL_VALUE, allocationSize = TableSequences.ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = TableSequences.NAME)
	@Column(name = "id_tipo_hallazgo", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer idTipoHallazgo) {
		this.id= idTipoHallazgo;
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
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 1/07/2014
	* @return
	 */
	@Column(name = "es_puntuado")
	public Integer getEsPuntuado() {
		return this.esPuntuado;
	}
/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
* @date 1/07/2014
* @param esPuntuado
 */
	public void setEsPuntuado(Integer esPuntuado) {
		this.esPuntuado = esPuntuado;
	}

/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
* @date 1/07/2014
* @return
 */
@Column(name = "tiene_causa_generacion")
public Integer getTieneCausaGeneracion() {
	return tieneCausaGeneracion;
}

/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
* @date 1/07/2014
* @param tieneCausaGeneracion
 */
public void setTieneCausaGeneracion(Integer tieneCausaGeneracion) {
	this.tieneCausaGeneracion = tieneCausaGeneracion;
}
	

}