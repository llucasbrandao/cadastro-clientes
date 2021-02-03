package com.lucasbrandao.cadastroclientes.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.lucasbrandao.cadastroclientes.dto.EstadoDTO;
import com.lucasbrandao.cadastroclientes.entities.EstadoEntity;

@Mapper(
	componentModel = "spring", 
	nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface EstadoMapper {
	
	EstadoDTO toDTO(EstadoEntity estadoEntity);
	
	EstadoEntity fromDTO(EstadoDTO estadoDTO);
}