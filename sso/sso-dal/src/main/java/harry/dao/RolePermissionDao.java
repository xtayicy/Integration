package harry.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import harry.base.IBaseDao;
import harry.domain.RolePermission;

/**
 * 
 * @author harry
 *
 */
public interface RolePermissionDao extends IBaseDao<RolePermission, Integer>{
	public int deleteByAppIds(@Param("idList") List<Integer> idList);
	
	public List<RolePermission> findByRoleId(@Param("roleId") Integer roleId);
	
	public int deleteByRoleIds(@Param("idList") List<Integer> idList);
	
	public int deleteByPermissionIds(@Param("idList") List<Integer> idList);
}
