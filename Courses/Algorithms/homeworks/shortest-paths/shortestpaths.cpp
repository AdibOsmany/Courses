/*************************************************************************************************
 * Name          : shortestpaths.cpp
 * Author        : Adib Osmany & Neel Meyyur
 * Pledge        : I pledge my honor that I have abided by the Stevens Honor System.
 * Date          : 12/05/2023
 * Description   : Solves the all pairs shortest paths problem with Floyd’s algorithm
 *************************************************************************************************/
#include <iostream>
#include <fstream>
#include <vector>
#include <cctype>
#include <string>
#include <limits.h>
#include <iomanip>
#include <utility> //for pair type
#define INF ULLONG_MAX
#define ULL unsigned long long int

using namespace std;

//The isInt function checks to see if the given string can properly be turned into an integer 
bool isInt(string x){
    for(size_t i=0; i<x.size();i++){
        if(!(isdigit(x[i]))){
            return false;
        }
    }
    return true;
}

//The has3 function checks to see if the given string can be split into three arguments.
bool has3(string x, int count){
    size_t loc=x.find(" ");
    if (loc!=string::npos){
        x=x.substr(loc+1,x.size());
        count++;
        return has3(x,count);
    }
    else{
        if (count!=2){
            return false;
        }
        else{
            return true;
        }
    }
}

//The validValue function checks to see that the arguments in the given string are what we want them to be. 
bool validValue(string x, int num, int line){
    char start=65;
    char end=64+num;
    string y;
    size_t loc=x.find(" ");
    y=x.substr(0,loc);
    if (y.size()==1){
        if(!((start<=y[0])&&(y[0]<=end))){
            cerr<<"Error: Starting vertex '"<<y<<"' on line "<<line<<" is not among valid values "<<start<<"-"<<end<<"."<<endl;
            return false;
        }
    }
    else{
        cerr<<"Error: Starting vertex '"<<y<<"' on line "<<line<<" is not among valid values "<<start<<"-"<<end<<"."<<endl;
        return false;
    }
    x=x.substr(loc+1,x.size());
    loc=x.find(" ");
    y=x.substr(0,loc);
    if (y.size()==1){
        if(!((start<=y[0])&&(y[0]<=end))){
            cerr<<"Error: Ending vertex '"<<y<<"' on line "<<line<<" is not among valid values "<<start<<"-"<<end<<"."<<endl;
            return false;
        }
    }
    else{
        cerr<<"Error: Ending vertex '"<<y<<"' on line "<<line<<" is not among valid values "<<start<<"-"<<end<<"."<<endl;
        return false;
    }
    y=x.substr(loc+1,x.size());
    if(isInt(y)){
        if(stoll(y)<=0){
            cerr<<"Error: Invalid edge weight '"<<y<<"' on line "<<line<<"."<<endl;
            return false;
        }
    }
    else{
        cerr<<"Error: Invalid edge weight '"<<y<<"' on line "<<line<<"."<<endl;
        return false;
    }
    return true;
}

// This is an implementation of floyd's algorithm. It outputs a pair of matrices. The first
// matrix is the path lengths matrix. The second matrix is the intermediate vertices matrix.
pair<vector<vector<ULL>>,vector<vector<ULL>>> floyd(vector<vector<ULL>> A){
    size_t n=A.size();
    vector<vector<ULL>> output(n);
    for(size_t i=0;i<n;i++){
        output[i].resize(n);
        fill(output[i].begin(),output[i].end(),INF);
    }
    for(size_t k=0; k<n; k++){
        for(size_t i=0; i<n; i++){
            for(size_t j=0; j<n; j++){
                if((A[k][j] != INF) && (A[i][k]!=INF)){
                    if(A[i][j]>(A[i][k]+A[k][j])){
                        A[i][j]=(A[i][k]+A[k][j]);
                        output[i][j]=k;
                    }
                }
            }
        }
    }
    return make_pair(A,output);
}

//the path function is a helper function for the display_paths function.
void path(vector<vector<ULL>> matrix, char FROM, char TO){
    while(true){
        ULL loc=matrix[FROM-65][TO-65];
        if(loc==INF){
            cout<<" -> "<<TO;
            return;
        }
        char next=loc+'A';
        if (next<TO){
            path(matrix,FROM, next);
        }
        else if ((next>TO)&&(matrix[FROM-65][next-65]!=INF)){
            path(matrix,FROM, next);
        }
        else{
            cout<<" -> "<<next;
        }
        FROM=next;
    }
    return;
}

