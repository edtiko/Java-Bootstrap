/**
 * 
 */
package com.premize.sgp.dto.pruebas;

import com.premize.sgp.dto.BaseEntity;
import com.premize.sgp.modelo.entities.anexo.SgpAnexoHallazgo;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-api
 * @class AnexoHallazgoDTO
 * @since 9/03/2014
 */
public class AnexoHallazgoDTO extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2970395743839233459L;
	
	private Integer id;
	private HallazgoDTO hallazgo;
	private String ruta;
	private String usuarioActual;
	private Integer idHallazgo;
	
	public AnexoHallazgoDTO() {
		super();
	}
	
	public AnexoHallazgoDTO(SgpAnexoHallazgo anexoHallazgo) {
		this.id = anexoHallazgo.getId();
		this.hallazgo = new HallazgoDTO(anexoHallazgo.getSgpHallazgo());
		this.ruta = anexoHallazgo.getRuta();
		setIndActivo(anexoHallazgo.getIndActivo());
		setNumeroEstado(anexoHallazgo.getIndActivo());
		setUsuarioCreacion(anexoHallazgo.getUsuarioCrea());
		setUsuarioCreacion(anexoHallazgo.getUsuarioCrea());
		setFecCreacion(anexoHallazgo.getFecCreacion());
		if (anexoHallazgo.getFecEdita() != null) {
			setFechaEdita(anexoHallazgo.getFecEdita());
		}
		if (anexoHallazgo.getUsuarioEdita() != null) {
			setUsuarioEdita(anexoHallazgo.getUsuarioEdita());
		}
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 9/03/2014
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 9/03/2014
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 9/03/2014
	 * @return the hallazgo
	 */
	public HallazgoDTO getHallazgo() {
		return hallazgo;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 9/03/2014
	 * @param hallazgo
	 *            the hallazgo to set
	 */
	public void setHallazgo(HallazgoDTO hallazgo) {
		this.hallazgo = hallazgo;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 9/03/2014
	 * @return the ruta
	 */
	public String getRuta() {
		return ruta;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 9/03/2014
	 * @param ruta
	 *            the ruta to set
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 5/06/2014 
	 * @return the idHallazgo 
	 */
	public Integer getIdHallazgo() {
		return idHallazgo;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 5/06/2014 
	 * @param idHallazgo the idHallazgo to set 
	 */
	public void setIdHallazgo(Integer idHallazgo) {
		this.idHallazgo = idHallazgo;
	}
	
	

	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto c</a>
	 * @since 9/03/2014
	 * @param the usuarioActual
	 *            
	 */
	public String getUsuarioActual() {
		return usuarioActual;
	}

	/**
	 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto c</a>
	 * @since 9/03/2014
	 * @param usuarioActual
	 *            the usuarioActual to set
	 */
	public void setUsuarioActual(String usuarioActual) {
		this.usuarioActual = usuarioActual;
	}
	
	
}
