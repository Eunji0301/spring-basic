package com.myaws.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	// �α��� �� ȸ�������� ���ǿ� ��´�.

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// ����ä���ϱ� �� ó���ϴ� �޼���
		HttpSession session = request.getSession();

		// ȯ�� ���� ������ ������ ����
		if (session.getAttribute("pidx") != null) {
			session.removeAttribute("pidx");
			session.removeAttribute("patientId");
			session.removeAttribute("patientName");
			// ���� ��ȿȭ
	        session.invalidate();

		}
		
		// �ǻ� ���� ������ ������ ����
		if (session.getAttribute("didx") != null) {
			session.removeAttribute("didx");
			session.removeAttribute("doctorId");
			session.removeAttribute("doctorName");
			// ���� ��ȿȭ
			session.invalidate();

		}

		return true;
	}

	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		// RedirectAttributes��  Model  ��ü�� ���� ���� ������.
		String pidx = (String) modelAndView.getModel().get("pidx");
		String patientName = (String) modelAndView.getModel().get("patientName");
		String patientId = (String) modelAndView.getModel().get("patientId");
		
		String didx = (String) modelAndView.getModel().get("didx");
		String doctorName = (String) modelAndView.getModel().get("doctorName");
		String doctorId = (String) modelAndView.getModel().get("doctorId");
        
	    modelAndView.getModel().clear(); /// �Ķ���� �𵨰��� �����.
		
	    // ���ǿ� ȯ�� ���� ����
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
