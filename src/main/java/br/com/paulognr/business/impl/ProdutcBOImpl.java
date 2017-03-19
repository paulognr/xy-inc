package br.com.paulognr.business.impl;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.paulognr.business.ProductBO;
import br.com.paulognr.dao.ProductDAO;
import br.com.paulognr.entity.ProductEntity;

@RequestScoped
public class ProdutcBOImpl implements ProductBO {

	@Inject
	private ProductDAO dao;

	public ProductEntity insert(ProductEntity entity) {
		return null;
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
