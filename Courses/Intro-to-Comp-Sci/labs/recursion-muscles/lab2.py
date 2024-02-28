############################################################
# Name: Adib Osmany
# Pledge: I pledge my honor that I have abided by the Stevens Honor System.
# CS 115 Lab 2
#  
############################################################

''' The dot function takes two lists and finds the dot product between them '''
def dot(L,K):
    if L==[] or K==[]:
        return 0
    else:
        result=(L[0])*(K[0])
        return result+dot(L[1:],K[1:])

'''The explode function takes a string and returns a list with the individual characters of the string '''
    
def explode(S):
    if S=="":
        return []
    else:
        return [S[0]]+explode(S[1:])

'''The ind function checks to see if any character 'e' is in 'L' which is either a list or a string.
If there is, then the function returns the number posotion in which 'e' is first found in 'L'. If it is not, then
the function returns the length of the list'''
def ind(e, L):
    if type(L)==type(''):
        L=explode(L)
    if (L==[]) or (e==L[0]) :
        return 0
    else:        
        return 1+ind(e,L[1:])

'''The remove function is only meant to be used with the removeALL function. The function checks to see if a character 'e' is in 'L',
which can either be a list or a string. If 'e' is in 'L' then the function returns the numbered position in which 'e' is found in 'L'. If it is not, then
the function returns -1'''
def remove(e, L,num=0):
    if (L==[]):
        return -1
    elif (e==L[0]):
        return num
    else:        
        return remove(e,L[1:],num=num+1)

'''The remove All function removes every character 'e' found in 'L', which is either a list or a string'''
def removeAll(e,L):
    if type(L)==type(''):
        L=explode(L)
    result=remove(e,L)
    if result>=0:
        newlist=(L[:result]+L[(result+1):])
        return removeAll(e,newlist)
    elif result==-1:
        return L

'''The myFilter function filters out a list of numbers by whatever condition is given'''
def myFilter(K,L):
    if L==[]:
        return L
    result=K(L[0])
    if result==True:
        return L[:1]+myFilter(K,L[1:])
    else:
        return myFilter(K,L[1:])


 
'''The deepReverse function takes a list and fully reverses it, as if the list was looking into a mirror'''
def deepReverse(L):
    if L==[]:
        return []
    if type(L[0])==type([]):
        return deepReverse(L[1:])+[(deepReverse(L[0]))]
    else:
        return deepReverse(L[1:])+[L[0]]
