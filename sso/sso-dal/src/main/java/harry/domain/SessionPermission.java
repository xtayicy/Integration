package harry.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author harry
 *
 */
public class SessionPermission implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1859402560121517904L;
	// 登录用户菜单
	private List<RpcPermission> menuList;
	// 登录用户权限
	private Set<String> permissionSet;
	// 登录用户没有的权限
	private String noPermissions;

	public List<RpcPermission> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<RpcPermission> menuList) {
		this.menuList = menuList;
	}

	public Set<String> getPermissionSet() {
		return permissionSet;
	}

	public void setPermissionSet(Set<String> permissionSet) {
		this.permissionSet = permissionSet;
	}

	public String getNoPermissions() {
		return noPermissions;
	}

	public void setNoPermissions(String noPermissions) {
		this.noPermissions = noPermissions;
	}

	@Override
	public String toString() {
		return "SessionPermission [menuList=" + menuList + ", permissionSet="
				+ permissionSet + ", noPermissions=" + noPermissions + "]";
	}
}
