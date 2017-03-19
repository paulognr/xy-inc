package br.com.paulognr.dao.impl;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import br.com.paulognr.dao.ProductDAO;
import br.com.paulognr.entity.ProductEntity;

@RequestScoped
public class ProductDAOImpl implements ProductDAO {

	private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().lookupClass());

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
		if (LOG.isDebugEnabled()) {
			LOG.debug("findById - id:" + id);
		}

		ProductEntity entity = em.find(ProductEntity.class, id);

		if (LOG.isDebugEnabled()) {
			LOG.debug("findById - result: " + entity);
		}
		
		return Optional.ofNullable(entity);
	}

	public List<ProductEntity> findAll() {
		LOG.debug("findAll");

		List<ProductEntity> result = em.createNamedQuery("ProductEntity.findAll", ProductEntity.class).getResultList();

		if (LOG.isDebugEnabled()) {
			LOG.debug("findAll - result: " + result);
		}

		return result;
	}
}
