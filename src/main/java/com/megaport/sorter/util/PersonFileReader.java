package com.megaport.sorter.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.megaport.sorter.model.Person;

public class PersonFileReader implements FileReader<Person>{

	private String sourceFile;
	
	public PersonFileReader (String sourceFile) {
		if(sourceFile == null || sourceFile.isBlank())
			throw new IllegalArgumentException("List of Persons or Source File Name missing");
		
		this.sourceFile = sourceFile;
	}
	
	public List<Person> readFile() throws Exception {
		
		Stream<String> lines = null;
		try {
			Path filePath = Paths.get(this.sourceFile);
			lines = Files.lines(filePath); 

			List<Person> filteredLines = lines.filter(s -> s != null && !s.isEmpty() && s.matches(Constants.REGEX_FILE_LINE_STRUCTURE))
					.map(Person::mapToPerson).distinct()
					.sorted(Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName)) 														// classes
					.collect(Collectors.toList());
			return filteredLines;
			
		} finally {
			if(lines != null)
				lines.close();
		}
		
	}
}
