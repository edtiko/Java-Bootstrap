package com.premize.sgp.dto.pruebas;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.premize.sgp.dto.BaseEntity;

public class ConsultaHallazgoDTO extends BaseEntity{


	/**
	 * 
	 */
	private static final long serialVersionUID = -5815547772835268208L;
	private Integer id;
	private String artefacto;
	private String usuarioArtefacto;
	private String tipoHallazgo;
	@JsonSerialize
	private Integer causaGeneracion;
	private String causaGeneraString;
	private String severidad;
	private String prioridad;
	@JsonSerialize
	private Integer causaAnulacion;
	private String causaAnulaString;
	@JsonSerialize
	private String usuarioAsignado;
	@JsonSerialize
	private Integer usuarioAsigna;
	private String proyecto;
	@JsonSerialize
	private String mapaPrueba;
	private String titulo;
	private String descripcion;
	private String usuarioCrea;
	private String accion;
	private Date fechaCreacion;
	private Date fechaEdita;
	private Integer indBandeja = 0;
	private Integer casoPruebaId;
	private String motivoReasignacion;
	private String usuarioSoluciona;
	private String color;
	
	public ConsultaHallazgoDTO(){
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getArtefacto() {
		return artefacto;
	}
	public void setArtefacto(String artefacto) {
		this.artefacto = artefacto;
	}
	public String getTipoHallazgo() {
		return tipoHallazgo;
	}
	public void setTipohallazgo(String tipoHallazgo) {
		this.tipoHallazgo = tipoHallazgo;
	}
	public Integer getCausageneracion() {
		return causaGeneracion;
	}
	public void setCausageneracion(Integer causaGeneracion) {
		this.causaGeneracion = causaGeneracion;
	}
	public String getSeveridad() {
		return severidad;
	}
	public void setSeveridad(String severidad) {
		this.severidad = severidad;
	}
	public String getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	public Integer getCausaanulacion() {
		return causaAnulacion;
	}
	public void setCausaanulacion(Integer causaAnulacion) {
		this.causaAnulacion = causaAnulacion;
	}
	public String getUsuarioasignado() {
		return usuarioAsignado;
	}
	public void setUsuarioasignado(String usuarioAsignado) {
		this.usuarioAsignado = usuarioAsignado;
	}
	public Integer getUsuarioasigna() {
		return usuarioAsigna;
	}
	public void setUsuarioasigna(Integer usuarioAsigna) {
		this.usuarioAsigna = usuarioAsigna;
	}
	public String getProyecto() {
		return proyecto;
	}
	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}
	public String getMapaprueba() {
		return mapaPrueba;
	}
	public void setMapaprueba(String mapaPrueba) {
		this.mapaPrueba = mapaPrueba;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUsuariocrea() {
		return usuarioCrea;
	}
	public void setUsuariocrea(String usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public Date getFechacreacion() {
		return fechaCreacion;
	}
	public void setFechacreacion(Date fechaCreacion) {
		setFecCreacion(fechaCreacion);
	}

	public Integer getIndBandeja() {
		return indBandeja;
	}

	public void setIndBandeja(Integer indBandeja) {
		this.indBandeja = indBandeja;
	}

	public Integer getCasoPruebaId() {
		return casoPruebaId;
	}

	public void setCasoPruebaId(Integer casoPruebaId) {
		this.casoPruebaId = casoPruebaId;
	}
	
	public Date getFechaedita() {
		return fechaEdita;
	}

	public void setFechaedita(Date fechaEdita) {

		setFechaEdita(fechaEdita);
	}

	public String getCausagenerastring() {
		return causaGeneraString;
	}

	public void setCausagenerastring(String causaGeneraString) {
		this.causaGeneraString = causaGeneraString;
	}

	public String getCausaanulastring() {
		return causaAnulaString;
	}

	public void setCausaanulastring(String causaAnulaString) {
		this.causaAnulaString = causaAnulaString;
	}

	public String getMotivoreasignacion() {
		return motivoReasignacion;
	}

	public void setMotivoreasignacion(String motivoReasignacion) {
		this.motivoReasignacion = motivoReasignacion;
	}

	public String getUsuarioSoluciona() {
		return usuarioSoluciona;
	}

	public void setUsuariosoluciona(String usuarioSoluciona) {
		this.usuarioSoluciona = usuarioSoluciona;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 18/06/2014 
	 * @return the usuarioArtefacto 
	 */
	public String getUsuarioArtefacto() {
		return usuarioArtefacto;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 18/06/2014 
	 * @param usuarioArtefacto the usuarioArtefacto to set 
	 */
	public void setUsuarioartefacto(String usuarioArtefacto) {
		this.usuarioArtefacto = usuarioArtefacto;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	
}
