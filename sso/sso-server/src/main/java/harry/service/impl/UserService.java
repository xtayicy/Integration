package harry.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import harry.annotations.Permissible;
import harry.base.BaseServiceImpl;
import harry.base.IBaseDao;
import harry.dao.UserDao;
import harry.domain.Pagination;
import harry.domain.Result;
import harry.domain.ResultCode;
import harry.domain.User;
import harry.enums.TrueFalseEnum;
import harry.service.IUserService;

/**
 * 
 * @author harry
 *
 */
@Service
public class UserService extends BaseServiceImpl<User,Integer> implements IUserService{
	@Autowired
	private UserDao userDao;
	
	@Override
	protected IBaseDao<User,Integer> getBaseDao() {
		return userDao;
	}

	@Override
	public Result login(String ipAddr, String appCode, String account, String password) {
		Result result = Result.createSuccessResult();
		User user = userDao.findByAccount(account);
		if(user == null){
			result.setCode(ResultCode.ERROR).setMessage("no account exist!");
		}else if(!user.getPassword().equals(password)){
			result.setCode(ResultCode.ERROR).setMessage("error password!");
		}else if(TrueFalseEnum.FALSE.getValue().equals(user.getIsEnable())){
			result.setCode(ResultCode.ERROR).setMessage("account was disebled by admin");
		}else{
			Set<String> set = appService.findAppCodeByUserId(TrueFalseEnum.TRUE.getValue(), user.getId());
			if(CollectionUtils.isEmpty(set)){
				result.setCode(ResultCode.ERROR).setMessage("no operational app");
			}else{
				user.setLastLoginIp(ipAddr);
				user.setLoginCount(user.getLoginCount() + 1);
				user.setLastLoginTime(new Date());
				userDao.update(user);
				result.setData(user);
			}
		}
		
		return result;
	}

	@Override
	public Pagination<User> findPaginationByAccount(String account, Integer appId, Pagination<User> p) {
		userDao.findPaginationByAccount(account, appId, p);
		return p;
	}

	@Override
	public void enable(Boolean isEnable, List<Integer> idList) {
		verifyRows(userDao.enable(isEnable, idList), idList.size(), "管理员数据库更新失败");
	}
	
	@Permissible
	@Transactional
	public void deleteById(List<Integer> idList) {
		userAppService.deleteByUserIds(idList);
		userRoleService.deleteByUserIds(idList, null);
		verifyRows(userDao.deleteById(idList), idList.size(), "管理员数据库删除失败");
	}

	@Override
	public void resetPassword(String password, List<Integer> idList) {
		verifyRows(userDao.resetPassword(password, idList), idList.size(), "管理员密码数据库重置失败");
	}
}
