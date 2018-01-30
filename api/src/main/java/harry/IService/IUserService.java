package harry.IService;

import harry.base.IBaseService;
import harry.domain.User;

/**
 * 
 * @author harry
 */
public interface IUserService extends IBaseService<User>{
   void deleteByPrimaryKey(String username) throws RuntimeException;
   
   void testTransaction() throws RuntimeException;
   
   User selectByPrimaryKey(String username);
}