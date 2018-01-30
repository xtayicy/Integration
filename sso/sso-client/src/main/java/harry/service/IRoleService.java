package harry.service;

import java.util.List;

import harry.base.BaseService;
import harry.domain.Pagination;
import harry.domain.Role;

/**
 * 
 * @author harry
 *
 */
public interface IRoleService extends BaseService<Role, Integer>{
	public void deleteByAppIds(List<Integer> idList);
	
	public Pagination<Role> findPaginationByName(String name, Integer appId, Pagination<Role> p);
	
	public List<Role> findByAppId(Boolean isEnable, Integer appId);
	
	public void enable(Boolean isEnable, List<Integer> idList);
}
