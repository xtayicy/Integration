package harry.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import harry.domain.RpcUser;
import harry.domain.SessionUser;
import harry.service.IAuthenticationRpcService;
import harry.utils.ApplicationUtil;

/**
 * 
 * @author harry
 *
 */
public class SSOInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private IAuthenticationRpcService authenticationRpcService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = getToken(request);
		
		if(StringUtils.isEmpty(token)){
			request.getSession().invalidate();
			
			String ssoLoginUrl = new StringBuilder().append(ApplicationUtil.getProperty(ApplicationUtil.SSO_SERVER_URL))
					.append("/login?backUrl=").append(getLocalUrl(request)).append("/admin/admin").toString();
			
			response.sendRedirect(ssoLoginUrl);
			
			return false;
		}else{
			if(!authenticationRpcService.validate(token)){
				request.getSession().invalidate();
				
				String ssoLoginUrl = new StringBuilder().append(ApplicationUtil.getProperty(ApplicationUtil.SSO_SERVER_URL))
						.append("/login?backUrl=").append(getLocalUrl(request)).append("/admin/admin").toString();
				
				response.sendRedirect(ssoLoginUrl);
				
				return false;
			}
			
			return true;
		}
	}

	private String getToken(HttpServletRequest request) {
		SessionUser user = ApplicationUtil.getSessionUser(request);
		String token = null;
		if (user != null) {
			token = user.getToken();
		}else{
			token = request.getParameter(ApplicationUtil.SSO_TOKEN_NAME);
			if(!StringUtils.isEmpty(token)){
				RpcUser rpcUser = authenticationRpcService.findAuthInfo(token);
				if (rpcUser != null) {
					ApplicationUtil.setSessionUser(request, new SessionUser(token, rpcUser.getUserName(), rpcUser.getProfile()));
				}
			}
		}
		
		return token;
	}
	
	public static String getLocalUrl(HttpServletRequest request) {
		return new StringBuilder().append(request.getScheme()).append("://").append(request.getServerName())
				.append(":").append(request.getServerPort() == 80 ? "" : request.getServerPort())
				.append(request.getContextPath()).toString();
	}
}
