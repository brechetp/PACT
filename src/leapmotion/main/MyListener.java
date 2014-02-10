package leapmotion.main;

import com.leapmotion.leap.*;

public class MyListener extends Listener{
	
	static KListener k1 = new KListener();
	int i = 0;
	int y = 0;
	
	public void onInit(Controller controller) {
        System.out.println("Initialisation Leap Motion");
    }
	
    public void onFrame(Controller controller) {
    	
    	
    	if (k1.getC()=='r'){
    		i = 0;
    		y=0;
    		System.out.println("Initialisé");
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
    		// Informations sur la frame la plus récente
            Frame frame = controller.frame();
    		
            if (controller.frame(1).hands().count()>0){
            	if (y==0){
            		System.out.println("Main détectée. Début de l'acquisition du mouvement. Appuyer sur Z pour arrêter.");
            		y=1;
            	}
            	FramesGestes.addToList(i, frame);
            	i++;
            }
            	
//        	// Affichage de certains paramètres pour contrôle
//        	System.out.println("Nombre de doigts : " + FramesGestes.getFingersCount());
//       		System.out.println("Nombre de mains : " + FramesGestes.getHandsCount());
//       		System.out.println("Nombre d'outils : " + FramesGestes.getToolsCount());
//       		System.out.println("Vecteurs de translation :");
//       		for (int k=0 ; k<FramesGestes.getSize() ; k++){
//       			System.out.println(FramesGestes.getHandPosition().get(k));
//       		}
        }
    	
    	if (k1.getC()=='z'){
    		System.out.println("Arrêt de l'acquisition. Appuyez sur R pour réinitialiser.");
    		for (int k = 0; k<FramesGestes.getSize();k++){
    			System.out.println(FramesGestes.getHandPosition().get(k));
    		}
    		while (k1.getC()=='z'){
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
