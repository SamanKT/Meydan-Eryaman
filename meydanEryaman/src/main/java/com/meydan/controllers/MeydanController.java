package com.meydan.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.meydan.api.MeydanInfoDTO;
import com.meydan.api.ResultDTO;
import com.meydan.editor.MeydanEditor;
import com.meydan.editor.MeydanEditorForTel;
import com.meydan.model.MeydanJDBC;
import com.meydan.model.MeydanModel;
import com.meydan.validator.MeydanValidator;

@Controller
@SessionAttributes("meydanInfo")
public class MeydanController {
	
	@Autowired
	MeydanModel meydanModel;
	
	@Autowired
	MeydanJDBC meydanJDBC;
	
	

	@RequestMapping("/")
	public String home(Model model) {
		int[] someList = IntStream.range(1, 142).toArray();
		model.addAttribute("items", someList);
		model.addAttribute("meydanInfo", new MeydanInfoDTO());
		return "home";
	}

	@RequestMapping("/confirm")
	public String confirm(@Valid @ModelAttribute("meydanInfo") MeydanInfoDTO info, BindingResult result) throws SQLException, ClassNotFoundException {

		if (result.hasErrors()) {
			return "home";
		}

		MeydanJDBC meydanJDBC = new MeydanJDBC();
		meydanJDBC.connect();
		meydanJDBC.addToDb(info);
		meydanJDBC.close();
		
		return "confirm";
	}
	
	
	

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.addValidators(new MeydanValidator());
		
		binder.registerCustomEditor(Integer.class, "carOwn", new MeydanEditor() );
		//binder.registerCustomEditor(String.class, "tel", new MeydanEditorForTel() );
		
		StringTrimmerEditor stringEditor = new StringTrimmerEditor(true);
		
		binder.registerCustomEditor(String.class, stringEditor);
		
	}

}
