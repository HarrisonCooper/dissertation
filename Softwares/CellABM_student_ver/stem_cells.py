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
    radius = random.randint(5,10)
    min_radius = 5
    max_speed = 1 #360 #(move at 1micrometer a min, time period = 6hrs therefore this is speed)
    max_direc = round((2.0/3)*math.pi,3)
    max_stage = 4 #each level =6hrs of real time (I believe this is turnover time)
    max_turnover = 50 

    num_sc = 0 #number of alive stem cells

    def __init__(self, ID=[], stage=[], pos=[], direc=[], turnover = []):
        general_cell.__init__(self, ID, stage, pos, direc, turnover)
        # Increase the number of living stem cells by one
        self.__class__.num_sc = self.__class__.num_sc + 1

    def __repr__(self):
        out = ('\nClass(sc)\nID : {0}\nStage : {1}\nPos : {2}\ndirec : {3}\n'. format(self.ID, self.stage, self.pos, self.direc))
        return (out)

    #uses general_cell migration and move_cell methods
    #uses general_cell apoptosis and kill_cell methods

    def split_cell(self,env):
        newcellpos=[self.pos[0]+random.uniform(-1,1)*self.radius, self.pos[1]+random.uniform(-1,1)* self.radius]
        newcell= sc(ID=self.num_sc, stage=1, pos=newcellpos, direc=random.random()*2*math.pi, turnover=1)
        self.stage = 1
        self.turnover = self.turnover +1
        self.pos=[self.pos[0]+random.uniform(-1,1)*self.radius, self.pos[1]+random.uniform(-1,1)* self.radius]
        print('new stem cell created with cell ID = %s' %str(newcell.ID))
        return(newcell)
        
        """Harrison P Cooper - 7/2/18"""
    def growth(self):
        #This function should enable the EC's to double in size over their 4 stages, before undergoing mitosis.
        #WARNING: all this does is for n < 10 after 4 steps n = 10. Therefore no doubling occurs...
        #Could cheat and on 4th stage double radius. But then instant splitting into 2 half produces no result.
        if self.stage == 1: #Increase orignial size by 1/4
            self.radius = self.radius + (self.radius)/4 
        elif self.stage == 2: #Decrease by 1/4 to achieve orignial, then increase by 2/4
            self.radius = self.radius + (10-self.radius)/3
        elif self.stage == 3: #Decrease by 2/4 to achieve orignial, the increase by 3/4
            self.radius = self.radius + (10-self.radius)/2
        else: #Decrease by 3/4 to achieve orignial, then double
            self.radius = self.radius + (10-self.radius)/1
        #Send this result onto mitosis.


    def mitosis(self,env):
        print('stage: ', self.stage)
        print('Turnover: ', self.turnover)
        
        """
        Experimentation with doubling EC size during proliferation.
        (therefore min starting size of 5um will go to 10um before splitting
        and max starting size of 10um will go to 20um before splitting)
        4 steps, in all steps , take current radius and increase by:
            stage1: 
                self.radius = radius + (2*radius)/4
            stage2:
                self.radius = radius + (10-radius)/4
        """
        
        
        if self.stage == self.max_stage:
            #self.turnover = self.turnover +1
            new=self.split_cell(env)
        else:
            self.stage = self.stage +1
            new = None
        return(new)