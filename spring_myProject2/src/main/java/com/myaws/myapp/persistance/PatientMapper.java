package com.myaws.myapp.persistance;

import java.util.ArrayList;

import com.myaws.myapp.domain.PatientVo;

//MyBatis�� �޼���
public interface PatientMapper {
	public int patientInsert(PatientVo mv);
	
	public int patientIdCheck(String patientId);
	
	public PatientVo patientLoginCheck(String patientId);
	
	// examinationWrite�Ҷ� ȭ�鿡 ȯ�� ���� ��� ����
	public PatientVo getPatientInfo(String patientId);
}
