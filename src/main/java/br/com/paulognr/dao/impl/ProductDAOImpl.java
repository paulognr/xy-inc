package br.com.paulognr.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.paulognr.dao.ProductDAO;
import br.com.paulognr.entity.ProductEntity;

@ApplicationScoped
public class ProductDAOImpl implements ProductDAO{

	@PersistenceContext
	private EntityManager em;

	public ProductEntity insert(ProductEntity entity) {
		return null;
	}

	public ProductEntity update(ProductEntity entity) {
		return null;
	}

	public void remove(ProductEntity entity) {
		
	}

	public Optional<ProductEntity> findById(int id) {
		return Optional.empty();
	}

	public List<ProductEntity> findAll() {
		return null;
	}
	
}
