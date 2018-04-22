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
        quiescentcells = []
        print(ncc, nsc)

        for c in range(ncc):
            ID = c
            radius = random.randint(10,50) 
            area = math.pi*(radius*radius)
            pos = [radius + (round(rand(),3))*(self.size-(2*radius)), radius +(round(rand(),3))*(self.size-(2*radius))]
            stage = np.ceil(rand()*4380)
            direc = rand()*2*np.pi
            turnover = 1;
            cancercells.append(cc(ID, stage, pos, direc, turnover, radius, area))

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
        self.quiescentcells=quiescentcells
        
    """
    From meeting with medical expert, Paul, he stated a wound size of 100 and 500
    microns is a typical scratch assay size, but larger sizes would be good.
    For now we will be using 100 microns (50 each side of midpoint)
    As all cells will be removed from between x1 and x2, no need to worry about 
    Y.
    """
    def wound(self, wsize):
        xlength = wsize #wound length
        x1 = (self.size/2) - (xlength/2)
        x2 = (self.size/2) + (xlength/2)
        
        print("EC: ", len(self.stemcells))
        print("CC: ", len(self.cancercells))
        print("QC: ", len(self.quiescentcells))
#        import sys
#        sys.exit("WOUNDED")
        
        for n in range(len(self.cancercells)):
            if self.cancercells[n].pos[0] > x1 and self.cancercells[n].pos[0] < x2:
                self.cancercells[n].kill_cell(self)
                
        for n in range(len(self.stemcells)):
            if self.stemcells[n].pos[0] > x1 and self.stemcells[n].pos[0] < x2:
                self.stemcells[n].kill_cell(self)
                
        for n in range(len(self.quiescentcells)):
            if self.quiescentcells[n].pos[0] > x1 and self.quiescentcells[n].pos[0] < x2:
                self.quiescentcells[n].kill_cell(self)
                
        # remove dead cells
        self.stemcells = ([a for a in self.stemcells if not a.dead])
        self.cancercells = ([a for a in self.cancercells if not a.dead])
        self.quiescentcells = ([a for a in self.quiescentcells if not a.dead])
