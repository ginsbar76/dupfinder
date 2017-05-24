package esx_acceptor;

import java.io.File;

import esx_enums.Hidden_Selector;
 

public class Hidden_Acceptor implements I_Acceptor {
	
	Hidden_Selector filtre;

	public Hidden_Acceptor() {
		// TODO Auto-generated constructor stub
	}

	public Hidden_Acceptor(Hidden_Selector filtre) {
		super();
		this.filtre = filtre;
	}

	@Override
	public boolean Accept(File f) {
		switch (filtre) { // HIDDEN_or_NOT, HIDDEN_ONLY, NOT_HIDDEN_ONLY
		case HIDDEN_or_NOT: {
			return true;
		}
		case HIDDEN_ONLY: {
			if (f.isHidden())return true;
			break;
		}
		case NOT_HIDDEN_ONLY: {
			if (!f.isHidden())return true;
			break;
		}
		} 
		return false;
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
