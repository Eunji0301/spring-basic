package com.myaws.myapp.service;

import com.myaws.myapp.domain.ExaminationVo;

// 스프링에서 사용할 메서드 선언하는 곳
public interface ExaminationService {
	public int examinationInsert(ExaminationVo ev);
	
	public ExaminationVo getExaminationResult(Integer pidx);
}
