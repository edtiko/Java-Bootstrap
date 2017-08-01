package com.premize.sgp.dto.pruebas.indicadores;

import java.io.Serializable;
import java.util.Map;

import com.premize.sgp.dto.parametros.IndicadoresDTO;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-api
 * @class IndCalidadDocumentacionDTO
 * @description
 * @date 3/07/2014
 */
public class IndCalidadDocumentacionDTO implements Serializable {

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 3/07/2014
	 */ 
	private static final long serialVersionUID = 8507331427884101904L;
	
	private String artefactoNombre;
	private Integer artefactoId;
	private Map<String, HallazgoPuntuacionDTO> mapSeveridad;
    private Double indicador;
    private String indicadorString;
    private Integer semaforo;
    private String semaforoImage;
    private IndicadoresDTO parametroIndicador;
	
	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 3/07/2014  
	 */ 
	public IndCalidadDocumentacionDTO() {
		super();
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/07/2014 
	 * @param mapSeveridad 
	 */ 
	public IndCalidadDocumentacionDTO(Map<String, HallazgoPuntuacionDTO> mapSeveridad) {
		super();
		this.mapSeveridad = mapSeveridad;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/07/2014 
	 * @param artefactoNombre
	 * @param mapSeveridad 
	 */ 
	public IndCalidadDocumentacionDTO(String artefactoNombre,
			Map<String, HallazgoPuntuacionDTO> mapSeveridad) {
		super();
		this.artefactoNombre = artefactoNombre;
		this.mapSeveridad = mapSeveridad;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 16/07/2014 
	 * @param parametroIndicador 
	 */ 
	public IndCalidadDocumentacionDTO(IndicadoresDTO parametroIndicador) {
		super();
		this.parametroIndicador = parametroIndicador;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/07/2014 
	 * @param artefactoNombre
	 * @param artefactoId 
	 */ 
	public IndCalidadDocumentacionDTO(String artefactoNombre,
			Integer artefactoId) {
		super();
		this.artefactoNombre = artefactoNombre;
		this.artefactoId = artefactoId;
	}
	
	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 3/07/2014 
	 * @return the artefactoNombre 
	 */
	public String getArtefactoNombre() {
		return artefactoNombre;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 3/07/2014 
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
	 * @return the mapSeveridad 
	 */
	public Map<String, HallazgoPuntuacionDTO> getMapSeveridad() {
		return mapSeveridad;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 4/07/2014 
	 * @param mapSeveridad the mapSeveridad to set 
	 */
	public void setMapSeveridad(Map<String, HallazgoPuntuacionDTO> mapSeveridad) {
		this.mapSeveridad = mapSeveridad;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 8/07/2014 
	 * @return the indicador 
	 */
	public Double getIndicador() {
		return indicador;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 8/07/2014 
	 * @param indicador the indicador to set 
	 */
	public void setIndicador(Double indicador) {
		this.indicador = indicador;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 8/07/2014 
	 * @return the indicadorString 
	 */
	public String getIndicadorString() {
		return indicadorString;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 8/07/2014 
	 * @param indicadorString the indicadorString to set 
	 */
	public void setIndicadorString(String indicadorString) {
		this.indicadorString = indicadorString;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 8/07/2014 
	 * @return the semaforo 
	 */
	public Integer getSemaforo() {
		return semaforo;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 8/07/2014 
	 * @param semaforo the semaforo to set 
	 */
	public void setSemaforo(Integer semaforo) {
		this.semaforo = semaforo;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 8/07/2014 
	 * @return the semaforoImage 
	 */
	public String getSemaforoImage() {
		return semaforoImage;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 8/07/2014 
	 * @param semaforoImage the semaforoImage to set 
	 */
	public void setSemaforoImage(String semaforoImage) {
		this.semaforoImage = semaforoImage;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 16/07/2014 
	 * @return the parametroIndicador 
	 */
	public IndicadoresDTO getParametroIndicador() {
		return parametroIndicador;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 16/07/2014 
	 * @param parametroIndicador the parametroIndicador to set 
	 */
	public void setParametroIndicador(IndicadoresDTO parametroIndicador) {
		this.parametroIndicador = parametroIndicador;
	}
	
}
