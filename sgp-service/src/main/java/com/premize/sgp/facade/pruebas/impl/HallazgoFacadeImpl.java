/**
 * 
 */
package com.premize.sgp.facade.pruebas.impl;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.EntityService;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.api.exception.MailServiceException;
import com.premize.pmz.facade.AbstractEntityFacade;
import com.premize.sgp.dao.gestion.pruebas.impl.SgpFlujoHallazgoDaoImpl;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.ParametroDTO;
import com.premize.sgp.dto.parametros.UsuarioDTO;
import com.premize.sgp.dto.pruebas.AnexoHallazgoDTO;
import com.premize.sgp.dto.pruebas.ConsultaHallazgoDTO;
import com.premize.sgp.dto.pruebas.FlujoHallazgoDTO;
import com.premize.sgp.dto.pruebas.HallazgoDTO;
import com.premize.sgp.dto.pruebas.ReporteHallazgoDTO;
import com.premize.sgp.dto.pruebas.TotalHallazgoDTO;
import com.premize.sgp.dto.pruebas.TotalesHallazgoDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndCalidadDocumentacionDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndCalidadFuncionalidadDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndEfectividadHallazgosReportadosDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndMejorasIdentificadasDTO;
import com.premize.sgp.dto.pruebas.indicadores.IndTiemposRespuestaGarantiaDTO;
import com.premize.sgp.facade.pruebas.HallazgoFacade;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpFlujoHallazgo;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpHallazgo;
import com.premize.sgp.service.parametros.UsuarioService;
import com.premize.sgp.service.pruebas.FlujoHallazgoService;
import com.premize.sgp.service.pruebas.HallazgoService;
import com.premize.sgp.utils.FilePmz;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-service
 * @class HallazgoFacadeImpl
 * @since 24/02/2014
 */
@Service("hallazgoFacade")
public class HallazgoFacadeImpl extends AbstractEntityFacade<SgpHallazgo, Integer> implements HallazgoFacade {
	
	@Autowired
	private HallazgoService hallazgoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private FlujoHallazgoService flujoHallazgoService;
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws ParseException 
	 * @since 24/02/2014
	 * @see com.premize.sgp.facade.pruebas.HallazgoFacade#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<ConsultaHallazgoDTO> getRecords(PagingCriteria criteria) throws AppBaseException, ParseException {
		
