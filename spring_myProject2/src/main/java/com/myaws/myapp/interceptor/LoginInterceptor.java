package com.myaws.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	// �α��� �� ȸ�������� ���ǿ� ��´�.

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// ����ä���ϱ� �� ó���ϴ� �޼���
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
		// RedirectAttributes��  Model  ��ü�� ���� ���� ������.
		String pidx = modelAndView.getModel().get("pidx").toString();
		String patientName = modelAndView.getModel().get("patientName").toString();
		
		String patientId = modelAndView.getModel().get("patientId").toString();
		
		modelAndView.getModel().clear(); /// �Ķ���� �𵨰��� �����.
		
		HttpSession session = request.getSession();		
		if(pidx != null) {
			session.setAttribute("pidx", pidx);
			session.setAttribute("patientId", patientId);
			session.setAttribute("patientName", patientName);
			request.getSession();
		}
	}

}
