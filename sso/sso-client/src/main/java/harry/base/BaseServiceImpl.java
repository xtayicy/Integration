package harry.base;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import harry.domain.Permission;
import harry.domain.PersistentObject;
import harry.exceptions.DaoException;
import harry.service.IAppService;
import harry.service.IPermissionService;
import harry.service.IRolePermissionService;
import harry.service.IRoleService;
import harry.service.IUserAppService;
import harry.service.IUserRoleService;

/**
 * 
 * @author harry
 *
 */
@Transactional
public abstract class BaseServiceImpl<T extends PersistentObject, ID> implements BaseService<T, ID> {
	protected abstract IBaseDao<T, ID> getBaseDao();

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseServiceImpl.class);

	@Autowired
	protected IRolePermissionService rolePermissionService;

	@Autowired
	protected IUserRoleService userRoleService;

	@Autowired
	protected IUserAppService userAppService;

	@Autowired
	protected IPermissionService permissionService;

	@Autowired
	protected IRoleService roleService;

	@Autowired
	protected IAppService appService;

	public T get(ID pk) {
		return getBaseDao().get(pk);
	}

	public void update(T t) {
		getBaseDao().update(t);
	}

	public void save(T t) {
		if (t.getId() == null) {
			getBaseDao().insert(t);
		} else {
			getBaseDao().update(t);
		}
	}

	// 递归方法，删除子权限
	protected void loopSubList(Integer id, List<Integer> idList, List<Permission> list) {
		for (Permission p : list) {
			if (id.equals(p.getParentId())) {
				idList.add(p.getId());
				loopSubList(p.getId(), idList, list);
			}
		}
	}

	public void save(Collection<T> ts) {
		for (T t : ts) {
			save(t);
		}
	}

	public void deleteById(List<ID> idList) {
		verifyRows(getBaseDao().deleteById(idList), idList.size(), "数据库删除失败");
	}

	protected void verifyRows(int updateRows, int rows, String message) {
		if (updateRows != rows) {
			DaoException e = new DaoException(message);
			LOGGER.error("need update is {}, but real update rows is {}.", rows, updateRows, e);
			throw e;
		}
	}
}
