package harry.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import harry.common.TokenManager;
import harry.domain.SessionUser;
import harry.service.IAppService;
import harry.service.IAuthenticationRpcService;
import harry.service.IPermissionService;
import harry.service.IRolePermissionService;
import harry.service.IRoleService;
import harry.service.IUserAppService;
import harry.service.IUserRoleService;
import harry.service.IUserService;
import harry.utils.ApplicationUtil;

/**
 * 
 * @author harry
 *
 */
public abstract class BaseController {
	@Autowired
	protected IUserService userService;
	
	@Autowired
	protected IPermissionService permissionService;
	
	@Autowired
	protected IRolePermissionService rolePermissionService;
	
	@Autowired
	protected IUserRoleService userRoleService;
	
	@Autowired
	protected IRoleService roleService;
	
	@Autowired
	protected TokenManager tokenManager;
	
	@Autowired
	protected IAppService appService;
	
	@Autowired
	protected IUserAppService userAppService;
	
	@Autowired
	protected IAuthenticationRpcService authenticationRpcService;
	
	protected String getToken(HttpServletRequest request) {
		SessionUser user = ApplicationUtil.getSessionUser(request);
		String token = null;
		if (user != null) {
			token = user.getToken();
		}
		
		return token;
	}
	
	private Integer[] getAjaxIds(final String str, final String separator) {
		Integer[] ids = null;
		if (str != null) {
			String[] strs = str.split(separator);
			ids = new Integer[strs.length];
			for (int i = 0, length = strs.length; i < length; i++) {
				ids[i] = Integer.valueOf(strs[i]);
			}
		}
		return ids;
	}
	
	protected List<Integer> getAjaxIds(final String ids) {
		return StringUtils.isEmpty(ids) ? new ArrayList<Integer>(0) : Arrays.asList(getAjaxIds(ids, ","));
	}
}
