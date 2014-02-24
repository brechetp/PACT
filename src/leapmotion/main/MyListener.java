package leapmotion.main;

import java.io.*;

import com.leapmotion.leap.*;

public class MyListener extends Listener{
	
	private static KListener k1 = new KListener();  //KeyListener pour contrôle par clavier
	
	private int i = 0; // incrémentation pour sauvegarder toutes les frames d'un mouvement
	private int y = 0; // paramètre de vérification de conditions
	private int b = 0; // incrémentation pour sauvegarder tous les mouvements
	
	private LoopWait lw = new LoopWait(); // création d'un LoopWait pour réduire le lag des boucles infinies
	
	private FramesGestes framesGestes = new FramesGestes(b); // enregistrement des frames d'un mouvement
	private ListeDeMouvements liste = new ListeDeMouvements(); // enregistrement de tous les mouvements
	
	public void onInit(Controller controller) {
        System.out.println("Initialisation Leap Motion");
    }
	
    public void onFrame(Controller controller) {
    	
    	// INITIALISATION DU PROGRAMME DE CAPTURE DE MOUVEMENTS
    	if (k1.getC()=='/'){
    		
    		i=0;
    		y=0;
    		
    		System.out.println("Initialisé. Appuyer sur A pour commencer une acquisition.");
    		System.out.println("-----------------------------------------------------------------------------------------------------");
    		
    		while (k1.getC()=='/'){
    			lw.w();
    		}
    		
    	}
    	
    	// ACQUISITION D'UN MOUVEMENT
    	if (k1.getC()=='a'){
    		
            Frame frame = controller.frame();
            
    		if(y==0){
    			System.out.println("Début de la séquence d'acquisition. Placer la main au dessus de la Leap pour commencer l'acquisition.");
    			y=1;
    		}
    		
            if (controller.frame(1).hands().count()>0){
            	if (y==1){
            		System.out.println("Main détectée. Début de l'acquisition du mouvement. Appuyer sur Z pour arrêter.");
            		y=2;
            	}
            	
            	ListeParam param = new ListeParam(i);
            	param.addToList(frame, controller.frame(1));
            	framesGestes.addToList(i, param);
                i++;
            }
         
        }
    	
    	// ARRÊT DE L'ACQUISITION ET SAUVEGARDE DU MOUVEMENT
    	if (k1.getC()=='z'){
    		
    		System.out.println("Arrêt de l'acquisition.");
    		System.out.println("-----------------------------------------------------------------------------------------------------");
    		System.out.println("Réinitialisé. Appuyer sur A pour commencer une nouvelle acquisition.");
    		
    		liste.add(b, framesGestes); 
    		
    		b++;
    		i=0;
    		y=0;
    		framesGestes = new FramesGestes(b);
    		
    		while (k1.getC()=='z'){
    			lw.w();
    		}
    	}
    	
    	// SAUVEGARDE DE LA LISTE DE TOUS LES MOUVEMENTS DANS UN FICHIER
    	if (k1.getC()=='s'){
    		
    		try{
    			FileOutputStream fileOut = new FileOutputStream("C:/Users/Benjamin-Zigaroula-/Desktop/GestesLeap/Geste1.ser");
    			ObjectOutputStream out = new ObjectOutputStream(fileOut);
    			out.writeObject(liste);
    			out.flush();
    			out.close();
    			fileOut.close();
    			System.out.println("La liste des gestes a été enregistrée.");
    		}
    		catch(IOException i){
    	          i.printStackTrace();
    	    }
    		
    		while (k1.getC()=='s'){
    			lw.w();
    		}
    		
    	}
    	
    	// AFFICHAGE DE PARAMETRES DANS LA CONSOLE POUR VERIFICATION
    	if (k1.getC()=='p'){
    		
    		try{
        		File fichier = new File("C:/Users/Benjamin-Zigaroula-/Desktop/GestesLeap/Geste1.ser");
        		ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(fichier)) ;
        		ListeDeMouvements hyk = (ListeDeMouvements)ois.readObject();
        		for (int k = 0 ; k<hyk.size() ; k++){
        			System.out.print("Geste n°" + k + " : ");
        			for (int g = 0 ; g<hyk.get(k).getSize() ; g++){
            			System.out.print(hyk.get(k).get(g).get(2) + ", "); // Affichage de posX de la main 0
        			}
        			System.out.println(" ");
        		}
        		ois.close();
    		}
    		catch(Exception i)
    		{
    			i.printStackTrace();
    			if (liste.size()>0){
    				for (int k = 0 ; k<liste.size() ; k++){
            			System.out.print("Geste n°" + k + " : ");
            			for (int g = 0 ; g<liste.get(k).getSize() ; g++){
                			System.out.print(liste.get(k).get(g).get(2) + ", "); // Affichage de posX de la main 0
            			}
            			System.out.println(" ");
            		}
    			}
    		}
    		
    		while (k1.getC()=='p'){
    			lw.w();
    		}
    		
    	}
		
   	}
        
}
