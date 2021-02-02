package com.lucasbrandao.cadastroclientes.mappers;

import org.mapstruct.Mapper;

import com.lucasbrandao.cadastroclientes.dto.ClienteDTO;
import com.lucasbrandao.cadastroclientes.entities.ClienteEntity;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
	
	ClienteDTO toDTO(ClienteEntity clienteEntity);
	ClienteEntity fromDTO(ClienteDTO clienteDTO);
}
