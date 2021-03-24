package com.megaport.sorter.main;

import java.util.List;

import com.megaport.sorter.model.Person;
import com.megaport.sorter.util.EventValidatorImpl;
import com.megaport.sorter.util.EventValidator;
import com.megaport.sorter.util.PersonFileReader;
import com.megaport.sorter.util.PersonFileWriter;

public class Main {

	public static void main(String[] args) {

		try {
			
			// Validate the arguments, sourcefile name
			EventValidator eventValidator = new EventValidatorImpl(args);
		    eventValidator.validate();
		    		
		    final String sourceFilename = args[0];
		    PersonFileReader personFileReader = new PersonFileReader(sourceFilename);
			List<Person> filteredLines = personFileReader.readFile();
			
			PersonFileWriter personFilerWriter = new PersonFileWriter(filteredLines, sourceFilename);
			personFilerWriter.writeFile();
			
		} 
		catch (Exception ex) { // Main point to catch errors and log messages
			System.out.println(ex.getMessage());
			System.out.println("Finished with an error. Please, try again");
		}
	}

}
