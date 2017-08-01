package com.premize.sgp.dto.pruebas;


import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.premize.sgp.dto.pruebas.indicadores.IndicadorDTO;

/**
 * 
 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
 * @project sgp-api
 * @class ReporteHallazgoDTO
 * @since 2/04/2014
 *
 */
public class ReporteHallazgoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7975051712779842957L;
	/**
	 * 
	 */
	@JsonSerialize
	private Integer id; 
	@JsonSerialize
	private String nombreSeveridad;
	@JsonSerialize
	private String nombreArtefacto;
	@JsonSerialize
	private String nombreMapaPruebas;
	@JsonSerialize
	private Long cantidad;
	@JsonSerialize
	private String responsable;
	@JsonSerialize
	private Integer bloqueantes;
	@JsonSerialize
	private Integer funcionales;
	@JsonSerialize
	private Integer presentacion;
	
	private Integer totalHallazgos;
	
	private IndicadorDTO indicador;
	
	private Double totalIndicador;
	private String totalIndicadorPorcentaje;
	private Integer pruebaEjecutada;
	
	

	
	public Integer getBloqueantes() {
		return bloqueantes;
	}
	public void setBloqueantes(Integer bloqueantes) {
		this.bloqueantes = bloqueantes;
	}
	public Integer getFuncionales() {
		return funcionales;
	}
	public void setFuncionales(Integer funcionales) {
		this.funcionales = funcionales;
	}
	public Integer getPresentacion() {
		return presentacion;
	}
	public void setPresentacion(Integer presentacion) {
		this.presentacion = presentacion;
	}
	public String getResponsable() {
		return responsable;
	}
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}


	
	
	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 2/04/2014
	 * @return
	 */
	private String getNombreSeveridad() {
		return nombreSeveridad;
	}
	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 2/04/2014
	 * @param nombreHallazgo
	 */
	private void setNombreSeveridad(String nombreSeveridad) {
		this.nombreSeveridad = nombreSeveridad;
	}
	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 2/04/2014
	 * @return
	 */
	private Long getCantidad() {
		return cantidad;
	}
	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 2/04/2014
	 * @param cantidad
	 */
	private void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 2/04/2014
	 * @return
	 */
	private Integer getId() {
		return id;
	}
	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 2/04/2014
	 * @param id
	 */
	private void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 14/04/2014
	 * @return
	 */
	private String getNombreMapaPruebas() {
		return nombreMapaPruebas;
	}
	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 14/04/2014
	 * @param nombreMapaPruebas
	 */
	private void setNombreMapaPruebas(String nombreMapaPruebas) {
		this.nombreMapaPruebas = nombreMapaPruebas;
	}
	
	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
	 * @since 2/04/2014
	 */
	public ReporteHallazgoDTO() {
		
	}
	
	public ReporteHallazgoDTO(Long cantidad, String nombreSeveridad) {
		this.cantidad = cantidad;
		this.nombreSeveridad = nombreSeveridad;
	}
	
	public ReporteHallazgoDTO(Integer id, String nombreSeveridad,
			String nombreArtefacto, String nombreMapaPruebas, Long cantidad,
			String responsable, Integer cantBloqueantes,
			Integer cantFuncionales, Integer cantPresentacion) {
		super();
		this.id = id;
		this.nombreSeveridad = nombreSeveridad;
		this.nombreArtefacto = nombreArtefacto;
		this.nombreMapaPruebas = nombreMapaPruebas;
		this.cantidad = cantidad;
		this.responsable = responsable;
		if(bloqueantes == null){
			this.bloqueantes=0;
		}
		if(funcionales == null){
			this.funcionales=0;
		}
		if(presentacion == null){
			this.presentacion=0;
		}
		this.bloqueantes = cantBloqueantes;
		this.funcionales = cantFuncionales;
		this.presentacion = cantPresentacion;
	}
	public Integer getTotalHallazgos() {
		if(bloqueantes == null){
			bloqueantes=0;
		}
		if(funcionales == null){
			funcionales=0;
		}
		if(presentacion == null){
			presentacion=0;
		}
		
		if(bloqueantes != null  || funcionales != null || presentacion != null ){
			this.totalHallazgos = (bloqueantes + funcionales + presentacion);
			}
			else{
				this.totalHallazgos = 0;
			}
			
			return this.totalHallazgos;
	}

	public IndicadorDTO getIndicador() {
		return indicador;
	}
	public void setIndicador(IndicadorDTO indicador) {
		this.indicador = indicador;
	}
	public Double getTotalIndicador() {
		return totalIndicador;
	}
	public void setTotalIndicador(Double totalIndicador) {
		this.totalIndicador = totalIndicador;
	}
	public String getTotalIndicadorPorcentaje() {
		return totalIndicadorPorcentaje;
	}
	public void setTotalIndicadorPorcentaje(String totalIndicadorPorcentaje) {
		this.totalIndicadorPorcentaje = totalIndicadorPorcentaje;
	}
	public Integer getPruebaEjecutada() {
		return pruebaEjecutada;
	}
	public void setPruebaEjecutada(Integer pruebaEjecutada) {
		this.pruebaEjecutada = pruebaEjecutada;
	}
	
}
