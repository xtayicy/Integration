package harry.domain;

/**
 * 
 * @author harry
 *
 */
public class UserApp extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7418751297680940153L;

	/** 应用ID */
	private Integer appId;
	/** 管理员ID */
	private Integer userId;

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

	@Override
	public String toString() {
		return "UserApp [appId=" + appId + ", userId=" + userId + "]";
	}
}
