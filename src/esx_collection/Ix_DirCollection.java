package esx_collection;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Interface pour un gestionnaire d'objets implémentant l'interface Ix_DirItem
 * 
 * Chaque classe qui implémente cette interface est responsable
 * de créer une collection d'objets qui associe:
 * 		un entier (la clé),
 * 		un objet de classe implémentant l'interface Ix_DirItem. 
 * 
 * Interface Ix_DirItem, possède  une référence pour cet objet et une référence pour un objet parent.
 * 
 * Méthodes del'interface:
 * 		AddItem: insertion d'un objet dans la collection implémentée par les classes qui implémentent cette interface.		
 * 
 * 		
 * 		BuildDico, construit un lexique à partir des objets de la collection des classes impléntées.
 * 		Le lexique implémente l'interface Ix_Dico
 * 		Le lexique découpe chaque élément de la collection en éléments simples grace à un regex.
 * 
 * 		Ix_DirCollection { Key, Text }
 * 
 * 		Ix_Dico
 * 		Text -> constitué de {items}
 * 		Ix_Dico, collection de {items, {Key vers les éléments de (Ix_DirCollection)} }
 * 
 * Autres Méthodes del'interface:
 * 		getAllDuplicates(int minFreq), retourne une liste des items de notre lexiqueque qui ont une frequence supérieure à un seuil donné.
 * 		getAllRef(String item),retourne une liste des ref de notre item, on pourra retrouver les éléments originaux de notre collection pour effectuer des comparaisons.
 * @author macbooksadler
 *
 */
public interface Ix_DirCollection {
	/**
	 * Ajoute un item 
	 * @param E: ajoute au container un élément qui implémente l'iterface Ix_DirItem
	 * @return la clé de l'élément ajouté au container
	 */
	public int  AddItem(Ix_DirItem E);//obsolete
	public int AddItem(int index_parent, File file);
	public Ix_DirItem getItem(int K);
	/**
	 * 
	 * @return une collection d'objets (les directories)
	 */
	public ArrayList<Ix_DirItem> getAllDir();
	/**
	 * 
	 * @return une collection d'objets (les directories
	 */
	public ArrayList<Ix_DirItem> getAllFiles();
	public Collections GetAllItems();
	//public int getInsertedRef();	// 
	// iterator getDirIterator();
	// iterator getFileIterator();

	public void BuildDico();
	
	public ArrayList<String> getAllItems();
	public ArrayList<Integer> getAllRef(String item);
	public ArrayList<String> getAllDuplicates(int minFreq);
	
	
	public void ShowFrequencies();
	public void ShowFrequentItems(int minFreg);
	public void setRegex(String string);
}
