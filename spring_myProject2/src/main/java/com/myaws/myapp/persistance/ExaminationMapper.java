package com.myaws.myapp.persistance;

import com.myaws.myapp.domain.ExaminationVo;

// MyBatis용 메서드
public interface ExaminationMapper {
	public int examinationInsert(ExaminationVo ev);
}
