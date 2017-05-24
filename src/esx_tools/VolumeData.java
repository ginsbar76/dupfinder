package esx_tools;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import esx_collection.Ix_DirItem;

/**
 * classe wrapper pour un objet File conserve les valeurs de l'objet File
 * 
 * @author macbooksadler
 *
 */
public class VolumeData implements Ix_DirItem {
	int ref;
	int refParent;

	//-------------------------------------------------------------------	
	@Override
	public String toTitle() {
		return getVolumeName();
	}
	//-------------------------------------------------------------------
	@Override
	public String toString() {
		/*
		return "VolumeData [ref=" + ref + ", refParent=" + refParent + ", volumeName=" + volumeName + ", isDirectory="
				+ isDirectory + ", isHidden=" + isHidden + "]";
		*/
		return String.format("%-75s : %-10s  : %4d  :  %4d  : %s", volumeName, isHidden?"Caché":"visible", ref, refParent, getPathonly());		
	}
	// _____________________________________________________________________________________
	public int setRef(int ref) {
		this.ref = ref;
		return 0;
	}

	public int setRefParent(int refParent) {
		this.refParent = refParent;
		return 0;
	}

	public int getRefParent() {
		return refParent;
	}

	public int getRef() {
		return ref;
	}

	// -------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------
	/**
	 * 
	 * @param refParent:
	 *            référence vers le parent de ce fichier
	 * @param f:
	 *            prend un objet de class File en paramètre, extrait les
	 *            informations nécessaires de ce fichier
	 */
	public VolumeData(int ref, int refParent, File f) {
		this.refParent = refParent;
		if (f.exists()) {
			refresh(f);
		}
	}

	/**
	 * constructeur: prend un objet de class File en paramètre, extrait les
	 * informations nécessaires de ce fichier
	 */
	public VolumeData(File f) {
		if (f.exists()) {
			refresh(f);
		}
	}

	// ---------------------------------------------------------------------------------
	public VolumeData() {
	}
	// ---------------------------------------------------------------------------------

	protected String volumeName = "volume unamed";
	protected String absolutePath = "absolutePath unamed";
	protected String path = "path unamed";
	protected long len = 0;
	protected long lastModified = 0;
	protected boolean isFile = false;
	protected boolean isDirectory = false;
	protected boolean isHidden = false;
	protected long totalSpace = 0;

	// ---------------------------------------------------------------------------------
	public String refresh(File m_file) {
		if (m_file != null && m_file.exists()) {
			volumeName = m_file.getName();
			absolutePath = m_file.getAbsolutePath();
			path = m_file.getPath();
			len = m_file.length();
			lastModified = m_file.lastModified();
			isFile = m_file.isFile();
			isDirectory = m_file.isDirectory();
			isHidden = m_file.isHidden();
			totalSpace = m_file.getTotalSpace();
			return (absolutePath);
		}
		System.out.println("File does not exist - use a try / catch");
		return null;
	}

	/**
	 * @return the volumeName
	 */
	public String getVolumeName() {
		return volumeName;
	}

	/**
	 * @return the absolutePath
	 */
	public String getAbsolutePath() {
		return absolutePath;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @return the len
	 */
	public long getLen() {
		return len;
	}

	/**
	 * @return the lastModified
	 */
	public long getLastModified() {
		return lastModified;
	}

	/**
	 * @return the isFile
	 */
	public boolean isFile() {
		return isFile;
	}

	/**
	 * @return the isDirectory
	 */
	public boolean isDirectory() {
		return isDirectory;
	}
	
	//-------------------------------------------------------------------	
    public String getPathonly(){
    	Path path = Paths.get(getAbsolutePath());
    	int n = path.getNameCount()-1;
    	if (n==0){
    		return "x  "+path.toString();
    	}
    	else{
        	Path ret = path.subpath(0,n ) ;
        	return ret.toString();    		
    	}
    }
	/*
	 * Both Paths.get("path/to/file").toString() 
	 * and Paths.get("path", "to", "file").toString() 
	 * both return "path/to/file" for me 
	 */
    

	//-------------------------------------------------------------------
	/**
	 * @return the isHidden
	 */
	public boolean isHidden() {
		return isHidden;
	}

	/**
	 * @return the totalSpace
	 */
	public long getTotalSpace() {
		return totalSpace;
	}

	/**
	 * @param volumeName
	 *            the volumeName to set
	 */
	public void setVolumeName(String volumeName) {
		this.volumeName = volumeName;
	}

	/**
	 * @param absolutePath
	 *            the absolutePath to set
	 */
	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @param len
	 *            the len to set
	 */
	public void setLen(long len) {
		this.len = len;
	}

	/**
	 * @param lastModified
	 *            the lastModified to set
	 */
	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	/**
	 * @param isFile
	 *            the isFile to set
	 */
	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}

	/**
	 * @param isDirectory
	 *            the isDirectory to set
	 */
	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}

	/**
	 * @param isHidden
	 *            the isHidden to set
	 */
	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	/**
	 * @param totalSpace
	 *            the totalSpace to set
	 */
	public void setTotalSpace(long totalSpace) {
		this.totalSpace = totalSpace;
	}


}
