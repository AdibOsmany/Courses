// Adib Osmany
// CS284A
// I pledge my honor that I have abided by the Stevens Honor System.

import java.util.ArrayList;
import java.lang.String;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class Dictionary {
	private ArrayList <DictionaryItem> dictArrayList;
	private ArrayList<String> wordList;
	
	/*
	 * Dictionary class constructor
	 */
	public Dictionary() {
		dictArrayList= new ArrayList<>(1300);
		wordList= new ArrayList<>(1300);
		readFile("C:\\Users\\adibo\\Downloads\\Neel 284\\Dictionary\\ionDictionary.txt");
	}
	
	
	/*
	 * Dictionary class constructor
	 */
	public Dictionary(String filename)  {
		dictArrayList= new ArrayList<>(1300);
		wordList= new ArrayList<>(1300);
		readFile(filename);
	}
	
	
	/*
	 * The readFile method takes a text file, scans it, and uses a helper 
	 * function to store the words in the text file into a dictionary array.
	 * The function also returns an error if it can not find the file. 
	 * 
	 */
	public void readFile(String filename)  {
		File Dict = new File(filename);
		try {
			Scanner scan =new Scanner(Dict);
			splitStoreLine(scan);
			scan.close();
		}
		catch(FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/*
	 * The splitStoreLine function is a helper function to
	 * the readFile function. It takes a scanner from readFile that 
	 * scanned the text file and stores each word in the text file 
	 * onto the dictionary array. It also stores the word and count 
	 * of that word into the dictArrayList array by storing the 
	 * values in an dictionaryItem type generic. 
	 */
	private void splitStoreLine(Scanner scan) {
		while(scan.hasNextLine()) {
			String s=scan.nextLine();
			String [] g=s.split("[|]");
			s="";
			for (int i=0;i<g.length;i++) {
				s=s+g[i];
			}
			Scanner sc=new Scanner(s);
			if (sc.hasNext()) {
				String word=sc.next();
				if (sc.hasNextInt()) {
					int count=sc.nextInt();
					wordList.add(word);
					DictionaryItem dictionaryWordCount = new DictionaryItem(word, count);
					dictArrayList.add(dictionaryWordCount);
				}
				
			}
		
		}
	}
	
	/*
	 * The printMenu function prints a menu for the user to interact with.
	 * It has three options, option 1 prints every word in the dictionary
	 * array. Option 2 checks to see if a word the user inputed is in the 
	 * dictionary, and if it is, it returns the count for that word. And 
	 * option 3 ends the program. 
	 */
	public void printMenu() {
		System.out.println("Welcome to the Ion Dictionary! This dictionary is created from the book Ion by Plato!");
		int menuCount=0;
		while (menuCount != 3) {
		System.out.println("Please choose one of the following menu items indicated with 1-3");
		System.out.println("1: To print all the words in the dictionary, choose 1");
		System.out.println("2: To search a word in the dictionary, choose 2");
		System.out.println("3: To quit the program, choose 3");
		Scanner userInput= new Scanner(System.in);
		if(userInput.hasNextInt()) {
			menuCount=userInput.nextInt();
			if (menuCount==1) {
				printDictionary();
			}
			else if (menuCount==2) {
				System.out.println("Please enter the word you would like to search:");
				Scanner wordScan= new Scanner(System.in);
				processMenuItem(menuCount,wordScan);
			}
			else if (menuCount==3) {
				System.out.println("Thanks for using Ion Dictionary! Bye!");
			}
			else {
				System.out.println("ERROR! Please enter a number between 1 and 3.");
			}
		}
		else {
			System.out.println("ERROR! Please enter a number between 1 and 3.");
		}
		}

	}
	
	
	/*
	 * The processMenuItem function is a helper function to the printMenu
	 * function. The processMenuItem function takes a word the user is
	 * trying to find in the dictionary and returns a 
	 * statement that has the associated count to that word. If the word
	 * is not found in the dictionary, then the function returns a
	 * statement that says the word is not in the dictionary. 
	 */
	private boolean processMenuItem(int menuitem, Scanner scan) {
		String wordString=scan.next();
		int dictionaryIndex=searchDictionary(wordString);
		if(dictionaryIndex==-1) {
			System.out.println("The word '"+wordString+"' does not exist in the Ion dictionary!");
		}
		else {
			System.out.println("The word '"+wordString+"' occurred "+dictionaryIndex+" times in the book!");
		}
		return true;
	}
	
	/*
	 * The printDictionary function prints every word in the
	 * dictionary array.
	 */
	public void printDictionary() {
		System.out.println("All the words mentioned in the Ion book!");
		System.out.println("Words");
		System.out.println("-----");
		for(int i=0; i< wordList.size(); i++)  {
			   System.out.println(wordList.get(i));
		   }
	}
	
	
	/*
	 * The searchDictionary function searches for a word 
	 * in the dictionary. If the word is in the dictionary, 
	 * the function returns the index at which the word is in.
	 * If the function can not find the word in the dictionary, 
	 * it returns -1.
	 */
	public int searchDictionary(String word) {
		word=word.toLowerCase();
		if(wordList.contains(word)) {
			int binaryNum=binarySearch(word, 0, wordList.size());
			DictionaryItem dictionaryWord= dictArrayList.get(binaryNum);
			return dictionaryWord.getCount();
		}
		else {
			return -1;
		}
	}
	
	
	/*
	 * The binarySearch function implements a binary search algorithm
	 * to find index in which a certain word is hiding in the dictionary
	 * array. 
	 */
	private int binarySearch(String word, int low, int high) {
		boolean wordFind=true;
		int lowNum=low;
		int highNum=high;
		int midNum = 0;
		while(wordFind) {
			midNum= (lowNum+highNum)/2;
			int numIndex=wordList.get(midNum).compareTo(word);
			if (numIndex==0) {
				wordFind=false;
			}
			else if (numIndex<0) {
				lowNum=midNum;
			}
			else if(numIndex>0) {
				highNum=midNum;
			}
		}
		return midNum;
	}
	
	
}
