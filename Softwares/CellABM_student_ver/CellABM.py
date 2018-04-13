# -*- coding: utf-8 -*-
"""
CellAbm - Master Script

@author: Marzieh, 2014
"""
#%%
#import matplotlib.pyplot as plt
import numpy as np
import sys


from cancer_cells import cc
from stem_cells import sc
from quiescent_cells import qc
from environment import environment
from solver import agent_solve
from results import plot_2d, plot_3d, growth_curve, save
from overlap import initiate_OC

# (size of environemnt, number of cancer cells, number of stem cells, number of steps, location of image files)
# Main line called from terminal
def CellABM(size, ncc, nsc, steps, directory, mode = 'sync', freq=0, labels = False):
    cc.num_cc = 0
    sc.num_sc = 0
    qc.num_qc = 0

    env = environment(size, mode)
    env.create_agents(ncc, nsc)

    num_cells = np.zeros((3, steps+1))
    num_cells[0, (0)] = cc.num_cc
    num_cells[1, (0)] = sc.num_sc    
    num_cells[2, (0)] = qc.num_qc
    

    initiate_OC(env, directory, labels, n_it=0)    
        
    plot_2d(env, directory, labels, n_it=0)
    plot_3d(env, directory, labels, n_it=0)
    
    counter = 0
    
    for n_it in range(1,steps+1):
        print("iteration %s" %(str(n_it)))        
        agent_solve(env)
        initiate_OC(env, directory, labels, n_it)    
        
        """
        Logic for confluence: works off number of senescent and quiescent 
        cells in env.
        Instead of sys.exit() add a function in environment to remove a segment 
        of cells, then return to orignial logic until below condition reached 
        again.
        """
        if qc.num_qc >= 1: #(sc.num_sc/3):#sc.num_sc:
            if counter == 0:
                env.wound()#Remove a strip of cells
                print("***WOUNDED***")
                timer = n_it
                counter += 1
            else:
                time = n_it - timer
                print("CONFLUENCE DETECTED, time taken: %s itteration == %s hours."%(time, time*6))
                #plot_2d(env,directory, labels, n_it)
                #sys.exit("End")
        
        if freq > 0:
            if not n_it%freq :
                plot_2d(env,directory, labels, n_it)
                plot_3d(env,directory, labels, n_it)    

        else:
            plot_2d(env,directory, labels, n_it)
            plot_3d(env,directory, labels, n_it)    
                        
        num_cells[0, n_it] = cc.num_cc 
        num_cells[1, n_it] = sc.num_sc
        num_cells[2, n_it] = qc.num_qc
        


        print('Senescent cells = %s | Endothelial cells = %s | Quiescent cells = %s'%(cc.num_cc, sc.num_sc, qc.num_qc))
        

        
    growth_curve(num_cells,directory)

    return(env,num_cells)
    
#%%    