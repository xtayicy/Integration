package harry.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import harry.base.IBaseDao;
import harry.domain.UserApp;

/**
 * 
 * @author harry
 *
 */
public interface UserAppDao extends IBaseDao<UserApp, Integer>{
	public int deleteByUserIds(@Param("idList") List<Integer> idList);
	
	public int deleteByAppIds(@Param("idList") List<Integer> idList);
	
	public UserApp findByUserAppId(@Param("userId") Integer userId, @Param("appId") Integer appId);
}
