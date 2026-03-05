package com.example.SpringFileSystem.factory;

import com.fasterxml.jackson.databind.JsonNode;

public interface JsonHandler {
	
	boolean supports(JsonNode root);

	void process(JsonNode root) throws Exception;

}
