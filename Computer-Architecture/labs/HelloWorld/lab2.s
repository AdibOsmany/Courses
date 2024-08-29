 /*******************************************************************************
 * Author  : Adib Osmany
 * Pledge  : I pledge my honor that I have abided by the Stevens Honor System.
 ******************************************************************************/
.text
.global _start
_start:
    MOV X0, 1 /*Destination (for printing, its value is 1)*/
    ADR X1, msg /*The address of the string to be printed */
    ADR X2, length /*The address of the length to be printed */
    LDR X2, [X2] /*The length of the string to be printed*/
    MOV X8, 64 /*System call number (for printing, its value is 64)*/
    SVC 0 /* invoke system call *

    /* Exit Program */
    MOV X0, 0 /* status <- 0 */
    MOV X8, 93 /* exit() is system call #93 */
    SVC 0 /* invoke system call */

.data
    msg: .string "Hello World!\n"  /*hello world string*/
    length: .quad 13 /*length of msg*/

