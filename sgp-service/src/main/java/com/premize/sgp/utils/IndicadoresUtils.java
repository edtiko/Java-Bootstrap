package com.premize.sgp.utils;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.api.util.DateUtils;
import com.premize.sgp.dto.parametros.IndicadoresDTO;
import com.premize.sgp.dto.pruebas.TipoPrioridadDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndicadorDTO;

/**
 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a>
 * @project sgp-service
 * @class IndicadoresUtils
 * @description
 * @date 10/07/2014
 */
public class IndicadoresUtils {
	
	public static final String FORMATO_FECHA = "yyyy/MM/dd";
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
	public static final int SEMAFORO_OK = 1;
	public static final int SEMAFORO_WARNING = 2;
	public static final int SEMAFORO_ERROR = 3;
	
	public static final String SEMAFORO_STR_OK = "semaforo3.png";
	public static final String SEMAFORO_STR_WARNING = "semaforo2.png";
	public static final String SEMAFORO_STR_ERROR = "semaforo1.png";
	public static final String SEMAFORO_STR_EMPTY = "";
	
	
    /**
     * 
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
     * @date 8/07/2014
     * @description 
     * @param puntuacion
     * @param puntosPosibles
     * @return
     */
	public static Double calculateIndicadorCalidadDocumentacion(Double puntuacion, Double puntosPosibles) {
    	return (puntosPosibles - puntuacion) / puntosPosibles;
    }
    
    /**
     * 
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
     * @date 9/07/2014
     * @description 
     * @param pruebasEjecutadas
     * @param hallazgosReportados
     * @param hallazgosAnulados
     * @param hallazgosInvalidados
     * @return
     */
    public static Double calculateIndicadorCalidadFuncionalidad(Integer pruebasEjecutadas, Integer hallazgosReportados,
    		Integer hallazgosAnulados, Integer hallazgosInvalidados) {
    	if(pruebasEjecutadas != null && pruebasEjecutadas != 0) {
    		Integer divisor = (pruebasEjecutadas - (hallazgosReportados - (hallazgosInvalidados + hallazgosAnulados)));
    		return divisor.doubleValue() / pruebasEjecutadas.doubleValue();
    	}
    	return 0d;
    }
    
    /**
     * 
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
     * @date 16/07/2014
     * @description 
     * @param tiempoPrioridad
     * @param fechaSolicitud
     * @param fechaRespuesta
     * @return
     * @throws AppBaseException
     */
    public static Double calculateIndicadorTiemposRespuestaGarantia(Double tiempoPrioridad, 
    		Date fechaSolicitud, Date fechaRespuesta) throws AppBaseException {
    	return dateDifferenceInHours(fechaRespuesta,fechaSolicitud);
    }
    
    /**
     * 
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
     * @date 14/07/2014
     * @description 
     * @param parametro
     * @param indicador
     * @return
     */
    public static IndicadorDTO getIndicadorDTO(IndicadoresDTO parametro, Double indicador) {
    	String indicadorString = DECIMAL_FORMAT.format(indicador * 100) + "%";
    	String semaforoImage = getSemaforoImageComparisonBetween(parametro, indicador);
    	IndicadorDTO indicadorDTO = new IndicadorDTO(indicador, indicadorString, semaforoImage);
    	return indicadorDTO;
    }
    
    /**
     * 
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
     * @date 16/07/2014
     * @description 
     * @param tiempoPrioridad
     * @param indicador
     * @return
     */
    public static IndicadorDTO getIndicadorTiempoRespuestaGarantia(Double tiempoPrioridad, Double indicador) {
    	String indicadorString = null;
    	String semaforoImage;
    	if(indicador == null) {
    		semaforoImage = SEMAFORO_STR_EMPTY;
    	} else {
    		indicadorString = DECIMAL_FORMAT.format(indicador);
    		if(indicador <= tiempoPrioridad) {
    			semaforoImage = SEMAFORO_STR_OK;
    		} else {
    			semaforoImage = SEMAFORO_STR_ERROR;
    		}
    	}
    	IndicadorDTO indicadorDTO = new IndicadorDTO(indicador, indicadorString, semaforoImage);
    	return indicadorDTO;
    }
    
    /**
     * 
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
     * @date 16/07/2014
     * @description 
     * @param parametro
     * @param indicador
     * @return
     */
    public static IndicadorDTO getIndicadorMejorasIdentificadas(IndicadoresDTO parametro, Double indicador) {
    	String indicadorString = indicador.intValue() + "";
    	String semaforoImage = getSemaforoImageComparisonBetween(parametro, indicador);
    	IndicadorDTO indicadorDTO = new IndicadorDTO(indicador, indicadorString, semaforoImage);
    	return indicadorDTO;
    }
    
    /**
     * 
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
     * @date 14/07/2014
     * @description 
     * @param indicadorDTO
     * @param indicador
     * @return
     */
    public static String getSemaforoImage(IndicadoresDTO indicadorDTO, Double indicador) {
    	if(indicador >= indicadorDTO.getValorMinimo() && indicador <= indicadorDTO.getValorMaximo()) {
    		return SEMAFORO_STR_OK;
    	}
    	return SEMAFORO_STR_ERROR;
    }
    
    /**
     * 
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
     * @date 14/07/2014
     * @description 
     * @param indicadorDTO
     * @param indicador
     * @return
     */
    public static String getSemaforoImageComparisonBetween(IndicadoresDTO indicadorDTO, Double indicador) {
    	if(between(indicador.floatValue(), indicadorDTO.getValorMinimo(), indicadorDTO.getValorMaximo())) {
    		return SEMAFORO_STR_OK;
    	}
    	return SEMAFORO_STR_ERROR;
    }
    
    /**
     * 
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
     * @date 14/07/2014
     * @description 
     * @param indicadorDTO
     * @param indicador
     * @return
     */
    public static Integer getSemaforo(IndicadoresDTO indicadorDTO, Double indicador) {
    	if(between(indicador.floatValue(), indicadorDTO.getValorMinimo(), indicadorDTO.getValorMaximo())) {
    		return SEMAFORO_OK;
    	}
    	return SEMAFORO_ERROR;
    }
    
    /**
     * 
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
     * @date 10/07/2014
     * @description 
     * @return
     */
    public static DecimalFormat decimalFormat() {
    	return DECIMAL_FORMAT;
    }
    
    /**
     * 
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
     * @date 14/07/2014
     * @description 
     * @param value
     * @param minValue
     * @param maxValue
     * @return
     */
    public static boolean between(Float value, Float minValue, Float maxValue) {
    	if(maxValue != null) {
    		if(value >= minValue && value <= maxValue ||
    				value >= maxValue && value <= minValue) {
    			return true;
    		}
    	} else {
    		if(value >= minValue) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * 
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
     * @date 16/07/2014
     * @description 
     * @param date1
     * @param date2
     * @return
     */
    public static Double dateDifferenceInHours(Date date1, Date date2) {
    	return dateDifference(date1, date2, TimeUnit.MINUTES) / 60;
    }
    
    /**
     * 
     * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
     * @date 16/07/2014
     * @description 
     * @param date1
     * @param date2
     * @param timeUnit
     * @return
     */
    private static Double dateDifference(Date date1, Date date2, TimeUnit timeUnit) {
    	Long diff = date1.getTime() - date2.getTime();
    	Long result = timeUnit.convert(diff, TimeUnit.MILLISECONDS);
    	return result.doubleValue();
    }
	
}
