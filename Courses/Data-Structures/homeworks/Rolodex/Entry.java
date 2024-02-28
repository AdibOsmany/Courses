/*
 * @author Adib Osmany
 * @course CS 284 F
 * @pledge I pledge my honor that I have abided by the Stevens Honor System.
 */
public abstract class Entry {
	protected Entry prev;
	protected Entry next;
	
	/*
	 * Entry constructor
	 */
	public Entry(Entry prev, Entry next) {
		super();
		this.prev = prev;
		this.next = next;
	}
	
	/*
	 * Returns whether or not the entry is a separator.
	 */
	public abstract Boolean isSeparator();
	
	/*
	 * Returns the size of the entry. 
	 */
	public abstract int size();
	
	/*	
	 * returns a string representation of the entry
	 */
	public abstract String getName();
	
}
