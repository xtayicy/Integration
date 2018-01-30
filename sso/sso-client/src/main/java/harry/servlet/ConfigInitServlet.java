package harry.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import harry.utils.ApplicationUtil;

/**
 * 
 * @author harry
 *
 */
public class ConfigInitServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2056516075091924927L;
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigInitServlet.class);

	private static final String PATH = "_path";
	private static final String STATIC_PATH = "_staticPath";
	private static final String SYSTEM_NAME = "_systemName";
	private static final String SYSTEM_ADMIN_NAME = "_systemAdminName";
	private static final String SYSTEM_COMPANY_NAME = "_companyName";
	private static final String SYSTEM_COMPANY_PHONE = "_companyPhone";
	private static final String SYSTEM_COMPANY_RIGHT = "_copyRight";

	@Override
	public void init() throws ServletException {
		try {
			ServletContext servletContext = getServletContext();
			servletContext.setAttribute(PATH, servletContext.getContextPath());
			servletContext.setAttribute(STATIC_PATH, ApplicationUtil.getProperty(STATIC_PATH));
			servletContext.setAttribute(SYSTEM_NAME, ApplicationUtil.getProperty(SYSTEM_NAME));
			servletContext.setAttribute(SYSTEM_ADMIN_NAME, ApplicationUtil.getProperty(SYSTEM_ADMIN_NAME));
			servletContext.setAttribute(SYSTEM_COMPANY_NAME, ApplicationUtil.getProperty(SYSTEM_COMPANY_NAME));
			servletContext.setAttribute(SYSTEM_COMPANY_PHONE, ApplicationUtil.getProperty(SYSTEM_COMPANY_PHONE));
			servletContext.setAttribute(SYSTEM_COMPANY_RIGHT, ApplicationUtil.getProperty(SYSTEM_COMPANY_RIGHT));
			
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("init the config of system success!");
			}
		} catch (Exception e) {
			if(LOGGER.isWarnEnabled()){
				LOGGER.info("fail init the config of system!");
			}
		}
	}

}
