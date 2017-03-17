package br.com.paulognr.resource;

import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

@ApplicationPath("/api/v1")
public class ApplicationResource extends Application {

	@Inject
	private ProductResource productResource;

	@Path("products")
	public ProductResource getProductResource() {
		return productResource;
	}
	
}
