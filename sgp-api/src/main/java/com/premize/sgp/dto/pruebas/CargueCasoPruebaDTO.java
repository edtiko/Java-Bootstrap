package com.premize.sgp.dto.pruebas;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="mailto:hernand.ramirez@premize.com">Hernan David Ramirez Mejia</a>
 * @project sgp-api
 * @class CargueCasoPruebaDTO
 * @since 20/02/2014
 */
public class CargueCasoPruebaDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3905133380354581656L;
	
	private List<CasoDePruebaDTO> listaDatosCargados;
	private List<CasoDePruebaDTO> listaDatosCargadosFallidos;
	
	/**
	 * si el estado es 0 se presento fallos al cargar el archivo y se debe de leer es datosCargadosFallidos, pero si el
	 * estado es 1 no se presento errores al cargar y se debe guardar los registros de datosCargados
	 */
	private Integer estadoCargueArchivo;
	
	private Integer registrosLeidos;
	private Integer registrosConFallos;
	private Integer registrosCargadosBien;
	private String msgFalloCargue;
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 10/03/2014
	 * @return the msgFalloCargue
	 */
	public String getMsgFalloCargue() {
		return msgFalloCargue;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 10/03/2014
	 * @param msgFalloCargue
	 *            the msgFalloCargue to set
	 */
	public void setMsgFalloCargue(String msgFalloCargue) {
		this.msgFalloCargue = msgFalloCargue;
	}
	
	public List<CasoDePruebaDTO> getListaDatosCargados() {
		return listaDatosCargados;
	}
	
	public void setListaDatosCargados(List<CasoDePruebaDTO> datosCargados) {
		this.listaDatosCargados = datosCargados;
	}
	
	public List<CasoDePruebaDTO> getListaDatosCargadosFallidos() {
		return listaDatosCargadosFallidos;
	}
	
	public void setListaDatosCargadosFallidos(List<CasoDePruebaDTO> datosCargadosFallidos) {
		this.listaDatosCargadosFallidos = datosCargadosFallidos;
	}
	
	public Integer getEstadoCargueArchivo() {
		return estadoCargueArchivo;
	}
	
	public void setEstadoCargueArchivo(Integer estadoCargueArchivo) {
		this.estadoCargueArchivo = estadoCargueArchivo;
	}
	
	public Integer getRegistrosLeidos() {
		return registrosLeidos;
	}
	
	public void setRegistrosLeidos(Integer registrosLeidos) {
		this.registrosLeidos = registrosLeidos;
	}
	
	public Integer getRegistrosConFallos() {
		return registrosConFallos;
	}
	
	public void setRegistrosConFallos(Integer registrosConFallos) {
		this.registrosConFallos = registrosConFallos;
	}
	
	public Integer getRegistrosCargadosBien() {
		return registrosCargadosBien;
	}
	
	public void setRegistrosCargadosBien(Integer registrosCargadosBien) {
		this.registrosCargadosBien = registrosCargadosBien;
	}
	
}
