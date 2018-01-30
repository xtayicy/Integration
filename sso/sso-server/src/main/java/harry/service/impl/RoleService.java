package harry.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import harry.base.BaseServiceImpl;
import harry.base.IBaseDao;
import harry.dao.RoleDao;
import harry.domain.Pagination;
import harry.domain.Role;
import harry.service.IRoleService;

/**
 * 
 * @author harry
 *
 */
@Service
public class RoleService extends BaseServiceImpl<Role, Integer> implements IRoleService{
	@Autowired
	private RoleDao roleDao;
	
	@Override
	protected IBaseDao<Role, Integer> getBaseDao() {
		return roleDao;
	}
	
	public void deleteByAppIds(List<Integer> idList) {
		roleDao.deleteByAppIds(idList);
	}

	@Override
	public Pagination<Role> findPaginationByName(String name, Integer appId, Pagination<Role> p) {
		roleDao.findPaginationByName(name, null, appId, p);
		return p;
	}

	@Override
	public List<Role> findByAppId(Boolean isEnable, Integer appId) {
		if (appId == null)
			return new ArrayList<Role>(0);
		return roleDao.findPaginationByName(null, isEnable, appId, null);
	}

	@Override
	public void enable(Boolean isEnable, List<Integer> idList) {
		verifyRows(roleDao.enable(isEnable, idList), idList.size(), "角色数据库更新失败");
	}
}
