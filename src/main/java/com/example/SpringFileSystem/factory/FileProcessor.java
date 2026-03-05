package com.example.SpringFileSystem.factory;

import org.springframework.web.multipart.MultipartFile;

public interface FileProcessor {
	
	boolean supports(String filename);
	
	void process(MultipartFile file) throws Exception;

}
