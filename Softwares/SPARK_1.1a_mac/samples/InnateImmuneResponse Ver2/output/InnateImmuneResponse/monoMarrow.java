package InnateImmuneResponse;

import java.util.ArrayList;
import java.io.*;
import org.spark.core.*;
import org.spark.data.*;
import org.spark.space.*;
import org.spark.utils.*;
import org.spark.math.*;

public class monoMarrow extends turtle
{
	
	
	public void _init()
	{
	}
	
	public  monoMarrow(double radius, int shape)
	{
		super(radius, shape);
		_init();
		_createmonoMarrow();
	}
	
	public  monoMarrow()
	{
		_init();
		_createmonoMarrow();
	}
	
	public void step(SimulationTime time)
	{
		this.monoMarrowFunction();
	}
	
	public void monoMarrowFunction()
	{
		if ((RandomHelper.random(100.0) < 1.0))
		{
			mono _agent;
			_agent = new mono();
			_agent.moveToSpace(this.getSpace(), this.getPosition());
			if (_agent != null)
			{
				mono __agent3 = _agent;
				__agent3.setColor(SpaceAgent.GREEN);
				__agent3.wbcRoll = 1.0;
				__agent3.wbcStick = 0.0;
				__agent3.wbcMigrate = 0.0;
				__agent3.monoAge = 50.0;
				__agent3.tnfr = 1000.0;
				__agent3.il1r = 1000.0;
				__agent3.jump(RandomHelper.random(100.0));
			}
		}
	}
	
	public void _createmonoMarrow()
	{
	}
	
}
