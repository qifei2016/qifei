package com.qifei.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	@RequestMapping(value = "/saveDimIndustry", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String saveDimIndustry(HttpServletRequest request,
			HttpServletResponse response) throws JsonGenerationException,
			JsonMappingException, IOException {
		String dimIndustryName = request.getParameter("dimIndustryName");
		DimIndustry dimIndustry = new DimIndustry();
		dimIndustry.setIndustryId(dimIndustryService.getMaxDimIndustryId());
		dimIndustry.setIndustryName(dimIndustryName);
		dimIndustry.setIsValId(1);
		dimIndustryService.saveDimIndustry(Arrays.asList(dimIndustry));
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(dimIndustry);
		System.out.println(jsonString);
		return jsonString;
	}

}
