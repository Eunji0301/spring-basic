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
		// ���� ��ο� �ش� �޼��� ���� ���� ����ä��
		HttpSession session = request.getSession();

		// ���ǿ��� ȯ�ڳ� �ǻ��� ���� ��������
		Object userRole = session.getAttribute("userRole"); // userRole�� ���ǿ� ����� ���� ����
		
		// ȯ�� ����
		if("pidx".equals(userRole)) {
			// ȯ�ڴ� examinationWrite �������� ���� �Ұ�
			if(request.getRequestURI().contains("/examination/examinationWrite")) {
				request.setAttribute("error", "ȯ�ڴ� ���� ��� �ۼ� �������� ������ �� �����ϴ�.");
				request.getRequestDispatcher("/errorPage").forward(request, response);
				return false;
			}
		}
		
		// �ǻ� ����
		if("didx".equals(userRole)) {
			// �ǻ�� makeAppointment �������� ���� �Ұ�
			if(request.getRequestURI().contains("/appointment/makeAppointment")) {
				request.setAttribute("error", "�ǻ�� ���� ���� �������� ������ �� �����ϴ�.");
				request.getRequestDispatcher("/errorPage").forward(request, response);
				return false;
			}
		}
		// ������ ���� ���
		return true;
//		if (session.getAttribute("pidx") == null) {
//			// �α����� �ȵǾ������� �̵��Ϸ��� �ϴ� �ּ� ����
//			// �α��� �������� ������.
//			saveUrl(request); // �̵��� ��θ� �����Ѵ�.
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
		String uri = request.getRequestURI(); // ��ü ��� �ּ�
		String param = request.getQueryString(); // �Ķ���͸� �����´�.

		if (param == null || param.equals("null") || param.equals("")) {
			param = "";
		} else {
			param = "?" + param;
		}

		String locationUrl = uri + param;

		HttpSession session = request.getSession();
		if (request.getMethod().equals("GET")) { // �빮�� GET
			session.setAttribute("saveUrl", locationUrl);
		}
	}
}
