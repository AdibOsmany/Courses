/*Adib Osmany*/
/*CS382 Lab 4*/
/*I pledge my honor that I have abided by the Stevens Honor System.*/


.text
.global _start
.extern scanf

_start:
    
    ADR   X0, fmt_str   // Load address of formated string
    ADR   X1, left      // Load &left
    ADR   X2, right     // Load &right
    ADR   X3, target    // Load &target
    BL    scanf         // scanf("%ld %ld %ld", &left, &right, &target);

    ADR   X1, left      // Load &left
    LDR   X1, [X1]      // Store left in X1
    ADR   X2, right     // Load &right
    LDR   X2, [X2]      // Store right in X2
    ADR   X3, target    // Load &target
    LDR   X3, [X3]      // Store target in X3

    CMP X1, X3 //Compares the X1 and X3
    B.LT GREAT    //the code jumps to GREAT if X3 is less than or equal to X1
    B.GE NOO  //the code jumps to NOO if X3 is greater than X3
    
    GREAT:
    CMP X2, X3 //Compares the X2 and X3
    B.GT LESS //the code jumps to GREAT if X3 is less than or equal to X1
    B.LE NOO //the code jumps to NOO if X3 is greater than X3

    LESS:
    MOV X0, 1 /*Destination (for printing, its value is 1)*/
    ADR X1, yes /*The address of the string for yes to be printed */
    ADR X2, len_yes /*The address of the length of yes to be printed */
    LDR X2, [X2] /*The length of the string to be printed*/
    MOV X8, 64  /*System call number (for printing, its value is 64)*/
    SVC 0   /* invoke system call */
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
    MOV   X0, 0        // Pass 0 to exit()
    MOV   X8, 93       // Move syscall number 93 (exit) to X8
    SVC   0            // Invoke syscall

.data
    left:    .quad     3
    right:   .quad     4
    target:  .quad     5
    fmt_str: .string   "%ld%ld%ld"
    yes:     .string   "Target is in range\n"
    len_yes: .quad     . - yes  // Calculate the length of string yes
    no:      .string   "Target is not in range\n"
    len_no:  .quad     . - no   // Calculate the length of string no
