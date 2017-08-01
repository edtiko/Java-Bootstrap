package com.premize.sgp.dto.pruebas.indicadores;

import java.io.Serializable;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-api
 * @class IndicadorDTO
 * @description
 * @date 9/07/2014
 */
public class IndicadorDTO implements Serializable {
	
	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014
	 */ 
	private static final long serialVersionUID = 1880031040529426273L;
	
	private Double indicador;
	private String indicadorString;
	private Integer semaforo;
	private String semaforoImage;
	
	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014  
	 */ 
	public IndicadorDTO() {
		super();
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @param indicador
	 * @param indicadorString
	 * @param semaforoImage 
	 */ 
	public IndicadorDTO(Double indicador, String indicadorString,
			String semaforoImage) {
		super();
		this.indicador = indicador;
		this.indicadorString = indicadorString;
		this.semaforoImage = semaforoImage;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @param indicador
	 * @param indicadorString
	 * @param semaforo
	 * @param semaforoImage 
	 */ 
	public IndicadorDTO(Double indicador, String indicadorString,
			Integer semaforo, String semaforoImage) {
		super();
		this.indicador = indicador;
		this.indicadorString = indicadorString;
		this.semaforo = semaforo;
		this.semaforoImage = semaforoImage;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @return the indicador 
	 */
	public Double getIndicador() {
		return indicador;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @param indicador the indicador to set 
	 */
	public void setIndicador(Double indicador) {
		this.indicador = indicador;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @return the indicadorString 
	 */
	public String getIndicadorString() {
		return indicadorString;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @param indicadorString the indicadorString to set 
	 */
	public void setIndicadorString(String indicadorString) {
		this.indicadorString = indicadorString;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @return the semaforo 
	 */
	public Integer getSemaforo() {
		return semaforo;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @param semaforo the semaforo to set 
	 */
	public void setSemaforo(Integer semaforo) {
		this.semaforo = semaforo;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @return the semaforoImage 
	 */
	public String getSemaforoImage() {
		return semaforoImage;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @param semaforoImage the semaforoImage to set 
	 */
	public void setSemaforoImage(String semaforoImage) {
		this.semaforoImage = semaforoImage;
	}
	
	
	
}
