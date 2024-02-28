############################################################
# Name: Adib Osmany
# Pledge: I pledge my honor that I have abided by the Stevens Honor System.
# CS 115 HW 2
#  
############################################################
import sys
from functools import reduce
# Be sure to submit hw2.py.  Remove the '_template' from the file name.
 
# Allows up to 10000 recursive calls.
# The maximum permitted limit varies from system to system.
sys.setrecursionlimit(10000)
 
# Leave the following lists in place.
scrabbleScores = \
   [ ['a', 1], ['b', 3], ['c', 3], ['d', 2], ['e', 1], ['f', 4], ['g', 2],
     ['h', 4], ['i', 1], ['j', 8], ['k', 5], ['l', 1], ['m', 3], ['n', 1],
     ['o', 1], ['p', 3], ['q', 10], ['r', 1], ['s', 1], ['t', 1], ['u', 1],
     ['v', 4], ['w', 4], ['x', 8], ['y', 4], ['z', 10] ]
 
Dictionary = ['a', 'am', 'at', 'apple', 'bat', 'bar', 'babble', 'can', 'foo', 'spam', 'spammy', 'zzyzva']



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


'''func12 is a helper function for function scoreList. it tests to see if the letters in a word are the same as the letters in a rack of letters '''
def func12(word, rack):
  if word == "":
    return True
  
  if word[0] in rack:
    tiger = ind(word[0], rack)
    return func12(word[1:], (rack[0: tiger] + rack[tiger+ 1:]))
  else:
    return False


''' the scoreList function returns a list of possible words that can be made from the letters in a rack, it also returns the associated values of those letters'''
def scoreList(rack):
  pot = filter(lambda x : func12(x, rack), Dictionary)
  top = map(lambda x: [x, wordScore(x, scrabbleScores)], pot)
  return list(top)
 


 
'''The ind function checks to see if any character 'e' is in 'L' which is either a list or a string.
If there is, then the function returns the number posotion in which 'e' is first found in 'L'. If it is not, then
the function returns the length of the list'''
def ind(e, L):
    if (L==[]) or (e==L[0]) :
        return 0
    else:        
        return 1+ind(e,L[1:])

    
'''func13 is a help function for bestWord function. It just returns the greater value of two numbers that are being compared'''
def func13(x, y):
  if x[1] < y[1]:
    return y
  else:
    return x

'''The bestWord function takes a rack of letters, and outputs a word that can be made using the letters in the rack. However, the word it outputs isnt
just any word, its the word that is considered to have the greatest value when you add up all the values of the individual letters of that word together'''
def bestWord(rack):

  pw = scoreList(rack)
  return reduce(lambda x, y: func13(x, y), pw, ["", 0])
  

