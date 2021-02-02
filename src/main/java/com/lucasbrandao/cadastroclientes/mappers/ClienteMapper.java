package com.lucasbrandao.cadastroclientes.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.lucasbrandao.cadastroclientes.dto.ClienteDTO;
import com.lucasbrandao.cadastroclientes.entities.ClienteEntity;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
	
	@Mapping(target = "estado", source = "cidade.estado.nome")
	@Mapping(target = "cidade", source = "cidade.nome")
	ClienteDTO toDTO(ClienteEntity clienteEntity);
	
	@Mapping(target = "cidade.nome", source = "cidade")
	@Mapping(target = "cidade.estado.nome", source = "estado")
	ClienteEntity fromDTO(ClienteDTO clienteDTO);
}