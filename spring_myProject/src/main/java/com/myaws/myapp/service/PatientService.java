package com.myaws.myapp.service;

import java.util.ArrayList;

import com.myaws.myapp.domain.PatientVo;

//스프링에서 사용할 메서드 선언하는 곳
public interface PatientService {
public int patientInsert(PatientVo mv);
	
	public int patientIdCheck(String patientId);
	
	public PatientVo patientLoginCheck(String patientId);
	
	public ArrayList<PatientVo> patientSelectAll();
}
