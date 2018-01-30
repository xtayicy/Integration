package harry.service;

import java.util.List;

import harry.base.BaseService;
import harry.domain.Permission;

/**
 * 
 * @author harry
 *
 */
public interface IPermissionService extends BaseService<Permission, Integer>{
	public void deleteByAppIds(List<Integer> idList);
	
	public List<Permission> findByName(String name, Integer appId, Boolean isEnable);
	
	public void deletePermission(Integer id, Integer appId);
}
