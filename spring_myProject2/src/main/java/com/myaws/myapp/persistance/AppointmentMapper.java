package com.myaws.myapp.persistance;

import java.util.ArrayList;

import com.myaws.myapp.domain.AppointmentVo;

// MyBatis용 메서드
public interface AppointmentMapper {
	public ArrayList<AppointmentVo> appointmentSelectAll();
}
