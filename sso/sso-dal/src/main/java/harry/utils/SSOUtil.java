package harry.utils;

import javax.servlet.http.HttpServletRequest;

import harry.domain.SessionPermission;

/**
 * 
 * @author harry
 *
 */
public class SSOUtil {
	/**
	 * 登录用户权限
	 */
	public static final String SESSION_USER_PERMISSION = "_sessionUserPermission_";
	
	public static final String SESSION_USER_NO_PERMISSION = "_sessionUserNoPermission_";
	
	public static SessionPermission getSessionPermission(HttpServletRequest request){
		return (SessionPermission) request.getSession().getAttribute(SESSION_USER_PERMISSION);
	}
}
