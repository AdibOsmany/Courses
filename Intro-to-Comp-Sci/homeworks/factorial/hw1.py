############################################################
# Name: Adib Osmany
# Pledge: I pledge my honor that I have abided by the Stevens Honor System.
# CS 115 Homework 1
#  
############################################################


from math import factorial
from functools import reduce
def mult(x, y): 
   """Returns the product of x and y""" 
   return x * y

"""takes a positive interger n and returns n!"""
def factorial(n):
    if n==0:
        return 1
    else:
        return n*factorial(n-1)

"""finds the mean number of a list"""
def mean(L):
    length=len(L)
    if (length==0):
        return 0
    result=reduce(lambda a,t: a+t, L)
    return result/length
