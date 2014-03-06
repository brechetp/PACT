package adaBoost;

import java.io.Serializable;

public class ClassiFinauxListe implements Serializable{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2429081611892742115L;
	private ClassiFinal[] tab;
	
	public ClassiFinauxListe(ClassiFinal[] tab){
		this.tab = tab;
	}
	
	public ClassiFinal[] get(){
		return tab;
	}

}
