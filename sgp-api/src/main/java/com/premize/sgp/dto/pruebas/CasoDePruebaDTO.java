/**
 * 
 */
package com.premize.sgp.dto.pruebas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.premize.sgp.dto.BaseEntity;
import com.premize.sgp.dto.parametros.ArtefactoDTO;
import com.premize.sgp.dto.parametros.ParametroDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpCasoPrueba;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-api
 * @class ParametroDTO
 * @since 21/01/2014
 */
public class CasoDePruebaDTO extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -349018818090205525L;
	
	private Integer id;
	private String descripcion;
	private String resultado;
	private String cumple;
	@Valid
	private ArtefactoDTO artefacto;
	@Valid
	private MapaPruebasDTO mapaPrueba;
	@NotNull
	private ParametroDTO tipoPrueba;
	@Size(min = 1, max = 5000)
	private List<String> erroresCargue;
	private String usuarioEjecuta;
	private Date fechaEjecuta;
	private String fechaEjecutaString;
	private Integer consecutivo;
	
	/**
	 * Utilizado para guardar el numero de registro en el cargue de archivo
	 */
	private Integer numeroRegistro;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 21/01/2014
	 * @return the id
	 */
	public Integer getId() {
		return id;
		
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 21/01/2014
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getResultado() {
		return resultado;
	}
	
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	public String getCumple() {
		return cumple;
	}
	
	public void setCumple(String cumple) {
		this.cumple = cumple;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 21/01/2014
	 */
	public CasoDePruebaDTO() {
		
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 21/01/2014
	 * @param descripcion
	 * @param nombre
	 * @param valor
	 */
	public CasoDePruebaDTO(String descripcion, String nombre, String valor) {
		super();
		this.descripcion = descripcion;
		
	}
	
	public CasoDePruebaDTO(String descripcion, Integer id) {
		super();
		this.descripcion = descripcion;
		
		this.id = id;
	}
	
	public CasoDePruebaDTO(SgpCasoPrueba casoPrueba) {
		
		this.id = casoPrueba.getId();
		this.descripcion = (casoPrueba.getDescripcion() != null) ? casoPrueba.getDescripcion() : "";
		this.artefacto = new ArtefactoDTO(casoPrueba.getSgpArtefacto());
		this.mapaPrueba = new MapaPruebasDTO(casoPrueba.getSgpMapaPrueba());
		this.resultado = casoPrueba.getResultado();
		this.tipoPrueba = new ParametroDTO(casoPrueba.getTipoPrueba());
		this.cumple = casoPrueba.getCumple();
		setFecCreacion(casoPrueba.getFecCreacion());
		setUsuarioCreacion(casoPrueba.getUsuarioCrea());
		if (casoPrueba.getFecEdita() != null) {
			setFechaEdita(casoPrueba.getFecEdita());
		}
		if (casoPrueba.getUsuarioEdita() != null) {
			setUsuarioEdita(casoPrueba.getUsuarioEdita());
		}
		
		this.usuarioEjecuta = (casoPrueba.getUsuarioEjecuta() != null) ? casoPrueba.getUsuarioEjecuta() : "";
		setFechaEjecuta((casoPrueba.getFecEjecuta() != null) ? casoPrueba.getFecEjecuta() : null);
		this.consecutivo = casoPrueba.getConsecutivo();
	}
	
	public ArtefactoDTO getArtefacto() {
		return artefacto;
	}
	
	public void setArtefacto(ArtefactoDTO artefacto) {
		this.artefacto = artefacto;
	}
	
	public MapaPruebasDTO getMapaPrueba() {
		return mapaPrueba;
	}
	
	public void setMapaPrueba(MapaPruebasDTO mapaPrueba) {
		this.mapaPrueba = mapaPrueba;
	}
	
	public ParametroDTO getTipoPrueba() {
		return tipoPrueba;
	}
	
	public void setTipoPrueba(ParametroDTO tipoPrueba) {
		this.tipoPrueba = tipoPrueba;
	}
	
	public List<String> getErroresCargue() {
		return erroresCargue;
	}
	
	public void setErroresCargue(List<String> erroresCargue) {
		this.erroresCargue = erroresCargue;
	}
	
	public String getUsuarioEjecuta() {
		return usuarioEjecuta;
	}
	
	public void setUsuarioEjecuta(String usuarioEjecuta) {
		this.usuarioEjecuta = usuarioEjecuta;
	}
	
	public Date getFechaEjecuta() {
		return fechaEjecuta;
	}
	
	public void setFechaEjecuta(Date fechaEjecuta) {
		this.fechaEjecuta = fechaEjecuta;
		if (fechaEjecuta != null) {
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String fechaTmp = df.format(fechaEjecuta);
			setFechaEjecutaString(fechaTmp);
		}
	}
	
	public String getFechaEjecutaString() {
		return fechaEjecutaString;
	}
	
	public void setFechaEjecutaString(String fechaEjecutaString) {
		this.fechaEjecutaString = fechaEjecutaString;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 10/03/2014
	 * @return the numeroRegistro
	 */
	public Integer getNumeroRegistro() {
		return numeroRegistro;
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 10/03/2014
	 * @param numeroRegistro
	 *            the numeroRegistro to set
	 */
	public void setNumeroRegistro(Integer numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 3/06/2014 
	 * @return the consecutivo 
	 */
	public Integer getConsecutivo() {
		return consecutivo;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 3/06/2014 
	 * @param consecutivo the consecutivo to set 
	 */
	public void setConsecutivo(Integer consecutivo) {
		this.consecutivo = consecutivo;
	}
	
	
}
