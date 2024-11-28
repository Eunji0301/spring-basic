package com.myaws.myapp.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myaws.myapp.domain.DoctorVo;
import com.myaws.myapp.persistance.DoctorMapper;

@Service
public class DoctorServiceImpl implements DoctorService{
	private DoctorMapper dm;
	
	@Autowired
	public DoctorServiceImpl(SqlSession sqlSession) {
		this.dm = sqlSession.getMapper(DoctorMapper.class);
	}
	

	@Override
	public int doctorInsert(DoctorVo dv) {
		int value = dm.doctorInsert(dv);
		return value;
	}


	@Override
	public int doctorIdCheck(String doctorId) {
		int value = dm.doctorIdCheck(doctorId);
		return value;
	}


	@Override
	public DoctorVo doctorLoginCheck(String doctorId) {
		DoctorVo dv = dm.doctorLoginCheck(doctorId);
//		System.out.println("doctorLoginCheck dv : " + dv);
		return dv;
	}
}
