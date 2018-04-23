#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Mon Apr 23 16:30:43 2018

@author: Harrison Paul Cooper
"""
from senescent_cells import sc
import unittest
import random
import math


class TestSenescentCells(unittest.TestCase):

    def test_growth(self):
        senescent_cell = sc(ID=1, stage=1, pos=[0, 0], direc=random.random() * 2 * math.pi,
                            turnover=1, radius=5, area=25*math.pi)
        cell = sc.growth(senescent_cell)
        self.assertEquals(cell.area, 37.5*math.pi)
        self.assertEquals(cell.stage, 2)
        self.assertEquals(cell.radius, math.sqrt((cell.area/math.pi)))

if __name__ == '__main__':
    unittest.main()
