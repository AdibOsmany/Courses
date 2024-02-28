import re
doc="AoC-2023-3-Gear_Ratios-doc.txt"

def findNum(lis):
    t=re.split("\D",lis)
    t.pop()
    index=[]
    for i in t:
        if i.isdigit():
            x=lis.find(i)
            index.append([x,x+len(i)])
            lis=lis.replace(i,len(i)*".",1)
    return index


def D2(start, length, row):
    for i in range(length):
        if(row[start+i]!="."):
            return True
    return False

def findPart(prev,look,next):
    done=False
    lis=[]
    indexes=findNum(look)
    for i in indexes:
        j=i[0]
        k=i[1]
        length=k-j
        if(j!=0):
            if(look[j-1]!="."):
                lis.append(int(look[j:k]))
                done=True
            elif(k!=(len(look))-1):
                if(look[k]!="."):
                    lis.append(int(look[j:k]))
                    done=True
        else:
            if(look[k]!="."):
                lis.append(int(look[j:k]))
                done=True
        if(done!=True):
            if(j==0):
                if(D2(j,length+1,prev)):
                    lis.append(int(look[j:k]))
                elif(D2(j,length+1,next)):
                    lis.append(int(look[j:k]))
            elif(k==(len(look)-1)):
                if(D2(j-1,length+1,prev)):
                    lis.append(int(look[j:k]))
                elif(D2(j-1,length+1,next)):
                    lis.append(int(look[j:k]))
            else:
                if (D2(j-1,length+2,prev)):
                    lis.append(int(look[j:k]))
                elif (D2(j-1,length+2,next)):
                    lis.append(int(look[j:k]))
        done=False
    return lis


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
            total+=sum(findPart(row1,row2,row3))
    total+=sum(findPart(row2,row3,(140*".")))
    file.close()
    print(total)
    return



if __name__=='__main__':
    main()