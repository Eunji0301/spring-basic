package com.myaws.myapp.persistance;

import java.util.ArrayList;

import com.myaws.myapp.domain.PatientVo;

//MyBatis용 메서드
public interface PatientMapper {
	public int patientInsert(PatientVo mv);
	
	public int patientIdCheck(String patientId);
	
	public PatientVo patientLoginCheck(String patientId);
	
	public ArrayList<PatientVo> patientSelectAll();
}
