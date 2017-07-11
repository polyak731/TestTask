package com.max;

import java.io.File;
import java.io.IOException;

public class Parser extends Thread implements Listener{

	private int count = 0;
	private String inputPath;
	private Runnable start;
	private Runnable end;
	
	public Parser(String inputPath) throws IOException{
		if(!new File(inputPath).exists()) throw new IOException("Directory is not exist "+inputPath);
		if(!new File(inputPath).isDirectory()) throw new IOException("Can't find dirrectory "+inputPath);
		this.inputPath = inputPath;
	}

	@Override
	public void run() {
		if(start!=null) start.run();
		parse(new File(inputPath));
		if(end!=null) end.run();
	}
	
	private void parse(File path){
		if(this.isInterrupted()) return;
		File[] files = path.listFiles(file->file.isFile());
		File[] dirs = path.listFiles(file->file.isDirectory());
		if(files!=null) count+=files.length;
		if(dirs!=null) for(File dir : dirs){
			parse(dir);
		}
	}
	
	public void onStart(Runnable target){
		start = target;
	}

	public void onEnd(Runnable target){
		end = target;
	}

	public synchronized int getCount() {
		return count;
	}

	public synchronized String getInputPath() {
		return inputPath;
	}

	@Override
	public synchronized String toString() {
			return "["+getName()+"]["+count+"]["+inputPath+"]";
	}

	@Override
	public void onEvent(Boolean state) {
		if(state==false) interrupt();
	}
}
