package esx_collection;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class DirFile_xCollection implements Ix_DirCollection {
	int index_dir;
	HashMap<Integer, Ix_DirItem> dirMap = new HashMap<Integer, Ix_DirItem>();
	int index_file;
	HashMap<Integer, Ix_DirItem> fileMap = new HashMap<Integer, Ix_DirItem>();

	public DirFile_xCollection() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int AddItem(Ix_DirItem E) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Ix_DirItem> getAllDir() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Ix_DirItem> getAllFiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collections GetAllItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void BuildDico() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ix_DirItem getItem(int K) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getAllItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Integer> getAllRef(String item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ShowFrequencies() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ShowFrequentItems(int minFreg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRegex(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<String> getAllDuplicates(int minFreq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int AddItem(int index_parent, File file) {
		// TODO Auto-generated method stub
		return 0;
	}

}
