package harry.service;

import harry.IDao.IUserDao;
import harry.IService.IUserService;
import harry.base.BaseService;
import harry.base.IBaseDao;
import harry.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 
 * @author harry
 *
 */
@Service
public class UserService extends BaseService<User> implements IUserService{
	@Autowired
	private IUserDao userDao;
	
	@Override
	protected IBaseDao<User> getBaseDao() {
		return userDao;
	}

	@Override
	public void deleteByPrimaryKey(String username) throws RuntimeException {
		userDao.deleteByPrimaryKey(username);
	}

	@Override
	public void testTransaction() throws RuntimeException {
		User user = selectByPrimaryKey("harry");
		System.out.println(user);
		user.setPassword("1");
		update(user);
		System.out.println(1 / 0);
		user = new User();
		user.setUsername("marry");
		user.setPassword("marry");
		insert(user);
	}

	@Override
	@Cacheable(value="baseCache")
	public User selectByPrimaryKey(String username) {
		
		return userDao.selectByPrimaryKey(username);
	}
}
