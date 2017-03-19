package br.com.paulognr.test.unit;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.paulognr.business.ProductBO;
import br.com.paulognr.business.impl.ProductBOImpl;
import br.com.paulognr.dao.ProductDAO;
import br.com.paulognr.entity.ProductEntity;

@RunWith(MockitoJUnitRunner.class)
public class ProductBOTest {

	@InjectMocks
	private ProductBO bo = spy(new ProductBOImpl());
	
	@Mock
	private ProductDAO dao;

	@Test
	public void findAllEmpty(){
		when(dao.findAll()).thenReturn(Collections.emptyList());

		assertThat(bo.findAll(), hasSize(0));
		verify(dao, times(1)).findAll();
	}
	
	@Test
	public void findAll(){
		List<ProductEntity> mockResult = new ArrayList<>();
		ProductEntity entity1 = new ProductEntity();
		entity1.setId(1);
		mockResult.add(entity1);
		
		ProductEntity entity2 = new ProductEntity();
		entity2.setId(2);
		mockResult.add(entity2);
		when(dao.findAll()).thenReturn(mockResult);
		
		List<ProductEntity> result = bo.findAll();
		
		assertThat(result, hasSize(2));
		assertThat(result.get(0), equalTo(entity1));
		assertThat(result.get(1), equalTo(entity2));
		verify(dao, times(1)).findAll();
	}
}
