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

import com.qifei.model.DimIndustry;
import com.qifei.service.DimIndustryService;

@Controller
public class DimIndustryController {

	@Autowired
	DimIndustryService dimIndustryService;

	@RequestMapping(value = "/getAllDimIndustrys", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String getAllDimIndustrys() throws JsonGenerationException,
			JsonMappingException, IOException {
		List<DimIndustry> list = dimIndustryService.getAllDimIndustrys();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(list);
		System.out.println(jsonString);
		return jsonString;
	}

}
