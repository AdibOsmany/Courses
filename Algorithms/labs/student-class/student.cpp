/*******************************************************************************
 * Filename: student.cpp
 * Author  : Adib Osmany
 * Version : 1.0
 * Date    : September 15, 2023
 * Description: Creates a student object with the students name, gpa, and id number.
 * Pledge  : I pledge my honor that I have abided by the Stevens Honor System.
 ******************************************************************************/
#include <iostream>
#include <iomanip>
#include <vector>
using namespace std;

class Student {
private:
    string first_;
    string last_;
    float gpa_;
    int id_;

public:
/*
Student constructor with initializer list.
*/
    Student(string x, string y, float z, int a) : first_{x}, last_{y}, gpa_{z}, id_{a} { }
    
    /*
    students full name getter function;
    */
    string full_name() const{
        return first_+" "+last_;
    }

    /*
    student id number getter function.
    */
    int id() const{
        return id_;
    }

    /*
    student gpa getter function.
    */
    float gpa() const{
        return gpa_;
    }

    /*
    function that prints all of the students info
    */
    void print_info() const{
        cout << full_name() << ", GPA: "<< fixed << setprecision(2) << gpa() << ", ID: " << id() << endl;
    }


};

vector<Student> find_failing_students(const vector<Student> &students) {
    vector<Student> failing_students;
    // Iterates through the students vector, appending each student whose gpa is
    // less than 1.0 to the failing_students vector.
    for (size_t i=0; i<students.size(); i++){
        if(students[i].gpa() < 1.0){
            failing_students.push_back(students[i]);
        }
    }
    
    return failing_students;
}

void print_students(const vector<Student> &students) {
// Iterates through the students vector, calling print_info() for each student.
    for (size_t i=0; i<students.size(); i++){
        students[i].print_info();
    }
}


int main() {
    string first_name, last_name;
    float gpa;
    int id;
    char repeat;
    vector<Student> students;
    do {
        cout << "Enter student's first name: ";
        cin >> first_name;
        cout << "Enter student's last name: ";
        cin >> last_name;
        gpa = -1;
        while (gpa < 0 || gpa > 4) {
            cout << "Enter student's GPA (0.0-4.0): ";
            cin >> gpa;
        }
        cout << "Enter student's ID: ";
        cin >> id;
        students.push_back(Student(first_name, last_name, gpa, id));
        cout << "Add another student to database (Y/N)? ";
        cin >> repeat;
    } 
    while (repeat == 'Y' || repeat == 'y');
    cout << endl << "All students:" << endl;
    print_students(students);
    cout << endl << "Failing students:";
    // TODO
    // Print a space and the word 'None' on the same line if no students are failing.
    // Otherwise, print each failing student on a separate line.
    vector<Student> failing = find_failing_students(students);
  
    if(failing.empty()){
        cout << " None" << endl;
    }
    else{
        cout << endl;
        print_students(failing);
    } 

    return 0;
}