package com.meydan.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.meydan.api.MeydanInfoDTO;
import com.meydan.api.ResultDTO;
import com.meydan.model.MeydanJDBC;
import com.meydan.model.MeydanModel;

@Controller
public class ResultController {

	@Autowired
	MeydanJDBC meydanJDBC;

	@Autowired
	MeydanModel meydanModel;

	Map<MeydanInfoDTO, List<MeydanInfoDTO>> calculationMap;
	String[] passengerPlaceHolder = { "first", "second", "third", "fourth" };
	private Statement statement;
	private List<MeydanInfoDTO> allInfoFromDB;

	@RequestMapping("/result")
	public String calculate(@ModelAttribute("resultInfo") ResultDTO result)
			throws ClassNotFoundException, SQLException {

		meydanJDBC.connect();
		allInfoFromDB = meydanJDBC.allInfoFromDB();
		calculationMap = meydanModel.calculation(allInfoFromDB);
		meydanJDBC.assign(calculationMap);
		meydanJDBC.close();
		return "result";
	}

	@RequestMapping("/resultShow")
	public String showResult(@Valid @ModelAttribute("resultInfo") ResultDTO result,BindingResult bindingResult, Model model)
			throws SQLException, ClassNotFoundException {
		
		if (bindingResult.hasErrors()) {
			return "result";
		}
		
		statement = meydanJDBC.connect();
		List<MeydanInfoDTO> allInfoFromDB = meydanJDBC.allInfoFromDB();

		for (MeydanInfoDTO dto : allInfoFromDB) {
			if (result.getTelResult().equals(dto.getTel())) {
				result.setNameResult(dto.getName());
				if (dto.isCarOwn()) {
					List<MeydanInfoDTO> temp = new ArrayList<>();

					for (int i = 0; i < dto.getCapacity(); i++) {

						String query = "SELECT " + passengerPlaceHolder[i] + " FROM assign WHERE cartel='"
								+ dto.getTel() + "';";
						ResultSet resultSet = statement.executeQuery(query);
						while (resultSet.next()) {
							for (MeydanInfoDTO info : allInfoFromDB) {
								if (info.getTel().equals(resultSet.getLong(1))) {
									temp.add(info);
								}
							}

						}

					}

					model.addAttribute("list", temp);
				} else {
					for (MeydanInfoDTO driver : calculationMap.keySet()) {
						if (calculationMap.get(driver).contains(dto)) {
							model.addAttribute("driver", driver);
						}
					}
				}

			}

		}
		meydanJDBC.close();
		return "show";

	}
	
	
	
}
