package harry.exceptions;

import harry.domain.ResultCode;

/**
 * 
 * @author harry
 *
 */
public class ValidateException extends ApplicationException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5895116623992447205L;
	public static final String MESSAGE = "验证异常";

	public ValidateException() {
		super(MESSAGE);
	}

	public ValidateException(String message) {
		super(message);
		this.code = ResultCode.VALIDATE_ERROR;
	}
	
	public ValidateException(int code, String message) {
		super(message);
		this.code = code;
	}

	public ValidateException(String message, Throwable cause) {
		super(message, cause);
		this.code = ResultCode.VALIDATE_ERROR;
	}
	
	public ValidateException(int code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public ValidateException(Throwable cause) {
		super(cause);
		this.code = ResultCode.VALIDATE_ERROR;
	}
}
