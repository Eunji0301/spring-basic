package com.myaws.myapp.persistance;

import com.myaws.myapp.domain.DoctorVo;

public interface DoctorMapper {
	public int doctorInsert(DoctorVo dv);
	
	public int doctorIdCheck(String doctorId);
	
	public DoctorVo doctorLoginCheck(String doctorId);
}
