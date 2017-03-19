package br.com.paulognr.application;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.paulognr.application.exception.BaseException;

@Provider
public class BaseExceptionMapper implements ExceptionMapper<BaseException> {

	@Override
	public Response toResponse(BaseException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(e.toJson().toString())
                .type(MediaType.APPLICATION_JSON)
                .build();
	}

}
