package Fractals;

import java.util.ArrayList;
import java.io.*;
import org.spark.core.*;
import org.spark.data.*;
import org.spark.space.*;
import org.spark.utils.*;
import org.spark.math.*;

public class Mandelbrot extends SpaceAgent
{
	protected double iteration = 0;
	protected Complex z = Complex.Zero;
	protected Complex c = Complex.Zero;
	protected boolean active = false;
	
	
	public void _init()
	{
	}
	
	public  Mandelbrot(double radius, int shape)
	{
		super(radius, shape);
		_init();
		_createMandelbrot();
	}
	
	public  Mandelbrot()
	{
		_init();
		_createMandelbrot();
	}
	
	public void _createMandelbrot()
	{
		((CircleNode) this.getNode()).setRadius(0.1);
		this.setColor(SpaceAgent.RED);
	}
	
	public void init()
	{
		this.iteration = 0.0;
		this.c = (new Complex(this.getPosition().x, this.getPosition().y));
		this.c = (this.c.div(100.0));
		this.active = true;
	}
	
	public void step(SimulationTime time)
	{
		if ((!this.active))
		{
			return;
		}
		this.z = ((this.z.times(this.z)).plus(this.c));
		this.iteration += 1.0;
		if ((this.z.abs() > 2.0))
		{
			FractalsModel.data.setValue(this, this.iteration);
			this.active = false;
		}
		if ((this.iteration >= FractalsModel.maxIteration))
		{
			FractalsModel.data.setValue(this, 0.0);
			this.active = false;
		}
	}
	
}
