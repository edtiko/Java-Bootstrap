package com.premize.sgp.modelo.abstracts.parametros;

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
import com.premize.sgp.modelo.entities.artefacto.SgpMapaArtefacto;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpCasoPrueba;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpProyecto;
import com.premize.sgp.modelo.entities.parametros.SgpTipoArtefacto;
import com.premize.sgp.modelo.entities.parametros.SgpUsuario;

/**
 * AbstractSgpArtefacto entity provides the base persistence definition of the
 * SgpArtefacto entity. @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractSgpArtefacto implements java.io.Serializable {

	/**
	 * 
	 */
	// Fields
	private static final long serialVersionUID = -1556826609990109393L;
	static final String TABLE_NAME = "sgp_artefacto";
	private Integer id;
	private String usuarioCrea;
	private SgpTipoArtefacto sgpTipoArtefacto;
	private String usuarioEdita;
	private String nombre;
	private String descripcion;
	private Integer indActivo;
	private Date fecCreacion;
	private Date fecEdita;
	private SgpProyecto proyecto;
	private Set<SgpCasoPrueba> sgpCasoPruebas = new HashSet<SgpCasoPrueba>(0);
	private Set<SgpMapaArtefacto> sgpMapaArtefactos = new HashSet<SgpMapaArtefacto>(0);
	
	private SgpUsuario usuario; //Usuario asociado al artefacto

	// Constructors

	/** default constructor */
	public AbstractSgpArtefacto() {
	}

	/** minimal constructor */
	public AbstractSgpArtefacto(Integer id,
			String usuarioCrea,
			SgpTipoArtefacto sgpTipoArtefacto, String nombre,
			Integer indActivo, Date fecCreacion,SgpProyecto proyecto) {
		this.id = id;
		this.usuarioCrea = usuarioCrea;
		this.sgpTipoArtefacto = sgpTipoArtefacto;
		this.nombre = nombre;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
		this.proyecto = proyecto;

	}

	/** full constructor */
	public AbstractSgpArtefacto(Integer id,
			String usuarioCrea,
			SgpTipoArtefacto sgpTipoArtefacto,
			String usuarioEdita, String nombre,
			String descripcion, Integer indActivo, Date fecCreacion,
			Date fecEdita,SgpProyecto proyecto,
			SgpUsuario usuario,
			Set<SgpCasoPrueba> sgpCasoPruebas,
			Set<SgpMapaArtefacto> sgpMapaArtefactos) {
		this.id = id;
		this.usuarioCrea = usuarioCrea;
		this.sgpTipoArtefacto = sgpTipoArtefacto;
		this.usuarioEdita = usuarioEdita;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
		this.fecEdita = fecEdita;
		this.proyecto = proyecto;
		this.usuario = usuario;
		this.sgpCasoPruebas = sgpCasoPruebas;
		this.sgpMapaArtefactos = sgpMapaArtefactos;
	}

	// Property accessors
	
	/**customized constructor*/
	public AbstractSgpArtefacto(Integer id) {
		this.id = id;
	}

	@Id
	@TableGenerator(name = TableSequences.NAME, table = TableSequences.NAME, pkColumnName = TableSequences.PK_COLUMN_NAME, valueColumnName = TableSequences.VALUE_COLUMN_NAME, pkColumnValue = TABLE_NAME, initialValue = TableSequences.INITIAL_VALUE, allocationSize = TableSequences.ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = TableSequences.NAME)
	@Column(name = "id_artefacto", unique = true, nullable = false)
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

	public void setUsuarioCrea(
			String usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_artefacto", nullable = false)
	public SgpTipoArtefacto getSgpTipoArtefacto() {
		return this.sgpTipoArtefacto;
	}

	public void setSgpTipoArtefacto(SgpTipoArtefacto sgpTipoArtefacto) {
		this.sgpTipoArtefacto = sgpTipoArtefacto;
	}

	@Column(name = "usuario_edita")
	public String getUsuarioEdita() {
		return this.usuarioEdita;
	}

	public void setUsuarioEdita(
			String usuarioEdita) {
		this.usuarioEdita = usuarioEdita;
	}

	@Column(name = "nombre", nullable = false, length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion", length = 1000)
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


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sgpArtefacto")
	public Set<SgpCasoPrueba> getSgpCasoPruebas() {
		return this.sgpCasoPruebas;
	}

	public void setSgpCasoPruebas(Set<SgpCasoPrueba> sgpCasoPruebas) {
		this.sgpCasoPruebas = sgpCasoPruebas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sgpArtefacto")
	public Set<SgpMapaArtefacto> getSgpMapaArtefactos() {
		return this.sgpMapaArtefactos;
	}

	public void setSgpMapaArtefactos(Set<SgpMapaArtefacto> sgpMapaArtefactos) {
		this.sgpMapaArtefactos = sgpMapaArtefactos;
	}

	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 11/02/2014
	 * @return the proyecto
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_proyecto", nullable = false)
	public SgpProyecto getProyecto() {
		return proyecto;
	}

	/**
	 * @author <a href="mailto:daniel.saavedra@premize.com">Daniel Saavedra</a>
	 * @since 11/02/2014
	 * @param proyecto the proyecto to set
	 */
	public void setProyecto(SgpProyecto proyecto) {
		this.proyecto = proyecto;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 17/06/2014
	 * @description 
	 * @return
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario", nullable = true)
	public SgpUsuario getUsuario() {
		return usuario;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 17/06/2014
	 * @description 
	 * @param usuario
	 */
	public void setUsuario(SgpUsuario usuario) {
		this.usuario = usuario;
	}

}