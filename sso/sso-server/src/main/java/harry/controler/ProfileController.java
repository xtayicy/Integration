package harry.controler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import harry.annotations.ValidateParam;
import harry.base.BaseController;
import harry.domain.Result;
import harry.domain.ResultCode;
import harry.enums.Validator;
import harry.utils.ApplicationUtil;

/**
 * 
 * @author harry
 *
 */
@Controller
@RequestMapping("/admin/profile")
public class ProfileController extends BaseController{
	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model, HttpServletRequest request) {
		model.addAttribute("user", ApplicationUtil.getSessionUser(request).getProfile());
		
		return "/admin/profile";
	}
	
	@RequestMapping(value = "/savePassword", method = RequestMethod.POST)
	public @ResponseBody Result save(@ValidateParam({ Validator.NOT_BLANK }) String newPassword,@ValidateParam({ Validator.NOT_BLANK }) String confirmPassword,
			HttpServletRequest request) {
		if (newPassword.equals(confirmPassword) && authenticationRpcService.updatePassword(ApplicationUtil.getSessionUser(request).getToken(),
						newPassword))
			return Result.createSuccessResult().setMessage("修改成功"); 
		else
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("修改失败");
	}
}
