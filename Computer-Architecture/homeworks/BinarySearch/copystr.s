/**
 * Name: Adib Osmany
 * Pledge: I pledge my honor that I have abided by the Stevens Honor System.
*/

.text
.global _start

_start:
    ADR X0, src_str //address for source string
    ADR X1, dst_str //address for destination string
    MOV X2, 0  //Stores 0 into X2 which acts as a way to increment through src_str 
    MOV X3, 1 //Stores 1 to X3

    //the iterate loop iterates through src_str and stores the elements in dst_str
    Iterate:
    LDRB W4, [X0, X2] //Loads an element of src_str to W4
    STRB W4, [X1, X2] //Stores W4 into dst_str
    ADD X2, X2, X3  //Adds 1 to X2
    CMP W4,WZR //checks if W4 is a null terminator
    B.NE Iterate // goes to print if W4 is a null terminator

    
    MOV X0, 1 /*Destination (for printing, its value is 1)*/
    MOV X8, 64 /*System call number (for printing, its value is 64)*/
    SVC 0 /* invoke system call */


    /* Exit Program */
    MOV X0, 0 /* status <- 0 */
    MOV X8, 93 /* exit() is system call #93 */
    SVC 0 /* invoke system call */



/*
 * If you need additional data,
 * declare a .data segment and add the data here
 */

