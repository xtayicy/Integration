package harry.domain;

/**
 * 
 * @author harry
 *
 */
public class UserRole extends PersistentObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2453987988807762139L;
	
	/** 应用ID */
	private Integer appId;
	/** 管理员ID */
	private Integer userId;
	/** 角色ID */
	private Integer roleId;
	
	public Integer getAppId() {
		return this.appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "UserRole [appId=" + appId + ", userId=" + userId + ", roleId="
				+ roleId + "]";
	}
}
