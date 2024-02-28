/*
 * @author Adib Osmany
 * @course CS 284 F
 * @pledge I pledge my honor that I have abided by the Stevens Honor System.
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IDLListTest{
	
	/*
	 * The addE function tests to see that the add(E elem) function from 
	 * the IDLList class works properly. 
	 */
	@Test
	void addE() {
		IDLList<Integer> p= new IDLList<Integer>();
		for(int i=3; i>0;i--) {
	  		p.add(i);
	
	  	}
		assertEquals("[1, 2, 3]", p.toString() );
		p.add(100);
		assertEquals("[100, 1, 2, 3]", p.toString() );
		System.out.println("passed add(E elem) fucntion test");
	}
	
	/*
	 * The append function tests to see that the append(E elem) function from 
	 * the IDLList class works properly. 
	 */
	@Test
	void append() {
		IDLList<Integer> p= new IDLList<Integer>();
		for(int i=3; i>0;i--) {
	  		p.append(i);
	  	}
		assertEquals("[3, 2, 1]", p.toString() );
		p.append(100);
		assertEquals("[3, 2, 1, 100]", p.toString() );
		System.out.println("passed append(E elem) function test");
	}
	
	/*
	 * The addIntE function tests to see that the add(int index, E elem) function from 
	 * the IDLList class works properly. 
	 */
	@Test
	void addIntE() throws Exception{
		IDLList<Integer> p= new IDLList<Integer>();
		for(int i=3; i>0;i--) {
	  		p.add(i);
	  	}
		p.add(1,100);
		assertEquals("[1, 100, 2, 3]", p.toString() );
		p.add(2, 100);
		assertEquals("[1, 100, 100, 2, 3]", p.toString() );
		System.out.println("passed add(int index, E elem) function test");
	}
	
	
	/*
	 * The get function tests to see that the get (int index) function from 
	 * the IDLList class works properly. 
	 */
	@Test
	void get() throws Exception{
		IDLList<Integer> p= new IDLList<Integer>();
		for(int i=3; i>0;i--) {
	  		p.add(i);
	  	}
		assertEquals(2, p.get(1) );
		p.add(100);
		assertEquals(1, p.get(1) );
		System.out.println("passed get(int index) function test");
	}
	
	
	/*
	 * The getLast function tests to see that the getLast() function from 
	 * the IDLList class works properly. 
	 */
	@Test
	void getLast() {
		IDLList<Integer> p= new IDLList<Integer>();
		for(int i=3; i>0;i--) {
	  		p.add(i);
	  	}
		assertEquals(3, p.getLast() );
		p.add(100);
		assertEquals(3, p.getLast() );
		System.out.println("passed getLast() function test ");
	}
	
	
	/*
	 * The getHead function tests to see that the getHead() function from 
	 * the IDLList class works properly. 
	 */
	@Test
	void getHead() {
		IDLList<Integer> p= new IDLList<Integer>();
		for(int i=3; i>0;i--) {
	  		p.add(i);
	  	}
		assertEquals(1, p.getHead() );
		p.add(100);
		assertEquals(100, p.getHead() );
		System.out.println("passed getHead() function test");
	}
	
	
	/*
	 * The size function tests to see that the size() function from 
	 * the IDLList class works properly. 
	 */
	@Test
	void size() throws Exception{
		IDLList<Integer> p= new IDLList<Integer>();
		for(int i=3; i>0;i--) {
	  		p.add(i);
	  	}
		assertEquals(3, p.size() );
		p.add(100);
		p.add(11100);
		p.removeAt(2);
		assertEquals(4, p.size() );
		System.out.println("passed size() function test");
	}
	
	/*
	 * The remove function tests to see that the remove() function from 
	 * the IDLList class works properly. 
	 */
	@Test
	void remove() throws Exception{
		IDLList<Integer> p= new IDLList<Integer>();
		for(int i=3; i>0;i--) {
	  		p.add(i);
	  	}
		p.remove();
		assertEquals("[2, 3]", p.toString() );
		p.add(100);
		p.remove();
		assertEquals("[2, 3]", p.toString() );
		System.out.println("passed remove() function test");
	}
	
	/*
	 * The removeLast function tests to see that the removeLast() 
	 * function from the IDLList class works properly. 
	 */
	@Test
	void removeLast() throws Exception{
		IDLList<Integer> p= new IDLList<Integer>();
		for(int i=3; i>0;i--) {
	  		p.add(i);
	  	}
		p.removeLast();
		assertEquals("[1, 2]", p.toString() );
		p.add(100);
		p.removeLast();
		assertEquals("[100, 1]", p.toString() );
		System.out.println("passed removeLast() function test");
	}
	
	/*
	 * The removeAt function tests to see that the  removeAt(int index) 
	 * function from the IDLList class works properly. 
	 */
	@Test
	void removeAt() throws Exception {
		IDLList<Integer> p= new IDLList<Integer>();
		for(int i=3; i>0;i--) {
	  		p.add(i);
	  	}
		p.removeAt(1);
		assertEquals("[1, 3]", p.toString() );
		p.add(100);
		p.add(200);
		p.removeAt(2);
		assertEquals("[200, 100, 3]", p.toString() );
		System.out.println("passed removeAt(int index) function test");
	}
	
	/*
	 * The removeE function tests to see that the remove(E elem) 
	 * function from the IDLList class works properly. 
	 */
	@Test
	void removeE() {
		IDLList<Integer> p= new IDLList<Integer>();
		for(int i=3; i>0;i--) {
	  		p.add(i);
	  	}
		p.remove(1);
		assertEquals("[2, 3]", p.toString() );
		p.add(100);
		p.add(200);
		p.remove(500);
		assertEquals("[200, 100, 2, 3]", p.toString() );
		System.out.println("passed remove(E elem) function test");
	}
	
	/*
	 * The string function tests to see that the toString() 
	 * function from the IDLList class works properly. 
	 */
	@Test
	void string() {
		IDLList<Integer> p= new IDLList<Integer>();
		assertEquals("[]", p.toString() );
		p.add(100);
		assertEquals("[100]", p.toString() );
		System.out.println("passed toString() function test");
	}

}
