############################################################
# Name: Adib Osmany
# Pledge: I pledge my honor that I have abided by the Stevens Honor System.
# CS 115 HW 3
#  
############################################################

##############################################################################################################

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
' PROBLEM 0
' Implement the function giveChange() here:
' See the PDF in Canvas for more details.
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

'''The remove function is a helper function to the removeALL function. The function checks to see if a character 'e' is in 'L. If 'e' is in 'L' then the function returns the numbered position in which 'e' is found in 'L'. If it is not, then
the function returns -1'''
def remove(e, L,num=0):
    if (L==[]):
        return -1
    elif (e==L[0]):
        return num
    else:        
        return remove(e,L[1:],num=num+1)

'''The remove All function removes every character 'e' found in 'L' '''
def removeAll(e,L):
    result=remove(e,L)
    if result>=0:
        newlist=(L[:result]+L[(result+1):])
        return newlist
    elif result==-1:
        return L
    
'''The sortmini function sorts a list from smallest to largest.'''
def sortmini(L):
    if L==[]:
        return []
    else:
        maxi=min(L)
        newlist=removeAll(maxi,L)
        return [maxi]+sortmini(newlist)
    
'''The change function gives you the smallest possible list of coins you can get when recieving your change.
It uses a use-it-or-lose-it strategy to solve the problem'''
def change(amount, coins):
    if amount==0:
        return []
    elif coins==[]:
        return [float("inf")]*amount
    elif coins[-1]==amount:
        return [coins[-1]]
    elif coins[-1]>amount:
        return change(amount, coins[:-1])
    else:
        result=amount//coins[-1]
        pop=result*coins[-1]
        useit= ([coins[-1]]*result)+(change(amount-pop, coins[:-1]))
        loseit= change(amount, coins[:-1])
        if (min(len(useit),len(loseit)))== len(useit):
            return useit
        else:
            return loseit

'''The giveChange function returns a list that tells you how many coins you will be getting for your change, and what those coins are.'''
def giveChange(amount,coins):
    top= sortmini(coins)
    pot=change(amount,top)
    return [len(pot)]+[pot]




######################################################################################################################
# your code goes here

# Here's the list of letter values and a small dictionary to use.
# Leave the following lists in place.
scrabbleScores = \
   [ ['a', 1], ['b', 3], ['c', 3], ['d', 2], ['e', 1], ['f', 4], ['g', 2],
     ['h', 4], ['i', 1], ['j', 8], ['k', 5], ['l', 1], ['m', 3], ['n', 1],
     ['o', 1], ['p', 3], ['q', 10], ['r', 1], ['s', 1], ['t', 1], ['u', 1],
     ['v', 4], ['w', 4], ['x', 8], ['y', 4], ['z', 10] ]

Dictionary = ['a', 'am', 'at', 'apple', 'bat', 'bar', 'babble', 'can', 'foo',
              'spam', 'spammy', 'zzyzva']

######################################################################################################################
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
' PROBLEM 1
' Implement wordsWithScore() which is specified below.
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
'''the letterScore function takes a letter as input and outputs the value associated with that letter'''
def letterScore(letter, scorelist):
    if letter=='':
        return 0
    elif scorelist==[]:
        return []
    elif letter==scorelist[0][0]:
        return scorelist[0][1]
    else:
        return letterScore(letter,scorelist[1:])

'''the wordScore function takes a word and adds up the individual values associated with the individual characters of that word'''
def wordScore(S, scorelist):
    if S=='':
        return 0
    elif scorelist==[]:
        return []
    return letterScore(S[0],scorelist) + wordScore(S[1:],scorelist)

'''List of words in dct, with their Scrabble score.

    Assume dct is a list of words and scores is a list of [letter,number]
    pairs. Return the dictionary annotated so each word is paired with its
    value. For example, wordsWithScore(Dictionary, scrabbleScores) should
    return [['a', 1], ['am', 4], ['at', 2] ...etc... ]
    '''
    #return None  # your code goes here

'''The wordsWithScore function takes a list of words and returns a list filled with the values of those words'''

def wordsWithScore(dct, scores):
    if dct==[]:
        return []
    return [[dct[0]]+[wordScore(dct[0],scores)]]+ wordsWithScore(dct[1:],scores)
    


###################################################################################################
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
' PROBLEM 2
' For the sake of an exercise, we will implement a function
' that does a kind of slice. You must use recursion for this
' one. Your code is allowed to refer to list index L[0] and
' also use slice notation L[1:] but no other slices.
' (Notice that you cannot assume anything about the length of the list.)
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

'''Returns the list L[0:n], assuming L is a list and n is at least 0.'''
def take(n, L):
    if n==0:
        return []
    if L==[]:
        return L
    else:
        return [L[0]]+take(n-1,L[1:])

##################################################################################################

'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
' PROBLEM 3
' Similar to problem 2, will implement another function
' that does a kind of slice. You must use recursion for this
' one. Your code is allowed to refer to list index L[0] and
' also use slice notation L[1:] but no other slices.
'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

'''Returns the list L[n:], assuming L is a list and n is at least 0.'''
def drop(n, L):
    if n != 0:
        return drop(n-1,L[1:])
    return L
    
    
    


