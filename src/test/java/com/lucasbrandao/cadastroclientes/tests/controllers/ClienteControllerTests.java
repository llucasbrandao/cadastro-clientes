package com.lucasbrandao.cadastroclientes.tests.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasbrandao.cadastroclientes.dto.CidadeDTO;
import com.lucasbrandao.cadastroclientes.dto.ClienteDTO;
import com.lucasbrandao.cadastroclientes.dto.EstadoDTO;
import com.lucasbrandao.cadastroclientes.enums.SexoEnum;
import com.lucasbrandao.cadastroclientes.security.JWTUtil;
import com.lucasbrandao.cadastroclientes.services.impl.ClienteServiceImpl;
import com.lucasbrandao.cadastroclientes.services.impl.UserDetailsServiceImpl;

@SpringBootTest
public class ClienteControllerTests {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@MockBean
	private ClienteServiceImpl clienteService;
	
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
	
	public ClienteControllerTests() {
		JWT_USER_TOKEN = JWTUtil.generateTestToken("usuario@usuario.com.br");
		JWT_ADMIN_TOKEN = JWTUtil.generateTestToken("admin@admin.com.br");
	}
	
	@Test
	public void criarClienteComTokenAdmin() throws Exception {
		ClienteDTO clienteDTO = new ClienteDTO();
		
		clienteDTO.setNomeCompleto("Teste da Silva Sauro");
		clienteDTO.setEmail("testeint@teste.com");
		clienteDTO.setCidade(new CidadeDTO(null, "São Paulo", new EstadoDTO(null, "São Paulo", "SP")));
		clienteDTO.setIsAdmin(false);
		clienteDTO.setSenha("123456");
		clienteDTO.setSexo(SexoEnum.M);
		clienteDTO.setDataNascimento(new Date());
		
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/clientes/novo-cliente")
				.content(new ObjectMapper().writeValueAsString(clienteDTO))
				.header("Authorization", "Bearer " + JWT_ADMIN_TOKEN)
				.contentType(MediaType.APPLICATION_JSON)
		).andExpect(status().isCreated());  
 
	}
	
	@Test
	public void criarClienteComTokenNormal() throws Exception {
		ClienteDTO clienteDTO = new ClienteDTO();
		
		clienteDTO.setNomeCompleto("Teste da Silva Sauro");
		clienteDTO.setEmail("testeint@teste.com");
		clienteDTO.setCidade(new CidadeDTO(null, "São Paulo", new EstadoDTO(null, "São Paulo", "SP")));
		clienteDTO.setIsAdmin(false);
		clienteDTO.setSenha("123456");
		clienteDTO.setSexo(SexoEnum.M);
		clienteDTO.setDataNascimento(new Date());
		
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/clientes/novo-cliente")
				.content(new ObjectMapper().writeValueAsString(clienteDTO))
				.header("Authorization", "Bearer " + JWT_USER_TOKEN)
				.contentType(MediaType.APPLICATION_JSON)
		).andExpect(status().isCreated());  
        
	}
}
