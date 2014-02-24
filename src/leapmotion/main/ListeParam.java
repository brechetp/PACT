package leapmotion.main;

import java.io.Serializable;
import java.util.ArrayList;

import com.leapmotion.leap.Frame;
import com.leapmotion.leap.HandList;

public class ListeParam implements Serializable{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6261558975654942473L;
	private ArrayList<Double> liste = new ArrayList<Double>();
	private int b;
	
	public ListeParam(int b){
		this.b=b;
		for (int k =0 ; k < 56 ; k++){
			liste.add(k, (double)0);
		}
	}
	
	public int size(){
		return liste.size();
	}
	
	public void addToList(Frame f, Frame f2){
		
		
		HandList hand = f.hands();
		if (hand == null)
			System.out.println("booooo snif");
		int nb = hand.count();
		System.out.println("... "+nb);
		liste.set(0, (double)nb);
		liste.set(7, (double)f.fingers().count());
		
		if (f.hands().count()==1){
			liste.set(1, (double)f.hands().get(0).palmPosition().getX());
			liste.set(2, (double)f.hands().get(0).palmPosition().getY());
			liste.set(3, (double)f.hands().get(0).palmPosition().getZ());
			liste.set(38, (double)f.hands().get(0).palmNormal().getX());
			liste.set(39, (double)f.hands().get(0).palmNormal().getY());
			liste.set(40, (double)f.hands().get(0).palmNormal().getZ());
			liste.set(44, (double)f.hands().get(0).direction().getX());
			liste.set(45, (double)f.hands().get(0).direction().getY());
			liste.set(46, (double)f.hands().get(0).direction().getZ());
			liste.set(50, (double)f.hands().get(0).translation(f2).getX());
			liste.set(51, (double)f.hands().get(0).translation(f2).getY());
			liste.set(52, (double)f.hands().get(0).translation(f2).getZ());
			
			if (f.hands().get(0).fingers().count()>0){
				if(f.hands().get(0).fingers().count()>0){
					liste.set(8, (double)f.hands().get(0).fingers().get(0).direction().getX());
					liste.set(9, (double)f.hands().get(0).fingers().get(0).direction().getY());
					liste.set(10, (double)f.hands().get(0).fingers().get(0).direction().getZ());
				}
				if(f.hands().get(0).fingers().count()>1){
					liste.set(11, (double)f.hands().get(0).fingers().get(1).direction().getX());
					liste.set(12, (double)f.hands().get(0).fingers().get(1).direction().getY());
					liste.set(13, (double)f.hands().get(0).fingers().get(1).direction().getZ());
				}
				if(f.hands().get(0).fingers().count()>2){
					liste.set(14, (double)f.hands().get(0).fingers().get(2).direction().getX());
					liste.set(15, (double)f.hands().get(0).fingers().get(2).direction().getY());
					liste.set(16, (double)f.hands().get(0).fingers().get(2).direction().getZ());
				}
				if(f.hands().get(0).fingers().count()>3){
					liste.set(17, (double)f.hands().get(0).fingers().get(3).direction().getX());
					liste.set(18, (double)f.hands().get(0).fingers().get(3).direction().getY());
					liste.set(19, (double)f.hands().get(0).fingers().get(3).direction().getZ());
				}
				if(f.hands().get(0).fingers().count()>4){
					liste.set(20, (double)f.hands().get(0).fingers().get(4).direction().getX());
					liste.set(21, (double)f.hands().get(0).fingers().get(4).direction().getY());
					liste.set(22, (double)f.hands().get(0).fingers().get(4).direction().getZ());
				}
			}
		}
		
		if (f.hands().count()>1){
			liste.set(4, (double)f.hands().get(1).palmPosition().getX());
			liste.set(5, (double)f.hands().get(1).palmPosition().getY());
			liste.set(6, (double)f.hands().get(1).palmPosition().getZ());
			liste.set(41, (double)f.hands().get(1).palmNormal().getX());
			liste.set(42, (double)f.hands().get(1).palmNormal().getY());
			liste.set(43, (double)f.hands().get(1).palmNormal().getZ());
			liste.set(47, (double)f.hands().get(1).direction().getX());
			liste.set(48, (double)f.hands().get(1).direction().getY());
			liste.set(49, (double)f.hands().get(1).direction().getZ());
			liste.set(53, (double)f.hands().get(1).translation(f2).getX());
			liste.set(54, (double)f.hands().get(1).translation(f2).getY());
			liste.set(55, (double)f.hands().get(1).translation(f2).getZ());
			
			if (f.hands().get(0).fingers().count()>0){
				if(f.hands().get(0).fingers().count()>0){
					liste.set(23, (double)f.hands().get(1).fingers().get(0).direction().getX());
					liste.set(24, (double)f.hands().get(1).fingers().get(0).direction().getY());
					liste.set(25, (double)f.hands().get(1).fingers().get(0).direction().getZ());
				}
				if(f.hands().get(0).fingers().count()>1){
					liste.set(26, (double)f.hands().get(1).fingers().get(1).direction().getX());
					liste.set(27, (double)f.hands().get(1).fingers().get(1).direction().getY());
					liste.set(28, (double)f.hands().get(1).fingers().get(1).direction().getZ());
				}
				if(f.hands().get(0).fingers().count()>2){
					liste.set(29, (double)f.hands().get(1).fingers().get(2).direction().getX());
					liste.set(30, (double)f.hands().get(1).fingers().get(2).direction().getY());
					liste.set(31, (double)f.hands().get(1).fingers().get(2).direction().getZ());
				}
				if(f.hands().get(0).fingers().count()>3){
					liste.set(32, (double)f.hands().get(1).fingers().get(3).direction().getX());
					liste.set(33, (double)f.hands().get(1).fingers().get(3).direction().getY());
					liste.set(34, (double)f.hands().get(1).fingers().get(3).direction().getZ());
				}
				if(f.hands().get(0).fingers().count()>4){
					liste.set(35, (double)f.hands().get(1).fingers().get(4).direction().getX());
					liste.set(36, (double)f.hands().get(1).fingers().get(4).direction().getY());
					liste.set(37, (double)f.hands().get(1).fingers().get(4).direction().getZ());
				}
			}
		}
		
	}
	
	public double get(int i){
		return liste.get(i);
	}

}
