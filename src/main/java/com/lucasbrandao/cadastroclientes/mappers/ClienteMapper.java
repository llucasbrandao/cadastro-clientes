package com.lucasbrandao.cadastroclientes.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.lucasbrandao.cadastroclientes.dto.ClienteDTO;
import com.lucasbrandao.cadastroclientes.entities.ClienteEntity;

@Mapper(
	componentModel = "spring", 
	nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ClienteMapper {
	
	@Mapping(target = "senha", ignore = true)
	ClienteDTO toDTO(ClienteEntity clienteEntity);
	
	ClienteEntity fromDTO(ClienteDTO clienteDTO);
}