package br.com.paulognr.resource;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public abstract class BaseRestResource {

    private static final Gson GSON = new GsonBuilder().create();

    public static Response buildResponse(Response.Status status) {
        return buildResponse(status, null);
    }
    
    public static Response buildResponse(Response.Status status, Object result) {
        return buildResponse(status, result, null);
    }
    
    public static Response buildResponse(Response.Status status, Object result, Map<String, Object> attributes) {
        Response.ResponseBuilder response = Response.status(status.getStatusCode());
        Map<String, Object> wrappedResult = new HashMap<>();
        if(result != null) {
            wrappedResult.put("data", result);
        }
        if(attributes != null){
            attributes.forEach(wrappedResult::put);
        }

        response.entity(GSON.toJson(wrappedResult));
        return response.build();
    }
}