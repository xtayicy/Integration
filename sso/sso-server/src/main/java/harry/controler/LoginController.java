package harry.controler;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import harry.base.BaseController;
import harry.domain.LoginUser;
import harry.domain.Result;
import harry.domain.User;
import harry.enums.TrueFalseEnum;
import harry.utils.ApplicationUtil;
import harry.utils.CookieUtil;
import harry.utils.PasswordProvider;

/**
 * 
 * @author harry
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	// 登录页
	private static final String LOGIN_PATH = "/login";
	
	private static final String CHARSET = "UTF-8";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String login(String backUrl, String appCode, HttpServletRequest request) {
		String token = CookieUtil.getCookie(request, "token");
		if (StringUtils.isEmpty(token)) {
			request.setAttribute("backUrl", backUrl);
			//request.setAttribute("appCode", appCode);

			return LOGIN_PATH;
		} else {
			LoginUser loginUser = tokenManager.validate(token);
			if(loginUser != null){
				
				return "redirect:" + authBackUrl(backUrl, token);
			}else{
				request.setAttribute("backUrl", backUrl);
				//request.setAttribute("appCode", appCode);
				
				return LOGIN_PATH;
			}
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public String login(String backUrl, String appCode, String account, String password, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Result result = userService.login(ApplicationUtil.getIpAddr(request),appCode,account, PasswordProvider.encrypt(password));
		if(!result.isSuccess()){
			request.setAttribute("errorMessage", result.getMessage());
			request.setAttribute("backUrl", backUrl);
			//request.setAttribute("appCode", appCode);
			
			return LOGIN_PATH;
		}else{
			User user = (User) result.getData();
			LoginUser loginUser = new LoginUser(user.getId(), user.getAccount(), user);
			loginUser.setAppCode(new ArrayList<>(appService.findAppCodeByUserId(TrueFalseEnum.TRUE.getValue(), user.getId())).get(0));
			String token = tokenManager.existsLoginUser(loginUser);
			if(StringUtils.isEmpty(token)){
				token = createToken(loginUser);
			}
			
			addTokenInCookie(token, response);
			
			backUrl = URLDecoder.decode(backUrl, CHARSET);
			
			return "redirect:" + authBackUrl(backUrl, token);
		}
	}
	
	private String createToken(LoginUser loginUser) {
		// 生成token
		String token = UUID.randomUUID().toString().replaceAll("-", "");

		// 缓存中添加token对应User
		tokenManager.addToken(token, loginUser);
		return token;
	}
	
	private void addTokenInCookie(String token, HttpServletResponse response) {
		// Cookie添加token
		Cookie cookie = new Cookie("token", token);
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
	}
	
	private String authBackUrl(String backUrl, String token) {
		StringBuilder sbf = new StringBuilder(backUrl);
		if (backUrl.indexOf("?") > 0) {
			sbf.append("&");
		}
		else {
			sbf.append("?");
		}
		sbf.append(ApplicationUtil.SSO_TOKEN_NAME).append("=").append(token);
		return sbf.toString();
	}
}
