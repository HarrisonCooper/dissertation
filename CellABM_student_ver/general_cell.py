# -*- coding: utf-8 -*-
"""
Agents - general cell class

@author: Marzieh, 2014
"""
import random
import math

from messages import messages     #Class representing messages passed to agents. 
#Each agent contains its own message class which represents messages passed to it. 

class general_cell:
    # The following attributes will apply to all cell agents
    radius = 1.5
    min_radius = 1
    
    
    def __init__(self, ID=[], stage=[], position=[], direc=[]):
        # These attributes vary per cell
        self.ID = ID        
        self.pos = position
        self.stage = stage
        self.direc = direc
        
        self.dead = False
        self.contact = {}
        self.iscluster = False
        
        # Messages passed to this agent
        self.messages = messages(self.pos, self.dead, self.contact)
        
                
    def process_messages(self,env):
        self.dead = self.messages.dead     # If this cell died in this iteration, set its current state as dead
        self.messages.pos = self.pos    # put position from this iteration into message
#        self.messages.contact = self.contact
        
    def move_cell(self,new_pos,env):
        self.pos = new_pos
    
    def kill_cell(self,env):
        self.dead = True
        self.messages.dead = True
        print("%s ID%s is dead. radius = %s" \
        %(self.__class__.__name__, self.ID, self.radius))
        
    def migrate(self, env):
        mig = False
        cnt = 1
        direction = self.direc + random.uniform(-1*self.max_direc, self.max_direc)
        temppos = [0,0]
        self.speed = random.random()*self.max_speed
        while not mig and cnt <= 10:
            temppos[0] = self.pos[0] + self.speed * math.cos(direction)
            temppos[1] = self.pos[1] + self.speed * math.sin(direction)
            # check that cell has not left edge of model - correct if so.
            if temppos[0]+self.radius < env.size and temppos[1]+self.radius < env.size and \
                temppos[0] >= self.radius and temppos[1] >= self.radius:
                mig = True
            cnt = cnt+1
            direction = direction + (random.uniform(-1*self.max_direc, self.max_direc))
            if cnt == 10:
                print("%s ID%s at position (%s, %s) could not move" \
                %(self.__class__.__name__, self.ID, round(self.pos[0],3), round(self.pos[1],3)))
        if mig:
            self.direc = direction 
            self.move_cell(temppos,env)
                    
    def apoptosis(self,env):
        if self.radius <= self.min_radius:  #cell dies if it gets smaller than minradius
            self.kill_cell(env)


