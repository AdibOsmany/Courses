/*
 * @author Adib Osmany
 * @course CS 284 F
 * @pledge I pledge my honor that I have abided by the Stevens Honor System.
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class Anagrams {
	final Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};

	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;
	Map<Long,ArrayList<String>> maxMap;
	ArrayList<String> maxm;

	/*
	 * The buildLetterTable method builds a hashmap with the alphabet as the key, and prime numbers as the value.
	 */
	public void buildLetterTable() {
		// Complete
		letterTable = new HashMap<Character,Integer>();
		String alphabet="abcdefghijklmnopqrstuvwxyz";
		char letter;
		int num;
		for(int i=0; i<26;i++) { 
			letter=alphabet.charAt(i);
			num=primes[i];
			letterTable.put(letter, num);
		}
		//throw new IllegalStateException("Not implemented");
	}

	/*
	 * This is an anagrams constructor that initializes maxMap, and anagramTable, it also calls the buildLetterTable method.
	 */
	Anagrams() {
		maxMap=new HashMap<Long,ArrayList<String>>();
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();

	}


	/*
	 * The addword method adds a given word to a list of its anagrams, and then adds that list to the anagramTable map. 
	 */
	public void addWord(String s) {
		// Complete
		long hashNum=myHashCode(s);
		ArrayList<String> hashLis;

		if(anagramTable.containsKey(hashNum)) {
			if(anagramTable.get(hashNum).contains(s)) {
				throw new IllegalArgumentException("addWord: duplicate value");
			}
			else {
				hashLis=new ArrayList<String>(anagramTable.get(hashNum));
				hashLis.add(s);
				anagramTable.put(hashNum,hashLis);
			}
		}
		else {


			hashLis=new ArrayList<String>();
			hashLis.add(s);
			anagramTable.put(hashNum,hashLis);


		}
		if(maxMap.isEmpty()) {
			maxm=new ArrayList<String>(hashLis);
			maxMap.put(hashNum, hashLis);

		}
		else {
			if(hashLis.size()>maxm.size()) {
				maxm.clear();
				maxm=new ArrayList<String>(hashLis);
				maxMap.clear();
				maxMap.put(hashNum, hashLis);
			}
			else if(hashLis.size()==maxm.size()) {

				maxMap.put(hashNum, hashLis);

			}
		}
	}
	//throw new IllegalStateException("Not implemented");

	/*
	 * the myHashCode method returns the numerical value of the string s based on the fundamental theorem of arithmetic. 
	 */
	public long myHashCode(String s) {
		// Complete
		if(s=="" || s==null) {
			throw new IllegalArgumentException();
		}
		else {
			s=s.toLowerCase();
			char letter;
			long num=1;
			for(int i=0; i<s.length();i++) {
				letter=s.charAt(i);
				num=num*letterTable.get(letter);
			}
			return num;
		}
		//throw new IllegalStateException("Not implemented");
	}

	/*
	 * The processFile method processes a given file. 
	 */
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null) {
			this.addWord(strLine);
		}
		br.close();
	}

	/*
	 * The getMaxEntries method returns the entries from anagramtable with the biggest arraylist. 
	 */
	public ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
		// Complete
		ArrayList<Entry<Long, ArrayList<String>>> k= new ArrayList<Entry<Long, ArrayList<String>>>(maxMap.entrySet());
		return k;
		//throw new IllegalStateException("Not implemented");
	}

	public static void main(String[] args) {
		Anagrams a = new Anagrams();
		final long startTime = System.nanoTime();
		try {
			a.processFile("C:\\Users\\adibo\\Downloads\\CS 284\\Anagrams\\words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}


		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries =
				a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000);
		System.out.println("Time: "+ seconds);
		System.out.println("List of max anagrams: "+ maxEntries);
	}
}
