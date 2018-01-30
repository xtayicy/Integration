package harry.service;

import java.util.List;

import harry.base.BaseService;
import harry.domain.RpcPermission;

/**
 * 
 * @author harry
 *
 */
public interface IRpcPermissionService extends BaseService<RpcPermission,Integer>{
	List<RpcPermission> findListById(String appCode, Integer userId);
}
