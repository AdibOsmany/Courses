import pandas as pd
# Reading a CSV file
csv = pd.read_csv('laptop_data.csv')


def newCol(obj, cat):
    csv['is_'+obj]=((csv[cat]==obj)*1)

def main():
    catlist=[]

    #gets columns of non numerical categories from dataset
    for line in csv.columns:
        if csv[line].dtype=='object':
            catlist.append(line)

    #gets the types of objects from the non-numerical categories
    catObj=[]
    for cat in catlist:
        catObj.append(csv[cat].unique())

    for i in range(len(catlist)):
        for obj in catObj[i]:
            newCol(obj,catlist[i])
            print("data$"+'is_'+obj+" + ", end="")
    
    csv.drop(columns=catlist, inplace=True)
    csv.to_csv('laptop_data_final.csv',index=False)


    return

if __name__=='__main__':
    main()

