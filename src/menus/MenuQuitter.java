package menus;

import iug.ImageMenu;
import iug.ViewControllerInterface;
import adaBoost.Classification;

public class MenuQuitter extends menu
{

	public MenuQuitter(ImageMenu image, ViewControllerInterface vci, Classification classi, MainMenu mainMenu) {
		super(image, vci, classi, mainMenu);
	}

	public void run() 
	{
		System.exit(0);
	}
}
