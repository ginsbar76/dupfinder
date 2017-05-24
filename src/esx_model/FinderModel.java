package esx_model;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import esx_acceptor.DirFile_Acceptor;
import esx_acceptor.Hidden_Acceptor;
import esx_acceptor.I_Acceptor;
import esx_collection.Ix_DirCollection;
import esx_collection.Ix_DirItem;
import esx_collection.Volume_xCollection;
import esx_enums.DirFile_Selector;
import esx_enums.Hidden_Selector;
import esx_tools.VolumeData;
import esx_tools.VolumeTool;

/**
 * Moteur de recherche à partir d'une racine avec une profonder maxi
 * <p> classe de stockage de l'image d'une directory </p>
 * <p>  
 * <p> Le stockage est réalisé à partir une méthode spéciale. C'est la classe imbriquée "ExplorerNode" qui se charge de remplir le container  "itemCollection" </p>
 * 
 * 
 * @author macbooksadler
 *			<p> Ix_DirCollection itemCollection = new Volume_xCollection(); </p>
 *          <p>Ix_DirCollection: interface pour les container dont l'objet est de stocker  le résultat de l'exploration</p>
 *			<p> Ix_DirCollection permet ensuite de construire un lexique de mots clés à partir des éléments stockés</p>
 *        
 *          
 *         <p>A partir de "Ix_DirCollection", on peut extraire une liste</p>
 *         	<ul>
 *         	<li>les noms de fichiers: 			public ArrayList<Ix_DirItem> getAllFiles();		</li>
 *         	<li>les noms de directories:	 	public ArrayList<Ix_DirItem> getAllDir();		</li>
 *         	
 *         <li>public void BuildDico():	pour construire une liste de mots clés, parties des noms de fichier et de directory qui sont utilisés pour établir des ressemblances entre noms de fichier oou de directoty</li> 
 *          </ul>
 */
public class FinderModel {
	// _____________________________________________________________________________________
	/**
	 * <p>constructeur: </p>
	 * <p> </p>
	 */
	public FinderModel() { 
	}
	// 	_____________________________________________________________________________________
	String rootPath;
	Ix_DirCollection itemCollection = new Volume_xCollection();	
	// 	_____________________________________________________________________________________
	/**	
	 * Filtre de sélection de fichier
	 * ffilter: objet FinderFilter hérité de FileFilter, permet de filtrer les
	 * fichier par: File[] files = f.listFiles(ffilter);
	 */
	FinderFilter ffilter = null;
	/**
	 * 	définition du filtre de recherche
	 * 	@param ff: filtre de recherche, classe = FinderFilter, héritée de FileFilter. Le principal rôle de FinderFilter est de surcharger la méthode <strong>accept(File)</strong> 
	 * 	@return: le filtre de recherche
	 */
	public FinderFilter setFilter(FinderFilter ff) {
		ffilter = ff;
		return ffilter;
	}
	/**
	 *	accès au filtre de recherhce de fichier
	 * 	@return: le filtre de recherche
	 */
	public FinderFilter getFilter() {
		return ffilter;
	}

	// _____________________________________________________________________________________
	/**
	 * valeur définissant la profondeur de recherche à partie de la racine
	 */
	int maxDepth;
	/**
	 * définit la profondeur maxi de la recherche
	 * @param d: profondeur maxi
	 * @return: la valeur de la profondeur
	 */
	public int setMaxDepth(int d) {
		maxDepth = d;
		return maxDepth;
	}
	/**
	 * Retourne la profondeur maxi de recherche
	 * @return: la valeur de la profondeur de recherche
	 */
	int getMaxDepth() {
		return maxDepth;
	}
	// _____________________________________________________________________________________
	/**
	 * <p>définit le chemin de la racine de recherche </p>
	 * <p>return false si le chemin indiqué n'existe pas ou est inaccessible</p>
	 * @param s: chemin de la racine de recherche
	 * @return: retourne true si cette racine existe réellement sinon, retourne False
	 */
	public boolean setRootPath(String s) {
		rootPath = s;
		return true;
	}
	/**
	 * Retourne le chemin de la racine
	 * @return: le chemin de la racine
	 */
	String getRootPath() {
		return rootPath;
	}
	// _____________________________________________________________________________________
	/**<p>Définit le regex pour la construction du lexique	</p>
	 */
	public void setRegex(String regex){
		itemCollection.setRegex(regex);
	}
	// _____________________________________________________________________________________
	/**
	 * <p>runExploration() méthode principale </p>
	 * <ul>
	 * 		<li>	définit la racine de la recherche</li>
	 * 		<li> effectue la rehcerhce des fichiers et directories de cette racine</li>
	 * 		<li> tous les fichiers et directories trouvés sont stockés dans la  <strong> collection </strong></li>
	 * 		<li> cette collection implémente l'interface <strong>Ix_DirCollection</strong></li>
	 * 		<li>	définit le masque de découpage des mots clé pour extraire les mots clés de cette collection</li>
	 * 		<li> A partir de cette collection et du lexique de mots clés, le programme</li>
	 * <ul>
	 * 	<li>la fréquence des mots clés</li>
	 * </ul>
	 * </ul>
	 * 
	 * 	
	 * 
	 * <ul>
	 * <li>	puis construction d'un lexique du container</li>
	 * </ul>
	 * 
	 */
	public void runExploration() {  

		File froot = new File(getRootPath());
		ExplorerNode root = new ExplorerNode(froot, 0, 0, getMaxDepth());
		log("Affichage des directories");
		log("--------------------------------------------------------------------------");	
		ArrayList<Ix_DirItem> dir_collection0 = itemCollection.getAllDir();
		//logList(dir_collection0);
		logSortedList(dir_collection0);	
		log("--------------------------------------------------------------------------");	
		log("Affichage des fichiers");
		ArrayList<Ix_DirItem> file_collection0 = itemCollection.getAllFiles();
		//logList(file_collection0);
		log("---------------------------------------------");
		log("Liste triée");
		log("---------------------------------------------");
		//logSortedList(file_collection0);		
		log("---------------------------------------------");
		//construction du lexique
		itemCollection.BuildDico();
		//----------------------------------------------------------------------------------
		//traitement des résultats
		//----------------------------------------------------------------------------------
		//Affichage de toutes les directories
		//ArrayList<Ix_DirItem> dir_collection = itemCollection.getAllDir(); 
		//----------------------------------------------------------------------------------
		//Affichage de tous les fichiers trouvés et correspondant au filtre de recherche		
		//System.out.println("Présentation des principales fréquences des mots clés");
		//itemCollection.ShowFrequentItems(10);
		//listAllDuplicates(10);		
	}

