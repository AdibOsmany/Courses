/*Adib Osmany*/
/*CS382 Lab 4*/
/*I pledge my honor that I have abided by the Stevens Honor System.*/


.text
.global _start

_start:   
    ADR X0, side_a /*The address of side_a */
    LDR X1, [X0] /*Loads X0 (side_a) to X1*/

    ADR X2, side_b /*The address of side_b */
    LDR X3, [X2] /*Loads X1 (side_b) to X3*/

    ADR X4, side_c /*The address of side_c */
    LDR X5, [X4] /*Loads X2 (side_c) to X5*/


    MUL X6, X1, X1 /*multiplies x1 with x1 to get the square of side_a*/
    MUL X7, X3, X3 /*multiplies x3 with x3 to get the square of side_b*/
    MUL X8, X5, X5 /*multiplies x5 with x5 to get the square of side_c*/

    ADD X9, X6, X7 /*adds X6 and X7 to X9 (a^2 + b^2) */
    
    CMP X9, X8 /*Compares X9 with X8 */

    B.NE NOO /*If X9 doesn't equal X8, it goes to yess*/
    B.EQ YESS /*If X9 does equals X8, it goes to noo*/

   YESS:
   MOV X0, 1 /*Destination (for printing, its value is 1)*/
   ADR X1, yes /*The address of the string for yes to be printed */
   ADR X2, len_yes /*The address of the length of yes to be printed */
   LDR X2, [X2] /*The length of the string to be printed*/
   MOV X8, 64  /*System call number (for printing, its value is 64)*/
   SVC 0       /* invoke system call */
   B exit /*Goes to exit*/

   NOO: 
   MOV X0, 1 /*Destination (for printing, its value is 1)*/
   ADR X1, no /*The address of the string for no to be printed */
   ADR X2, len_no /*The address of the length of no to be printed */
   LDR X2, [X2] /*The length of the string to be printed*/
   MOV X8, 64  /*System call number (for printing, its value is 64)*/
   SVC 0    /* invoke system call */
   B exit /*Goes to exit*/


 /* Exit Program */
    exit:            
    MOV X0, 0 /* status <- 0 */
    MOV X8, 93 /* exit() is system call #93 */
    SVC 0 /* invoke system call */

.data
    side_a:  .quad 3 /*length of a*/

    side_b:  .quad 4 /*length of b*/

    side_c:  .quad 5 /*length of c*/

    yes:     .string "It is a right triangle.\n" /*string for if c^2 is equal to a^2+b^2*/

    len_yes: .quad . - yes /*length of string yes*/

    no:      .string "It is not a right triangle.\n" /*string for if c^2 is not equal to a^2+b^2*/

    len_no:  .quad . - no /*length of string no*/
