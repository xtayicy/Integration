package harry.service;

import java.util.List;

import harry.base.BaseService;
import harry.domain.UserRole;

/**
 * 
 * @author harry
 *
 */
public interface IUserRoleService extends BaseService<UserRole, Integer>{
	public void deleteByUserIds(List<Integer> idList, Integer appId);
	
	public void deleteByAppIds(List<Integer> idList);
	
	public void deleteForChangeApp(Integer userId, List<Integer> idList);
	
	public UserRole findByUserRoleId(Integer userId, Integer roleId);
	
	public void allocate(Integer userId, Integer appId, List<UserRole> list);
}
