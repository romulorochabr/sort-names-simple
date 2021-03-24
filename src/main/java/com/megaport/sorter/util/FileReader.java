package com.megaport.sorter.util;

import java.util.List;

public interface FileReader<T> {
	
	List<T> readFile() throws Exception;

}
