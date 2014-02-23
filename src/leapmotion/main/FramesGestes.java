package leapmotion.main;
import com.leapmotion.leap.*;

import java.io.Serializable;
import java.util.ArrayList;

public class FramesGestes implements Serializable{
	
	private static final long serialVersionUID = 6614254148316806275L;
	
	private int b; // paramètre servant à différencier les différents mouvements
	
	public FramesGestes(int b){
		this.b=b;
	}
	
	private ArrayList<Frame> liste = new ArrayList<Frame>();
	
	public void addToList(int i, Frame f){
		liste.add(i,f);
	}
	
	public void setToList(int i, Frame f){
		liste.set(i, f);
	}
	
	public int getFingersCount(int i){
		return liste.get(i).fingers().count();
	}
	
	public int getHandsCount(){
		return liste.get(20).hands().count();
	}

	public int getToolsCount(){
		return liste.get(20).tools().count();
	}
	
	public ArrayList<Vector> getHandTranslation(){
		
		ArrayList<Vector> mouvementDeTranslation = new ArrayList<Vector>();
		
		for (int i=0 ; i<10 ; i++){
			Hand hand = liste.get(20+i).hands().get(0);	
			mouvementDeTranslation.add(i, hand.translation(liste.get(30+i)));
		}

		return mouvementDeTranslation;
	}
	
	public ArrayList<Vector> getHandPosition(){
		
		ArrayList<Vector> positionsDeLaMain = new ArrayList<Vector>();
		
		for (int k=0 ; k<liste.size() ; k++){
			Hand hand = liste.get(k).hands().get(0);
			positionsDeLaMain.add(k, hand.palmPosition());
		}
		
		return positionsDeLaMain;
	}
	
	public int getSize(){
		return liste.size();
	}
}
