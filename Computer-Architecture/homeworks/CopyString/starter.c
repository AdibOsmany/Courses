
#include <stdio.h>

/*******************************************************************************
 * Author  : Adib Osmany
 * Pledge  : I pledge my honor that I have abided by the Stevens Honor System.
 
 Sorting Algorithm for Task 3: Selection Sort
 I would like to be considered for the bonus points on task 3. 
 ******************************************************************************/


/*
The copy_str fucntion takes a string and copys all the characters into another variable. 
It goes through a loop to get the individual characters and store it into the other variable.
*/
void copy_str(char* src, char* dst) {
    /* Your code here */
    int x=0; //int x helps get the elements of the src and dst array
    L1: if(src[x]!='\0'){
        dst[x]=src[x];
        x++;
        goto L1; //goes back to L1
    }
    else{   
    dst[x] = '\0'; //this helps let dst know when to end.
    }
}

/*
The dot product function returns the dot product of two vectors. 
The code goes through a loop to get the respective individual numbers from the vectors 
and multiplies them together, and adds that to a total, which is what gets returned. 
*/
int dot_prod(char* vec_a, char* vec_b, int length, int size_elem) {
    /* Your code here
       Do not cast the vectors directly, such as
       int* va = (int*)vec_a;
    */
   int total=0; //total represents the dot product of vec_a and vec_b

   L2: if (length!=0){
   int a=*(int*)(vec_a) >> ((4 - size_elem) * 8); //gets the individual number in vec_a
   int b=*(int*)(vec_b) >> ((4 - size_elem) * 8); //gets the individual number in vec_b
   //TA Rudolph Sedlin helped me with the casting part for (a) vec_a and (b) vec_b. 
   total=total+(a*b);
   vec_a=vec_a+size_elem; //iterates through vec_a
   vec_b=vec_b+size_elem; //iterates through vec_b
   length--;
   goto L2;
   }

   return total; // Please modify this line as needed
}

/*
The swap function is a helper function for selection sort algorithm.
It swaps two elements in an array. 
*/

void swap(char *first, char *second){
    char hold = *first; //hold first so that we still have the value to work with
    *first = *second; //swaps first with second
    *second = hold; //swaps second with first
}


/*
The selection sort function is the sorting algorithm I used for sort_nib.
It takes the smallest element from an array and places it in the front. 

I would like to be considered for the bonus points on task 3. 
*/
void selectionSort(char* array, int size){

int small;
int i=0;
loop: //this loop goes through to the elements in the array
if(i< size-1){
    small=i;
    int j = i+1;
    sorting: //this loops checks to see if the selected element is smaller than the next elements
     if( j < size){
        if (array[j] < array[small]){
            small = j;
        }
        j++;
        goto sorting;
     }
     swap(&array[small], &array[i]); //swaps two elements in an array
     i++;
     goto loop;
}
}



/*
The sort_nib function takes an array of hexidecimels and takes the individual numbers
and sorts them from smallest to largest and stores them back into the array as new hexidecimals. 
The code is split into three parts. The first parts extracts the individual numbers and stores them in an 
array. The second part sorts that array from smallest to biggest using the selection sort algorithm. 
The third part takes the individual numbers from the array and turns them back into hexidecimals, which then
replaces the hexidecimals from the orignal array of hexidecimals. 
*/

void sort_nib(int* arr, int length) {
    /* Your code here */
    int nibSize= length*8; //is the size of the nib array
    int shift=28; //shift represents how many bytes we are shifting, it starts at 28 because that is the max that we will shift
    int position=0; //helps the elements of arr
    char nibs[nibSize]; //creates a char array
    int count=0; //helps go throguh the nibs array
    int current;  //temporary int to help the individual number in a hexidecimal 
    int base=0x0000000f;


    //this section extracts the individual numbers in the hexidecimals and stores it in nibs
    L3: //this loop goes through arr
    if(position!=length){
        bit_wise: //this loop goes through the individual numbers of a hexidecimal
        if(shift >= 0){
            current=arr[position];
            current=current>>shift;
            current = current<<28;
            current = current>>28;
            current= current&base;
            nibs[count]=current;
            shift=shift-4;
            count++;
            goto bit_wise;
        }
        else{
            shift=28;
            position++;
            goto L3;
        }
    }
    
    //-------------------------------------------------------//

    //this section sorts the nibs array with the selection sort algorithm

    selectionSort(nibs, nibSize); 
    

    //-------------------------------------------------//


    //this section puts everyhing back in order from smallest to largest. 
    int store=0x00000000; //this is the start of the hexidecimal we are creating. 
    position=0;
    shift=28;
    count=0;
    storing: //this loop goes through the arr
    if(position!=length){
        inOrder: //this loops goes through nibs and creats the hexidecimals, and stores them into arr
        if(shift >= 0){
            store=store|(nibs[count]<<shift);
            shift=shift-4;
            count++;
            goto inOrder;
        }
        else{
            arr[position]=store;
            shift=28;
            position++;
            store=0x00000000;
            goto storing;
        }
    }
}






/*
The main function tests all  the functions. 
*/

int main(int argc, char** argv) {

    /**
    * Task 1
    */

    char str1[] = "382 is the best!";
    char str2[100] = {0};

    copy_str(str1,str2);
    puts(str1);
    puts(str2);

    /**
    * Task 2
    */

    int vec_a[3] = {12, 34, 10};
    int vec_b[3] = {10, 20, 30};
    int dot = dot_prod((char*)vec_a, (char*)vec_b, 3, sizeof(int));

    printf("%d\n",dot);

    /**
    * Task 3
    */

    int arr[3] = {0x12BFDA09, 0x9089CDBA, 0x56788910};
    sort_nib(arr, 3);
    for(int i = 0; i<3; i++) {
        printf("0x%08x ", arr[i]);
    }
    puts(" ");

    return 0;
}

