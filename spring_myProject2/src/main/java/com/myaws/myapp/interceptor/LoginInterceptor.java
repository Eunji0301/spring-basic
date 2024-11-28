package com.myaws.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	// 로그인 후 회원정보를 세션에 담는다.

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 가로채기하기 전 처리하는 메서드
		HttpSession session = request.getSession();

		// 환자 세션 정보가 있으면 제거
		if (session.getAttribute("pidx") != null) {
			session.removeAttribute("pidx");
			session.removeAttribute("patientId");
			session.removeAttribute("patientName");
			// 세션 무효화
	        session.invalidate();

		}
		
		// 의사 세션 정보가 있으면 제거
		if (session.getAttribute("didx") != null) {
			session.removeAttribute("didx");
			session.removeAttribute("doctorId");
			session.removeAttribute("doctorName");
			// 세션 무효화
			session.invalidate();

		}

		return true;
	}

	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		// RedirectAttributes나  Model  객체에 담은 것을 꺼낸다.
		String pidx = (String) modelAndView.getModel().get("pidx");
		String patientName = (String) modelAndView.getModel().get("patientName");
		String patientId = (String) modelAndView.getModel().get("patientId");
		
		String didx = (String) modelAndView.getModel().get("didx");
		String doctorName = (String) modelAndView.getModel().get("doctorName");
		String doctorId = (String) modelAndView.getModel().get("doctorId");
        
	    modelAndView.getModel().clear(); /// 파라미터 모델값을 지운다.
		
	    // 세션에 환자 정보 설정
		HttpSession session = request.getSession();		
		if(pidx != null) {
			session.setAttribute("pidx", pidx);
			session.setAttribute("patientId", patientId);
			session.setAttribute("patientName", patientName);
			// request.getSession();
		}
		
		if(didx != null) {
			session.setAttribute("didx", didx);
			session.setAttribute("doctorId", doctorId);
			session.setAttribute("doctorName", doctorName);
			// request.getSession();
		
		}

	}
}
