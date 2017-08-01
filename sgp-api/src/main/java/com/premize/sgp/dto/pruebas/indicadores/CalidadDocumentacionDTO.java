package com.premize.sgp.dto.pruebas.indicadores;

import java.io.Serializable;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-api
 * @class CalidadDocumentacionDTO
 * @description
 * @date 4/07/2014
 */
public class CalidadDocumentacionDTO implements Serializable {

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/07/2014
	 */ 
	private static final long serialVersionUID = 8507331427884101904L;
	
	private String artefactoNombre;
	private Integer artefactoId;
	private String severidadNombre;
	private Integer hallazgos;
	private Double puntuacion;
	
	
	
	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/07/2014  
	 */ 
	public CalidadDocumentacionDTO() {
		super();
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/07/2014 
	 * @param artefactoId
	 * @param artefactoNombre
	 * @param severidadNombre
	 * @param hallazgos
	 * @param puntuacion 
	 */ 
	public CalidadDocumentacionDTO(Integer artefactoId, String artefactoNombre,
			String severidadNombre, Integer hallazgos, Double puntuacion) {
		super();
		this.artefactoNombre = artefactoNombre;
		this.artefactoId = artefactoId;
		this.severidadNombre = severidadNombre;
		this.hallazgos = hallazgos;
		this.puntuacion = puntuacion;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/07/2014 
	 * @return the artefactoNombre 
	 */
	public String getArtefactoNombre() {
		return artefactoNombre;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/07/2014 
	 * @param artefactoNombre the artefactoNombre to set 
	 */
	public void setArtefactoNombre(String artefactoNombre) {
		this.artefactoNombre = artefactoNombre;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/07/2014 
	 * @return the artefactoId 
	 */
	public Integer getArtefactoId() {
		return artefactoId;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/07/2014 
	 * @param artefactoId the artefactoId to set 
	 */
	public void setArtefactoId(Integer artefactoId) {
		this.artefactoId = artefactoId;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/07/2014 
	 * @return the severidadNombre 
	 */
	public String getSeveridadNombre() {
		return severidadNombre;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/07/2014 
	 * @param severidadNombre the severidadNombre to set 
	 */
	public void setSeveridadNombre(String severidadNombre) {
		this.severidadNombre = severidadNombre;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/07/2014 
	 * @return the hallazgos 
	 */
	public Integer getHallazgos() {
		
		if (this.hallazgos==null){
			this.hallazgos=0;
		}
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
	
	

}
