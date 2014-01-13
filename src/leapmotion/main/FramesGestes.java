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
	
	
	
	
	
	
	
	
	
	
	
}
