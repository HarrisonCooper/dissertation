package InnateImmuneResponse;

import java.util.ArrayList;
import java.io.*;
import org.spark.core.*;
import org.spark.data.*;
import org.spark.space.*;
import org.spark.utils.*;
import org.spark.math.*;

public class pmn extends turtle
{
	
	
	public void _init()
	{
	}
	
	public  pmn(double radius, int shape)
	{
		super(radius, shape);
		_init();
		_createpmn();
	}
	
	public  pmn()
	{
		_init();
		_createpmn();
	}
	
	public void pmnFunction()
	{
		if ((this.wbcMigrate > 0.0))
		{
			this.pmnBurst();
		}
		else
		{
			if (((InnateImmuneResponseModel.ecRoll.getValue(this) > 3.0) && (this.wbcRoll == 1.0)))
			{
				this.pmnSniff();
			}
			else
			{
				for (double _i = 0; _i < 2.0; _i++)
				{
					this.pmnSniff();
				}
			}
			if (((InnateImmuneResponseModel.tnf.getValue(this) + InnateImmuneResponseModel.paf.getValue(this)) > 1.0))
			{
				this.wbcStick = InnateImmuneResponseModel.il1.getValue(this);
				InnateImmuneResponseModel.il1ra.addValue(this, 1.0);
			}
			if (((this.wbcStick >= 1.0) && (InnateImmuneResponseModel.ecStick.getValue(this) >= 100.0)))
			{
				this.wbcMigrate = (((InnateImmuneResponseModel.tnf.getValue(this) + InnateImmuneResponseModel.il1.getValue(this)) + InnateImmuneResponseModel.gcsf.getValue(this)) - InnateImmuneResponseModel.il10.getValue(this));
				this.setColor(SpaceAgent.YELLOW);
			}
			this.pmnAge -= 1.0;
			if ((this.pmnAge < 0.0))
			{
				this.die();
			}
		}
	}
	
	public void pmnBurst()
	{
		InnateImmuneResponseModel.cytotox.addValue(this, Math.max(10.0, InnateImmuneResponseModel.tnf.getValue(this)));
		InnateImmuneResponseModel.oxy.setValue(this, 100.0);
		InnateImmuneResponseModel.ecRoll.setValue(this, 0.0);
		InnateImmuneResponseModel.ecStick.setValue(this, 0.0);
		InnateImmuneResponseModel.ecMigrate.setValue(this, 0.0);
		InnateImmuneResponseModel.tnf.addValue(this, 1.0);
		InnateImmuneResponseModel.il1.addValue(this, 1.0);
		this.pmnAge = this.pmnPcd;
		this.pmnPcd = ((this.pmnPcd - 1.0) + Math.max(0.0, ((((InnateImmuneResponseModel.tnf.getValue(this) + InnateImmuneResponseModel.infG.getValue(this)) + InnateImmuneResponseModel.gcsf.getValue(this)) - InnateImmuneResponseModel.il10.getValue(this)) / 100.0)));
		if ((this.pmnAge < 0.0))
		{
			this.die();
		}
	}
	
	public void pmnSniff()
	{
		Vector p = new Vector();
		double pmnAhead = 0;
		double pmnRight = 0;
		double pmnLeft = 0;
		
		pmnAhead = 0.0;
		pmnRight = 0.0;
		pmnLeft = 0.0;
		p = this.getPosition();
		Vector _v;
		_v = new Vector(p);
		_v.add(Vector.getVector(1.0, this.heading));
		pmnAhead = InnateImmuneResponseModel.il8.getValue(_v);
		Vector _v1;
		_v1 = new Vector(p);
		_v1.add(Vector.getVector(1.0, (this.heading - 45.0)));
		pmnRight = InnateImmuneResponseModel.il8.getValue(_v1);
		Vector _v2;
		_v2 = new Vector(p);
		_v2.add(Vector.getVector(1.0, (this.heading + 45.0)));
		pmnLeft = InnateImmuneResponseModel.il8.getValue(_v2);
		if (((pmnRight >= pmnAhead) && (pmnRight >= pmnLeft)))
		{
			this.rt(45.0);
		}
		else
		{
			if ((pmnLeft >= pmnAhead))
			{
				this.lt(45.0);
			}
		}
		this.jump(1.0);
	}
	
	public void _createpmn()
	{
	}
	
}
