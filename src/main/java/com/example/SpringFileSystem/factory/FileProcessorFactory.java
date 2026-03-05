package com.example.SpringFileSystem.factory;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class FileProcessorFactory {
	
	
	private static final Logger logger = LoggerFactory.getLogger(FileProcessorFactory.class);

	private final List<FileProcessor> processors;
	
	public FileProcessorFactory(List<FileProcessor> processors) {
		this.processors = processors;
	}
	
	public void processFile(MultipartFile file) throws Exception{
		
		logger.info("Inside file FileTypeResolverImpl "+file);
		
		String fileName = file.getOriginalFilename();
		
		FileProcessor processor = 
		
		processors.stream()
		.filter(p -> p.supports(fileName))
		.findFirst()
		.orElseThrow(() ->  new IllegalStateException("Unsupported File Exception"));
		
		
		processor.process(file);
	}
	
	
	

}
