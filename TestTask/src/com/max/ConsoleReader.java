package com.max;

import com.max.input.InputUtils;
/**
 * 
 * @author Максим
 *
 * A class that allow's to work with console input,
 * the successor of class thread for receiving input
 * in an additional thread
 */
public class ConsoleReader extends Thread{

	private Pool pool;
	private Integer flag;
	private Integer barrier;
	
	public ConsoleReader(Pool pool){
		this(pool,Integer.MAX_VALUE-1);
	}
	
	public ConsoleReader(Pool pool, Integer barrier){
		this.pool = pool;
		this.barrier = barrier;
		System.out.println("Press Esc to close app");
		this.flag = 0;
	}
	
	public synchronized void incFlag(){
		flag++;
	}
	
	public void setBarrier(Integer max){
		barrier = max;
	}

	@Override
	public void run() {
		while(true){
			if(InputUtils.kbhit() && InputUtils.getch()==27){
				pool.setState(false);
				break;
			}
			if(flag==barrier) break;
			sleep();
		}
	}
	
	private void sleep(){
		try {
			Thread.sleep(60);
		} catch (InterruptedException e) {
			System.out.println("Error with delay");
		}
	}
}
