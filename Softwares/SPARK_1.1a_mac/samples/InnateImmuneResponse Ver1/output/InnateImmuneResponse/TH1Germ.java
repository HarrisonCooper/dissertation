package InnateImmuneResponse;

import java.util.ArrayList;
import java.io.*;
import org.spark.core.*;
import org.spark.data.*;
import org.spark.space.*;
import org.spark.utils.*;
import org.spark.math.*;

public class TH1Germ extends turtle
{
	
	
	public void _init()
	{
	}
	
	public  TH1Germ(double radius, int shape)
	{
		super(radius, shape);
		_init();
		_createTH1Germ();
	}
	
	public  TH1Germ()
	{
		_init();
		_createTH1Germ();
	}
	
	public void th1GermFunction()
	{
		if ((RandomHelper.random(100.0) < 1.0))
		{
			TH1 _agent;
			_agent = new TH1();
			_agent.moveToSpace(this.getSpace(), this.getPosition());
			if (_agent != null)
			{
				TH1 __agent3 = _agent;
				__agent3.setColor(SpaceAgent.BLUE);
				__agent3.th1Age = 100.0;
				__agent3.activation = 0.0;
			}
		}
	}
	
	public void _createTH1Germ()
	{
	}
	
}
