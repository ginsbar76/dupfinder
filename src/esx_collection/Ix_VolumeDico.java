package esx_collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>Map<String, ArrayList<Integer>> lexique; </p>
 * <p> container des mots clés du lexique</p>
 * <p> chaque élément du lexique associe un mot clé à une liste de référence </p>
 * <p> les références sont des entiers qui pointes vers les éléments du container de fichier</p>
 * <p> (le container de fichier associe une clé - un entier - à un fichier </>
 * @author macbooksadler
 *
 */
public class Ix_VolumeDico implements Ix_Dico{
	//----------------------------------------------
	String regex;
	public void setRegex(String rgx){
		this.regex = rgx;
	}
	//----------------------------------------------
	public Ix_VolumeDico() {
		lexique = new HashMap<String, ArrayList<Integer>>();
	}
	public int getFrequency(String item){
		ArrayList<Integer> l = lexique.get(item);
		int freq = l.size();
		return freq;
	}
	public void listFrequencies(){
		Iterator<String> dico_Iterator = lexique.keySet().iterator();		
		while (dico_Iterator.hasNext()) {
			String item = dico_Iterator.next();
			int freq = getFrequency(item);
			System.out.printf("%d  : %s %n",freq,item);
		}
	}
	public void listFrequentItems(int miniFreq){
		Iterator<String> dico_Iterator = lexique.keySet().iterator();		
		while (dico_Iterator.hasNext()) {
			String item = dico_Iterator.next();
			int freq = getFrequency(item);
			if (freq>= miniFreq){
				System.out.printf("%d  : %s %n",freq,item);
			}
			
		}
	}
	//---------------------------------------------
	public void add(String volumeName, int refOwner){
		//log("Dictionnaire -> :" + volumeName);
		regex = "[\\p{Punct}\\s]+";
		//String [] listTokens = iitem.split_toTokens(regex_file);
		
		String[] ret;
		if(regex==null){
			ret = new String[1];
			ret[0]=volumeName;
		}
		else{
			ret =  volumeName.split(regex);
		}	
		
		for (String str : ret) {
			addItem(str,refOwner);
			//log("item-> :" + str);
			//log(str);
		}
		//return ret;
	
	}
	//----------------------------------------------
	Map<String, ArrayList<Integer>> lexique;
	//---------------------------------------------- 
	/**
	 * 
	 * @param volumeName: nom de volume (dont la clé est donné par refOwner)
	 * @param refOwner: il s'agit de la Clé de notre collection de volume.
	 * 
	 * Si notre map contient déjà l'item, alors on ajoute la référence du nom de volume à la liste des références de notre item
	 *
	 * si notre map ne contient pas, il faut créer une liste et l'ajouter
	 */
	public void addItem(String item, int refOwner){
		if (lexique.containsKey(item)){
			ArrayList<Integer> l = lexique.get(item);
			l.add(refOwner);
		}
		else{
			ArrayList<Integer> l = new ArrayList<Integer>();
			l.add(refOwner);
			lexique.put(item, l);
		}
	}
	//_____________________________________________________________________________________
	public static void log(String s){
		System.out.println(s);
	}
	@Override
	public ArrayList<String> getAllItems() {
		// TODO Auto-generated method stub
		return null;
	}
	//_____________________________________________________________________________________
	/**
	 * retourne toutes les références vers un item
	 */
	@Override
	public ArrayList<Integer> getAllRef(String item) {
		ArrayList<Integer> ret = lexique.get(item);
		return ret;
	}
	
	/**
	 * retourne tous les items qui ont des références multiples.
	 * La fréquence mini est donnée par le paramètre minFreq
	 * @return une liste des item qui sont des doublons
	 */
	public ArrayList<String> getAllDuplicates(int minFreq){
		ArrayList<String> ret = new ArrayList<String>();
		
		Iterator<String> dico_Iterator = lexique.keySet().iterator();		
		while (dico_Iterator.hasNext()) {
			String item = dico_Iterator.next();
			int freq = getFrequency(item);
			if (freq>= minFreq){
				ret.add(item);
				//System.out.println("list duplicates:  "+freq+"   "+item);
			}
			
		}
		System.out.println("fin liste duplicate");
		return ret;		
	}
	//_____________________________________________________________________________________
}

