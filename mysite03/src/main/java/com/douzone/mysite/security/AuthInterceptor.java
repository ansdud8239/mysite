package com.douzone.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.function.support.HandlerFunctionAdapter;

import com.douzone.mysite.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {

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
		Auth authType = handler.getClass().getAnnotation(Auth.class);
		System.out.println(authType);
		// 5. Type이나 Method에 @Auth가 없는경우
		if (auth == null || authType == null) {
			return true;
		}

		// 6. @Auth가 붙어있기 때문에 인증(Authenfication) 여부 확인
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		// 7. 권한체크(Authorization) 체크를 위해 @Auth의 rold가져오기("ADMIN","USER")
		String role = auth.role();
		String authUserRole = authUser.getRole();
		System.out.println(role+":"+authUserRole);
		if(!role.equals(authUserRole)) {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		// 6. 인증확인
		return true;
	}
}
