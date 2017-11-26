package InnateImmuneResponse;

import java.util.ArrayList;
import java.io.*;
import org.spark.core.*;
import org.spark.data.*;
import org.spark.space.*;
import org.spark.utils.*;
import org.spark.math.*;

public class mono extends turtle
{
	
	
	public void _init()
	{
	}
	
	public  mono(double radius, int shape)
	{
		super(radius, shape);
		_init();
		_createmono();
	}
	
	public  mono()
	{
		_init();
		_createmono();
	}
	
	public void monoFunction()
	{
		if ((InnateImmuneResponseModel.stnfr.getValue(this) <= 100.0))
		{
			this.tnfr = Math.min(100.0, (InnateImmuneResponseModel.tnf.getValue(this) + InnateImmuneResponseModel.stnfr.getValue(this)));
		}
		else
		{
			this.tnfr = Math.min(100.0, (InnateImmuneResponseModel.tnf.getValue(this) - InnateImmuneResponseModel.stnfr.getValue(this)));
		}
		this.il1r = Math.min(100.0, ((InnateImmuneResponseModel.il1.getValue(this) - InnateImmuneResponseModel.il1ra.getValue(this)) - InnateImmuneResponseModel.sil1r.getValue(this)));
		InnateImmuneResponseModel.il1ra.addValue(this, (InnateImmuneResponseModel.il1.getValue(this) / 2.0));
		InnateImmuneResponseModel.stnfr.addValue(this, (this.tnfr / 2.0));
		InnateImmuneResponseModel.sil1r.addValue(this, (this.il1r / 2.0));
		this.activation = (((InnateImmuneResponseModel.endotoxin.getValue(this) + InnateImmuneResponseModel.paf.getValue(this)) + InnateImmuneResponseModel.infG.getValue(this)) - InnateImmuneResponseModel.il10.getValue(this));
		if ((this.activation > 0.0))
		{
			InnateImmuneResponseModel.gcsf.addValue(this, (((InnateImmuneResponseModel.endotoxin.getValue(this) + InnateImmuneResponseModel.paf.getValue(this)) + InnateImmuneResponseModel.tnf.getValue(this)) + InnateImmuneResponseModel.infG.getValue(this)));
			InnateImmuneResponseModel.il8.addValue(this, (InnateImmuneResponseModel.tnf.getValue(this) + InnateImmuneResponseModel.il1.getValue(this)));
			InnateImmuneResponseModel.il12.addValue(this, (InnateImmuneResponseModel.tnf.getValue(this) + InnateImmuneResponseModel.il1.getValue(this)));
			InnateImmuneResponseModel.il10.addValue(this, (InnateImmuneResponseModel.tnf.getValue(this) + InnateImmuneResponseModel.il1.getValue(this)));
			InnateImmuneResponseModel.il1.addValue(this, (((InnateImmuneResponseModel.endotoxin.getValue(this) + InnateImmuneResponseModel.paf.getValue(this)) + this.il1r) + InnateImmuneResponseModel.tnf.getValue(this)));
			InnateImmuneResponseModel.tnf.addValue(this, (((InnateImmuneResponseModel.endotoxin.getValue(this) + InnateImmuneResponseModel.paf.getValue(this)) + this.tnfr) + InnateImmuneResponseModel.infG.getValue(this)));
			if (((this.wbcStick == 1.0) && (InnateImmuneResponseModel.ecStick.getValue(this) >= 100.0)))
			{
				this.wbcMigrate = 1.0;
			}
			if ((this.wbcRoll == 1.0))
			{
				this.wbcStick = 1.0;
			}
			this.wbcRoll = 1.0;
		}
		if ((this.activation < 0.0))
		{
			InnateImmuneResponseModel.il10.addValue(this, (InnateImmuneResponseModel.tnf.getValue(this) + InnateImmuneResponseModel.il1.getValue(this)));
		}
		if ((this.wbcMigrate == 1.0))
		{
			this.heal();
		}
		if ((this.wbcRoll == 1.0))
		{
			this.monoSniff();
			this.jump(1.0);
		}
		else
		{
			for (double _i = 0; _i < 2.0; _i++)
			{
				this.monoSniff();
				this.jump(1.0);
			}
		}
		this.monoAge -= 1.0;
		if ((this.monoAge < 0.0))
		{
			this.die();
		}
		if ((this.activation > 20.0))
		{
			this.activation = 20.0;
		}
	}
	
	public void heal()
	{
		InnateImmuneResponseModel.oxy.setValue(this, 100.0);
		InnateImmuneResponseModel.ecRoll.setValue(this, 0.0);
		InnateImmuneResponseModel.ecStick.setValue(this, 0.0);
		InnateImmuneResponseModel.ecMigrate.setValue(this, 0.0);
		InnateImmuneResponseModel.infection.setValue(this, 0.0);
	}
	
	public void monoSniff()
	{
		Vector p = new Vector();
		double monoLeft = 0;
		double monoRight = 0;
		double monoAhead = 0;
		
		monoAhead = 0.0;
		monoRight = 0.0;
		monoLeft = 0.0;
		p = this.getPosition();
		Vector _v;
		_v = new Vector(p);
		_v.add(Vector.getVector(1.0, this.heading));
		monoAhead = InnateImmuneResponseModel.paf.getValue(_v);
		Vector _v1;
		_v1 = new Vector(p);
		_v1.add(Vector.getVector(1.0, (this.heading - 45.0)));
		monoRight = InnateImmuneResponseModel.paf.getValue(_v1);
		Vector _v2;
		_v2 = new Vector(p);
		_v2.add(Vector.getVector(1.0, (this.heading + 45.0)));
		monoLeft = InnateImmuneResponseModel.paf.getValue(_v2);
		if (((monoRight >= monoAhead) && (monoRight >= monoLeft)))
		{
			this.rt(45.0);
		}
		else
		{
			if ((monoLeft >= monoAhead))
			{
				this.lt(45.0);
			}
		}
	}
	
	public void _createmono()
	{
	}
	
}
