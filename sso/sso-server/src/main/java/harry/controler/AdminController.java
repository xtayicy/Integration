package harry.controler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import harry.base.BaseController;
import harry.domain.Result;
import harry.domain.SessionPermission;
import harry.utils.ApplicationUtil;

/**
 * 
 * @author harry
 *
 */
@Controller
@RequestMapping("/admin/admin")
public class AdminController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String execute(HttpServletRequest request, Model model) {
		SessionPermission sessionPermission = ApplicationUtil.getSessionPermission(request);
		// 设置当前登录用户没有的权限
		model.addAttribute("sessionUserNoPermissions", sessionPermission == null ? null : sessionPermission.getNoPermissions());
		// 默认首页
		// model.addAttribute("defaultPage", null);
		return "/admin";
	}
	
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public @ResponseBody Result menu(HttpServletRequest request) {
		SessionPermission sessionPermission = ApplicationUtil.getSessionPermission(request);
		// 如果配置的权限拦截器，则获取登录用户权限下的菜单，没有权限拦截限制的情况下，获取当前系统菜单呈现
		return Result.createSuccessResult().setData(
				sessionPermission == null ? ApplicationUtil.getApplicationMenu(request) : sessionPermission.getMenuList());
	}
}
