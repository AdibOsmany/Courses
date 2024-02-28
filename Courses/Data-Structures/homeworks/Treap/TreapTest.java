/*
 * @author Adib Osmany
 * @course CS 284 F
 * @pledge I pledge my honor that I have abided by the Stevens Honor System.
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TreapTest {

	@Test
	void addK() {
		Treap<Integer> testTree = new Treap <Integer> ();
		testTree . add (1 ,99);
		assertEquals(testTree.toString(), "(key=1, priority=99)\n  null\n  null\n");
		testTree . add (2 ,20);
		
		assertEquals(true, testTree . add (4 ,20));
		assertEquals(false, testTree . add (1 ,20));
		System.out.println("Passed add (E key ) function test");
	}
	
	@Test
	void addKP() {
		Treap<Integer> testTree = new Treap <Integer> ();
	
		assertEquals(true,testTree . add (1));
		testTree . add (2);
		
		assertEquals(true, testTree . add (4));
		assertEquals(false, testTree . add (1));
		System.out.println("Passed add (E key , int priority ) function test");
	}
	
	@Test
	void delete() {
		Treap<String> testTree = new Treap <String> ();
		assertEquals(false, testTree .delete("z"));
		testTree . add ("p" ,99);
		assertEquals(true, testTree .delete("p"));
		testTree . add ("p" ,99);
		testTree . add ("g" ,80);
		testTree . add ("u",75);
		testTree . add ("a" ,60);
		testTree . add ("j" ,65);
		testTree . add ("r",40);
		testTree . add ("z" ,47);
		testTree . add ("w",32);
		testTree . add ("v",21);
		testTree . add ("x" ,25);
		assertEquals(true, testTree .delete("z"));
		testTree .delete("p");
		assertEquals(false, testTree.find("p"));
		System.out.println("Passed delete ( E key ) function test");
	}
	
	@Test
	void find() {
		Treap<String> testTree = new Treap <String> ();
		assertEquals(false, testTree.find("z"));
		testTree . add ("p" ,99);
		assertEquals(true, testTree.find("p"));
		testTree . add ("g" ,80);
		assertEquals(true, testTree.find("g"));
		testTree . add ("u",75);
		assertEquals(true, testTree.find("u"));
		testTree . add ("a" ,60);
		assertEquals(true, testTree.find("a"));
		testTree . add ("j" ,65);
		assertEquals(true, testTree.find("j"));
		testTree . add ("r",40);
		assertEquals(true, testTree.find("r"));
		testTree . add ("z" ,47);
		assertEquals(true, testTree.find("z"));
		testTree . add ("w",32);
		assertEquals(true, testTree.find("w"));
		testTree . add ("v",21);
		assertEquals(true, testTree.find("v"));
		testTree . add ("x" ,25);
		assertEquals(true, testTree.find("x"));
		testTree .delete("z");
		assertEquals(false, testTree.find("z"));
		System.out.println("Passed  find (E key ) function test");
	}
	
	@Test
	void tostring() {
		Treap< Integer >testTree = new Treap < Integer > ();
		testTree . add (4 ,19);
		assertEquals(testTree.toString(), "(key=4, priority=19)\n  null\n  null\n");
		testTree . delete (4);
		testTree . add (5 ,20);
		assertEquals(testTree.toString(), "(key=5, priority=20)\n  null\n  null\n");
		System.out.println("Passed toString () function test");
	}
	
	
}
