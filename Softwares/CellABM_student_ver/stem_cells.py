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
    min_radius = 1
    max_speed = 1
    max_direc = round((2.0/3)*math.pi,3)
    max_stage = 90 #each level =20mins of real time

    num_sc = 0 #number of alive stem cells

    def __init__(self, ID=[], stage=[], pos=[], direc=[]):
        general_cell.__init__(self, ID, stage, pos, direc)
        # Increase the number of living stem cells by one
        self.__class__.num_sc = self.__class__.num_sc + 1

    def __repr__(self):
        out = ('\nClass(sc)\nID : {0}\nStage : {1}\nPos : {2}\ndirec : {3}\n'. format(self.ID, self.stage, self.pos, self.direc))
        return (out)

    #uses general_cell migration and move_cell methods
    #uses general_cell apoptosis and kill_cell methods

    def split_cell(self,env):
        newcellpos=[self.pos[0]+random.uniform(-1,1)*self.radius, self.pos[1]+random.uniform(-1,1)* self.radius]
        newcell= sc(ID=self.num_sc, stage=1, pos=newcellpos, direc=random.random()*2*math.pi)
        self.stage = 1
        self.pos=[self.pos[0]+random.uniform(-1,1)*self.radius, self.pos[1]+random.uniform(-1,1)* self.radius]
        print('new stem cell created with cell ID = %s' %str(newcell.ID))
        return(newcell)

    def mitosis(self,env):
        if self.stage == self.max_stage:
            new=self.split_cell(env)
        else:
            self.stage = self.stage +1
            new = None
        return(new)