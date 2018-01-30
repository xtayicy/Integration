package harry.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import harry.common.TokenManager;
import harry.domain.LoginUser;
import harry.domain.RpcPermission;
import harry.domain.RpcUser;
import harry.domain.User;
import harry.service.IAuthenticationRpcService;
import harry.service.IRpcPermissionService;
import harry.service.IUserService;
import harry.utils.PasswordProvider;

/**
 * 
 * @author harry
 *
 */
@Service
public class AuthenticationRpcService implements IAuthenticationRpcService{
	@Autowired
	private TokenManager tokenManager;
	
	@Autowired
	private IRpcPermissionService rpcPermissionService;
	
	@Autowired
	private IUserService userService;
	
	@Override
	public List<RpcPermission> getAllPermissions(String token,String appCode) {
		if(StringUtils.isEmpty(token)){
			return rpcPermissionService.findListById(appCode, null);
		}else{
			//TODO
		}
		
		return null;
	}

	@Override
	public RpcUser findAuthInfo(String token) {
		LoginUser user = tokenManager.validate(token);
		if (user != null) {
			return new RpcUser(user.getUserName(), user.getProfile());
		}
		
		return null;
	}

	@Override
	public boolean validate(String token) {
		return tokenManager.validate(token) != null;
	}

	@Override
	public List<RpcPermission> findPermissionList(String token, String appCode) {
		if (StringUtils.isEmpty(token)) {
			return rpcPermissionService.findListById(appCode, null);
		}else {
			LoginUser user = tokenManager.validate(token);
			if (user != null) {
				return rpcPermissionService.findListById(appCode, user.getUserId());
			}else {
				return new ArrayList<RpcPermission>(0);
			}
		}
	}

	@Override
	public boolean updatePassword(String token, String newPassword) {
		LoginUser loginUser = tokenManager.validate(token);
		if (loginUser != null) {
			User user = userService.get(loginUser.getUserId());
			user.setPassword(PasswordProvider.encrypt(newPassword));
			userService.update(user);
			return true;
		}else {
			return false;
		}
	}
}
