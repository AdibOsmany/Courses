/**
 * Name: Adib Osmany 
 * Pledge: I pledge my honor that I have abided by the Stevens Honor System.
*/


.text
.global _start


_start:
  ADR X0, arr //address of arr
  ADR X1, length //address of length
  LDR X1, [X1] //loads X1 into X1
  ADR X2, target//address of target
  LDR X2, [X2] //loads X2 into X2
  MOV X3, X1 //Stores the value of X1 into X3, representing the highest value
  MOV X4, 0 //Stores 0 into X4, representing the lowest value
  MOV X8, 8 //Stores 8 into X8
  MOV X10, 2 //Stores 2 into X10



  Loops:
  CMP X4, X3 //compares X4 and X3
  B.GE NOO //If X4 is greater than or equal to X3 then go to NOO
  ADD X5, X4, X3 //Adds X4 and X3 and stores it in X5
  UDIV X5, X5, X10 //divides X5 by 2
  MUL X5, X5, X8 //multiplies X5 with X8
  LDR X6, [X0, X5] //Loads an element of X0 to X6
  UDIV X5, X5, X8 //divides X5 by 8
  CMP X2, X6 //compares X2 and X6
  B.EQ YESS //if X6 and X2 are equall, then go to YESS
  B.GT greater //If X2 is greater than X6, go to greater
  B.LT lower //If X2 is less than X6, go to lower

  greater:
  MOV X4, X5 //stores the value of X5 to X4
  ADD X4, X4, 1 //adds 1 to X4
  B Loops //goes to loop


  lower:
  MOV X3, X5 //stores the value of X5 to X3
  B Loops //goes to loop



  YESS:
   MOV X0, 1 /*Destination (for printing, its value is 1)*/
   ADR X1, msg1 /*The address of the string for msg1 to be printed */
   MOV X2, 24  /*The length of the string to be printed*/
   MOV X8, 64  /*System call number (for printing, its value is 64)*/
   SVC 0    /* invoke system call */
   B exit /*Goes to exit*/

   NOO: 
   MOV X0, 1 /*Destination (for printing, its value is 1)*/
   ADR X1, msg2 /*The address of the string for msg2 to be printed */
   MOV X2, 30  /*The length of the string to be printed*/
   MOV X8, 64  /*System call number (for printing, its value is 64)*/
   SVC 0    /* invoke system call */
   B exit /*Goes to exit*/


 /* Exit Program */
    exit:            
    MOV X0, 0 /* status <- 0 */
    MOV X8, 93 /* exit() is system call #93 */
    SVC 0 /* invoke system call */





/*
 * If you need additional data,
 * declare a .data segment and add the data here
 */