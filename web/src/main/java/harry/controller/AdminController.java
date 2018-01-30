package harry.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import harry.base.BaseController;

/**
 * 
 * @author harry
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController{
	
	@RequestMapping
	public String index(HttpServletRequest request,Model model){
		//SessionPermission sessionPermission = SSOUtil.getSessionPermission(request);
		
		//model.addAttribute(SSOUtil.SESSION_USER_NO_PERMISSION, sessionPermission == null ? sessionPermission : sessionPermission.getNoPermission());
		
		return "/admin/admin";
	}
}
