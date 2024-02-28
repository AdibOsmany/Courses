import re
doc="AoC-2023-5-Seed_To_Location-doc.txt"

def mapped(maps):
    nL=maps.index("\n")
    return [maps[:nL],maps[(nL+1):]]

def change(AtoB,seed):
    AtoB=AtoB[1:]
    input=[]
    for line in AtoB:
        line=line.split()
        input.append(line)
    
    for line in input:
        if(seed in range(int(line[1]),(int(line[1])+int(line[2])))):
            output=(seed-(int(line[1])))+(int(line[0]))
            return output
    return seed


def main():
    file=open(doc,'r')
    maps=list(file)
    seeds=maps[0].split()
    seeds=seeds[1:]
    maps=maps[2:]
#--------------------------------------#
    a=mapped(maps)
    StoS=a[0]
    maps=a[1]

    a=mapped(maps)
    StoF=a[0]
    maps=a[1]

    a=mapped(maps)
    FtoW=a[0]
    maps=a[1]

    a=mapped(maps)
    WtoL=a[0]
    maps=a[1]

    a=mapped(maps)
    LtoT=a[0]
    maps=a[1]

    a=mapped(maps)
    TtoH=a[0]
    HtoL=a[1]
#-----------------------------------#
    minNum=-1
    for t in range(int(seeds[0]),(int(seeds[0])+int(seeds[1]))):
        t=change(StoS,t)
        t=change(StoF,t)
        t=change(FtoW,t)
        t=change(WtoL,t)
        t=change(LtoT,t)
        t=change(TtoH,t)
        t=change(HtoL,t)
        if(minNum==-1):
            minNum=t
        elif (t<minNum):
            minNum=t

    file.close()
    print(minNum)
    return


if __name__=='__main__':
    main()