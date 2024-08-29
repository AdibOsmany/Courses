/*
 * @author Adib Osmany
 * @course CS 284 F
 * @pledge I pledge my honor that I have abided by the Stevens Honor System.
 */
import java.util.ArrayList;
import java.util.Scanner;


public class Rolodex {
	private Entry cursor;
	private final Entry[] index;
	private ArrayList<Character> al;
	private ArrayList<String> names;
	private ArrayList<String> book;
	private int it;

	// Rolodex Constructor

	Rolodex() {
		names=new ArrayList<String>();
		book=new ArrayList<String>();
		index=new Entry[26];
		String alphabet="abcdefghijklmnopqrstuvwxyz".toUpperCase();
		al=new ArrayList<Character>();
		Entry prev=null; 
		Entry next=null;
		Entry set;
		char letter;
		for(int i=0; i<26;i++) {
			letter=alphabet.charAt(i);
			al.add(letter);
			set=new Separator(prev,next,letter);
			index[i]=set;
			prev=index[i];

		}
		index[0].prev=index[25];
		for(int i=0; i<26;i++) {
			if(i!=25) {
				next=index[i+1];
				index[i].next=next;	
			}
			else {
				next=index[0];
				index[i].next=next;
			}
		}
	}

	
	/*
	 * The contains functions checks to see if a given name is in the rolodex. 
	 */
	public Boolean contains(String name) {
		return names.contains(name);
	}

	/*
	 * The size function returns the number of cards in the rolodex.
	 */
	public int size() {
		return names.size();
	}

	/*
	 * The lookup function returns all the cell numbers of a given name.
	 * If the give name is not in the rolodex, the function returns an error statement.
	 */
	public ArrayList<String> lookup(String name) {
		if(contains(name)) {
			ArrayList<String> du=new ArrayList<String>();
			char letter=name.charAt(0);
			int num=al.indexOf(letter);
			Entry curr=index[num].next;
			while((curr.isSeparator())==false) {
				if(curr.getName().equals(name)) {
					du.add(((Card) curr).getCell());
				}
				curr=curr.next;
			}
			return du;
		}
		else {
			throw new IllegalArgumentException("lookup: name not found");
		}
	}

	/*
	 * The toString function returns a string representation of the rolodex.
	 */
	public String toString() {
		Entry current = index[0];

		StringBuilder b = new StringBuilder();
		while (current.next!=index[0]) {
			b.append(current.toString()+"\n");
			current=current.next;
		}
		b.append(current.toString()+"\n");		
		return b.toString();
	}

	
	/*
	 * The addCard function adds a card to the rolodex.
	 * If the input of the name does not match the requirements of 
	 * name, then the function returns an error. The same is applied to 
	 * the cell input. If a card already exists with the same name and cell,
	 * the function returns an error. 
	 */
	public void addCard(String name, String cell) {
		char letter=name.charAt(0);
		Entry next=null;
		Entry prev=null;
		Entry curr=null;
		Entry contact;
		boolean p=true;
		Scanner scan=new Scanner(cell);
		if(al.contains(letter)) {
			if(scan.hasNextInt()) {
				scan.close();
				if (book.contains(name+" "+cell)) {
					throw new IllegalArgumentException("addCard: duplicate entry");
				}
				else {
					int num=al.indexOf(letter);
					curr=index[num];
					next=curr.next;
					prev=curr.prev;
					while(p){
						if(next.isSeparator()) {
							contact=new Card(curr,next,name,cell);
							curr.next=contact;
							next.prev=contact;
							p=false;
						}
						else {
							curr=curr.next;
							next=curr.next;
							prev=curr.prev;
							int q=name.compareTo(curr.getName());
							if(q<=0) {
								contact=new Card(prev,curr,name,cell);
								prev.next=contact;
								curr.prev=contact;
								p=false;
							}
						}
					}
				}
				names.add(name);
				book.add(name+" "+cell);
			}
			else {
				scan.close();
				throw new IllegalArgumentException("addCard: illegal cell input");
			}
		}
		else {
			scan.close();
			throw new IllegalArgumentException("addCard: illegal name input");
		}

	}

	/*
	 * The removeCard function removes a given card. If the name 
	 * given is not in the rolodex, the function returns an error.
	 * If there is a card with the given name, but not the given cell,
	 * the function returns an error.
	 */
	public void removeCard(String name, String cell) {
		if(contains(name)) {
			if (book.contains(name+" "+cell)) {
				int tu=book.indexOf((name+" "+cell));
				book.remove(tu);
				names.remove(tu);
				char letter=name.charAt(0);
				boolean p=true;
				int num=al.indexOf(letter);
				Entry curr=index[num].next;
				while(p) {
					if((curr.getName().equals(name))&& (((Card) curr).getCell()).equals(cell)) {
						curr.prev.next=curr.next;
						curr.next.prev=curr.prev;
						p=false;
					}
					else {
						curr=curr.next;
					}
				}
			}
			else {
				throw new IllegalArgumentException("removeCard: cell for that name does not exist");
			}
		}
		else {
			throw new IllegalArgumentException("removeCard: name does not exist" );
		}
	}

	/*
	 * The removeAllCards function removes all the cards with the given name.
	 * If the name given is not in the rolodex, the function returns an error.
	 */
	public void removeAllCards(String name) {
		if(contains(name)) {
			char letter=name.charAt(0);
			int num=al.indexOf(letter);
			Entry curr=index[num].next;
			while((curr.isSeparator())==false) {
				if((curr.getName().equals(name))) {
					int tu=names.indexOf(name);
					book.remove(tu);
					names.remove(tu);
					curr.prev.next=curr.next;
					curr.next.prev=curr.prev;
				}
					curr=curr.next;
				
			}

		}
		else {
			throw new IllegalArgumentException("removeAllCards: name does not exist");
		}
	}

	// Cursor operations
/*
 * The initialize Cursor function sets cursor to the first separator in the rolodex. 
 */
	public void initializeCursor() {
		it=0;
		cursor=index[it];
	}
/*
 * The nextSeparator function sets the cursor to next separator. 
 */
	public void nextSeparator() {
		it++;
		if(it==26) {
			it=0;
		}
		cursor=index[it];
	}

	/*
	 * The nextEntry function sets the cursor to next entry in the rolodex. 
	 */
	public void nextEntry() {
		cursor=cursor.next;
		if(cursor.isSeparator()) {
			it++;
		}

	}

	/*
	 * The currentEntryTOString function returns the string representation
	 * of the cursor. 
	 */
	public String currentEntryToString() {
		return cursor.toString();
	}

	public static void main(String[] args) {
		int ffgh=6;
		int hy=ffgh;
		hy++;
		System.out.println(ffgh);
		System.out.println(hy);
		Rolodex r = new Rolodex();


		System.out.println(r);

		r.addCard("Chloe", "123");
		r.addCard("Chad", "23");
		r.addCard("Cris", "3");
		r.addCard("Cris", "4");
		r.addCard("Cris", "5");
		//		r.addCard("Cris", "4");
		r.addCard("Maddie", "23");

		System.out.println(r);

		System.out.println(r.contains("Albert"));

		r.removeAllCards("Cris");

		System.out.println(r);

		r.removeAllCards("Chad");
		r.removeAllCards("Chloe");

		r.addCard("Chloe", "123");
		r.addCard("Chad", "23");
		r.addCard("Cris", "3");
		r.addCard("Cris", "4");

		System.out.println(r);

	}
}
