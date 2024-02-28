/*
 * @author Adib Osmany
 * @course CS 284 F
 * @pledge I pledge my honor that I have abided by the Stevens Honor System.
 */
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class RolodexTest {

	/*
	 * Tests the contains function of the rolodex class.
	 */
	@Test
	void contains() {
		Rolodex r= new Rolodex();
		r.addCard("Zeke", "1");
		r.addCard("Apple", "6");
		r.addCard("Albert", "123");
		assertEquals(true, r.contains("Zeke") );
		assertEquals(false, r.contains("Chris"));
		
		System.out.println("Passed contains() fucntion test");
	}
	
	/*
	 * Tests the size function of the rolodex class.
	 */
	@Test
	void size() {
		Rolodex r= new Rolodex();
		r.addCard("Zeke", "1");
		r.addCard("Apple", "6");
		r.addCard("Albert", "123");
		assertEquals(3, r.size() );
		r.addCard("Zeke", "2");
		r.addCard("Apple", "5");
		r.addCard("Albert", "23");
		r.removeAllCards("Zeke");
		assertEquals(4, r.size());
		System.out.println("Passed size() fucntion test");
	}
	
	/*
	 * Tests the lookup function of the rolodex class.
	 */
	@Test
	void lookup() {
		Rolodex r= new Rolodex();
		r.addCard("Zeke", "1");
		r.addCard("Apple", "6");
		r.addCard("Albert", "123");
		assertNotEquals("[2]", r.lookup("Apple").toString());
		r.addCard("Zeke", "2");
		r.addCard("Apple", "5");
		r.addCard("Albert", "23");
		assertEquals("[5, 6]", r.lookup("Apple").toString());
		assertThrows(IllegalArgumentException.class, () -> r.lookup("aple").toString());

		System.out.println("Passed lookup(String name) fucntion test");
	}

	/*
	 * Tests the toString function of the rolodex class.
	 */
	@Test
	void string() {
		Rolodex r= new Rolodex();
		Rolodex d= new Rolodex();
		assertEquals(r.toString(),d.toString());
		r.addCard("Zeke", "1");
		d.addCard("Zeke", "1");
		assertEquals(r.toString(),d.toString());
	
		System.out.println("Passed toString() fucntion test");
	}
	
	/*
	 * Tests the addCard function of the rolodex class.
	 */
	@Test
	void addCard() {
		Rolodex r= new Rolodex();
		r.addCard("Zeke", "1");
		r.addCard("Apple", "6");
		r.addCard("Albert", "123");
		r.addCard("Drake", "2");
		r.addCard("Smith", "5");
		r.addCard("Gregg", "23");
		assertThrows(IllegalArgumentException.class, () -> r.addCard("zeke", "1"));
		assertThrows(IllegalArgumentException.class, () -> r.addCard("Zeke", "1"));
		r.initializeCursor ();
		r.nextEntry();
		assertEquals("Name: Albert, Cell: 123",r.currentEntryToString());
		
		System.out.println("Passed addCard(String name, String cell) fucntion test");
	}
	
	/*
	 * Tests the removeCard function of the rolodex class.
	 */
	@Test
	void removeCard() {
		Rolodex r= new Rolodex();
		r.addCard("Zeke", "1");
		r.addCard("Apple", "6");
		r.addCard("Albert", "123");
		r.addCard("Drake", "2");
		r.addCard("Smith", "5");
		r.addCard("Gregg", "23");
		assertThrows(IllegalArgumentException.class, () -> r.removeCard("tyler", "1"));
		assertThrows(IllegalArgumentException.class, () -> r.removeCard("Drake", "3"));
		r.removeCard("Albert", "123");
		r.initializeCursor ();
		r.nextEntry();
		assertEquals("Name: Apple, Cell: 6",r.currentEntryToString());
		
		System.out.println("Passed removeCard(String name, String cell) fucntion test");
	}
	
	/*
	 * Tests the removeAllCards function of the rolodex class.
	 */
	@Test
	void removeAllCards() {
		Rolodex r= new Rolodex();
		r.addCard("Zeke", "1");
		r.addCard("Apple", "6");
		r.addCard("Albert", "123");
		r.addCard("Drake", "2");
		r.addCard("Smith", "5");
		r.addCard("Gregg", "23");
		r.addCard("Albert", "13");
		assertThrows(IllegalArgumentException.class, () -> r.removeAllCards("Tyler"));
		r.removeAllCards("Albert");
		r.initializeCursor ();
		r.nextEntry();
		assertEquals("Name: Apple, Cell: 6",r.currentEntryToString());
	
		System.out.println("Passed removeAllCards(String name) fucntion test");
	}
	
	
	/*
	 * Tests the initializeCursor function of the rolodex class.
	 */
	@Test
	void initializeCursor() {
		Rolodex r= new Rolodex();
		r.addCard("Zeke", "1");
		r.addCard("Apple", "6");
		r.addCard("Albert", "123");
		r.addCard("Drake", "2");
		r.addCard("Smith", "5");
		r.addCard("Gregg", "23");
		r.initializeCursor ();
		assertEquals("Separator A",r.currentEntryToString());
		
		System.out.println("Passed initializeCursor() fucntion test");
	}
	
	/*
	 * Tests the nextSeparator function of the rolodex class.
	 */
	@Test
	void nextSeparator() {
		Rolodex r= new Rolodex();
		r.addCard("Zeke", "1");
		r.addCard("Apple", "6");
		r.addCard("Albert", "123");
		r.addCard("Drake", "2");
		r.addCard("Smith", "5");
		r.addCard("Gregg", "23");
		r.initializeCursor ();
		r.nextSeparator();
		assertEquals("Separator B",r.currentEntryToString());
		r.initializeCursor ();
		r.nextEntry();
		r.nextEntry();
		r.nextEntry();
		r.nextSeparator();
		assertEquals("Separator C",r.currentEntryToString());
		
		System.out.println("Passed nextSeparator() fucntion test");
	}
	
	/*
	 * Tests the nextEntry function of the rolodex class.
	 */
	@Test
	void nextEntry() {
		Rolodex r= new Rolodex();
		r.addCard("Zeke", "1");
		r.addCard("Apple", "6");
		r.addCard("Albert", "123");
		r.addCard("Drake", "2");
		r.addCard("Smith", "5");
		r.addCard("Gregg", "23");
		r.initializeCursor ();
		r.nextEntry();
		assertEquals("Name: Albert, Cell: 123",r.currentEntryToString());
		r.nextSeparator();
		r.nextEntry();
		assertEquals("Separator C",r.currentEntryToString());
		
		System.out.println("Passed nextEntry() fucntion test");
	}
	
	/*
	 * Tests the currentEntryToString function of the rolodex class.
	 */
	@Test
	void currentEntryToString() {
		Rolodex r= new Rolodex();
		Rolodex d= new Rolodex();
		r.addCard("Zeke", "1");
		r.addCard("Apple", "6");
		r.addCard("Albert", "123");
		r.addCard("Drake", "2");
		r.addCard("Smith", "5");
		r.addCard("Gregg", "23");
		r.initializeCursor ();
		d.initializeCursor ();
		assertEquals(d.currentEntryToString(),r.currentEntryToString());
		for (int i=0; i<25; i++) {
			r.nextSeparator();
			d.nextSeparator();
		}
		assertEquals(d.currentEntryToString(),r.currentEntryToString());
	
		System.out.println("Passed currentEntryToString() fucntion test");
	}
	
}
