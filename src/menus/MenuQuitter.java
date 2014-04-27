package menus;

import iug.ImageMenu;
import iug.ViewControllerInterface;
import adaBoost.Classification;

public class MenuQuitter extends menu
{

	public MenuQuitter(ImageMenu image, ViewControllerInterface vci, Classification classi) {
		super(image, vci, classi);
	}

	public void run() {
		System.exit(0);
	}

}
