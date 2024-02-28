/**
 * @author Adib Osmany
 * @course CS 284 F
 * @pledge I pledge my honor that I have abided by the Stevens Honor System.
 */

import java.util.Arrays;
class BinaryNumber {
	//the data variable stores the individual numbers of a binary input, the length variable is how long the binary is, the binstr variable is the String form of the binary, and binnum is the decimal form of the binary.//
    private int[] data;
	private int length;
	private String binstr="";
	private int binnum;
	
	
  BinaryNumber(int length)  {
	  if(length<=0) {
		  throw new IllegalArgumentException();
	  }
		this.length = length;
		data = new int[length];
		binnum=0;
		for(int i=0; i<length;i++) {
			data[i]=0;
			binstr=binstr+"0";
		}
	
  }
  //The binit function is used to store, save, and change any variables that might have changed with the other functions.
  public void binint(String str,int dir, int val) {
	  if(str.length()==0) {
		  throw new IllegalArgumentException();
	  }
	  for(int i=0; i<str.length();i++) {
		  if(str.charAt(i)!= '0' && str.charAt(i)!= '1') {
			  throw new IllegalArgumentException();
		  }
	  }
	  binnum=0;
	  String rts=str;
	  if (val== -1) {
		  for (int i=0;i<dir;i++) {
			  rts=rts+"0";
		  }
	  }
	  else if(val==1) {
		  rts=rts.substring(0,rts.length()-dir);
	  }
	  int j=rts.length();
	  length = j;
	  data = new int[j];
	  binstr=rts;
	  for (int i=0; i<j;i++) {
		int t=Character.getNumericValue(binstr.charAt(j-1-i));
		data[i]=t;
		binnum=(binnum+((int)(t*(Math.pow(2,i)))));
	  }
  }
  BinaryNumber(String str){
	  binint(str,0,0);
	}
//The getLength function return length of the argument
  public int getLength() {
		return length;
	}
  //The getInnerArray function returns the list of binary numbers of the argument
  public int[] getInnerArray() {
	  
	  return data;
  }
  //The getDigit function returns a specific element of the list of the binary numbers of the argument
  public int getDigit(int index){
	  if (index < 0 || index >= length) {
		  throw new IndexOutOfBoundsException();
	  }
	  else {
		  return data[index];
	  }
  }
  //The to Decimal function returns the decimal form of the binary argument
  public int toDecimal(){
	  return binnum;
  }
  //The bitshift function  shifts the binary output to the right or left in amount spaces.
  public void bitShift(int direction, int amount) {
	  if((direction !=-1) && (direction !=1)) {
		  throw new IllegalArgumentException("Invalid");
	  }
	  else if(amount<0) {
		  throw new IllegalArgumentException("Invalid");
	  }
	  else if (direction==-1){
		  binint(binstr,amount,-1);
	  }
	  else if(direction==1){
		if (length-amount<0) {
			throw new IllegalArgumentException("Invalid")  ;
			}
		else {
			binint(binstr,amount,1);
		}
	  }
  }

  public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2) {
	  if(bn1.length!=bn2.length) {
		  throw new IllegalArgumentException("Invalid");
	  }
	  else {
		  int []p=new int[bn1.length];
		  for (int i=0; i<bn1.length;i++) {
			  p[i]=(bn1.data[i] | bn2.data[i]);
		  }
		  return p;
	  }
  }
  
  public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2) {
	  if(bn1.length!=bn2.length) {
		  throw new IllegalArgumentException("Invalid");
	  }
	  else {
		  int []p=new int[bn1.length];
		  for (int i=0; i<bn1.length;i++) {
			  p[i]=(bn1.data[i] & bn2.data[i]);
		  }
		  return p;
	  }
  }
  //The toString function returns the binary output of the argument
  public String toString() {
	  String str="";
	  for(int i=0;i<this.length;i++) {
		  str=Integer.toString(data[i])+str;
	  }
	  binint(str,0,0);
	  return binstr;
  } 
  //The add function adds two binary arguments together. 
  public void add(BinaryNumber aBinaryNumber) {
	  int count=0;
	  int first;
	  int second;
	  if(this.length>aBinaryNumber.length) {
		  for(int i=0;i<this.length-aBinaryNumber.length;i++) {
			  aBinaryNumber.binstr="0"+aBinaryNumber.binstr;
		  }
		  aBinaryNumber.binint(aBinaryNumber.binstr,0,0);
	  }
	  else if(this.length<aBinaryNumber.length) {
		  for(int i=0;i<aBinaryNumber.length-this.length;i++) {
			  this.binstr="0"+this.binstr;
		  }
		  binint(this.binstr,0,0);
	  }
	  for(int i=0;i<this.length;i++) {
		  first= this.data[i];
		  second= aBinaryNumber.data[i];
		  if ((first==0) & (second==0)) {
			  this.data[i]=0+count;
			  count=0;
			  
		  }
		  else if (((first==1) & (second==0))|((first==0) & (second==1))) {
			  if (count==1) {
				  this.data[i]=0;
			  }
			  else {
				  this.data[i]=1;
			  }
		  }
		  else if((first==1) & (second==1)) {
			  if (count==1) {
				  this.data[i]=1;
			  }
			  else {
				this.data[i]=0;
				count=1;
			  }
			  
		  }
	  }
	  if(count==1) {
		  int[]p=new int[this.length+1];
		  for (int i=0;i<this.length;i++) {
			  p[i]=this.data[i];
		  }
		  p[this.length]=1;
		  this.data=p;
		  this.length=this.length+1;
	  }
	  this.toString();
  }
  
  public static void main(String[] args){
	  System.out.println((double) Math.round(((double) 95/13)*100)/100);
	  System.out.println( (int)(((double)2/5)+.5));
	  	BinaryNumber test = new BinaryNumber("10");
	  	System.out.println(test.getDigit(0));
		}
}
