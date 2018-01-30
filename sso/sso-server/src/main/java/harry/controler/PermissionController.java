package harry.controler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import harry.annotations.ValidateParam;
import harry.base.BaseController;
import harry.domain.Permission;
import harry.domain.Result;
import harry.enums.Validator;

/**
 * 
 * @author harry
 *
 */
@Controller
@RequestMapping("/admin/permission")
public class PermissionController extends BaseController{
	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model,HttpServletRequest request) {
		String appCode = tokenManager.getAppCode(getToken(request));
		System.out.println(appCode);
		
		model.addAttribute("appList", appService.findByAll(null));
		
		return "/admin/permission";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Result save(
			Integer id,
			@ValidateParam({ Validator.NOT_BLANK }) Integer appId,
			Integer parentId,
			String icon,
			 @ValidateParam({ Validator.NOT_BLANK }) String name,
			 @ValidateParam({ Validator.NOT_BLANK }) String url,
			 @ValidateParam({ Validator.NOT_BLANK }) Integer sort,
			 @ValidateParam({ Validator.NOT_BLANK }) Boolean isMenu,
			 @ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable) {
		Permission permission;
		if (id == null) {
			permission = new Permission();
		}
		else {
			permission = permissionService.get(id);
		}
		permission.setAppId(appId);
		permission.setParentId(parentId);
		permission.setIcon(icon);
		permission.setName(name);
		permission.setUrl(url);
		permission.setSort(sort);
		permission.setIsMenu(isMenu);
		permission.setIsEnable(isEnable);
		permissionService.save(permission);
		return Result.createSuccessResult().setMessage("保存成功");
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Result delete(
			 @ValidateParam({ Validator.NOT_BLANK }) Integer id,
			@ValidateParam({ Validator.NOT_BLANK }) Integer appId) {
		permissionService.deletePermission(id, appId);
		return Result.createSuccessResult().setMessage("删除成功");
	}
	
	@RequestMapping(value = "/nodes", method = RequestMethod.GET)
	public @ResponseBody List<Permission> nodes(Integer appId,
			String name,
			Boolean isEnable) {
		List<Permission> list = permissionService.findByName(name, appId, isEnable);
		Permission permission = new Permission();
		permission.setId(null);
		permission.setParentId(-1);
		permission.setName("根节点");
		permission.setAppId(appId);
		list.add(0, permission);
		return list;
	}
}
