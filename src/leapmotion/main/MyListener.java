package leapmotion.main;

import java.io.*;

import adaBoost.Classification;
import camera.MainThreadImage;

import com.leapmotion.leap.*;

public class MyListener extends Listener{
	
	int compteurMain = 0;
	int compteur2Main = 0;
	
	private static KListener k1 = new KListener();  //KeyListener pour controle par clavier
	
	private int i = 0; // incrementation pour sauvegarder toutes les frames d'un mouvement
	private int y = 0; // parametre de vérification de conditions
	private int b = 0; // incrementation pour sauvegarder tous les mouvements
	
	private LoopWait lw = new LoopWait(); // creation d'un LoopWait pour reduire le lag des boucles infinies
	
	private FramesGestes framesGestes = new FramesGestes(b); // enregistrement des frames d'un mouvement
	private ListeDeMouvements liste = new ListeDeMouvements(); // enregistrement de tous les mouvements
	
	private Classification classi; // pour envoi vers la classification
	// Modification du constructeur pour avoir acces a la classification. Sert pour la fonction principale, a enlever pour la fonction d'enregistrement.
	public MyListener(Classification classi){
		this.classi=classi;
	}
	
	public MyListener(){
		
	}
	
	// Initialisation
	public void onInit(Controller controller) {
        System.out.println("Initialisation Leap Motion");
    }
	
	// Boucle principale
    public void onFrame(Controller controller) {
    	
    	/*************************************************************************************************************************/
    	/**                             FONCTION PRINCIPALE : ENVOI VERS LA CLASSIFICATION EN CONTINU                           **/
    	/*************************************************************************************************************************/
   
    	try{
    		Frame frame = controller.frame();
        	if (frame.hands().count()>0)
        	{
        		System.out.println(frame.hands().count());
        		ParamUtiles param = new ParamUtiles();
        		param.addToList(frame, controller.frame(1));
        		double tab[] = new double[Classification.NOMBRE_DE_FEATURES];
        		for (int k = 0 ; k < Classification.NOMBRE_DE_FEATURES ; k++){
        			tab[k] = param.get(k);
        		}
        		
        		classi.onFrame(tab);
        	}
        	else{
        		classi.lancerClassi();
        	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		

    	
    	
    	/*************************************************************************************************************************/
    	/**                                    CAPTURE ET ENREGISTREMENT POUR LA CLASSIFICATION                                 **/
    	/*************************************************************************************************************************/
/*    	
  	
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
    			FileOutputStream fileOut = new FileOutputStream("./Gestes/Geste3 (accepter)/Geste3 Tim (accepter).ser");
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
    	
    	// AFFICHAGE DE PARAMETRES DANS LA CONSOLE POUR VERIFICATION, RENVOIE LA LISTE ACTUELLE SI PAS DE SAUVEGARDE FAITE
    	if (k1.getC()=='p'){
    		
    		try{
        		File fichier = new File("./Gestes/Geste3 (accepter)/Geste3 Tim (accepter).ser");
        		ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(fichier)) ;
        		ListeDeMouvements hyk = (ListeDeMouvements)ois.readObject();
        		for (int k = 0 ; k<hyk.size() ; k++){
        			System.out.print("Geste n°" + k + " : ");
        			for (int g = 0 ; g<hyk.get(k).getSize() ; g++){
        				if (hyk.get(k).get(g).get(7)>=3){
                			System.out.print((int)hyk.get(k).get(g).get(7) + ", "); // Affichage du nombre de doigts
                			compteur2Main++;
        				}
        				else{
                			System.out.print((int)hyk.get(k).get(g).get(7) + "(PAS OK), "); // Affichage du nombre de doigts
        				}
        				compteurMain++;
        			}
        			System.out.println(" ");
        		}
        		ois.close();
        		double pourcentage = ((double)compteur2Main/(double)compteurMain)*100;
        		System.out.println("Nombre de données : " + compteurMain + " ||| Nombre de >3 doigts : " + compteur2Main + " ||| " + pourcentage + "% de >3 doigts");
    		}
    		catch(Exception i)
    		{
    			i.printStackTrace();
    			if (liste.size()>0){
    				for (int k = 0 ; k<liste.size() ; k++){
            			System.out.print("Geste n°" + k + " : ");
            			for (int g = 0 ; g<liste.get(k).getSize() ; g++){
            				if (liste.get(k).get(g).get(0)==2.0){
                    			System.out.print((int)liste.get(k).get(g).get(0) + ", "); // Affichage du nombre de mains
            				}
            				else{
                    			System.out.print((int)liste.get(k).get(g).get(0) + "(PAS OK), "); // Affichage du nombre de mains
            				}
            			}
            			System.out.println(" ");
            		}
    			}
    		}
    		
    		while (k1.getC()=='p'){
    			lw.w();
    		}
    		
    	}
*/		
   	}
        
}
