package com.premize.sgp.dto.pruebas;

import java.io.Serializable;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-api
 * @class TotalesHallazgoDTO
 * @since 14/04/2014
 *
 */
public class TotalesHallazgoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -868678645322417470L;
	
	private String severidad;
	private String tipoHallazgo;
	private Integer totalHallazgos;
	private Integer totalAbierto;
	private Integer totalDevuelta;
	private Integer totalCerrado;
	private Integer totalAnulado;
	private Integer totalInvalidado;
	private Integer totalReproceso;
	private Integer totalDesarrollo;
	private String  usuarioAsignado;
	private String cargoUsuario;
	private Integer totalBloqueantes;
	private Integer totalFuncionales;
	private Integer totalPresentacion;
	
	public String getUsuarioAsignado() {
		return usuarioAsignado;
	}
	public void setUsuarioAsignado(String usuarioAsignado) {
		this.usuarioAsignado = usuarioAsignado;
	}
	public Integer getTotalBloqueantes() {
		return totalBloqueantes;
	}
	public void setTotalBloqueantes(Integer totalBloqueantes) {
		this.totalBloqueantes = totalBloqueantes;
	}
	public Integer getTotalFuncionales() {
		return totalFuncionales;
	}
	public void setTotalFuncionales(Integer totalFuncionales) {
		this.totalFuncionales = totalFuncionales;
	}
	public Integer getTotalPresentacion() {
		return totalPresentacion;
	}
	public void setTotalPresentacion(Integer totalPresentacion) {
		this.totalPresentacion = totalPresentacion;
	}
	public String getSeveridad() {
		return severidad;
	}
	public void setSeveridad(String severidad) {
		this.severidad = severidad;
	}
	public Integer getTotalHallazgos() {
		return totalHallazgos;
	}
	public void setTotalHallazgos(Integer totalHallazgos) {
		this.totalHallazgos = totalHallazgos;
	}
	public Integer getTotalAbierto() {
		return totalAbierto;
	}
	public void setTotalAbierto(Integer totalAbierto) {
		this.totalAbierto = totalAbierto;
	}
	public Integer getTotalDevuelta() {
		return totalDevuelta;
	}
	public void setTotalDevuelta(Integer totalDevuelta) {
		this.totalDevuelta = totalDevuelta;
	}
	public Integer getTotalCerrado() {
		return totalCerrado;
	}
	public void setTotalCerrado(Integer totalCerrado) {
		this.totalCerrado = totalCerrado;
	}
	public Integer getTotalAnulado() {
		return totalAnulado;
	}
	public void setTotalAnulado(Integer totalAnulado) {
		this.totalAnulado = totalAnulado;
	}
	public Integer getTotalInvalidado() {
		return totalInvalidado;
	}
	public void setTotalInvalidado(Integer totalInvalidado) {
		this.totalInvalidado = totalInvalidado;
	}
	public Integer getTotalReproceso() {
		return totalReproceso;
	}
	public void setTotalReproceso(Integer totalReproceso) {
		this.totalReproceso = totalReproceso;
	}
	public Integer getTotalDesarrollo() {
		return totalDesarrollo;
	}
	public void setTotalDesarrollo(Integer totalDesarrollo) {
		this.totalDesarrollo = totalDesarrollo;
	}
	public String getCargoUsuario() {
		return cargoUsuario;
	}
	public void setCargoUsuario(String cargoUsuario) {
		this.cargoUsuario = cargoUsuario;
	}
	public String getTipoHallazgo() {
		return tipoHallazgo;
	}
	public void setTipoHallazgo(String tipoHallazgo) {
		this.tipoHallazgo = tipoHallazgo;
	}
}
