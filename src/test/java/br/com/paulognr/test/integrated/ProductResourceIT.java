package br.com.paulognr.test.integrated;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

import javax.ws.rs.core.Response.Status;

import org.junit.Test;

import br.com.paulognr.api.exception.ProductException;
import br.com.paulognr.application.exception.ParamConstraintException;
import br.com.paulognr.dto.ProductDTO;
import br.com.paulognr.test.integrated.core.BaseIntegratedTest;
import br.com.paulognr.test.integrated.resource.ProductTestResource;

public class ProductResourceIT extends BaseIntegratedTest {

	@Test
	public void findAll() {
		ProductTestResource.findAll().then().assertThat().body("data", hasSize(1), "data.id", hasItem(1));
	}

	@Test
	public void findByIdNotFound() {
		ProductTestResource.findById(99).then().assertThat().statusCode(Status.NOT_FOUND.getStatusCode());
	}

	@Test
	public void findById() {
		ProductTestResource.findById(1).then().assertThat().body("data.id", equalTo(1));
	}

	@Test
	public void insertNull() {
		ProductTestResource.insert().then().assertThat().statusCode(Status.BAD_REQUEST.getStatusCode()).and()
				.body("code", equalTo(ParamConstraintException.INVALID_PARAMS.getCode())).and()
				.body("violations.messageId", hasItem("javax.validation.constraints.NotNull.message"));
	}

	@Test
	public void insertIdMustBeNull() {
		ProductDTO dto = new ProductDTO();
		dto.setId(99);
		
		ProductTestResource.insert(dto).then().assertThat().statusCode(Status.BAD_REQUEST.getStatusCode()).and()
				.body("code", equalTo(ProductException.ID_MUST_BE_NULL.getCode()));
	}

}
