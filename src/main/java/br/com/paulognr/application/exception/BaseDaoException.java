package br.com.paulognr.application.exception;

import com.google.gson.JsonObject;

public class BaseDaoException extends Exception {

	private static final long serialVersionUID = 1L;

	public static final ErrorCode PERSISTENCE_ERROR = new ErrorCode("XY_PERSISTENCE_0000", "Error to persiste object.");
	
    private String code;
    private String description;
    private String details;
    
    public BaseDaoException(String code, String description, String details, Throwable cause) {
        super(details, cause);
        this.code = code;
        this.description = description;
        this.details = details;
    }

    public BaseDaoException(String code, String description, Throwable cause) {
        this(code, description, null, cause);
    }

    public BaseDaoException(String code, String description, String details) {
        this(code, description, details, null);
    }

    public BaseDaoException(String code, String description) {
        this(code, description, null, null);
    }

    public BaseDaoException(ErrorCode error, String details, Throwable cause) {
        this(error.getCode(), error.getDescription(), details, cause);
    }

    public BaseDaoException(ErrorCode error, String details) {
        this(error.getCode(), error.getDescription(), details, null);
    }

    public BaseDaoException(ErrorCode error, Throwable cause) {
        this(error.getCode(), error.getDescription(), null, cause);
    }

    public BaseDaoException(ErrorCode error) {
        this(error.getCode(), error.getDescription(), null, null);
    }
    
    public String getCode() {
    	return code;
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
