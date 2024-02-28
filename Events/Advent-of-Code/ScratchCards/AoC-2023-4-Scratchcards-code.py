import re
doc="AoC-2023-4-Scratchcards-doc.txt"

def gamble(lot,win):
    output=-1
    for i in lot:
        if i in win:
            output+=1
    if (output==-1):
        return 0
    else:
        return pow(2,output)


def main():
    total=0
    file=open(doc,'r')
    for line in file:
        line =line[9:]
        hold=line.split()
        lottery=hold[:10]
        winnings=hold[11:]
        total+=gamble(lottery,winnings)
    file.close()
    print(total)
    return


if __name__=='__main__':
    main()