package harry.IDao;

import harry.base.IBaseDao;
import harry.domain.User;

/**
 * 
 * @author harry
 *
 */
public interface IUserDao extends IBaseDao<User>{
	public int deleteByPrimaryKey(String username);
	
	public User selectByPrimaryKey(String username);
}
