package com.meydan.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.meydan.api.MeydanInfoDTO;

@Component
public class MeydanModel {

	
	public Map<MeydanInfoDTO, List<MeydanInfoDTO>> calculation(List<MeydanInfoDTO> infoList) {
		
		Map<MeydanInfoDTO, List<MeydanInfoDTO>> mapper = new HashMap<MeydanInfoDTO, List<MeydanInfoDTO>>();
		List<MeydanInfoDTO> carOwners = new ArrayList<MeydanInfoDTO>();
		List<MeydanInfoDTO> passengers = new ArrayList<MeydanInfoDTO>();
		
		for (MeydanInfoDTO dto: infoList) {
			if (dto.isCarOwn()) {
				carOwners.add(dto);
			}
			else if(!dto.isCarOwn() && dto.isParticipate()) {
				passengers.add(dto);
			}
		}
		
		List<MeydanInfoDTO> passengersTemp = new ArrayList<MeydanInfoDTO>();
		passengersTemp.addAll(passengers);
		for (MeydanInfoDTO dto: carOwners) {
		if (passengers.size()!=0) {
			mapper.put(dto, new ArrayList<MeydanInfoDTO>());
		
		int size = 	Math.min(dto.getCapacity(), passengers.size());
		for (int i=0; i<size ;i++) {
			mapper.get(dto).add(passengers.get(0));
			passengers.remove(0);
		}
		}
		}
		
		
		return mapper;
	}
	
	
	
	
	
}