//the display_paths function displays the shortet path from one point to another for all the points.
void display_paths(pair<vector<vector<ULL>>,vector<vector<ULL>>> matrices){
    size_t n=matrices.first.size();
    for(size_t i=0;i<n; i++){
        char FROM='A'+i;
        for(size_t j=0; j<n; j++){
            char TO='A'+j;
            cout<<FROM<<" -> "<<TO<<", distance: ";
            if(matrices.first[i][j]==INF){
                cout<<"infinity, path: none"<<endl;
            }
            else{
                cout<<matrices.first[i][j];
                cout<<", path: ";
                if(FROM==TO){
                    cout<<TO<<endl;
                }
                else{
                    cout<<FROM;
                    path(matrices.second,FROM,TO);
                    cout<<endl;
                }
            }
        }
    }
    return;
}

//The display_table function helps to display the given matrix. 
void display_table(vector<vector<ULL>> matrix, const string &label, const bool use_letters = false) {
    cout << label << endl;
    ULL max_val = 0;
    size_t num_vertices=matrix.size();
    for (size_t i = 0; i < num_vertices; i++) {
        for (size_t j = 0; j < num_vertices; j++) {
            ULL cell = matrix[i][j];
            if (cell < INF && cell > max_val) {
                max_val = matrix[i][j];
            }
        }
    }
    int max_cell_width;
    if(use_letters){
        max_cell_width=1;
    }
    else{
        max_cell_width = to_string(max_val).size();
    }
    cout << ' ';
    for (size_t j = 0; j < num_vertices; j++) {
        cout << setw(max_cell_width + 1) << static_cast<char>(j + 'A');
    }
    cout << endl;
    for (size_t i = 0; i < num_vertices; i++) {
        cout << static_cast<char>(i + 'A');
        for (size_t j = 0; j < num_vertices; j++) {
            cout << " " << setw(max_cell_width);
            if (matrix[i][j] == INF) {
                cout << "-";
            } 
            else if (use_letters) {
                cout << static_cast<char>(matrix[i][j] + 'A');
            }
            else {
                cout << matrix[i][j];
            }
        }
        cout << endl;
    }
    cout << endl;
}

//main function. This function is divided into three sections. The first section parses the given file. 
//The second section checks for any errors in the file. And the third section implements the actual code. 
int main(int argc, const char *argv[]) {
    //file parsing
    // Make sure the right number of command line arguments exist.
    if (argc != 2) {
        cerr << "Usage: " << argv[0] << " <filename>" << endl;
        return 1;
    }
    // Create an ifstream object.
    ifstream input_file(argv[1]);
    // If it does not exist, print an error message.
    if (!input_file) {
        cerr << "Error: Cannot open file '" << argv[1] << "'." << endl;
        return 1;
    }
    // Add read errors to the list of exceptions the ifstream will handle.
    input_file.exceptions(ifstream::badbit);
    string line;
    vector<string> lines;

    try {
        // Use getline to read in a line.
        while (getline(input_file, line)) {
            lines.push_back(line);
        }
        // Don't forget to close the file.
        input_file.close();
    } 
    catch (const ifstream::failure &f) {
        cerr << "Error: An I/O error occurred reading '" << argv[1] << "'.";
        input_file.close();
        return 1;
    }
    //-----------------------------------------------------------------------------------//
    int num; //number of vertices

    //error checking
    //checks that the first line is a valid number between 1 and 26
    if(isInt(lines[0])){
        num=stoll(lines[0]);
        if(!((1<=num)&&(num<=26))){
            cerr << "Error: Invalid number of vertices '" << num << "' on line 1." <<endl;
            return 1;
        }
    }
    else{
        cerr<<"Error: Invalid number of vertices '"<<lines[0]<<"' on line 1."<<endl;
        return 1;
    }

    //checks if each line has three arguments
    for (size_t i=1;i<lines.size();i++){
        if(!(has3(lines[i],0))){
            cerr<<"Error: Invalid edge data '"<<lines[i]<<"' on line "<<(i+1)<<"."<<endl;
            return 1;
        }
    }

    //checks if each line has valid values
    for (size_t i=1;i<lines.size();i++){
        if(!(validValue(lines[i],num, i+1))){
            return 1;
        }
    }
    //--------------------------------------------------------------------------------------------//
    //actual algorithm
    vector<vector<ULL>> distMatrix(num);
    for(int i=0;i<num;i++){
        distMatrix[i].resize(num);
        fill(distMatrix[i].begin(),distMatrix[i].end(),INF);
        distMatrix[i][i]=0;
    }
    for (size_t i=1;i<lines.size();i++){
        int start=(lines[i][0])-65;
        int end=(lines[i][2])-65;
        ULL dist=stoull(lines[i].substr(4,lines[i].size()));
        distMatrix[start][end]=dist;
    }
    display_table(distMatrix,"Distance matrix:");
    pair<vector<vector<ULL>>,vector<vector<ULL>>> matrices=floyd(distMatrix);
    display_table(matrices.first,"Path lengths:");
    display_table(matrices.second,"Intermediate vertices:",true);
    display_paths(matrices);
    return 0;
}
