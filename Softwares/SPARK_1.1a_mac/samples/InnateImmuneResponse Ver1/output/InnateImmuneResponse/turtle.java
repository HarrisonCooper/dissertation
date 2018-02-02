package InnateImmuneResponse;

import java.util.ArrayList;
import java.io.*;
import org.spark.core.*;
import org.spark.data.*;
import org.spark.space.*;
import org.spark.utils.*;
import org.spark.math.*;

public class turtle extends SpaceAgent
{
	protected double wbcRoll = 0;
	protected double wbcStick = 0;
	protected double wbcMigrate = 0;
	protected double pmnAge = 0;
	protected double pmnPcd = 0;
	protected double monoAge = 0;
	protected double tnfr = 0;
	protected double il1r = 0;
	protected double th0Age = 0;
	protected double th1Age = 0;
	protected double th2Age = 0;
	protected double activation = 0;
	protected double proTh1 = 0;
	protected double proTh2 = 0;
	protected double rth1 = 0;
	protected double rth2 = 0;
	protected double heading = 0;
	
	
	public void _init()
	{
	}
	
	public  turtle(double radius, int shape)
	{
		super(radius, shape);
		_init();
		_createturtle();
	}
	
	public  turtle()
	{
		_init();
		_createturtle();
	}
	
	public void _createturtle()
	{
		this.heading = RandomHelper.random(360.0);
		((CircleNode) this.getNode()).setRadius(0.2);
	}
	
	public void rt(double number)
	{
		this.heading -= number;
	}
	
	public void lt(double number)
	{
		this.heading += number;
	}
	
	public void jump(double number)
	{
		this.move(Vector.getVector(number, this.heading));
	}
	
	public void wiggle()
	{
		this.lt(RandomHelper.random(45.0));
		this.rt(RandomHelper.random(45.0));
		this.jump(1.0);
	}
	
}
