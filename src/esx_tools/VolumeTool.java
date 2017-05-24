package esx_tools;

import java.io.File;

import esx_collection.Ix_DirItem;

public class VolumeTool {
	int ref;
	int refParent;
	VolumeData data = null;
	//_____________________________________________________________________________________	
	public VolumeTool() {
		// TODO Auto-generated constructor stub
	}
	public VolumeTool(int refParent, File f) {
		this.refParent = refParent;
		data = new VolumeData(f);
	}
	//_____________________________________________________________________________________	
	public int setRef(int ref) {
		this.ref = ref;
		return 0;
	}
	public int setRefParent(int refParent) {
		this.refParent = refParent;
		return 0;
	}
	public int getRefParent(){
		return refParent;
	}
	public int getRef(){
		return ref;
	}
	// -------------------------------------------------------------------------------------------------------
	boolean isdir;
	public boolean isDir(){ 
		return data.isDirectory();
	}
	// -------------------------------------------------------------------------------------------------------	
	// -------------------------------------------------------------------------------------------------------
	/**
	 * @return the index_owner
	 */
	public int getIndex_owner() {
		return refParent;
	}

	/**
	 * @return the data
	 */
	public VolumeData getData() {
		return data;
	}
	// -------------------------------------------------------------------------------------------------------
	/**
	 * @param index_owner the index_owner to set
	 */
	public void setIndex_owner(int index_owner) {
		this.refParent = index_owner;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(VolumeData data) {
		this.data = data;
	}
	// -------------------------------------------------------------------------------------------------------

}
