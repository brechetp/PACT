package iug;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageMenu {
	
	private Image image;
	
	public ImageMenu(int i){
		try{
			if(i==1){
				image = ImageIO.read(new File("./testmenus/mode 1.png"));
			}
			if(i==2){
				image = ImageIO.read(new File("./testmenus/mode 2.png"));
			}
			if(i==3){
				image = ImageIO.read(new File("./testmenus/mode 3.png"));
			}
			if(i==4){
				image = ImageIO.read(new File("./testmenus/mode 4.png"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
