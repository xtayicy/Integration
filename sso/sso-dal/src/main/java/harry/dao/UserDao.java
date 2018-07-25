package harry.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import harry.base.IBaseDao;
import harry.domain.Pagination;
import harry.domain.User;

/**
 * 
 * @author harry
 *
 */
public interface UserDao extends IBaseDao<User,Integer>{
	public int enable(@Param("isEnable") Boolean isEnable, @Param("idList") List<Integer> idList);
	
	public User findByAccount(@Param("account")String account);
	
	public int resetPassword(@Param("password") String password, @Param("idList") List<Integer> idList);
	
	public List<User> findPaginationByAccount(@Param("account") String account, @Param("appId") Integer appId, Pagination<User> p);
}
