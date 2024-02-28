#By: Adib Osmany and Neel Meyyur
#I pledge my honor that I have abided by the stevens honors system
doc="CUP.txt"
output="image file.txt"

def pad(txt, size):
    length=len(txt)
    zero=size-length
    txt=(zero*"0")+txt
    return txt

def main():
    contents=[]
    file=open(doc,'r')
    contents.append("0000000000000000")
    contents.append("0000000000001053")
    contents.append("0000000000002053")
    contents.append("0000000000003053")
    contents.append("0000000000004053")
    contents.append("0000000000005053")
    for line in file:
        final=""
        operation=""
        rd=""
        data1="0"
        data2="0"
        num1="000000"
        num2="000000"
        instruction=line.split()
        if(instruction[0]=="ADD"):
            operation="1"
            rd=str(int(instruction[1][1])+1)
            if(instruction[2][:-1].isdigit()):
                hold=hex(int(instruction[2][:-1]))
                hold=str(hold)[2:]
                num1=pad(hold, 6)
                data1="5"
            else:
                data1=str(int(instruction[2][1])+1)
            if(instruction[3].isdigit()):
                hold=hex(int(instruction[3]))
                hold=str(hold)[2:]
                num2=pad(hold,6)
                data2="6"
            else:
                data2=str(int(instruction[3][1])+1)
        elif(instruction[0]=="MUL"):
            operation="2"
            rd=str(int(instruction[1][1])+1)
            if(instruction[2][:-1].isdigit()):
                hold=hex(int(instruction[2][:-1]))
                hold=str(hold)[2:]
                num1=pad(hold,6)
                data1="5"
            else:
                data1=str(int(instruction[2][1])+1)
            if(instruction[3].isdigit()):
                hold=hex(int(instruction[3]))
                hold=str(hold)[2:]
                num2=pad(hold,6)
                data2="6"
            else:
                data2=str(int(instruction[3][1])+1)
        elif(instruction[0]=="STR"):
            if(instruction[2]=="MEM"):
                operation="3"
                rd=str(int(instruction[1][1])+1)
                data1=str(int(instruction[1][1])+1)
            else:
                operation="1"
                rd=str(int(instruction[2][1])+1)
                if(instruction[1][:-1].isdigit()):
                    hold=hex(int(instruction[1][:-1]))
                    hold=str(hold)[2:]
                    num1=pad(hold,6)
                    data1="5"
                else:
                    data1=str(int(instruction[1][1])+1)
        elif(instruction[0]=="LDR"):
            if(instruction[2]=="MEM"):
                operation="4"
                rd=str(int(instruction[1][1])+1)
            else:
                operation="1"
                rd=str(int(instruction[1][1])+1)
                if(instruction[2].isdigit()):
                    hold=hex(int(instruction[2]))
                    hold=str(hold)[2:]
                    num1=pad(hold,6)
                    data1="5"
                else:
                    data1=str(int(instruction[2][1])+1)
        final=num2+num1+rd+data2+data1+operation
        contents.append(final)
    file.close()
    count=0
    file=open(output,'w+')
    file.write("v3.0 hex words addressed\n")
    i=0
    for code in contents:
        i=i+1
        if(i==1):
            hold=str(hex(count))[2:]
            file.write(pad(hold,2)+": ")
            file.write(code+" ")
        elif(i!=8):
            file.write(code+" ")
        else:
            i=0
            count+=8
            file.write(code+"\n")
    file.close()
    return


if __name__=='__main__':
    main()
