package com.lucasbrandao.cadastroclientes.init;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.lucasbrandao.cadastroclientes.entities.CidadeEntity;
import com.lucasbrandao.cadastroclientes.entities.ClienteEntity;
import com.lucasbrandao.cadastroclientes.entities.EstadoEntity;
import com.lucasbrandao.cadastroclientes.enums.SexoEnum;
import com.lucasbrandao.cadastroclientes.repositories.CidadeRepository;
import com.lucasbrandao.cadastroclientes.repositories.ClienteRepository;
import com.lucasbrandao.cadastroclientes.repositories.EstadoRepository;
import com.lucasbrandao.cadastroclientes.utils.LogUtils;

/*
 * Esta classe é responsável por inserir dados de testes no banco, caso ele esteja vazio.
 * Usada apenas em teste.
 * Cria 2 instâncias de cada: usuário, estado e cidade.
 */

@Component
public class DBInit {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@EventListener(ApplicationReadyEvent.class) // Roda este médoto depois que o Spring terminar de inicializar
	public void cadastraDadosFake() {
		int _aux = 0;
		
		try {
			LogUtils.log("Iniciando a criação dos dados fake", "info", this.getClass());
			
			if (estadoRepository.count() == 0) {
				EstadoEntity estadoEntity = new EstadoEntity();
				
				estadoEntity.setNome("Minas Gerais");
				estadoEntity.setSigla("MG");
				
				this.estadoRepository.saveAndFlush(estadoEntity);
				
				estadoEntity = new EstadoEntity();
				
				estadoEntity.setNome("Rio Grande do Sul");
				estadoEntity.setSigla("RS");
				
				this.estadoRepository.saveAndFlush(estadoEntity);
				
				_aux++;
			}
			
			if (cidadeRepository.count() == 0) {
				CidadeEntity cidadeEntity = new CidadeEntity();
				
				cidadeEntity.setNome("Belo Horizonte");
				cidadeEntity.setEstado(this.estadoRepository.findBySiglaEstado("MG"));
				
				this.cidadeRepository.saveAndFlush(cidadeEntity);
				
				cidadeEntity = new CidadeEntity();
				
				cidadeEntity.setNome("Porto Alegre");
				cidadeEntity.setEstado(this.estadoRepository.findBySiglaEstado("RS"));
				
				this.cidadeRepository.saveAndFlush(cidadeEntity);
				
				_aux++;
			}
			
			if (clienteRepository.count() == 0) {
				ClienteEntity clienteEntity = new ClienteEntity();
				BCryptPasswordEncoder B_CRYPT = new BCryptPasswordEncoder();
				
				// Cadastra Admin
				clienteEntity.setNomeCompleto("Admin");
				clienteEntity.setIsAdmin(true);
				clienteEntity.setSexo(SexoEnum.M);
				clienteEntity.setCidade(this.cidadeRepository.findByNome("Porto Alegre"));
				clienteEntity.setDataNascimento(new Date());
				clienteEntity.setSenha(B_CRYPT.encode("123456"));
				
				this.clienteRepository.saveAndFlush(clienteEntity);
				
				// Cadastra usuário comum
				clienteEntity = new ClienteEntity();
				
				clienteEntity.setNomeCompleto("Usuário");
				clienteEntity.setIsAdmin(true);
				clienteEntity.setSexo(SexoEnum.M);
				clienteEntity.setCidade(this.cidadeRepository.findByNome("Belo Horizonte"));
				clienteEntity.setDataNascimento(new Date());
				clienteEntity.setSenha(B_CRYPT.encode("123456"));
				
				this.clienteRepository.saveAndFlush(clienteEntity);
				
				_aux++;
			}
			
			if (_aux > 0) {
				LogUtils.log("Criação dos dados fake concluída", "info", this.getClass());
				
			} else {
				LogUtils.log("Dados fake já existem", "info", this.getClass());
			}
			
		} catch (Exception e) {
			LogUtils.log("Falha na criação dos dados fake", "alerta", this.getClass());
			LogUtils.log(e.getMessage(), "erro", this.getClass());
		}
	}
}
