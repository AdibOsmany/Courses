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
    for i in range(len(seeds)):
        seeds[i]=change(StoS,int(seeds[i]))

    for i in range(len(seeds)):
        seeds[i]=change(StoF,int(seeds[i]))

    for i in range(len(seeds)):
        seeds[i]=change(FtoW,int(seeds[i]))

    for i in range(len(seeds)):
        seeds[i]=change(WtoL,int(seeds[i]))

    for i in range(len(seeds)):
        seeds[i]=change(LtoT,int(seeds[i]))
    
    for i in range(len(seeds)):
        seeds[i]=change(TtoH,int(seeds[i]))

    for i in range(len(seeds)):
        seeds[i]=change(HtoL,int(seeds[i]))

    file.close()
    print(min(seeds))
    return


if __name__=='__main__':
    main()