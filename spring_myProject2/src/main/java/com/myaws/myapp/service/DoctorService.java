package com.myaws.myapp.service;

import com.myaws.myapp.domain.DoctorVo;

public interface DoctorService {
	public int doctorInsert(DoctorVo dv);
	
	public int doctorIdCheck(String doctorId);
	
	public DoctorVo doctorLoginCheck(String doctorId);
}
