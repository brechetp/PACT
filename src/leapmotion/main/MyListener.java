package leapmotion.main;

import java.io.*;
import java.util.ArrayList;

import com.leapmotion.leap.*;

public class MyListener extends Listener{
	
	static KListener k1 = new KListener();
	int i = 0;
	int y = 0;
	int b = 0;
	ArrayList<GesteIndex> liste = new ArrayList<GesteIndex>();
	
	public void onInit(Controller controller) {
        System.out.println("Initialisation Leap Motion");
    }
	
    public void onFrame(Controller controller) {
    	
    	
    	if (k1.getC()=='r'){
    		i=0;
    		y=0;
    		System.out.println("Initialis�. Appuyer sur A pour commencer une acquisition.");
    		System.out.println("-----------------------------------------------------------------------------------------------------");
    		while (k1.getC()=='r'){
    			try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	
    	if (k1.getC()=='a'){
    		// Informations sur la frame la plus r�cente
            Frame frame = controller.frame();
    		if(y==0){
    			System.out.println("D�but de la s�quence d'acquisition. Placer la main au dessus de la Leap pour commencer l'acquisition.");
    			y=1;
    		}
            if (controller.frame(1).hands().count()>0){
            	if (y==1){
            		System.out.println("Main d�tect�e. D�but de l'acquisition du mouvement. Appuyer sur Z pour arr�ter.");
            		y=2;
            	}
            	FramesGestes.addToList(i, frame);
            	i++;
            }
            	
        }
    	
    	if (k1.getC()=='z'){
    		System.out.println("Arr�t de l'acquisition.");
    		System.out.println("-----------------------------------------------------------------------------------------------------");
    		System.out.println("R�initialis�. Appuyer sur A pour commencer une nouvelle acquisition.");
    		FramesGestes framesGestes = new FramesGestes();
    		GesteIndex gesteIndex = new GesteIndex(framesGestes, b);
    		liste.add(b, gesteIndex);
    		b++;
    		i=0;
    		y=0;
    		while (k1.getC()=='z'){
    			try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	
    	if (k1.getC()=='s'){
    		try{
    			FileOutputStream fileOut = new FileOutputStream("C:/Users/Benjamin-Zigaroula-/Desktop/FramesGestes.ser");
    			ObjectOutputStream out = new ObjectOutputStream(fileOut);
    			out.writeObject(liste);
    			out.flush();
    			out.close();
    			fileOut.close();
    			System.out.println("La liste des gestes a �t� enregistr�e.");
    		}
    		catch(IOException i){
    	          i.printStackTrace();
    	    }
    	}
    	
    	if (k1.getC()=='p'){
    		for (int k = 0 ; k<liste.size() ; k++){
    			liste.get(k).printInfo();
    		}
    		while (k1.getC()=='p'){
    			try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
		
   	}
        
}
