package com.max;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Максим
 *
 *A class that reads all lines from a file
 */
public class FileLinesReader extends FileReader{
	
	private List<String> lines = new ArrayList<>();
	private BufferedReader in;
	
	public FileLinesReader(File file) throws FileNotFoundException {
		super(file);
		in = new BufferedReader(this);
	}
	
	public void readFileLines() throws IOException{
		String line;
        while((line = in.readLine())!=null){
        	lines.add(line);
        }
	}

	public List<String> getLines() {
		return lines;
	}

	@Override
	public String toString() {
		return lines.toString();
	}	
}
