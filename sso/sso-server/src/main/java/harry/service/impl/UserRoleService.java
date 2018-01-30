package harry.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import harry.annotations.Permissible;
import harry.base.BaseServiceImpl;
import harry.base.IBaseDao;
import harry.dao.UserRoleDao;
import harry.domain.UserRole;
import harry.service.IUserRoleService;
/**
 * 
 * @author harry
 *
 */
@Service
public class UserRoleService extends BaseServiceImpl<UserRole, Integer> implements IUserRoleService{
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Override
	protected IBaseDao<UserRole, Integer> getBaseDao() {
		return userRoleDao;
	}
	
	public void deleteByUserIds(List<Integer> idList, Integer appId) {
		userRoleDao.deleteByUserIds(idList, appId);
	}
	
	public void deleteByAppIds(List<Integer> idList) {
		userRoleDao.deleteByAppIds(idList);
	}

	@Override
	public void deleteForChangeApp(Integer userId, List<Integer> idList) {
		userRoleDao.deleteForChangeApp(userId, idList);
	}

	@Override
	public UserRole findByUserRoleId(Integer userId, Integer roleId) {
		return userRoleDao.findByUserRoleId(userId, roleId);
	}

	@Permissible
	@Transactional
	@Override
	public void allocate(Integer userId, Integer appId, List<UserRole> list) {
		userRoleDao.deleteByUserIds(Arrays.asList(userId), appId);
		super.save(list);
	}
}
