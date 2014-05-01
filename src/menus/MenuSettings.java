package menus;

import java.io.IOException;

import camera.Capture;
import camera.Initialisation;
import iug.ImageMenu;
import iug.ViewControllerInterface;
import adaBoost.Classification;

public class MenuSettings extends menu {

	public MenuSettings(ImageMenu image, ViewControllerInterface vci,
			Classification classi) {
		super(image, vci, classi);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		try {
			Capture.cardSize("data/database/size/");
			Initialisation.setCardSize("data/database/size/size.txt");
		
		} catch (NumberFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		try {
			Capture.symbolDatabase(0, 4, "data/database/symbols/symbol", "data/database/symbols/text");
			Capture.letterDatabase(0, 3, "data/database/letters/letter", "data/database/letters/text");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
