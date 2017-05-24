package esd_views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import dupFinder.DupFinderControler;
import esx_collection.Ix_DirItem;
import esx_model.FinderModel;

public class DF_viewConsole extends Abs_dupViews{

	public DF_viewConsole(DupFinderControler controler) {
		super(controler);
	}

	@Override
	public void refresh() {
		System.out.println("Console View -> refreshing...");	
		log("--------------------------------------------------------------------------");
		log("Affichage des directories");
		log("--------------------------------------------------------------------------");
		ArrayList<Ix_DirItem> dir_collection0 = ctrl.getModel().getAllDir();
		//logList(dir_collection0);
		logSortedList(dir_collection0);	
		log("--------------------------------------------------------------------------");
		log("Affichage des fichierss");
		log("--------------------------------------------------------------------------");
		ArrayList<Ix_DirItem> file_collection0 = ctrl.getModel().getAllFiles();
		logSortedList(file_collection0);
		log("--------------------------------------------------------------------------");
		log("Affichage des doublons");
		log("--------------------------------------------------------------------------");	
	}

	public static void logSortedList(ArrayList<Ix_DirItem> dir_collection){
		// Sorting 
		Collections.sort(dir_collection,
				new Comparator<Ix_DirItem>() { 
					@Override
					public int compare(Ix_DirItem o1, Ix_DirItem o2) {
						//log(o1.toTitle() +  "  vs  "+ o2.toTitle());
						return  o1.toTitle().compareTo(o2.toTitle());						
					}
				}
				);
		// Printing
		Iterator<Ix_DirItem> dir_Iterator = dir_collection.iterator();
		while (dir_Iterator.hasNext()) {
			Ix_DirItem item = dir_Iterator.next(); 
			System.out.println(item.toString());
		}
		//
	}
	
}
