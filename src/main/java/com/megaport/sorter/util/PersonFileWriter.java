package com.megaport.sorter.util;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.megaport.sorter.model.Person;

public class PersonFileWriter {

	private List<Person> persons;
	private String sourceFileName;
	
	public PersonFileWriter (List<Person> persons, String sourceFileName) {
		
		if(persons == null || persons.size() == 0 || sourceFileName == null || sourceFileName.isBlank())
			throw new IllegalArgumentException("List of Persons or Source File Name missing");
		
		this.persons = persons;
		this.sourceFileName = sourceFileName;
	}
	
	public void writeFile () throws Exception {
		Pattern p = Pattern.compile(Constants.REGEX_FILE_NAME); // Retrieves the filename  from the path
		Matcher m = p.matcher(this.sourceFileName);
		if(m.find()) {
			
			String destinationFile = m.group(0);
			String [] pathParts = this.sourceFileName.split(destinationFile);
			String [] filesParts = destinationFile.split("\\.");
			destinationFile = new StringBuilder()
					.append(pathParts[0])
					.append(filesParts[0])
					.append("-sorted")
					.append(".")
					.append(filesParts[1])
					.toString();
				
			Files.write(Path.of(destinationFile),
					(Iterable<String>) this.persons.stream().map(s -> {
						    System.out.println(s);
							return String.valueOf(s);
						}).collect(Collectors.toList()),
					StandardCharsets.UTF_8);
			
			System.out.println("Finished: created " + destinationFile); // TODO - Change the file name, use LOG4J?
		} else {
			throw new Exception("Error trying to match source file name");
		}
	}
}
