package com.max;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

	private File file;
	
	
	public FileHandler(File file){
		this.file = file;
	}
	
	public void clearFile(){
		try(FileWriter writer = new FileWriter(file)){     
			writer.write("");
        }catch (IOException e) {
            System.out.println("Can't clear");
        }
	}
	
	public void writeLine(String line){
		try(FileWriter writer = new FileWriter(file, true);
	            BufferedWriter bufferWriter = new BufferedWriter(writer)) {     
            bufferWriter.write(line);
        }
        catch (IOException e) {
            System.out.println("Can't write to file");
        }
	}
}
