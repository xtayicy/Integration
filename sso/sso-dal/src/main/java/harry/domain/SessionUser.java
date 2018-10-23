package harry.domain;

import java.io.Serializable;

/**
 * 
 * @author harry
 *
 */
public class SessionUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1998480832198827782L;

	// 登录用户访问Token
	private String token;
	// 登录名
	private String account;
	// 登录对象
	private Object profile;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Object getProfile() {
		return profile;
	}

	public void setProfile(Object profile) {
		this.profile = profile;
	}

	public SessionUser(String token, String userName, Object profile) {
		this.token = token;
		this.account = userName;
		this.profile = profile;
	}

	public SessionUser() {
		super();
	}

	@Override
	public String toString() {
		return "SessionUser [token=" + token + ", account=" + account
				+ ", profile=" + profile + "]";
	}
}
