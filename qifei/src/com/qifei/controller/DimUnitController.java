package com.qifei.controller;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qifei.model.DimUnit;
import com.qifei.service.DimUnitService;

@Controller
public class DimUnitController {

	@Autowired
	DimUnitService dimUnitService;

	@RequestMapping(value = "/getAllDimUnits", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String getAllDimUnits() throws JsonGenerationException,
			JsonMappingException, IOException {
		List<DimUnit> list = dimUnitService.getAllDimUnits();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(list);
		System.out.println(jsonString);
		return jsonString;
	}

}
