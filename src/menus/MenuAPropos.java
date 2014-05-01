package menus;

import iug.ImageMenu;
import iug.ViewControllerInterface;
import adaBoost.Classification;

public class MenuAPropos extends menu{

	public MenuAPropos(ImageMenu image, ViewControllerInterface vci,
			Classification classi) {
		super(image, vci, classi);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		getVci().modeAPropos();
	}

}
