package br.com.paulognr.test.integrated;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Test;

import br.com.paulognr.test.integrated.core.BaseIntegratedTest;
import br.com.paulognr.test.integrated.resource.ProductTestResource;

public class ProductResourceIT extends BaseIntegratedTest{

	@Test
	public void findAll(){
		ProductTestResource.findAll().then().assertThat().body("data", hasSize(1) ,"data.id", hasItem(1));
	}
	
}
