/**
 * 
 */
package com.premize.sgp.dto.parametros;

import javax.validation.constraints.Size;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.premize.sgp.dto.BaseEntity;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpIndicadores;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-modelo
 * @class ArtefactoDTO
 * @since 23/01/2014
 */
public class IndicadoresDTO extends BaseEntity {
	
	private static final long serialVersionUID = 8172766632059686397L;
	@JsonSerialize
	private Integer id;
	private String nombre;
	private String fase;
	private String formula;
	private String periodicidad;

	private Float valorMinimo;
	
	private Float valorMaximo;

	
	public IndicadoresDTO() {
		super();
	}
	
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 10/07/2014
	* @param id
	* @param nombre
	* @param fase
	* @param formula
	* @param periodicidad
	* @param valorMinimo
	* @param valorMaximo
	* @param valorMedio
	 */
	public IndicadoresDTO(Integer id, String nombre, String fase,
			String formula, String periodicidad, Float valorMinimo,
			Float valorMaximo, Float valorMedio,Integer numeroEstado) {
		super();
		this.id = id;
		this.nombre = nombre;
	
		this.fase = fase;
		this.formula = formula;
		this.periodicidad = periodicidad;
		this.valorMinimo = valorMinimo;
		this.valorMaximo = valorMaximo;

		setIndActivo(numeroEstado);
		
	}

/**
 * 
* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
* @date 10/07/2014
* @param sgpIndicadores
 */
	public IndicadoresDTO(SgpIndicadores sgpIndicadores) {
		super();
		this.id=sgpIndicadores.getId();
		this.nombre=sgpIndicadores.getNombre();
		this.formula=sgpIndicadores.getFormula();
		this.periodicidad=sgpIndicadores.getPeriodicidad();
		this.fase=sgpIndicadores.getFase();
		this.valorMinimo=sgpIndicadores.getValorMinimo();

		this.valorMaximo=sgpIndicadores.getValorMaximo();
		setIndActivo(sgpIndicadores.getIndActivo());
		setNumeroEstado(sgpIndicadores.getIndActivo());
		setFecCreacion(sgpIndicadores.getFecCreacion());
		setUsuarioCreacion(sgpIndicadores.getUsuarioCrea());
		if (sgpIndicadores.getFecEdita() != null) {
			setFechaEdita(sgpIndicadores.getFecEdita());
		}
		if (sgpIndicadores.getUsuarioEdita() != null) {
			setUsuarioEdita(sgpIndicadores.getUsuarioEdita());
		}
	}


	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 9/07/2014
	* @return
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 9/07/2014
	* @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 9/07/2014
	* @return
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 9/07/2014
	* @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 9/07/2014
	* @return
	 */
	public String getFase() {
		return fase;
	}
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 9/07/2014
	* @param fase
	 */
	public void setFase(String fase) {
		this.fase = fase;
	}
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 9/07/2014
	* @return
	 */
	public String getFormula() {
		return formula;
	}
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 9/07/2014
	* @param formula
	 */
	public void setFormula(String formula) {
		this.formula = formula;
	}
	/**
	 * @return the periodicidad
	 */
	public String getPeriodicidad() {
		return periodicidad;
	}
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 9/07/2014
	* @param periodicidad
	 */
	public void setPeriodicidad(String periodicidad) {
		this.periodicidad = periodicidad;
	}
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 9/07/2014
	* @return
	 */
	public Float getValorMinimo() {
		return valorMinimo;
	}
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 9/07/2014
	* @param valorMinimo
	 */
	public void setValorMinimo(Float valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	
	
	/**
	 * @return the valorMaximo
	 */
	public Float getValorMaximo() {
		return valorMaximo;
	}

	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 9/07/2014
	* @param valorMaximo
	 */
	public void setValorMaximo(Float valorMaximo) {
		this.valorMaximo = valorMaximo;
	}


	

	
}
