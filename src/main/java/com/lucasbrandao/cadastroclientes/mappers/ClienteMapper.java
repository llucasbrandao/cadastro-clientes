package com.lucasbrandao.cadastroclientes.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.lucasbrandao.cadastroclientes.dto.ClienteDTO;
import com.lucasbrandao.cadastroclientes.entities.ClienteEntity;

@Mapper(
	componentModel = "spring", 
	nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ClienteMapper {
	
	@Mapping(target = "estado", source = "cidade.estado.nome")
	@Mapping(target = "cidade", source = "cidade.nome")
	@Mapping(target = "senha", ignore = true)
	ClienteDTO toDTO(ClienteEntity clienteEntity);
	
	@Mapping(target = "cidade.nome", source = "cidade")
	@Mapping(target = "cidade.estado.nome", source = "estado")
	ClienteEntity fromDTO(ClienteDTO clienteDTO);
	
	@Mapping(target = "cidade.nome", source = "cidade")
	@Mapping(target = "cidade.estado.nome", source = "estado")
	void atualizaClienteFromDTO(ClienteDTO clienteDTO, @MappingTarget ClienteEntity clienteEntity);
}