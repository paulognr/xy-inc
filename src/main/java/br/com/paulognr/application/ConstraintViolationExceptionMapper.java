package br.com.paulognr.application;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.paulognr.application.exception.ParamConstraintException;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException ex) {
		ParamConstraintException e = new ParamConstraintException(ParamConstraintException.INVALID_PARAMS,
				ex.getConstraintViolations());
		return Response.status(Response.Status.BAD_REQUEST).entity(e.toJson().toString())
				.type(MediaType.APPLICATION_JSON).build();
	}

}
