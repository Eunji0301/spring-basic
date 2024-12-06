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
		System.out.println("Interceptor triggered for URI : " + request.getRequestURI());
		// ���� ��ο� �ش� �޼��� ���� ���� ����ä��
		HttpSession session = request.getSession();

		// ���ǿ��� ȯ�ڳ� �ǻ��� ���� ��������
		Object pidx = session.getAttribute("pidx");
		Object didx = session.getAttribute("didx");
		
		System.out.println("pidx : " + pidx);
		System.out.println("didx : " + didx);
		String requestURI = request.getRequestURI();
		
		
		// ȯ�ڰ� ���� ��� �ۼ� �������� ������ ���
		if(pidx != null && requestURI.contains("/examination/examinationWrite")) {
			alertAndRedirect(response, "ȯ�ڴ� ���� ��� �ۼ� �������� ������ �� �����ϴ�.", request.getContextPath() + "/");
			return false;
		}
		
		// �ǻ簡 ���� ���� �������� ������ ���
		if(didx != null && requestURI.contains("/appointment/makeAppointment")) {
			alertAndRedirect(response, "�ǻ�� ���� ���� �������� ������ �� �����ϴ�.", request.getContextPath() + "/");
			return false;
		}
				
		// ������ ���� ���
		return true;
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
	
	// �˶� �޽����� �Բ� �����̷�Ʈ�ϴ� �޼���
    private void alertAndRedirect(HttpServletResponse response, String message, String redirectUrl) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write( // ��� ��Ʈ�� ������ Ŭ���̾�Ʈ�� ������ ������
            "<script>" +
            "alert('" + message + "');" +
            "location.href='" + redirectUrl + "';" + // ������ URL�� ������ �����̷�Ʈ
            "</script>"
        );
        response.getWriter().flush(); // ��� ��Ʈ���� �����ִ� ������ ��� Ŭ���̾�Ʈ�� ����
    }
}
