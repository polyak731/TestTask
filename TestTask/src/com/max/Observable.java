package com.max;

public interface Observable {

	public void addListener(Listener listener);
	public void removeListener(Listener listener);
	public void notifyListeners();
}
