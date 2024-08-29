/*******************************************************************************
 * Name        : waterjugpuzzle.cpp
 * Author      : Adib Osmany & Neel Meyyur
 * Date        : 10/13/2023
 * Description : Solves water jug puzzle.
 * Pledge      : I pledge my honor that I have abided by the Stevens Honor System.
 ******************************************************************************/
#include <iostream>
#include <sstream>
#include <queue>
#include <utility> //for pair type
#include <stack>

using namespace std;

// Struct to represent state of water in the jugs.
struct State {
    int a, b, c;
    string directions;
    State *parent; 

    //State constructor
    State(int _a, int _b, int _c, string _directions) : 
        a{_a}, b{_b}, c{_c}, directions{_directions}, parent{nullptr} { }

    //State constructor
    State(int _a, int _b, int _c, string _directions, State *par) : 
        a{_a}, b{_b}, c{_c}, directions{_directions}, parent{par} { }

    
    
    // String representation of state in tuple form.
    string to_string() {
        
        ostringstream oss;
        oss << directions<<". (" << a << ", " << b << ", " << c << ")";
        return oss.str();
    }
};

//the checkGoal function determines if jugs a,b,c in both the current and goal states are equal, returns true if they, returns false if they are not.
bool checkGoal(State current, State goal){
    bool check_a=false;
    bool check_b=false;
    bool check_c=false;
    if(current.a==goal.a){
        check_a=true;
    }
    if(current.b==goal.b){
        check_b=true;
    }
    if(current.c==goal.c){
        check_c=true;
    }
    return (check_a && check_b && check_c);
}

//the shift function moves the water from the jugs properly. It returns a pair with the jug that was poured from and the jug that was poured into.
pair<int, int> shift(int jug, int move, int cap){
        if((jug-(cap-move))<0){ //checks to make sure the jug doesnt have negative gallons of water
            move+=jug;
            jug=0;
        }
        else{
            jug=jug-(cap-move);
            move=cap;
        }
        return make_pair(jug,move); //returns the pair
        
}

//the transfer function checks if two jugs can be poured into each other. Returns true if yes, and false if no.
bool transfer(int jug, int move, int cap){
    return (!((jug==0)||(move==cap)));
}

//the backTrack function backtracks the given state until the initial state using recursion. Returns a string of the string representation of each state.
string backTrack(State current){
    if(current.parent==nullptr){
        return current.to_string();
    }
    else{
        
        return backTrack(*current.parent)+"\n"+current.to_string();
    }
}

//the visit function checks if the current state has already been visited in BFS. Returns true if not visited, and false if visited.
bool visit(State current, stack<State> visited){
    while(!(visited.empty())){
        if(checkGoal(current, visited.top())){
            return false;
        }
        visited.pop();
    }
    return true;
}

//The BFS function implements the BFS algorithm to find the steps for the water jug puzzle. Returns a string.
string bfs(State current, State max, State goal){
    queue<State> store; //the queue used for bfs
    store.push(current);
    pair<int,int> movement;
    int amount;
    stack<State> visited; //the stack used to check if a state was already visited.
   
   //while loop that stops when the queue is empty
    while(!(store.empty())){
        current=(store.front());
        store.pop();
        
        if(checkGoal(current,goal)){ //checks if current equals, if so it goes to backTrack.
            return backTrack(current);
        }
        if(visit(current, visited)){
            visited.push(current);
            
            //the following lines of code pour the current state into 6 different other states, if they can be poured. 

            //C to A
            if(transfer(current.c,current.a,max.a)){ //checks if the jugs are transferable from C to A
                movement=shift(current.c,current.a,max.a);
                amount=(current.c-movement.first);
                string plural="";
                if(amount>1){
                    plural="s";
                }
                store.push(State(movement.second, current.b, movement.first, "Pour "+to_string(amount)+" gallon"+plural+" from C to A",&visited.top()));
                
            }
            //B to A
            if(transfer(current.b,current.a,max.a)){//checks if the jugs are transferable from B to A
                movement=shift(current.b,current.a,max.a);
                amount=(current.b-movement.first);
                string plural="";
                if(amount>1){
                    plural="s";
                }
                store.push(State(movement.second, movement.first, current.c, "Pour "+to_string(amount)+" gallon"+plural+" from B to A",&visited.top()));
                
              
            }
            //C to B
            if(transfer(current.c,current.b,max.b)){ //checks if the jugs are transferable from C to B
                movement=shift(current.c,current.b,max.b);
                amount=(current.c-movement.first);
                string plural="";
                if(amount>1){
                    plural="s";
                }
                store.push(State(current.a, movement.second, movement.first, "Pour "+to_string(amount)+" gallon"+plural+" from C to B",&visited.top()));
        
              
              
            }
            //A to B
            if(transfer(current.a,current.b,max.b)){ //checks if the jugs are transferable from A to B
                movement=shift(current.a,current.b,max.b);
                amount=(current.a-movement.first);
                string plural="";
                if(amount>1){
                    plural="s";
                }
                store.push(State(movement.first, movement.second, current.c, "Pour "+to_string(amount)+" gallon"+plural+" from A to B",&visited.top()));
    
                
              
            }
            //B to C
            if(transfer(current.b,current.c,max.c)){ //checks if the jugs are transferable from B to C
                movement=shift(current.b,current.c,max.c);
                amount=(current.b-movement.first);
                string plural="";
                if(amount>1){
                    plural="s";
                }
                store.push(State(current.a, movement.first, movement.second, "Pour "+to_string(amount)+" gallon"+plural+" from B to C",&visited.top()));
                
            }
            //A to C
            if(transfer(current.a,current.c,max.c)){ //checks if the jugs are transferable from A to C
                movement=shift(current.a,current.c,max.c);
                amount=(current.a-movement.first);
                string plural="";
                if(amount>1){
                    plural="s";
                }
                store.push(State(movement.first, current.b, movement.second, "Pour "+to_string(amount)+" gallon"+plural+" from A to C",&visited.top()));
               
            }   
        }
        
    }
    return "No solution.";
    //returns no solution if the queue is empty.
}

