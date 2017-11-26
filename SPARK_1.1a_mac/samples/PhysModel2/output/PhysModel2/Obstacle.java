package PhysModel2;

import java.util.ArrayList;
import java.io.*;
import org.spark.core.*;
import org.spark.data.*;
import org.spark.space.*;
import org.spark.utils.*;
import org.spark.math.*;

public class Obstacle extends SpaceAgent
{
	
	
	public void _init()
	{
	}
	
	public  Obstacle(double radius, int shape)
	{
		super(radius, shape);
		_init();
		_createObstacle();
	}
	
	public  Obstacle()
	{
		_init();
		_createObstacle();
	}
	
	public void _createObstacle()
	{
		this.setColor(SpaceAgent.RED);
		((CircleNode) this.getNode()).setRadius(0.5);
	}
	
}
