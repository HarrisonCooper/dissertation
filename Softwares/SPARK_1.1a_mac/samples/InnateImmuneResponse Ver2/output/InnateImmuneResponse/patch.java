package InnateImmuneResponse;

import java.util.ArrayList;
import java.io.*;
import org.spark.core.*;
import org.spark.data.*;
import org.spark.space.*;
import org.spark.utils.*;
import org.spark.math.*;

public class patch extends SpaceAgent
{
	
	
	public void _init()
	{
	}
	
	public  patch(double radius, int shape)
	{
		super(radius, shape);
		_init();
		_createpatch();
	}
	
	public  patch()
	{
		super(0.5, SpaceAgent.SQUARE);
		_init();
		_createpatch();
	}
	
	public void _createpatch()
	{
	}
	
	public void step(SimulationTime time)
	{
		this.injFunction();
		this.ecFunction();
		if ((InnateImmuneResponseModel.infection.getValue(this) > 50.0))
		{
			this.setColor(SpaceAgent.GREY);
		}
		else
		{
			this.setColor((new Vector((InnateImmuneResponseModel.oxy.getValue(this) / 100.0), 0.0, 0.0)));
		}
	}
	
	public void injFunction()
	{
		double randInj = 0;
		
		randInj = 0.0;
		InnateImmuneResponseModel.oxy.setValue(this, Math.max(0.0, (InnateImmuneResponseModel.oxy.getValue(this) - InnateImmuneResponseModel.infection.getValue(this))));
		InnateImmuneResponseModel.endotoxin.addValue(this, (InnateImmuneResponseModel.infection.getValue(this) / 10.0));
		if ((InnateImmuneResponseModel.infection.getValue(this) >= 100.0))
		{
			Vector _v;
			_v = new Vector(this.getPosition());
			_v.add(Vector.getVector(1.0, RandomHelper.random(360.0)));
			ArrayList<patch> _result = Observer.getDefaultSpace().getAgents(_v, 0.5, patch.class);
			if (_result != null)
			{
				for (int _i = 0; _i < _result.size(); _i++)
				{
					patch __agent3 = _result.get(_i);
					InnateImmuneResponseModel.infection.addValue(__agent3, 1.0);
				}
			}
			InnateImmuneResponseModel.infection.setValue(this, 100.0);
		}
		if ((InnateImmuneResponseModel.infection.getValue(this) > 0.0))
		{
			InnateImmuneResponseModel.infection.setValue(this, Math.max(0.0, ((InnateImmuneResponseModel.infection.getValue(this) - InnateImmuneResponseModel.cytotox.getValue(this)) + 0.1)));
		}
	}
	
	public void ecFunction()
	{
		if (((InnateImmuneResponseModel.endotoxin.getValue(this) >= 1.0) || (InnateImmuneResponseModel.oxy.getValue(this) < 60.0)))
		{
			InnateImmuneResponseModel.ecActivation.setValue(this, 1.0);
		}
		if ((InnateImmuneResponseModel.ecActivation.getValue(this) == 1.0))
		{
			this.ecActivate();
		}
		this.patchInjSpread();
	}
	
