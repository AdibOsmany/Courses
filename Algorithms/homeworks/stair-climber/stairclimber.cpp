/*******************************************************************************
 * Name        : stairclimber.cpp
 * Author      : Adib Osmany
 * Date        : 10/4/2023
 * Description : Lists the number of ways to climb n stairs.
 * Pledge      : I pledge my honor that I have abided by the Stevens Honor System.
 ******************************************************************************/
#include <iostream>
#include <vector>
#include <algorithm>
#include <sstream>
#include <iomanip>

using namespace std;
vector<vector< vector<int> >> memorize={{{}}}; //a global vector variable that is used as memorization for the get_ways recursive function.



vector< vector<int> > get_ways(int num_stairs) {
    // TODO: Return a vector of vectors of ints representing
    // the different combinations of ways to climb num_stairs
    // stairs, moving up either 1, 2, or 3 stairs at a time.

    if(num_stairs<(int)memorize.size()){ //checks to see if num_stairs is already in memorize, which is our global vector variable. 
        return memorize[num_stairs];
    }
    vector<vector<int>> ways={}; //creates a vector storing a vector of ints, this is what will get returned.
    for(int i=1; i<=3; i++){ //goes through a for-lopp three times, starting at 1. It goes through the loop three times because that is the highest number of steps we can go through in one stride. 
        if(num_stairs>=i){
            vector< vector<int> > result= get_ways(num_stairs-i); //this is the part of the function that recurses. the result vector variable is what we will use to combine the ways vector with.
            for(int j=0; j<(int)result.size(); j++){ //goes through a loop of the int vectors in the result vector.
                result[j].push_back(i); //adds i to each int vector of result.
            } 
            ways.insert(ways.end(),result.begin(),result.end()); //inserts all the elements from the result vector to the end of the ways vector.
        }
    }
    sort(ways.begin(), ways.end()); //uses the sort function from the algorithms package to sort the ways vector.
    memorize.push_back(ways); //adds the ways vector into the memorize vector, which is what we use for memorization.  
    return ways; //returns ways. 
}

void display_ways(const vector< vector<int> > &ways) {
    // TODO: Display the ways to climb stairs by iterating over
    // the vector of vectors and printing each combination.
    int space=0;
    int len=ways.size();
    while(len>=1){
        len=len/10;
        space ++;
    }
    //the piece of code above helps to determine the width of the number of spaces in the beginning of each line of labels. 
    for (int i=0; i <(int)ways.size(); i++){ //goes through every vector in ways.
        cout<< setw(space) << right << i+1<<". ["; //the labels are right-aligned to the width of the highest label
        for (auto it = ways[i].begin(); it != ways[i].end(); it++){ //goes through every integer in the current vector.
            cout << *it;
            if(it+1 != ways[i].end()){ //checks to see if the next iterator is the end of the vector. If it is, it prints out the end bracket, if not, then it prints out a comma.
                cout<<", ";
            }
            else{
                 cout<<"]"<<endl;
            }
         }
    }
}

int main(int argc, char * const argv[]) {
    int num; //number of stairs to be climbed
    
    istringstream iss;
    if(argc == 2){ //checks to see that there are two arguments, if not then it prints out an error message.
        iss.str(argv[1]); //stores the the second argument in iss.
        if(!(iss >> num)){ //checks to see if iss can be converted to be used in num, if not then it prints out an error message.
            cerr << "Error: Number of stairs must be a positive integer." << endl;
            return 1;
        }
        if(num<=0){ //checks to see if num is greater than 0, if not then it returns an error message. 
            cerr << "Error: Number of stairs must be a positive integer." << endl;
            return 1;
        }
    }
    else{
        cerr << "Usage: " << argv[0] << " <number of stairs>" << endl;
        return 1;
    }

    vector< vector<int> > output=get_ways(num); //the vector of integer vectors that will be displayed
    int len=output.size(); //size of output
    string plural="";
    if(len>1){
        plural="s";
    }
    //the piece of code above helps to determine if the following print statement should be in plural tense. 
    cout<<len<<" way"<<plural<<" to climb "<<num<<" stair"<<plural<<"."<<endl; //prints out how many ways there is to climb num stairs.
    
    display_ways(output); //prints out the output vector in a specific way. 
    
    return 0;
}
