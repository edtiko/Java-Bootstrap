/**
 * 
 */
package com.premize.sgp.service.pruebas.impl;

import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.service.AbstractEntityService;
import com.premize.sgp.dao.gestion.pruebas.CasoPruebaDao;
import com.premize.sgp.dao.gestion.pruebas.HallazgoDao;
import com.premize.sgp.dao.gestion.pruebas.MapaPruebasDao;
import com.premize.sgp.dao.parametros.ArtefactoDao;
import com.premize.sgp.dao.parametros.SgpParametroDao;
import com.premize.sgp.dao.parametros.UsuarioDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.pruebas.CargueCasoPruebaDTO;
import com.premize.sgp.dto.pruebas.CasoDePruebaDTO;
import com.premize.sgp.dto.pruebas.MapaPruebasDTO;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpCasoPrueba;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpHallazgo;
import com.premize.sgp.modelo.entities.gestion.pruebas.SgpMapaPrueba;
import com.premize.sgp.modelo.entities.parametros.SgpArtefacto;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;
import com.premize.sgp.service.pruebas.CasoDePruebaService;
import com.premize.sgp.utils.LeerArchivoCasoPruebasExcel;

/**
 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
 * @project sgp-service
 * @class CasoDePruebaServiceImpl
 * @date 11/02/2014
 */
