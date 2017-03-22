package br.com.paulognr.test.integrated;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

import javax.ws.rs.core.Response.Status;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.paulognr.api.exception.ProductException;
import br.com.paulognr.application.exception.ParamConstraintException;
import br.com.paulognr.dto.ProductDTO;
import br.com.paulognr.test.integrated.core.BaseIntegratedTest;
import br.com.paulognr.test.integrated.resource.ProductTestResource;

public class ProductResourceIT extends BaseIntegratedTest {

	@BeforeClass
	public static void setup() {
		ProductDTO dto = new ProductDTO();
		dto.setName("Xbox 360");
		dto.setDescription("Xbox 360 bloqueado");
		dto.setPrice(0.6);
		dto.setCategory("Console");

		ProductTestResource.insert(dto);
	}

	@Test
	public void findAll() {
		ProductTestResource.findAll().then().assertThat().body("data", hasSize(greaterThan(1)), "data.id", hasItem(1));
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

	@Test
	public void insert() {
		ProductDTO dto = new ProductDTO();
		dto.setName("name");
		dto.setDescription("description");
		dto.setPrice(1.0);
		dto.setCategory("category");

		ProductTestResource.insert(dto).then().assertThat().statusCode(Status.CREATED.getStatusCode()).and()
				.root("data").body("id", notNullValue()).and().body("name", equalTo(dto.getName())).and()
				.body("description", equalTo(dto.getDescription())).and().body("category", equalTo(dto.getCategory()));
	}

}