//the errorCheck function checks for any errors, returns true is there are, and false if there arent. 
bool errorCheck(string &first_arg, string &second_arg, int &cap, int &goal, istringstream &iss){
    iss.str(first_arg);
    if((!(iss>>cap)) || (cap<=0)){ //checks if the jugs have valid capacity
        cerr << "Error: Invalid capacity '"<<first_arg<<"' for jug ";
        return true;
    }
    iss.clear();
    iss.str(second_arg);
    if((!(iss>>goal)) || (goal<0)){ //checks if the goals are valid. 
        cerr << "Error: Invalid goal '"<<second_arg<<"' for jug ";
        return true;
    }
    iss.clear();
    return false;
}


//the main function checks for any errors in the command argument line. If there are, it outputs an error; if not, then it proceeds to the BFS algorithm and outputs its return. 
int main(int argc, char * const argv[]) {
    int cap_a=0; //capacity for jug a
    int cap_b=0; //capacity for jub b
    int cap_c=0; //capacity for jub c
    int goal_a=0; //goal for jub a
    int goal_b=0;//goal for jub b
    int goal_c=0;//goal for jub c
    string first_arg; //string for jug capacity
    string second_arg; //string for jug goal
    istringstream iss;
    if(argc == 7){ 
        first_arg=argv[1];
        second_arg=argv[4];
        if(errorCheck(first_arg,second_arg,cap_a,goal_a,iss)){ //checks for any errors for jug a
            iss.clear();
            cerr<<"A."<<endl;
            return 1;
        }
        first_arg=argv[2];
        second_arg=argv[5];
        if(errorCheck(first_arg,second_arg,cap_b,goal_b,iss)){ //checks for any errors for jug b
            iss.clear();
            cerr<<"B."<<endl;
            return 1;
        }
        first_arg=argv[3];
        second_arg=argv[6];
        if(errorCheck(first_arg,second_arg,cap_c,goal_c,iss)){ //checks for any errors for jug c
            iss.clear();
            cerr<<"C."<<endl;
            return 1;
        }
        if(goal_a>cap_a){ //checks if the goal for jug a is feasible
            cerr<<"Error: Goal cannot exceed capacity of jug A."<<endl;
            return 1;
        }
        if(goal_b>cap_b){ //checks if the goal for jug b is feasible
            cerr<<"Error: Goal cannot exceed capacity of jug B."<<endl;
            return 1;
        }
        if(goal_c>cap_c){ //checks if the goal for jug c is feasible
            cerr<<"Error: Goal cannot exceed capacity of jug C."<<endl;
            return 1;
        }
        if((goal_a+goal_b+goal_c) != cap_c){ //checks if all the jug goals together are equal to the capacity of jug c.
            cerr<<"Error: Total gallons in goal state must be equal to the capacity of jug C."<<endl;
            return 1;
        }
    }
    else{ //if the command line argument is what we want it to be
        cerr << "Usage: " << argv[0] << " <cap A> <cap B> <cap C> <goal A> <goal B> <goal C>" << endl;
        return 1;
    }
   
    
    State head=State(0,0,cap_c, "Initial state"); //initial state.
    State max=State(cap_a,cap_b,cap_c, "max"); //state of the max capacity of each jug.
    State goal=State(goal_a,goal_b,goal_c, "goal"); //state of the goal capacity of each jug. 
    string output=bfs(head, max, goal); //string of the return of BFS

    cout<<output<<endl; //prints the output


    return 0;
}
