package com.myaws.myapp.persistance;

import java.util.ArrayList;

import com.myaws.myapp.domain.AppointmentVo;

// MyBatis�� �޼���
public interface AppointmentMapper {
	public ArrayList<AppointmentVo> appointmentSelectAll();
}
