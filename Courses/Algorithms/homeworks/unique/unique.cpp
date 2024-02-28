/*******************************************************************************
 * Name        : unique.cpp
 * Author      : Adib Osmany
 * Date        : 9/29/23
 * Description : Determining uniqueness of chars with int as bit vector.
 * Pledge      : I pledge my honor that I have abided by the Stevens Honor System.
 ******************************************************************************/
#include <iostream>
#include <cctype>

using namespace std;

/*
The is_all_lowercase function determines if a given string has all lowercase letters. It does
this by going through the individual characters of the string using a for loop. The loop stops when 
it has found a character that is not a lowercase letter or if it has reached the end of the string.
I used the "isalpha" and "isupper" function to help determine if the characters are lowercase letters.
If the character is not a lower case letter then the function returns false. 
*/
bool is_all_lowercase(const string &s) {
    // TODO: returns true if all characters in string are lowercase
    // letters in the English alphabet; false otherwise.
    for(int i=0; s[i]!='\0'; i++){
        if(!(isalpha(s[i])) || isupper(s[i])){
            return false;
        }
    }
    return true;
}

/*
The all_unique_letters function takes a given word and determines if the letters 
in the string are all unique. If they are not, the function returns false. If they are
the function returns true. The function determines if the letters are unique by creating 
a 26 bit int called vector. Each bit represents a letter in the alphabet, the right most 
bit representing 'a' and the left most bit representing 'z'. The function then goes
through a for loop to help go through all the characters in the string. Inside the for
loop the given character gets subtracted by 'a' to determine how far away it is from the 
start of the alphabet. This value bitshifts the value of 1 to the left. This is then
stored in to 'setter'. With setter representing the bit spot of the given letter, it
then checks with vector using the bit-wise-and function to see if vector already contains
a 1 in that given spot. If it does, this mean the letter has already been seen the word
is not unique in letter, and the function returns false. If not, then vector stores the 
1 in the same spot as setter. It does this by using the bit-wise-or function. The loop
then repeats until it finds a letter it has already seen before or if it has reached the
end of the string, in this case that means all the letters are unique and the function 
returns true.
*/
bool all_unique_letters(const string &s) {
    // TODO: returns true if all letters in string are unique, that is
    // no duplicates are found; false otherwise.

    // You MUST use only single unsigned integers and work with bitwise
    // and bitshifting operators only.  Using any other kind of solution
    // will automatically result in a grade of ZERO for the whole assignment.
    unsigned int vector= 0b00000000000000000000000000; //26 bit int called vector. Each bit represents a letter in the alphabet, the right most bit representing 'a' and the left most bit representing 'z'
    unsigned int setter; 
    //algorithm taken from lecture 8 of class. 
     for(int i=0; s[i]!='\0'; i++){ //a for loop to help go through all the characters in the string
        setter=1<<(s[i]-'a'); //given character gets subtracted by 'a' to determine how far away it is from the start of the alphabet. This value bitshifts the value of 1 to the left. This is then stored in to 'setter'
        if(!(vector&setter)){// setter checks with vector using the bit-wise-and function to see if vector already contains a 1 in that given spot
            vector=vector|setter; //vector stores the 1 in the same spot as setter.
        }
        else{
            return false;
        }
    }
    return true;


}


/*
The main function takes in two arguments. The first being 
the name of the file and second being the word to check the
uniquness of. If the program is only given 1 argument, it 
returns an error. If it is given more than two, it also 
returns an error. If it is given a string that does not
contain only lowercase letters, then it returns an error.
If all the inputs are correct, it runs the all_unique_letters 
function. If returned true, it outputs a statement saying the 
word is unique, if not, then it outputs a statement saying the
word has duplicate letters. 
*/
int main(int argc, char * const argv[]) {
    // TODO: reads and parses command line arguments.
    // Calls other functions to produce correct output.
    string word;
    if(argc == 2){
        word=argv[1];
        if(!(is_all_lowercase(word))){
            cerr << "Error: String must contain only lowercase letters." << endl;
            return 1;
        }
    }
    else{
         cerr << "Usage: " << argv[0] << " <string>" << endl;
        return 1;
    }

    if(all_unique_letters(word)){
        cout<<"All letters are unique."<<endl;
    }
    else{
         cout<<"Duplicate letters found."<<endl;
    }
    
    return 0;

}
