package com.myaws.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 가상 경로에 해당 메서드 접근 전에 가로채기
		HttpSession session = request.getSession();

		// 세션에서 환자나 의사의 정보 가져오기
		Object userRole = session.getAttribute("userRole"); // userRole은 세션에 저장된 역할 정보
		
		// 환자 정보
		if("pidx".equals(userRole)) {
			// 환자는 examinationWrite 페이지에 접근 불가
			if(request.getRequestURI().contains("/examination/examinationWrite")) {
				request.setAttribute("error", "환자는 진료 결과 작성 페이지에 접근할 수 없습니다.");
				request.getRequestDispatcher("/errorPage").forward(request, response);
				return false;
			}
		}
		
		// 의사 정보
		if("didx".equals(userRole)) {
			// 의사는 makeAppointment 페이지에 접근 불가
			if(request.getRequestURI().contains("/appointment/makeAppointment")) {
				request.setAttribute("error", "의사는 진료 예약 페이지에 접근할 수 없습니다.");
				request.getRequestDispatcher("/errorPage").forward(request, response);
				return false;
			}
		}
		// 권한이 있을 경우
		return true;
//		if (session.getAttribute("pidx") == null) {
//			// 로그인이 안되어있으면 이동하려고 하는 주소 보관
//			// 로그인 페이지로 보낸다.
//			saveUrl(request); // 이동할 경로를 저장한다.
//			response.sendRedirect(request.getContextPath() + "/patient/patientLogin.aws");
//			
//			return false;
//		} else {
//			return true;
//		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void saveUrl(HttpServletRequest request) {
		String uri = request.getRequestURI(); // 전체 경로 주소
		String param = request.getQueryString(); // 파라미터를 가져온다.

		if (param == null || param.equals("null") || param.equals("")) {
			param = "";
		} else {
			param = "?" + param;
		}

		String locationUrl = uri + param;

		HttpSession session = request.getSession();
		if (request.getMethod().equals("GET")) { // 대문자 GET
			session.setAttribute("saveUrl", locationUrl);
		}
	}
}
