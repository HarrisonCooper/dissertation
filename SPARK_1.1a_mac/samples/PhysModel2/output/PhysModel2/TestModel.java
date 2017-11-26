package PhysModel2;

import java.util.ArrayList;
import java.io.*;
import org.spark.core.*;
import org.spark.data.*;
import org.spark.space.*;
import org.spark.utils.*;
import org.spark.math.*;

public class TestModel extends SparkModel
{
	public static double velocityCoefficient = 0;
	public static double separationCoefficient = 0;
	public static double centralForceCoefficient = 0;
	public static double collisionCoefficient = 0;
	public static double dt = 0;
	public static double initialAgentNumber = 0;
	
	
	public void _init()
	{
		TestModel.velocityCoefficient = 0.6;
		TestModel.separationCoefficient = 0.1;
	}
	
	public boolean begin(long tick)
	{
		return false;
	}
	
	public boolean end(long tick)
	{
		ArrayList<PhysAgent> _result = Observer.getInstance().getAgentsList(PhysAgent.class);
		if (_result != null)
		{
			for (int _i = 0; _i < _result.size(); _i++)
			{
				PhysAgent __agent2 = _result.get(_i);
				__agent2.makeStep(TestModel.dt);
			}
		}
		return false;
	}
	
	public void setup()
	{
		StandardSpace __space = new StandardSpace(-10.0, 10.0, -10.0, 10.0, false, false);
		Observer.getInstance().addSpace("space", __space);
		_init();
		for (double _i = 0; _i < TestModel.initialAgentNumber; _i++)
		{
			Vector p = new Vector();
			double r = 0;
			Vector c = new Vector();
			double d = 0;
			double angle = 0;
			double k = 0;
			
			d = RandomHelper.random(0.4, 0.7);
			r = RandomHelper.random(0.3, 0.5);
			p = Vector.randomVector2d(-10.0, 10.0);
			c = Vector.randomVector2d(0.0, 0.8);
			c.z = RandomHelper.random(0.0, 0.8);
			k = RandomHelper.random(1.0, 10.0);
			angle = RandomHelper.random(360.0);
			p = Vector.getVector(RandomHelper.random(5.0, 10.0), angle);
			this.createCircle(6.0, 0.5, 0.25, c, p, 5.0);
		}
		double _tmpto = 9.0;
		double _tmpstep = 1.0;
		
		for (double i = 0.0; i <= _tmpto; i += _tmpstep)
		{
			
			Obstacle _object = new Obstacle();
			if (_object != null)
			{
				Obstacle __agent3 = _object;
				__agent3.jump(Vector.getVector(3.0, ((360.0 / 10.0) * i)));
			}
		}
	}
	
	public void createCircle(double number, double radius, double dr, Vector color, Vector p, double k)
	{
		PhysAgent[] all = null;
		double r = 0;
		double g = 0;
		
		r = (((Math.PI * radius) / number) + dr);
		int _nn = (int)(number);
		PhysAgent[] _objects = new PhysAgent[_nn];
		for (int _i = 0; _i < _nn; _i++)
		{
			_objects[_i] = new PhysAgent();
		}
		all = _objects;
		g = all[(int)(0.0)].group;
		double _tmpto = (number - 1.0);
		double _tmpstep = 1.0;
		
		for (double i = 0.0; i <= _tmpto; i += _tmpstep)
		{
			double angle = 0;
			
			angle = ((360.0 / number) * i);
			if (all[(int)(i)] != null)
			{
				PhysAgent __agent3 = all[(int)(i)];
				Vector _v;
				_v = new Vector(p);
				_v.add(Vector.getVector(radius, angle));
				__agent3.jump(_v);
				((CircleNode) __agent3.getNode()).setRadius(r);
				__agent3.setColor(color);
				__agent3.group = g;
			}
		}
		double _tmpto2 = (number - 1.0);
		double _tmpstep2 = 1.0;
		
		for (double i = 0.0; i <= _tmpto2; i += _tmpstep2)
		{
			PhysAgent agent1 = null;
			
			agent1 = all[(int)(i)];
			double _tmpto1 = (number - 1.0);
			double _tmpstep1 = 1.0;
			
			for (double j = 0.0; j <= _tmpto1; j += _tmpstep1)
			{
				PhysAgent agent2 = null;
				
				if ((j == i))
				{
					continue;
				}
				agent2 = all[(int)(j)];
				Spring _object = new Spring();
				if (_object != null)
				{
					Spring __agent4 = _object;
					__agent4.init(agent1, agent2, k);
				}
			}
		}
	}
	
