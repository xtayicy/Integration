package harry.service;

import java.util.List;
import java.util.Set;

import harry.base.BaseService;
import harry.domain.App;
import harry.domain.Pagination;

/**
 * 
 * @author harry
 *
 */
public interface IAppService extends BaseService<App,Integer>{
	public void enable(Boolean isEnable, List<Integer> idList);
	
	public Set<String> findAppCodeByUserId(Boolean isEnable, Integer userId);
	
	public List<App> findByAll(String name);
	
	public Pagination<App> findPaginationByName(String name, Pagination<App> p);
	
	public List<App> findByUserId(Boolean isEnable, Integer userId);
}
