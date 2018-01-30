package harry.exceptions;

import harry.domain.ResultCode;

/**
 * 
 * @author harry
 *
 */
public class ApplicationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7502143711276894486L;
	
	public static final String MESSAGE = "应用异常";
	
	protected int code = ResultCode.APPLICATION_ERROR;
	
	public ApplicationException(String message) {
		super(message);
	}
	
	public ApplicationException(int code, String message) {
		super(message);
		this.code = code;
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ApplicationException(int code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public ApplicationException(Throwable cause) {
		super(cause);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
