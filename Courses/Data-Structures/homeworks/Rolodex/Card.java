/*
 * @author Adib Osmany
 * @course CS 284 F
 * @pledge I pledge my honor that I have abided by the Stevens Honor System.
 */
public class Card extends Entry {
	private String name;
	private String cell;
	
		/*
		 * card constructor
		 */
	public Card(Entry prev, Entry next, String name, String cell) {
		super(prev, next);
		this.name = name;
		this.cell = cell;
	}

/*
 * returns the name of the card
 */
	public String getName() {
		return name;
	}

/*
 * sets the name of the card
 */
	public void setName(String name) {
		this.name = name;
	}

/*
 * returns the cell of the card
 */
	public String getCell() {
		return cell;
	}

/*
 * sets the cell of the card
 */
	public void setCell(String cell) {
		this.cell = cell;
	}

/*
 * Returns whether or not the entry is a separator
 */
	public Boolean isSeparator() {
		return false;
	}
	
	/*
	 * returns the size of the card
	 */
	public int size() {
		return 1;
	}
	
	/*
	 * Returns a string representation of the card. 
	 */
	public String toString() {
		return "Name: "+name+", Cell: "+cell;
	}
}
