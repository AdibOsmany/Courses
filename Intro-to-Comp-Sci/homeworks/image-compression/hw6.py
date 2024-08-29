############################################################
# Name: Adib Osmany
# Pledge: I pledge my honor that I have abided by the Stevens Honor System.
# CS 115 HW 6
#  
############################################################
# Number of bits for data in the run-length encoding format.
# The assignment refers to this as k.

COMPRESSED_BLOCK_SIZE = 5
k=COMPRESSED_BLOCK_SIZE
bits='0'*k


# Number of bits for data in the original format.
MAX_RUN_LENGTH = 2 ** COMPRESSED_BLOCK_SIZE - 1

# Do not change the variables above.
# Write your functions here. You may use those variables in your code.
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

byte=binaryToNum('1'*k)
'''The comp function is a helper function to the compress function. This
function finds out the number of consecutive 0's until the fist 1, and
vise versa'''
def comp(t,z):
    if t=='':
        return 0
    if t[0]==z:
        return 1+comp(t[1:],z)
    else:
        return 0
'''The ress function is a helper function to the compress function. This
function outputs the  base-2 number with a fixed number, k, of bits'''
def ress(l):
    if l>byte:
        return numToBinary(byte)+bits+ress(l-byte)
    else:
        return ('0'*(k-(len(numToBinary(l)))))+numToBinary(l)
'''The compress function compresses an image to a fixed number of 'k'
bits, using a run length encoding system'''
def compress(t):
    if t=='':
        return ''
    z=t[0]
    if len(t)==64:
        tiger=''
        if t[0]=='1':
            tiger=bits
    else:
        tiger=''
    top= 1+comp(t[1:],z)
    return tiger+ress(top)+compress(t[top:])
'''The uncom function is a helper function to the uncompress function.
This function deteremines the number of '0s' or '1s' in a consecutive
sequence'''
def uncom(c):
    if c=='':
        return 0
    top=binaryToNum(c[0:k])
    if c[k:(2*k)]==bits:
        return top+uncom(c[(2*k):])
    else:
        return top
'''The press function is a helper function to the uncompress function.
This function helps to determine when in the compressed file the
sequence switches from '0s' to '1s', and vise versa.'''
def press(c):
    if c=='':
        return 0
    if c[k:(2*k)]==bits:
        return (2*k)+press(c[(2*k):])
    else:
        return k
'''The uncompress function takes a compressed image and restores it to
its original image'''
def uncompress(c,n=0):
    if c=='':
        return ''
    if c[0:5]==bits:
        return ''+uncompress(c[k:],n=n+1)
    tiger=str(n%2)*uncom(c)
    lion=press(c)
    return tiger+uncompress(c[lion:],n=n+1)
'''The compression function finds the ratio of a compressed image size
to the original size'''
def compression(c):
    return (len(compress(c)))/64

'''This function just checks the ratio of a compressed image size
to the original size of three images'''
def comptest():
    assert compression("00011000"+"00111100"*3 + "01111110"+"11111111"+"00111100"+"00100100" )==1.484375
    assert compression("0"*8 + "01100110"*2 + "0"*8 + "00001000" + "01000010" + "01111110" + "0"*8 )==1.328125
    assert compression("1"*9 + "0"*7 + "10000000"*2 + "1"*7 + "0" + "00000001"*2 + "1"*7 + "0" )==1.015625
    print("ok")
##############################################################################
'''The largest number of bits the compress function could possibly use
to encode a 64-bit image is 64 bits'''

'''The tests that I conducted in the compression ratios were the pengiun
image, the smile image, the five image, and "0"*64. The penguin ratio
was 1.484375, the smile ratio was 1.328125, the five ratio was 1.015625,
the "0"*64 image ratio was 0.390625. This means all the compressed image
files were bigger than the original, except for "0"*64'''

'''Professor Lai algorithm cannot exist as there is no possible way to
account for a 64-bit image that does not follow some sort of pattern.
If there was a patternless 64 bit image, then no algorithm in the world
can compress it any farther then it is. This patternless image alone is
what makes Proffessor Lai algorithm impossible. '''
