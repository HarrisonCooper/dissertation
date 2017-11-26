package InnateImmuneResponse;

import java.util.ArrayList;
import java.io.*;
import org.spark.core.*;
import org.spark.data.*;
import org.spark.space.*;
import org.spark.utils.*;
import org.spark.math.*;

public class InnateImmuneResponseModel extends SparkModel
{
	public static double systemOxy = 0;
	public static double oxyDeficit = 0;
	public static double totalInfection = 0;
	public static double totalTnf = 0;
	public static double totalStnfr = 0;
	public static double totalIl10 = 0;
	public static double totalIl6 = 0;
	public static double totalGcsf = 0;
	public static double step = 0;
	public static double time = 0;
	public static double loopRun = 0;
	public static Grid oxy = null;
	public static Grid ecActivation = null;
	public static Grid ecRoll = null;
	public static Grid ecStick = null;
	public static Grid ecMigrate = null;
	public static Grid cytotox = null;
	public static Grid infection = null;
	public static Grid endotoxin = null;
	public static Grid paf = null;
	public static Grid tnf = null;
	public static Grid stnfr = null;
	public static Grid il1 = null;
	public static Grid sil1r = null;
	public static Grid il1ra = null;
	public static Grid infG = null;
	public static Grid il2 = null;
	public static Grid il4 = null;
	public static Grid il6 = null;
	public static Grid il8 = null;
	public static Grid il10 = null;
	public static Grid il12 = null;
	public static Grid gcsf = null;
	public static double injNumber = 0;
	
	
	public void _init()
	{
		InnateImmuneResponseModel.systemOxy = 0.0;
		InnateImmuneResponseModel.oxyDeficit = 0.0;
		InnateImmuneResponseModel.totalInfection = 0.0;
		InnateImmuneResponseModel.totalTnf = 0.0;
		InnateImmuneResponseModel.totalStnfr = 0.0;
		InnateImmuneResponseModel.totalIl10 = 0.0;
		InnateImmuneResponseModel.totalIl6 = 0.0;
		InnateImmuneResponseModel.totalGcsf = 0.0;
		InnateImmuneResponseModel.step = 0.0;
		InnateImmuneResponseModel.time = 1.0;
		InnateImmuneResponseModel.loopRun = 0.0;
		InnateImmuneResponseModel.oxy = Observer.getDefaultSpace().addDataLayer("oxy", GridFactory.createGrid((int)(((BoundedSpace) Observer.getDefaultSpace()).getXSize()), (int)(((BoundedSpace) Observer.getDefaultSpace()).getYSize())));
		InnateImmuneResponseModel.ecActivation = Observer.getDefaultSpace().addDataLayer("ec-activation", GridFactory.createGrid((int)(((BoundedSpace) Observer.getDefaultSpace()).getXSize()), (int)(((BoundedSpace) Observer.getDefaultSpace()).getYSize())));
		InnateImmuneResponseModel.ecRoll = Observer.getDefaultSpace().addDataLayer("ec-roll", GridFactory.createGrid((int)(((BoundedSpace) Observer.getDefaultSpace()).getXSize()), (int)(((BoundedSpace) Observer.getDefaultSpace()).getYSize())));
		InnateImmuneResponseModel.ecStick = Observer.getDefaultSpace().addDataLayer("ec-stick", GridFactory.createGrid((int)(((BoundedSpace) Observer.getDefaultSpace()).getXSize()), (int)(((BoundedSpace) Observer.getDefaultSpace()).getYSize())));
		InnateImmuneResponseModel.ecMigrate = Observer.getDefaultSpace().addDataLayer("ec-migrate", GridFactory.createGrid((int)(((BoundedSpace) Observer.getDefaultSpace()).getXSize()), (int)(((BoundedSpace) Observer.getDefaultSpace()).getYSize())));
		InnateImmuneResponseModel.cytotox = Observer.getDefaultSpace().addDataLayer("cytotox", GridFactory.createGrid((int)(((BoundedSpace) Observer.getDefaultSpace()).getXSize()), (int)(((BoundedSpace) Observer.getDefaultSpace()).getYSize())));
		InnateImmuneResponseModel.infection = Observer.getDefaultSpace().addDataLayer("infection", GridFactory.createGrid((int)(((BoundedSpace) Observer.getDefaultSpace()).getXSize()), (int)(((BoundedSpace) Observer.getDefaultSpace()).getYSize())));
		InnateImmuneResponseModel.endotoxin = Observer.getDefaultSpace().addDataLayer("endotoxin", GridFactory.createGrid((int)(((BoundedSpace) Observer.getDefaultSpace()).getXSize()), (int)(((BoundedSpace) Observer.getDefaultSpace()).getYSize())));
		InnateImmuneResponseModel.paf = Observer.getDefaultSpace().addDataLayer("paf", GridFactory.createGrid((int)(((BoundedSpace) Observer.getDefaultSpace()).getXSize()), (int)(((BoundedSpace) Observer.getDefaultSpace()).getYSize())));
		InnateImmuneResponseModel.tnf = Observer.getDefaultSpace().addDataLayer("tnf", GridFactory.createGrid((int)(((BoundedSpace) Observer.getDefaultSpace()).getXSize()), (int)(((BoundedSpace) Observer.getDefaultSpace()).getYSize())));
		InnateImmuneResponseModel.stnfr = Observer.getDefaultSpace().addDataLayer("stnfr", GridFactory.createGrid((int)(((BoundedSpace) Observer.getDefaultSpace()).getXSize()), (int)(((BoundedSpace) Observer.getDefaultSpace()).getYSize())));
		InnateImmuneResponseModel.il1 = Observer.getDefaultSpace().addDataLayer("il-1", GridFactory.createGrid((int)(((BoundedSpace) Observer.getDefaultSpace()).getXSize()), (int)(((BoundedSpace) Observer.getDefaultSpace()).getYSize())));
		InnateImmuneResponseModel.sil1r = Observer.getDefaultSpace().addDataLayer("sil-1r", GridFactory.createGrid((int)(((BoundedSpace) Observer.getDefaultSpace()).getXSize()), (int)(((BoundedSpace) Observer.getDefaultSpace()).getYSize())));
		InnateImmuneResponseModel.il1ra = Observer.getDefaultSpace().addDataLayer("il-1ra", GridFactory.createGrid((int)(((BoundedSpace) Observer.getDefaultSpace()).getXSize()), (int)(((BoundedSpace) Observer.getDefaultSpace()).getYSize())));
		InnateImmuneResponseModel.infG = Observer.getDefaultSpace().addDataLayer("inf-g", GridFactory.createGrid((int)(((BoundedSpace) Observer.getDefaultSpace()).getXSize()), (int)(((BoundedSpace) Observer.getDefaultSpace()).getYSize())));
		InnateImmuneResponseModel.il2 = Observer.getDefaultSpace().addDataLayer("il-2", GridFactory.createGrid((int)(((BoundedSpace) Observer.getDefaultSpace()).getXSize()), (int)(((BoundedSpace) Observer.getDefaultSpace()).getYSize())));
		InnateImmuneResponseModel.il4 = Observer.getDefaultSpace().addDataLayer("il-4", GridFactory.createGrid((int)(((BoundedSpace) Observer.getDefaultSpace()).getXSize()), (int)(((BoundedSpace) Observer.getDefaultSpace()).getYSize())));
		InnateImmuneResponseModel.il6 = Observer.getDefaultSpace().addDataLayer("il-6", GridFactory.createGrid((int)(((BoundedSpace) Observer.getDefaultSpace()).getXSize()), (int)(((BoundedSpace) Observer.getDefaultSpace()).getYSize())));
		InnateImmuneResponseModel.il8 = Observer.getDefaultSpace().addDataLayer("il-8", GridFactory.createGrid((int)(((BoundedSpace) Observer.getDefaultSpace()).getXSize()), (int)(((BoundedSpace) Observer.getDefaultSpace()).getYSize())));
		InnateImmuneResponseModel.il10 = Observer.getDefaultSpace().addDataLayer("il-10", GridFactory.createGrid((int)(((BoundedSpace) Observer.getDefaultSpace()).getXSize()), (int)(((BoundedSpace) Observer.getDefaultSpace()).getYSize())));
		InnateImmuneResponseModel.il12 = Observer.getDefaultSpace().addDataLayer("il-12", GridFactory.createGrid((int)(((BoundedSpace) Observer.getDefaultSpace()).getXSize()), (int)(((BoundedSpace) Observer.getDefaultSpace()).getYSize())));
		InnateImmuneResponseModel.gcsf = Observer.getDefaultSpace().addDataLayer("gcsf", GridFactory.createGrid((int)(((BoundedSpace) Observer.getDefaultSpace()).getXSize()), (int)(((BoundedSpace) Observer.getDefaultSpace()).getYSize())));
		InnateImmuneResponseModel.injNumber = 1000.0;
	}
	
