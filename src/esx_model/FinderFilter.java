package esx_model;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import esx_acceptor.I_Acceptor;
import esx_collection.Ix_DirItem;
import esx_tools.VolumeData;

/**
 * classe spécialisée dans le filtrage de fichiers, utulisable en particulier
 * dans File.List(FileFilter ...)
 * <p>
 * Cette classe dériive de FileFilter
 * </p>
 * <p>
 * en particulier, elle surcharge la méthode Accept(File f)
 * </p>
 * <p>
 * Notre classe possède une collection de test qui seront appelés par
 * Accept(File f). Chaque teste implémente l'interface I_Acceptor. L'interface
 * I_Acceptor déclare une méthode boolean Accept(File f)
 * </p>
 * <p>
 * </p>
 * 
 * @author macbooksadler
 *         <ul>
 *         <li></li>
 *         <li></li>
 *         </ul>
 */
public class FinderFilter implements FileFilter {
	/**
	 * <p>Collection de test à utiliser dans Accept(File f)</p>
	 * <p>Pour la manipulation des test, on associe un titre à notre test.</p>
	 */
	HashMap<String, I_Acceptor> testMap = new HashMap<String, I_Acceptor>();
	/**
	 * <p>Ajoute un nouveau test à notre collection (hashmap)</p>
	 * @param t: test, classe qui implémente I_Acceptor et qui surcharge Accept(File f)
	 * @param titre, nom  de notre test.
	 */
	public void AddTest(I_Acceptor t, String titre){
		testMap.put(titre, t);
	}
	/**
	 * Méthode de validation du File f
	 * <p>Construit un itérator sur la collection des test (testMap) </p>
	 * <p>Pour chaque test (chaque item de notre collection), invoque la méthode Acccept(File f)
	 * <p>	</p>
	 *  @param f: fichier à tester
	 * 	@return true si le fichier satisfait à tous les tests
	 */
	public boolean runTests(File f){
		
		boolean ret = true;
		boolean value_test = false;
		Iterator<Entry<String, I_Acceptor>> it = testMap.entrySet().iterator();
		
		while (it.hasNext()) {
			Entry<String, I_Acceptor> pair = it.next();
			I_Acceptor V = pair.getValue() ; 
			value_test = V.Accept(f);
			ret = ret && value_test;
		}
		String str = f.isDirectory()?"Dir  ":"File  "+f.getName();
		String str_test = ret==true?" OK  ":"  xxx  ";
		//	log("test acceptance " + str_test+ str);
		return ret;
	}
	
	//--------------------------------------------------------
	public boolean accept(File f) {
		return runTests(f);
	}
	//--------------------------------------------------------
	// Depeciated
	public FinderFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * <p>
	 * extension_collection: collection des extensions de fichier que
	 * Accept(File f) va considérer comme valides
	 * </p>
	 */
	Collection<String> extension_collection = new ArrayList<String>();

	public boolean match_extensions(String txt) {
		Iterator<String> it = extension_collection.iterator();
		while (it.hasNext()) {
			boolean okExtension = txt.toLowerCase().endsWith(it.next());
			if (okExtension) {
				// System.out.println("accepted: " + txt);
				return true;
			}
		}
		return false;
	}

	public void add_extension(String txt) {
		extension_collection.add(txt);
		// System.out.println("not yey implemented");
	}

	public void remove_extension(String txt) {
		if (extension_collection.contains(txt)) {
			extension_collection.remove(txt);
		}
	}

	boolean selectHiddenFiles;
	boolean dirOnly;
	boolean fileOnly = true;

	public void selectDirOnly(boolean bval) {
		dirOnly = bval;
	}

	public void selectFilesOnly(boolean bval) {
		fileOnly = bval;
	}

	public void selectHiddenFiles(boolean bval) {
		selectHiddenFiles = bval;
	}

	/**
	 * Constructeur
	 * 
	 * @param selectHiddenFiles:
	 *            false si on veut masquer les fichiers cachés
	 * @param dirOnly:
	 *            true si on ne veut que les directories
	 * @param extensions:
	 *            filtre sur les extensions recherchées si extensions = null;
	 *            pas de filtre, tous les fichiers sont recherchés
	 */
	public FinderFilter(boolean selectHiddenFiles, boolean dirOnly, String[] extensions) {
		this.selectHiddenFiles = selectHiddenFiles;
		this.dirOnly = dirOnly;
		if (extensions != null) {
			int length = extensions.length;
			// this.extensions = new String[length];
			for (String s : extensions) {
				// this.extensions[--length] = s;
				add_extension(s);
			}
		} else {
			extensions = null;
		}

	}

	// -------------------------------------------------------------------------------------------------------------
	/**
	 * fonction d'acceptation des fichiers
	 * <p>
	 * on commence pas distinguer les directories de fichiers
	 * </p>
	 */
	public boolean accept4(File file) {
		boolean ret = true;

		if (file.isDirectory()) {
			ret = true;
		} else {
			return false;
		}

		return ret;
	}

	public boolean accept3(File file) {
		boolean ret = false;

		boolean testHiddenFile = (selectHiddenFiles) && (file.isHidden());
		boolean testDirOnly = (dirOnly) && (file.isDirectory());
		boolean testmatchExtension = false;
		// traitement des extension
		if (extension_collection.size() != 0) {
			if (match_extensions(file.getName()))
				testmatchExtension = true;
		} else {
			return true; // tous les fichiers sont acceptés
		}

		return ret;
	}

	public boolean accept2(File file) {
		// controle 1: vérifie si le statut isHidden est respecté.
		if (file.isHidden() && (selectHiddenFiles == false)) {
			return false; // on ne prend pas les fichiers cachés
		} else {
			// test sur directories only
			if ((file.isDirectory() == true) && dirOnly) {
				return true;
			}
			// directory or file
			else {
				// cas des directories
				if ((file.isDirectory() == true)) {
					// System.out.println(" Directory " + file.getName() );
					return true;
				}
				// cas des fichiers
				if (extension_collection.size() != 0) { // extensions!=null
					return match_extensions(file.getName());
				} else {
					return true; // tous les fichiers sont acceptés
				}
			}
		}
	}

	// -------------------------------------------------------------------------------------------------------------
	/**
	 * 
	 */
	public String toString() {
		String str = "Filtre en cours:  ";
		Iterator<String> it = extension_collection.iterator();

		if (extension_collection.size() == 0) {
			return "pas de filtre";
		}

		while (it.hasNext()) {
			String element = it.next(); // retourne un objet de type String
			str = str + element + " ; ";
		}
		return str;
	}

	/**
	 * 
	 */
	public void printFilter() {
		System.out.print("Extensions de fichier...");
		Iterator<String> it = extension_collection.iterator();
		while (it.hasNext()) {

			String element = it.next(); // retourne un objet de type String
			System.out.print(element + " ; ");
		}
		System.out.println();
		/*
		 * //vérification des extensions int n_extensions = extensions.length;
		 * for (int i=0;i<n_extensions;i++) { System.out.print(extensions[i] +
		 * " ; "); }
		 */
	}

	// -------------------------------------------------------------------------------------------------------------
	// _____________________________________________________________________________________
	public static void log(String s) {
		System.out.println(s);
	}

}
