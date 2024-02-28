import re
doc="AoC-2023-1-Trebuchet-doc.txt"


def replaceAll(word,num,lis):
    return [x if x!=word else num for x in lis]

def main():
    total=0
    file=open(doc,'r')
    for line in file:
        hold=(re.findall("[0,1,2,3,4,5,6,7,8,9]|zero|one|two|three|four|five|six|seven|eight|nine",line))
        hold=replaceAll('zero','0',hold)
        hold=replaceAll('one','1',hold)
        hold=replaceAll('two','2',hold)
        hold=replaceAll('three','3',hold)
        hold=replaceAll('four','4',hold)
        hold=replaceAll('five','5',hold)
        hold=replaceAll('six','6',hold)
        hold=replaceAll('seven','7',hold)
        hold=replaceAll('eight','8',hold)
        hold=replaceAll('nine','9',hold)
        if(len(hold)!=0):
            total+=int(hold[0]+hold[-1])
    file.close()
    print(total)
    return

if __name__=='__main__':
    main()