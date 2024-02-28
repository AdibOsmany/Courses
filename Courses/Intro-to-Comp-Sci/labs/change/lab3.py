############################################################
# Name: Adib Osmany
# Pledge: I pledge my honor that I have abided by the Stevens Honor System.
# CS 115 Lab 3
#  
############################################################

'''The change function gives you the least possible total
amount of coins you can get when recieving your change.
It uses a use-it-or-lose-it strategy to solve the problem'''
def change(amount, coins):
    if amount==0:
        return 0
    elif coins==[]:
        return float("inf")
    elif coins[-1]==amount:
        return 1
    elif coins[-1]>amount:
        return change(amount, coins[:-1])
    else:
        result=amount//coins[-1]
        pop=result*coins[-1]
        useit= (result+change(amount-pop, coins[:-1]))
        loseit= change(amount, coins[:-1])
        return min(useit,loseit)
