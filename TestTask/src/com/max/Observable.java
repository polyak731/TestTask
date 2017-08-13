package com.max;
/**
 * 
 * @author Максим
 *
 *	Interface Observable created as notifier for listeners
 */
public interface Observable {

	public void addListener(Listener listener);
	public void removeListener(Listener listener);
	public void notifyListeners();
}
