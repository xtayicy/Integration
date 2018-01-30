package harry.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import harry.base.IBaseDao;
import harry.domain.Permission;

/**
 * 
 * @author harry
 *
 */
public interface PermissionDao extends IBaseDao<Permission, Integer>{
	public int deleteByAppIds(@Param("idList") List<Integer> idList);
	
	public List<Permission> findByName(@Param("name") String name, @Param("appId") Integer appId, @Param("isEnable") Boolean isEnable);
}
