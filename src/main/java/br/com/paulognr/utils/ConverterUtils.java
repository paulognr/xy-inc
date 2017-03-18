package br.com.paulognr.utils;

import br.com.paulognr.dto.ProductDTO;
import br.com.paulognr.entity.ProductEntity;

public final class ConverterUtils {
	private ConverterUtils() {
		super();
	}
	
	public static ProductDTO toDto(ProductEntity entity){
		ProductDTO dto = null;
		if(entity != null){
			dto = new ProductDTO();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setDescription(entity.getDescription());
			dto.setPrice(entity.getPrice());
			dto.setCategory(entity.getCategory());
		}
		return dto;
	}

}
