package br.com.paulognr.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.paulognr.business.ProductBO;
import br.com.paulognr.entity.ProductEntity;
import br.com.paulognr.utils.ConverterUtils;

public class ProductResource extends BaseRestResource{

	@Inject
	private ProductBO bo;

	@GET
	public Response findAll() {
		List<ProductEntity> products = bo.findAll();
		return buildResponse(Status.OK, products.stream().map(p -> ConverterUtils.toDto(p))
				.collect(Collectors.toList()));
	}

}
