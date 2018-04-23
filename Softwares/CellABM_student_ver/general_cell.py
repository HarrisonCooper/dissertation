# -*- coding: utf-8 -*-
"""
Agents - general cell class

@author: Marzieh, 2014
@commented: Harrison Paul Cooper, 2017
@updated: Harrison Paul Cooper, 2018
@last_updated: Harrison Paul Cooper, 23/04/2018
"""
import random
import math


from messages import messages


class general_cell:
    """

    """
    def __init__(self, ID=[], stage=[], position=[], direc=[], turnover=[], radius=[], area=[]):
        """
        How each cell is defined.

        These attributes vary per cell
        :param ID:
        :param stage:
        :param position:
        :param direc:
        :param turnover:
        :param radius:
        :param area:
        """
        self.ID = ID
        self.pos = position
        self.stage = stage
        self.direc = direc
        self.turnover = turnover
        self.radius = radius
        self.area = area
        self.dead = False
        self.iscluster = False
        
        # Messages passed to this agent
        self.messages = messages(self.pos, self.dead)

    def process_messages(self):
        """

        :return:
        """
        self.dead = self.messages.dead     # If this cell died in this iteration, set its current state as dead
        self.messages.pos = self.pos    # put position from this iteration into message

    def move_cell(self, new_pos):
        """

        :param new_pos:
        :return:
        """
        self.pos = new_pos
    
    def kill_cell(self):
        """

        :return:
        """
        self.dead = True
        self.messages.dead = True
        # print("%s ID%s is dead. radius = %s turnover = %s"
        #     % (self.__class__.__name__, self.ID, self.radius, self.turnover))
        
    def migrate(self, env):
        """

        :param env:
        :return:
        """
        mig = False
        cnt = 1
        direction = self.direc + random.uniform(-1*self.max_direc, self.max_direc)
        temppos = [0, 0]
        self.speed = random.random()*self.max_speed
        while not mig and cnt <= 10:
            temppos[0] = self.pos[0] + self.speed * math.cos(direction)
            temppos[1] = self.pos[1] + self.speed * math.sin(direction)
            # check that cell has not left edge of model - correct if so.
            if temppos[0]+self.radius < env.size and temppos[1]+self.radius < env.size and \
                temppos[0] >= self.radius and temppos[1] >= self.radius:
                mig = True
            cnt += 1
            direction = direction + (random.uniform(-1*self.max_direc, self.max_direc))
            # if cnt == 10:
            #    print("%s ID%s at position (%s, %s) could not move"
            #          % (self.__class__.__name__, self.ID, round(self.pos[0], 3), round(self.pos[1], 3)))
        if mig:
            self.direc = direction 
            self.move_cell(temppos)
                    
    def apoptosis(self):
        """

        :return:
        """
        if self.radius <= self.min_radius:  # cell dies if it gets smaller than minradius
            self.kill_cell()
