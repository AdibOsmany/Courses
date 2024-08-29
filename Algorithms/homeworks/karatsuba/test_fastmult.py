# Tester for Fast Mult
# By: Adib Osmany
# Version: 2.0
from colorama import Fore
import random
import subprocess
import sys
from timeit import default_timer as timer

sys.set_int_max_str_digits(100000)
subprocess.run(["make"])
test_num=0
correct=0

#----------------------------------------------------------------------------------------#
def bold(text):
  return "\033[1m" + text + "\033[0m"


def testFastMult(input1, input2):
    global test_num
    global correct
    answer=input1*input2
    test_num=test_num+1
    print("Test "+str(test_num)+": ", end =" ")
    start = timer()
    hold=subprocess.run(["./fastmult", str(input1), str(input2)], capture_output=True)
    end = timer()
    result=hold.stdout.decode()
    if(result==(str(answer)+"\n")):
        correct=correct+1
        print(Fore.GREEN+bold("Passed!")+Fore.RESET, end =" ")
        print("Time elapsed: "+ str(round((end-start),3)))
    
    else:
        print(Fore.RED+bold("\nWrong output for: ")+Fore.RESET)
        print(bold("Input 1: ")+str(input1))
        print("\n"+bold("Input 2: ")+str(input2))
        print("\n"+bold("Expected: "))
        print(str(answer))
        print("\n"+bold("Received: "))
        print(result)
    return
#----------------------------------------------------------------------------------------#

def random_test(limit):
    digit1=random.randint(1, limit)
    digit2=random.randint(1, limit)
    testFastMult(random.randint(int("1"+((digit1-1)*"0")),int(digit1*"9")),random.randint(int("1"+((digit2-1)*"0")),int(digit2*"9")))
    return

#----------------------------------------------------------------------------------------#

testFastMult(0,0) #1
testFastMult(random.randint(1, 9),random.randint(1, 9)) #2
testFastMult(9,9) #3
testFastMult(10,10) #4
testFastMult(random.randint(10, 99),random.randint(10, 99)) #5
testFastMult(99,99) #6
testFastMult(4, random.randint(int("1"+(17*"0")),int(18*"9"))) #7 
testFastMult(0, random.randint(int("1"+(20*"0")),int(21*"9"))) #8
random_test(19) #9
random_test(18) #10
random_test(18) #11
random_test(25) #12
random_test(30) #13
testFastMult(int("1"+(16*"0")+"5"),int("1"+(17*"0"))) #14
testFastMult(0, random.randint(int("1"+(999*"0")),int(1000*"9"))) #15 #large number times 0
testFastMult(random.randint(1, 9),random.randint(int("1"+(999*"0")),int(1000*"9"))) #16 #large number times 1 digt
testFastMult(int("6"+(900*"7")+(64*"0")+(35*"2")),random.randint(int("1"+(999*"0")),int(1000*"9"))) #17 #large number with more than 19 zeros
testFastMult(int("1"+(999*"0")),random.randint(int("1"+(999*"0")),int(1000*"9"))) #18
testFastMult(int(1000*"9"),int(1000*"9")) #19
testFastMult(int("1"+(999*"0")),int("1"+(999*"0"))) #20

for i in range(9):
    random_test(1000) #21-39

for i in range(8):
   random_test(10000) #30-37

testFastMult(int("1"+(49999*"0")),int("1"+(49999*"0"))) #38
testFastMult(random.randint(int("1"+(49999*"0")),int(50000*"9")),random.randint(int("1"+(49999*"0")),int(50000*"9"))) #39

#longest test case
testFastMult(int(50000*"9"),int(50000*"9")) #40

#----------------------------------------------------------------------------------------#
print("\nTest Cases Passed: "+str(correct)+"/"+str(test_num))


#python3 test_fastmult.py