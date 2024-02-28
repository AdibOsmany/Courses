############################################################
# Name: Adib Osmany
# Pledge: I pledge my honor that I have abided by the Stevens Honor System.
# CS 115 Lab 4
#  
############################################################


'''The knapsack function takes the weight of which a bag can carry stuff
and a list of stuff with their weight and value and returns a list with
the highest possible value to obtain that doesnt go over the weight
limit of the bag. It also returns the items that were used to get the
highest value.'''
#TA assisted in this program
def knapsack(capacity, items):
    if (capacity <=0) or (len(items)==0):
        return [0,[]]
    elif capacity< items[0][0]:
        return knapsack(capacity,items[1:])
    useit= knapsack(capacity-items[0][0], items[1:])
    loseit=knapsack(capacity, items[1:])

    use=useit[0]+items[0][1]
    lose=loseit[0]

    if max(use, lose) == use:
        return [use]+[[items[0]]+useit[1]]
    else:
        return loseit
