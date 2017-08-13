package com.max;
/**
 * 
 * @author Максим
 *
 * 
 * Functional interface allowing to 
 * listen to classes inheriting Observable
 */
public interface Listener {
	/**
	 *  * @param state . Setter of state of the current listener.
	 */
	public void onEvent(Boolean state);
}
