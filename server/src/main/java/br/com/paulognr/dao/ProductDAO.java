package br.com.paulognr.dao;

import java.util.List;
import java.util.Optional;

import br.com.paulognr.entity.ProductEntity;

public interface ProductDAO {

	ProductEntity insert(ProductEntity entity);
	ProductEntity update(ProductEntity entity);
	void remove(ProductEntity entity);
	Optional<ProductEntity> findById(int id);
	List<ProductEntity> findAll();
	
}
