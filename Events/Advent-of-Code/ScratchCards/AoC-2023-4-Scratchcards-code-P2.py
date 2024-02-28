import re
doc="AoC-2023-4-Scratchcards-doc.txt"

def gamble(lot,win):
    output=0
    for i in lot:
        if i in win:
            output+=1
    return output


def main():
    total=[]
    for i in range(192):
        total.append(1)
    file=open(doc,'r')
    lis=list(file)
    for line in lis:
        card=int(line[5:8])
        line =line[9:]
        hold=line.split()
        lottery=hold[:10]
        winnings=hold[11:]
        won=gamble(lottery,winnings)
        for j in range(won):
            offset=j+1
            game=card-1
            if ((game+offset)<=191):
                lis.append(lis[game+offset])
                total[game+offset]=total[game+offset]+1
    file.close()
    print(sum(total))
    return


if __name__=='__main__':
    main()