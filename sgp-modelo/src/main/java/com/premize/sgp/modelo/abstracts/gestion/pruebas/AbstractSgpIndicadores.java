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
public abstract class AbstractSgpIndicadores implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7700449181888317861L;
	private Integer id;
	private String nombre;
	private String formula;
	private String fase;
	private String periodicidad;
	private Float valorMinimo;
	private Float valorMedio;
	private Float valorMaximo;
	private Integer indActivo;
	private Date fecCreacion;
	private Date fecEdita;
	private String usuarioCrea;
	private String usuarioEdita;
	static final String TABLE_NAME = "sgp_indicadores";

	// Constructors

	/** default constructor */
	public AbstractSgpIndicadores() {
	}

	/** minimal constructor */
	public AbstractSgpIndicadores(Integer idIndicadores, String nombre, Integer indActivo,
			Date fecCreacion, String usuarioCrea) {
		this.id = idIndicadores;
		this.nombre = nombre;
		this.indActivo = indActivo;
		this.fecCreacion = fecCreacion;
		this.usuarioCrea = usuarioCrea;
	}

	/** full constructor */
	public AbstractSgpIndicadores(Integer idIndicadores, String nombre,
			String fase,String formula,String periodicidad,
			Integer indActivo, Date fecCreacion, Date fecEdita,
			String usuarioCrea, String usuarioEdita) {
		this.id = idIndicadores;
		this.nombre = nombre;
		this.fase=fase;
		this.formula=formula;
		this.periodicidad=periodicidad;
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
	@Column(name = "id_indicadores", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer idPais) {
		this.id = idPais;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Column(name = "fase", nullable = false)
	public String getFase() {
		return this.fase;
	}

	public void setFase(String fase) {
		this.fase = fase;
	}
	@Column(name = "formula", nullable = false)
	public String getFormula() {
		return this.formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	@Column(name = "periodicidad", nullable = false)
	public String getPeriodicidad() {
		return this.periodicidad;
	}

	public void setPeriodicidad(String periodicidad) {
		this.periodicidad = periodicidad;
	}

	@Column(name = "valor_minimo", nullable = false)
	public Float getValorMinimo() {
		return this.valorMinimo;
	}

	public void setValorMinimo(Float valorMinimo) {
		this.valorMinimo = valorMinimo;
	}
	@Column(name = "valor_medio", nullable = false)
	public Float getValorMedio() {
		return this.valorMedio;
	}

	public void setValorMedio(Float valorMedio) {
		this.valorMedio = valorMedio;
	}
	
	@Column(name = "valor_maximo", nullable = false)
	public Float getValorMaximo() {
		return this.valorMaximo;
	}

	public void setValorMaximo(Float valorMaximo) {
		this.valorMaximo = valorMaximo;
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

	

}