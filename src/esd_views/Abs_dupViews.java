package esd_views;

import dupFinder.DupFinderControler;
import esx_model.FinderModel;

public abstract class Abs_dupViews  implements I_dupViews {
	DupFinderControler ctrl;

	public Abs_dupViews(DupFinderControler controler) {
		ctrl = controler;
	}

	@Override
	public FinderModel getModel() { 
		return ctrl.getModel();
	}
	
	public static void log(String s) {
		System.out.println(s);
	}

}
