/*******************************************************************************
 * Filename: sqrt.cpp
 * Author  : Adib Osmany
 * Version : 1.0
 * Date    : September 7th, 2023
 * Description: Computes the square root of an integer from the command-line argument.
 * Pledge  : I pledge my honor that I have abided by the Stevens Honor System.
 ******************************************************************************/
#include <iostream>
#include <sstream>
#include <iomanip>
#include <math.h>
#include <limits>
using namespace std;

/*
The sqrt function uses Newtons method to calculate the square root of a number. 
It takes in 2 arguments, one double variable which is what is getting square rooted, 
and epsilon which determines when to stop running newtons method. 
*/
double sqrt(double num, double epsilon){
    double lastGuess = num;
    double nextGuess =0;
    if(num == 1 || num==0){
        return num;
    }
    else if(num<0){
        return numeric_limits<double>::quiet_NaN();
    }
    while((abs(lastGuess-nextGuess)) > epsilon){
        if (nextGuess==0){
            nextGuess=((lastGuess+(num/lastGuess))/2);
        }
        else{
            lastGuess=nextGuess;
            nextGuess=((lastGuess+(num/lastGuess))/2);
        }
    }
    return nextGuess;
        
}


/*
The main function checks to see there are a right number of arguments being inputed, 
and that the arguments are of the right type. 
*/

int main(int argc, char* argv[]) {
    double num;
    double ep;
    istringstream iss;

    if(argc == 2){
        iss.str(argv[1]);
        if(!(iss >> num)){
            cerr << "Error: Value argument must be a double." << endl;
            return 1;
        }
        iss.clear();
        ep= 1e-7;
    }
    else if (argc == 3){
        iss.str(argv[1]);
        if(!(iss >> num)){
            cerr << "Error: Value argument must be a double." << endl;
            return 1;
        }
        iss.clear();
        iss.str(argv[2]);
        if(!(iss >> ep)){
            cerr << "Error: Epsilon argument must be a positive double." << endl;
            return 1;
        }
        if(ep<=0){
            cerr << "Error: Epsilon argument must be a positive double." << endl;
            return 1;
        }
        iss.clear();
    }
    else{
        cerr << "Usage: " << argv[0] << " <value> [epsilon]" << endl;
        return 1;
    }

    cout << fixed << setprecision(8) << sqrt(num, ep) << endl; 
    
    return 0;
}
