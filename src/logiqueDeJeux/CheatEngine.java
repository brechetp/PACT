package logiqueDeJeux;

import javax.swing.JOptionPane;

import machineEtat.CardEvent;
import structure.Carte;

public class CheatEngine implements Runnable
{
	private BeloteCoinche belote;
	
	public CheatEngine(BeloteCoinche beloteCoinche) {
		this.belote=beloteCoinche;
	}

	@Override
	public void run() {
		System.out.println("boo cheat");
		while (true) {
			// TODO Auto-generated method stub
			String newCarte = (String) JOptionPane.showInputDialog(null,
					"Carte: ", "New Card", JOptionPane.PLAIN_MESSAGE);

			String[] card = newCarte.split(" ");

			belote.nouvelleCarte(new CardEvent(new Carte(card[0], card[1],
					belote.getEtat())));

		}
		
		
	}

}
