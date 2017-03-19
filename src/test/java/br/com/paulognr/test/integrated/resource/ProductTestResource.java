package br.com.paulognr.test.integrated.resource;

import static io.restassured.RestAssured.given;

import com.google.gson.Gson;

import br.com.paulognr.dto.ProductDTO;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

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
	
	public static Response insert(){
		return insert(null);
	}
	
	public static Response insert(ProductDTO dto){
		RequestSpecification spec = given();
		
		if(dto != null){
			spec.body(new Gson().toJson(dto).toString());
		}
		
		return spec.post(ROOT_PATH).andReturn();
	}
}
