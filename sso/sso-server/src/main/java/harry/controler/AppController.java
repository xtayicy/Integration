package harry.controler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import harry.annotations.ValidateParam;
import harry.base.BaseController;
import harry.domain.App;
import harry.domain.Pagination;
import harry.domain.Result;
import harry.enums.Validator;

/**
 * 
 * @author harry
 *
 */
@Controller
@RequestMapping("/admin/app")
public class AppController extends BaseController {
	@RequestMapping(method = RequestMethod.GET)
	public String execute() {
		return "/admin/app";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Result list(String name, @ValidateParam({ Validator.NOT_BLANK }) Integer pageNo,
			@ValidateParam({ Validator.NOT_BLANK }) Integer pageSize) {
		return Result.createSuccessResult()
				.setData(appService.findPaginationByName(name, new Pagination<App>(pageNo, pageSize)));
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Integer id, Model model) {
		App app;
		if (id == null) {
			app = new App();
		} else {
			app = appService.get(id);
		}
		model.addAttribute("app", app);
		return "/admin/appEdit";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Result save(Integer id, @ValidateParam({ Validator.NOT_BLANK }) String name,
			@ValidateParam({ Validator.NOT_BLANK }) String code,
			@ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable,
			@ValidateParam({ Validator.NOT_BLANK, Validator.INT }) Integer sort) {
		App app;
		if (id == null) {
			app = new App();
			app.setCreateTime(new Date());
		} else {
			app = appService.get(id);
		}
		app.setName(name);
		app.setSort(sort);
		app.setIsEnable(isEnable);
		app.setCode(code);
		appService.save(app);
		return Result.createSuccessResult();
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Result delete(@ValidateParam({ Validator.NOT_BLANK }) String ids) {
		appService.deleteById(getAjaxIds(ids));
		return Result.createSuccessResult();
	}
	
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public @ResponseBody Result enable(@ValidateParam({ Validator.NOT_BLANK }) String ids, @ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable) {
		appService.enable(isEnable, getAjaxIds(ids));
		return Result.createSuccessResult();
	}
}
