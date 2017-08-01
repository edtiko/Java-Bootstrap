package com.premize.sgp.dto.pruebas.indicadores;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-api
 * @class IndicadoresProyectoDTO
 * @description
 * @date 7/07/2014
 */
public class IndicadoresProyectoDTO implements Serializable {
	
	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 7/07/2014
	 */ 
	private static final long serialVersionUID = 3800497363111405096L;
	
	private Map<Integer, IndCalidadDocumentacionDTO> indicadorCalidadDocumentacion;
	private List<IndCalidadFuncionalidadDTO> indicadorCalidadFuncionalidad;
	private IndReprocesoConstruccionPruebasDTO indicadorReprocesoConstruccionPruebas;
	private IndEfectividadHallazgosReportadosDTO indicadorEfectividadHallazgosReportados;
	private List<IndMejorasIdentificadasDTO> indicadorMejorasIdentificadas;
	private IndTiemposRespuestaGarantiaDTO indicadorTiemposRespuestaGarantia;

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 7/07/2014  
	 */ 
	public IndicadoresProyectoDTO() {
		super();
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 7/07/2014 
	 * @return the indicadorCalidadDocumentacion 
	 */
	public Map<Integer, IndCalidadDocumentacionDTO> getIndicadorCalidadDocumentacion() {
		return indicadorCalidadDocumentacion;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 7/07/2014 
	 * @param indicadorCalidadDocumentacion the indicadorCalidadDocumentacion to set 
	 */
	public void setIndicadorCalidadDocumentacion(
			Map<Integer, IndCalidadDocumentacionDTO> indicadorCalidadDocumentacion) {
		this.indicadorCalidadDocumentacion = indicadorCalidadDocumentacion;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @return the indicadorCalidadFuncionalidad 
	 */
	public List<IndCalidadFuncionalidadDTO> getIndicadorCalidadFuncionalidad() {
		return indicadorCalidadFuncionalidad;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014 
	 * @param indicadorCalidadFuncionalidad the indicadorCalidadFuncionalidad to set 
	 */
	public void setIndicadorCalidadFuncionalidad(
			List<IndCalidadFuncionalidadDTO> indicadorCalidadFuncionalidad) {
		this.indicadorCalidadFuncionalidad = indicadorCalidadFuncionalidad;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014 
	 * @return the indicadorReprocesoConstruccionPruebas 
	 */
	public IndReprocesoConstruccionPruebasDTO getIndicadorReprocesoConstruccionPruebas() {
		return indicadorReprocesoConstruccionPruebas;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014 
	 * @param indicadorReprocesoConstruccionPruebas the indicadorReprocesoConstruccionPruebas to set 
	 */
	public void setIndicadorReprocesoConstruccionPruebas(
			IndReprocesoConstruccionPruebasDTO indicadorReprocesoConstruccionPruebas) {
		this.indicadorReprocesoConstruccionPruebas = indicadorReprocesoConstruccionPruebas;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014 
	 * @return the indicadorEfectividadHallazgosReportados 
	 */
	public IndEfectividadHallazgosReportadosDTO getIndicadorEfectividadHallazgosReportados() {
		return indicadorEfectividadHallazgosReportados;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014 
	 * @param indicadorEfectividadHallazgosReportados the indicadorEfectividadHallazgosReportados to set 
	 */
	public void setIndicadorEfectividadHallazgosReportados(
			IndEfectividadHallazgosReportadosDTO indicadorEfectividadHallazgosReportados) {
		this.indicadorEfectividadHallazgosReportados = indicadorEfectividadHallazgosReportados;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @return the indicadorMejorasIdentificadas 
	 */
	public List<IndMejorasIdentificadasDTO> getIndicadorMejorasIdentificadas() {
		return indicadorMejorasIdentificadas;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 15/07/2014 
	 * @param indicadorMejorasIdentificadas the indicadorMejorasIdentificadas to set 
	 */
	public void setIndicadorMejorasIdentificadas(
			List<IndMejorasIdentificadasDTO> indicadorMejorasIdentificadas) {
		this.indicadorMejorasIdentificadas = indicadorMejorasIdentificadas;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 16/07/2014 
	 * @return the indicadorTiemposRespuestaGarantia 
	 */
	public IndTiemposRespuestaGarantiaDTO getIndicadorTiemposRespuestaGarantia() {
		return indicadorTiemposRespuestaGarantia;
	}

	/** 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 16/07/2014 
	 * @param indicadorTiemposRespuestaGarantia the indicadorTiemposRespuestaGarantia to set 
	 */
	public void setIndicadorTiemposRespuestaGarantia(
			IndTiemposRespuestaGarantiaDTO indicadorTiemposRespuestaGarantia) {
		this.indicadorTiemposRespuestaGarantia = indicadorTiemposRespuestaGarantia;
	}
	
	
}
