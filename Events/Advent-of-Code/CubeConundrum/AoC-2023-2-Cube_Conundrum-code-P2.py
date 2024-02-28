import re
doc="AoC-2023-2-Cube_Conundrum-doc.txt"


#red-12
#green-13
#blue-14

def helper(hold):  
    blue=[0]
    red=[0]
    green=[0]
    for i in range(1,len(hold)):
        if(hold[i][-1]=='d'): #red
            num=int(hold[i][:-4][1:])
            red.append(num)
        elif(hold[i][-1]=='n'): #green
            num=int(hold[i][:-6][1:])
            green.append(num)
        elif(hold[i][-1]=='e'): #blue
            num=int(hold[i][:-5][1:])
            blue.append(num)
    return(max(red)*max(green)*max(blue))

def main():
    total=0
    file=open(doc,'r')
    for line in file:
        hold=re.split(":|,|;|\n",line)
        hold.pop()
        total+=helper(hold)
    file.close()
    print(total)
    return


if __name__=='__main__':
    main()