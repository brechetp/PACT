import java.io.IOException;

import adaBoost.BaseApprentissage;
import adaBoost.ClassiFinal;
import adaBoost.Classification;

import com.leapmotion.leap.Controller;

import leapmotion.main.MyListener;
import menus.MainMenu;
import menus.menu;
import structure.CircularArray;
import iug.ViewController;


public class MainFinal {

	public static void main(String[] args) {
		
		ViewController vc = new ViewController();
		menu[] tab = new menu[3];
		//remplir tab
		CircularArray<menu> menus = new CircularArray<menu>(tab);
		MainMenu mainMenu = new MainMenu(menus, vc);
		
		//Classification
		ClassiFinal[] classilol = BaseApprentissage.main(null);
		Classification classi = new Classification(classilol);
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
