package harry.service;

import java.util.List;

import harry.base.BaseService;
import harry.domain.RolePermission;

/**
 * 
 * @author harry
 *
 */
public interface IRolePermissionService extends BaseService<RolePermission, Integer>{
	public void deleteByAppIds(List<Integer> idList);
	
	public void deleteByPermissionIds(List<Integer> idList);
	
	public List<RolePermission> findByRoleId(Integer roleId);
	
	public void allocate(Integer roleId, List<RolePermission> list);
}
