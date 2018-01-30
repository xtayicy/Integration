package harry.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import harry.base.IBaseDao;
import harry.domain.App;
import harry.domain.Pagination;

/**
 * 
 * @author harry
 *
 */
public interface AppDao extends IBaseDao<App,Integer>{
	public int enable(@Param("isEnable") Boolean isEnable, @Param("idList") List<Integer> idList);
	
	public List<App> findPaginationByName(@Param("name") String name, Pagination<App> p);
	
	public Set<String> findAppCodeByUserId(@Param("isEnable") Boolean isEnable, @Param("userId") Integer userId);
	
	public List<App> findByUserId(@Param("isEnable") Boolean isEnable, @Param("userId") Integer userId);
}
