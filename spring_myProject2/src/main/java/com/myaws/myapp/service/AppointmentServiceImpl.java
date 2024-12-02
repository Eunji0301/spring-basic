package com.myaws.myapp.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myaws.myapp.domain.AppointmentVo;
import com.myaws.myapp.persistance.AppointmentMapper;

@Service
public class AppointmentServiceImpl implements AppointmentService{
	private AppointmentMapper am;
	
	@Autowired
	public AppointmentServiceImpl(SqlSession sqlSession) {
		this.am = sqlSession.getMapper(AppointmentMapper.class);
	}
	
	@Override
	public ArrayList<AppointmentVo> appointmentSelectAll() {
		ArrayList<AppointmentVo> blist = am.appointmentSelectAll();
		return blist;
	}

}
