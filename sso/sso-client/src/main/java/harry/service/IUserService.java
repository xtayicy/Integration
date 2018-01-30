package harry.service;

import java.util.List;

import harry.base.BaseService;
import harry.domain.Pagination;
import harry.domain.Result;
import harry.domain.User;

/**
 * 
 * @author harry
 *
 */
public interface IUserService extends BaseService<User,Integer>{
	public void enable(Boolean isEnable, List<Integer> idList);
	
	Result login(String ipAddr, String appCode, String account, String encrypt);
	
	public Pagination<User> findPaginationByAccount(String account, Integer appId, Pagination<User> p);
	
	public void resetPassword(String password, List<Integer> idList);
}
