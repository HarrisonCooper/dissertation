# -*- coding: utf-8 -*-
"""
Agents - stem cells (sc)

@author: Marzieh, 2014
"""
from messages import messages
from general_cell import general_cell

import random
import math

class sc(general_cell):

    #radius = 1.5
    #radius = random.randint(5,10)
    min_radius = 4.9
    max_speed = 360 #1 #(move at 1micrometer a min, time period = 6hrs therefore this is speed)
    max_direc = round((2.0/3)*math.pi,3)
    max_stage = 4 #each level =6hrs of real time (I believe this is turnover time)
    max_turnover = 50 

    num_sc = 0 #number of alive stem cells

    def __init__(self, ID=[], stage=[], pos=[], direc=[], turnover = [], radius = [], area = []):
        general_cell.__init__(self, ID, stage, pos, direc, turnover, radius, area)
        # Increase the number of living stem cells by one
        self.__class__.num_sc = self.__class__.num_sc + 1

    def __repr__(self):
        out = ('\nClass(sc)\nID : {0}\nStage : {1}\nPos : {2}\ndirec : {3}\n'. format(self.ID, self.stage, self.pos, self.direc))
        return (out)

    #uses general_cell migration and move_cell methods
    #uses general_cell apoptosis and kill_cell methods

    def split_cell(self,env):
        self.area = self.area/2 #Daughter cell is halve the size of the parent
        self.radius = math.sqrt(self.area/math.pi)#Daughter cell is halve the size of the parent
        newcellpos=[self.pos[0]+random.uniform(-1,1)*self.radius, self.pos[1]+random.uniform(-1,1)* self.radius]
        newcell= sc(ID=self.num_sc, stage=1, pos=newcellpos, direc=random.random()*2*math.pi, turnover=1, radius=self.radius, area=self.area)
        #self.radius = self.radius/2 #Daughter cell is half area of parent
#        self.area = self.area/2 #Daughter cell is halve the size of the parent
#        self.radius = math.sqrt(self.area/math.pi)#Daughter cell is halve the size of the parent
        self.stage = 1
        self.turnover = self.turnover +1
        self.pos=[self.pos[0]+random.uniform(-1,1)*self.radius, self.pos[1]+random.uniform(-1,1)* self.radius]
        print('new stem cell created with cell ID = %s' %str(newcell.ID))
        return(newcell)
        
    def mitosis(self,env):
        #print('stage: ', self.stage)
        #print('Turnover: ', self.turnover)
        print("Cell: %s Radius %s" %(self.ID, self.radius))

        if self.stage == self.max_stage:
            #self.turnover = self.turnover +1
            new=self.split_cell(env)
        else:
            self.stage = self.stage +1
            new = None
        return(new)
        

        """Harrison P Cooper - 7/2/18"""
    def growth(self,env):
        #This function should enable the EC's to double in size over their 4 stages, before undergoing mitosis.
        #WARNING: all this does is for n < 10 after 4 steps n = 10. Therefore no doubling occurs...
        #Could cheat and on 4th stage double radius. But then instant splitting into 2 half produces no result.
        
        #This is all wrong, the cells double is area not radius, area = pi*r^2
        
        print("*1*", "CellID:", self.ID, "Stage:", self.stage, "Area:", self.area, "Radius:", self.radius)
        
        if self.stage == 1: #Increase orignial size by 1/4
            self.area = self.area * 1.25
#            print(self.area)
        elif self.stage == 2: #Decrease by 1/4 to achieve orignial, then increase by 2/4
            sa = self.area / 1.25
            self.area = sa * 1.5
#            print(self.area)
        elif self.stage == 3: #Decrease by 2/4 to achieve orignial, the increase by 3/4
            sa = self.area / 1.5
            self.area = sa * 1.75
#            print(self.area)
        else: #Decrease by 3/4 to achieve orignial, then double
            sa = self.area / 1.75
            self.area = sa * 2
        #Send this result onto mitosis.
        self.radius = math.sqrt(self.area/math.pi)
        
        print("*2*", "CellID:", self.ID, "Stage:", self.stage, "Area:", self.area, "Radius:", self.radius)
        
        #new=self.mitosis(env)
        return(self.mitosis(env))



        
