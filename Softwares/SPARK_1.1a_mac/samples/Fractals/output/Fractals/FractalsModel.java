package Fractals;

import java.util.ArrayList;
import java.io.*;
import org.spark.core.*;
import org.spark.data.*;
import org.spark.space.*;
import org.spark.utils.*;
import org.spark.math.*;

public class FractalsModel extends SparkModel
{
	public static Grid data = null;
	public static double numberOfAgents = 0;
	public static double maxIteration = 0;
	
	
	public void _init()
	{
		FractalsModel.data = Observer.getDefaultSpace().addDataLayer("data", GridFactory.createGrid((int)(((BoundedSpace) Observer.getDefaultSpace()).getXSize()), (int)(((BoundedSpace) Observer.getDefaultSpace()).getYSize())));
	}
	
	public void setup()
	{
		GridSpace __space = new GridSpace(-50.0, 50.0, -100.0, 100.0, false, false);
		Observer.getInstance().addSpace("space", __space);
		_init();
		double _tmpto1 = ((BoundedSpace) Observer.getDefaultSpace()).getXMax();
		double _tmpstep1 = 1.0;
		
		for (double x = (((BoundedSpace) Observer.getDefaultSpace()).getXMin() + 0.5); x <= _tmpto1; x += _tmpstep1)
		{
			
			double _tmpto = ((BoundedSpace) Observer.getDefaultSpace()).getYMax();
			double _tmpstep = 1.0;
			
			for (double y = (((BoundedSpace) Observer.getDefaultSpace()).getYMin() + 0.5); y <= _tmpto; y += _tmpstep)
			{
				
				Mandelbrot _object = new Mandelbrot();
				if (_object != null)
				{
					Mandelbrot __agent4 = _object;
					__agent4.jump((new Vector(x, y, 0.0)));
					__agent4.init();
				}
			}
		}
	}
	
	public boolean end(long tick)
	{
		FractalsModel.numberOfAgents = Observer.getInstance().getAgentsNumber(Mandelbrot.class);
		return false;
	}
	
	public boolean begin(long tick)
	{
		return false;
	}
	
	
	
	public static double get_numberOfAgents()
	{
		return numberOfAgents;
	}
	
	public static void set_numberOfAgents(Double value)
	{
		numberOfAgents = value;
	}
	
	public static double get_maxIteration()
	{
		return maxIteration;
	}
	
	public static void set_maxIteration(Double value)
	{
		maxIteration = value;
	}
}