	public void setup()
	{
		GridSpace __space = new GridSpace(-50.0, 50.0, -50.0, 50.0, true, true);
		Observer.getInstance().addSpace("space", __space);
		_init();
		InnateImmuneResponseModel.oxy.setValue(100.0);
		double _tmpto1 = (((BoundedSpace) Observer.getDefaultSpace()).getXMax() - 0.5);
		double _tmpstep1 = 1.0;
		
		for (double x = (((BoundedSpace) Observer.getDefaultSpace()).getXMin() + 0.5); x <= _tmpto1; x += _tmpstep1)
		{
			
			double _tmpto = (((BoundedSpace) Observer.getDefaultSpace()).getYMax() - 0.5);
			double _tmpstep = 1.0;
			
			for (double y = (((BoundedSpace) Observer.getDefaultSpace()).getYMin() + 0.5); y <= _tmpto; y += _tmpstep)
			{
				
				patch _object = new patch();
				if (_object != null)
				{
					patch __agent4 = _object;
					__agent4.jump((new Vector(x, y, 0.0)));
				}
			}
		}
		int _nn = (int)(500.0);
		pmn[] _objects = new pmn[_nn];
		for (int _i = 0; _i < _nn; _i++)
		{
			_objects[_i] = new pmn();
		}
		if (_objects != null)
		{
			for (int _i2 = 0; _i2 < _objects.length; _i2++)
			{
				pmn __agent2 = _objects[_i2];
				__agent2.setColor(SpaceAgent.WHITE);
				for (double _i1 = 0; _i1 < 10.0; _i1++)
				{
					__agent2.jump(RandomHelper.random(100.0));
				}
				__agent2.pmnAge = RandomHelper.random(50.0);
				__agent2.wbcRoll = 1.0;
				__agent2.wbcStick = 0.0;
				__agent2.wbcMigrate = 0.0;
				__agent2.pmnPcd = 10.0;
			}
		}
		int _nn1 = (int)(50.0);
		mono[] _objects1 = new mono[_nn1];
		for (int _i3 = 0; _i3 < _nn1; _i3++)
		{
			_objects1[_i3] = new mono();
		}
		if (_objects1 != null)
		{
			for (int _i5 = 0; _i5 < _objects1.length; _i5++)
			{
				mono __agent2 = _objects1[_i5];
				__agent2.setColor(SpaceAgent.GREEN);
				for (double _i4 = 0; _i4 < 10.0; _i4++)
				{
					__agent2.jump(RandomHelper.random(50.0));
				}
				__agent2.tnfr = 0.0;
				__agent2.il1r = 0.0;
				__agent2.activation = 0.0;
			}
		}
		int _nn2 = (int)(50.0);
		TH1[] _objects2 = new TH1[_nn2];
		for (int _i6 = 0; _i6 < _nn2; _i6++)
		{
			_objects2[_i6] = new TH1();
		}
		if (_objects2 != null)
		{
			for (int _i8 = 0; _i8 < _objects2.length; _i8++)
			{
				TH1 __agent2 = _objects2[_i8];
				__agent2.setColor(SpaceAgent.BLUE);
				for (double _i7 = 0; _i7 < 10.0; _i7++)
				{
					__agent2.jump(RandomHelper.random(100.0));
				}
				__agent2.th1Age = RandomHelper.random(100.0);
			}
		}
		int _nn3 = (int)(50.0);
		TH2[] _objects3 = new TH2[_nn3];
		for (int _i9 = 0; _i9 < _nn3; _i9++)
		{
			_objects3[_i9] = new TH2();
		}
		if (_objects3 != null)
		{
			for (int _i11 = 0; _i11 < _objects3.length; _i11++)
			{
				TH2 __agent2 = _objects3[_i11];
				__agent2.setColor(SpaceAgent.CYAN);
				for (double _i10 = 0; _i10 < 10.0; _i10++)
				{
					__agent2.jump(RandomHelper.random(100.0));
				}
				__agent2.th2Age = RandomHelper.random(100.0);
			}
		}
		int _nn4 = (int)(100.0);
		pmnMarrow[] _objects4 = new pmnMarrow[_nn4];
		for (int _i12 = 0; _i12 < _nn4; _i12++)
		{
			_objects4[_i12] = new pmnMarrow();
		}
		if (_objects4 != null)
		{
			for (int _i14 = 0; _i14 < _objects4.length; _i14++)
			{
				pmnMarrow __agent2 = _objects4[_i14];
				__agent2.setColor((new Vector(204.0/255.0, 102.0/255.0, 0)));
				for (double _i13 = 0; _i13 < 10.0; _i13++)
				{
					__agent2.jump(RandomHelper.random(100.0));
				}
			}
		}
		int _nn5 = (int)(100.0);
		monoMarrow[] _objects5 = new monoMarrow[_nn5];
		for (int _i15 = 0; _i15 < _nn5; _i15++)
		{
			_objects5[_i15] = new monoMarrow();
		}
		if (_objects5 != null)
		{
			for (int _i17 = 0; _i17 < _objects5.length; _i17++)
			{
				monoMarrow __agent2 = _objects5[_i17];
				__agent2.setColor((new Vector(1, 153.0/255.0, 0)));
				for (double _i16 = 0; _i16 < 10.0; _i16++)
				{
					__agent2.jump(RandomHelper.random(100.0));
				}
			}
		}
		int _nn6 = (int)(100.0);
		TH0Germ[] _objects6 = new TH0Germ[_nn6];
		for (int _i18 = 0; _i18 < _nn6; _i18++)
		{
			_objects6[_i18] = new TH0Germ();
		}
		if (_objects6 != null)
		{
			for (int _i20 = 0; _i20 < _objects6.length; _i20++)
			{
				TH0Germ __agent2 = _objects6[_i20];
				__agent2.setColor(SpaceAgent.RED);
				for (double _i19 = 0; _i19 < 10.0; _i19++)
				{
					__agent2.jump(RandomHelper.random(100.0));
				}
			}
		}
		int _nn7 = (int)(100.0);
		TH1Germ[] _objects7 = new TH1Germ[_nn7];
		for (int _i21 = 0; _i21 < _nn7; _i21++)
		{
			_objects7[_i21] = new TH1Germ();
		}
		if (_objects7 != null)
		{
			for (int _i23 = 0; _i23 < _objects7.length; _i23++)
			{
				TH1Germ __agent2 = _objects7[_i23];
				__agent2.setColor(SpaceAgent.RED);
				for (double _i22 = 0; _i22 < 10.0; _i22++)
				{
					__agent2.jump(RandomHelper.random(100.0));
				}
			}
		}
		int _nn8 = (int)(100.0);
		TH2Germ[] _objects8 = new TH2Germ[_nn8];
		for (int _i24 = 0; _i24 < _nn8; _i24++)
		{
			_objects8[_i24] = new TH2Germ();
		}
		if (_objects8 != null)
		{
			for (int _i26 = 0; _i26 < _objects8.length; _i26++)
			{
				TH2Germ __agent2 = _objects8[_i26];
				__agent2.setColor(SpaceAgent.RED);
				for (double _i25 = 0; _i25 < 10.0; _i25++)
				{
					__agent2.jump(RandomHelper.random(100.0));
				}
			}
		}
		this.setScalePc();
		InnateImmuneResponseModel.systemOxy = 10201.0;
		InnateImmuneResponseModel.oxyDeficit = 0.0;
		this.injureInfection();
	}
	
