package com.max;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args){
		
		if(args.length<2 || args.length>2){
			System.out.println("Invalid number of arguments: "+args.length);
			System.exit(0);
		}
		
		File inputFile = new File(args[0]);
		File outputFile = new File(args[1]);
		
		try(FileLinesReader reader = new FileLinesReader(inputFile)) {
			FileHandler handler = new FileHandler(outputFile);
			handler.clearFile();
			reader.readFileLines();
			Pool pool = new Pool();
			ConsoleReader console = new ConsoleReader(pool,reader.getLines().size());
			for(String path : reader.getLines()){
				Parser parser = new Parser(path);
				parser.onEnd(()->{
					handler.writeLine(parser.getInputPath()+";"+parser.getCount()+"\n");
					System.out.println(parser);
					console.incFlag();
				});
				pool.addListener(parser);
				parser.start();
			}
			console.start();
			
		}catch(FileNotFoundException ex){
			System.out.println(ex.getMessage());
		}catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
