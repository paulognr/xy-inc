package br.com.paulognr.test.integrated.resource;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public final class ProductTestResource {
	
	private static final String ROOT_PATH = "/api/v1/products";
	private static final String FIND_BY_ID = ROOT_PATH + "/{id}";

	private ProductTestResource() {}
	
	public static Response findAll(){
		return given().get(ROOT_PATH).andReturn();
	}
	
	public static Response findById(int id){
		return given().pathParam("id", id).get(FIND_BY_ID).andReturn();
	}
}
