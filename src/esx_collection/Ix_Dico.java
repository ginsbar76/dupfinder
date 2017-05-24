package esx_collection;

import java.util.ArrayList;

public interface Ix_Dico {
	public void setRegex(String rgx);
	public void add(String volumeName, int refOwner);
	public void listFrequencies();
	public void listFrequentItems(int miniFreq);
	public ArrayList<String> getAllItems();
	public ArrayList<Integer> getAllRef(String item);
	
	
	/**
	 * retourne tous les items qui ont des références multiples.
	 * La fréquence mini est donnée par le paramètre minFreq
	 * @return une liste des item qui sont des doublons
	 */
	public ArrayList<String> getAllDuplicates(int minFreq);
}
