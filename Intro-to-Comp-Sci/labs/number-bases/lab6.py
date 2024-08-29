############################################################
# Name: Adib Osmany
# Pledge: I pledge my honor that I have abided by the Stevens Honor System.
# CS 115 Lab 6
#  
############################################################
'''the isOdd function checks to wether a number is odd or even'''
def isOdd(n):
    if n%2==0:
        return False
    return True
'''
1) 00101010

2) The least significant bit of an odd number in base 2, would be a 1.
Since that bit represnts a 1, and we do need a 1 for an odd number.

    The rightmost bit of an even number in base 2 would be a 0. Since that
 it represnts a 1, and we do not need a 1 for an even number.
    

3)If you get rid of the rightmost bit, then the value of the original
number would be less now.

4)If N is odd then you woul return a 1, if it is even, you would return
0.'''


'''The numToBinary function returns the binary representation of a number'''
def numToBinary(N):
    if N==0:
        return ''
    if N==1:
        return '1'
    if (N%2)==1:
        return numToBinary(N//2)+'1'
    else:
        return numToBinary(N//2)+'0'

'''The binaryToNum function returns the number that the binary(N)
represents.'''
def binaryToNum(N,p=0):
    if N=='':
        return 0
    if N[-1]=='1':
        return (2**p)+binaryToNum(N[:-1],p=p+1)
    else:
        return binaryToNum(N[:-1],p=p+1)


'''The increment function takes a binary input and returns the binary
representation that is one value bigger'''
def increment(N):
    if N=='11111111':
        return '00000000'
    pot=binaryToNum(N)
    top=numToBinary(pot+1)
    return ('0'*(8-(len(top))))+top


'''The count function takes an 8-bit binary string as input and begins
counting up by one from the input S for n increments, printing each
number as it goes.'''
def count(S,n):
    print (S)
    S=(increment(S))
    if n!=0:
        return count(S,n-1)
'''Ternary representation for 59 is '2012' this is because 2*(3^3)=54,
0*(3^2)=0, 1*(3^1)=3, 2*(3^0)=2, 54+0+3+2=59'''


'''The numToTernary function returns the ternary representation of a number'''

def numToTernary(N):
    if N==0:
        return ''
    if N==1:
        return '1'
    if N==2:
        return '2'
    if (N%3)==1:
        return numToTernary(N//3)+'1'
    if (N%3)==2:
        return numToTernary(N//3)+'2'
    else:
        return numToTernary(N//3)+'0'
'''The ternaryToNum function returns the number that the ternary(N)
represents.'''
def ternaryToNum(N,p=0):
    if N=='':
        return 0
    if N[-1]=='1':
        return (3**p)+ternaryToNum(N[:-1],p=p+1)
    if N[-1]=='2':
        return (2*(3**p))+ternaryToNum(N[:-1],p=p+1)
    else:
        return ternaryToNum(N[:-1],p=p+1)
    
