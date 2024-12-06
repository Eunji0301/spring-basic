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
		// 가상 경로에 해당 메서드 접근 전에 가로채기
		HttpSession session = request.getSession();

		// 세션에서 환자나 의사의 정보 가져오기
		Object pidx = session.getAttribute("pidx");
		Object didx = session.getAttribute("didx");
		
		System.out.println("pidx : " + pidx);
		System.out.println("didx : " + didx);
		String requestURI = request.getRequestURI();
		
		
		// 환자가 진료 결과 작성 페이지에 접근할 경우
		if(pidx != null && requestURI.contains("/examination/examinationWrite")) {
			alertAndRedirect(response, "환자는 진료 결과 작성 페이지에 접근할 수 없습니다.", request.getContextPath() + "/");
			return false;
		}
		
		// 의사가 진료 예약 페이지에 접근할 경우
		if(didx != null && requestURI.contains("/appointment/makeAppointment")) {
			alertAndRedirect(response, "의사는 진료 예약 페이지에 접근할 수 없습니다.", request.getContextPath() + "/");
			return false;
		}
				
		// 권한이 있을 경우
		return true;
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
	
	// 알람 메시지와 함께 리다이렉트하는 메서드
    private void alertAndRedirect(HttpServletResponse response, String message, String redirectUrl) throws Exception {
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write( // 출력 스트림 가져와 클라이언트로 데이터 보내기
            "<script>" +
            "alert('" + message + "');" +
            "location.href='" + redirectUrl + "';" + // 지정된 URL로 브라우저 리다이렉트
            "</script>"
        );
        response.getWriter().flush(); // 출력 스트림에 남아있는 데이터 비워 클라이언트로 전송
    }
}
