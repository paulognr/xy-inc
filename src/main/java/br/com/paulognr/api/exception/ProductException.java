package br.com.paulognr.api.exception;

import br.com.paulognr.application.exception.BaseException;
import br.com.paulognr.application.exception.ErrorCode;

public class ProductException extends BaseException{

	private static final long serialVersionUID = 1L;
	
	public static final ErrorCode ID_MUST_BE_NULL = new ErrorCode("XY_PRODUCT_0001", "Id must be null.");
	public static final ErrorCode ID_MUST_NOT_BE_NULL = new ErrorCode("XY_PRODUCT_0002", "Id must not be null.");
	public static final ErrorCode NOT_FOUND = new ErrorCode("XY_PRODUCT_0003", "Product not found.");
	
    public ProductException(String code, String description, String details, Throwable cause) {
        super(code, description, details, cause);
    }

    public ProductException(String code, String description, Throwable cause) {
        this(code, description, null, cause);
    }

    public ProductException(String code, String description, String details) {
        this(code, description, details, null);
    }

    public ProductException(String code, String description) {
        this(code, description, null, null);
    }

    public ProductException(ErrorCode error, String details, Throwable cause) {
        this(error.getCode(), error.getDescription(), details, cause);
    }

    public ProductException(ErrorCode error, String details) {
        this(error.getCode(), error.getDescription(), details, null);
    }

    public ProductException(ErrorCode error, Throwable cause) {
        this(error.getCode(), error.getDescription(), null, cause);
    }

    public ProductException(ErrorCode error) {
        this(error.getCode(), error.getDescription(), null, null);
    }
	
}
