/**
 * Name: Adib Osmany 
 * Pledge: I pledge my honor that I have abided by the Stevens Honor System.
 */

.text
.global _start

_start:
ADR X0, numstr //address of numstr
ADR X1, number //address for number
MOV X3, 0 //Stores 0 into X3 which acts as a way to increment through numstr 
MOV W5, 48 //Stores 48 into W5 
MOV X6, 0 //Stores 0 into X6 which will act as a way to check in the program is done converting a string to number
MOV X7, 1 //Stores 1 into X7 which will act as a way to determine how many decimal places to the left we need to move an integer
MOV X9, 0 //Stores 0 into X9 which acts as the total to store into number
MOV X10, 10 //stores 10 in X10


//loop to count how many digits there are
Counter: 
LDRB W4, [X0,X3] //Loads an element of numstr into W4
CMP W4,WZR //checks if W4 is a null terminator
B.NE increase // goes to increase if W4 is not a null terminator
MOV X11, X3 //Stores the value of X3 into X11
B TenLoopCon //goes to TenLoopCon

//part of counter loop, increases X3
increase:
ADD X3, X3, 1 //adds 1 to X3
B Counter //goes to counter

//loops that determine highest degree to multiply the number by
TenLoopCon:
CMP X11, 1 //compares X11 with 1
B.NE TenLoop //if X11 is not 1, it goes to TenLoop
B.EQ MainLOOP //if X11 is 1, it goes to MainLOOP

//loop to determine how many decimal places to the left we need to move the first integer
TenLoop:
MUL X7, X7, X10 //multiplies 10 to X7
SUB X11, X11, 1 //subtracts 1 from X11
B TenLoopCon //goes to TenLoopCon

//Loop that copies the numbers from numstr to number succesfully. 
MainLOOP:
LDRB W8, [X0,X6] //loads an element from numstr to W8
SUB W8, W8, W5 //subtracts 48 from W8
STRB W8, [X1] //stores W8 into number
LDR X8, [X1] //loads number into X8

MUL X8, X8, X7 //multiplies X8 by X7 and stores it in X8
 
ADD X9, X9, X8 //Adds X8 to X9 which is the total
ADD X6, X6, 1 //Adds 1 to X6
CMP X6, X3 //checks if X6 is equal to X3
B.NE con //If X6 is not equal to X3, then it goes to con
STR X9, [X1] //stores X9 into number
 
B exit //goes to exit

//loop that divides X7 by 10
con:
UDIV X7, X7, X10 //divides X7 by 10
B MainLOOP //goes to MainLoop



/* Do not change any part of the following code */
exit:
    MOV  X0, 1
    ADR  X1, number
    MOV  X2, 8
    MOV  X8, 64
    SVC  0
    MOV  X0, 0
    MOV  X8, 93
    SVC  0
    /* End of the code. */


/*
 * If you need additional data,
 * declare a .data segment and add the data here
 */







