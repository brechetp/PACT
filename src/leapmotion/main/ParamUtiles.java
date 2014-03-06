package leapmotion.main;

import com.leapmotion.leap.Frame;

public class ParamUtiles {
	
	private double tab[] = new double[15];
	
	public ParamUtiles(){
		for (int i = 0 ; i <15 ; i++){
			tab[i]=(double)0;
		}
	}
	
	public void addToList(Frame f, Frame f2){
		
		//Nombre de mains
		tab[0] = (double)f.hands().count();
		
		//Nombre de doigts
		tab[1] = (double)f.fingers().count();
		
		//Vecteur normal de la première main
		tab[2] = (double)f.hands().get(0).palmNormal().getX();
		tab[3] = (double)f.hands().get(0).palmNormal().getY();
		tab[4] = (double)f.hands().get(0).palmNormal().getZ();
		
		//Vecteur normal de la seconde main
		tab[5] = (double)f.hands().get(1).palmNormal().getX();
		tab[6] = (double)f.hands().get(1).palmNormal().getY();
		tab[7] = (double)f.hands().get(1).palmNormal().getZ();
		
		//Vecteur translation de la première main
		tab[8] = (double)f.hands().get(0).translation(f2).getX();
		tab[9] = (double)f.hands().get(0).translation(f2).getY();
		tab[10] = (double)f.hands().get(0).translation(f2).getZ();
		
		//Vecteur translation de la seconde main
		tab[11] = (double)f.hands().get(1).translation(f2).getX();
		tab[12] = (double)f.hands().get(1).translation(f2).getY();
		tab[13] = (double)f.hands().get(1).translation(f2).getZ();
		
		//Rotation de la première main
		tab[14] = (double)Math.sqrt(f.hands().get(0).palmNormal().getX()*f.hands().get(0).palmNormal().getX()+f.hands().get(0).palmNormal().getY()*f.hands().get(0).palmNormal().getY()+f.hands().get(0).palmNormal().getZ()*f.hands().get(0).palmNormal().getZ());
	}
	
	public double get(int i){
		return tab[i];
	}
	                         

}
