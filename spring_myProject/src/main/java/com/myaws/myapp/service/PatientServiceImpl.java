package com.myaws.myapp.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myaws.myapp.domain.PatientVo;
import com.myaws.myapp.persistance.PatientMapper;

@Service
public class PatientServiceImpl implements PatientService{
private PatientMapper pm;
	
	@Autowired
	public PatientServiceImpl(SqlSession sqlSession) {
		this.pm = sqlSession.getMapper(PatientMapper.class);
	}
	

	@Override
	public int patientInsert(PatientVo pv) {
		int value = pm.patientInsert(pv);
		return value;
	}


	@Override
	public int patientIdCheck(String patientId) {
		int value = pm.patientIdCheck(patientId);
		return value;
	}


	@Override
	public PatientVo patientLoginCheck(String patientId) {
		PatientVo pv = pm.patientLoginCheck(patientId);
//		System.out.println("patientLoginCheck pv : " + pv);
		return pv;
	}

	@Override
	public ArrayList<PatientVo> patientSelectAll() {
		ArrayList<PatientVo> alist = pm.patientSelectAll();
		return alist;
	}
}
