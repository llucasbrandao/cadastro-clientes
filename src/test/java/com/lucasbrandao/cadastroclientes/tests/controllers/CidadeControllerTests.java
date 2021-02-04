package com.lucasbrandao.cadastroclientes.tests.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasbrandao.cadastroclientes.dto.CidadeDTO;
import com.lucasbrandao.cadastroclientes.dto.EstadoDTO;
import com.lucasbrandao.cadastroclientes.security.JWTUtil;
import com.lucasbrandao.cadastroclientes.services.impl.CidadeServiceImpl;
import com.lucasbrandao.cadastroclientes.services.impl.UserDetailsServiceImpl;

@SpringBootTest
public class CidadeControllerTests {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@MockBean
	private CidadeServiceImpl cidadeService;
	
	@MockBean
	private UserDetailsServiceImpl userDetailsService;
	
	@MockBean
	private JWTUtil jwtUtil;
	
	private final String JWT_USER_TOKEN;
	private final String JWT_ADMIN_TOKEN;
	
	@BeforeEach
  	public void setUp() {
  		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	public CidadeControllerTests() {
		JWT_USER_TOKEN = JWTUtil.generateTestToken("usuario@usuario.com.br");
		JWT_ADMIN_TOKEN = JWTUtil.generateTestToken("admin@admin.com.br");
	}
	
	@Test
	public void cadastraCidade() throws JsonProcessingException, Exception {
		CidadeDTO cidadeDTO = new CidadeDTO();
		
		cidadeDTO.setNome("Brasília");
		cidadeDTO.setEstado(new EstadoDTO(null, "Distrido Federal", "DF"));
		
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/cidades/cadastrar-nova-cidade")
				.content(new ObjectMapper().writeValueAsString(cidadeDTO))
				.header("Authorization", "Bearer " + JWT_ADMIN_TOKEN)
				.contentType(MediaType.APPLICATION_JSON)
		).andExpect(status().isCreated());  
	}
	
	@Test
	public void buscaCidadePeloNome() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/cidades/buscar-cidade-pelo-nome/Brasília")
				.header("Authorization", "Bearer " + JWT_USER_TOKEN)
				.contentType(MediaType.APPLICATION_JSON)
		).andExpect(status().isOk());  
	}
}
