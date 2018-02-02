# -*- coding: utf-8 -*-
"""
Solve ABM

@author: Marzieh, 2014
"""
import random
import numpy as np

from cancer_cells import cc
from stem_cells import sc
from messages import messages

# update_messages used for sync mode
def update_messages(env):
        # Update messages for each cancer cell
        for agent in env.cancercells:
            agent.process_messages(env)

        # Create new list that only contains the living
        env.cancercells = ([a for a in env.cancercells if not a.messages.dead]) #and not a.messages.contact])

        # Update cancer cell counter
        cc.num_cc = sum([isinstance(agent,cc) for agent in env.cancercells])
        
        # Update messages for each stem cell
        for agent in env.stemcells:
            agent.process_messages(env)

        # Create new list that only contains the living
        env.stemcells = ([a for a in env.stemcells if not a.messages.dead]) #and not a.messages.contact])

        # Update stem cell counter        
        sc.num_sc = sum([isinstance(agent,sc) for agent in env.stemcells])

#temp_shuffle used for async mode
def temp_shuffle(lst):
    shuffled = list(lst)
    random.shuffle(shuffled)
    return shuffled
    
def agent_solve(env):
        
    new_cancercells= []  # List of new cancer cells created for this iteration
    new_stemcells= []  # List of new stem cells created for this iteration

    if env.mode == 'sync':        
                            
        for agent in env.cancercells:    
            
            #40% chance of migration
            chance = random.random()
            if chance <= 0.4:
                agent.migrate(env)
            else:
                pass
 
            agent.apoptosis(env)

            if not agent.messages.dead:
                new = agent.mitosis(env)
                if new is not None:
                    new_cancercells.append(new)

        # Add new agents to list
        env.cancercells.extend(new_cancercells)

        for agent in env.stemcells:
            
            agent.migrate(env)

            agent.apoptosis(env)

            if not agent.messages.dead:
                new = agent.mitosis(env)
                if new is not None:
                    new_stemcells.append(new)

        # Add new agents to list
        env.stemcells.extend(new_stemcells)
        
        # Update messages
        update_messages(env)
    #return {'cancercells' :env.cancercells, 'stemcells' :env.stemcells}
    
    if env.mode == 'async':
        for agent in temp_shuffle(env.cancercells):
            #40% chance of migration
            chance = random.random()
            if chance <= 0.4:
                agent.migrate(env)
            else:
                pass

            agent.apoptosis(env)

            if not agent.dead:
                new = agent.mitosis(env)
                if new is not None:
                    new_cancercells.append(new)

        # Add new agents to list
        env.cancercells.extend(new_cancercells)
        
        # remove dead cells
        env.cancercells = ([a for a in env.cancercells if not a.dead])
                            
        for agent in temp_shuffle(env.stemcells):    
            
            agent.migrate(env)
 
            agent.apoptosis(env)

            if not agent.dead:
                new = agent.mitosis(env)
                if new is not None:
                    new_stemcells.append(new)

        # Add new agents to list
        env.stemcells.extend(new_stemcells)

        # remove dead cells
        env.stemcells = ([a for a in env.stemcells if not a.dead])
    #return {'cancercells' :env.cancercells, 'stemcells' :env.stemcells}        
        
        
