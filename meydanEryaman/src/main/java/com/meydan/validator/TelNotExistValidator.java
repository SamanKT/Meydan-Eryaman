package com.meydan.validator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.meydan.annotations.TelNotExist;
import com.meydan.api.MeydanInfoDTO;
import com.meydan.model.MeydanJDBC;

public class TelNotExistValidator implements ConstraintValidator<TelNotExist, Long> {

	@Autowired
	private MeydanJDBC jdbc;
	
	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		try {
			jdbc.connect();
			List<MeydanInfoDTO> list = jdbc.allInfoFromDB();
			List<Long> telList = new ArrayList<>();
			list.forEach(s-> telList.add(s.getTel()));
			if (telList.contains(value)) {
				return true;
			}
			jdbc.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}
