// Adib Osmany
// CS284A
// I pledge my honor that I have abided by the Stevens Honor System.

import java.lang.String;

public class DictionaryItem {
    //The DictionaryItem class stores a word and the number of times that word appeared
	//in a story. 
    private String word;
    private int count;
    
    /*
     * DictionaryItem constructor.
     */

    public DictionaryItem(String word, int count){
        this.word=word;
        this.count=count;
    }

    /*
     * Returns the word variable of the DictionarItem
     */
    public String getWord(){
        return this.word;
    }
    
    /*
     * Sets the word variable for the DictionaryItem
     */
   public void setWord(String word){
        this.word=word;
   }
   
   /*
    * Returns the count variable of the DictionaryItem
    */
   public int getCount(){
        return this.count;
   }
   
   /*
    * Sets the count variable for the DictionaryItem
    */
   public void setCount(int count){
        this.count=count;
    }
   

   
   
}