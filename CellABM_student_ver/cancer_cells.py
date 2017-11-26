# -*- coding: utf-8 -*-
"""
Agents - cancer cells (cc) 

@author: Marzieh, 2014
@commented: Harrison Paul Cooper, 2017
"""
import random
import math

from messages import messages     
from general_cell import general_cell


class cc(general_cell):
    
    #How large the cell is
    radius = 1.5
    #Implies the cell decreases over time? Or randomly generates too small?
    min_radius = 1
    #Implies movement of the cancer cell
    max_speed = 0.5
    # Not entirely sure of the reason to use 2/3 pi radians? Wouldn't random work?
    max_direc = round((2.0/3)*math.pi,3) #2/3pi radians 
    #So 1 stage is 1 iteration?
    max_stage = 60 #each level =20mins of real time
    
    #But we declare the number of cells at terminal?
    num_cc = 0 #number of alive cancer cells
    
    def __init__(self, ID=[], stage=[], pos=[], direc=[]):
        general_cell.__init__(self, ID, stage, pos, direc)
        # Increase the number of living cancer cells by one
        self.__class__.num_cc = self.__class__.num_cc + 1
    
    def __repr__(self):
        out = ('\nClass(cc)\nID : {0}\nStage : {1}\nPos : {2}\ndirec : {3}\n'. format(self.ID, self.stage, self.pos, self.direc))
        return (out)
    
    #uses general_cell migration and move_cell methods
    #uses general_cell apoptosis and kill_cell methods
        
    def split_cell(self,env):
        newcellpos=[self.pos[0]+random.uniform(-1,1)*self.radius, self.pos[1]+random.uniform(-1,1)* self.radius]
        newcell= cc(ID=self.num_cc, stage=1, pos=newcellpos, direc=random.random()*2*math.pi)
        self.stage = 1 
        self.pos=[self.pos[0]+random.uniform(-1,1)*self.radius, self.pos[1]+random.uniform(-1,1)* self.radius]
        print('new cancer cell created with cell ID = %s' %str(newcell.ID))        
        return(newcell)
    
    def mitosis(self,env):
        if self.stage >= self.max_stage:
            new=self.split_cell(env)
        else:
            self.stage = self.stage +1 
            new = None    
        return(new)    
        