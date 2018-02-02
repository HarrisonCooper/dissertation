package InnateImmuneResponse;

import java.util.ArrayList;
import java.io.*;
import org.spark.core.*;
import org.spark.data.*;
import org.spark.space.*;
import org.spark.utils.*;
import org.spark.math.*;

public class TH0 extends turtle
{
	
	
	public void _init()
	{
	}
	
	public  TH0(double radius, int shape)
	{
		super(radius, shape);
		_init();
		_createTH0();
	}
	
	public  TH0()
	{
		_init();
		_createTH0();
	}
	
	public void step(SimulationTime time)
	{
		this.th0Function();
	}
	
	public void th0Function()
	{
		if (((InnateImmuneResponseModel.il12.getValue(this) + InnateImmuneResponseModel.il4.getValue(this)) > 0.0))
		{
			this.proTh1 = ((InnateImmuneResponseModel.il12.getValue(this) + InnateImmuneResponseModel.infG.getValue(this)) * 100.0);
			this.proTh2 = ((InnateImmuneResponseModel.il10.getValue(this) + InnateImmuneResponseModel.il4.getValue(this)) * 100.0);
			this.rth1 = RandomHelper.random(this.proTh1);
			this.rth2 = RandomHelper.random(this.proTh2);
			if ((this.rth1 > this.rth2))
			{
				this.activation += 1.0;
			}
			if ((this.rth1 < this.rth2))
			{
				this.activation -= 1.0;
			}
		}
		this.wiggle();
		this.th0Age -= 1.0;
		if ((this.th0Age < 0.0))
		{
			this.die();
			return;
		}
		if ((this.activation >= 10.0))
		{
			TH1 _agent;
			_agent = new TH1();
			_agent.moveToSpace(this.getSpace(), this.getPosition());
			if (_agent != null)
			{
				TH1 __agent3 = _agent;
				__agent3.setColor(SpaceAgent.BLUE);
				__agent3.th1Age = this.th0Age;
			}
			this.die();
		}
		if ((this.activation <= -10.0))
		{
			TH2 _agent1;
			_agent1 = new TH2();
			_agent1.moveToSpace(this.getSpace(), this.getPosition());
			if (_agent1 != null)
			{
				TH2 __agent3 = _agent1;
				__agent3.setColor(SpaceAgent.CYAN);
				__agent3.th2Age = this.th0Age;
			}
			this.die();
		}
	}
	
	public void _createTH0()
	{
	}
	
}
