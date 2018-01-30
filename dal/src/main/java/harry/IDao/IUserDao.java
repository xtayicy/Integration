package harry.IDao;

import harry.base.IBaseDao;
import harry.domain.User;

/**
 * 
 * @author harry
 */
public interface IUserDao extends IBaseDao<User>{
	int deleteByPrimaryKey(String username);
	
	User selectByPrimaryKey(String username);
}
