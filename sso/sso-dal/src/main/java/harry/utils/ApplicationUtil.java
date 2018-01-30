package harry.utils;

import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ContextLoader;


import harry.domain.RpcPermission;
import harry.domain.SessionPermission;
import harry.domain.SessionUser;

/**
 * 
 * @author harry
 *
 */
public final class ApplicationUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationUtil.class);
	
	/**
	 * sso授权回调参数token名称
	 */
	public static final String SSO_TOKEN_NAME = "__vt_param__";
	
	/**
	 * 登录用户
	 */
	public static final String SESSION_USER = "_sessionUser";
	
	public static final String SSO_SERVER_URL = "sso.server.url";
	
	public static final String SSO_APP_CODE = "sso.app.code";
	
	/**
	 * 系统所有菜单
	 */
	public static final String APPLICATION_MENU = "_applicationMenu";
	
	/**
	 * 系统所有权限
	 */
	public static final String APPLICATION_PERMISSION = "_applicationPermission";
	
	/**
	 * 登录用户权限
	 */
	public static final String SESSION_USER_PERMISSION = "_sessionUserPermission";
	
	public static final String SYSTEM_INIT_PASSWORD = "system.init.password";
	
	private static Properties properties;
	
	static{
		try {
			ClassPathResource resource = new ClassPathResource("config.properties");
			properties = new Properties();
			properties.load(resource.getInputStream());
		} catch (Exception e) {
			if(LOGGER.isWarnEnabled()){
				LOGGER.info("no config.properties in the classpath,fail init!");
			}
		}
	}
	
	public static <T> T getBean(Class<T> requiredType){
		
		return ContextLoader.getCurrentWebApplicationContext().getBean(requiredType);
	}

	public static SessionUser getSessionUser(HttpServletRequest request) {
		return (SessionUser) request.getSession().getAttribute(SESSION_USER);
	}
	
	public static void setSessionUser(HttpServletRequest request, SessionUser sessionUser) {
		request.getSession().setAttribute(SESSION_USER, sessionUser);
	}
	
	public static Object getProperty(String key) throws RuntimeException{
		
		return properties == null ? null : properties.get(key);
	}

	public static void setApplicationMenu(ServletContext servletContext, List<RpcPermission> menuList) {
		servletContext.setAttribute(APPLICATION_MENU, menuList);
	}

	public static void setApplicationPermission(ServletContext servletContext, Set<String> operateSet) {
		servletContext.setAttribute(APPLICATION_PERMISSION, operateSet);
	}
	
	@SuppressWarnings("unchecked")
	public static Set<String> getApplicationPermission(HttpServletRequest request) {
		return (Set<String>) request.getServletContext().getAttribute(APPLICATION_PERMISSION);
	}

	public static SessionPermission getSessionPermission(HttpServletRequest request) {
		return (SessionPermission) request.getSession().getAttribute(SESSION_USER_PERMISSION);
	}
	
	public static void setSessionPermission(HttpServletRequest request, SessionPermission sessionPermission) {
		request.getSession().setAttribute(SESSION_USER_PERMISSION, sessionPermission);
	}
	
	@SuppressWarnings("unchecked")
	public static List<RpcPermission> getApplicationMenu(HttpServletRequest request) {
		return (List<RpcPermission>) request.getServletContext().getAttribute(APPLICATION_MENU);
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			}else {
				return ip;
			}
		}else {
			return request.getRemoteAddr();
		}
	}
}
