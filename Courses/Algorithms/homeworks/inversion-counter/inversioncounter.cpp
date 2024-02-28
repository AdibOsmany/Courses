/*******************************************************************************
 * Name        : inversioncounter.cpp
 * Author      : Adib Osmany
 * Version     : 1.0
 * Date        : 10/30/23
 * Description : Counts the number of inversions in an array.
 * Pledge      : I pledge my honor that I have abided by the Stevens Honor System.
 ******************************************************************************/
#include <iostream>
#include <algorithm>
#include <sstream>
#include <vector>
#include <cstdio>
#include <cctype>
#include <cstring>

using namespace std;

// Function prototype.
static long mergesort(int array[], int scratch[], int low, int high);


/**
 * Counts the number of inversions in an array in Theta(n^2) time using two nested loops.
 */
long count_inversions_slow(int array[], int length) {
    // TODO
    long count=0; //value to be returned
    for(int i=0; i<length-1; i++){
        for(int j=i+1; j<length; j++){
            if(array[i]>array[j]){
                count++;
            }
        }
    }
    return count;
}

/**
 * Counts the number of inversions in an array in Theta(n lg n) time.
 */
long count_inversions_fast(int array[], int length) {
    // TODO
    // Hint: Use mergesort!
    int low=0;
    int high=length-1;
    vector<int> scratch(length);
    return mergesort(array,&scratch[0],low,high);
}

/* the merged function implements the merge sort algorithm but returns the number of inversions found*/
static long merged(int array[], int scratch[], int low, int mid, int high){
    int i=low; //i1
    int j=mid+1; //i2
    int k=low; //i
    long count=0; //value to be returned
    while ((i<=mid) && (j<=high)){
        if(array[i]<=array[j]){
            scratch[k++]=array[i++];
        }
        else{
            scratch[k++]=array[j++];
            count+=mid-i+1;
        }
    }
    while(i<=mid){
        scratch[k++]=array[i++];
    }
    while(j<=high){
        scratch[k++]=array[j++];
    }
    copy(scratch+low, scratch+high+1, array);
    return count;
} 

/* merge sort algorithm modified to find the number of inversions in the given array*/
static long mergesort(int array[], int scratch[], int low, int high) {
    // TODO
    long count=0; //value to be returned
    if(low<high){
        int mid=low+((high-low)/2);
        count+=mergesort(array, scratch, low, mid);
        count+=mergesort(array, scratch, mid+1, high);
        count+=merged(array,scratch,low,mid,high);
    }
    return count;
}



int main(int argc, char *argv[]) {
    // TODO: parse command-line argument
    bool slow=false; //variable for if user wanted the slow version
    if(argc == 2){ 
        if(strcmp(argv[1],"slow")==0){ //uses strcmp from cstring to see if the second argument is "slow"
            slow=true;
        }
        else{
            cerr<<"Error: Unrecognized option '"<<argv[1]<<"'."<<endl; 
            return 1;
        }
    }
    else if(argc>2){ //returns error if more than 2 arguments
        cerr<<"Usage: ./inversioncounter [slow]"<<endl;
        return 1;
    }


    cout << "Enter sequence of integers, each followed by a space: " << flush;

    istringstream iss;
    int value, index = 0;
    vector<int> values;
    string str;
    str.reserve(11);
    char c;
    while (true) {
        c = getchar();
        const bool eoln = c == '\r' || c == '\n';
        if (isspace(c) || eoln) {
            if (str.length() > 0) {
                iss.str(str);
                if (iss >> value) {
                    values.push_back(value);
                } else {
                    cerr << "Error: Non-integer value '" << str
                         << "' received at index " << index << "." << endl;
                    return 1;
                }
                iss.clear();
                ++index;
            }
            if (eoln) {
                break;
            }
            str.clear();
        } else {
            str += c;
        }
    }
    // TODO: produce output
    int num_values = values.size(); //size of values
    if (num_values == 0) { //error message is of size 0
        cerr << "Error: Sequence of integers not received." << endl;
        return 1;
    }
    //prints the number of inversions using the selected algorithm
    if(slow){
        cout<<"Number of inversions (slow): "<<count_inversions_slow(&values[0], num_values)<<endl;
    }
    else{
        cout<<"Number of inversions (fast): "<<count_inversions_fast(&values[0], num_values)<<endl;
    }

    return 0;
}
