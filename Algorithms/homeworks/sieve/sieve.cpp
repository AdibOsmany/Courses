/*******************************************************************************
 * Name        : sieve.cpp
 * Author      : Adib Osmany
 * Date        : 9/18/2023
 * Description : Finds prime numbers using the sieve of Eratosthenes
 * Pledge      : I pledge my honor that I have abided by the Stevens Honor System.
 ******************************************************************************/
#include <cmath>
#include <iomanip>
#include <iostream>
#include <sstream>

using namespace std;

class PrimesSieve {
public:
//PrimeSieve Constructor
    PrimesSieve(int limit);

    ~PrimesSieve() {
        delete [] is_prime_;
    }

    void display_primes() const;

private:
    // Instance variables
    bool * const is_prime_;
    const int limit_;
    int num_primes_, max_prime_;

    // Method declarations
    void sieve();
    static int num_digits(int num);


};

PrimesSieve::PrimesSieve(int limit) :
        is_prime_{new bool[limit + 1]}, limit_{limit} {
    sieve();
}

void PrimesSieve::display_primes() const {
    // TODO: write code to display the primes in the format specified in the
    // requirements document.
    cout << endl;
    cout << "Number of primes found: "<< num_primes_ <<endl;
    cout << "Primes up to "<< limit_ << ":" <<endl;
    const int max_prime_width = num_digits(max_prime_), primes_per_row = 80 / (max_prime_width + 1);
    int primes= primes_per_row; //is used to countdown until the next line of primes

    /*
    outputs the prime numbers in the desired sequence 
    */
    for(int i=2; i<= limit_; i++ ){
        if (is_prime_[i]){
            if((num_primes_ <= primes_per_row) ){
                cout << setw(1) << right << i;
            }
            else{
                cout << setw(max_prime_width) << right << i;
            }
            primes--;
            if (primes==0){
                cout << endl;
                primes= primes_per_row;
            }
            else if (i != max_prime_){
                cout << " ";
            }
        }

    }
    cout << endl;
    
}

void PrimesSieve::sieve() {
    // TODO: write sieve algorithm
    // All instance variables must be initialized by the end of this method.

    /*
    sets all the integers in the is_prime_ array to true
    */
    for(int i=2; i<=limit_; i++){
        is_prime_[i]=true;
        }

/*
uses the sieve of Eratosthenes algorithm to determine the prime numbers in the a given limit
*/
    for(int i=2; i<=sqrt(limit_); i++){
        if(is_prime_[i]==true){
            for(int j=pow(i,2); j<=limit_ ; j=j+i){
                is_prime_[j]=false;
            }
        }
    }

/*
stores the number of primes in num_primes_ and stores the max_prime_ as well. 
*/
    num_primes_=0;
    for(int i=2; i<=limit_; i++){
        if(is_prime_[i]){
            num_primes_++;
            max_prime_=i;
        }
    }
}

int PrimesSieve::num_digits(int num) {
    // TODO: write code to determine how many digits are in an integer
    // Hint: No strings are needed. Keep dividing by 10.
    /*
     finds the number of digits in num by dividing the integer by 10 until
     it is less than 1. 
    */
    int space=0;
    while(num>=1){
        num=num/10;
        space ++;
    }

    return space;
}

int main() {
    cout << "**************************** " <<  "Sieve of Eratosthenes" <<
            " ****************************" << endl;
    cout << "Search for primes up to: ";
    string limit_str;
    cin >> limit_str;
    int limit;

    // Use stringstream for conversion. Don't forget to #include <sstream>
    istringstream iss(limit_str);

    // Check for error.
    if ( !(iss >> limit) ) {
        cerr << "Error: Input is not an integer." << endl;
        return 1;
    }
    if (limit < 2) {
        cerr << "Error: Input must be an integer >= 2." << endl;
        return 1;
    }
    

    // TODO: write code that uses your class to produce the desired output.
    
    PrimesSieve test= PrimesSieve(limit);
    test.display_primes();



    return 0;
}
