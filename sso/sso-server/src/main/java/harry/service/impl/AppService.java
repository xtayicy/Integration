package harry.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import harry.annotations.Permissible;
import harry.base.BaseServiceImpl;
import harry.base.IBaseDao;
import harry.dao.AppDao;
import harry.domain.App;
import harry.domain.Pagination;
import harry.service.IAppService;

/**
 * 
 * @author harry
 *
 */
@Service
public class AppService extends BaseServiceImpl<App,Integer> implements IAppService{
	@Autowired
	private AppDao appDao;
	
	@Override
	protected IBaseDao<App,Integer> getBaseDao() {
		return appDao;
	}

	public Set<String> findAppCodeByUserId(Boolean isEnable, Integer userId) {
		return appDao.findAppCodeByUserId(isEnable, userId);
	}

	@Override
	public List<App> findByAll(String name) {
		return appDao.findPaginationByName(name, null);
	}

	@Override
	public Pagination<App> findPaginationByName(String name, Pagination<App> p) {
		appDao.findPaginationByName(name, p);
		return p;
	}
	
	@Permissible
	public void enable(Boolean isEnable, List<Integer> idList) {
		verifyRows(appDao.enable(isEnable, idList), idList.size(), "应用数据库更新失败");
	}
	
	@Permissible
	public void save(App t) {
		super.save(t);
	}
	
	@Permissible
	@Transactional
	public void deleteById(List<Integer> idList) {
		rolePermissionService.deleteByAppIds(idList);
		userRoleService.deleteByAppIds(idList);
		userAppService.deleteByAppIds(idList);
		permissionService.deleteByAppIds(idList);
		roleService.deleteByAppIds(idList);
		verifyRows(appDao.deleteById(idList), idList.size(), "应用数据库删除失败");
	}

	@Override
	public List<App> findByUserId(Boolean isEnable, Integer userId) {
		return appDao.findByUserId(isEnable, userId);
	}
}
