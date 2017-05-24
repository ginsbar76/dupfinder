package dupFinder;
import esd_views.DF_viewConsole;
import esd_views.I_dupViews;
import esx_model.FinderModel;

/**
 * Controler
 * @author macbooksadler
 *
 */
public class DupFinderControler {
	FinderModel model;
	public FinderModel getModel(){
		return model;
	}
	

	I_dupViews console;	
	public DupFinderControler(FinderModel sm) {
		model = sm;
		console = new DF_viewConsole(this);
	}

	public void refreshViews(){
		console.refresh();
	}
}
