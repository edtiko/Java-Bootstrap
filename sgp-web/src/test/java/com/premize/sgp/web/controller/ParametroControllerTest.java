/**
 * 
 */
package com.premize.sgp.web.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.premize.sgp.dto.JsonResponse;
import com.premize.sgp.dto.parametros.ParametroDTO;
import com.premize.sgp.dto.pruebas.TipoHallazgoDTO;
import com.premize.sgp.facade.ParametroFacade;

/**
 * @author <a href="mailto:gustavoa.soto@premize.com">Gustavo A soto C</a>
 * @project sgp-web
 * @class TipoHallazgoControllerTest
 * @date 16/06/2014
 * 
 */
public class ParametroControllerTest {

	@Mock
	private ParametroFacade parametroFacade;
	@Mock
	private Validator validator;

	@InjectMocks
	private ParametroController parametroController;

	private MockMvc mockMvc;

	@Before
	public void setup() {

		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		// Setup Spring test in standalone mode
		this.mockMvc = MockMvcBuilders.standaloneSetup(parametroController)
				.build();

	}

	

	

	@Test
	public void testGuardar() throws Exception {

		ParametroDTO  parametroDTO = new ParametroDTO();

		
		
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String json = prettyGson.toJson(parametroDTO);
		ResultActions re = this.mockMvc.perform(post("/parametros/guardar")

		.contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(
				status().isOk());
		MvcResult res = re.andReturn();
		String dat = res.getResponse().getContentAsString();

		Gson prettyGsons = new Gson();
		JsonResponse condition = prettyGsons.fromJson(dat, JsonResponse.class);

		Assert.assertTrue("Es ok", condition.getStatus().equals("SUCCESS"));
	}

//	@Test(expected = Exception.class)
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
