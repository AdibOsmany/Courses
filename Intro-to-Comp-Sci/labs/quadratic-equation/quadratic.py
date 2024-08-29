############################################################
# Name: Adib Osmany
# Pledge: I pledge my honor that I have abided by the Stevens Honor System.
# CS 115 Lab 12
#  
############################################################  
  
class QuadraticEquation:
    '''The init function is a constructor for the class QuadraticEquation.
It assigns a,b,c to a private member float variable '''
    def __init__(self,a,b,c):
        a=float(a)
        b=float(b)
        c=float(c)
        if a==0:
            raise ValueError("Coefficient 'a' cannot be 0 in a quadratic equation.")
        else:
            self.a=a
            self.b=b
            self.c=c

    '''The discriminant function returns (b^2−4ac)'''
    def discriminant(self):
        return ((self.b)**2)-((4*self.a)*self.c)
    '''The root1 function computes the positive root of the quadratic formula.'''
    def root1(self):
        v=QuadraticEquation.discriminant(self)
        if v<0:
            return None
        else:
            return ((-(self.b))+(v**(1/2)))/(2*self.a)
    '''The root2 function computes the negative root of the quadratic formula.'''
    def root2(self):
        v=QuadraticEquation.discriminant(self)
        if v<0:
            return None
        else:
            return ((-(self.b))-(v**(1/2)))/(2*self.a)
    '''The str function returns a string representation of the quadratic equation.'''
    def __str__(self):
        ta=''
        fb=' + '
        gc=' + '
        aa=(str(self.a))+'x^2'
        bb=(str(abs(self.b)))+'x'
        cc=(str(abs(self.c)))
        if self.b==0:
            bb=''
            fb=''
        if self.c==0:
            cc=''
            gc=''
        if self.b<0:
            fb=' - '
        if self.c<0:
            gc=' - '
        if self.a==1:
            aa='x^2'
        if self.a==-1:
            aa='-x^2'
        if self.b==1 or self.b==-1:
            bb='x'
        return ta+aa+fb+bb+gc+cc+' = 0'        
            
