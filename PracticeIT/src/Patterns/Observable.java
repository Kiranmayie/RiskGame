package Patterns;

// TODO: Auto-generated Javadoc
/**
 * The Interface Observable.
 */
public interface Observable {
	
	/**
	 * Register observer.
	 *
	 * @param o the o
	 */
	public void registerObserver(Observer o);
	
	/**
	 * Removes the observer.
	 *
	 * @param o the o
	 */
	public void removeObserver(Observer o);
	
	/**
	 * Notify observers.
	 *
	 * @param obj the obj
	 */
	public void notifyObservers(String obj);

}
