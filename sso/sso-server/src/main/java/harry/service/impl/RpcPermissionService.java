package harry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import harry.base.BaseServiceImpl;
import harry.base.IBaseDao;
import harry.dao.RpcPermissionDao;
import harry.domain.RpcPermission;
import harry.service.IRpcPermissionService;

/**
 * 
 * @author harry
 *
 */
@Service
public class RpcPermissionService extends BaseServiceImpl<RpcPermission,Integer> implements IRpcPermissionService{
	@Autowired
	private RpcPermissionDao rpcPermissionDao;
	
	@Override
	protected IBaseDao<RpcPermission,Integer> getBaseDao() {
		return rpcPermissionDao;
	}
	
	@Override
	public List<RpcPermission> findListById(String appCode, Integer userId) {
		return rpcPermissionDao.findListById(appCode,userId);
	}
}