	public void setScalePc()
	{
	}
	
	public void injureSterile()
	{
		int _nn = (int)(InnateImmuneResponseModel.injNumber);
		inj[] _objects = new inj[_nn];
		for (int _i = 0; _i < _nn; _i++)
		{
			_objects[_i] = new inj();
		}
		if (_objects != null)
		{
			for (int _i1 = 0; _i1 < _objects.length; _i1++)
			{
				inj __agent2 = _objects[_i1];
				__agent2.jump(RandomHelper.random(Math.sqrt(InnateImmuneResponseModel.injNumber)));
				InnateImmuneResponseModel.oxy.setValue(__agent2, 0.0);
				InnateImmuneResponseModel.ecRoll.setValue(__agent2, 3.0);
				InnateImmuneResponseModel.ecStick.setValue(__agent2, 100.0);
				__agent2.die();
			}
		}
	}
	
	public void injureInfection()
	{
		int _nn = (int)(InnateImmuneResponseModel.injNumber);
		inj[] _objects = new inj[_nn];
		for (int _i = 0; _i < _nn; _i++)
		{
			_objects[_i] = new inj();
		}
		if (_objects != null)
		{
			for (int _i1 = 0; _i1 < _objects.length; _i1++)
			{
				inj __agent2 = _objects[_i1];
				__agent2.jump(RandomHelper.random(Math.sqrt(InnateImmuneResponseModel.injNumber)));
				InnateImmuneResponseModel.infection.setValue(__agent2, 100.0);
				__agent2.die();
			}
		}
	}
	
