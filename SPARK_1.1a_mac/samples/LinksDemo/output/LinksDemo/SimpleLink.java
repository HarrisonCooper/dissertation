package LinksDemo;

import java.util.ArrayList;
import java.io.*;
import org.spark.core.*;
import org.spark.data.*;
import org.spark.space.*;
import org.spark.utils.*;
import org.spark.math.*;

public class SimpleLink extends SpaceLink
{
	
	
	public void _init()
	{
	}
	
	public  SimpleLink()
	{
		_init();
		_createSimpleLink();
	}
	
	public void _createSimpleLink()
	{
		this.setColor(SpaceAgent.RED);
		this.setWidth(0.2);
	}
	
	public void step(SimulationTime time)
	{
	}
	
}
