package harry.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import harry.base.IBaseDao;
import harry.domain.UserRole;

/**
 * 
 * @author harry
 *
 */
public interface UserRoleDao extends IBaseDao<UserRole, Integer>{
	public int deleteByUserIds(@Param("idList") List<Integer> idList, @Param("appId") Integer appId);
	
	public int deleteByAppIds(@Param("idList") List<Integer> idList);
	
	public int deleteForChangeApp(@Param("userId") Integer userId, @Param("idList") List<Integer> idList);
	
	public UserRole findByUserRoleId(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
}
