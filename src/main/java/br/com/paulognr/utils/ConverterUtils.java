package br.com.paulognr.utils;

import br.com.paulognr.api.entity.ProductEntity;
import br.com.paulognr.dto.ProductDTO;

public final class ConverterUtils {
	private ConverterUtils() {
		super();
	}

	public static ProductDTO toDto(ProductEntity entity) {
		ProductDTO dto = null;
		if (entity != null) {
			dto = new ProductDTO();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setDescription(entity.getDescription());
			dto.setPrice(entity.getPrice());
			dto.setCategory(entity.getCategory());
		}
		return dto;
	}

	public static ProductEntity toEntity(ProductDTO dto) {
		ProductEntity entity = null;
		if (dto != null) {
			entity = new ProductEntity();
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			entity.setDescription(dto.getDescription());
			entity.setPrice(dto.getPrice());
			entity.setCategory(dto.getCategory());
		}
		return entity;
	}

}
