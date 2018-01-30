package harry.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import harry.base.IBaseDao;
import harry.domain.RpcPermission;

/**
 * 
 * @author harry
 *
 */
public interface RpcPermissionDao extends IBaseDao<RpcPermission,Integer>{
	List<RpcPermission> findListById(@Param("appCode")String appCode, @Param("userId")Integer userId);
}