		return hallazgoService.getRecords(criteria);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 24/02/2014
	 * @see com.premize.pmz.facade.AbstractEntityFacade#getEntityService()
	 */
	@Override
	public EntityService<SgpHallazgo, Integer> getEntityService() {
		return hallazgoService;
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws IOException 
	 * @throws AppBaseException 
	 * @throws MailServiceException 
	 * @throws ParseException 
	 * @since 6/03/2014
	 * @see com.premize.sgp.facade.pruebas.HallazgoFacade#guardarHallazgo(com.premize.sgp.dto.pruebas.FlujoHallazgoDTO,
	 *      com.premize.sgp.utils.File)
	 */
	@Override
	public void guardarHallazgo(FlujoHallazgoDTO flujoHallazgo, FilePmz file) throws AppBaseException, IOException,  ParseException  {
		
		SgpHallazgo sgpHallazgo = new SgpHallazgo();
		SgpFlujoHallazgo sgpFlujoHallazgo = new SgpFlujoHallazgo();
		hallazgoService.guardarHallazgo(flujoHallazgo, sgpHallazgo);
		flujoHallazgoService.guardarFlujoHallazgo(flujoHallazgo, sgpHallazgo, sgpFlujoHallazgo);
		hallazgoService.guardarAnexoHallazgo(sgpHallazgo, file);

		
	}
	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.facade.pruebas.HallazgoFacade#enviarEmailHallazgo(com.premize.sgp.modelo.entities.gestion.pruebas.SgpFlujoHallazgo)
	 */
	public void enviarEmailHallazgo(FlujoHallazgoDTO sgpFlujoHallazgo) throws MailServiceException, AppBaseException{
		SgpFlujoHallazgo flujoHallazgo=flujoHallazgoService.findById(sgpFlujoHallazgo.getId());
		
		hallazgoService.enviarEmailHallazgo(flujoHallazgo);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.pruebas.HallazgoFacade#editarHallazgo(com.premize.sgp.dto.pruebas.FlujoHallazgoDTO)
	 */
	@Override
	public void editarHallazgo(FlujoHallazgoDTO flujoHallazgo) throws Exception {
		
		SgpFlujoHallazgo sgpFlujoHallazgo = new SgpFlujoHallazgo();
		flujoHallazgoService.editarHallazgo(flujoHallazgo, sgpFlujoHallazgo);

		
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.pruebas.HallazgoFacade#getHallazgo(java.lang.Integer)
	 */
	@Override
	public HallazgoDTO getHallazgo(Integer idHallazgo,String login) throws AppBaseException {
		
		return hallazgoService.getHallazgo(idHallazgo,login);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.facade.pruebas.HallazgoFacade#getHallazgosPorCaso(java.lang.Integer)
	 */
	@Override
	public List<HallazgoDTO> getHallazgosPorCaso(Integer idCasoPrueba) throws AppBaseException {
		
		return hallazgoService.getHallazgosPorCaso(idCasoPrueba);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException 
	 * @since 10/03/2014
	 * @see com.premize.sgp.facade.pruebas.HallazgoFacade#getEstadosHallazgo(java.lang.Integer)
	 */
	@Override
	public List<ParametroDTO> getEstadosHallazgo(Integer idHallazgo) throws AppBaseException {
		
		return hallazgoService.getEstadosHallazgo(idHallazgo);
	}
/**
 * 
 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache</a>
 * @since 14/04/2014
 * @param idProyecto
 * @return
 * @throws AppBaseException
 * @throws ParseException 
 */
	@Override
	public List<ReporteHallazgoDTO> getHallazgosPorSeveridad(Integer idProyecto, String fechaFrom, String fechaTo, String idTipoHallazgo) throws AppBaseException, ParseException {
		
		return hallazgoService.getHallazgosPorSeveridad(idProyecto, fechaFrom, fechaTo, idTipoHallazgo);
	}

	/**
	 * 
	 * @author <a href="mailto:jonathan.almache@premize.com">Jonathan Almache
	 </a>
	 * @throws ParseException 
	 * @since 14/04/2014
	 * @see com.premize.sgp.facade.pruebas.HallazgoFacade#getHallazgosPorMapaPruebas(java.lang.Integer)
	 */
	@Override
	public List<ReporteHallazgoDTO> getHallazgosPorMapaPruebas(Integer idProyecto, String fechaFrom, String fechaTo, String idTipoHallazgo) throws AppBaseException, ParseException {
		
		return hallazgoService.getHallazgosPorMapaPruebas(idProyecto, fechaFrom, fechaTo, idTipoHallazgo);
	}
	
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 11/03/2014
	 * @see com.premize.sgp.facade.pruebas.HallazgoFacade#guardarAnexoHallazgo(com.premize.sgp.dto.pruebas.HallazgoDTO,
	 *      com.premize.sgp.utils.File)
	 */
	@Override
	public void guardarAnexoHallazgo(HallazgoDTO hallazgodto, FilePmz file) throws AppBaseException, IOException {
		
		hallazgoService.guardarAnexo(hallazgodto, file);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException
	 * @since 11/03/2014
	 * @see com.premize.sgp.facade.pruebas.HallazgoFacade#getAnexo(java.lang.Integer)
	 */
	@Override
	public AnexoHallazgoDTO getAnexo(Integer idAnexo) throws AppBaseException {
		
		return hallazgoService.getAnexo(idAnexo);
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/06/2014
	 * @description 
	 * @param idAnexo
	 * @return
	 * @throws AppBaseException
	 */
	@Override
	public File getArchivoAnexo(Integer idAnexo) throws AppBaseException {
		return hallazgoService.getArchivoAnexo(idAnexo);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 21/03/2014
	 * @see com.premize.sgp.facade.pruebas.HallazgoFacade#modificarDatosHallazgo(com.premize.sgp.dto.pruebas.HallazgoDTO)
	 */
	@Override
	public void modificarDatosHallazgo(HallazgoDTO hallazgoDTO)
			throws AppBaseException {
		hallazgoService.modificarDatosHallazgo(hallazgoDTO);
	}

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws ParseException 
	 * @since 14/04/2014
	 * @see com.premize.sgp.facade.pruebas.HallazgoFacade#getTotalesHallazgoProyecto(java.lang.Integer)
	 */
	@Override
	public List<TotalesHallazgoDTO> getTotalesHallazgoProyecto(
			Integer idProyecto, String fechaFrom, String fechaTo, String idTipoHallazgo) throws AppBaseException, ParseException {
		return hallazgoService.getTotalesHallazgoProyecto(idProyecto, fechaFrom, fechaTo, idTipoHallazgo);
	}   


	/*
	 * (non-Javadoc)
	 * @see com.premize.sgp.facade.pruebas.HallazgoFacade#getUsuarios_Hallazgos(java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public List<TotalHallazgoDTO> getUsuarios_Hallazgos(Integer idProyecto,
			String fechaFrom, String fechaTo, String tipoHallazgo) throws AppBaseException,
			ParseException {
		return hallazgoService.getUsuarios_Hallazgos(idProyecto, fechaFrom, fechaTo, tipoHallazgo);
	}
	
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 21/04/2014
	 * @see com.premize.sgp.facade.pruebas.HallazgoFacade#consultaHallazgosExcel(com.premize.sgp.dto.pruebas.FlujoHallazgoDTO)
	 */
	@Override
	public Workbook consultaHallazgosExcel(Map<String, Object> filtros)
			throws AppBaseException, NumberFormatException, IOException {
		return hallazgoService.consultaHallazgosExcel(filtros);
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 8/07/2014
	 * @description 
	 * @param idProyecto
	 * @param fechaFrom
	 * @param fechaTo
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException
	 * @throws NumberFormatException
	 */
	@Override
	public Map<Integer, IndCalidadDocumentacionDTO> consultarIndicadorCalidadDocumentacion(String idProyecto,
			String fechaFrom, String fechaTo) throws AppBaseException, ParseException, NumberFormatException {
		return hallazgoService.consultarIndicadorCalidadDocumentacion(idProyecto, fechaFrom, fechaTo);
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 9/07/2014
	 * @description 
	 * @param idProyecto
	 * @param fechaFrom
	 * @param fechaTo
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException
	 * @throws NumberFormatException
	 */
	@Override
	public List<IndCalidadFuncionalidadDTO> getIndicadorCalidadFuncionalidad(String idProyecto,
			String fechaFrom, String fechaTo) throws AppBaseException, ParseException, NumberFormatException {
		return hallazgoService.getIndicadorCalidadFuncionalidad(idProyecto, fechaFrom, fechaTo);
	}

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws ParseException 
	 * @throws AppBaseException 
	 * @since 8/07/2014
	 * @see com.premize.sgp.facade.pruebas.HallazgoFacade#getHallazgosPorArtefacto(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<ReporteHallazgoDTO> getHallazgosPorArtefacto(
			Integer idProyecto, String fechaFrom, String fechaTo,
			String tipoHallazgo) throws AppBaseException, ParseException {
		return hallazgoService.getHallazgosPorArtefacto(idProyecto, fechaFrom, fechaTo, tipoHallazgo);
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 10/07/2014
	 * @description 
	 * @param idProyecto
	 * @param fechaFrom
	 * @param fechaTo
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException
	 * @throws NumberFormatException
	 */
	@Override
	public IndEfectividadHallazgosReportadosDTO getIndicadorEfectividadHallazgosReportados(String idProyecto,
			String fechaFrom, String fechaTo) throws AppBaseException, ParseException, NumberFormatException {
		return hallazgoService.getIndicadorEfectividadHallazgosReportados(idProyecto, fechaFrom, fechaTo);
	}

	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws ParseException 
	 * @throws AppBaseException 
	 * @since 11/07/2014
	 * @see com.premize.sgp.facade.pruebas.HallazgoFacade#getHallazgosPorRecurso(java.lang.String, java.lang.String, java.lang.String, java.util.List, java.util.List)
	 */
	@Override
	public List<ReporteHallazgoDTO> getHallazgosPorRecurso(String proyecto,
			String fechaFrom, String fechaTo, List<Integer> listEstados,
			List<Integer> listCausas) throws AppBaseException, ParseException {
		return hallazgoService.getHallazgosPorRecurso(proyecto , fechaFrom, fechaTo, listEstados, listCausas);
	}

	@Override
	public List<ConsultaHallazgoDTO> getSeveridadColorHallazgo(String usuario)
			throws AppBaseException {
		UsuarioDTO usuarioDTO=usuarioService.obtenerUsuario(usuario);
		return hallazgoService.getSeveridadColorHallazgo(usuarioDTO.getNombre());
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 14/07/2014
	 * @description 
	 * @param idProyecto
	 * @param fechaFrom
	 * @param fechaTo
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException
	 * @throws NumberFormatException
	 */
	@Override
	public List<IndMejorasIdentificadasDTO> getMejorasIndentifcadas(String idProyecto,
			String fechaFrom, String fechaTo) throws AppBaseException, ParseException, NumberFormatException {
		return hallazgoService.getMejorasIndentifcadas(idProyecto, fechaFrom, fechaTo);
	}
	
	/**
	 * 
	 * @author <a href="mailto:jhona.filigrana@premize.com">Jhon Alexander Filigrana Cardona</a> 
	 * @date 16/07/2014
	 * @description 
	 * @param idProyecto
	 * @param fechaFrom
	 * @param fechaTo
	 * @return
	 * @throws AppBaseException
	 * @throws ParseException
	 * @throws NumberFormatException
	 */
	@Override
	public IndTiemposRespuestaGarantiaDTO getIndicadorTiemposRespuestaGarantia(String idProyecto,
			String fechaFrom, String fechaTo) throws AppBaseException, ParseException, NumberFormatException {
		return hallazgoService.getIndicadorTiemposRespuestaGarantia(idProyecto, fechaFrom, fechaTo);
	}

}
