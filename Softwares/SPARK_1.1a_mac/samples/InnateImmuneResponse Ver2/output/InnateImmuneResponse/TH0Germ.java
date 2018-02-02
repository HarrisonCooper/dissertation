package InnateImmuneResponse;

import java.util.ArrayList;
import java.io.*;
import org.spark.core.*;
import org.spark.data.*;
import org.spark.space.*;
import org.spark.utils.*;
import org.spark.math.*;

public class TH0Germ extends turtle
{
	
	
	public void _init()
	{
	}
	
	public  TH0Germ(double radius, int shape)
	{
		super(radius, shape);
		_init();
		_createTH0Germ();
	}
	
	public  TH0Germ()
	{
		_init();
		_createTH0Germ();
	}
	
	public void step(SimulationTime time)
	{
		this.th0GermFunction();
	}
	
	public void th0GermFunction()
	{
		if ((RandomHelper.random(100.0) < 1.0))
		{
			TH0 _agent;
			_agent = new TH0();
			_agent.moveToSpace(this.getSpace(), this.getPosition());
			if (_agent != null)
			{
				TH0 __agent3 = _agent;
				__agent3.setColor((new Vector(238.0/255.0, 130.0/255.0, 238.0/255.0)));
				__agent3.th0Age = 100.0;
				__agent3.activation = 0.0;
			}
		}
	}
	
	public void _createTH0Germ()
	{
	}
	
}
