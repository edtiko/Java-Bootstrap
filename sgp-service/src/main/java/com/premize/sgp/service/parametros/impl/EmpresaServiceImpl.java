/**
 * 
 */
package com.premize.sgp.service.parametros.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.premize.pmz.api.dto.Paginator;
import com.premize.pmz.api.exception.AppBaseException;
import com.premize.pmz.service.AbstractEntityService;
import com.premize.sgp.dao.parametros.CiudadDao;
import com.premize.sgp.dao.parametros.EmpresaDao;
import com.premize.sgp.dao.parametros.SgpParametroDao;
import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.ResultSet;
import com.premize.sgp.dto.parametros.EmpresaDTO;
import com.premize.sgp.modelo.entities.parametros.SgpCiudad;
import com.premize.sgp.modelo.entities.parametros.SgpEmpresa;
import com.premize.sgp.modelo.entities.parametros.SgpParametro;
import com.premize.sgp.service.parametros.EmpresaService;
import com.premize.sgp.utils.FilePmz;

/**
 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
 * @project sgp-service
 * @class EmpresaServiceImpl
 * @since 21/01/2014
 */
@Service
public class EmpresaServiceImpl extends AbstractEntityService<SgpEmpresa, Integer> implements EmpresaService {
	
	@Autowired
	private EmpresaDao sgpEmpresaDao;
	
	@Autowired
	private SgpParametroDao sgpParametroDao;
	
	@Autowired
	private CiudadDao ciudadDao;
	
	@Override
	public EmpresaDao getDao() {
		return sgpEmpresaDao;
	}
	
