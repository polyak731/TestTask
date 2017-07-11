package com.max;

import java.util.LinkedList;
import java.util.List;
/**
 * 
 * @author Максим
 *
 * Class Pool class is a Parsers notifier
 */
public class Pool implements Observable{

	private List<Listener> listeners = new LinkedList<>();
	private boolean state = true;
	
	public synchronized void setState(boolean state){
		this.state = state;
		notifyListeners();
	}

	@Override
	public synchronized void addListener(Listener listener) {
		listeners.add(listener);
	}

	@Override
	public synchronized void removeListener(Listener listener) {
		listeners.remove(listener);
	}

	@Override
	public void notifyListeners() {
		for(Listener listener:listeners){
			listener.onEvent(state);
		}
	}
}
