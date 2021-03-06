package br.com.paulognr.resource;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.paulognr.api.entity.ProductEntity;
import br.com.paulognr.application.exception.BaseException;
import br.com.paulognr.business.ProductBO;
import br.com.paulognr.dto.ProductDTO;
import br.com.paulognr.utils.ConverterUtils;

@ApplicationScoped
@Path("/products")
public class ProductResource extends BaseRestResource{
	
	@Inject
	private ProductBO bo;

	@GET
	public Response findAll() {
		return buildResponse(Status.OK, bo.findAll().stream().map(p -> ConverterUtils.toDto(p))
				.collect(Collectors.toList()));
	}
	
	@GET
	@Path("/{id}")
	public Response findById(@PathParam("id") int id) throws BaseException{
		Optional<ProductEntity> entity = bo.findById(id);
		
		if(!entity.isPresent()){
			return buildResponse(Status.NOT_FOUND);
		}
		
		return buildResponse(Status.OK, entity.get());
	}
	
	@POST
	public Response insert(@NotNull ProductDTO dto) throws BaseException{
		ProductEntity result = bo.insert(ConverterUtils.toEntity(dto));
		return buildResponse(Status.CREATED, result);
	}
	
	@PUT
	@Path("/{id}")
	public Response update(@NotNull @PathParam("id") int id, @NotNull ProductDTO dto) throws BaseException{
		dto.setId(id);
		ProductEntity result = bo.update(ConverterUtils.toEntity(dto));
		return buildResponse(Status.OK, result);
	}
	
	@DELETE
	@Path("/{id}")
	public Response remove(@NotNull @PathParam("id") int id) throws BaseException{
		bo.remove(id);
		return buildResponse(Status.NO_CONTENT);
	}
}
