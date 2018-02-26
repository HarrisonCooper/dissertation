# -*- coding: utf-8 -*-
"""
Methods to store and display results

@author: Marzieh, 2014
"""
from mpl_toolkits.mplot3d import Axes3D, proj3d
import matplotlib.pyplot as plt
from matplotlib.patches import Circle
import numpy as np
import pylab
from cancer_cells import cc
from stem_cells import sc
from quiescent_cells import qc
import os
import operator
#import matplotlib.animation as animation
#from matplotlib._png import read_png
#import PIL
#from PIL import Image
#%%
def plot_3d(env, directory, labels, n_it):
    fig = plt.figure()
    ax = fig.gca(projection='3d')
    
    phi = np.linspace(0, 2 * np.pi, 100)
    theta = np.linspace(0, np.pi, 100)
    for n in range (len(env.cancercells)):
        xm = env.cancercells[n].radius * np.outer(np.cos(phi), np.sin(theta)) + env.cancercells[n].pos[0]
        ym = env.cancercells[n].radius * np.outer(np.sin(phi), np.sin(theta)) + env.cancercells[n].pos[1]
        zm = env.cancercells[n].radius * np.outer(np.ones(np.size(phi)), np.cos(theta))
                            
        ax.plot_surface(xm, ym, zm,rstride=10, cstride=10,  linewidth =0, alpha = 0.5, color='g')                    
        
        if labels == True:        
            ax.text(env.cancercells[n].pos[0], env.cancercells[n].pos[1], 0, str(env.cancercells[n].ID)) #IDs
    
    for n in range (len(env.stemcells)):
        xm = env.stemcells[n].radius * np.outer(np.cos(phi), np.sin(theta)) + env.stemcells[n].pos[0]
        ym = env.stemcells[n].radius * np.outer(np.sin(phi), np.sin(theta)) + env.stemcells[n].pos[1]
        zm = env.stemcells[n].radius * np.outer(np.ones(np.size(phi)), np.cos(theta))
        
        ax.plot_surface(xm, ym, zm,rstride=10, cstride=10,  linewidth =0, alpha = 0.5, color='r')
    
        if labels == True:        
            ax.text(env.stemcells[n].pos[0], env.stemcells[n].pos[1], 0, str(env.stemcells[n].ID))     #IDs
    
    ax.set_zlim((-1, env.size))
    ax.set_xlim((0, env.size))
    ax.set_ylim((0, env.size))
    ax.zaxis.set_visible(False)
    ax.view_init(elev=65, azim =235) #camera position
    ax.w_zaxis.line.set_lw(0.)
    ax.set_zticks([])
#    ax._axis3don = False
    
    if n_it == 0:
        figname = 'Initial Setup \n No of Cancer cells = %s \n No of Stem cells = %s' %(str(cc.num_cc), str(sc.num_sc))
        filename = 'Initial_Setup'
    else:
        figname = 'Iteration %s \n No of Cancer cells = %s \n No of Stem cells = %s' %(str(n_it), str(cc.num_cc), str(sc.num_sc))
        filename = "Iteration_" + str(n_it)
    
    ax.set_title(figname)
    save(filename,directory,'3d')
    plt.close(fig)
#%%    
def plot_2d(env, directory, labels, n_it):
    fig = plt.figure()
    ax = fig.add_subplot(111,aspect='equal')

    for n in range (len(env.cancercells)):
        ax.add_artist(Circle((env.cancercells[n].pos[0], env.cancercells[n].pos[1]), env.cancercells[n].radius, fc='g', alpha = 0.5))
        if labels == True:            
            ax.text(env.cancercells[n].pos[0]-0.7, env.cancercells[n].pos[1]-0.5, str(env.cancercells[n].ID), fontsize = 7)     #IDs    
    
    for n in range (len(env.stemcells)):
        
#        print(n, env.stemcells[n].radius)
        
        ax.add_artist(Circle((env.stemcells[n].pos[0], env.stemcells[n].pos[1]), env.stemcells[n].radius, fc='r', alpha = 0.5))
        if labels == True:            
            ax.text(env.stemcells[n].pos[0]-0.7, env.stemcells[n].pos[1]-0.5, str(env.stemcells[n].ID), fontsize=7)     #IDs    
            
    for n in range (len(env.quiescentcells)):
        ax.add_artist(Circle((env.quiescentcells[n].pos[0], env.quiescentcells[n].pos[1]), env.quiescentcells[n].radius, fc='b', alpha = 0.5))
        if labels == True:            
            ax.text(env.quiescentcells[n].pos[0]-0.7, env.quiescentcells[n].pos[1]-0.5, str(env.quiescentcells[n].ID), fontsize = 7)     #IDs   
    
    plt.axis([0, env.size, 0, env.size])
    
    if n_it == 0:
        figname = 'Initial Setup \n No of Cancer cells = %s \n No of Stem cells = %s \n No of Quiescent cells = %s' %(str(cc.num_cc), str(sc.num_sc), str(qc.num_qc))
        filename = 'Initial_Setup' 
    else:
        figname = 'Iteration %s \n No of Cancer cells = %s \n No of Stem cells = %s \n No of Quiescent cells = %s' %(str(n_it), str(cc.num_cc), str(sc.num_sc), str(qc.num_qc))
        filename = "Iteration_" + str(n_it)
    
    ax.set_title(figname)
    save(filename,directory,'2d')
    plt.close(fig)
#%%    
    #Can adapt to have time to heal over number of senescent cells / age.
def growth_curve(num_cells,directory):
    fig=plt.figure()
    plt.plot(num_cells[0,:],'g',label='Senescent cells')
    plt.hold(True)
    plt.plot(num_cells[1,:],'r',label='Stem cells')
    plt.plot(num_cells[2,:],'b',label='Quiescent cells')
    fig.suptitle('growth_curve')
    plt.xlabel('interation')
    plt.ylabel('number of cells')
    plt.legend(loc="upper left", bbox_to_anchor=[0, 1], ncol=2, shadow=True, fancybox=True)
    save('growth_curve.png', directory)
#%%
def save(filename,directory,sub=''):
    if directory:
        d = directory
    else:
        d = 'CellAbm_Output'
    if not os.path.exists(d):
        os.makedirs(d)
    os.chdir(d)
    if sub != '':
        if not os.path.exists(sub):
            os.makedirs(sub)
        os.chdir(sub)
        plt.savefig(filename)
        os.chdir('..')
    else:
        plt.savefig(filename)
    os.chdir('..')    
#%%


