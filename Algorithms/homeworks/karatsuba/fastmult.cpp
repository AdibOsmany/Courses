/*******************************************************************************
 * Name          : fastmult.cpp
 * Author        : Adib Osmany
 * Pledge        : I pledge my honor that I have abided by the Stevens Honor System.
 * Date          : 11/13/2023
 * Description   : Implements the Karatsuba algorithm.
 ******************************************************************************/
#include <iostream>
#include <cstring>
#include <string>
using namespace std;

void pad(string& a, string& b);
void unpad(string& a);

//The add function takes in two numbers in the form of strings and returns the sum of the two in string form as well.
string add(string& a, string& b){
    pad(a,b);
    int len=a.size();
    int carry=0;
    string output;
    string temp;
    int j=18;
    int temp_size;
    for(int i=len; i>0;i-=18){
        if(i<18){
            j=i;
        }
        temp=to_string(stoull(a.substr(i-j, j))+stoull(b.substr(i-j, j))+carry);
        temp_size=temp.size();
        if(temp_size>j){
            carry=1;
            output.insert(0,temp.substr(1, temp_size-1));
        }
        else{
            temp.insert(0,string(j-temp_size,'0'));
            carry=0;
            output.insert(0,temp);
        }
    }
    if(carry==1){
        output.insert(0,"1");
    }
    unpad(output);
    return output;
}

//The subtract function takes in two numbers in the form of strings and returns the difference of the two in string form as well.
string subtract(string& a, string& b) {
    pad(a,b);
    int len=a.size();
    int carry=0;
    string output;
    string temp;
    int j=18;
    long long int a_num;
    long long int b_num;
    string a_hold;
    int temp_size;
    for(int i=len; i>0;i-=18){
        if(i<18){
            j=i;
        }
        a_hold=a.substr(i-j, j);
        a_num=stoll(a_hold)-carry;
        b_num=stoll(b.substr(i-j, j));
        if(b_num>a_num){
            a_num+=stoll(("1"+string(a_hold.size(),'0')));
            carry=1;
        }
        else{
            carry=0;
        }
        temp=to_string(a_num-b_num);
        temp_size=temp.size();
        if(temp_size<j){
            temp.insert(0,string(j-temp_size,'0'));
        }
        output.insert(0,temp);
    }
    return output;
}

//The mult_ten function takes a number in the form of a string and pad a given number of 0's to the end of the string.
string mult_ten(const string& a, const int& b) {
    return a+string(b,'0');
}

//The pad function makes sures two given strings have the same length
void pad(string& a, string& b){
    int a_size=a.size();
    int b_size=b.size();
    if(a_size>b_size){
        b.insert(0,string(a_size-b_size,'0'));
    }
    else if(b_size>a_size){
        a.insert(0,string(b_size-a_size,'0'));
    }
}

//The unpad function removes any leading 0's from the given string
void unpad(string& a){
    size_t location=a.find_first_not_of("0");
    if(location != string::npos){
        a.erase(0,location);
    }
    else{
        a="0";
    }
}

//The karatsuba function implemets the karatsuba algorithm (which I got from lecture 24). 
string karatsuba(string a, string b){
    pad(a, b);
    int len=a.size();
    if(len==1){
        return to_string(stoi(a)*stoi(b));
    }
    else if(len%2){
        a.insert(0,"0");
        b.insert(0,"0");
        len++;
    }
    int mid=len/2;
    string a1=a.substr(0,mid);          //a1
    string a0=a.substr(mid,mid);        //a0
    string b1=b.substr(0,mid);          //b1
    string b0=b.substr(mid,mid);        //b0

    string c0=karatsuba(a0,b0);                             //co=a0*b0
    string c1=karatsuba(add(a1,a0), add(b1,b0));            //c1=(a1+a0)*(b1+b0)
    string c2=karatsuba(a1,b1);                             //c2=a1*b1

    string beginning=mult_ten(c2,len);                      //c2*B^n

    string first=subtract(c1, c2);
    string middle= mult_ten((subtract(first, c0)), mid);    //(c1-c2-c0)*B^(n/2)

    string c12=add(beginning,middle);  
    string end=add(c12, c0);                                //(a*b)=(c2*B^n)+((c1-c2-c0)*B^(n/2))+c0
    return end;                                             //return result
}

int main(int argc, char *argv[]) {
    string a=argv[1];
    string b=argv[2];
    string output=karatsuba(a,b);
    cout<<output<<endl;
    return 0;
} 