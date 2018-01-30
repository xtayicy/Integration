package harry.service;

import java.util.List;

import harry.domain.RpcPermission;
import harry.domain.RpcUser;

/**
 * 
 * @author harry
 *
 */
public interface IAuthenticationRpcService {
	
	public List<RpcPermission> getAllPermissions(String token,String appCode);

	public RpcUser findAuthInfo(String token);

	public boolean validate(String token);

	public List<RpcPermission> findPermissionList(String token, String appCode);
	
	public boolean updatePassword(String token, String newPassword);
}
