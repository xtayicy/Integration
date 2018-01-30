package harry.servlet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import harry.domain.RpcPermission;
import harry.service.IAuthenticationRpcService;
import harry.utils.ApplicationUtil;

/**
 * 
 * @author harry
 *
 */
public class PermissionInitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7907088600614778116L;

	private static final Logger LOGGER = LoggerFactory.getLogger(PermissionInitServlet.class);

	@Override
	public void init() throws ServletException {
		ServletContext servletContext = getServletContext();
		IAuthenticationRpcService authenticationRpcService = ApplicationUtil.getBean(IAuthenticationRpcService.class);

		List<RpcPermission> rpcPermissions = null;
		try {
			rpcPermissions = authenticationRpcService.getAllPermissions(null,
					(String) ApplicationUtil.getProperty(ApplicationUtil.SSO_APP_CODE));
		} catch (RuntimeException e) {
			e.printStackTrace();
			rpcPermissions = new ArrayList<RpcPermission>();
			if (LOGGER.isWarnEnabled()) {
				LOGGER.info("fail to connect to " + (String) ApplicationUtil.getProperty(ApplicationUtil.SSO_APP_CODE));
			}
		}

		if (rpcPermissions != null) {
			List<RpcPermission> menuList = new ArrayList<RpcPermission>();
			Set<String> operateSet = new HashSet<String>();
			for (RpcPermission menu : rpcPermissions) {
				if (menu.getIsMenu()) {
					menuList.add(menu);
				}
				if (!StringUtils.isEmpty(menu.getUrl())) {
					operateSet.add(menu.getUrl());
				}
			}

			ApplicationUtil.setApplicationMenu(servletContext, menuList);
			ApplicationUtil.setApplicationPermission(servletContext, operateSet);
		}
	}
}
