 /*******************************************************************************
 * Author  : Adib Osmany
 * Pledge  : I pledge my honor that I have abided by the Stevens Honor System.
 ******************************************************************************/
 #include <stdio.h>
 #include <stdlib.h>

 void display(int8_t bit) {
    putchar(bit + 48);
 }

 void display_32(int32_t num) {
    int i = 31;
    while(i>=0){
        if (((num >> i) & 1) == 0){
            display(0);
        }
        else {
            display(1);
        }
        i--;
    }
    
 }

 int main(int argc, char const *argv[]) {
      display_32(382);
      return 0;
  }