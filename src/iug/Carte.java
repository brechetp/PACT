package iug;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Carte {
	
	private Image image;
	
	public Carte(int n){
		try{
			
			if (n==1){
				image = ImageIO.read(new File("./Cartes/7_trefle.png"));
			}
			
			if (n==2){
				image = ImageIO.read(new File("./Cartes/8_trefle.png"));
			}
			
			if (n==3){
				image = ImageIO.read(new File("./Cartes/9_trefle.png"));
			}
			
			if (n==4){
				image = ImageIO.read(new File("./Cartes/10_trefle.png"));
			}
			
			if (n==5){
				image = ImageIO.read(new File("./Cartes/J_trefle.png"));
			}
			
			if (n==6){
				image = ImageIO.read(new File("./Cartes/Q_trefle.png"));
			}
			
			if (n==7){
				image = ImageIO.read(new File("./Cartes/K_trefle.png"));
			}
			
			if (n==8){
				image = ImageIO.read(new File("./Cartes/A_trefle.png"));
			}
			
			if (n==9){
				image = ImageIO.read(new File("./Cartes/7_pique.png"));
			}
			
			if (n==10){
				image = ImageIO.read(new File("./Cartes/8_pique.png"));
			}
			
			if (n==11){
				image = ImageIO.read(new File("./Cartes/9_pique.png"));
			}
			
			if (n==12){
				image = ImageIO.read(new File("./Cartes/10_pique.png"));
			}
			
			if (n==13){
				image = ImageIO.read(new File("./Cartes/J_pique.png"));
			}
			
			if (n==14){
				image = ImageIO.read(new File("./Cartes/Q_pique.png"));
			}
			
			if (n==15){
				image = ImageIO.read(new File("./Cartes/K_pique.png"));
			}
			
			if (n==16){
				image = ImageIO.read(new File("./Cartes/A_pique.png"));
			}
			
			if (n==17){
				image = ImageIO.read(new File("./Cartes/7_coeur.png"));
			}
			
			if (n==18){
				image = ImageIO.read(new File("./Cartes/8_coeur.png"));
			}
			
			if (n==19){
				image = ImageIO.read(new File("./Cartes/9_coeur.png"));
			}
			
			if (n==20){
				image = ImageIO.read(new File("./Cartes/10_coeur.png"));
			}
			
			if (n==21){
				image = ImageIO.read(new File("./Cartes/J_coeur.png"));
			}
			
			if (n==22){
				image = ImageIO.read(new File("./Cartes/Q_coeur.png"));
			}
			
			if (n==23){
				image = ImageIO.read(new File("./Cartes/K_coeur.png"));
			}
			
			if (n==24){
				image = ImageIO.read(new File("./Cartes/A_coeur.png"));
			}
			
			if (n==25){
				image = ImageIO.read(new File("./Cartes/7_carreau.png"));
			}
			
			if (n==26){
				image = ImageIO.read(new File("./Cartes/8_carreau.png"));
			}
			
			if (n==27){
				image = ImageIO.read(new File("./Cartes/9_carreau.png"));
			}
			
			if (n==28){
				image = ImageIO.read(new File("./Cartes/10_carreau.png"));
			}
			
			if (n==29){
				image = ImageIO.read(new File("./Cartes/J_carreau.png"));
			}
			
			if (n==30){
				image = ImageIO.read(new File("./Cartes/Q_carreau.png"));
			}
			
			if (n==31){
				image = ImageIO.read(new File("./Cartes/K_carreau.png"));
			}
			
			if (n==32){
				image = ImageIO.read(new File("./Cartes/A_carreau.png"));
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
