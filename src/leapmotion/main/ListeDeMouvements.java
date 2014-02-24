package leapmotion.main;

import java.io.Serializable;
import java.util.ArrayList;

public class ListeDeMouvements implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5725171662469738694L;
	private ArrayList<FramesGestes> listeDeGestes = new ArrayList<FramesGestes>();
	
	public void add(int i, FramesGestes framesGestes){
		listeDeGestes.add(i, framesGestes);
	}
	
	public int size(){
		return listeDeGestes.size();
	}
	
	public FramesGestes get(int k){
		return listeDeGestes.get(k);
	}

}
