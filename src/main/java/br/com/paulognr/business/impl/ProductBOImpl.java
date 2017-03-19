package br.com.paulognr.business.impl;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.paulognr.api.entity.ProductEntity;
import br.com.paulognr.api.exception.ProductException;
import br.com.paulognr.business.ProductBO;
import br.com.paulognr.dao.ProductDAO;

@RequestScoped
public class ProductBOImpl implements ProductBO {

	@Inject
	private ProductDAO dao;

	public ProductEntity insert(ProductEntity entity) throws ProductException {
		if(entity.getId() != null){
			throw new ProductException(ProductException.ID_MUST_BE_NULL); 
		}
		
		return dao.insert(entity);
	}

	public ProductEntity update(ProductEntity entity) {
		return null;
	}

	public void remove(ProductEntity entity) {

	}

	public Optional<ProductEntity> findById(int id) {
		return dao.findById(id);
	}

	public List<ProductEntity> findAll() {
		return dao.findAll();
	}
	
}