	public void updateSystemOxy()
	{
		InnateImmuneResponseModel.systemOxy = (InnateImmuneResponseModel.oxy.getTotalNumber() / 100.0);
		InnateImmuneResponseModel.oxyDeficit = (10201.0 - InnateImmuneResponseModel.systemOxy);
		InnateImmuneResponseModel.totalInfection = (InnateImmuneResponseModel.infection.getTotalNumber() / 100.0);
		InnateImmuneResponseModel.totalTnf = (InnateImmuneResponseModel.tnf.getTotalNumber() / 100.0);
		InnateImmuneResponseModel.totalStnfr = (InnateImmuneResponseModel.stnfr.getTotalNumber() / 100.0);
		InnateImmuneResponseModel.totalIl10 = (InnateImmuneResponseModel.il10.getTotalNumber() / 100.0);
		InnateImmuneResponseModel.totalGcsf = (InnateImmuneResponseModel.gcsf.getTotalNumber() / 100.0);
	}
	
	public boolean begin(long tick)
	{
		if ((InnateImmuneResponseModel.loopRun == 100.0))
		{
			InnateImmuneResponseModel.loopRun = 0.0;
			System.out.println(("Above Inj-number is " + String.valueOf(InnateImmuneResponseModel.injNumber)));
			InnateImmuneResponseModel.injNumber = (InnateImmuneResponseModel.injNumber + 50.0);
			return true;
		}
		if ((InnateImmuneResponseModel.time == 58.0))
		{
			InnateImmuneResponseModel.loopRun = (InnateImmuneResponseModel.loopRun + 1.0);
			System.out.println(("oxy-deficit = " + String.valueOf(InnateImmuneResponseModel.oxyDeficit)));
			System.out.println(("total-infection = " + String.valueOf(InnateImmuneResponseModel.totalInfection)));
			System.out.println(("time = " + String.valueOf(InnateImmuneResponseModel.time)));
			return true;
		}
		if ((InnateImmuneResponseModel.oxyDeficit > 8160.8))
		{
			InnateImmuneResponseModel.loopRun = (InnateImmuneResponseModel.loopRun + 1.0);
			System.out.println(("oxy-deficit = " + String.valueOf(InnateImmuneResponseModel.oxyDeficit)));
			System.out.println(("total-infection = " + String.valueOf(InnateImmuneResponseModel.totalInfection)));
			System.out.println(("time + step / 100 = " + String.valueOf((InnateImmuneResponseModel.time + (InnateImmuneResponseModel.step / 100.0)))));
			return true;
		}
		InnateImmuneResponseModel.step += 1.0;
		if ((InnateImmuneResponseModel.step == 100.0))
		{
			InnateImmuneResponseModel.time += 1.0;
			InnateImmuneResponseModel.step = 0.0;
		}
		this.updateSystemOxy();
		ArrayList<TH0> _result = Observer.getInstance().getAgentsList(TH0.class);
		if (_result != null)
		{
			for (int _i = 0; _i < _result.size(); _i++)
			{
				TH0 __agent2 = _result.get(_i);
				__agent2.th0Function();
			}
		}
		ArrayList<patch> _result1 = Observer.getInstance().getAgentsList(patch.class);
		if (_result1 != null)
		{
			for (int _i1 = 0; _i1 < _result1.size(); _i1++)
			{
				patch __agent2 = _result1.get(_i1);
				__agent2.injFunction();
				__agent2.ecFunction();
			}
		}
		ArrayList<pmn> _result2 = Observer.getInstance().getAgentsList(pmn.class);
		if (_result2 != null)
		{
			for (int _i2 = 0; _i2 < _result2.size(); _i2++)
			{
				pmn __agent2 = _result2.get(_i2);
				__agent2.pmnFunction();
			}
		}
		ArrayList<mono> _result3 = Observer.getInstance().getAgentsList(mono.class);
		if (_result3 != null)
		{
			for (int _i3 = 0; _i3 < _result3.size(); _i3++)
			{
				mono __agent2 = _result3.get(_i3);
				__agent2.monoFunction();
			}
		}
		ArrayList<TH1> _result4 = Observer.getInstance().getAgentsList(TH1.class);
		if (_result4 != null)
		{
			for (int _i4 = 0; _i4 < _result4.size(); _i4++)
			{
				TH1 __agent2 = _result4.get(_i4);
				__agent2.th1Function();
			}
		}
		ArrayList<TH2> _result5 = Observer.getInstance().getAgentsList(TH2.class);
		if (_result5 != null)
		{
			for (int _i5 = 0; _i5 < _result5.size(); _i5++)
			{
				TH2 __agent2 = _result5.get(_i5);
				__agent2.th2Function();
			}
		}
		ArrayList<pmnMarrow> _result6 = Observer.getInstance().getAgentsList(pmnMarrow.class);
		if (_result6 != null)
		{
			for (int _i6 = 0; _i6 < _result6.size(); _i6++)
			{
				pmnMarrow __agent2 = _result6.get(_i6);
				__agent2.pmnMarrowFunction();
			}
		}
		ArrayList<monoMarrow> _result7 = Observer.getInstance().getAgentsList(monoMarrow.class);
		if (_result7 != null)
		{
			for (int _i7 = 0; _i7 < _result7.size(); _i7++)
			{
				monoMarrow __agent2 = _result7.get(_i7);
				__agent2.monoMarrowFunction();
			}
		}
		ArrayList<TH1Germ> _result8 = Observer.getInstance().getAgentsList(TH1Germ.class);
		if (_result8 != null)
		{
			for (int _i8 = 0; _i8 < _result8.size(); _i8++)
			{
				TH1Germ __agent2 = _result8.get(_i8);
				__agent2.th1GermFunction();
			}
		}
		ArrayList<TH2Germ> _result9 = Observer.getInstance().getAgentsList(TH2Germ.class);
		if (_result9 != null)
		{
			for (int _i9 = 0; _i9 < _result9.size(); _i9++)
			{
				TH2Germ __agent2 = _result9.get(_i9);
				__agent2.th2GermFunction();
			}
		}
		ArrayList<TH0Germ> _result10 = Observer.getInstance().getAgentsList(TH0Germ.class);
		if (_result10 != null)
		{
			for (int _i10 = 0; _i10 < _result10.size(); _i10++)
			{
				TH0Germ __agent2 = _result10.get(_i10);
				__agent2.th0GermFunction();
			}
		}
		if ((InnateImmuneResponseModel.step == 99.0))
		{
			int _nn = (int)(5.0);
			inj[] _objects = new inj[_nn];
			for (int _i11 = 0; _i11 < _nn; _i11++)
			{
				_objects[_i11] = new inj();
			}
			if (_objects != null)
			{
				for (int _i12 = 0; _i12 < _objects.length; _i12++)
				{
					inj __agent3 = _objects[_i12];
					__agent3.heading = RandomHelper.random(360.0);
					__agent3.jump(RandomHelper.random(100.0));
					InnateImmuneResponseModel.infection.setValue(__agent3, 100.0);
				}
			}
		}
		return false;
	}
	
