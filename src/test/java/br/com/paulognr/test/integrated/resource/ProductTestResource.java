package br.com.paulognr.test.integrated.resource;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public final class ProductTestResource {
	
	private static final String FIND_ALL = "/api/v1/products";

	private ProductTestResource() {}
	
	public static Response findAll(){
		return given().get(FIND_ALL).andReturn();
	}
}
