package br.com.paulognr.dao.impl;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import br.com.paulognr.api.entity.ProductEntity;
import br.com.paulognr.application.exception.BaseDaoException;
import br.com.paulognr.dao.ProductDAO;

@RequestScoped
public class ProductDAOImpl implements ProductDAO {

	private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().lookupClass());

	@PersistenceContext
	private EntityManager em;

	public ProductEntity insert(ProductEntity entity) throws BaseDaoException {
		
		try {
			em.persist(entity);
		} catch (Exception e) {
			LOG.error("Error to persiste object", e);
			throw new BaseDaoException(BaseDaoException.PERSISTENCE_ERROR, e);
		}
		
		return entity; 
	}

	public ProductEntity update(ProductEntity entity) throws BaseDaoException {
		
		try {
			em.merge(entity);
		} catch (Exception e) {
			LOG.error("Error to persiste object", e);
			throw new BaseDaoException(BaseDaoException.PERSISTENCE_ERROR, e);
		}
		
		return entity; 
	}

	public void remove(int id) throws BaseDaoException {
		try {
			ProductEntity entity = em.find(ProductEntity.class, id);
			em.remove(entity);
		} catch (Exception e) {
			LOG.error("Error to remove object", e);
			throw new BaseDaoException(BaseDaoException.PERSISTENCE_ERROR, e);
		}
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
