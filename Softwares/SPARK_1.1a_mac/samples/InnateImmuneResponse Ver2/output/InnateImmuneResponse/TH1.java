package InnateImmuneResponse;

import java.util.ArrayList;
import java.io.*;
import org.spark.core.*;
import org.spark.data.*;
import org.spark.space.*;
import org.spark.utils.*;
import org.spark.math.*;

public class TH1 extends turtle
{
	
	
	public void _init()
	{
	}
	
	public  TH1(double radius, int shape)
	{
		super(radius, shape);
		_init();
		_createTH1();
	}
	
	public  TH1()
	{
		_init();
		_createTH1();
	}
	
	public void step(SimulationTime time)
	{
		this.th1Function();
	}
	
	public void th1Function()
	{
		if ((InnateImmuneResponseModel.il12.getValue(this) > 0.0))
		{
			InnateImmuneResponseModel.infG.addValue(this, (((InnateImmuneResponseModel.infG.getValue(this) + InnateImmuneResponseModel.il12.getValue(this)) + InnateImmuneResponseModel.tnf.getValue(this)) + InnateImmuneResponseModel.il1.getValue(this)));
		}
		this.wiggle();
		this.th1Age -= 1.0;
		if ((this.th1Age < 0.0))
		{
			this.die();
		}
	}
	
	public void _createTH1()
	{
	}
	
}
