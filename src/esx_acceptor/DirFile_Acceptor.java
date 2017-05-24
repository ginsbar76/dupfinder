package esx_acceptor;

import java.io.File;

import esx_enums.DirFile_Selector;

public class DirFile_Acceptor implements I_Acceptor {

	DirFile_Selector filtre;

	public DirFile_Acceptor() {
		// TODO Auto-generated constructor stub
	}

	public DirFile_Acceptor(DirFile_Selector filtre) {
		super();
		this.filtre = filtre;
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

	public void setSelection(DirFile_Selector filtre) {
		this.filtre = filtre;
	}

	@Override
	public boolean Accept(File f) {
		switch (filtre) { // FILES_n_DIR, FILES_ONLY, DIR_ONLY
		case FILES_n_DIR: {
			return true;
		}
		case FILES_ONLY: {
			if (!f.isDirectory())return true;
			break;
		}
		case DIR_ONLY: {
			if (f.isDirectory())return true;
			break;
		}
		}
		return false;
		
	}

	@Override
	public String toString() {
		String ret;
		switch (filtre) { // FILES_n_DIR, FILES_ONLY, DIR_ONLY
		case FILES_n_DIR: {
			logTitre("Affichage des fichiers et directories");
			break;
		}
		case FILES_ONLY: {
			logTitre("Affichage des fichiers uniquement");
			break;
		}
		case DIR_ONLY: {
			logTitre("Affichage des directories uniquement");
			break;
		}
		}
		return "DirFile_Acceptor []";
	}

	public static void log(String s) {
		System.out.println(s);
	}

	public static void logTitre(String s) {
		System.out.println("---------------------------------------------------------");
		System.out.println(s);
		System.out.println("---------------------------------------------------------");
	}
}
