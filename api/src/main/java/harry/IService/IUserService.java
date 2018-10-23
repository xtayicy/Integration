package harry.IService;

import harry.base.IBaseService;
import harry.domain.User;

/**
 * 
 * @author harry
 */
public interface IUserService extends IBaseService<User>{
   public void deleteByPrimaryKey(String username) throws RuntimeException;
   
   public void testTransaction() throws RuntimeException;
   
   public User selectByPrimaryKey(String username);
}