	public void createSquare(double d, double radius, Vector color, Vector p, double k)
	{
		PhysAgent a1 = null;
		Spring s3 = null;
		PhysAgent a2 = null;
		Spring s4 = null;
		PhysAgent a3 = null;
		PhysAgent a4 = null;
		double g = 0;
		Spring s1 = null;
		Spring s2 = null;
		
		PhysAgent _object = new PhysAgent();
		a1 = _object;
		PhysAgent _object1 = new PhysAgent();
		a2 = _object1;
		PhysAgent _object2 = new PhysAgent();
		a3 = _object2;
		PhysAgent _object3 = new PhysAgent();
		a4 = _object3;
		if (a1 != null)
		{
			PhysAgent __agent2 = a1;
			__agent2.setColor(color);
			((CircleNode) __agent2.getNode()).setRadius(radius);
			Vector _v;
			_v = new Vector(p);
			_v.add((new Vector(d, d, 0.0)));
			__agent2.jump(_v);
			g = __agent2.group;
		}
		if (a2 != null)
		{
			PhysAgent __agent2 = a2;
			__agent2.setColor(color);
			((CircleNode) __agent2.getNode()).setRadius(radius);
			Vector _v1;
			_v1 = new Vector(p);
			_v1.add((new Vector((-d), d, 0.0)));
			__agent2.jump(_v1);
			__agent2.group = g;
		}
		if (a3 != null)
		{
			PhysAgent __agent2 = a3;
			__agent2.setColor(color);
			((CircleNode) __agent2.getNode()).setRadius(radius);
			Vector _v2;
			_v2 = new Vector(p);
			_v2.add((new Vector((-d), (-d), 0.0)));
			__agent2.jump(_v2);
			__agent2.group = g;
		}
		if (a4 != null)
		{
			PhysAgent __agent2 = a4;
			__agent2.setColor(color);
			((CircleNode) __agent2.getNode()).setRadius(radius);
			Vector _v3;
			_v3 = new Vector(p);
			_v3.add((new Vector(d, (-d), 0.0)));
			__agent2.jump(_v3);
			__agent2.group = g;
		}
		Spring _object4 = new Spring();
		s1 = _object4;
		Spring _object5 = new Spring();
		s2 = _object5;
		Spring _object6 = new Spring();
		s3 = _object6;
		Spring _object7 = new Spring();
		s4 = _object7;
		s1.init(a1, a2, k);
		s2.init(a2, a3, k);
		s3.init(a3, a4, k);
		s4.init(a4, a1, k);
		Spring _object8 = new Spring();
		if (_object8 != null)
		{
			Spring __agent2 = _object8;
			__agent2.init(a1, a3, k);
		}
		Spring _object9 = new Spring();
		if (_object9 != null)
		{
			Spring __agent2 = _object9;
			__agent2.init(a2, a4, k);
		}
	}
	
	
	public static double get_velocityCoefficient()
	{
		return velocityCoefficient;
	}
	
	public static void set_velocityCoefficient(Double value)
	{
		velocityCoefficient = value;
	}
	
	public static double get_separationCoefficient()
	{
		return separationCoefficient;
	}
	
	public static void set_separationCoefficient(Double value)
	{
		separationCoefficient = value;
	}
	
	public static double get_centralForceCoefficient()
	{
		return centralForceCoefficient;
	}
	
	public static void set_centralForceCoefficient(Double value)
	{
		centralForceCoefficient = value;
	}
	
	public static double get_collisionCoefficient()
	{
		return collisionCoefficient;
	}
	
	public static void set_collisionCoefficient(Double value)
	{
		collisionCoefficient = value;
	}
	
	public static double get_dt()
	{
		return dt;
	}
	
	public static void set_dt(Double value)
	{
		dt = value;
	}
	
	public static double get_initialAgentNumber()
	{
		return initialAgentNumber;
	}
	
	public static void set_initialAgentNumber(Double value)
	{
		initialAgentNumber = value;
	}
}
