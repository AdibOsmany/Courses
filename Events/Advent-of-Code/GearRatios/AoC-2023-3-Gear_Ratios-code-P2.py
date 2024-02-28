import re
doc="AoC-2023-3-Gear_Ratios-doc.txt"

def findStar(lis):
    t=list(lis)
    t.pop()
    index=[]
    for i in t:
        if i=="*":
            x=lis.find(i)
            index.append(x)
            lis=lis.replace(i,".",1)
    return index

def findNum(txt,pos,direction):
    if(txt[pos].isdigit()):
        if(direction):
            return(txt[pos]+findNum(txt,pos+1,direction))
        else:
            return(findNum(txt,pos-1,direction)+txt[pos])
    else:
        return ""

def D2(start, length, row):
    for i in range(length):
        if(row[start+i].isdigit()):
            x=findNum(row,start+i,True)
            y=findNum(row,start+i,False)
            if((len(x)==len(y))&(len(y)==1)):
                return [True,y]
            elif(len(x)==len(y)):
                y=y+x[1:]
                return [True,y]
            elif(len(x)>len(y)):
                return [True,x]
            else:
                z=findNum(row,start+2,True)
                if (z!=""):
                    return [True,y,z]
                else:
                    return [True,y]
    return [False]

def findPart(prev,look,next):
    output=0
    indexes=findStar(look)
    for i in indexes:
        lis=[]
        if(i!=0):
            if(look[i-1].isdigit()):
                lis.append(int(findNum(look,i-1,False)))
            if(i!=(len(look))-1):
                if(look[i+1].isdigit()):
                    lis.append(int(findNum(look,i+1,True)))
        else:
            if(look[i+1].isdigit()):
                lis.append(int(findNum(look,i+1,True)))

        if(i==0):
            if((D2(i,2,prev))[0]):
                lis.append(int((D2(i,2,prev))[1]))
            if((D2(i,2,next))[0]):
                lis.append(int((D2(i,2,next))[1]))
        elif(i==(len(look)-1)):
            if((D2(i-1,2,prev))[0]):
                lis.append(int((D2(i-1,2,prev))[1]))
            if((D2(i-1,2,next))[0]):
                lis.append(int((D2(i-1,2,next))[1]))
        else:
            if ((D2(i-1,3,prev))[0]):
                lis.append(int((D2(i-1,3,prev))[1]))
                if (len((D2(i-1,3,prev)))==3):
                    lis.append(int((D2(i-1,3,prev))[2]))
            if ((D2(i-1,3,next))[0]):
                lis.append(int((D2(i-1,3,next))[1]))
                if (len((D2(i-1,3,next)))==3):
                    lis.append(int((D2(i-1,3,next))[2])) 

        if(len(lis)==2):
            output+=(lis[0]*lis[1])

    return output


def main():
    row1=None
    row2=None
    row3=None
    total=0
    file=open(doc,'r')
    prev=(140*".")
    prev2=None
    i1L=True
    for line in file:
        if(i1L):
            row2=line
            i1L=False
            prev2=row2
        else:
            row1=prev
            row2=prev2
            row3=line
            prev2=row3
            prev=row2
            total+=findPart(row1,row2,row3)
    total+=findPart(row2,row3,(140*"."))
    file.close()
    print(total)
    return



if __name__=='__main__':
    main()