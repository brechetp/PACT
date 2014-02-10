package leapmotion.main;
import com.leapmotion.leap.*;

import java.util.ArrayList;

public class FramesGestes {
	
	private static ArrayList<Frame> liste = new ArrayList<Frame>();
	
	public static void addToList(int i, Frame f){
		liste.add(i,f);
	}
	
	public static int getFingersCount(){
		return liste.get(20).fingers().count();
	}
	
	public static int getHandsCount(){
		return liste.get(20).hands().count();
	}

	public static int getToolsCount(){
		return liste.get(20).tools().count();
	}
	
	public static ArrayList<Vector> getHandTranslation(){
		
		ArrayList<Vector> mouvementDeTranslation = new ArrayList<Vector>();
		
		for (int i=0 ; i<10 ; i++){
			Hand hand = liste.get(20+i).hands().get(0);	
			mouvementDeTranslation.add(i, hand.translation(liste.get(30+i)));
		}

		return mouvementDeTranslation;
	}
	
	public static ArrayList<Vector> getHandPosition(){
		
		ArrayList<Vector> positionsDeLaMain = new ArrayList<Vector>();
		
		for (int k=0 ; k<liste.size() ; k++){
			Hand hand = liste.get(k).hands().get(0);
			positionsDeLaMain.add(k, hand.palmPosition());
		}
		
		return positionsDeLaMain;
	}
	
	public static int getSize(){
		return liste.size();
	}
	
	
	
	
	
	
	
	
}