	public boolean end(long tick)
	{
		InnateImmuneResponseModel.endotoxin.diffuse(1.0);
		InnateImmuneResponseModel.paf.diffuse(0.6);
		InnateImmuneResponseModel.cytotox.diffuse(0.4);
		InnateImmuneResponseModel.tnf.diffuse(0.6);
		InnateImmuneResponseModel.stnfr.diffuse(0.8);
		InnateImmuneResponseModel.il1.diffuse(0.6);
		InnateImmuneResponseModel.infG.diffuse(0.8);
		InnateImmuneResponseModel.il8.diffuse(0.6);
		InnateImmuneResponseModel.il10.diffuse(0.8);
		InnateImmuneResponseModel.il1ra.diffuse(0.8);
		InnateImmuneResponseModel.sil1r.diffuse(0.8);
		InnateImmuneResponseModel.il12.diffuse(0.8);
		InnateImmuneResponseModel.il4.diffuse(0.8);
		InnateImmuneResponseModel.gcsf.diffuse(1.0);
		this.evaporate();
		this.setScalePc();
		return false;
	}
	
	public void evaporate()
	{
		InnateImmuneResponseModel.endotoxin.multiply(0.7);
		InnateImmuneResponseModel.paf.multiply(0.7);
		InnateImmuneResponseModel.cytotox.multiply(0.7);
		InnateImmuneResponseModel.tnf.multiply(0.8);
		InnateImmuneResponseModel.il1.multiply(0.8);
		InnateImmuneResponseModel.stnfr.multiply(0.9);
		InnateImmuneResponseModel.il1ra.multiply(0.9);
		InnateImmuneResponseModel.sil1r.multiply(0.9);
		InnateImmuneResponseModel.infG.multiply(0.8);
		InnateImmuneResponseModel.il8.multiply(0.7);
		InnateImmuneResponseModel.il10.multiply(0.95);
		InnateImmuneResponseModel.il12.multiply(0.8);
		InnateImmuneResponseModel.il4.multiply(0.95);
		InnateImmuneResponseModel.gcsf.multiply(0.95);
	}
	
	
	public static double get_systemOxy()
	{
		return systemOxy;
	}
	
