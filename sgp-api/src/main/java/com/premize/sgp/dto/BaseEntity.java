/**
 * 
 */
package com.premize.sgp.dto;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-web
 * @class BaseEntity
 * @since 21/01/2014
 */

public abstract class BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 412378452076119273L;
	private static final String formatoFecha = "yyyy/MM/dd HH:mm:ss";
	
	@JsonSerialize
	private Date fecCreacion;
	
	@JsonSerialize
	private Date fechaEdita;
	private Integer numeroEstado;
	private String indActivo;
	private String usuarioCreacion;
	private String usuarioEdita;
	private String fechaCreacionString;
	private String fechaEditaString;
	private String fromFechaFilter;
	private String toFechaFilter;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 30/01/2014
	 * @return the indActivo
	 */
	public String getIndActivo() {
		return indActivo;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 30/01/2014
	 * @param indActivo
	 *            the indActivo to set
	 */
	public void setIndActivo(Integer indActivo) {
		this.indActivo = (indActivo == 1) ? "Activo" : "Inactivo";
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 29/01/2014
	 * @return the fechaCreacion
	 */
	public Date getFecCreacion() {
		return fecCreacion;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 29/01/2014
	 * @param fechaCreacion
	 *            the fechaCreacion to set
	 */
	public void setFecCreacion(Date fechaCreacion) {
		this.fecCreacion = fechaCreacion;
		if (fechaCreacion != null) {
			DateFormat df = new SimpleDateFormat(formatoFecha);
			String fechaTmp = df.format(fechaCreacion);
			setFechaCreacionString(fechaTmp);
		}
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 29/01/2014
	 * @return the fechaEdita
	 */
	public Date getFechaEdita() {
		return fechaEdita;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 29/01/2014
	 * @param fechaEdita
	 *            the fechaEdita to set
	 */
	public void setFechaEdita(Date fechaEdita) {
		this.fechaEdita = fechaEdita;
		if (fechaEdita != null) {
			DateFormat df = new SimpleDateFormat(formatoFecha);
			String fechaTmp = df.format(fechaEdita);
			setFechaEditaString(fechaTmp);
		}
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 29/01/2014
	 * @return the nombreEstado
	 */
	public Integer getNumeroEstado() {
		return numeroEstado;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 13/02/2014
	 * @param estado
	 */
	public void setNumeroEstado(Integer estado) {
		
		this.numeroEstado = estado;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 31/01/2014
	 * @return the usuarioCreacion
	 */
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 31/01/2014
	 * @param usuarioCreacion
	 *            the usuarioCreacion to set
	 */
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 31/01/2014
	 * @return the usuarioEdita
	 */
	public String getUsuarioEdita() {
		return usuarioEdita;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 31/01/2014
	 * @param usuarioEdita
	 *            the usuarioEdita to set
	 */
	public void setUsuarioEdita(String usuarioEdita) {
		this.usuarioEdita = usuarioEdita;
		
	}
	
	public String getFechaCreacionString() {
		return fechaCreacionString;
	}
	
	public void setFechaCreacionString(String fechaCreacionString) {
		this.fechaCreacionString = fechaCreacionString;
	}
	
	public String getFechaEditaString() {
		return fechaEditaString;
	}
	
	public void setFechaEditaString(String fechaEditaString) {
		this.fechaEditaString = fechaEditaString;
	}
	
	/**
	 * @author <a href="mailto:juanm.caicedo@premize.com">Juan Manuel Caicedo</a>
	 * @date Mar 13, 2014
	 * @return the fromFechaFilter
	 */
	public String getFromFechaFilter() {
		return fromFechaFilter;
	}
	
	/**
	 * @author <a href="mailto:juanm.caicedo@premize.com">Juan Manuel Caicedo</a>
	 * @date Mar 13, 2014
	 * @param fromFechaFilter
	 *            the fromFechaFilter to set
	 */
	public void setFromFechaFilter(String fromFechaFilter) {
		this.fromFechaFilter = fromFechaFilter;
	}
	
	/**
	 * @author <a href="mailto:juanm.caicedo@premize.com">Juan Manuel Caicedo</a>
	 * @date Mar 13, 2014
	 * @return the toFechaFilter
	 */
	public String getToFechaFilter() {
		return toFechaFilter;
	}
	
	/**
	 * @author <a href="mailto:juanm.caicedo@premize.com">Juan Manuel Caicedo</a>
	 * @date Mar 13, 2014
	 * @param toFechaFilter
	 *            the toFechaFilter to set
	 */
	public void setToFechaFilter(String toFechaFilter) {
		this.toFechaFilter = toFechaFilter;
	}
	
}
