package harry.service;

import java.util.List;

import harry.base.BaseService;
import harry.domain.UserApp;

public interface IUserAppService extends BaseService<UserApp, Integer>{
	public void deleteByUserIds(List<Integer> idList);
	
	public void deleteByAppIds(List<Integer> idList);
	
	public UserApp findByUserAppId(Integer userId, Integer roleId);
	
	public void allocate(Integer userId, List<Integer> idList, List<UserApp> list);
}
