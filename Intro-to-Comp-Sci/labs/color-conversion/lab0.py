#Name: Adib Osmany
#Pledge: I pledge my honor that I have abided by the Stevens Honor System.
r= (int(input("Enter 'r' value: ")))/255
g= (int(input("Enter 'g' value: ")))/255
b= (int(input("Enter 'b' value: ")))/255

w=max((r),(g),(b))
c=(w-(r))/w
m=(w-(g))/w
y=(w-(b))/w
k=1-w

print(c)
print(m)
print(y)
print(k)
