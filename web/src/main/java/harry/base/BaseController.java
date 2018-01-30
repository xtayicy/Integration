package harry.base;

import org.springframework.beans.factory.annotation.Autowired;

import harry.IService.IUserService;

public abstract class BaseController {
	@Autowired
	protected IUserService userService;
}
