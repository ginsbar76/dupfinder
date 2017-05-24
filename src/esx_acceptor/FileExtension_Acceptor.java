package esx_acceptor;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class FileExtension_Acceptor implements I_Acceptor {
	
	Collection<String> extension_collection = new ArrayList<String>();
	
	public boolean match_extensions(String txt) {
		Iterator<String> it = extension_collection.iterator();
		while (it.hasNext()) {
			boolean okExtension = txt.toLowerCase().endsWith(it.next());
			if (okExtension) { 
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
	
	public FileExtension_Acceptor(String[] extensions) {
		if (extensions != null) {
			int length = extensions.length; 
			for (String s : extensions) { 
				add_extension(s);
			}
		} else {
			extensions = null;
		}
	}
	public FileExtension_Acceptor() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean Accept(File f) {
		if (f.isDirectory()){
			return true;
		}
		
		else if (extension_collection.size() != 0) { // extensions!=null
			return match_extensions(f.getName());
		} else {
			return true; // tous les fichiers sont acceptés
		} 
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setText() {
		// TODO Auto-generated method stub

	}

}
