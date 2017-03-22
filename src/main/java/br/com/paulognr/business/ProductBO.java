package br.com.paulognr.business;

import java.util.List;
import java.util.Optional;

import br.com.paulognr.api.entity.ProductEntity;
import br.com.paulognr.api.exception.ProductException;
import br.com.paulognr.application.exception.BaseException;

public interface ProductBO {
	ProductEntity insert(ProductEntity entity) throws BaseException;
	ProductEntity update(ProductEntity entity);
	void remove(ProductEntity entity);
	Optional<ProductEntity> findById(int id);
	List<ProductEntity> findAll();
}
