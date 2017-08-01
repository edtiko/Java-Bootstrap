package com.premize.sgp.dto.pruebas.indicadores;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-api
 * @class HallazgoPuntuacionDTO
 * @description
 * @date 4/07/2014
 */
public class HallazgoPuntuacionDTO implements Serializable {

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/07/2014
	 */ 
	private static final long serialVersionUID = 4954677176370975547L;
	private static final DecimalFormat f = new DecimalFormat("##.00");
	private Integer hallazgos;
    private Double puntuacion;
    private String puntuacionDouble;
	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/07/2014  
	 */ 
	public HallazgoPuntuacionDTO() {
		super();
//		hallazgos = 0;
//		puntuacion = 0d;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/07/2014 
	 * @param hallazgos
	 * @param puntuacion 
	 */ 
	public HallazgoPuntuacionDTO(Integer hallazgos, Double puntuacion) {
		super();
		this.hallazgos = hallazgos;
		this.puntuacion = puntuacion;
        this.puntuacionDouble= (f.format(puntuacion));
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/07/2014 
	 * @return the hallazgos 
	 */
	public Integer getHallazgos() {
		return hallazgos;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/07/2014 
	 * @param hallazgos the hallazgos to set 
	 */
	public void setHallazgos(Integer hallazgos) {
		this.hallazgos = hallazgos;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/07/2014 
	 * @return the puntuacion 
	 */
	public Double getPuntuacion() {
		return puntuacion;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/07/2014 
	 * @param puntuacion the puntuacion to set 
	 */
	public void setPuntuacion(Double puntuacion) {
		this.puntuacion = puntuacion;
	}
    
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/07/2014
	 * @description 
	 * @param value
	 */
    public void sumHallazgos(Integer value) {
    	if(hallazgos==null){
    		hallazgos=0;
    	}
        hallazgos += value;
    }
    
    /**
     * 
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
     * @date 4/07/2014
     * @description 
     * @param value
     */
    public void sumPuntuacion(Double value) {
    	if(puntuacion==null){
    		puntuacion=0D;
    	}
        puntuacion += value;
        this.puntuacionDouble= (f.format(puntuacion));
    }

	public String getPuntuacionDouble() {
		return puntuacionDouble;
	}

	public void setPuntuacionDouble(String puntuacionDouble) {
		this.puntuacionDouble = puntuacionDouble;
	}    
    

}
