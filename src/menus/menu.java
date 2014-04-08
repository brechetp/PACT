package menus;

import adaBoost.Classification;
import iug.ImageMenu;
import iug.ViewControllerInterface;

public abstract class menu 
{
	private ImageMenu image;
	private ViewControllerInterface vci;
	private Classification classi;
	
	public menu(ImageMenu image, ViewControllerInterface vci,Classification classi) 
	{
		this.image = image;
		this.setVci(vci);
		this.setClassi(classi);
	}

	public abstract void run();
	
	public ImageMenu getImage()
	{
		return image;
	}

	public ViewControllerInterface getVci() {
		return vci;
	}

	public void setVci(ViewControllerInterface vci) {
		this.vci = vci;
	}

	public Classification getClassi() {
		return classi;
	}

	public void setClassi(Classification classi) {
		this.classi = classi;
	}
}
