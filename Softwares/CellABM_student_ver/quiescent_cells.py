#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Mon Feb 26 18:29:05 2018

@author: harrycooper
"""

import random
import math

from messages import messages     
from general_cell import general_cell


class qc(general_cell):
    
    min_radius = 1
    #Quiescent cells cannot move
    max_speed = 0
    # Not entirely sure of the reason to use 2/3 pi radians? Wouldn't random work?
    max_direc = round((2.0/3)*math.pi,3) #2/3pi radians 
    #So 1 stage is 1 iteration?
    max_stage = 240 #each level = 6hrs of real time
#    max_turnover = 4381
    
    num_qc = 0 #number of alive cancer cells
    
    def __init__(self, ID=[], stage=[], pos=[], direc=[], turnover = [], radius = [], area = []):
        general_cell.__init__(self, ID, stage, pos, direc, turnover, radius, area)
        # Increase the number of living quiescent cells by one
        self.__class__.num_qc = self.__class__.num_qc + 1
    
    def __repr__(self):
        out = ('\nClass(cc)\nID : {0}\nStage : {1}\nPos : {2}\ndirec : {3}\n'. format(self.ID, self.stage, self.pos, self.direc))
        return (out)
    
    
#    def mitosis(self,env):
#        if self.stage >= self.max_stage:
#            new=self.split_cell(env)
#        else:
#            self.stage = self.stage +1 
#            new = None    
#        return(new)    
        
    #When a QC has been in this state for long enough -> Senesent 
    #def senescence():
        
    #When a QC can proliforate  -> Endothelial Cell
    #def endothelial():
        
    
    def growth(self,env):
        self.area = self.area * (1.5)
        self.radius = math.sqrt(self.area/math.pi)
        self.stage += 1
        
        