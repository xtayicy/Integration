package harry.controler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import harry.annotations.ValidateParam;
import harry.base.BaseController;
import harry.domain.App;
import harry.domain.Result;
import harry.domain.UserApp;
import harry.enums.Validator;

/**
 * 
 * @author harry
 *
 */
@Controller
@RequestMapping("/admin/userApp")
public class UserAppController extends BaseController {
	@RequestMapping(value = "/allocate", method = RequestMethod.GET)
	public String edit(@ValidateParam({ Validator.NOT_BLANK }) Integer userId, Model model) {
		model.addAttribute("userId", userId);
		model.addAttribute("appList", getAppList(userId));

		return "/admin/userApp";
	}

	@RequestMapping(value = "/allocateSave", method = RequestMethod.POST)
	public @ResponseBody Result allocateSave(@ValidateParam({ Validator.NOT_BLANK }) Integer userId, String appIds) {
		List<Integer> idList = getAjaxIds(appIds);
		List<UserApp> list = new ArrayList<UserApp>();
		UserApp bean = null;
		for (Integer appId : idList) {
			bean = new UserApp();
			bean.setAppId(appId);
			bean.setUserId(userId);
			list.add(bean);
		}
		
		userAppService.allocate(userId, idList, list);
		
		return Result.createSuccessResult().setMessage("授权成功");
	}

	private List<App> getAppList(Integer userId) {
		List<App> list = appService.findByAll(null);
		for (App app : list) {
			UserApp userApp = userAppService.findByUserAppId(userId, app.getId());
			if (null != userApp) {
				app.setIsChecked(true);
			} else {
				app.setIsChecked(false);
			}
		}
		
		return list;
	}
}