	public void patchInjSpread()
	{
		InnateImmuneResponseModel.oxy.addValue(this, (-InnateImmuneResponseModel.cytotox.getValue(this)));
		if (((InnateImmuneResponseModel.oxy.getValue(this) < 60.0) && (InnateImmuneResponseModel.oxy.getValue(this) > 30.0)))
		{
			Vector p = new Vector();
			
			InnateImmuneResponseModel.ecRoll.addValue(this, 1.0);
			InnateImmuneResponseModel.oxy.addValue(this, -0.05);
			InnateImmuneResponseModel.paf.addValue(this, 1.0);
			p = this.getPosition();
			Vector _v;
			_v = new Vector(p);
			_v.add(new Vector(0.0, 1.0, 0.0));
			ArrayList<patch> _result = Observer.getDefaultSpace().getAgents(_v, 0.5, patch.class);
			if (_result != null)
			{
				for (int _i = 0; _i < _result.size(); _i++)
				{
					patch __agent3 = _result.get(_i);
					InnateImmuneResponseModel.oxy.addValue(__agent3, -0.05);
				}
			}
			Vector _v1;
			_v1 = new Vector(p);
			_v1.add(new Vector(0.0, -1.0, 0.0));
			ArrayList<patch> _result1 = Observer.getDefaultSpace().getAgents(_v1, 0.5, patch.class);
			if (_result1 != null)
			{
				for (int _i1 = 0; _i1 < _result1.size(); _i1++)
				{
					patch __agent3 = _result1.get(_i1);
					InnateImmuneResponseModel.oxy.addValue(__agent3, -0.05);
				}
			}
			Vector _v2;
			_v2 = new Vector(p);
			_v2.add(new Vector(1.0, 0.0, 0.0));
			ArrayList<patch> _result2 = Observer.getDefaultSpace().getAgents(_v2, 0.5, patch.class);
			if (_result2 != null)
			{
				for (int _i2 = 0; _i2 < _result2.size(); _i2++)
				{
					patch __agent3 = _result2.get(_i2);
					InnateImmuneResponseModel.oxy.addValue(__agent3, -0.05);
				}
			}
			Vector _v3;
			_v3 = new Vector(p);
			_v3.add(new Vector(1.0, 1.0, 0.0));
			ArrayList<patch> _result3 = Observer.getDefaultSpace().getAgents(_v3, 0.5, patch.class);
			if (_result3 != null)
			{
				for (int _i3 = 0; _i3 < _result3.size(); _i3++)
				{
					patch __agent3 = _result3.get(_i3);
					InnateImmuneResponseModel.oxy.addValue(__agent3, -0.05);
				}
			}
			Vector _v4;
			_v4 = new Vector(p);
			_v4.add(new Vector(1.0, -1.0, 0.0));
			ArrayList<patch> _result4 = Observer.getDefaultSpace().getAgents(_v4, 0.5, patch.class);
			if (_result4 != null)
			{
				for (int _i4 = 0; _i4 < _result4.size(); _i4++)
				{
					patch __agent3 = _result4.get(_i4);
					InnateImmuneResponseModel.oxy.addValue(__agent3, -0.05);
				}
			}
			Vector _v5;
			_v5 = new Vector(p);
			_v5.add(new Vector(-1.0, 0.0, 0.0));
			ArrayList<patch> _result5 = Observer.getDefaultSpace().getAgents(_v5, 0.5, patch.class);
			if (_result5 != null)
			{
				for (int _i5 = 0; _i5 < _result5.size(); _i5++)
				{
					patch __agent3 = _result5.get(_i5);
					InnateImmuneResponseModel.oxy.addValue(__agent3, -0.05);
				}
			}
			Vector _v6;
			_v6 = new Vector(p);
			_v6.add(new Vector(-1.0, 1.0, 0.0));
			ArrayList<patch> _result6 = Observer.getDefaultSpace().getAgents(_v6, 0.5, patch.class);
			if (_result6 != null)
			{
				for (int _i6 = 0; _i6 < _result6.size(); _i6++)
				{
					patch __agent3 = _result6.get(_i6);
					InnateImmuneResponseModel.oxy.addValue(__agent3, -0.05);
				}
			}
			Vector _v7;
			_v7 = new Vector(p);
			_v7.add(new Vector(-1.0, -1.0, 0.0));
			ArrayList<patch> _result7 = Observer.getDefaultSpace().getAgents(_v7, 0.5, patch.class);
			if (_result7 != null)
			{
				for (int _i7 = 0; _i7 < _result7.size(); _i7++)
				{
					patch __agent3 = _result7.get(_i7);
					InnateImmuneResponseModel.oxy.addValue(__agent3, -0.05);
				}
			}
		}
		else
		{
			if ((InnateImmuneResponseModel.oxy.getValue(this) <= 30.0))
			{
				Vector p = new Vector();
				
				InnateImmuneResponseModel.ecStick.addValue(this, 1.0);
				InnateImmuneResponseModel.oxy.addValue(this, -0.25);
				InnateImmuneResponseModel.paf.addValue(this, 1.0);
				p = this.getPosition();
				Vector _v8;
				_v8 = new Vector(p);
				_v8.add(new Vector(0.0, 1.0, 0.0));
				ArrayList<patch> _result8 = Observer.getDefaultSpace().getAgents(_v8, 0.5, patch.class);
				if (_result8 != null)
				{
					for (int _i8 = 0; _i8 < _result8.size(); _i8++)
					{
						patch __agent4 = _result8.get(_i8);
						InnateImmuneResponseModel.oxy.addValue(__agent4, -0.25);
					}
				}
				Vector _v9;
				_v9 = new Vector(p);
				_v9.add(new Vector(0.0, -1.0, 0.0));
				ArrayList<patch> _result9 = Observer.getDefaultSpace().getAgents(_v9, 0.5, patch.class);
				if (_result9 != null)
				{
					for (int _i9 = 0; _i9 < _result9.size(); _i9++)
					{
						patch __agent4 = _result9.get(_i9);
						InnateImmuneResponseModel.oxy.addValue(__agent4, -0.25);
					}
				}
				Vector _v10;
				_v10 = new Vector(p);
				_v10.add(new Vector(1.0, 0.0, 0.0));
				ArrayList<patch> _result10 = Observer.getDefaultSpace().getAgents(_v10, 0.5, patch.class);
				if (_result10 != null)
				{
					for (int _i10 = 0; _i10 < _result10.size(); _i10++)
					{
						patch __agent4 = _result10.get(_i10);
						InnateImmuneResponseModel.oxy.addValue(__agent4, -0.25);
					}
				}
				Vector _v11;
				_v11 = new Vector(p);
				_v11.add(new Vector(1.0, 1.0, 0.0));
				ArrayList<patch> _result11 = Observer.getDefaultSpace().getAgents(_v11, 0.5, patch.class);
				if (_result11 != null)
				{
					for (int _i11 = 0; _i11 < _result11.size(); _i11++)
					{
						patch __agent4 = _result11.get(_i11);
						InnateImmuneResponseModel.oxy.addValue(__agent4, -0.25);
					}
				}
				Vector _v12;
				_v12 = new Vector(p);
				_v12.add(new Vector(1.0, -1.0, 0.0));
				ArrayList<patch> _result12 = Observer.getDefaultSpace().getAgents(_v12, 0.5, patch.class);
				if (_result12 != null)
				{
					for (int _i12 = 0; _i12 < _result12.size(); _i12++)
					{
						patch __agent4 = _result12.get(_i12);
						InnateImmuneResponseModel.oxy.addValue(__agent4, -0.25);
					}
				}
				Vector _v13;
				_v13 = new Vector(p);
				_v13.add(new Vector(-1.0, 0.0, 0.0));
				ArrayList<patch> _result13 = Observer.getDefaultSpace().getAgents(_v13, 0.5, patch.class);
				if (_result13 != null)
				{
					for (int _i13 = 0; _i13 < _result13.size(); _i13++)
					{
						patch __agent4 = _result13.get(_i13);
						InnateImmuneResponseModel.oxy.addValue(__agent4, -0.25);
					}
				}
				Vector _v14;
				_v14 = new Vector(p);
				_v14.add(new Vector(-1.0, 1.0, 0.0));
				ArrayList<patch> _result14 = Observer.getDefaultSpace().getAgents(_v14, 0.5, patch.class);
				if (_result14 != null)
				{
					for (int _i14 = 0; _i14 < _result14.size(); _i14++)
					{
						patch __agent4 = _result14.get(_i14);
						InnateImmuneResponseModel.oxy.addValue(__agent4, -0.25);
					}
				}
				Vector _v15;
				_v15 = new Vector(p);
				_v15.add(new Vector(-1.0, -1.0, 0.0));
				ArrayList<patch> _result15 = Observer.getDefaultSpace().getAgents(_v15, 0.5, patch.class);
				if (_result15 != null)
				{
					for (int _i15 = 0; _i15 < _result15.size(); _i15++)
					{
						patch __agent4 = _result15.get(_i15);
						InnateImmuneResponseModel.oxy.addValue(__agent4, -0.25);
					}
				}
			}
		}
		if ((InnateImmuneResponseModel.oxy.getValue(this) < 0.0))
		{
			InnateImmuneResponseModel.oxy.setValue(this, 0.0);
		}
	}
	
	public void ecActivate()
	{
		InnateImmuneResponseModel.ecRoll.addValue(this, 1.0);
		InnateImmuneResponseModel.ecStick.addValue(this, 1.0);
		InnateImmuneResponseModel.paf.addValue(this, 1.0);
		InnateImmuneResponseModel.il8.addValue(this, 1.0);
	}
	
}
