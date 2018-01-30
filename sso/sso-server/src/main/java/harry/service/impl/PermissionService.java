package harry.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import harry.annotations.Permissible;
import harry.base.BaseServiceImpl;
import harry.base.IBaseDao;
import harry.dao.PermissionDao;
import harry.domain.Permission;
import harry.service.IPermissionService;

/**
 * 
 * @author harry
 *
 */
@Service
public class PermissionService extends BaseServiceImpl<Permission, Integer> implements IPermissionService{
	@Autowired
	private PermissionDao permissionDao;
	
	@Override
	protected IBaseDao<Permission, Integer> getBaseDao() {
		return permissionDao;
	}
	
	public void deleteByAppIds(List<Integer> idList) {
		permissionDao.deleteByAppIds(idList);
	}

	@Override
	public List<Permission> findByName(String name, Integer appId, Boolean isEnable) {
		return permissionDao.findByName(name, appId, isEnable);
	}

	@Permissible
	@Transactional
	@Override
	public void deletePermission(Integer id, Integer appId) {
		List<Integer> idList = new ArrayList<Integer>();
		
		List<Permission> list = permissionService.findByName(null, appId, null);
		loopSubList(id, idList, list);
		idList.add(id);
		
		rolePermissionService.deleteByPermissionIds(idList);
		
		verifyRows(permissionDao.deleteById(idList), idList.size(), "权限数据库删除失败");
	}
}	
