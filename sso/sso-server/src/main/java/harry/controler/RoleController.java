package harry.controler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import harry.annotations.ValidateParam;
import harry.base.BaseController;
import harry.domain.Pagination;
import harry.domain.Result;
import harry.domain.Role;
import harry.domain.RolePermission;
import harry.enums.Validator;

/**
 * 
 * @author harry
 *
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController extends BaseController{
	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model) {
		model.addAttribute("appList", appService.findByAll(null));
		
		return "/admin/role";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Integer id, Model model) {
		Role role;
		if (id == null) {
			role = new Role();
		}
		else {
			role = roleService.get(id);
		}
		model.addAttribute("role", role);
		model.addAttribute("appList", appService.findByAll(null));
		return "/admin/roleEdit";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Result save(Integer id, @ValidateParam({ Validator.NOT_BLANK }) Integer appId,
			 @ValidateParam({ Validator.NOT_BLANK }) String name,
			 @ValidateParam({ Validator.NOT_BLANK }) Integer sort,
			 String description,
			 @ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable) {
		Role role;
		if (id == null) {
			role = new Role();
		}
		else {
			role = roleService.get(id);
		}
		role.setAppId(appId);
		role.setName(name);
		role.setSort(sort);
		role.setDescription(description);
		role.setIsEnable(isEnable);
		roleService.save(role);
		return Result.createSuccessResult();
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Result delete(@ValidateParam({ Validator.NOT_BLANK }) String ids) {
		roleService.deleteById(getAjaxIds(ids));
		return Result.createSuccessResult();
	}
	
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public @ResponseBody Result enable(@ValidateParam({ Validator.NOT_BLANK }) String ids,
			@ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable) {
		roleService.enable(isEnable, getAjaxIds(ids));
		return Result.createSuccessResult();
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Result list(String name,Integer appId,
			@ValidateParam({ Validator.NOT_BLANK }) Integer pageNo,
			@ValidateParam({ Validator.NOT_BLANK }) Integer pageSize) {
		return Result.createSuccessResult().setData(roleService.findPaginationByName(name, appId, new Pagination<Role>(pageNo, pageSize)));
	}
	
	@RequestMapping(value = "/allocate", method = RequestMethod.GET)
	public @ResponseBody Result allocate(@ValidateParam({ Validator.NOT_BLANK }) Integer roleId) {
		return Result.createSuccessResult().setData(rolePermissionService.findByRoleId(roleId));
	}
	
	@RequestMapping(value = "/allocateSave", method = RequestMethod.POST)
	public @ResponseBody Result allocateSave( @ValidateParam({ Validator.NOT_BLANK }) Integer appId,
			@ValidateParam({ Validator.NOT_BLANK }) Integer roleId,
			@ValidateParam({ Validator.NOT_BLANK }) String permissionIds) {
		List<Integer> idList = getAjaxIds(permissionIds);
		List<RolePermission> list = new ArrayList<RolePermission>();
		Integer permissionId;
		for (Iterator<Integer> i$ = idList.iterator(); i$.hasNext(); list.add(new RolePermission(appId, roleId, permissionId))) {
			permissionId = i$.next();
		}
		rolePermissionService.allocate(roleId, list);
		return Result.createSuccessResult().setMessage("授权成功");
	}
}
