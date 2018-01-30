package harry.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import harry.base.BaseController;
import harry.domain.User;

/**
 * @author harry
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	@RequestMapping("/test")
	public ModelAndView test(@RequestParam String name,HttpServletRequest request){
		User user = userService.selectByPrimaryKey(name);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user");
		
		return modelAndView.addObject("user", user);
	}
	
	@RequestMapping("/selectAll")
	public void selectAll(HttpServletRequest request){
		userService.selectAll();
	}
	
	@RequestMapping("/testTransaction")
	public void testTransaction(){
		userService.testTransaction();
	}
}
