package InnateImmuneResponse;

import java.util.ArrayList;
import java.io.*;
import org.spark.core.*;
import org.spark.data.*;
import org.spark.space.*;
import org.spark.utils.*;
import org.spark.math.*;

public class TH2Germ extends turtle
{
	
	
	public void _init()
	{
	}
	
	public  TH2Germ(double radius, int shape)
	{
		super(radius, shape);
		_init();
		_createTH2Germ();
	}
	
	public  TH2Germ()
	{
		_init();
		_createTH2Germ();
	}
	
	public void th2GermFunction()
	{
		if ((RandomHelper.random(100.0) < 1.0))
		{
			TH2 _agent;
			_agent = new TH2();
			_agent.moveToSpace(this.getSpace(), this.getPosition());
			if (_agent != null)
			{
				TH2 __agent3 = _agent;
				__agent3.setColor(SpaceAgent.CYAN);
				__agent3.th1Age = 100.0;
				__agent3.activation = 0.0;
			}
		}
	}
	
	public void _createTH2Germ()
	{
	}
	
}
