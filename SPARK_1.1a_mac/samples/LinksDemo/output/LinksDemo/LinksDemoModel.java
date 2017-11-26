package LinksDemo;

import java.util.ArrayList;
import java.io.*;
import org.spark.core.*;
import org.spark.data.*;
import org.spark.space.*;
import org.spark.utils.*;
import org.spark.math.*;

public class LinksDemoModel extends SparkModel
{
	public static double initNumber = 0;
	
	
	public void _init()
	{
	}
	
	public void setup()
	{
		SimpleAgent[] a = null;
		
		StandardSpace3d __space = new StandardSpace3d(-10.0, 10.0, -10.0, 10.0, -10.0, 10.0, false, false, false);
		Observer.getInstance().addSpace("space", __space);
		_init();
		int _nn = (int)(LinksDemoModel.initNumber);
		SimpleAgent[] _objects = new SimpleAgent[_nn];
		for (int _i = 0; _i < _nn; _i++)
		{
			_objects[_i] = new SimpleAgent();
		}
		a = _objects;
		if (a != null)
		{
			for (int _i1 = 0; _i1 < a.length; _i1++)
			{
				SimpleAgent __agent2 = a[_i1];
				__agent2.setRandomPosition();
			}
		}
		double _tmpto = (LinksDemoModel.initNumber - 1.0);
		double _tmpstep = 1.0;
		
		for (double i = 1.0; i <= _tmpto; i += _tmpstep)
		{
			SimpleAgent end2 = null;
			SimpleAgent end1 = null;
			
			end1 = a[(int)((i - 1.0))];
			end2 = a[(int)(i)];
			SimpleLink _object = new SimpleLink();
			if (_object != null)
			{
				SimpleLink __agent3 = _object;
				__agent3.connect(end1, end2);
			}
		}
	}
	
	public boolean end(long tick)
	{
		return false;
	}
	
	public boolean begin(long tick)
	{
		return false;
	}
	
	
	public static double get_initNumber()
	{
		return initNumber;
	}
	
	public static void set_initNumber(Double value)
	{
		initNumber = value;
	}
}
