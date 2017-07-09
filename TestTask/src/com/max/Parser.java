package com.max;

import java.io.File;

public class Parser extends Thread {

	private int count = 0;
	private String inputPath;
	
	public Parser(String inputPath){
		this.inputPath = inputPath;
	}

	@Override
	public void run() {
		parse(new File(inputPath));

	}
	
	private void parse(File path){
		File[] files = path.listFiles(file->file.isFile());
		File[] dirs = path.listFiles(file->file.isDirectory());
		count+=files.length;
		for(File dir : dirs){
			parse(dir);
		}
	}

	public int getCount() {
		return count;
	}

	public String getInputPath() {
		return inputPath;
	}
}
