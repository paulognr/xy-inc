package br.com.paulognr.application.exception;

import com.google.gson.JsonObject;

public class BaseException extends Exception {

	private static final long serialVersionUID = 1L;

	public static final ErrorCode GENERIC_ERROR = new ErrorCode("XY_BASE_0000", "An unknown error occurred.");
	
    private String code;
    private String description;
    private String details;
    
    public BaseException(String code, String description, String details, Throwable cause) {
        super(details, cause);
        this.code = code;
        this.description = description;
        this.details = details;
    }

    public BaseException(String code, String description, Throwable cause) {
        this(code, description, null, cause);
    }

    public BaseException(String code, String description, String details) {
        this(code, description, details, null);
    }

    public BaseException(String code, String description) {
        this(code, description, null, null);
    }

    public BaseException(ErrorCode error, String details, Throwable cause) {
        this(error.getCode(), error.getDescription(), details, cause);
    }

    public BaseException(ErrorCode error, String details) {
        this(error.getCode(), error.getDescription(), details, null);
    }

    public BaseException(ErrorCode error, Throwable cause) {
        this(error.getCode(), error.getDescription(), null, cause);
    }

    public BaseException(ErrorCode error) {
        this(error.getCode(), error.getDescription(), null, null);
    }
    
    public JsonObject toJson() {
        JsonObject jObject = new JsonObject();
        jObject.addProperty("code", code);
        jObject.addProperty("description", description);
        if (details != null) {
            jObject.addProperty("details", details);
        }
        if (this.getCause() != null) {
            jObject.addProperty("causedBy", this.getCause().getClass().getName());
        }
        return jObject;
    }
	
}