	/**
	 * pour chacun item frequent, liste les éléments de notre collection qui
	 * contiennent cet item
	 * 
	 * A partir du lexique de la collection, liste tous les item qui sont
	 * dupliqués au moins "minFreq" fois
	 */
	public void listAllDuplicates(int minFreq) {
		// préparatoire
		//log("travail préparatoire");
		//itemCollection.GetAllItems();

		//log("fin du listing");
		// recherche des mots clés avec plusieurs occurences
		ArrayList<String> itemList = itemCollection.getAllDuplicates(minFreq);
		//System.out.println("Nombre de duplication " + itemList.size());
		for (String item : itemList) {
			System.out.println("---------------------------------------------");
			System.out.println("élément :" + item);
			System.out.println("---------------------------------------------");
			// recherche toutes les éléments de la collection qui contiennent ce
			// mot clé
			ArrayList<Integer> refList = itemCollection.getAllRef(item);
			//-----------------------------------------------------------------------
			//sort refList
			Collections.sort(refList,
					new Comparator<Integer>() { 

						@Override
						public int compare(Integer o1, Integer o2) {
							Ix_DirItem t1 = itemCollection.getItem(o1);
							Ix_DirItem t2 = itemCollection.getItem(o2);
							//log(t1.toTitle() +  "  vs  "+ t2.toTitle());
							return  t1.toTitle().compareTo(t2.toTitle());
						}
					}
					);
			//-----------------------------------------------------------------------
			//print
			for (int ref : refList) {
				// System.out.println(ref);
				Ix_DirItem text = itemCollection.getItem(ref);
				log(text.toString());
				/*
				if (text != null) {
					VolumeData EVT = (VolumeData) text;
					//String name = EVT.getPath();
					//log(name);
					//EVT.toString();
					//text.toTitle();
				} else {
					log(ref + "  x");
				}
				*/
			}
		}

		// pour chaque élément de cette liste, obient un tableau des références
		// vers
		// les éléments de notre collection
		// ArrayList<Integer> refList = itemCollection.getAllRef(item)
		// Affiche chaque elément de notre collection dont la clé est fournie
		// l'élément de refList
	}

	// _____________________________________________________________________________________
	/**
	 * 	<p>classe interne qui effectue la recherhce,</p>
	 *  <p>utilise la fonction listFiles avec filtre de recherche de fichier </p>
	 *  </p>Cette classe effectue une recherche par recurrence pour descendre la hierarchie</p>
	 *  <p> puis ajoute au container les fichiers trouvés. </p>
	 *  <p>Le container qui reçoit les fichiers est responsable de faire la distinction entre les fichiers et les directories</p>
	 * 
	 * @author macbooksadler
	 *
	 */
	private class ExplorerNode {

		public ExplorerNode(File f, int depth, int index_parent, int maxdepth) {
			File[] files = f.listFiles(ffilter);

			for (File file : files) {
				if (file.isDirectory()) {
					int K = itemCollection.AddItem(index_parent, file);
					if (depth < maxdepth) {
						ExplorerNode nd = new ExplorerNode(file, depth + 1, K, maxdepth);
					}
				} else {
					itemCollection.AddItem( index_parent, file);
					log("Adding file  "+file.getName());
				}
			}
			
		}

	}

	// _____________________________________________________________________________________
	public static void logList(ArrayList<Ix_DirItem> dir_collection){
		Iterator<Ix_DirItem> dir_Iterator = dir_collection.iterator();
		while (dir_Iterator.hasNext()) {
			Ix_DirItem item = dir_Iterator.next(); 
			System.out.println(item.toString());
		}
	}
	
	// _____________________________________________________________________________________
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
	
	// _____________________________________________________________________________________
	public static void log(String s) {
		System.out.println(s);
	}
}
