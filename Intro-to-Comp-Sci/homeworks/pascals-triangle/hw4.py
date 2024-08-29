############################################################
# Name: Adib Osmany
# Pledge: I pledge my honor that I have abided by the Stevens Honor System.
# CS 115 HW4
#  
############################################################
memo={0:[1],1:[1,1]}
'''the pascal function is a helper function to pascal_row. this function
takes a pascal row and outputs a list of numbers you would attain if you
were to add the two adjacent numbers of each number in that row together'''
def pascal(n,m):
    if n==[] or m==[]:
        return []
    tiger=n[0]+m[0]
    return [tiger]+pascal(n[1:],m[1:])
'''the pascal_row function takes a number and outputs a row of numbers
associated with that part of pascal's triangle'''
def pascal_row(n):
    if (n) in memo:
        return memo[n]
    top=pascal_row(n-1)
    memo[n-1]=top
    pot=pascal(top,top[1:])
    memo[n]=[1]+pot+[1]
    return memo[n]
'''the pascal_triangle function outputs pascal's triangle up until the
nth row'''
def pascal_triangle(n):
    if n<0:
        return []
    top=pascal_row(n)
    return pascal_triangle(n-1)+[top]
'''the test_pascal_row function tests to see if the pascal_row function
is working properly by testing 4 cases.'''
def test_pascal_row():
    assert pascal_row(1)==[1, 1]
    assert pascal_row(5)==[1, 5, 10, 10, 5, 1]
    assert pascal_row(7)==[1, 7, 21, 35, 35, 21, 7, 1]
    assert pascal_row(10)==[1, 10, 45, 120, 210, 252, 210, 120, 45, 10, 1]
'''the test_pascal_triangle function tests to see if the pascal_triangle
function is working properly by testing 4 cases.'''
def test_pascal_triangle():
    assert pascal_triangle(0)==[[1]]
    assert pascal_triangle(1)==[[1], [1, 1]]
    assert pascal_triangle(5)==[[1], [1, 1], [1, 2, 1], [1, 3, 3, 1], [1, 4, 6, 4, 1], [1, 5, 10, 10, 5, 1]]
    assert pascal_triangle(7)==[[1], [1, 1], [1, 2, 1], [1, 3, 3, 1], [1, 4, 6, 4, 1], [1, 5, 10, 10, 5, 1], [1, 6, 15, 20, 15, 6, 1], [1, 7, 21, 35, 35, 21, 7, 1]]
