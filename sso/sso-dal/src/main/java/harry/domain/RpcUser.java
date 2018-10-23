package harry.domain;

import java.io.Serializable;

/**
 * 
 * @author harry
 *
 */
public class RpcUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6124913513990079479L;

	// 登录成功用户名
	private String userName;
	// 登录对象
	private Object profile;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Object getProfile() {
		return profile;
	}

	public void setProfile(Object profile) {
		this.profile = profile;
	}

	public RpcUser(String userName, Object profile) {
		super();
		this.userName = userName;
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "RpcUser [userName=" + userName + ", profile=" + profile + "]";
	}
}
