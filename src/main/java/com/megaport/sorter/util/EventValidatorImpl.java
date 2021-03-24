package com.megaport.sorter.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventValidatorImpl implements EventValidator {

	private String[] arguments;
	
	public EventValidatorImpl(String[] arguments) {
		this.arguments = arguments;
	}
	
	public void validate() throws Exception {
		this.validateArguments();
		this.validateSouceFilename();
		this.validateFileType();
	}
	
	private void validateArguments() throws Exception {
		if (arguments == null || arguments.length == 0 )
			throw new IllegalArgumentException("Source filename is missing. Please, supply a source filename with proper extension.");
	}
	
	private void validateSouceFilename() throws Exception {
		final String sourceFile = arguments[0];
		
		Pattern p = Pattern.compile(Constants.REGEX_FILE_NAME); // Retrieves the filename  from the path
		Matcher m = p.matcher(sourceFile);
		if(m.find()) {
			
			String destinationFile = m.group(0);
			if(!destinationFile.matches(Constants.REGEX_VALIDATE_FILENAME_EXTENSION))
				throw new Exception("Source filename invalid. Please, ensure the source filename has an extension. For example: filename.txt");
		} else {
			throw new Exception("Filename not found in the arguments. Please, supply a source filename with proper extension.");
		}
			
	}
	
	private void validateFileType() throws Exception {
		final String sourceFile = arguments[0];
		try {
			Path filePath = Paths.get(sourceFile);
		    String type = Files.probeContentType(filePath);
		    if (type == null) {
		        throw new Exception(String.format("'%s' has an" + " unknown filetype.%n", sourceFile));
		    } else if (!type.equals("text/plain")) {
		        throw new Exception(String.format("'%s' is not" + " a plain text file.%n", sourceFile));
		    }
		} catch (IOException x) {
		    System.err.println(x);
		}
	}
	
}
