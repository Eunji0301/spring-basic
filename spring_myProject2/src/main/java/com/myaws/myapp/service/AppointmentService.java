package com.myaws.myapp.service;

import java.util.ArrayList;

import com.myaws.myapp.domain.AppointmentVo;

// ���������� ����� �޼��� �����ϴ� ��
public interface AppointmentService {
	public ArrayList<AppointmentVo> appointmentSelectAll();
	
	public int appointmentInsert(AppointmentVo av);
}
