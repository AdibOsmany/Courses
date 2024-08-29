/*
 * @author Adib Osmany
 * @course CS 284 F
 * @pledge I pledge my honor that I have abided by the Stevens Honor System.
 */
public class Separator extends Entry {
	protected Character letter;

	public Separator(Entry prev, Entry next, Character letter) {
		super(prev, next);
		this.letter = letter;
	}

/*
 * Returns the letter of the separator
 */
	public Character getLetter() {
		return letter;
	}

/*
 * Sets the letter of the separator
 */
	public void setLetter(Character letter) {
		this.letter = letter;
	}

/*
 * Returns whether or not the entry is a separator.
 */
	public Boolean isSeparator() {
		return true;
	}
	
	/*
	 * Returns the size of the entry. 
	 */
	public int size() {
		return 0;
	}
	
	/*
	 * Returns the string representation of letter.
	 */
	public String getName() {
		return letter.toString();
	}
	
/*	
 * returns a string representation of the separator
 */
	public String toString() {
		return "Separator "+letter;
	}
	
}
