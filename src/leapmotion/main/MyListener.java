package leapmotion.main;

import java.util.ArrayList;
import com.leapmotion.leap.*;

public class MyListener extends Listener{
	
	public void onInit(Controller controller) {
        System.out.println("Initialisation Leap Motion");
    }
	
    public void onFrame(Controller controller) {
    	
        // Informations sur la frame la plus récente
        Frame frame = controller.frame();
        
        // Rangement dans une liste de l'historique des frames
        ArrayList<Frame> frameList = new ArrayList<Frame>();
        for (int i = 0 ; i<60 ; i++){
        	frameList.add(i,controller.frame(i));
        }
        
        // Détection d'un mouvement
        if (frameList.get(1).hands().count()>0){
        	if (frame.hands().count()==0){
        		System.out.println("Mouvement détécté");
        		
        		// Sauvegarde du mouvement
        		for (int i = 0 ; i<60 ; i++){
        			FramesGestes.addToList(i, frameList.get(i));
        		}
        		
        		// Affichage de certains paramètres pour contrôle
        		System.out.println("Nombre de doigts : " + FramesGestes.getFingersCount());
        		System.out.println("Nombre de mains : " + FramesGestes.getHandsCount());
        		System.out.println("Nombre d'outils : " + FramesGestes.getToolsCount());
        	}
        }
    }
}
