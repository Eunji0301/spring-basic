package com.myaws.myapp.service;

import java.util.ArrayList;

import com.myaws.myapp.domain.PatientVo;

//���������� ����� �޼��� �����ϴ� ��
public interface PatientService {
	public int patientInsert(PatientVo mv);
	
	public int patientIdCheck(String patientId);
	
	public PatientVo patientLoginCheck(String patientId);
	
	// examinationWrite�Ҷ� ȭ�鿡 ȯ�� ���� ��� ����
	public PatientVo getPatientInfo(String patientId);
}
