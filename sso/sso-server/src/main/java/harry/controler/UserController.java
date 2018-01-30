package harry.controler;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import harry.annotations.ValidateParam;
import harry.base.BaseController;
import harry.domain.Pagination;
import harry.domain.Result;
import harry.domain.User;
import harry.enums.Validator;
import harry.exceptions.ValidateException;
import harry.service.IAppService;
import harry.utils.ApplicationUtil;
import harry.utils.PasswordProvider;

/**
 * 
 * @author harry
 *
 */
@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseController{
	@Autowired
	private IAppService appService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model) {
		model.addAttribute("appList", appService.findByAll(null));
		
		return "/admin/user";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Result list(String account,Integer appId,@ValidateParam({ Validator.NOT_BLANK }) Integer pageNo,
			@ValidateParam({ Validator.NOT_BLANK }) Integer pageSize) {
		return Result.createSuccessResult().setData(userService.findPaginationByAccount(account, appId, new Pagination<User>(pageNo, pageSize)));
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Integer id, Model model) {
		User user;
		if (id == null) {
			user = new User();
		}
		else {
			user = userService.get(id);
		}
		model.addAttribute("user", user);
		model.addAttribute("appList", appService.findByAll(null));
		return "/admin/userEdit";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Result save(Integer id, @ValidateParam({ Validator.NOT_BLANK }) String account,
			String password,
			@ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable) {
		User user;
		if (id == null) {
			if (StringUtils.isEmpty(password)) {
				throw new ValidateException("密码不能为空");
			}
			user = new User();
			user.setCreateTime(new Date());
		}
		else {
			user = userService.get(id);
		}
		user.setAccount(account);
		if (!StringUtils.isEmpty(password)) {
			user.setPassword(PasswordProvider.encrypt(password));
		}
		user.setIsEnable(isEnable);
		userService.save(user);
		return Result.createSuccessResult();
	}
	
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public @ResponseBody Result enable( @ValidateParam({ Validator.NOT_BLANK }) String ids,@ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable) {
		userService.enable(isEnable, getAjaxIds(ids));
		return Result.createSuccessResult();
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Result delete(@ValidateParam({ Validator.NOT_BLANK }) String ids) {
		userService.deleteById(getAjaxIds(ids));
		return Result.createSuccessResult();
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public @ResponseBody Result resetPassword(@ValidateParam({ Validator.NOT_BLANK }) String ids) {
		userService.resetPassword(PasswordProvider.encrypt((String)ApplicationUtil.getProperty(ApplicationUtil.SYSTEM_INIT_PASSWORD)), getAjaxIds(ids));
		
		return Result.createSuccessResult();
	}
}
