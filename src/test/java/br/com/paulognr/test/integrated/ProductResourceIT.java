package br.com.paulognr.test.integrated;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

import javax.ws.rs.core.Response.Status;

import org.junit.Test;

import br.com.paulognr.test.integrated.core.BaseIntegratedTest;
import br.com.paulognr.test.integrated.resource.ProductTestResource;

public class ProductResourceIT extends BaseIntegratedTest{

	@Test
	public void findAll(){
		ProductTestResource.findAll().then().assertThat().body("data", hasSize(1) ,"data.id", hasItem(1));
	}
	
	@Test
	public void findByIdNotFound(){
		ProductTestResource.findById(99).then().assertThat().statusCode(Status.NOT_FOUND.getStatusCode());
	}
	
	@Test
	public void findById(){
		ProductTestResource.findById(1).then().assertThat().body("data.id", equalTo(1));
	}
	
}
