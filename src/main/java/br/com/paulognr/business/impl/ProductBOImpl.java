package br.com.paulognr.business.impl;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;

import br.com.paulognr.api.entity.ProductEntity;
import br.com.paulognr.api.exception.ProductException;
import br.com.paulognr.application.exception.BaseException;
import br.com.paulognr.business.ProductBO;
import br.com.paulognr.dao.ProductDAO;

@RequestScoped
@Transactional
public class ProductBOImpl implements ProductBO {

	private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	@Inject
	private ProductDAO dao;

	public ProductEntity insert(ProductEntity entity) throws BaseException {
		if(entity.getId() != null){
			throw new ProductException(ProductException.ID_MUST_BE_NULL); 
		}
		
		try {
			entity = dao.insert(entity);
		} catch (Exception e) {
			LOG.error("Error insert product", e);
			throw new BaseException(BaseException.GENERIC_ERROR);
		}
		
		return entity;
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
