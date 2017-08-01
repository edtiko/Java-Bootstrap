/**
 * 
 */
package com.premize.sgp.web.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.premize.sgp.dto.JsonResponse;
import com.premize.sgp.dto.pruebas.TipoHallazgoDTO;
import com.premize.sgp.facade.pruebas.TipoHallazgoFacade;

/**
 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
 * @project sgp-web
 * @class TipoHallazgoControllerTest
 * @date 16/06/2014
 * 
 */
public class TipoHallazgoControllerTest {

	@Mock
	private TipoHallazgoFacade tipoHallazgoFacade;
	@Mock
	private Validator validator;

	@InjectMocks
	private TipoHallazgoController tipoHallazgoController;

	private MockMvc mockMvc;

	@Before
	public void setup() {

		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		// Setup Spring test in standalone mode
		this.mockMvc = MockMvcBuilders.standaloneSetup(tipoHallazgoController)
				.build();

	}
	@Test
	public void testGetTipoHallazgoIdnull() throws Exception {

		TipoHallazgoDTO tipoHallazgoDTO = new TipoHallazgoDTO();
		tipoHallazgoDTO.setDescripcion("descripcion");
		tipoHallazgoDTO.setNombre("nombre");
		tipoHallazgoDTO.setNumeroEstado(1);
		tipoHallazgoDTO.setEsPuntuado(1);
		tipoHallazgoDTO.setNumeroTieneCausaGeneracion(1);
		tipoHallazgoDTO.setId(1);

		// when(methodCall)n(tipoHallazgoFacade.guardarTipoHallazgo(tipoHallazgoDTO))
		// .thenThrow(new AppBaseException());

		

		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();

		String json = prettyGson.toJson(tipoHallazgoDTO);

		this.mockMvc.perform(get("/tipoHallazgo/getTipoHallazgo")

		.param("id", "1"));

		Assert.assertNotNull("Retorna dato vacio", tipoHallazgoDTO.getNombre());
		Assert.assertTrue(tipoHallazgoDTO.getDescripcion()
				.equals("descripcion"));
	}
	@Test
	public void testGetTipoHallazgo() throws Exception {

		TipoHallazgoDTO tipoHallazgoDTO = new TipoHallazgoDTO();
		tipoHallazgoDTO.setDescripcion("descripcion");
		tipoHallazgoDTO.setNombre("nombre");
		tipoHallazgoDTO.setNumeroEstado(1);
		tipoHallazgoDTO.setEsPuntuado(1);
		tipoHallazgoDTO.setNumeroTieneCausaGeneracion(1);
		tipoHallazgoDTO.setId(1);

		// when(methodCall)n(tipoHallazgoFacade.guardarTipoHallazgo(tipoHallazgoDTO))
		// .thenThrow(new AppBaseException());

		

		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();

		String json = prettyGson.toJson(tipoHallazgoDTO);

		this.mockMvc.perform(get("/tipoHallazgo/getTipoHallazgo")

		.param("id", "null"));

		Assert.assertNotNull("Retorna dato vacio", tipoHallazgoDTO.getNombre());
		Assert.assertTrue(tipoHallazgoDTO.getDescripcion()
				.equals("descripcion"));
	}
	@Test
	public void testGetTipoHallazgoException() throws Exception {

		TipoHallazgoDTO tipoHallazgoDTO = new TipoHallazgoDTO();

		// when(methodCall)n(tipoHallazgoFacade.guardarTipoHallazgo(tipoHallazgoDTO))
		// .thenThrow(new AppBaseException());

		when(tipoHallazgoFacade.getTipoHallazgo(1)).thenThrow(
				new NumberFormatException("Requiere un numero"));

		this.mockMvc.perform(get("/tipoHallazgo/getTipoHallazgo")

		.param("id", "null"));

		Assert.assertNull("Id null no retorna nada",tipoHallazgoDTO.getNombre());
		
		Assert.assertTrue(null == (tipoHallazgoDTO.getDescripcion()));
	}

	@Test
	public void testGuardar() throws Exception {

		TipoHallazgoDTO tipoHallazgoDTO = new TipoHallazgoDTO();
		tipoHallazgoDTO
				.setNombre("nom");
		tipoHallazgoDTO.setDescripcion("descripcion");
		tipoHallazgoDTO.setId(1);
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String json = prettyGson.toJson(tipoHallazgoDTO);
		ResultActions re = this.mockMvc.perform(post("/tipoHallazgo/guardar")

		.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(
				status().isOk());
		MvcResult res = re.andReturn();
		String dat = res.getResponse().getContentAsString();

		Gson prettyGsons = new Gson();
		JsonResponse condition = prettyGsons.fromJson(dat, JsonResponse.class);

		Assert.assertTrue("Es ok", condition.getStatus().equals("SUCCESS"));
	}

	
	@Test
	public void testEditar() throws Exception {

		TipoHallazgoDTO tipoHallazgoDTO = new TipoHallazgoDTO();
		tipoHallazgoDTO
				.setNombre("nom");
		tipoHallazgoDTO.setDescripcion("descripcion");
		tipoHallazgoDTO.setId(1);
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String json = prettyGson.toJson(tipoHallazgoDTO);
		ResultActions re = this.mockMvc.perform(post("/tipoHallazgo/editar")

		.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(
				status().isOk());
		MvcResult res = re.andReturn();
		String dat = res.getResponse().getContentAsString();

		Gson prettyGsons = new Gson();
		JsonResponse condition = prettyGsons.fromJson(dat, JsonResponse.class);

		Assert.assertTrue("Es ok", condition.getStatus().equals("SUCCESS"));
	}

	@Test(expected = Exception.class)
	public void testEditarException() throws Exception  {

		TipoHallazgoDTO tipoHallazgoDTO = null;
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String json = prettyGson.toJson(tipoHallazgoDTO);
		


		
		ResultActions re = this.mockMvc.perform(post("/tipoHallazgo/editar")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
						.andExpect(status().isOk());
		MvcResult res = re.andReturn();
		String dat = res.getResponse().getContentAsString();

		Gson prettyGsons = new Gson();
		JsonResponse condition = prettyGsons.fromJson(dat, JsonResponse.class);

		Assert.assertTrue("Es ok", condition.getStatus().equals("FAIL"));
	}
	
	@Test(expected = Exception.class)
	public void testGuardarException() throws Exception  {

		TipoHallazgoDTO tipoHallazgoDTO = null;
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String json = prettyGson.toJson(tipoHallazgoDTO);
		


		
		ResultActions re = this.mockMvc.perform(post("/tipoHallazgo/guardar")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
						.andExpect(status().isOk());
		MvcResult res = re.andReturn();
		String dat = res.getResponse().getContentAsString();

		Gson prettyGsons = new Gson();
		JsonResponse condition = prettyGsons.fromJson(dat, JsonResponse.class);

		Assert.assertTrue("Es ok", condition.getStatus().equals("FAIL"));
	}
}
