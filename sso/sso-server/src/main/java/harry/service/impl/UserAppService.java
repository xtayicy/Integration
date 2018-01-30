package harry.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import harry.annotations.Permissible;
import harry.base.BaseServiceImpl;
import harry.base.IBaseDao;
import harry.dao.UserAppDao;
import harry.domain.UserApp;
import harry.service.IUserAppService;
/**
 * 
 * @author harry
 *
 */
@Service
public class UserAppService extends BaseServiceImpl<UserApp, Integer> implements IUserAppService{
	@Autowired
	private UserAppDao userAppDao;

	@Override
	protected IBaseDao<UserApp, Integer> getBaseDao() {
		return userAppDao;
	}

	@Override
	public void deleteByUserIds(List<Integer> idList) {
		userAppDao.deleteByUserIds(idList);
	}
	
	public void deleteByAppIds(List<Integer> idList) {
		userAppDao.deleteByAppIds(idList);
	}

	@Override
	public UserApp findByUserAppId(Integer userId, Integer roleId) {
		return userAppDao.findByUserAppId(userId, roleId);
	}

	@Permissible
	@Transactional
	@Override
	public void allocate(Integer userId, List<Integer> idList, List<UserApp> list) {
		userRoleService.deleteForChangeApp(userId, idList);
		userAppDao.deleteByUserIds(Arrays.asList(userId));
		
		super.save(list);
	}
}
