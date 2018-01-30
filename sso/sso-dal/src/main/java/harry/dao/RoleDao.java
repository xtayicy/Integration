package harry.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import harry.base.IBaseDao;
import harry.domain.Pagination;
import harry.domain.Role;

/**
 * 
 * @author harry
 *
 */
public interface RoleDao extends IBaseDao<Role, Integer>{
	public int deleteByAppIds(@Param("idList") List<Integer> idList);
	
	public List<Role> findPaginationByName(@Param("name") String name, @Param("isEnable") Boolean isEnable,
			@Param("appId") Integer appId, Pagination<Role> p);
	
	public int enable(@Param("isEnable") Boolean isEnable, @Param("idList") List<Integer> idList);
}
