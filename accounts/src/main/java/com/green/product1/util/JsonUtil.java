package com.green.product1.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	
	public static String asJson(Object o) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(o);
	}
}
