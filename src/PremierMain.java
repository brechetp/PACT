import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.leapmotion.leap.Controller;

import adaBoost.ClassiFinal;
import adaBoost.Classification;
import leapmotion.main.ListeDeMouvements;
import leapmotion.main.MyListener;
import logiqueDeJeux.BeloteCoinche;
import logiqueDeJeux.EtatDuJeu;


public class PremierMain {

	public static void main(String[] args) 
	{
		
		ClassiFinal[] classiFinal = null;
		/*try {
			ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(new File("./adaboost/Classificateurs Finaux.ser"))) ;
			classiFinal = (ClassiFinal[])ois.readObject();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}*/
		
		
		EtatDuJeu etat = new EtatDuJeu();
		BeloteCoinche belote = new BeloteCoinche(etat);
		Classification classi = new Classification(classiFinal);
		classi.addListener(belote);
		
		//Start Leap
		MyListener listener = new MyListener(classi);
        Controller controller = new Controller();
        controller.addListener(listener);
        
		//lien leap classi
		//start detection image
		//lien detection image
	}

}