	public static void set_systemOxy(Double value)
	{
		systemOxy = value;
	}
	
	public static double get_oxyDeficit()
	{
		return oxyDeficit;
	}
	
	public static void set_oxyDeficit(Double value)
	{
		oxyDeficit = value;
	}
	
	public static double get_totalInfection()
	{
		return totalInfection;
	}
	
	public static void set_totalInfection(Double value)
	{
		totalInfection = value;
	}
	
	public static double get_totalTnf()
	{
		return totalTnf;
	}
	
	public static void set_totalTnf(Double value)
	{
		totalTnf = value;
	}
	
	public static double get_totalStnfr()
	{
		return totalStnfr;
	}
	
	public static void set_totalStnfr(Double value)
	{
		totalStnfr = value;
	}
	
	public static double get_totalIl10()
	{
		return totalIl10;
	}
	
	public static void set_totalIl10(Double value)
	{
		totalIl10 = value;
	}
	
	public static double get_totalIl6()
	{
		return totalIl6;
	}
	
	public static void set_totalIl6(Double value)
	{
		totalIl6 = value;
	}
	
	public static double get_totalGcsf()
	{
		return totalGcsf;
	}
	
	public static void set_totalGcsf(Double value)
	{
		totalGcsf = value;
	}
	
	public static double get_step()
	{
		return step;
	}
	
	public static void set_step(Double value)
	{
		step = value;
	}
	
	public static double get_time()
	{
		return time;
	}
	
	public static void set_time(Double value)
	{
		time = value;
	}
	
	public static double get_loopRun()
	{
		return loopRun;
	}
	
	public static void set_loopRun(Double value)
	{
		loopRun = value;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static double get_injNumber()
	{
		return injNumber;
	}
	
	public static void set_injNumber(Double value)
	{
		injNumber = value;
	}
}
