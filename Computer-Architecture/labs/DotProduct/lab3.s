/*******************************************************************************
 * Author  : Adib Osmany
 * Pledge  : I pledge my honor that I have abided by the Stevens Honor System.
 ******************************************************************************/
.text
.global _start
_start:
    LDR X0, =vec1 /* Loads vector 1 into X0 */ 
    LDR X1, =vec2 /* Loads vector 2 into X1 */ 
    LDR X2, =dot /* Loads dot into X2 */ 

    LDR X3, [X0] /* Loads the first element of vector 1 into X3 */ 
    LDR X4, [X1] /* Loads the first element of vector 2 into X4 */ 
    MUL X5, X3, X4 /* Multiplies X3 and X4 and put it in X5 */

    LDR X6, [X0, 8] /* Loads the second element of vector 1 into X6 */ 
    LDR X7, [X1, 8] /* Loads the second element of vector 2 into X7 */ 
    MUL X8, X6, X7 /* Multiplies X6 and X7 and put it in X8 */

    LDR X9, [X0, 16] /* Loads the third element of vector 1 into X9 */ 
    LDR X10, [X1, 16] /* Loads the third element of vector 2 into X10 */ 
    MUL X11, X9, X10 /* Multiplies X9 and X10 and put it in X11 */

    ADD X12, X5, X8 /* Adds X5 and X8 and puts it in X12 */
    ADD X13, X12, X11 /* Adds X12 and X11 and puts it in X13 */
    STR X13, [X2] /* Stores X13 into X2 which is the dot product*/

    /* Exit Program */
    MOV X0, 0 /* status <- 0 */
    MOV X8, 93 /* exit() is system call #93 */
    SVC 0 /* invoke system call */

.data
vec1: .quad 10, 20, 30 
vec2: .quad 1, 2, 3
dot: .quad 0
  