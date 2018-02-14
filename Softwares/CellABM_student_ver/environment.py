# -*- coding: utf-8 -*-
"""
Creating environment and initial model cells (inc pos, direc and stage info)

@author: Marzieh, 2014
"""

import numpy as np
import math
import random
from cancer_cells import cc
from stem_cells import sc
from numpy.random import rand

class environment:
    def __init__(self, size, mode):
        self.size = size
        self.mode = mode

    def create_agents(self, ncc, nsc):
        cancercells = []
        stemcells = []
        print(ncc, nsc)

        for c in range(ncc):
            ID = c
            radius = random.randint(5,10) #why is this not working like it did for ECs?
            area = math.pi*(radius*radius)
            pos = [radius + (round(rand(),3))*(self.size-(2*radius)), radius +(round(rand(),3))*(self.size-(2*radius))]
            stage = np.ceil(rand()*4380)
            direc = rand()*2*np.pi
            
            cancercells.append(cc(ID, stage, pos, direc, radius, area))

        for s in range(nsc):
            ID = s
            radius = random.randint(5,10)
            area = math.pi*(radius*radius)
            pos = [radius + (round(rand(),3))*(self.size-(2*radius)), radius +(round(rand(),3))*(self.size-(2*radius))]
            stage = np.ceil(rand()*4)
            direc = rand()*2*np.pi
            turnover = 1 #Adapt logic so older the patient, the higher the average starting turnover of seed ECs.
            stemcells.append(sc(ID, stage, pos, direc, turnover, radius, area))

        #if list type is seperate (each agent type has its own list)
        self.cancercells=cancercells
        self.stemcells=stemcells
