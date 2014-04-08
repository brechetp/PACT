package menus;

import adaBoost.Classification;
import iug.ImageMenu;
import iug.ViewControllerInterface;

public class MenuTest extends menu{
	
	
	public MenuTest(ImageMenu image, ViewControllerInterface vci, Classification classi)
	{
		super(image, vci, classi);
	}

	@Override
	public void run() {
		System.out.println("boo");
		
	}

}
