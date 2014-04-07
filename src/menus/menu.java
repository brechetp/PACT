package menus;

import iug.ImageMenu;

public abstract class menu 
{
	private ImageMenu image;
	
	public abstract void run();
	
	public ImageMenu getImage()
	{
		return image;
	}
}
