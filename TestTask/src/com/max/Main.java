package com.max;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.max.input.InputUtils;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException{
		FileListReader flr = new FileListReader(new File(args[0]));
		flr.readFileNames();
		ParserPool pool = new ParserPool();
		for(String path : flr.getDirs()){
			pool.add(new Parser(path));
		}
		pool.startAll();
		System.out.println("Press Esc to close app");
		while(InputUtils.getCh()!=27){}
		pool.stopAll();
		try(FileWriter writer = new FileWriter(new File(args[1]))){
			for(Parser parser : pool.getParsers()){
				writer.append(parser.getInputPath()+";"+parser.getCount()+"\n");
			}
			writer.write("");
		}catch(IOException ex){
			
		}
	}
	
	
}
