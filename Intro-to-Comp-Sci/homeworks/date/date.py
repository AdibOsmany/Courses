############################################################
# Name: Adib Osmany
# Pledge: I pledge my honor that I have abided by the Stevens Honor System.
# CS 115 HW 12
#  
############################################################
DAYS_IN_MONTH = (0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

class Date(object):
    '''A user-defined data structure that stores and manipulates dates.'''

    # The constructor is always named __init__.
    def __init__(self, month, day, year):
        '''The constructor for objects of type Date.'''
        self.month = month
        self.day = day
        self.year = year

    # The 'printing' function is always named __str__.
    def __str__(self):
        '''This method returns a string representation for the
           object of type Date that calls it (named self).

             ** Note that this _can_ be called explicitly, but
                it more often is used implicitly via the print
                statement or simply by expressing self's value.'''
        return '%02d/%02d/%04d' % (self.month, self.day, self.year)

    def __repr__(self):
        '''This method also returns a string representation for the object.'''
        return self.__str__()

    # Here is an example of a 'method' of the Date class.
    def isLeapYear(self):
        '''Returns True if the calling object is in a leap year; False
        otherwise.'''
        if self.year % 400 == 0:
            return True
        if self.year % 100 == 0:
            return False
        if self.year % 4 == 0:
            return True
        return False
    def copy(self): 
        '''Returns a new object with the same month, day, year 
           as the calling object (self).''' 
        dnew = Date(self.month, self.day, self.year) 
        return dnew
    def equals(self, d2): 
        '''Decides if self and d2 represent the same calendar date, 
            whether or not they are the in the same place in memory.''' 
        return self.year == d2.year and self.month == d2.month and \
               self.day == d2.day

    '''The tomorrow function changes the calling object so that it
represents one calendar day after the date it originally represented'''
    def tomorrow(self):
        tiger=DAYS_IN_MONTH[self.month]
        if ((self.isLeapYear())==True) and (self.month==2):
            tiger=29
        if self.day == tiger:
            self.day=1
            if self.month==12:
                self.month=1
                self.year=self.year+1
            else:
                self.month=self.month+1
        else:
            self.day=self.day+1
            
    '''The yesterday function changes the calling object so that it
represents one calendar day before the date it originally represented'''
    def yesterday(self):
        tiger=DAYS_IN_MONTH[self.month-1]
        if tiger==0:
            tiger=31
        if ((self.isLeapYear())==True) and (self.month==3) and (self.day==1):
            self.day=29
            self.month=2
        elif self.day == 1:
            self.day=tiger
            if self.month==1:
                self.month=12
                self.year=self.year-1
            else:
                self.month=self.month-1
        else:
            self.day=self.day-1

    '''The addNDays function changes the calling object so that it 
    represents N calendar days after the date it originally represented.'''

    def addNDays(self, N):
        print(self)
        for t in range(N):
            self.tomorrow()
            print(self)
            
    '''The subNDays function changes the calling object so that it 
    represents N calendar days before the date it originally represented. '''
    def subNDays(self, N):
        print(self)
        for t in range(N):
            self.yesterday()
            print(self)
            
    '''The isBefore function return True if the calling object is a calendar
    date before the input named d2'''
    def isBefore(self, d2):
        if self.year<d2.year:
            return True
        elif (self.year==d2.year) and (self.month<d2.month):
            return True
        elif (self.month==d2.month) and (self.day<d2.day):
            return True
        else:
            return False
        
    '''The isAfter function return True if the calling object is a calendar
    date after the input named d2'''
    def isAfter(self, d2):
        if self.year>d2.year:
            return True
        elif (self.year==d2.year) and (self.month>d2.month):
            return True
        elif (self.month==d2.month) and (self.day>d2.day):
            return True
        else:
            return False
    '''The diff function returns an integer representing the number of days
    between self and d2. '''
    def diff(self,d2):
        d3=self.copy()
        d4=d2.copy()
        t=d3.isBefore(d4)
        f=d3.isAfter(d4)
        n=0
        if t==f:
            return n
        elif f==True:
            while f==True:
                d4.tomorrow()
                f=d3.isAfter(d4)
                n+=1
            return n
        elif t==True:
            while t==True:
                d4.yesterday()
                t=d3.isBefore(d4)
                n-=1
            return n
    '''The dow function returns a string that indicates the day of the week
    (dow) of the object that call it'''
    def dow(self):
        week=['Monday','Tuesday', 'Wednesday', 'Thursday', 'Friday',\
              'Saturday','Sunday']
        d=Date(11,7,2011)
        n=self.diff(d)
        return week[n%7]
        
        
            
 
