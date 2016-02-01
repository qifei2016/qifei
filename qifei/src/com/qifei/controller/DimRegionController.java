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

import com.qifei.model.DimRegion;
import com.qifei.service.DimRegionService;

@Controller
public class DimRegionController {

	@Autowired
	DimRegionService dimRegionService;

	@RequestMapping(value = "/getAllDimRegions", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String getAllDimRegions() throws JsonGenerationException,
			JsonMappingException, IOException {
		List<DimRegion> list = dimRegionService.getAllDimRegions();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(list);
		System.out.println(jsonString);
		return jsonString;
	}

	@RequestMapping(value = "/saveDimRegion", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String saveDimRegion(HttpServletRequest request,
			HttpServletResponse response) throws JsonGenerationException,
			JsonMappingException, IOException {
		String dimRegionName = request.getParameter("dimRegionName");
		DimRegion dimIndustry = new DimRegion();
		dimIndustry.setRegionName(dimRegionName);
		dimIndustry.setRegionId(dimRegionService.getMaxRegionId());
		dimIndustry.setIsValId(1);
		dimRegionService.saveDimRegion(Arrays.asList(dimIndustry));
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(dimIndustry);
		System.out.println(jsonString);
		return jsonString;
	}
}
