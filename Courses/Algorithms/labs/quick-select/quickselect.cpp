/*******************************************************************************
 * Name          : quickselect.cpp
 * Author        : Adib Osmany
 * Pledge        : I pledge my honor that I have abided by the Stevens Honor System.
 * Date          : 10/23/2023
 * Description   : Implements the quickselect algorithm.
 ******************************************************************************/
#include <iostream>
#include <sstream>
#include <algorithm>
#include <vector>

using namespace std;

//lomuto_parition function implements the lomuto_parition algorithm. I used the algorithm given to us in lecture 21.
size_t lomuto_partition(int array[], size_t left, size_t right) {
    // TODO
    // DO NOT change the function header in any way, otherwise you will lose points.
    int p=array[left];
    size_t s=left;
    for(size_t i=left+1; i<=right; i++){
        if(array[i]<p){  
            s++;
            swap(array[s],array[i]);
        }
    }
    swap(array[left],array[s]);
    return s;
}

//quick_select function implements the quick_select algorithm. I used the algorithm given to us in lecture 21.
int quick_select(int array[], size_t left, size_t right, size_t k) {
    // TODO
    // DO NOT change the function header in any way, otherwise you will lose points.
    size_t s=lomuto_partition(array, left, right);  
    if(s==k-1){
        return array[s];
    }
    else if(s>k-1){
        return quick_select(array,left,s-1,k);
    }
    else{
        return quick_select(array,s+1,right,k);
    }
}

int quick_select(int array[], const size_t length, size_t k) {
    return quick_select(array, 0, length - 1, k);
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        cerr << "Usage: " << argv[0] << " <k>" << endl;
        return 1;
    }

    int k;
    istringstream iss;
    iss.str(argv[1]);
    if ( !(iss >> k) || k <= 0 ) {
        cerr << "Error: Invalid value '" << argv[1] << "' for k." << endl;
        return 1;
    }

    cout << "Enter sequence of integers, each followed by a space: " << flush;
    int value, index = 0;
    vector<int> values;
    string str;
    str.reserve(11);
    char c;
    iss.clear();
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

    int num_values = values.size();
    if (num_values == 0) {
        cerr << "Error: Sequence of integers not received." << endl;
        return 1;
    }
    // TODO - error checking k against the size of the input
    // TODO - call the quick_select function and display the result

    //checks if the error output statement needs to be in plural tense. 
    string plural="";
    if(num_values!=1){
        plural="s";
    }
    //checks if k if greater than the array size and returns an error if so
    if(k>num_values){
        cerr << "Error: Cannot find smallest element "<<k<<" with only "<<num_values<<" value"<<plural<<"."<< endl;
        return 1;
    }

    //calls the quick_select function and displays the result.
    int element=quick_select(&values[0],num_values,k);
    cout<<"Smallest element "<<k<<": "<<element<<endl;

    return 0;
}
