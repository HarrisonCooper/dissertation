package LinksDemo;

import java.util.ArrayList;
import java.io.*;
import org.spark.core.*;
import org.spark.data.*;
import org.spark.space.*;
import org.spark.utils.*;
import org.spark.math.*;

public class SimpleAgent extends SpaceAgent
{
	protected Vector v = new Vector();
	
	
	public void _init()
	{
	}
	
	public  SimpleAgent(double radius, int shape)
	{
		super(radius, shape);
		_init();
		_createSimpleAgent();
	}
	
	public  SimpleAgent()
	{
		_init();
		_createSimpleAgent();
	}
	
	public void _createSimpleAgent()
	{
		this.setColor(SpaceAgent.GREEN);
		((CircleNode) this.getNode()).setRadius(0.5);
	}
	
	public void step(SimulationTime time)
	{
		this.v.add(Vector.randomVector2d(-0.03, 0.03));
		this.v = (new Vector(this.v).truncate(-0.2, 0.2));
		this.move(this.v);
	}
	
}
