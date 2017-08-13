package scanners;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;
import java.io.File;

public class Scanner extends Thread{

	private int counter = 0;
	private String path;
	private int iterations = 0;
	private List<Exchanger<Integer>> exchangers = new ArrayList<>();
	private List<String> ignoreList = new ArrayList<>();
	private Exchanger<Integer> remover;
	public static CountDownLatch stop;
	public static CountDownLatch start;
	
	public void addToExchange(Exchanger<Integer> exchanger){
		exchangers.add(exchanger);
	}
	
	public Exchanger<Integer> getExchanger(){
		return remover = new Exchanger<>();
	}
	
	public Scanner(String path){
		this.path = new File(path).getAbsolutePath();
	}
	
	@Override
	public void run(){
		try {
			if(start!=null) start.await();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		parse(path);
		try {
			for(Exchanger<Integer> changer: exchangers){
				counter+= changer.exchange(new Integer(counter));
			}
			if(remover != null)
				remover.exchange(new Integer(counter));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		stop.countDown();
	}
	
	private void parse(String path){
		for(String ignore : ignoreList)
			if(ignore.compareTo(path)==0) return;
		for(File directory : new File(path).listFiles()){
			if(directory.isDirectory()){
					parse(directory.getAbsolutePath());
			} else {
				counter++;
			}
			iterations++;
		}
	}
	
	public int getIterations(){
		return iterations;
	}
	
	public synchronized int getCounter(){
		return counter;
	}
	
	public void addToIgnore(String path){
		ignoreList.add(path);
	}
	
	public void print(){
		System.out.println(counter+" "+path+" "+iterations+"\n"+ignoreList);
	}
	
	public synchronized void setCounter(int counter){
		this.counter =counter;
	}
	
	public synchronized void addToCounter(int count){
		counter+=count;
	}
	
	public List<String> getIgnoreList(){
		return ignoreList;
	}

	public String getPath() {
		// TODO Auto-generated method stub
		return path;
	}
}
