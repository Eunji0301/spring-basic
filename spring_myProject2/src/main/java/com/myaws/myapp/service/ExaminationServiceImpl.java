package com.myaws.myapp.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myaws.myapp.domain.ExaminationVo;
import com.myaws.myapp.persistance.ExaminationMapper;

@Service
public class ExaminationServiceImpl implements ExaminationService {
	private ExaminationMapper em;
	
	@Autowired
	public ExaminationServiceImpl(SqlSession sqlSession) {
		this.em = sqlSession.getMapper(ExaminationMapper.class);
	}
	
	@Override
	public int examinationInsert(ExaminationVo ev) {
		int value = em.examinationInsert(ev);
		return value;
	}
	
	@Override
    public ExaminationVo getExaminationResult(Integer pidx) {
        // patientId를 기반으로 진료 결과를 조회
        ExaminationVo result = em.getExaminationResult(pidx);
        return result;
    }
}
