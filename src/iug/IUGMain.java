package iug;

public class IUGMain {

	public static void main(String[] args) {
		
		int k = 0;
		
		Fenetre fen = new Fenetre();
		try {
			while(true){
				Thread.sleep(500);
				fen.getPan().repaint();
				fen.getPan().setI(k);
				k++;
				if(k>32){
					k=0;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


	}

}
