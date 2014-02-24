package leapmotion.main;
import com.leapmotion.leap.*;

import java.io.Serializable;
import java.util.ArrayList;

public class FramesGestes implements Serializable{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8793877169175079157L;
	private int b; // paramètre servant à différencier les différents mouvements
	
	public FramesGestes(int b){
		this.b=b;
	}
	
	private ArrayList<ListeParam> liste = new ArrayList<ListeParam>();
	
	public int getSize(){
		return liste.size();
	}
	
	public void addToList(int i, ListeParam param){
		liste.add(i, param);
	}
	
	public ListeParam get(int i){
		return liste.get(i);
	}
	
}
