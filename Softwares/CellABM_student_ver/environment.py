# -*- coding: utf-8 -*-
"""
Creating environment and initial model cells (inc pos, direc and stage info)

@author: Marzieh, 2014
"""

import numpy as np
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
            pos = [cc.radius + (round(rand(),3))*(self.size-(2*cc.radius)), cc.radius +(round(rand(),3))*(self.size-(2*cc.radius))]
            stage = np.ceil(rand()*60)
            direc = rand()*2*np.pi
            
            cancercells.append(cc(ID, stage, pos, direc))

        for s in range(nsc):
            ID = s
            pos = [sc.radius + (round(rand(),3))*(self.size-(2*sc.radius)), sc.radius +(round(rand(),3))*(self.size-(2*sc.radius))]
            stage = np.ceil(rand()*4)
            direc = rand()*2*np.pi
            turnover = 1
            #radius = sc.radius
            #print("Cell: %s Radius: %s"%s(ID, radius))
            
            stemcells.append(sc(ID, stage, pos, direc, turnover))

        #if list type is seperate (each agent type has its own list)
        self.cancercells=cancercells
        self.stemcells=stemcells
