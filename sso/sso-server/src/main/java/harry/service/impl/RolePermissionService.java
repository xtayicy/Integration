package harry.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import harry.base.BaseServiceImpl;
import harry.base.IBaseDao;
import harry.dao.RolePermissionDao;
import harry.domain.RolePermission;
import harry.service.IRolePermissionService;

/**
 * 
 * @author harry
 *
 */
@Service
public class RolePermissionService extends BaseServiceImpl<RolePermission, Integer> implements IRolePermissionService{
	@Autowired
	private RolePermissionDao rolePermissionDao;
	
	@Override
	protected IBaseDao<RolePermission, Integer> getBaseDao() {
		return rolePermissionDao;
	}
	
	public void deleteByAppIds(List<Integer> idList) {
		rolePermissionDao.deleteByAppIds(idList);
	}

	@Override
	public List<RolePermission> findByRoleId(Integer roleId) {
		return rolePermissionDao.findByRoleId(roleId);
	}

	@Override
	public void allocate(Integer roleId, List<RolePermission> list) {
		rolePermissionDao.deleteByRoleIds(Arrays.asList(roleId));
		super.save(list);
	}

	@Override
	public void deleteByPermissionIds(List<Integer> idList) {
		rolePermissionDao.deleteByPermissionIds(idList);
	}
}
