package br.com.paulognr.dao;

import java.util.List;
import java.util.Optional;

import br.com.paulognr.api.entity.ProductEntity;
import br.com.paulognr.application.exception.BaseDaoException;

public interface ProductDAO {

	ProductEntity insert(ProductEntity entity) throws BaseDaoException;
	ProductEntity update(ProductEntity entity) throws BaseDaoException;
	void remove(int id) throws BaseDaoException;
	Optional<ProductEntity> findById(int id);
	List<ProductEntity> findAll();
	
}
