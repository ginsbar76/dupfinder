package esx_collection;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import esx_tools.VolumeData;
import esx_tools.VolumeTool;

/**
 * 
 * @author Eric
 *	<p>Wrapper pour une collection de HashMap qui associe	</p>
 *	<ul>
 *		<li>une clé unique, qui permet d'indexer le fichier (ou la directory)	</li>
 *		<li>une objet qui implémente l'interface: <strong>Ix_DirItem	</strong>	</li>
 *	</ul>
 *
 *	<p>	les éléments de cette collection sont les noms de fichier et de directory</p>
 *	<p> en plus de gérer une collection de fichiers, ce wrapper gère une ensemble de mots clés tirés des éléments de la collection	</p>
 *	<p> ces mots clés sont tirés des éléments de la collection (nom de fichier/dir) grace à une fonction regex</p>
 *	<p></p>	
 *	<ul>>	Ce wrapper possède:
 *		<li> un générateur d'index. Il s'agit d'un compteur auto-incrémenté à chaque traitement d'un nouvel élément</li>
 *		<li> un lexique de mots clés </li>
 *		<li> une méthode qui parcourt tous les éléments de la collection pour construire le lexique </li>
 *
 *	</ul>
 *	
 *	<p>	</p>
 *	<p>	Le traitement d'un élément</p>
 *	<p> lors de l'ajout d'un élément à notre collection</p>
 *	<ul>	
 *		<li>	</li>
 *	
 *	</ul>
 *	<p> L'interface <strong>Ix_DirItem	</strong> gère  </p>
 *	<ul>
 *		<li> un index </li>
 *		<li> une référenece vers le parent de cet objet </li>
 *	</ul>
 */
public class Volume_xCollection  implements Ix_DirCollection{
	//_____________________________________________________________________________________
	int index;
	HashMap<Integer, Ix_DirItem> volumeMap;
	Ix_Dico lexique;
	//_____________________________________________________________________________________
	/**
	 * Constructeur
	 * 
	 */
	public Volume_xCollection() {
		index=0;
		volumeMap = new HashMap<Integer, Ix_DirItem>();
		lexique = new Ix_VolumeDico();		
		//lexique.setRegex("[\\p{Punct}\\s]+");
	}
	//_____________________________________________________________________________________	
	/**
	 * A supprimer
	<p>Méthode d'ajout d'un nouvel élément à notre collection</p>
	 * <p>dans notre application le FinderModel passe un objet VolumeData qui implémente l'interface Ix_DirItem</p>
	 * <p>La méthode associe un index avec notre objet à insérer dans le Hashmap.</p>
	 * 
	*/
	@Override
	public int AddItem(Ix_DirItem E) {
		int K = index;
		//VolumeData EVT= (VolumeData)E;
		//String name = EVT.getVolumeName();
		//log("Adding :" + K + "  =  "+ name);
		volumeMap.put(K, E);
		index = index +1;
		log("-"+K);
		return K;		
	}
	
	
	/**
	 * incrémenter le compteur de fichier ne pas prendre en comptes les directories vides
	 */
	@Override
	public int AddItem(int index_parent, File file) {
		int K = index;
		VolumeData item = new VolumeData(file);
		item.setRefParent(index_parent);
		item.setRef(index);
		volumeMap.put(K, item);
		index = index +1;
		return K;
	}
	
	/**
	 * 
	 */
	@Override
	/**
	 * Retourne une liste des éléments de notre container qui sont des directories
	 */
	public ArrayList<Ix_DirItem> getAllDir() {
		ArrayList<Ix_DirItem> ret = new ArrayList<Ix_DirItem>();		
		Iterator<Ix_DirItem> it = volumeMap.values().iterator();
		while (it.hasNext()) {
			Ix_DirItem item = it.next(); 
			if (item.isDirectory()){
				ret.add(item);
				//System.out.println(item.toString());
			}
		}
		return ret;
	}

	@Override
	public ArrayList<Ix_DirItem> getAllFiles() {
		ArrayList<Ix_DirItem> ret = new ArrayList<Ix_DirItem>();		
		Iterator<Ix_DirItem> it = volumeMap.values().iterator();
		while (it.hasNext()) {
			Ix_DirItem item = it.next(); 
			if (!item.isDirectory()){
				ret.add(item);
				//System.out.println(item.toString());
			}
		}
		return ret;
	}

	@Override
	public Collections GetAllItems() {
		//log("nombre d'éléments = "+ volumeMap.size());
		Iterator<HashMap.Entry<Integer, Ix_DirItem>> it = volumeMap.entrySet().iterator();
		while (it.hasNext()) {
			HashMap.Entry<Integer, Ix_DirItem> pair = it.next();
			VolumeData EVT= (VolumeData)pair.getValue() ;
		    //log( "clé = "+ pair.getKey() + "  :  " + EVT.getAbsolutePath()) ;
		}
		return null;
	}
	//_____________________________________________________________________________________
	public static void log(String s){
		System.out.println(s);
	}
	//_____________________________________________________________________________________
	public void setRegex(String regex){
		lexique.setRegex(regex);
	}
	/**
	 * parcourt tous les éléments de la collection et les transmet au lexique pour traitement
	 */
	@Override
	public void BuildDico() {
		lexique = new Ix_VolumeDico();
		log("__________________________________________________");
		Iterator it = volumeMap.entrySet().iterator();
		while (it.hasNext()) {
			HashMap.Entry pair = (HashMap.Entry)it.next();
			int K = (int) pair.getKey();
			Ix_DirItem V = (Ix_DirItem)pair.getValue();			
			VolumeData EVT= (VolumeData)V;			
			String name = EVT.getVolumeName();
			//log("dico -> :" + name);
			// on n'ajoute pas les directories
			if(!EVT.isDirectory()){
				lexique.add(name, K);
			}
				
			//log("Dictionnaire -> :" + name);
			// 			
	        //System.out.println(pair.getKey() + " = " + pair.getValue());
	        //it.remove(); // avoids a ConcurrentModificationException
	    }		
	}
	public void ShowFrequencies(){
		lexique.listFrequencies();
	}
	public void ShowFrequentItems(int minFreq){
		lexique.listFrequentItems(minFreq);
	}
	//_____________________________________________________________________________________

	@Override
	public Ix_DirItem getItem(int K) {
		/*
		if(volumeMap.containsKey(K)){
			log("la clé "+K+ "existe");
		}
		else{
			log("cette clé n'existe pas : "+ K);
		}
		*/
		return volumeMap.get(K);
	}

	@Override
	public ArrayList<String> getAllItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Integer> getAllRef(String item) {		
		return lexique.getAllRef(item);
	}

	public ArrayList<String> getAllDuplicates(int minFreq){
		return lexique.getAllDuplicates(minFreq);
	}

}
