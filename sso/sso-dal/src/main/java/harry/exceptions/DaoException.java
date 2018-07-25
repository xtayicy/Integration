package harry.exceptions;

import harry.domain.ResultCode;

/**
 * 
 * @author harry
 *
 */
public class DaoException extends ApplicationException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7895373426405676424L;
	public static final String MESSAGE = "数据访问异常";

	public DaoException() {
		super(MESSAGE);
	}

	public DaoException(String message) {
		super(message);
		this.code = ResultCode.DAO_ERROR;
	}
	
	public DaoException(int code, String message) {
		super(message);
		this.code = code;
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
		this.code = ResultCode.DAO_ERROR;
	}
	
	public DaoException(int code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public DaoException(Throwable cause) {
		super(cause);
		this.code = ResultCode.DAO_ERROR;
	}
}
