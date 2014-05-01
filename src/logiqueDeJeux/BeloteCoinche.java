package logiqueDeJeux;

import java.io.IOException;

import javax.swing.JOptionPane;

import iug.ViewControllerInterface;
import structure.AnnonceInterface;
import machineEtat.*;
import menus.MenuBelote;

public class BeloteCoinche implements GlobalListener
{
	private StateMachine machine;
	private MenuBelote menu;
	
	public BeloteCoinche(ViewControllerInterface vci, MenuBelote menuBelote) throws IOException
	{
		this.menu=menuBelote;
		this.machine = new StateMachine(new JoueurDistant(this), vci,this);
	}
	
	public void nouvelleCarte(CardEvent carte) 
	{
		String label = carte.getCarte().getLabelNum();
		String suit = carte.getCarte().getSuit();
		
		String newCarte;
		if (!isStateDistrib()) 
		{
			newCarte = (String) JOptionPane.showInputDialog(null, "Carte: ",
					"New Card", JOptionPane.PLAIN_MESSAGE, null, null, label
							+ " " + suit);
		}
		
		if (!newCarte.equals("")&&newCarte!=null&&!newCarte.equals(" "))
		{
			String[] card =newCarte.split(" ");
			
			carte.setLabel(card[0]);
			if (card.length>1) {
				carte.setSuit(card[1]);
			}
			synchronized (machine) {
				machine.eventCarte(carte);
			}
		}
	}
	
	public void nouvelleCarteJoueurDistant(CardEvent carte)
	{
		synchronized (machine) {
			machine.eventCarte(carte);
		}
	}

	public void nouveauGeste(MouvementEvent mouvement) 
	{
		System.out.println("Message Recu !!");
		String label = mouvement.getLabel();
		int n=0;
//		int n = JOptionPane.showConfirmDialog(
//			    null,
//			    "Geste : "+label,
//			    "New movement",
//			    JOptionPane.YES_NO_OPTION);
		
		if (n==0) 
		{
			if (mouvement.getLabel() == "passer") {
				synchronized (machine) {
					machine.eventGestePasser();
				}
			} else if (mouvement.getLabel() == "retour") {
				synchronized (machine) {
					machine.eventGesteRetour();
				}
			} else if (mouvement.getLabel() == "coinche") {
				synchronized (machine) {
					machine.eventGesteCoinche();
				}
			} else if (mouvement.getLabel() == "accepter") {
				synchronized (machine) {
					machine.eventGesteAccept();
				}
			} else if (mouvement.getLabel() == "quitter") {
				synchronized (machine) {
					machine.eventQuit();
				}
			}
		}
	}

	public void finPli() 
	{
		System.out.println("fin de pli");
		synchronized (machine) 
		{
			machine.eventFinPli();
		}
	}
	
	public void setAnnonce(AnnonceInterface annonce)
	{
		synchronized (machine) 
		{
			machine.setAnnonce(annonce);
		}
	}

	public EtatDuJeuInterface getEtat() 
	{
		synchronized (machine) 
		{
			return machine.getEtat();
		}
	}

	public boolean isStateDistrib() 
	{
		return machine.isStateDistrib();
		
	}

	public void quit() 
	{
		menu.stopBelote(this);
	}
}
