package com.max;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileListReader{
	
	private List<String> dirs = new ArrayList<>();
	private File file;
	
	public FileListReader(File file) throws FileNotFoundException {
		if(!file.exists() || !file.isFile()) throw new FileNotFoundException();
		this.file = file;
	}
	
	public void readFileNames() throws IOException{
		try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {
			dirs = stream.filter(line->{
				return !line.isEmpty() && !line.contains(".");
				}).collect(Collectors.toList());
		} catch (IOException e) {
			throw e;
		}
	}

	public List<String> getDirs() {
		return dirs;
	}

	@Override
	public String toString() {
		return dirs.toString();
	}
	
	
}
