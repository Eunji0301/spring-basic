package com.myaws.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	// 로그인 후 회원정보를 세션에 담는다.

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 가로채기하기 전 처리하는 메서드
		HttpSession session = request.getSession();

		if (session.getAttribute("pidx") != null) {
			session.removeAttribute("pidx");
			session.removeAttribute("patientId");
			session.removeAttribute("patientName");

			session.invalidate();
		}

		return true;
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		// RedirectAttributes나  Model  객체에 담은 것을 꺼낸다.
		String pidx = modelAndView.getModel().get("pidx").toString();
		String patientName = modelAndView.getModel().get("patientName").toString();
		
		String patientId = modelAndView.getModel().get("patientId").toString();
		
		modelAndView.getModel().clear(); /// 파라미터 모델값을 지운다.
		
		HttpSession session = request.getSession();		
		if(pidx != null) {
			session.setAttribute("pidx", pidx);
			session.setAttribute("patientId", patientId);
			session.setAttribute("patientName", patientName);
			request.getSession();
		}
	}

}
