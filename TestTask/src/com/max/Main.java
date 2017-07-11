package com.max;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * 
 * @author Максим
 *
 * The Main class
 */
public class Main {

	public static void main(String[] args){
		//Checking the arguments
		if(args.length<2 || args.length>2){
			System.out.println("Invalid number of arguments: "+args.length);
			System.exit(0);
		}
		//Creating the files
		File inputFile = new File(args[0]);
		File outputFile = new File(args[1]);
		//Reading files
		try(FileLinesReader reader = new FileLinesReader(inputFile)) {
			FileHandler handler = new FileHandler(outputFile);
			handler.clearFile();	//clear output file
			reader.readFileLines();	//reading absolute paths from input file
			Pool pool = new Pool(); //creating of notifier for parsers
			//creating thread that listening console
			ConsoleReader console = new ConsoleReader(pool,reader.getLines().size());
			int number=0;	//counter for thread number
			//starting of all parsers
			for(String path : reader.getLines()){
				Parser parser = new Parser(path);
				parser.setName(String.valueOf(number++));
				parser.onEnd(()->{
					handler.writeLine(parser.getInputPath()+";"+parser.getCount()+"\r\n");
					System.out.println(parser);
					console.incFlag();
				});
				//add listeners for notifier
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
