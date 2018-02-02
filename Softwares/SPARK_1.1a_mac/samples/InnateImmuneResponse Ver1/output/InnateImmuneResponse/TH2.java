package InnateImmuneResponse;

import java.util.ArrayList;
import java.io.*;
import org.spark.core.*;
import org.spark.data.*;
import org.spark.space.*;
import org.spark.utils.*;
import org.spark.math.*;

public class TH2 extends turtle
{
	
	
	public void _init()
	{
	}
	
	public  TH2(double radius, int shape)
	{
		super(radius, shape);
		_init();
		_createTH2();
	}
	
	public  TH2()
	{
		_init();
		_createTH2();
	}
	
	public void th2Function()
	{
		if ((InnateImmuneResponseModel.il10.getValue(this) > 0.0))
		{
			InnateImmuneResponseModel.il4.addValue(this, InnateImmuneResponseModel.il10.getValue(this));
			InnateImmuneResponseModel.il10.addValue(this, InnateImmuneResponseModel.il10.getValue(this));
		}
		this.wiggle();
		this.th2Age -= 1.0;
		if ((this.th2Age < 0.0))
		{
			this.die();
		}
	}
	
	public void _createTH2()
	{
	}
	
}
