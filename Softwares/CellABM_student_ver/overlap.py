# -*- coding: utf-8 -*-
"""
Correct overlap

@author: Marzieh Tehrani
"""
import random
import math
import numpy as np
import xlwt

from mpl_toolkits.mplot3d import Axes3D, proj3d
from matplotlib.patches import Circle
from quiescent_cells import qc
import matplotlib.pyplot as plt

#from stem_cell import quiescence

from results import save

def initiate_OC(env, directory, labels, n_it):
    cells = []
    """
    As Senescent Cells (CC) cant be pushed around, may remove the env.cancercells and 
    replace with env.quiescentcells - therefore CCs will never be adjusted (however 
    they also may never be seen thus cause overlap.)
    """
    for cell in env.cancercells: #removing env.cancercells causes crash :S 
        cells.append(cell)
    for cell in env.stemcells:
        cells.append(cell)    
    
    values = [[[0 for k in range(2)] for j in range(1)] for i in range(len(cells))]
    
    for cell in range(len(cells)):

        #initial position and displacement values for all the cells (t=0)
        #values[cell][time][0=xi, 1=yi, 2=uxi, 3=uyi]
        values[cell][0] = [cells[cell].pos[0], cells[cell].pos[1]] #[xi, yi]
#        pprint.pprint(values)
        
    plot_values = [[0 for j in range(0)] for i in range(2)] #row1 = tally, # row2 = overlap error
    check_overlap(env, cells, values, plot_values, directory, labels, n_it, OCM_it=0)
    
def check_overlap(env, cells, values, plot_values, directory, labels, n_it, OCM_it):
    overlap_tally = 0    
    overlap_error = 0
    overlap = [[0 for k in range(len(cells))] for j in range(len(cells))]
    
    for i in range(len(overlap)):
        xi = values[i][len(values[i])-1][0]
        yi = values[i][len(values[i])-1][1]
        ri = cells[i].radius
        #print(i, " ", ri)
        for j in range(i, len(overlap)):
            if i !=j:
                xj = values[j][len(values[j])-1][0]
                yj = values[j][len(values[j])-1][1]
                rj = cells[j].radius
                
                if rj == []:
                    rj = ri
                
                #print("1Type ri:", ri, type(ri))
                #print("1Type rj:", rj, type(rj))
                
                #print("ri:", ri, "rj:", rj)
                overlap[i][j] = np.sqrt((xj-xi)**2 +(yj-yi)**2) - (ri+rj)
                #print("****",ri+rj)
                if overlap[i][j] < -(ri+rj)/100.0 :
                    overlap_tally = overlap_tally +1
                    overlap_error = overlap_error + overlap[i][j]
    
    plot_values[0].append(overlap_tally)
    plot_values[1].append(overlap_error*-1.0)

    
    # But what is OCM_it? Overlap class method iteration number?
    if overlap_tally > 0 and OCM_it < 200:
        correct_overlap(env, cells, values, plot_values, directory, labels, n_it, OCM_it)
        
    if overlap_tally == 0 and OCM_it < 200:
        update_pos_ABM(env, values)    
        display_plot_values(plot_values, OCM_it, n_it)
    # Why does it update the radii here but not in the other ones?
    if overlap_tally >= 0 and OCM_it == 200:
        update_pos_ABM(env, values)
        update_radii(env, cells, overlap, directory, labels)
        display_plot_values(plot_values, OCM_it, n_it)

        
