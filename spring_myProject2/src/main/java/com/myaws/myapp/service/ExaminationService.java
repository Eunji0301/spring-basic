package com.myaws.myapp.service;

import com.myaws.myapp.domain.ExaminationVo;

// ���������� ����� �޼��� �����ϴ� ��
public interface ExaminationService {
	public int examinationInsert(ExaminationVo ev);
	
	public ExaminationVo getExaminationResult(Integer pidx);
}
