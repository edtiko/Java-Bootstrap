/**
 * 
 */
package com.premize.sgp.utils;

import java.io.InputStream;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-api
 * @class FilePmz
 * @since 27/03/2014
 *
 */
@JsonIgnoreProperties({ "bytes" })
public class FilePmz {
	
	private String name;
	private int length;
	private String type;
	private byte[] bytes;
	private String ruta;
	private InputStream inputStream;
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 4/02/2014
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 4/02/2014
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 4/02/2014
	 * @return the length
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 4/02/2014
	 * @param length
	 *            the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 4/02/2014
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 4/02/2014
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 4/02/2014
	 * @return the bytes
	 */
	public byte[] getBytes() {
		return bytes;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 4/02/2014
	 * @param bytes
	 *            the bytes to set
	 */
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 4/02/2014
	 * @return the ruta
	 */
	public String getRuta() {
		return ruta;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 4/02/2014
	 * @param ruta
	 *            the ruta to set
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}
	
	/**
	 * @param inputStream
	 *            the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
}
