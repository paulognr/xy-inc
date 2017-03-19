package br.com.paulognr.resource;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;

import br.com.paulognr.business.ProductBO;
import br.com.paulognr.entity.ProductEntity;
import br.com.paulognr.utils.ConverterUtils;

@ApplicationScoped
@Path("/products")
public class ProductResource extends BaseRestResource{
	
	private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().lookupClass());

	@Inject
	private ProductBO bo;

	@GET
	@Consumes
	public Response findAll() {
		LOG.debug("findAll");
		
		List<ProductEntity> products = bo.findAll();
		return buildResponse(Status.OK, products.stream().map(p -> ConverterUtils.toDto(p))
				.collect(Collectors.toList()));
	}

}
