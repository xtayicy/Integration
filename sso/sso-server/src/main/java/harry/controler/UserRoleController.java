package harry.controler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import harry.annotations.ValidateParam;
import harry.base.BaseController;
import harry.domain.App;
import harry.domain.Result;
import harry.domain.Role;
import harry.domain.UserRole;
import harry.enums.TrueFalseEnum;
import harry.enums.Validator;

/**
 * 
 * @author harry
 *
 */
@Controller
@RequestMapping("/admin/userRole")
public class UserRoleController extends BaseController{
	@RequestMapping(value = "/allocate", method = RequestMethod.GET)
	public String edit(
			@ValidateParam({ Validator.NOT_BLANK }) Integer userId, Model model) {
		List<App> appList = appService.findByUserId(TrueFalseEnum.TRUE.getValue(), userId);
		model.addAttribute("userId", userId);
		model.addAttribute("appList", appList);
		model.addAttribute("roleList", getRoleList(userId, CollectionUtils.isEmpty(appList) ? null : appList.get(0).getId()));
		return "/admin/userRole";
	}
	
	@RequestMapping(value = "/allocateSave", method = RequestMethod.POST)
	public @ResponseBody Result allocateSave(@ValidateParam({ Validator.NOT_BLANK }) Integer appId,
			@ValidateParam({ Validator.NOT_BLANK }) Integer userId,
			String roleIds) {
		List<Integer> idList = getAjaxIds(roleIds);
		List<UserRole> list = new ArrayList<UserRole>();
		UserRole bean = null;
		for (Integer roleId : idList) {
			bean = new UserRole();
			bean.setAppId(appId);
			bean.setUserId(userId);
			bean.setRoleId(roleId);
			list.add(bean);
		}
		userRoleService.allocate(userId, appId, list);
		return Result.createSuccessResult().setMessage("授权成功");
	}
	
	@RequestMapping(value = "/change", method = RequestMethod.GET)
	public @ResponseBody Result changeApp( @ValidateParam({ Validator.NOT_BLANK }) Integer appId,
			@ValidateParam({ Validator.NOT_BLANK }) Integer userId) {
		return Result.createSuccessResult().setData(getRoleList(userId, appId));
	}
	
	private List<Role> getRoleList(Integer userId, Integer appId) {
		List<Role> list = roleService.findByAppId(TrueFalseEnum.TRUE.getValue(), appId);
		for (Role role : list) {
			UserRole userRole = userRoleService.findByUserRoleId(userId, role.getId());
			if (null != userRole) {
				role.setIsChecked(true);
			}
			else {
				role.setIsChecked(false);
			}
		}
		
		return list;
	}
}