def correct_overlap(env, cells, values, plot_values, directory, labels, n_it, OCM_it):

    for i in range(len(values)):
        ri = cells[i].radius
        xi = values[i][len(values[i])-1][0] #current x value (most updated)
        yi = values[i][len(values[i])-1][1] #current y value (most updated)


        neighbour = []
        ''' neighbour ([0:prev_xj, 1:prev_yi, 2:prev_uxj, 3:prev_uyj, 4:kij, 5:kijx, 6:kijy]) '''
        for j in range(len(values)):
            if i != j:
                xj = values[j][len(values[j])-1][0]
                yj = values[j][len(values[j])-1][1]
                rj = cells[j].radius
                
                dist_ij = np.sqrt((xj-xi)**2 +(yj-yi)**2)
                
                if ri == []:
                    ri = rj
                
                if rj == []:
                    rj = ri
                    
                #print("2Type ri:", ri, type(ri))
                #print("2Type rj:", rj, type(rj))

                
                if (dist_ij -(ri+rj)) < -(ri+rj)/100.0 :
                    Lij = ri + rj
                                
                    dist_ijx = abs(xj-xi)
                    dist_ijy = abs(yj-yi)
                    
                    uijx= (xj-xi)/dist_ij
                    uijy= (yj-yi)/dist_ij


                    neighbour.append([Lij, dist_ijx, dist_ijy, uijx, uijy])
        

   
        
        if len(neighbour) > 0:
            totalx = 0
            totaly = 0 
            for j in range(len(neighbour)):
                Lij = neighbour[j][0]
                dist_ijx = neighbour[j][1]
                dist_ijy = neighbour[j][2]
                uijx = neighbour[j][3]
                uijy =  neighbour[j][4]

                totalx = totalx + (uijx*(dist_ijx-Lij))
                totaly = totaly+  (uijy*(dist_ijy-Lij))
            
            new_xi = xi + 0.1*totalx
            new_yi = yi + 0.1*totaly
            
            #to ensure cells don't move off model
            if new_xi > (env.size - ri):
                new_xi = (env.size - ri)-random.random()*0.02

            if new_xi < ri:
                new_xi = ri+random.random()*0.02

            if new_yi > (env.size - ri):
                new_yi = (env.size - ri)-random.random()*0.02

            if new_yi < ri:
                new_yi = ri+random.random()*0.02    
    
            values[i].append([new_xi, new_yi])
            
                # This should send it across to sc.py to turn to quiescent
        """
        Error: Output seems to duplicate cells a lot on itterations (however, may be the automatic reassigning of ID nums - more testing)
        """
        if len(neighbour) > 5:
            print("-----")
            print(cells[i].ID, " | ", cells[i].iscluster)
#            quiescence(cells[i], env)
            if cells[i].iscluster != True:
                cells[i].iscluster = True
                print(cells[i].ID, " | ", cells[i].iscluster)
    #            print("...EC - > QC"
                cells[i].quiescence(env)
            #if cells[i].ID in env.stemcells:
             #   print(cells[i].ID, " is a EC")
            
        else:
            cells[i].isCluster = False
    """     
    #After assigning the local variables their cluster value, this should
    #line up with the cells in the other class
    i=0        
    for agent in env.stemcells:
        agent.iscluster = cells[i].iscluster
    i += 1
    """
    check_overlap(env, cells, values, plot_values, directory, labels, n_it, OCM_it+1)
    
def update_pos_ABM(env, values):
    i = 0        
    for agent in env.cancercells:
        npos = np.zeros(2)
        npos[0] = values[i][len(values[i])-1][0]
        npos[1] = values[i][len(values[i])-1][1]
        agent.move_cell(npos,env)
        i += 1
    for agent in env.stemcells:
        npos = np.zeros(2)
        npos[0] = values[i][len(values[i])-1][0]
        npos[1] = values[i][len(values[i])-1][1]
        agent.move_cell(npos,env)
        i += 1
                
#I Think this is whats making the cells sometimes go tiny on some iterations
def update_radii(env, cells, overlap, directory, labels):
    for i in range(len(overlap)):
        ri = cells[i].radius
        for j in range(len(overlap)):
            rj = cells[j].radius
            if overlap[i][j] < -(ri+rj)/50.0 :
                cells[i].radius = ri - overlap[i][j]/-2.0
                cells[j].radius = rj - overlap[i][j]/-2.0
                
    i = 0        
    for agent in env.cancercells:
        agent.radius = cells[i].radius
        i += 1
    for agent in env.stemcells:
        agent.radius = cells[i].radius
        i += 1
    
def display_plot_values(plot_values, OCM_it, n_it):
    time = []
    for i in range(OCM_it+1):
        time.append(i)
    f, axarr = plt.subplots(2, sharex=True)
    axarr[0].plot(time, plot_values[0])
    axarr[0].set_title('Number of pairs of overlapping cells')
    axarr[1].plot(time, plot_values[1])
    axarr[1].set_title('Total overlap error')
    axarr[1].set_xlabel('OCM_it')        
                