	private static final String PARAMETRO_FIELD_CLAVE="nombre";
	private static final String RUTA_KEY="RUTA_LOGO";
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @see com.premize.sgp.service.parametros.EmpresaService#guardar(com.premize.sgp.modelo.entities.parametros.SgpEmpresa)
	 */
	@Override
	public void guardar (EmpresaDTO pEmpresa, FilePmz logo)throws Exception {
		
		try {
	
			
			
			
			SgpCiudad ciudad = ciudadDao.get(pEmpresa.getCiudad().getId());
			if (null!=logo){
				logo.setRuta(guardarLogo(logo));
				pEmpresa.setRutaLogo(logo.getRuta());
			}
			SgpEmpresa empresa = new SgpEmpresa();
			empresa.setNombre(pEmpresa.getNombre());
			empresa.setDescripcion(pEmpresa.getDescripcion());
			empresa.setNit(pEmpresa.getNit());
			empresa.setDireccion(pEmpresa.getDireccion());
			empresa.setRutaLogo(pEmpresa.getRutaLogo());
			empresa.setTelefono(pEmpresa.getTelefono());
			empresa.setSgpCiudad(ciudad);
			empresa.setIndActivo(pEmpresa.getNumeroEstado());
			empresa.setFecCreacion(Calendar.getInstance().getTime());
			empresa.setUsuarioCrea(pEmpresa.getUsuarioCreacion());
			
			sgpEmpresaDao.save(empresa);
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 27/01/2014
	 * @see com.premize.sgp.service.parametros.EmpresaService#actualizar(com.premize.sgp.modelo.entities.parametros.SgpEmpresa)
	 */
	@Override
	public void actualizar(EmpresaDTO pEmpresa, FilePmz logo) throws AppBaseException {
		
		try {
			if (pEmpresa.getId() == null || StringUtils.isBlank(pEmpresa.getId().toString())) {
				throw new Exception("Empresa sin ingresar.");
			}
			// Consulta la empresa
			SgpEmpresa empresa = sgpEmpresaDao.get(pEmpresa.getId());
			
			if (empresa == null || empresa.getId() == null) {
				throw new AppBaseException("Registro no existe.", null);
			}
			if (!pEmpresa.getRutaLogo().equals("") && pEmpresa.getRutaLogo() != null) {
				if (!empresa.getRutaLogo().equals(pEmpresa.getRutaLogo())) {
					logo.setRuta(guardarLogo(logo));
					eliminarLogo(pEmpresa.getRutaLogo());
					pEmpresa.setRutaLogo(logo.getRuta());
					empresa.setRutaLogo(pEmpresa.getRutaLogo());
				}
			}
			
			empresa.setNombre(pEmpresa.getNombre());
			empresa.setDescripcion(pEmpresa.getDescripcion());
			empresa.setNit(pEmpresa.getNit());
			empresa.setDireccion(pEmpresa.getDireccion());
			empresa.setTelefono(pEmpresa.getTelefono());
			empresa.setSgpCiudad(new SgpCiudad(pEmpresa.getCiudad().getId()));
			empresa.setIndActivo(pEmpresa.getNumeroEstado());
			empresa.setFecEdita(Calendar.getInstance().getTime());
			empresa.setUsuarioEdita(pEmpresa.getUsuarioEdita());
			
			sgpEmpresaDao.update(empresa);
		} catch (Exception e) {
			throw new AppBaseException("Operacion fallo." + e.getMessage(), null);
		}
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 4/02/2014
	 * @param logo
	 * @return ruta completa donde se almaceno la imagen
	 * @throws Exception
	 */
	private String guardarLogo(FilePmz logo) throws AppBaseException {
		
		String ruta = null;
		String rutaGrabarBBDD= null;
		
		if (logo != null) {
			// Obtiene la ruta del servidor local
			SgpParametro paramRuta = sgpParametroDao.findByProperty(PARAMETRO_FIELD_CLAVE,RUTA_KEY );
			
			if (paramRuta == null || StringUtils.isEmpty(paramRuta.getValor())
					|| StringUtils.isBlank(paramRuta.getValor())) {
				
				throw new AppBaseException("No fue posible obtener la ruta del servidor para guardar el logo.","RUTA_LOGO_INVALIDA");
			}
			
			// Copia el archivo en el servidor local
			rutaGrabarBBDD=paramRuta.getValor() +"/" + Calendar.getInstance().getTimeInMillis() +logo.getName();
			
			ruta = logo.getRuta()+"/"+rutaGrabarBBDD;
			
			try {
				verificaCarpetaImagenLogo(logo.getRuta()+File.separator+paramRuta.getValor());
				FileCopyUtils.copy(logo.getBytes(), new FileOutputStream(ruta));
			} catch (IOException ex) {
				throw new AppBaseException("No fue posible guardar el logo en el servidor.", ex, "ERROR_GUARDAR_LOGO");
			}
		}
		return rutaGrabarBBDD;
	}
	/**
	 * 
	* @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A Soto C</a>
	* @date 3/06/2014
	* @param ruta
	 */
	private void verificaCarpetaImagenLogo(String ruta){
		File file=new File(ruta);
		if(!file.exists()){
			file.mkdirs();
		}
		
	}
	
	/**
	 * @author <a href="mailto:carolina.diez@premize.com">Carolina Diez</a>
	 * @since 5/02/2014
	 * @param rutaLogo
	 * @throws Exception
	 */
	private void eliminarLogo(String rutaLogo) throws Exception {
		
		if (StringUtils.isEmpty(rutaLogo) || StringUtils.isBlank(rutaLogo)) {
				throw new Exception("No fue posible eliminar el logo en el servidor.");
		}
		
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 31/01/2014
	 * @see com.premize.sgp.service.parametros.EmpresaService#getRecords(com.premize.sgp.dto.PagingCriteria)
	 */
	@Override
	public ResultSet<EmpresaDTO> getRecords(PagingCriteria criteria) throws AppBaseException {
		
		return sgpEmpresaDao.getRecords(criteria);
	}
	
	/**
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 31/01/2014
	 * @see com.premize.sgp.service.parametros.EmpresaService#obtenerEmpresa(java.lang.Integer)
	 */
	@Override
	public EmpresaDTO obtenerEmpresa(Integer idEmpresa) throws AppBaseException {
		SgpEmpresa sgpEmpresa = sgpEmpresaDao.get(idEmpresa);
		EmpresaDTO empresaDTO = new EmpresaDTO(sgpEmpresa);
		return empresaDTO;
	}
	
	@Override
	public List<EmpresaDTO> consultarEmpresasPorCiudad(Integer idCiudad, Paginator page) throws AppBaseException {
		return getDao().consultarEmpresaPorCiudad(idCiudad, page);
	}
	
}
