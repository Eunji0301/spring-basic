package com.myaws.myapp.service;

import java.util.ArrayList;

import com.myaws.myapp.domain.AppointmentVo;

// 스프링에서 사용할 메서드 선언하는 곳
public interface AppointmentService {
	public ArrayList<AppointmentVo> appointmentSelectAll();
	
	public int appointmentInsert(AppointmentVo av);
}
