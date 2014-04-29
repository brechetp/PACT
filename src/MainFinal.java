import java.io.IOException;

import adaBoost.BaseApprentissage;
import adaBoost.ClassiFinal;
import adaBoost.Classification;
import camera.Initialisation;

import com.leapmotion.leap.Controller;

import leapmotion.main.MyListener;
import menus.*;
import structure.CircularArray;
import iug.ImageMenu;
import iug.ViewController;


public class MainFinal {

	public static void main(String[] args) {
		
		ViewController vc = new ViewController();
		
		//Classification
		ClassiFinal[] classilol = BaseApprentissage.main(null);
		Classification classi = new Classification(classilol);
		
		Initialisation.setSymbolDatabase("data/database/symbols/text");
		Initialisation.setLetterDatabase("data/database/letters/text");
		
		vc.modeMenu();
		
		//Creation menus
		MainMenu mainMenu = new MainMenu(classi, vc);
		mainMenu.init();
		classi.addListener(mainMenu);
		
		//Start leap
		MyListener listener = new MyListener(classi);
        Controller controller = new Controller();
        controller.addListener(listener);
        
        //Blocage
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}
