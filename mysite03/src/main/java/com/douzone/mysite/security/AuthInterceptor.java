package com.douzone.mysite.security;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.mysite.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {

	@Autowired
	private SiteService siteService;

	@Autowired
	ServletContext servletConext;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 1. handler 종류 확인
		if (!(handler instanceof HandlerMethod)) {
			// DefaultServletHandler가 처리하는 경우(정적 자원, /assets/**)
			return true;
		}
		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		// 3. Handler Method의 @Auth 가져오기
		// auth기 null이면 @Auth가 안붙어있음
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

		// 4. Handler Method에 @Auth가 없으면 Type(class)에 붙어있는지 확인
		auth = (auth == null) ? handlerMethod.getBeanType().getAnnotation(Auth.class) : auth;
		// auth=(auth == null )?
		// handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class):auth;

		// 5. Type이나 Method에 @Auth가 없는경우
		if (auth == null) {
			return true;
		}

		// 6. @Auth가 붙어있기 때문에 인증(Authenfication) 여부 확인
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		// 7. 권한체크(Authorization) 체크를 위해 @Auth의 role가져오기("ADMIN","USER")
		String role = auth.role();
		String authUserRole = authUser.getRole();

		// 8. @Auth의 role이 "USER"인 경우 , authUser의 role은 상관없다.
		if ("USER".equals(role)) {
			return true;
		}

		// 9. @Auth의 role이 "ADMIN"인 경우, authUser의 role은 반드시 "ADMIN"이어야 한다
		if (!"ADMIN".equals(authUserRole)) {
			response.sendRedirect(request.getContextPath());
			return false;
		}

		// 10. 옯은 관리자 권한
		// @Auth의 role :"ADMIN"
		// authUser의 role : "ADMIN"
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		SiteVo vo = siteService.getSite();
		servletConext.setAttribute("site", vo);
	}

}
