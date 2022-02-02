package com.meydan.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.meydan.api.MeydanInfoDTO;
import com.meydan.model.MeydanJDBC;

public class MeydanValidator implements Validator {
	
	

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return MeydanInfoDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"tel", "info.tel","telefon bos birakilamaz");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name", "info.tel","ad bos birakilamaz");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"lastName", "info.tel","soyad bos birakilamaz");
		
		MeydanInfoDTO dto = (MeydanInfoDTO)target;
		if (dto.getTel()!=null) {
		if (dto.getTel().toString().startsWith("0")) {
			errors.rejectValue("tel", "tel.invalid", "telefon numarasi 5 ile baslamali");
		}
		if (dto.getTel().toString().chars().toArray().length!= 10) {
			errors.rejectValue("tel","tel.noInvalid","telefon numarasi 10 rakamdan olusmali");
		}
		}
		
		try {
		MeydanJDBC jdbc = new MeydanJDBC();
		jdbc.connect();
		List<MeydanInfoDTO> db = jdbc.allInfoFromDB();
		List<Long> tels = new ArrayList<>();
		for (MeydanInfoDTO temp: db) {
			tels.add(temp.getTel());
		}
		if (tels.contains(dto.getTel())) {
			errors.rejectValue("tel", "tel.duplicated", "Bu numarayla daha onceden giris yapilmistir");
		}
		jdbc.close();
		}
		catch (Exception e) {
			
		}
		
	}

}
