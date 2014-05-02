package menus;

import iug.ImageMenu;
import iug.ViewControllerInterface;
import adaBoost.Classification;

public class MenuAPropos extends menu{

	public MenuAPropos(ImageMenu image, ViewControllerInterface vci,
			Classification classi,MainMenu mainMenu) {
		super(image, vci, classi, mainMenu);
	}

	@Override
	public void run() {
		for (int secAD = 0 ; secAD < 10 ; secAD ++)
		{
			getVci().modeAPropos(secAD);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		getVci().modeMenu();
		getVci().supprimerMessage();
	}
}
