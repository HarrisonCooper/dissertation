package InnateImmuneResponse;

import java.util.ArrayList;
import java.io.*;
import org.spark.core.*;
import org.spark.data.*;
import org.spark.space.*;
import org.spark.utils.*;
import org.spark.math.*;

public class pmnMarrow extends turtle
{
	
	
	public void _init()
	{
	}
	
	public  pmnMarrow(double radius, int shape)
	{
		super(radius, shape);
		_init();
		_createpmnMarrow();
	}
	
	public  pmnMarrow()
	{
		_init();
		_createpmnMarrow();
	}
	
	public void step(SimulationTime time)
	{
		this.pmnMarrowFunction();
	}
	
	public void pmnMarrowFunction()
	{
		for (double _i = 0; _i < Math.floor((1.0 + (InnateImmuneResponseModel.totalGcsf / 100.0))); _i++)
		{
			if ((RandomHelper.random(10.0) < 1.0))
			{
				pmn _agent;
				_agent = new pmn();
				_agent.moveToSpace(this.getSpace(), this.getPosition());
				if (_agent != null)
				{
					pmn __agent4 = _agent;
					__agent4.setColor(SpaceAgent.WHITE);
					__agent4.wbcRoll = 1.0;
					__agent4.wbcStick = 0.0;
					__agent4.wbcMigrate = 0.0;
					__agent4.pmnAge = 50.0;
					__agent4.pmnPcd = 10.0;
					__agent4.jump(RandomHelper.random(100.0));
				}
			}
		}
	}
	
	public void _createpmnMarrow()
	{
	}
	
}
