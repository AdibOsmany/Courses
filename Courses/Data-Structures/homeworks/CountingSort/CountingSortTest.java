/*
 * @author Adib Osmany
 * @course CS 284 F
 * @pledge I pledge my honor that I have abided by the Stevens Honor System.
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CountingSortTest {

	
	private static String print(int[] D) {
		String s="[ ";
		for(int i = 0; i < D.length-1; i++) {
			s= s+D[i]+", ";
		}
		s=s+D[D.length-1]+" ]";
		return s;
	}
	
	@Test
	void sort() {
		int[] A= { 2, 5, 3, 0, 2, 3, 0, 3};
		A=CountingSort.sort(A);
		assertEquals("[ 0, 0, 2, 2, 3, 3, 3, 5 ]", print(A));
		System.out.println("Test 1 passed");
		int[] B=null;
		assertThrows(IllegalArgumentException.class, () -> CountingSort.sort(B));
		System.out.println("Test 2 passed");
		int[] C= {};
		assertThrows(IllegalArgumentException.class, () -> CountingSort.sort(C));
		System.out.println("Test 3 passed");
		int[] D= { 2};
		D=CountingSort.sort(D);
		assertEquals("[ 2 ]", print(D));
		System.out.println("Test 4 passed");
		int[] E= { 0, 0, 0, 0, 0, 0, 0, 0};
		E=CountingSort.sort(E);
		assertEquals("[ 0, 0, 0, 0, 0, 0, 0, 0 ]", print(E));
		System.out.println("Test 5 passed");
		int[] F= { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 23, 54};
		F=CountingSort.sort(F);
		assertEquals("[ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 23, 54 ]", print(F));
		System.out.println("Test 6 passed");
		int[] G= { 0,0,0,0,7,0,0,0,0,2};
		G=CountingSort.sort(G);
		assertEquals("[ 0, 0, 0, 0, 0, 0, 0, 0, 2, 7 ]", print(G));
		System.out.println("Test 7 passed");
		
	}

}
