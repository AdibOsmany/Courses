############################################################
# Name: Adib Osmany
# Pledge: I pledge my honor that I have abided by the Stevens Honor System.
# CS115 Lab 1
#  
############################################################
from math import factorial
from functools import reduce

def inverse(x):
    '''returns the reciprocal of x'''
    return (1/x)

'''this function is meant to be used with the reduce with list3
to help find the factorial of the given number'''
def multi(z,t):
    '''returns the product of z and t'''
    return z*t


def factorial(y):
    '''returns the factorial of y'''
    list3=(range(1,y+1))
    return reduce(multi,(list3))
    
def e(n):
    list1=list(range(1,n+1))
    list4=list(map(factorial,(list1)))
    list2=list(map(inverse,(list4)))
    return sum(list2)+1
    
    
