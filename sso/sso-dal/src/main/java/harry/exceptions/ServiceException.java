package harry.exceptions;

import harry.domain.ResultCode;

/**
 * 
 * @author harry
 *
 */
public class ServiceException extends ApplicationException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1393665433708721295L;
	public static final String MESSAGE = "业务逻辑异常";
	
	public ServiceException() {
		super(MESSAGE);
	}

	public ServiceException(String message) {
		super(message);
		this.code = ResultCode.SERVICE_ERROR;
	}
	
	public ServiceException(int code, String message) {
		super(message);
		this.code = code;
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		this.code = ResultCode.SERVICE_ERROR;
	}
	
	public ServiceException(int code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public ServiceException(Throwable cause) {
		super(cause);
		this.code = ResultCode.SERVICE_ERROR;
	}
}