@Service
public class CasoDePruebaServiceImpl extends AbstractEntityService<SgpCasoPrueba, Integer> implements
		CasoDePruebaService {
	
	private final Integer CARGUE_VALIDO = 1;
	private final Integer CARGUE_NO_VALIDO = 0;
	
	@Autowired
	private CasoPruebaDao casoPruebaDao;
	
	@Autowired
	private ArtefactoDao artefactoDao;
	@Autowired
	private MapaPruebasDao sgpMapaPruebasDao;
	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private LeerArchivoCasoPruebasExcel archivoExcel;
	@Autowired
	private HallazgoDao hallazgoDao;
	@Autowired
	private SgpParametroDao parametroDao;
	
	@Override
	public CasoPruebaDao getDao() {
		return casoPruebaDao;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.pruebas.CasoDePruebaService#guardarCasoDePrueba(com.premize.sgp.dto.pruebas.CasoDePruebaDTO)
	 */
	public void guardarCasoDePrueba(CasoDePruebaDTO casoDePruebaDTO) throws AppBaseException {
		
		SgpCasoPrueba sgpCasoPrueba = new SgpCasoPrueba();
		SgpArtefacto sgpArtefacto = artefactoDao.get(casoDePruebaDTO.getArtefacto().getId());
		SgpMapaPrueba sgpMapaPrueba = sgpMapaPruebasDao.get(casoDePruebaDTO.getMapaPrueba().getId());
		SgpParametro tipoPrueba = parametroDao.get(casoDePruebaDTO.getTipoPrueba().getId());
		
		Integer consecutivo = casoPruebaDao.consultarConsecutivo(casoDePruebaDTO.getMapaPrueba().getId());
		consecutivo += 1;
		
		sgpCasoPrueba.setDescripcion(casoDePruebaDTO.getDescripcion());
		sgpCasoPrueba.setResultado(casoDePruebaDTO.getResultado());
		sgpCasoPrueba.setSgpArtefacto(sgpArtefacto);
		sgpCasoPrueba.setSgpMapaPrueba(sgpMapaPrueba);
		sgpCasoPrueba.setTipoPrueba(tipoPrueba);
		sgpCasoPrueba.setUsuarioCrea(casoDePruebaDTO.getUsuarioCreacion());
		sgpCasoPrueba.setFecCreacion(Calendar.getInstance().getTime());
		
		sgpCasoPrueba.setConsecutivo(consecutivo);
		
		casoPruebaDao.save(sgpCasoPrueba);
	}
	
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.pruebas.CasoDePruebaService#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<CasoDePruebaDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		return casoPruebaDao.getRecords(criteria);
		
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.pruebas.CasoDePruebaService#editarCasoDePrueba(com.premize.sgp.dto.pruebas.CasoDePruebaDTO)
	 */
	@Override
	public void editarCasoDePrueba(CasoDePruebaDTO casoDePruebaDTO) throws AppBaseException {
		
		SgpCasoPrueba sgpCasoPrueba = casoPruebaDao.get(casoDePruebaDTO.getId());
		SgpArtefacto sgpAtefacto = artefactoDao.get(casoDePruebaDTO.getArtefacto().getId());
		SgpParametro tipoPrueba = parametroDao.get(casoDePruebaDTO.getTipoPrueba().getId());
		sgpCasoPrueba.setDescripcion(casoDePruebaDTO.getDescripcion());
		sgpCasoPrueba.setResultado(casoDePruebaDTO.getResultado());
		sgpCasoPrueba.setTipoPrueba(tipoPrueba);
		sgpCasoPrueba.setSgpArtefacto(sgpAtefacto);
		sgpCasoPrueba.setUsuarioEdita(casoDePruebaDTO.getUsuarioEdita());
		sgpCasoPrueba.setFecEdita(Calendar.getInstance().getTime());
		casoPruebaDao.update(sgpCasoPrueba);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @throws AppBaseException
	 * @since 28/01/2014
	 * @see com.premize.sgp.service.ParametroService#getParametro(java.lang.Integer)
	 */
	@Override
	public CasoDePruebaDTO getCasoDePrueba(Integer idCasoDePrueba) throws AppBaseException {
		
		CasoDePruebaDTO casoDePruebaDTO = null;
		if (idCasoDePrueba != null) {
			SgpCasoPrueba sgpCasoPrueba = casoPruebaDao.get(idCasoDePrueba);
			casoDePruebaDTO = new CasoDePruebaDTO(sgpCasoPrueba);
		}
		
		return casoDePruebaDTO;
	}
	
	/**
	 * @author <a href="mailto:hernand.ramirez@premize.com">Hernan David Ramirez Mejia</a>
	 * @throws AppBaseException
	 * @since 20/02/2014
	 */
	public void guardarCasoDePrueba(List<CasoDePruebaDTO> listaCasoDePruebaDTO) throws AppBaseException {
		CasoDePruebaDTO casoDePruebaDTO_=listaCasoDePruebaDTO.get(0);
		Integer consecutivo = casoPruebaDao.consultarConsecutivo(casoDePruebaDTO_.getMapaPrueba().getId());
		

		for (CasoDePruebaDTO casoDePruebaDTO : listaCasoDePruebaDTO) {
			
			SgpCasoPrueba sgpCasoPrueba = new SgpCasoPrueba();
			SgpMapaPrueba sgpMapaPrueba = sgpMapaPruebasDao.get(casoDePruebaDTO.getMapaPrueba().getId());
			SgpArtefacto sgpArtefacto = artefactoDao.get(casoDePruebaDTO.getArtefacto().getId());
			SgpParametro tipoPrueba = parametroDao.get(casoDePruebaDTO.getTipoPrueba().getId());
			
			sgpCasoPrueba.setDescripcion(casoDePruebaDTO.getDescripcion());
			sgpCasoPrueba.setResultado(casoDePruebaDTO.getResultado());
			sgpCasoPrueba.setSgpArtefacto(sgpArtefacto);
			sgpCasoPrueba.setSgpMapaPrueba(sgpMapaPrueba);
			sgpCasoPrueba.setTipoPrueba(tipoPrueba);
			sgpCasoPrueba.setConsecutivo(++consecutivo);
			sgpCasoPrueba.setUsuarioCrea(casoDePruebaDTO.getUsuarioCreacion());
			sgpCasoPrueba.setFecCreacion(Calendar.getInstance().getTime());
			casoPruebaDao.save(sgpCasoPrueba);
		}
		
	}
	
	/**
	 * @author <a href="mailto:hernand.ramirez@premize.com">Hernan David Ramirez Mejia</a>
	 * @param imstream
	 * @return
	 * @throws AppBaseException
	 */
	public CargueCasoPruebaDTO cargarCasosPruebas(InputStream archivoImportar, Integer idMapaPruebas, String login)
			throws AppBaseException {
		CargueCasoPruebaDTO datosRespuesta = null;
		
		if(archivoImportar != null && login != null){
			datosRespuesta = archivoExcel.leerArchivoCasoPrueba(archivoImportar, login);
		}
		
		SgpMapaPrueba sgpMapaPrueba = sgpMapaPruebasDao.get(idMapaPruebas);
		if (sgpMapaPrueba == null || sgpMapaPrueba.getId() == null) {
			datosRespuesta.setEstadoCargueArchivo(CARGUE_NO_VALIDO);
			throw new AppBaseException("No fue posible obtener el mapa de pruebas", null);
		}
		
		if (datosRespuesta.getEstadoCargueArchivo() == CARGUE_VALIDO) {
			MapaPruebasDTO mapaDTO = new MapaPruebasDTO(sgpMapaPrueba.getId());
			List<CasoDePruebaDTO> lista = datosRespuesta.getListaDatosCargados();
			
			for (CasoDePruebaDTO casoDePruebaDTO : lista) {
				casoDePruebaDTO.setMapaPrueba(mapaDTO);
			}
			guardarCasoDePrueba(lista);
		}
		return datosRespuesta;
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.pruebas.CasoDePruebaService#eliminarCasoPrueba(java.lang.Integer)
	 */
	@Override
	public void eliminarCasoPrueba(Integer id) throws AppBaseException {
		
		if (id == null || StringUtils.isBlank(id.toString())) {
			throw new AppBaseException("Caso de prueba sin ingresar", null);
		}
		
		// Verifica si existe
		SgpCasoPrueba casoPrueba = casoPruebaDao.get(id);
		if (casoPrueba == null || casoPrueba.getId() == null) {
			throw new AppBaseException("Registro no existe.", null);
		}
		
		// verifica si ya se encuentra ejecutado
		if (casoPrueba.getCumple() != null 
				&& StringUtils.isNotBlank(casoPrueba.getCumple().toString()) ){
			throw new AppBaseException("No se puede eliminar: El caso de prueba ya fue ejecutado", null);
		} 
		
		// verifica si tiene hallazgos relacionados
		List<SgpHallazgo> listaHallazgos = hallazgoDao.getHallazgosPorCasoPrueba(id);
		if (!listaHallazgos.isEmpty()) {
			throw new AppBaseException("No se puede eliminar: Existen hallazgos relacionados", null);
		}
		
		casoPruebaDao.deleteById(id);
	}
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see com.premize.sgp.service.pruebas.CasoDePruebaService#ejecutarCasoPrueba(com.premize.sgp.dto.pruebas.CasoDePruebaDTO)
	 */
	@Override
	public void ejecutarCasoPrueba(CasoDePruebaDTO casoDePruebaDTO) throws AppBaseException {
		
		SgpCasoPrueba casoPrueba = casoPruebaDao.get(casoDePruebaDTO.getId());
		if (casoPrueba == null || casoPrueba.getId() == null) {
			throw new AppBaseException("Registro no existe.", null);
		}
		
		casoPrueba.setFecEjecuta(Calendar.getInstance().getTime());
		casoPrueba.setUsuarioEjecuta(casoDePruebaDTO.getUsuarioEjecuta());
		casoPrueba.setCumple(casoDePruebaDTO.getCumple());
		casoPruebaDao.update(casoPrueba);
	}
	
}
