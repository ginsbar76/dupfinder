package esx_collection;

public interface  Ix_DirItem {
	/**
	 * <p> retourne une chaine de titre pour cet objet </p>
	 * @return
	 */
	public String toTitle();//retourne un titre pour l'objet (chane de caractère pour comparaison)
	public int setRef(int ref);
	public int setRefParent(int refParent);
	
	public boolean isDirectory();

}
