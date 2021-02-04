package com.lucasbrandao.cadastroclientes.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.lucasbrandao.cadastroclientes.dto.CidadeDTO;
import com.lucasbrandao.cadastroclientes.entities.CidadeEntity;

@Mapper(
	componentModel = "spring", 
	nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CidadeMapper {
	
	CidadeDTO toDTO(CidadeEntity cidadeEntity);
	
	CidadeEntity fromDTO(CidadeDTO cidadeDTO);
}