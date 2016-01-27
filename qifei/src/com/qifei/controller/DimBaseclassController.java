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
import org.springframework.web.servlet.ModelAndView;

import com.qifei.model.DimBaseclass;
import com.qifei.service.DimBaseclassService;

@Controller
public class DimBaseclassController {

	@Autowired
	DimBaseclassService dimBaseclassService;

	@RequestMapping(value = "/getAllDimBaseclass",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String getAllDimBaseclass(ModelAndView model)
			throws JsonGenerationException, JsonMappingException, IOException {
		List<DimBaseclass> list = dimBaseclassService.getAllDimBaseclass();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(list);
		System.out.println(jsonString);
		return jsonString;
	}

}
