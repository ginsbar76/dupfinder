package dupFinder;

import esx_acceptor.DirFile_Acceptor;
import esx_acceptor.FileExtension_Acceptor;
import esx_acceptor.Hidden_Acceptor;
import esx_acceptor.I_Acceptor;
import esx_enums.DirFile_Selector;
import esx_enums.Hidden_Selector;
import esx_model.Dico_Interface2;
import esx_model.Dico_interface;
import esx_model.FinderFilter;
import esx_model.FinderModel;
import esx_model.I_collecFunction;

/**
 *  
 * @author macbooksadler
 *
 *	<h1>Main class	</h1>
 *	<p>	Construit le modèle et son controler		</p>
 *	<p>	To do: deplacer les input dans une vue	pour simuler un utilisateur	</p>
 */

public class FinderMain {
	
	public FinderMain() {		
	}
	public static void log(String s){
		System.out.println(s);
	}
	public static void main(String[] args) {
		start();
	}
	/**
	 * le rôle de cette fonction est de lancer le moteur de recherche
	 * 	
	 */
	public static void start(){
		FinderModel fm = new FinderModel();
		DupFinderControler ctrl = new DupFinderControler(fm);
		
		
		log("on commence");
		
		// ________________________________________________________
		//	Définition du point de départ de l'exploration
		fm.setRootPath("//Users//Shared//Photo");
		fm.setRootPath("//Users//macbooksadler//Google Drive");
		fm.setRootPath("//Users//macbooksadler//Pictures"); 
		fm.setRootPath("//Applications//MAMP//htdocs");
		fm.setRootPath("//Users//Shared//Eric");
		fm.setRootPath("//Users//Shared/");
		///Users/macbooksadler/Pictures/Images Vacances Ete
		fm.setRootPath("//Users//macbooksadler//Pictures");
		fm.setRootPath("//Users//Shared//Lili");
		// ________________________________________________________	
		//	définition de la profondeur de recherche
		fm.setMaxDepth(100);
		// ________________________________________________________	
		//	définition du filtre
		FinderFilter ffilter = new FinderFilter(); 
		// ________________________________________________________		
		//I_Acceptor t; 
		//construction du test fichier ou dir
		DirFile_Acceptor tf = new DirFile_Acceptor(DirFile_Selector.FILES_n_DIR);//FILES_n_DIR, FILES_ONLY, DIR_ONLY
		ffilter.AddTest(tf, "FiltreFichiers");
		Hidden_Acceptor th = new Hidden_Acceptor(Hidden_Selector.HIDDEN_or_NOT);//HIDDEN_or_NOT, HIDDEN_ONLY, NOT_HIDDEN_ONLY
		ffilter.AddTest(th, "FiltreVisibilité");
//		Définit les extensions recherchées
		FileExtension_Acceptor tx = new FileExtension_Acceptor(new String[] { ".doc", ".docx", ".xls", ".xlsx", ".xlsm"  });	
		tx.add_extension(".pdf");
		tx.remove_extension(".xlsm");
		tx.remove_extension(".docx");
		tx.remove_extension(".doc");
		tx = new FileExtension_Acceptor(new String[] { ".pdf"  });
		tx = new FileExtension_Acceptor(new String[] { ".jpg", ".JPG"   });
		tx = new FileExtension_Acceptor(new String[] { ".doc", ".docx", ".xls", ".xlsx", ".xlsm" , ".pdf"  });
		tx.add_extension("");
		ffilter.AddTest(tx, "FiltreExtensions");
		
		fm.setFilter(ffilter);
		String regex1 = "[\\p{Punct}\\s]+" ;
		fm.setRegex(regex1);
		// ________________________________________________________	
		//lancement de la recherce
		fm.runExploration();
		// ________________________________________________________	
		test(new Dico_interface());
		test(new Dico_Interface2());
		
		
		DirFile_Acceptor dfa = new DirFile_Acceptor(DirFile_Selector.FILES_n_DIR);//FILES_n_DIR, FILES_ONLY, DIR_ONLY
		dfa.toString();
		
		
		ctrl.refreshViews();
		
	}

	// test
	public static void test(I_collecFunction func){
		func.execFunc();
	}
}
